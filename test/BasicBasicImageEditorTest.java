import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import cs3500.imageprocessor.model.BasicImageEditor;
import cs3500.imageprocessor.model.RGBAPixel;
import cs3500.imageprocessor.model.ImageEditor;
import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.operations.BrightenPixel;
import cs3500.imageprocessor.operations.DarkenPixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

/**
 * Tests for BasicPPMImageEditor.
 */
public class BasicBasicImageEditorTest {

  @Test
  public void testConstructorInvalid() {
    assertThrows(NullPointerException.class, () -> new BasicImageEditor(null));
  }

  @Test
  public void testConstructorWithMapsHasProperInternalListOfNames() {
    ImageEditor editor = new BasicImageEditor();
    assertEquals(0, editor.getImageNames().size());

    Map<String, ImageState> images = new HashMap<>();
    RGBAPixel[][] pixels = new RGBAPixel[1][1];
    pixels[0][0] = new RGBAPixel(0, 0, 0);
    ImageState image = new ImagePPM(pixels);
    images.put("image1", image);
    editor = new BasicImageEditor(images);
    assertEquals(1, editor.getImageNames().size());
    assertEquals("image1", editor.getImageNames().get(0));
    assertEquals(image, editor.getImage("image1"));
  }

  @Test
  public void testUnwantedMutationWithConstructor() {
    ImageEditor editor = new BasicImageEditor();
    assertEquals(0, editor.getImageNames().size());

    Map<String, ImageState> images = new HashMap<>();
    RGBAPixel[][] pixels = new RGBAPixel[1][1];
    pixels[0][0] = new RGBPixel(0, 0, 0);
    ImageState image = new ImagePPM(pixels);
    images.put("image1", image);
    editor = new BasicImageEditor(images);
    assertEquals(1, editor.getImageNames().size());
    assertEquals("image1", editor.getImageNames().get(0));
    assertEquals(image, editor.getImage("image1"));
    images.clear();
    assertEquals(1, editor.getImageNames().size());
    assertEquals("image1", editor.getImageNames().get(0));
    assertEquals(image, editor.getImage("image1"));
  }

  @Test
  public void testImportNotPPM() {
    ImageEditor editor = new BasicImageEditor();
    assertThrows(UnsupportedOperationException.class,
        () -> editor.importImageFromDisk("image.jpg", "image1"));
    assertThrows(UnsupportedOperationException.class,
        () -> editor.importImageFromDisk("image.png", "image1"));
  }

  /**
   * Tests that the importImageFromDisk method works for PPMs.
   * Doesn't test that an image is loaded properly, since this utilizes ImageUtil's load PPM
   * which is tested elsewhere, just tests that loading PPMs doesn't throw an exception, and they
   * show up in the internal map,
   */
  @Test
  public void testImportPPM() {
    ImageEditor editor = new BasicImageEditor();
    editor.importImageFromDisk("images/Koala.ppm", "koala");
    assertEquals(1, editor.getImageNames().size());
    assertEquals("koala", editor.getImageNames().get(0));
  }

  @Test
  public void testAddImageInvalid() {
    ImageEditor editor = new BasicImageEditor();
    RGBAPixel[][] pixels = new RGBAPixel[1][1];
    pixels[0][0] = new RGBPixel(0, 0, 0);
    ImageState image = new ImagePPM(pixels);
    assertThrows(NullPointerException.class, () -> editor.addImage(null, "image1"));
    assertThrows(NullPointerException.class, () -> editor.addImage(image,
            null));
    assertThrows(NullPointerException.class, () -> editor.addImage(null,
            null));
  }

  @Test
  public void testAddImageValid() {
    RGBAPixel[][] pixels = new RGBAPixel[1][1];
    pixels[0][0] = new RGBPixel(0, 0, 0);
    ImageState image = new ImagePPM(pixels);
    ImageEditor editor = new BasicImageEditor();
    editor.addImage(image, "image1");
    assertEquals(1, editor.getImageNames().size());
    assertEquals("image1", editor.getImageNames().get(0));
    assertEquals(new RGBPixel(0, 0, 0), editor.getImage("image1")
            .getPixelAt(0, 0));
  }

  @Test
  public void testRemoveImageInvalid() {
    ImageEditor editor = new BasicImageEditor();
    assertThrows(NullPointerException.class, () -> editor.removeImage(null));
  }

  @Test
  public void testRemoveImageValid() {
    ImageEditor editor = new BasicImageEditor();
    RGBAPixel[][] pixels = new RGBAPixel[1][1];
    pixels[0][0] = new RGBPixel(0, 0, 0);
    ImageState image = new ImagePPM(pixels);
    editor.addImage(image, "image1");
    assertEquals(1, editor.getImageNames().size());
    assertEquals("image1", editor.getImageNames().get(0));
    editor.removeImage("image1");
    assertEquals(0, editor.getImageNames().size());
  }

