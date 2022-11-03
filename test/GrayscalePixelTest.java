import org.junit.Before;
import org.junit.Test;

import cs3500.imageprocessor.model.GrayscalePixel;
import cs3500.imageprocessor.model.IPixel;

import static org.junit.Assert.assertEquals;

public class GrayscalePixelTest {
  private IPixel grayPixel1 = new GrayscalePixel(0);
  private IPixel grayPixel2;
  private IPixel grayPixel3;

  @Before
  public void initData() {
    grayPixel1 = new GrayscalePixel(0);
    grayPixel2 = new GrayscalePixel(2);
    grayPixel3 = new GrayscalePixel(0);
  }

  @Test
  public void testConstructorCorrect() {
    assertEquals(grayPixel1, grayPixel3);
  }
}
