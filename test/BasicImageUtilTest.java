import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.StringReader;

import cs3500.imageprocessor.controller.BasicEditorController;
import cs3500.imageprocessor.model.BasicImage;
import cs3500.imageprocessor.model.BasicImageEditor;
import cs3500.imageprocessor.model.RGBAPixel;
import cs3500.imageprocessor.model.ImageUtil;
import cs3500.imageprocessor.view.ImageEditorViewStub;

import static org.junit.Assert.assertEquals;

/**
 * Tests for ImageUtil.
 */
public class BasicImageUtilTest {
  ImageUtil util = new ImageUtil();
  BasicImageEditor e = new BasicImageEditor();
  ImageEditorViewStub v = new ImageEditorViewStub();

  @Test
  public void testReadPPM1() {
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
    BufferedImage img2  = this.util.readPPM("images/testReadPPM1.ppm");

    assertEquals(colors[0][0], new Color(img2.getRGB(0, 0)));
    assertEquals(colors[1][0], new Color(img2.getRGB(0, 1)));
    assertEquals(colors[0][1], new Color(img2.getRGB(1, 0)));
    assertEquals(colors[1][1], new Color(img2.getRGB(1, 1)));

  }

  @Test
  public void testReadPPM2() {
    RGBAPixel[][] initPixels = {{new RGBAPixel(0, 128, 240)}};
    BufferedImage img1 = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    img1.setRGB(0, 0, new Color(0, 128, 240).getRGB());
    BasicImage image = new BasicImage(img1);

    e.addImage(image, "test2");
    Readable in = new StringReader("save test2 images/testReadPPM2.ppm");
    BasicEditorController c = new BasicEditorController(e, in, v);
    c.start();
    BufferedImage img2  = this.util.readPPM("images/testReadPPM2.ppm");

    assertEquals(new Color(0, 128, 240), new Color(img2.getRGB(0, 0)));
  }

}