  @Test
  public void testGetNames() {
    ImageEditor editor = new BasicImageEditor();
    RGBAPixel[][] pixels = new RGBAPixel[1][1];
    pixels[0][0] = new RGBPixel(0, 0, 0);
    ImageState image = new ImagePPM(pixels);
    ImageState image2 = new ImagePPM(pixels);
    ImageState image3 = new ImagePPM(image2);

    editor.addImage(image, "image1");
    editor.addImage(image2, "image2");
    editor.addImage(image3, "image3");
    assertEquals(3, editor.getImageNames().size());
    assertTrue(editor.getImageNames().contains("image1"));
    assertTrue(editor.getImageNames().contains("image2"));
    assertTrue(editor.getImageNames().contains("image3"));

    Map<String, ImageState> images = new HashMap<>();
    images.put("image1", image);
    images.put("image2", image2);
    editor = new BasicImageEditor(images);
    assertEquals(2, editor.getImageNames().size());
    assertTrue(editor.getImageNames().contains("image1"));
    assertTrue(editor.getImageNames().contains("image2"));
    editor.addImage(image3, "image3");
    assertEquals(3, editor.getImageNames().size());
    assertTrue(editor.getImageNames().contains("image1"));
    assertTrue(editor.getImageNames().contains("image2"));
    assertTrue(editor.getImageNames().contains("image3"));
  }

  @Test
  public void testGetImage() {
    RGBAPixel[][] pixels = new RGBAPixel[1][1];
    pixels[0][0] = new RGBPixel(0, 0, 0);
    ImageState image = new ImagePPM(pixels);
    ImageState image2 = new ImagePPM(pixels);
    ImageState image3 = new ImagePPM(image2);
    Map<String, ImageState> images = new HashMap<>();
    images.put("image1", image);
    images.put("image2", image2);
    ImageEditor editor = new BasicImageEditor(images);
    assertEquals(image, editor.getImage("image1"));
    assertEquals(image2, editor.getImage("image2"));
    editor.addImage(image3, "image3");
    assertEquals(image3, editor.getImage("image3"));
  }

  @Test
  public void testApplyAndSaveBrighten() {
    ImageEditor editor = new BasicImageEditor();
    RGBAPixel[][] pixels = new RGBAPixel[3][3];
    pixels[0][0] = new RGBPixel(1, 0, 0);
    pixels[0][1] = new RGBPixel(1, 0, 0);
    pixels[0][2] = new RGBPixel(1, 0, 0);
    pixels[1][0] = new RGBPixel(0, 1, 0);
    pixels[1][1] = new RGBPixel(0, 1, 0);
    pixels[1][2] = new RGBPixel(0, 1, 0);
    pixels[2][0] = new RGBPixel(0, 0, 1);
    pixels[2][1] = new RGBPixel(0, 0, 1);
    pixels[2][2] = new RGBPixel(0, 0, 1);
    ImageState image = new ImagePPM(pixels);
    editor.addImage(image, "image1");
    editor.applyFilterAndSave("image1", "image2", new BrightenPixel(10));
    ImageState newImage = editor.getImage("image2");
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(new RGBPixel(pixels[i][j].getRed() + 10, pixels[i][j].getGreen() + 10,
            pixels[i][j].getBlue() + 10), newImage.getPixelAt(i, j));
      }
    }
  }

  @Test
  public void testApplyAndSaveDarken() {
    ImageEditor editor = new BasicImageEditor();
    RGBAPixel[][] pixels = new RGBAPixel[3][3];
    pixels[0][0] = new RGBPixel(11, 10, 10);
    pixels[0][1] = new RGBPixel(11, 10, 10);
    pixels[0][2] = new RGBPixel(11, 10, 10);
    pixels[1][0] = new RGBPixel(10, 11, 10);
    pixels[1][1] = new RGBPixel(10, 11, 10);
    pixels[1][2] = new RGBPixel(10, 11, 10);
    pixels[2][0] = new RGBPixel(10, 10, 11);
    pixels[2][1] = new RGBPixel(10, 10, 11);
    pixels[2][2] = new RGBPixel(10, 10, 11);
    ImageState image = new ImagePPM(pixels);
    editor.addImage(image, "image1");
    editor.applyFilterAndSave("image1", "image2", new DarkenPixel(10));
    ImageState newImage = editor.getImage("image2");
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(new RGBPixel(pixels[i][j].getRed() - 10, pixels[i][j].getGreen() - 10,
            pixels[i][j].getBlue() - 10), newImage.getPixelAt(i, j));
      }
    }
  }
}