import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.image.BufferedImage;

import cs3500.imageprocessor.model.BasicImage;
import cs3500.imageprocessor.model.RGBAPixel;
import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.operations.FlipHorizontal;

import static org.junit.Assert.assertEquals;

/**
 * Tests for FlipHorizontal.
 */
public class FlipHorizontalTest {
  FlipHorizontal flipHorizontal;
  ImageState image1;
  ImageState image2;

  @Before
  public void initData() {
    this.flipHorizontal = new FlipHorizontal();
    RGBAPixel[][] pixels1 = {{new RGBAPixel(1), new RGBAPixel(128)},
        {new RGBAPixel(10), new RGBAPixel(240)}};
    RGBAPixel[][] pixels2 = {{new RGBAPixel(0, 128, 240)}};

    BufferedImage img1 = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB);
    for (int r = 0; r < pixels1.length; r++) {
      for (int c = 0; c < pixels1[r].length; c++) {
        RGBAPixel pixel = pixels1[r][c];
        Color color = new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue());
        img1.setRGB(c, r, color.getRGB());
      }
    }

    BufferedImage img2 = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    for (int r = 0; r < pixels2.length; r++) {
      for (int c = 0; c < pixels2[r].length; c++) {
        RGBAPixel pixel = pixels2[r][c];
        Color color = new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue());
        img2.setRGB(c, r, color.getRGB());
      }
    }
    this.image1 = new BasicImage(img1);
    this.image2 = new BasicImage(img2);
  }

  @Test
  public void testApply() {
    assertEquals(new RGBAPixel(1), this.image1.getPixelAt(0,0));
    assertEquals(new RGBAPixel(128), this.image1.getPixelAt(0,1));
    assertEquals(new RGBAPixel(10), this.image1.getPixelAt(1,0));
    assertEquals(new RGBAPixel(240), this.image1.getPixelAt(1,1));

    assertEquals(new RGBAPixel(128), this.flipHorizontal.apply(this.image1, 0, 0));
    assertEquals(new RGBAPixel(1), this.flipHorizontal.apply(this.image1, 0, 1));
    assertEquals(new RGBAPixel(240), this.flipHorizontal.apply(this.image1, 1, 0));
    assertEquals(new RGBAPixel(10), this.flipHorizontal.apply(this.image1, 1, 1));

    assertEquals(new RGBAPixel(0, 128, 240), this.image2.getPixelAt(0,0));
    assertEquals(new RGBAPixel(0, 128, 240), this.flipHorizontal.apply(this.image2, 0, 0));
  }
}