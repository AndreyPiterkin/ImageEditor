import org.junit.Test;

import java.io.InputStreamReader;
import java.io.StringReader;

import cs3500.imageprocessor.controller.BasicEditorController;
import cs3500.imageprocessor.controller.ImageEditorController;
import cs3500.imageprocessor.model.BasicImageEditor;
import cs3500.imageprocessor.model.ImageEditor;
import cs3500.imageprocessor.view.ImageEditorViewStub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

/**
 * Tests the functionality of the BasicEditorController class.
 */
public class BasicEditorControllerTest {

  @Test
  public void testBasicEditorControllerConstructorInvalid() {
    assertThrows(NullPointerException.class, () ->
        new BasicEditorController(null, null, null));
    ImageEditor editor = new BasicImageEditor();
    assertThrows(NullPointerException.class, () ->
        new BasicEditorController(editor, null, null));
    assertThrows(NullPointerException.class, () ->
        new BasicEditorController(editor, new InputStreamReader(System.in), null));
    assertThrows(NullPointerException.class, () ->
        new BasicEditorController(editor, null, new ImageEditorViewStub()));
    assertThrows(NullPointerException.class, () ->
        new BasicEditorController(null, new InputStreamReader(System.in),
        new ImageEditorViewStub()));
    assertThrows(NullPointerException.class, () ->
            new BasicEditorController(null, null,
            new ImageEditorViewStub()));
    assertThrows(NullPointerException.class, () ->
        new BasicEditorController(null,null));
  }

  @Test
  public void testControllerUsesEditorModelCorrectly() {
    ImageEditor editor = new BasicImageEditor();
    Appendable out = new StringBuilder();
    MockEditorModel model = new MockEditorModel(editor, out);
    ImageEditorViewStub view = new ImageEditorViewStub();

    Readable in = new StringReader("load images/testReadPPM1.ppm test\n" +
        "darken 0 test test2\n" +
        "brighten 0 test2 test3\n" +
        "vertical-flip test3 test4\n" +
        "save test4 images/test5.ppm");
    ImageEditorController controller = new BasicEditorControllerLogger(model, in, view, out);
    controller.start();
    String expected = "add image. image name: test\n" +
        "get image. name: test\n" +
        "apply + darken 0 0\n" +
        "apply + darken 0 1\n" +
        "apply + darken 1 0\n" +
        "apply + darken 1 1\n" +
        "add image. image name: test2\n" +
        "get image. name: test2\n" +
        "apply + brighten 0 0\n" +
        "apply + brighten 0 1\n" +
        "apply + brighten 1 0\n" +
        "apply + brighten 1 1\n" +
        "add image. image name: test3\n" +
        "get image. name: test3\n" +
        "apply + vertical-flip 0 0\n" +
        "apply + vertical-flip 0 1\n" +
        "apply + vertical-flip 1 0\n" +
        "apply + vertical-flip 1 1\n" +
        "add image. image name: test4\n" +
        "get image. name: test4\n" +
        "Writing image to images/test5.ppm in ppm format.\n";
    assertEquals(expected, out.toString());

    editor = new BasicImageEditor();
    out = new StringBuilder();
    model = new MockEditorModel(editor, out);
    view = new ImageEditorViewStub();

    in = new StringReader("load images/testReadPPM1.ppm test\n" +
            "brighten 0 test test2\n" +
            "darken 0 test2 test3\n" +
            "vertical-flip test3 test4\n" +
            "save test4 images/test4.ppm");
    controller = new BasicEditorControllerLogger(model, in, view, out);
    controller.start();
    expected = "add image. image name: test\n" +
        "get image. name: test\n" +
        "apply + brighten 0 0\n" +
        "apply + brighten 0 1\n" +
        "apply + brighten 1 0\n" +
        "apply + brighten 1 1\n" +
        "add image. image name: test2\n" +
        "get image. name: test2\n" +
        "apply + darken 0 0\n" +
        "apply + darken 0 1\n" +
        "apply + darken 1 0\n" +
        "apply + darken 1 1\n" +
        "add image. image name: test3\n" +
        "get image. name: test3\n" +
        "apply + vertical-flip 0 0\n" +
        "apply + vertical-flip 0 1\n" +
        "apply + vertical-flip 1 0\n" +
        "apply + vertical-flip 1 1\n" +
        "add image. image name: test4\n" +
        "get image. name: test4\n" +
        "Writing image to images/test4.ppm in ppm format.\n";
    assertEquals(expected, out.toString());

  }

  /**
   * Tests that the controller can load images of different formats and that it adds them to the
   * model without errors.
   */
  @Test
  public void testLoadImagesCorrectly() {
    ImageEditor editor = new BasicImageEditor();
    Appendable out = new StringBuilder();
    MockEditorModel model = new MockEditorModel(editor, out);
    ImageEditorViewStub view = new ImageEditorViewStub();

    Readable in = new StringReader("load res/cat.ppm cat1\nload res/catblur.bmp cat\n" +
        "load res/catblur.jpg cat2\nload res/catblur.png cat3\n");
    ImageEditorController controller = new BasicEditorControllerLogger(model, in, view, out);
    controller.start();
    String expected = "add image. image name: cat1\n" +
        "add image. image name: cat\n" +
        "add image. image name: cat2\n" +
        "add image. image name: cat3\n";
    assertEquals(expected, out.toString());
    assertEquals(4, editor.getImageNames().size());
  }

