import org.junit.Test;

import cs3500.imageprocessor.model.IPixel;
import cs3500.imageprocessor.model.ImagePPM;
import cs3500.imageprocessor.model.ImageUtil;
import cs3500.imageprocessor.model.RGBPixel;

import static org.junit.Assert.assertArrayEquals;

/**
 * Tests for ImageUtil.
 */
public class ImageUtilTest {
  ImageUtil util = new ImageUtil();

  @Test
  public void testReadPPM1() {
    IPixel[][] initPixels = {{new RGBPixel(0, 128, 240), new RGBPixel(1, 129, 241)},
        {new RGBPixel(2, 130, 242), new RGBPixel(3, 131, 243)}};
    ImagePPM image = new ImagePPM(initPixels);
    image.save("images/testReadPPM1.ppm");
    IPixel[][] readPixels = this.util.readPPM("images/testReadPPM1.ppm");
    assertArrayEquals(initPixels, readPixels);
  }

  @Test
  public void testReadPPM2() {
    IPixel[][] initPixels = {{new RGBPixel(0, 128, 240)}};
    ImagePPM image = new ImagePPM(initPixels);
    image.save("images/testReadPPM2.ppm");
    IPixel[][] readPixels = this.util.readPPM("images/testReadPPM2.ppm");
    assertArrayEquals(initPixels, readPixels);
  }

  @Test
  public void testReadPPM3() {
    IPixel[][] initPixels = {
        {new RGBPixel(0, 128, 240),
            new RGBPixel(1, 129, 241),
            new RGBPixel(0, 128, 240)},
        {new RGBPixel(2, 130, 242),
            new RGBPixel(3, 131, 243),
            new RGBPixel(2, 130, 242)},
        {new RGBPixel(2, 130, 242),
            new RGBPixel(3, 131, 243),
            new RGBPixel(2, 130, 242)}};
    ImagePPM image = new ImagePPM(initPixels);
    image.save("images/testReadPPM3.ppm");
    IPixel[][] readPixels = this.util.readPPM("images/testReadPPM3.ppm");
    assertArrayEquals(initPixels, readPixels);
  }

}
