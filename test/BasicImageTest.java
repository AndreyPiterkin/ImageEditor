import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import cs3500.imageprocessor.model.BasicImage;
import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.model.RGBAPixel;
import cs3500.imageprocessor.operations.DarkenPixel;
import cs3500.imageprocessor.operations.FlipHorizontal;
import cs3500.imageprocessor.operations.GrayscaleRed;
import cs3500.imageprocessor.operations.VisualizeValue;

import static org.junit.Assert.assertEquals;

/**
 * Tests for BasicImage.
 */
public class BasicImageTest {
  BasicImage image1x1;
  BasicImage image2x2;
  BasicImage image3x3;
  BufferedImage img1;
  BufferedImage img2;
  BufferedImage img3;
  RGBAPixel[][] initPixels1 = {{new RGBAPixel(0, 128, 240)}};
  RGBAPixel[][] initPixels2 = {
      {new RGBAPixel(0, 128, 240),
          new RGBAPixel(1, 129, 241)},
      {new RGBAPixel(2, 130, 242),
          new RGBAPixel(3, 131, 243)}};
  RGBAPixel[][] initPixels3 = {
      {new RGBAPixel(0, 128, 240),
          new RGBAPixel(1, 129, 241),
          new RGBAPixel(0, 128, 240)},
      {new RGBAPixel(2, 130, 242),
          new RGBAPixel(3, 131, 243),
          new RGBAPixel(2, 130, 242)},
      {new RGBAPixel(2, 130, 242),
          new RGBAPixel(3, 131, 243),
          new RGBAPixel(2, 130, 242)}};

  @Before
  public void initData() {
    this.img1 = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    for (int r = 0; r < initPixels1.length; r++) {
      for (int c = 0; c < initPixels1[r].length; c++) {
        RGBAPixel pixel = initPixels1[r][c];
        Color color = new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue());
        img1.setRGB(c, r, color.getRGB());
      }
    }