  /**
   * Tests that the controller can save images of different formats.
   */
  @Test
  public void testSaveImagesCorrectlyLoggerController() {
    ImageEditor editor = new BasicImageEditor();
    Appendable out = new StringBuilder();
    MockEditorModel model = new MockEditorModel(editor, out);
    ImageEditorViewStub view = new ImageEditorViewStub();

    Readable in = new StringReader("load res/cat.ppm cat\n" +
        "save cat images/cat.ppm\n" +
        "save cat images/cat.bmp\n" +
        "save cat images/cat.jpg\n" +
        "save cat images/cat.png\n");
    ImageEditorController controller = new BasicEditorControllerLogger(model, in, view, out);
    controller.start();
    String expected = "add image. image name: cat\n" +
        "get image. name: cat\n" +
        "Writing image to images/cat.ppm in ppm format.\n" +
        "get image. name: cat\n" +
        "Writing image to images/cat.bmp in bmp format.\n" +
        "get image. name: cat\n" +
        "Writing image to images/cat.jpg in jpg format.\n" +
        "get image. name: cat\n" +
        "Writing image to images/cat.png in png format.\n";
    assertEquals(expected, out.toString());
    assertEquals(1, editor.getImageNames().size());
  }

  @Test
  public void testSaveImagesCorrectlyRegController() {
    ImageEditor editor = new BasicImageEditor();
    Appendable out = new StringBuilder();
    MockEditorModel model = new MockEditorModel(editor, out);
    ImageEditorViewStub view = new ImageEditorViewStub();

    Readable in = new StringReader("load res/cat.ppm cat\n" +
            "save cat images/cat.ppm\n" +
            "save cat images/cat.bmp\n" +
            "save cat images/cat.jpg\n" +
            "save cat images/cat.png\n");
    ImageEditorController controller = new BasicEditorController(model, in, view);
    controller.start();
    String expected = "add image. image name: cat\n" +
            "get image. name: cat\n" +
            "get image. name: cat\n" +
            "get image. name: cat\n" +
            "get image. name: cat\n";
    assertEquals(expected, out.toString());
    assertEquals(1, editor.getImageNames().size());
  }

  /**
   * Tests that the controller will error if given an unknown file format.
   * (It will still log that it is trying to save the image).
   */
  @Test (expected = UnsupportedOperationException.class)
  public void testInvalidWriteFormatLoggerController() {
    ImageEditor editor = new BasicImageEditor();
    Appendable out = new StringBuilder();
    MockEditorModel model = new MockEditorModel(editor, out);
    ImageEditorViewStub view = new ImageEditorViewStub();

    Readable in = new StringReader("load res/cat.ppm cat\n" +
        "save cat images/cat.gif\n");
    ImageEditorController controller = new BasicEditorControllerLogger(model, in, view, out);
    controller.start();
    String expected = "add image. image name: cat\n" +
        "get image. name: cat\n" +
        "Writing image to images/cat.gif in gif format.\n";
    assertEquals(expected, out.toString());
    assertEquals(1, editor.getImageNames().size());
  }

  @Test (expected = UnsupportedOperationException.class)
  public void testInvalidWriteFormatRegController() {
    ImageEditor editor = new BasicImageEditor();
    Appendable out = new StringBuilder();
    MockEditorModel model = new MockEditorModel(editor, out);
    ImageEditorViewStub view = new ImageEditorViewStub();

    Readable in = new StringReader("load res/cat.ppm cat\n" +
            "save cat images/cat.gif\n");
    ImageEditorController controller = new BasicEditorController(model, in, view);
    controller.start();
    String expected = "add image. image name: cat\n" +
            "get image. name: cat\n";
    assertEquals(expected, out.toString());
    assertEquals(1, editor.getImageNames().size());
  }

  @Test
  public void testInvalidCommand() {
    ImageEditor editor = new BasicImageEditor();
    Appendable out = new StringBuilder();
    MockEditorModel model = new MockEditorModel(editor, out);
    ImageEditorViewStub view = new ImageEditorViewStub();

    Readable in = new StringReader("xyzw 10 12 30");
    ImageEditorController controller = new BasicEditorController(model, in, view);
    try {
      controller.start();
      fail("Invalid command should throw an exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid operation: xyzw", e.getMessage());
    }

    editor = new BasicImageEditor();
    out = new StringBuilder();
    model = new MockEditorModel(editor, out);
    view = new ImageEditorViewStub();

    in = new StringReader("load res/cat.ppm cat\ndarken 10 cat cat2 cat3\n");
    controller = new BasicEditorController(model, in, view);
    try {
      controller.start();
      fail("Invalid number of arguments will roll over and produce invalid operation exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid operation: cat3", e.getMessage());
    }

    editor = new BasicImageEditor();
    out = new StringBuilder();
    model = new MockEditorModel(editor, out);
    view = new ImageEditorViewStub();

    in = new StringReader("load res/cat.ppm cat\ndarken 10 cat \n");
    controller = new BasicEditorController(model, in, view);
    try {
      controller.start();
      fail("Invalid number of arguments will roll over and produce invalid operation exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Not enough arguments", e.getMessage());
    }
  }

  @Test
  public void invalidScan() {
    ImageEditor editor = new BasicImageEditor();
    Appendable out = new StringBuilder();
    MockEditorModel model = new MockEditorModel(editor, out);
    ImageEditorViewStub view = new ImageEditorViewStub();

    Readable in = new StringReader("darken x");
    ImageEditorController controller = new BasicEditorController(model, in, view);
    try {
      controller.start();
      fail("Invalid input");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input", e.getMessage());
    }
  }

}
