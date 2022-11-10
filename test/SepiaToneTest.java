import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import cs3500.imageprocessor.model.BasicImage;
import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.model.RGBAPixel;
import cs3500.imageprocessor.operations.SepiaTone;

import static org.junit.Assert.assertEquals;

/**
 * Tests for SepiaTone.
 */
public class SepiaToneTest {
  SepiaTone sepiaTone;
  private ImageState image2x2;
  private ImageState image3x3;

  @Before
  public void initData() {
    this.sepiaTone = new SepiaTone();
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
  public void testApply() {
    assertEquals(new RGBAPixel(2, 2, 1), this.sepiaTone.apply(this.image2x2, 0, 0));
    assertEquals(new RGBAPixel(6, 5, 4), this.sepiaTone.apply(this.image2x2, 0, 1));
    assertEquals(new RGBAPixel(10, 9, 7), this.sepiaTone.apply(this.image2x2, 1, 0));
    assertEquals(new RGBAPixel(14, 13, 10), this.sepiaTone.apply(this.image2x2, 1, 1));

    assertEquals(new RGBAPixel(2, 2, 1), this.sepiaTone.apply(this.image3x3, 0, 0));
    assertEquals(new RGBAPixel(5, 4, 3), this.sepiaTone.apply(this.image3x3, 0, 1));
    assertEquals(new RGBAPixel(7, 7, 5), this.sepiaTone.apply(this.image3x3, 0, 2));
    assertEquals(new RGBAPixel(10, 9, 7), this.sepiaTone.apply(this.image3x3, 1, 0));
    assertEquals(new RGBAPixel(13, 11, 9), this.sepiaTone.apply(this.image3x3, 1, 1));
    assertEquals(new RGBAPixel(16, 14, 11), this.sepiaTone.apply(this.image3x3, 1, 2));
    assertEquals(new RGBAPixel(18, 16, 12), this.sepiaTone.apply(this.image3x3, 2, 0));
    assertEquals(new RGBAPixel(21, 19, 14), this.sepiaTone.apply(this.image3x3, 2, 1));
    assertEquals(new RGBAPixel(24, 21, 16), this.sepiaTone.apply(this.image3x3, 2, 2));
  }
}
