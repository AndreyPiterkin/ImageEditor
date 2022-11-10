import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import cs3500.imageprocessor.model.BasicImage;
import cs3500.imageprocessor.model.RGBAPixel;
import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.operations.VisualizeValue;

import static org.junit.Assert.assertEquals;

/**
 * Tests for VisualizeValue.
 */
public class VisualizeValueTest {
  VisualizeValue visualizeValue;
  ImageState image;

  @Before
  public void initData() {
    this.visualizeValue = new VisualizeValue();
    RGBAPixel[][] pixels = {{new RGBAPixel(0, 128, 240), new RGBAPixel(1, 242, 241)},
        {new RGBAPixel(255, 130, 130), new RGBAPixel(23, 23, 23)}};

    BufferedImage img1 = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB);
    for (int r = 0; r < pixels.length; r++) {
      for (int c = 0; c < pixels[r].length; c++) {
        RGBAPixel pixel = pixels[r][c];
        Color color = new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue());
        img1.setRGB(c, r, color.getRGB());
      }
    }

    this.image = new BasicImage(img1);
  }

  @Test
  public void testApply() {
    assertEquals(new RGBAPixel(0, 128, 240), this.image.getPixelAt(0,0));
    assertEquals(new RGBAPixel(1, 242, 241), this.image.getPixelAt(0,1));
    assertEquals(new RGBAPixel(255, 130, 130), this.image.getPixelAt(1,0));
    assertEquals(new RGBAPixel(23, 23, 23), this.image.getPixelAt(1,1));

    assertEquals(new RGBAPixel(240), this.visualizeValue.apply(this.image, 0, 0));
    assertEquals(new RGBAPixel(242), this.visualizeValue.apply(this.image, 0, 1));
    assertEquals(new RGBAPixel(255), this.visualizeValue.apply(this.image, 1, 0));
    assertEquals(new RGBAPixel(23), this.visualizeValue.apply(this.image, 1, 1));
  }
}