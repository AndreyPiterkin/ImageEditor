import org.junit.Before;
import org.junit.Test;

import cs3500.imageprocessor.model.GrayscalePixel;
import cs3500.imageprocessor.model.IPixel;
import cs3500.imageprocessor.model.ImagePPM;
import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.model.RGBPixel;
import cs3500.imageprocessor.operations.DarkenPixel;

import static org.junit.Assert.assertEquals;

/**
 * Tests for DarkenImage.
 */
public class DarkenImageTest {
  DarkenPixel darken1;
  DarkenPixel darken10;
  DarkenPixel darken100;
  ImageState image1;
  ImageState image2;

  @Before
  public void initData() {
    this.darken1 = new DarkenPixel(1);
    this.darken10 = new DarkenPixel(10);
    this.darken100 = new DarkenPixel(100);
    IPixel[][] pixels1 = {{new GrayscalePixel(1), new GrayscalePixel(128)},
        {new GrayscalePixel(10), new GrayscalePixel(240)}};;
    IPixel[][] pixels2 = {{new RGBPixel(0, 128, 240)}};
    this.image1 = new ImagePPM(pixels1);
    this.image2 = new ImagePPM(pixels2);
  }

  @Test
  public void testApply() {
    assertEquals(new GrayscalePixel(1), this.image1.getPixelAt(0,0));
    assertEquals(new GrayscalePixel(0), this.darken1.apply(this.image1, 0, 0));

    assertEquals(new GrayscalePixel(128), this.image1.getPixelAt(0,1));
    assertEquals(new GrayscalePixel(118), this.darken10.apply(this.image1, 0, 1));

    assertEquals(new GrayscalePixel(10), this.image1.getPixelAt(1,0));
    assertEquals(new GrayscalePixel(0), this.darken100.apply(this.image1, 1, 0));

    assertEquals(new RGBPixel(0, 128, 240), this.image2.getPixelAt(0,0));
    assertEquals(new RGBPixel(0, 28, 140), this.darken100.apply(this.image2, 0, 0));
  }
}