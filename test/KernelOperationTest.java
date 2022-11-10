import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.image.BufferedImage;

import cs3500.imageprocessor.model.BasicImage;
import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.model.RGBAPixel;
import cs3500.imageprocessor.operations.KernelOperation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Tests for KernelOperation.
 */
public class KernelOperationTest {
  private KernelOperation kernelOperation;
  private KernelOperation kernelOperation2;
  private ImageState image2x2;
  private ImageState image3x3;

  @Before
  public void initData() {
    double[][] kernel1 = {{1, 1}, {1, 1}};
    double[][] kernel2 = {{2.5, 2.5, 2.5}, {2.5, 2.5, 2.5}, {2.5, 2.5, 2.5}};
    this.kernelOperation = new KernelOperation(kernel1);
    this.kernelOperation2 = new KernelOperation(kernel2);

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
    double[][] kernelNull = {null};
    assertThrows(IllegalArgumentException.class, () -> this.kernelOperation =
        new KernelOperation(kernel1x3));
    assertThrows(IllegalArgumentException.class, () -> this.kernelOperation =
        new KernelOperation(kernel3x1));
    assertThrows(NullPointerException.class, () -> this.kernelOperation =
        new KernelOperation(null));
    assertThrows(NullPointerException.class, () -> this.kernelOperation =
        new KernelOperation(kernelNull));
  }

  @Test
  public void testApply() {
    assertEquals(new RGBAPixel(1, 2, 3), this.kernelOperation.apply(this.image2x2, 0, 0));
    assertEquals(new RGBAPixel(5, 7, 9), this.kernelOperation.apply(this.image2x2, 0, 1));
    assertEquals(new RGBAPixel(8, 10, 12), this.kernelOperation.apply(this.image2x2, 1, 0));
    assertEquals(new RGBAPixel(22, 26, 30), this.kernelOperation.apply(this.image2x2, 1, 1));

    assertEquals(new RGBAPixel(54, 64, 74), this.kernelOperation2.apply(this.image2x2, 0, 0));
    assertEquals(new RGBAPixel(54, 64, 74), this.kernelOperation2.apply(this.image2x2, 0, 1));
    assertEquals(new RGBAPixel(54, 64, 74), this.kernelOperation2.apply(this.image2x2, 1, 0));
    assertEquals(new RGBAPixel(54, 64, 74), this.kernelOperation2.apply(this.image2x2, 1, 1));

    assertEquals(new RGBAPixel(48, 60, 68), this.kernelOperation2.apply(this.image3x3, 0, 0));
    assertEquals(new RGBAPixel(87, 105, 117), this.kernelOperation2.apply(this.image3x3, 0, 1));
    assertEquals(new RGBAPixel(68, 80, 88), this.kernelOperation2.apply(this.image3x3, 0, 2));
    assertEquals(new RGBAPixel(117, 135, 147), this.kernelOperation2.apply(this.image3x3, 1, 0));
    assertEquals(new RGBAPixel(198, 225, 243), this.kernelOperation2.apply(this.image3x3, 1, 1));
    assertEquals(new RGBAPixel(147, 165, 177), this.kernelOperation2.apply(this.image3x3, 1, 2));
    assertEquals(new RGBAPixel(108, 120, 128), this.kernelOperation2.apply(this.image3x3, 2, 0));
    assertEquals(new RGBAPixel(177, 195, 207), this.kernelOperation2.apply(this.image3x3, 2, 1));
    assertEquals(new RGBAPixel(128, 140, 148), this.kernelOperation2.apply(this.image3x3, 2, 2));
  }
}
