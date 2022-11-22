import org.junit.Before;
import org.junit.Test;

import cs3500.imageprocessor.controller.BasicGUIController;
import cs3500.imageprocessor.controller.BasicProcessorFeatures;
import cs3500.imageprocessor.controller.Features;
import cs3500.imageprocessor.controller.GUIEditorController;
import cs3500.imageprocessor.model.BasicImageEditor;
import cs3500.imageprocessor.model.ImageEditor;
import cs3500.imageprocessor.view.ImageEditorGUIView;
import cs3500.imageprocessor.view.JFrameView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Tests BasicGUIController.
 */
public class BasicGUIControllerTest {
  private Appendable out;
  private ImageEditor model;
  private ImageEditorGUIView view;
  private GUIEditorController controller;
  private Features features;

  @Before
  public void initData() {
    ImageEditor editor = new BasicImageEditor();
    this.out = new StringBuilder();
    this.model = new MockEditorModel(editor, this.out);
    this.view = new JFrameViewStub();
    this.controller = new BasicGUIControllerLogger(this.model, this.view, this.out);
    this.features = new BasicProcessorFeatures(controller);
  }

  @Test
  public void testBasicGUIControllerConstructorInvalid() {
    assertThrows(NullPointerException.class, () ->
        new BasicGUIController(null, null));
    ImageEditor editor = new BasicImageEditor();
    assertThrows(NullPointerException.class, () ->
        new BasicGUIController(editor, null));
    assertThrows(NullPointerException.class, () ->
        new BasicGUIController(null, new JFrameView()));
  }

  @Test
  public void testControllerUsesEditorModelCorrectly() {
    features.load("images/testReadPPM1.ppm");
    features.darken(0);
    features.brighten(0);
    features.flipVertical();
    features.save("images/test5.ppm");
    String expected = "add image. image name: curr-image\n" +
        "get image. name: curr-image\n" +
        "apply class cs3500.imageprocessor.operations.DarkenPixel\n" +
        "apply class cs3500.imageprocessor.operations.BrightenPixel\n" +
        "apply class cs3500.imageprocessor.operations.FlipVertical\n" +
        "get image. name: curr-image\n" +
        "Writing image to images/test5.ppm in ppm format.\n";
    assertEquals(expected, out.toString());

    initData();

    features.load("images/testReadPPM1.ppm");
    features.brighten(0);
    features.darken(0);
    features.flipVertical();
    features.save("images/test4.ppm");
    expected = "add image. image name: curr-image\n" +
        "get image. name: curr-image\n" +
        "apply class cs3500.imageprocessor.operations.BrightenPixel\n" +
        "apply class cs3500.imageprocessor.operations.DarkenPixel\n" +
        "apply class cs3500.imageprocessor.operations.FlipVertical\n" +
        "get image. name: curr-image\n" +
        "Writing image to images/test4.ppm in ppm format.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Tests that the controller can load images of different formats and that it adds them to the
   * model without errors.
   */
  @Test
  public void testLoadImagesCorrectly() {
    features.load("res/cat.ppm");
    features.load("res/catblur.bmp");
    features.load("res/catblur.jpg");
    features.load("res/catblur.png");

    String expected = "add image. image name: curr-image\n" +
        "get image. name: curr-image\n" +
        "add image. image name: curr-image\n" +
        "get image. name: curr-image\n" +
        "add image. image name: curr-image\n" +
        "get image. name: curr-image\n" +
        "add image. image name: curr-image\n" +
        "get image. name: curr-image\n";
    assertEquals(expected, out.toString());

  }

  /**
   * Tests that the controller can save images of different formats.
   */
  @Test
  public void testSaveImagesCorrectlyLoggerController() {
    features.load("res/cat.ppm");
    features.save("images/cat.ppm");
    features.save("images/cat.bmp");
    features.save("images/cat.jpg");
    features.save("images/cat.png");
    String expected = "add image. image name: curr-image\n" +
        "get image. name: curr-image\n" +
        "get image. name: curr-image\n" +
        "Writing image to images/cat.ppm in ppm format.\n" +
        "get image. name: curr-image\n" +
        "Writing image to images/cat.bmp in bmp format.\n" +
        "get image. name: curr-image\n" +
        "Writing image to images/cat.jpg in jpg format.\n" +
        "get image. name: curr-image\n" +
        "Writing image to images/cat.png in png format.\n";
    assertEquals(expected, out.toString());
  }

  @Test
  public void testSaveImagesCorrectlyRegController() {
    this.controller = new BasicGUIController(model, view);
    this.features = new BasicProcessorFeatures(controller);
    features.load("res/cat.ppm");
    features.save("images/cat.ppm");
    features.save("images/cat.bmp");
    features.save("images/cat.jpg");
    features.save("images/cat.png");
    String expected = "add image. image name: curr-image\n" +
        "get image. name: curr-image\n" +
        "get image. name: curr-image\n" +
        "get image. name: curr-image\n" +
        "get image. name: curr-image\n" +
        "get image. name: curr-image\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Tests that the controller will error if given an unknown file format.
   * (It will still log that it is trying to save the image).
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidWriteFormatLoggerController() {
    features.load("res/cat.ppm");
    features.save("images/cat.gif");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidWriteFormatRegController() {
    this.controller = new BasicGUIController(model, view);
    this.features = new BasicProcessorFeatures(this.controller);
    features.load("res/cat.ppm");
    features.save("images/cat.gif");
  }
}