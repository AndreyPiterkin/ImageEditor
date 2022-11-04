import org.junit.Before;
import org.junit.Test;

import cs3500.imageprocessor.model.GrayscalePixel;
import cs3500.imageprocessor.model.IPixel;
import cs3500.imageprocessor.model.ImagePPM;
import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.model.RGBPixel;
import cs3500.imageprocessor.operations.VisualizeIntensity;

import static org.junit.Assert.assertEquals;

/**
 * Tests for VisualizeIntensity.
 */
public class VisualizeIntensityTest {
  VisualizeIntensity visualizeIntensity;
  ImageState image;

  @Before
  public void initData() {
    this.visualizeIntensity = new VisualizeIntensity();
    IPixel[][] pixels = {{new RGBPixel(0, 128, 240), new RGBPixel(1, 129, 241)},
        {new RGBPixel(2, 130, 242), new RGBPixel(3, 131, 243)}};
    this.image = new ImagePPM(pixels);
  }

  @Test
  public void testApply() {
    assertEquals(new RGBPixel(0, 128, 240), this.image.getPixelAt(0,0));
    assertEquals(new RGBPixel(1, 129, 241), this.image.getPixelAt(0,1));
    assertEquals(new RGBPixel(2, 130, 242), this.image.getPixelAt(1,0));
    assertEquals(new RGBPixel(3, 131, 243), this.image.getPixelAt(1,1));

    assertEquals(new GrayscalePixel(122), this.visualizeIntensity.apply(this.image, 0, 0));
    assertEquals(new GrayscalePixel(123), this.visualizeIntensity.apply(this.image, 0, 1));
    assertEquals(new GrayscalePixel(124), this.visualizeIntensity.apply(this.image, 1, 0));
    assertEquals(new GrayscalePixel(125), this.visualizeIntensity.apply(this.image, 1, 1));
  }
}