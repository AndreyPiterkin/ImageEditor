import org.junit.Test;

import cs3500.imageprocessor.model.RGBAPixel;
import cs3500.imageprocessor.model.ImageUtil;

import static org.junit.Assert.assertArrayEquals;

/**
 * Tests for ImageUtil.
 */
public class BasicImageUtilTest {
  ImageUtil util = new ImageUtil();

  @Test
  public void testReadPPM1() {
    RGBAPixel[][] initPixels = {{new RGBPixel(0, 128, 240), new RGBPixel(1, 129, 241)},
        {new RGBPixel(2, 130, 242), new RGBPixel(3, 131, 243)}};
    ImagePPM image = new ImagePPM(initPixels);
    image.save("images/testReadPPM1.ppm");
    RGBAPixel[][] readPixels = this.util.readPPM("images/testReadPPM1.ppm");
    assertArrayEquals(initPixels, readPixels);
  }

  @Test
  public void testReadPPM2() {
    RGBAPixel[][] initPixels = {{new RGBPixel(0, 128, 240)}};
    ImagePPM image = new ImagePPM(initPixels);
    image.save("images/testReadPPM2.ppm");
    RGBAPixel[][] readPixels = this.util.readPPM("images/testReadPPM2.ppm");
    assertArrayEquals(initPixels, readPixels);
  }

  @Test
  public void testReadPPM3() {
    RGBAPixel[][] initPixels = {
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
    RGBAPixel[][] readPixels = this.util.readPPM("images/testReadPPM3.ppm");
    assertArrayEquals(initPixels, readPixels);
  }

}
