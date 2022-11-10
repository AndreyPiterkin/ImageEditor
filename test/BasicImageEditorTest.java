import org.junit.Test;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import cs3500.imageprocessor.model.BasicImage;
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
 * Tests for BasicImageEditor.
 */
public class BasicImageEditorTest {

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
    BufferedImage i = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    i.setRGB(0, 0, 0);
    ImageState image = new BasicImage(i);
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
    pixels[0][0] = new RGBAPixel(0, 0, 0);
    BufferedImage i = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    i.setRGB(0, 0, 0);
    ImageState image = new BasicImage(i);
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
  public void testAddImageInvalid() {
    ImageEditor editor = new BasicImageEditor();
    RGBAPixel[][] pixels = new RGBAPixel[1][1];
    pixels[0][0] = new RGBAPixel(0, 0, 0);
    BufferedImage i = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    i.setRGB(0, 0, 0);
    ImageState image = new BasicImage(i);
    assertThrows(NullPointerException.class, () -> editor.addImage(null, "image1"));
    assertThrows(NullPointerException.class, () -> editor.addImage(image,
            null));
    assertThrows(NullPointerException.class, () -> editor.addImage(null,
            null));
  }

  @Test
  public void testAddImageValid() {
    RGBAPixel[][] pixels = new RGBAPixel[1][1];
    pixels[0][0] = new RGBAPixel(0, 0, 0);
    BufferedImage i = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    i.setRGB(0, 0, 0);
    ImageState image = new BasicImage(i);
    ImageEditor editor = new BasicImageEditor();
    editor.addImage(image, "image1");
    assertEquals(1, editor.getImageNames().size());
    assertEquals("image1", editor.getImageNames().get(0));
    assertEquals(new RGBAPixel(0, 0, 0), editor.getImage("image1")
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
    pixels[0][0] = new RGBAPixel(0, 0, 0);
    BufferedImage i = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    i.setRGB(0, 0, 0);
    ImageState image = new BasicImage(i);
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
    pixels[0][0] = new RGBAPixel(0, 0, 0);
    BufferedImage i = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    i.setRGB(0, 0, 0);
    ImageState image = new BasicImage(i);
    ImageState image2 = new BasicImage(i);
    ImageState image3 = new BasicImage(i);

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
    pixels[0][0] = new RGBAPixel(0, 0, 0);
    BufferedImage i = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    i.setRGB(0, 0, 0);
    ImageState image = new BasicImage(i);
    ImageState image2 = new BasicImage(i);
    ImageState image3 = new BasicImage(i);
    Map<String, ImageState> images = new HashMap<>();
    images.put("image1", image);
    images.put("image2", image2);
    ImageEditor editor = new BasicImageEditor(images);
    assertEquals(image, editor.getImage("image1"));
    assertEquals(image2, editor.getImage("image2"));
    editor.addImage(image3, "image3");
    assertEquals(image3, editor.getImage("image3"));
  }

}