import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.image.BufferedImage;

import cs3500.imageprocessor.model.BasicImage;
import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.model.RGBAPixel;
import cs3500.imageprocessor.operations.ColorOperation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Tests for ColorOperation.
 */
public class ColorOperationTest {
  private ColorOperation colorOperation;
  private ColorOperation colorOperation2;
  private ImageState image2x2;
  private ImageState image3x3;

  @Before
  public void initData() {
    double[][] kernel1 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
    double[][] kernel2 = {{2.5, 2.5, 2.5}, {2.5, 2.5, 2.5}, {2.5, 2.5, 2.5}};
    this.colorOperation = new ColorOperation(kernel1);
    this.colorOperation2 = new ColorOperation(kernel2);

    RGBAPixel[][] initPixels2x2 = {
        {new RGBAPixel(1, 2, 3),
            new RGBAPixel(4, 5, 6)},
        {new RGBAPixel(7, 8, 9),
            new RGBAPixel(10, 11, 12)}};
    RGBAPixel[][] initPixels3x3 = {
        {new RGBAPixel(1, 2, 3),
            new RGBAPixel(3, 4, 5),
            new RGBAPixel(5, 6, 7)},
        {new RGBAPixel(7, 8, 9),
            new RGBAPixel(9, 10, 11),
            new RGBAPixel(11, 12, 13)},
        {new RGBAPixel(13, 14, 15),
            new RGBAPixel(15, 16, 17),
            new RGBAPixel(17, 18, 19)}};

    BufferedImage img1 = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB);
    for (int r = 0; r < initPixels2x2.length; r++) {
      for (int c = 0; c < initPixels2x2[r].length; c++) {
        RGBAPixel pixel = initPixels2x2[r][c];
        Color color = new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue());
        img1.setRGB(c, r, color.getRGB());
      }
    }

    BufferedImage img2 = new BufferedImage(3, 3, BufferedImage.TYPE_INT_RGB);
    for (int r = 0; r < initPixels3x3.length; r++) {
      for (int c = 0; c < initPixels3x3[r].length; c++) {
        RGBAPixel pixel = initPixels3x3[r][c];
        Color color = new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue());
        img2.setRGB(c, r, color.getRGB());
      }
    }

    this.image2x2 = new BasicImage(img1);
    this.image3x3 = new BasicImage(img2);
  }

  @Test
  public void testConstructorExns() {
    double[][] kernel1x3 = {{1, 2, 3}};
    double[][] kernel3x1 = {{1}, {2}, {3}};
    double[][] kernel1x1 = {{1}};
    assertThrows(IllegalArgumentException.class, () -> this.colorOperation =
        new ColorOperation(kernel1x3));
    assertThrows(IllegalArgumentException.class, () -> this.colorOperation =
        new ColorOperation(kernel3x1));
    assertThrows(IllegalArgumentException.class, () -> this.colorOperation =
        new ColorOperation(kernel1x1));
  }

  @Test
  public void testApply() {
    assertEquals(new RGBAPixel(6), this.colorOperation.apply(this.image2x2, 0, 0));
    assertEquals(new RGBAPixel(15), this.colorOperation.apply(this.image2x2, 0, 1));
    assertEquals(new RGBAPixel(24), this.colorOperation.apply(this.image2x2, 1, 0));
    assertEquals(new RGBAPixel(33), this.colorOperation.apply(this.image2x2, 1, 1));

    assertEquals(new RGBAPixel(6), this.colorOperation.apply(this.image3x3, 0, 0));
    assertEquals(new RGBAPixel(12), this.colorOperation.apply(this.image3x3, 0, 1));
    assertEquals(new RGBAPixel(18), this.colorOperation.apply(this.image3x3, 0, 2));
    assertEquals(new RGBAPixel(24), this.colorOperation.apply(this.image3x3, 1, 0));
    assertEquals(new RGBAPixel(30), this.colorOperation.apply(this.image3x3, 1, 1));
    assertEquals(new RGBAPixel(36), this.colorOperation.apply(this.image3x3, 1, 2));
    assertEquals(new RGBAPixel(42), this.colorOperation.apply(this.image3x3, 2, 0));
    assertEquals(new RGBAPixel(48), this.colorOperation.apply(this.image3x3, 2, 1));
    assertEquals(new RGBAPixel(54), this.colorOperation.apply(this.image3x3, 2, 2));

    assertEquals(new RGBAPixel(15), this.colorOperation2.apply(this.image2x2, 0, 0));
    assertEquals(new RGBAPixel(37), this.colorOperation2.apply(this.image2x2, 0, 1));
    assertEquals(new RGBAPixel(60), this.colorOperation2.apply(this.image2x2, 1, 0));
    assertEquals(new RGBAPixel(82), this.colorOperation2.apply(this.image2x2, 1, 1));

    assertEquals(new RGBAPixel(15), this.colorOperation2.apply(this.image3x3, 0, 0));
    assertEquals(new RGBAPixel(30), this.colorOperation2.apply(this.image3x3, 0, 1));
    assertEquals(new RGBAPixel(45), this.colorOperation2.apply(this.image3x3, 0, 2));
    assertEquals(new RGBAPixel(60), this.colorOperation2.apply(this.image3x3, 1, 0));
    assertEquals(new RGBAPixel(75), this.colorOperation2.apply(this.image3x3, 1, 1));
    assertEquals(new RGBAPixel(90), this.colorOperation2.apply(this.image3x3, 1, 2));
    assertEquals(new RGBAPixel(105), this.colorOperation2.apply(this.image3x3, 2, 0));
    assertEquals(new RGBAPixel(120), this.colorOperation2.apply(this.image3x3, 2, 1));
    assertEquals(new RGBAPixel(135), this.colorOperation2.apply(this.image3x3, 2, 2));
  }
}
