import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.image.BufferedImage;

import cs3500.imageprocessor.model.BasicImage;
import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.model.RGBAPixel;
import cs3500.imageprocessor.operations.GaussianBlur;

import static org.junit.Assert.assertEquals;

/**
 * Tests for GaussianBlur.
 */
public class GaussianBlurTest {
  GaussianBlur gaussianBlur;
  private ImageState image2x2;
  private ImageState image3x3;

  @Before
  public void initData() {
    this.gaussianBlur = new GaussianBlur();
    RGBAPixel[][] initPixels2x2 = {
        {new RGBAPixel(16),
            new RGBAPixel(32)},
        {new RGBAPixel(48),
            new RGBAPixel(64)}};
    RGBAPixel[][] initPixels3x3 = {
        {new RGBAPixel(16),
            new RGBAPixel(32),
            new RGBAPixel(48)},
        {new RGBAPixel(64),
            new RGBAPixel(80),
            new RGBAPixel(96)},
        {new RGBAPixel(112),
            new RGBAPixel(128),
            new RGBAPixel(144)}};

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
    assertEquals(new RGBAPixel(18), this.gaussianBlur.apply(this.image2x2, 0,0));
    assertEquals(new RGBAPixel(21), this.gaussianBlur.apply(this.image2x2, 0,1));
    assertEquals(new RGBAPixel(24), this.gaussianBlur.apply(this.image2x2, 1,0));
    assertEquals(new RGBAPixel(27), this.gaussianBlur.apply(this.image2x2, 1,1));

    assertEquals(new RGBAPixel(21), this.gaussianBlur.apply(this.image3x3, 0,0));
    assertEquals(new RGBAPixel(36), this.gaussianBlur.apply(this.image3x3, 0,1));
    assertEquals(new RGBAPixel(33), this.gaussianBlur.apply(this.image3x3, 0,2));
    assertEquals(new RGBAPixel(52), this.gaussianBlur.apply(this.image3x3, 1,0));
    assertEquals(new RGBAPixel(80), this.gaussianBlur.apply(this.image3x3, 1,1));
    assertEquals(new RGBAPixel(68), this.gaussianBlur.apply(this.image3x3, 1,2));
    assertEquals(new RGBAPixel(57), this.gaussianBlur.apply(this.image3x3, 2,0));
    assertEquals(new RGBAPixel(84), this.gaussianBlur.apply(this.image3x3, 2,1));
    assertEquals(new RGBAPixel(69), this.gaussianBlur.apply(this.image3x3, 2,2));
  }
}
