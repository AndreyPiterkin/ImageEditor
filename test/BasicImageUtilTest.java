import org.junit.Test;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.StringReader;

import cs3500.imageprocessor.controller.BasicEditorController;
import cs3500.imageprocessor.model.BasicImage;
import cs3500.imageprocessor.model.BasicImageEditor;
import cs3500.imageprocessor.model.RGBAPixel;
import cs3500.imageprocessor.model.ImageUtil;
import cs3500.imageprocessor.view.ImageEditorViewStub;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Tests for ImageUtil.
 */
public class BasicImageUtilTest {
  BasicImageEditor e = new BasicImageEditor();
  ImageEditorViewStub v = new ImageEditorViewStub();

  @Test
  public void testRead() {
    Color[][] colors = new Color[2][2];
    RGBAPixel[][] initPixels = {{new RGBAPixel(0, 128, 240), new RGBAPixel(1, 129, 241)},
        {new RGBAPixel(2, 130, 242), new RGBAPixel(3, 131, 243)}};
    BufferedImage img1 = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB);
    for (int r = 0; r < initPixels.length; r++) {
      for (int c = 0; c < initPixels[r].length; c++) {
        RGBAPixel pixel = initPixels[r][c];
        colors[r][c] = new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue());
        img1.setRGB(c, r, colors[r][c].getRGB());
      }
    }
    BasicImage image = new BasicImage(img1);

    e.addImage(image, "test1");
    Readable in = new StringReader("save test1 images/testReadPPM1.ppm");
    BasicEditorController c = new BasicEditorController(e, in, v);
    c.start();
    BufferedImage img2  = ImageUtil.readPPM("images/testReadPPM1.ppm");

    assertEquals(colors[0][0], new Color(img2.getRGB(0, 0)));
    assertEquals(colors[1][0], new Color(img2.getRGB(0, 1)));
    assertEquals(colors[0][1], new Color(img2.getRGB(1, 0)));
    assertEquals(colors[1][1], new Color(img2.getRGB(1, 1)));

  }

  @Test
  public void testRead2() {
    BufferedImage img1 = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    img1.setRGB(0, 0, new Color(0, 128, 240).getRGB());
    BasicImage image = new BasicImage(img1);

    e.addImage(image, "test2");
    Readable in = new StringReader("save test2 images/testReadPPM2.ppm");
    BasicEditorController c = new BasicEditorController(e, in, v);
    c.start();
    BufferedImage img2  = ImageUtil.readPPM("images/testReadPPM2.ppm");

    assertEquals(new Color(0, 128, 240), new Color(img2.getRGB(0, 0)));
  }

  @Test
  public void testComputeFrequencyHistogram() {
    Color[][] colors = new Color[2][2];
    RGBAPixel[][] initPixels = {{new RGBAPixel(0, 128, 240), new RGBAPixel(1, 129, 241)},
        {new RGBAPixel(2, 130, 242), new RGBAPixel(3, 131, 243)}};

    BufferedImage img1 = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB);
    for (int r = 0; r < initPixels.length; r++) {
      for (int c = 0; c < initPixels[r].length; c++) {
        RGBAPixel pixel = initPixels[r][c];
        colors[r][c] = new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue());
        img1.setRGB(c, r, colors[r][c].getRGB());
      }
    }

    int[][] histogram = new int[4][256];
    int[] rgb = img1.getRGB(0, 0, 2, 2, null, 0, 2);
    for (int i : rgb) {
      Color color = new Color(i);
      histogram[0][color.getRed()]++;
      histogram[1][color.getGreen()]++;
      histogram[2][color.getBlue()]++;
      double intensity = (color.getRed() + color.getGreen() + color.getBlue()) / 3.0;
      histogram[3][(int) Math.round(intensity)]++;
    }

    int[] maxes = new int[4];
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 256; j++) {
        if (histogram[i][j] > maxes[i]) {
          maxes[i] = histogram[i][j];
        }
      }
    }

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 256; j++) {
        histogram[i][j] = (int)((histogram[i][j] * 100.0) / (double) maxes[i]);
      }
    }

    int[][] returned = ImageUtil.computeFrequencyHistogram(img1);
    assertArrayEquals(histogram, returned);
  }

}
