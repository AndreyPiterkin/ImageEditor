import org.junit.Before;
import org.junit.Test;

import cs3500.imageprocessor.model.GrayscalePixel;
import cs3500.imageprocessor.model.IPixel;
import cs3500.imageprocessor.model.ImagePPM;
import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.model.RGBPixel;
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
    IPixel[][] pixels = {{new RGBPixel(0, 128, 240), new RGBPixel(1, 242, 241)},
        {new RGBPixel(255, 130, 130), new RGBPixel(23, 23, 23)}};
    this.image = new ImagePPM(pixels);
  }

  @Test
  public void testApply() {
    assertEquals(new RGBPixel(0, 128, 240), this.image.getPixelAt(0,0));
    assertEquals(new RGBPixel(1, 242, 241), this.image.getPixelAt(0,1));
    assertEquals(new RGBPixel(255, 130, 130), this.image.getPixelAt(1,0));
    assertEquals(new RGBPixel(23, 23, 23), this.image.getPixelAt(1,1));

    assertEquals(new GrayscalePixel(240), this.visualizeValue.apply(this.image, 0, 0));
    assertEquals(new GrayscalePixel(242), this.visualizeValue.apply(this.image, 0, 1));
    assertEquals(new GrayscalePixel(255), this.visualizeValue.apply(this.image, 1, 0));
    assertEquals(new GrayscalePixel(23), this.visualizeValue.apply(this.image, 1, 1));
  }
}