    this.img2 = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB);
    for (int r = 0; r < initPixels2.length; r++) {
      for (int c = 0; c < initPixels2[r].length; c++) {
        RGBAPixel pixel = initPixels2[r][c];
        Color color = new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue());
        img2.setRGB(c, r, color.getRGB());
      }
    }

    this.img3 = new BufferedImage(3, 3, BufferedImage.TYPE_INT_RGB);
    for (int r = 0; r < initPixels3.length; r++) {
      for (int c = 0; c < initPixels3[r].length; c++) {
        RGBAPixel pixel = initPixels3[r][c];
        Color color = new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue());
        img3.setRGB(c, r, color.getRGB());
      }
    }

    this.image1x1 = new BasicImage(img1);
    this.image2x2 = new BasicImage(img2);
    this.image3x3 = new BasicImage(img3);
  }

  @Test
  public void testGetWidth() {
    assertEquals(1, this.image1x1.getWidth());
    assertEquals(2, this.image2x2.getWidth());
    assertEquals(3, this.image3x3.getWidth());
  }

  @Test
  public void testGetHeight() {
    assertEquals(1, this.image1x1.getHeight());
    assertEquals(2, this.image2x2.getHeight());
    assertEquals(3, this.image3x3.getHeight());
  }

  @Test
  public void testGetPixelAt() {
    for (int i = 0; i < this.image1x1.getHeight(); i++) {
      for (int j = 0; j < this.image1x1.getWidth(); j++) {
        assertEquals(this.initPixels1[i][j], this.image1x1.getPixelAt(i, j));
      }
    }
    for (int i = 0; i < this.image2x2.getHeight(); i++) {
      for (int j = 0; j < this.image2x2.getWidth(); j++) {
        assertEquals(this.initPixels2[i][j], this.image2x2.getPixelAt(i, j));
      }
    }
    for (int i = 0; i < this.image3x3.getHeight(); i++) {
      for (int j = 0; j < this.image3x3.getWidth(); j++) {
        assertEquals(this.initPixels3[i][j], this.image3x3.getPixelAt(i, j));
      }
    }
  }

  @Test
  public void testApply() {
    ImageState darkened = this.image2x2.apply(new DarkenPixel(10));
    RGBAPixel[][] darkenedPixels = {
        {new RGBAPixel(0, 118, 230),
            new RGBAPixel(0, 119, 231)},
        {new RGBAPixel(0, 120, 232),
            new RGBAPixel(0, 121, 233)}};

    ImageState flipHorz = this.image2x2.apply(new FlipHorizontal());
    RGBAPixel[][] flipPixels = {
        {new RGBAPixel(1, 129, 241),
            new RGBAPixel(0, 128, 240)},
        {new RGBAPixel(3, 131, 243),
            new RGBAPixel(2, 130, 242)}};

    ImageState grayscaleRed = this.image2x2.apply(new GrayscaleRed());
    RGBAPixel[][] red = {
        {new RGBAPixel(0, 0, 0),
            new RGBAPixel(1, 1, 1)},
        {new RGBAPixel(2, 2, 2),
            new RGBAPixel(3, 3, 3)}};

    ImageState valueImage = this.image2x2.apply(new VisualizeValue());
    RGBAPixel[][] value = {
        {new RGBAPixel(240, 240, 240),
            new RGBAPixel(241, 241, 241)},
        {new RGBAPixel(242, 242, 242),
            new RGBAPixel(243, 243, 243)}};

    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        assertEquals(darkenedPixels[i][j], darkened.getPixelAt(i, j));
        assertEquals(flipPixels[i][j], flipHorz.getPixelAt(i, j));
        assertEquals(red[i][j], grayscaleRed.getPixelAt(i, j));
        assertEquals(value[i][j], valueImage.getPixelAt(i, j));
      }
    }
  }

  @Test
  public void asBufferedImageTest() {
    assertEquals(this.img1.getRGB(0, 0),
        image1x1.asBufferedImage(BufferedImage.TYPE_INT_RGB).getRGB(0, 0));

    assertEquals(this.img2.getRGB(0, 0),
        image2x2.asBufferedImage(BufferedImage.TYPE_INT_RGB).getRGB(0, 0));
    assertEquals(this.img2.getRGB(0, 1),
        image2x2.asBufferedImage(BufferedImage.TYPE_INT_RGB).getRGB(0, 1));
    assertEquals(this.img2.getRGB(1, 0),
        image2x2.asBufferedImage(BufferedImage.TYPE_INT_RGB).getRGB(1, 0));
    assertEquals(this.img2.getRGB(1, 1),
        image2x2.asBufferedImage(BufferedImage.TYPE_INT_RGB).getRGB(1, 1));

    assertEquals(this.img3.getRGB(0, 0),
        image3x3.asBufferedImage(BufferedImage.TYPE_INT_RGB).getRGB(0, 0));
    assertEquals(this.img3.getRGB(0, 1),
        image3x3.asBufferedImage(BufferedImage.TYPE_INT_RGB).getRGB(0, 1));
    assertEquals(this.img3.getRGB(0, 2),
        image3x3.asBufferedImage(BufferedImage.TYPE_INT_RGB).getRGB(0, 2));
    assertEquals(this.img3.getRGB(1, 0),
        image3x3.asBufferedImage(BufferedImage.TYPE_INT_RGB).getRGB(1, 0));
    assertEquals(this.img3.getRGB(1, 1),
        image3x3.asBufferedImage(BufferedImage.TYPE_INT_RGB).getRGB(1, 1));
    assertEquals(this.img3.getRGB(1, 2),
        image3x3.asBufferedImage(BufferedImage.TYPE_INT_RGB).getRGB(1, 2));
    assertEquals(this.img3.getRGB(2, 0),
        image3x3.asBufferedImage(BufferedImage.TYPE_INT_RGB).getRGB(2, 0));
    assertEquals(this.img3.getRGB(2, 1),
        image3x3.asBufferedImage(BufferedImage.TYPE_INT_RGB).getRGB(2, 1));
    assertEquals(this.img3.getRGB(2, 1),
        image3x3.asBufferedImage(BufferedImage.TYPE_INT_RGB).getRGB(2, 2));
  }
}
