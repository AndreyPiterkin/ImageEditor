import org.junit.Before;
import org.junit.Test;

import cs3500.imageprocessor.model.RGBAPixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for RGBAPixel.
 */
public class RGBAPixelTest {
  private RGBAPixel rgbaPixel1;
  private RGBAPixel rgbaPixel2;
  private RGBAPixel rgbaPixel3;
  private RGBAPixel grayPixel = new RGBAPixel(28);

  @Before
  public void initData() {
    this.rgbaPixel1 = new RGBAPixel(0, 128, 255);
    this.rgbaPixel2 = new RGBAPixel(10, 20, 30);
    this.rgbaPixel3 = new RGBAPixel(50, 100, 150, 234);
  }

  @Test
  public void testConstructorCorrect() {
    assertEquals(0, this.rgbaPixel1.getRed());
    assertEquals(128, this.rgbaPixel1.getGreen());
    assertEquals(255, this.rgbaPixel1.getBlue());
    assertEquals(255, this.rgbaPixel1.getAlpha());

    assertEquals(10, this.rgbaPixel2.getRed());
    assertEquals(20, this.rgbaPixel2.getGreen());
    assertEquals(30, this.rgbaPixel2.getBlue());
    assertEquals(255, this.rgbaPixel2.getAlpha());

    assertEquals(50, this.rgbaPixel3.getRed());
    assertEquals(100, this.rgbaPixel3.getGreen());
    assertEquals(150, this.rgbaPixel3.getBlue());
    assertEquals(234, this.rgbaPixel3.getAlpha());

    assertEquals(28, this.grayPixel.getRed());
    assertEquals(28, this.grayPixel.getGreen());
    assertEquals(28, this.grayPixel.getBlue());
    assertEquals(255, this.grayPixel.getAlpha());
  }

  @Test
  public void testConstructorClamps() {
    assertEquals(new RGBAPixel(0, 0, 0), new RGBAPixel(-1, 0, 0));
    assertEquals(new RGBAPixel(0, 0, 0), new RGBAPixel(0, -1, 0));
    assertEquals(new RGBAPixel(0, 0, 0), new RGBAPixel(0, 0, -1));
    assertEquals(new RGBAPixel(0, 0, 0), new RGBAPixel(-100, -100,-100));

    assertEquals(new RGBAPixel(255, 0, 0), new RGBAPixel(256, 0, 0));
    assertEquals(new RGBAPixel(0, 255, 0), new RGBAPixel(0, 256, 0));
    assertEquals(new RGBAPixel(0, 0, 255), new RGBAPixel(0, 0, 256));
    assertEquals(new RGBAPixel(255, 255, 255), new RGBAPixel(1000, 1000, 1000));

    assertEquals(new RGBAPixel(0, 0, 0, 0), new RGBAPixel(-1, 0, 0, 0));
    assertEquals(new RGBAPixel(0, 0, 0, 0), new RGBAPixel(0, -1, 0, 0));
    assertEquals(new RGBAPixel(0, 0, 0, 0), new RGBAPixel(0, 0, -1, 0));
    assertEquals(new RGBAPixel(0, 0, 0, 0), new RGBAPixel(0, 0, 0, -1));
    assertEquals(new RGBAPixel(0, 0, 0, 0), new RGBAPixel(-100, -100, -100, -100));

    assertEquals(new RGBAPixel(255, 0, 0, 0), new RGBAPixel(256, 0, 0, 0));
    assertEquals(new RGBAPixel(0, 255, 0, 0), new RGBAPixel(0, 256, 0, 0));
    assertEquals(new RGBAPixel(0, 0, 255, 0), new RGBAPixel(0, 0, 256, 0));
    assertEquals(new RGBAPixel(0), new RGBAPixel(0, 0, 0, 256));
    assertEquals(new RGBAPixel(255), new RGBAPixel(1000, 1000, 1000));

    assertEquals(new RGBAPixel(0), new RGBAPixel(-1));
    assertEquals(new RGBAPixel(255), new RGBAPixel(256));
  }

  @Test
  public void testGetRed() {
    assertEquals(0, this.rgbaPixel1.getRed());
    assertEquals(10, this.rgbaPixel2.getRed());
    assertEquals(50, this.rgbaPixel3.getRed());
  }

  @Test
  public void testGetGreen() {
    assertEquals(128, this.rgbaPixel1.getGreen());
    assertEquals(20, this.rgbaPixel2.getGreen());
    assertEquals(100, this.rgbaPixel3.getGreen());
  }

  @Test
  public void testGetBlue() {
    assertEquals(255, this.rgbaPixel1.getBlue());
    assertEquals(30, this.rgbaPixel2.getBlue());
    assertEquals(150, this.rgbaPixel3.getBlue());
  }

  @Test
  public void testGetAlpha() {
    assertEquals(255, this.rgbaPixel1.getAlpha());
    assertEquals(255, this.rgbaPixel2.getAlpha());
    assertEquals(234, this.rgbaPixel3.getAlpha());
  }

  @Test public void testEquals() {
    this.rgbaPixel2 = new RGBAPixel(0, 128, 255);
    assertTrue(this.rgbaPixel2.equals(this.rgbaPixel1));
    assertTrue(this.rgbaPixel1.equals(this.rgbaPixel2));

    this.rgbaPixel2 = new RGBAPixel(50, 100, 150, 234);
    assertTrue(this.rgbaPixel2.equals(this.rgbaPixel3));
    assertTrue(this.rgbaPixel3.equals(this.rgbaPixel2));

    this.rgbaPixel1 = new RGBAPixel(50, 100, 150, 234);
    assertTrue(this.rgbaPixel3.equals(this.rgbaPixel1));
    assertTrue(this.rgbaPixel1.equals(this.rgbaPixel3));
  }

  @Test public void testHashCode() {
    assertEquals(62397, this.rgbaPixel1.hashCode());
    assertEquals(30655, this.rgbaPixel2.hashCode());
    assertEquals(52298, this.rgbaPixel3.hashCode());
  }
}
