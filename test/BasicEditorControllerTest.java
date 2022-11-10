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

    Readable in = new StringReader("load images/Koala.ppm koala\n" +
        "darken 0 koala koala2\n" +
        "brighten 0 koala2 koala3\n" +
        "vertical-flip koala3 koala4\n" +
        "save koala4 images/koala5.ppm");
    ImageEditorController controller = new BasicEditorControllerLogger(model, in, view, out);
    controller.start();
    String expected = "add image. image name: koala\n" +
    "apply filter darken\n" +
    "get image. name: koala\n" +
    "add image. image name: koala2\n" +
    "apply filter brighten\n" +
    "get image. name: koala2\n" +
    "add image. image name: koala3\n" +
    "apply filter vertical-flip\n" +
    "get image. name: koala3\n" +
    "add image. image name: koala4\n" +
    "get image. name: koala4\n" +
    "Writing image to images/koala5.ppm in ppm format.\n";
    assertEquals(expected, out.toString());

    editor = new BasicImageEditor();
    out = new StringBuilder();
    model = new MockEditorModel(editor, out);
    view = new ImageEditorViewStub();

    in = new StringReader("load images/koala5.ppm koala\n" +
            "brighten 0 koala koala2\n" +
            "darken 0 koala2 koala3\n" +
            "vertical-flip koala3 koala4\n" +
            "save images/koala4.ppm koala4");
    controller = new BasicEditorController(model, in, view);
    controller.start();
    expected = "import from disk. file path: images/koala5.ppm name: koala\n"
            + "apply filter and save. name: koala new name: koala2\n"
            + "apply filter and save. name: koala2 new name: koala3\n"
            + "apply filter and save. name: koala3 new name: koala4\n"
            + "get image. name: koala4\n";
    assertEquals(expected, out.toString());

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

    in = new StringReader("load images/Koala.ppm koala\ndarken 10 koala koala2 koala3\n");
    controller = new BasicEditorController(model, in, view);
    try {
      controller.start();
      fail("Invalid number of arguments will roll over and produce invalid operation exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid operation: koala3", e.getMessage());
    }

    editor = new BasicImageEditor();
    out = new StringBuilder();
    model = new MockEditorModel(editor, out);
    view = new ImageEditorViewStub();

    in = new StringReader("load images/Koala.ppm koala\ndarken 10 koala \n");
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
