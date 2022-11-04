import org.junit.Test;

import java.io.InputStreamReader;
import java.io.StringReader;

import cs3500.imageprocessor.controller.BasicEditorController;
import cs3500.imageprocessor.controller.ImageEditorController;
import cs3500.imageprocessor.model.BasicPPMImageEditor;
import cs3500.imageprocessor.model.ImageEditor;
import cs3500.imageprocessor.model.ImageState;
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
    ImageEditor editor = new BasicPPMImageEditor();
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
    ImageEditor editor = new BasicPPMImageEditor();
    Appendable out = new StringBuilder();
    MockEditorModel model = new MockEditorModel(editor, out);
    ImageEditorViewStub view = new ImageEditorViewStub();

    Readable in = new StringReader("load images/Koala.ppm koala\n" +
        "darken 0 koala koala2\n" +
        "brighten 0 koala2 koala3\n" +
        "vertical-flip koala3 koala4\n" +
        "save images/koala5.ppm koala4");
    ImageEditorController controller = new BasicEditorController(model, in, view);
    controller.start();
    String expected = "import from disk. file path: images/Koala.ppm name: koala\n"
        + "apply filter and save. name: koala new name: koala2\n"
        + "apply filter and save. name: koala2 new name: koala3\n"
        + "apply filter and save. name: koala3 new name: koala4\n"
        + "get image. name: koala4\n";
    assertEquals(expected, out.toString());

    editor = new BasicPPMImageEditor();
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

    // check to make sure koala4.ppm is the same as koala.ppm, since we performed inverse operation
    // on koala4.ppm
    editor.importImageFromDisk("images/koala4.ppm", "koala10");
    editor.importImageFromDisk("images/koala.ppm", "koala11");
    ImageState k1 = editor.getImage("koala10");
    ImageState k2 = editor.getImage("koala11");
    for (int i = 0; i < k1.getHeight(); i++) {
      for (int j = 0; j < k1.getWidth(); j++) {
        assertEquals(k1.getPixelAt(i, j), k2.getPixelAt(i, j));
      }
    }
  }

  @Test
  public void testInvalidCommand() {
    ImageEditor editor = new BasicPPMImageEditor();
    Appendable out = new StringBuilder();
    MockEditorModel model = new MockEditorModel(editor, out);
    ImageEditorViewStub view = new ImageEditorViewStub();

    Readable in = new StringReader("xyzw 10 12 30");
    ImageEditorController controller = new BasicEditorController(model, in, view);
    try {
      controller.start();
      fail("Invalid command should throw an exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid command", e.getMessage());
    }
  }

  @Test
  public void invalidScan() {
    ImageEditor editor = new BasicPPMImageEditor();
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
