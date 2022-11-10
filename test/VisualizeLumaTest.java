import org.junit.Before;
import org.junit.Test;

import cs3500.imageprocessor.model.RGBAPixel;
import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.operations.VisualizeLuma;

import static org.junit.Assert.assertEquals;

/**
 * Tests for VisualizeLuma.
 */
public class VisualizeLumaTest {
  VisualizeLuma visualizeLuma;
  ImageState image;

  @Before
  public void initData() {
    this.visualizeLuma = new VisualizeLuma();
    RGBAPixel[][] pixels = {{new RGBPixel(0, 128, 240), new RGBPixel(1, 129, 241)},
        {new RGBPixel(2, 130, 242), new RGBPixel(3, 131, 243)}};
    this.image = new ImagePPM(pixels);
  }

  @Test
  public void testApply() {
    assertEquals(new RGBPixel(0, 128, 240), this.image.getPixelAt(0,0));
    assertEquals(new RGBPixel(1, 129, 241), this.image.getPixelAt(0,1));
    assertEquals(new RGBPixel(2, 130, 242), this.image.getPixelAt(1,0));
    assertEquals(new RGBPixel(3, 131, 243), this.image.getPixelAt(1,1));

    assertEquals(new GrayscalePixel(108), this.visualizeLuma.apply(this.image, 0, 0));
    assertEquals(new GrayscalePixel(109), this.visualizeLuma.apply(this.image, 0, 1));
    assertEquals(new GrayscalePixel(110), this.visualizeLuma.apply(this.image, 1, 0));
    assertEquals(new GrayscalePixel(111), this.visualizeLuma.apply(this.image, 1, 1));
  }
}