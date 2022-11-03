import org.junit.Before;
import org.junit.Test;

import cs3500.imageprocessor.model.RGBPixel;
import cs3500.imageprocessor.model.IPixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

/**
 * Tests for rgbPixel.
 */
public class RGBPixelTest {
  private IPixel rgbPixel1;
  private IPixel rgbPixel2;
  private IPixel rgbPixel3;

  @Before
  public void initData() {
    this.rgbPixel1 = new RGBPixel(0, 128, 255);
    this.rgbPixel2 = new RGBPixel(10, 20, 30);
    this.rgbPixel3 = new RGBPixel(50, 100, 150);
  }

  @Test
  public void testConstructorCorrect() {
    assertEquals(0, this.rgbPixel1.getRed());
    assertEquals(128, this.rgbPixel1.getGreen());
    assertEquals(255, this.rgbPixel1.getBlue());
    assertEquals(99, this.rgbPixel1.getAlpha());

    assertEquals(10, this.rgbPixel2.getRed());
    assertEquals(20, this.rgbPixel2.getGreen());
    assertEquals(30, this.rgbPixel2.getBlue());
    assertEquals(99, this.rgbPixel2.getAlpha());

    assertEquals(50, this.rgbPixel3.getRed());
    assertEquals(100, this.rgbPixel3.getGreen());
    assertEquals(150, this.rgbPixel3.getBlue());
    assertEquals(99, this.rgbPixel3.getAlpha());
  }

  @Test
  public void testConstructorIncorrect() {
    assertThrows(IllegalArgumentException.class, () -> this.rgbPixel1 = new RGBPixel(-1, 0, 0));
    assertThrows(IllegalArgumentException.class, () -> this.rgbPixel1 = new RGBPixel(0, -1, 0));
    assertThrows(IllegalArgumentException.class, () -> this.rgbPixel1 = new RGBPixel(0, 0, -1));
    assertThrows(IllegalArgumentException.class, () -> this.rgbPixel1 = new RGBPixel(-100, -100,
        -100));

    assertThrows(IllegalArgumentException.class, () -> this.rgbPixel1 = new RGBPixel(256, 0, 0));
    assertThrows(IllegalArgumentException.class, () -> this.rgbPixel1 = new RGBPixel(0, 256, 0));
    assertThrows(IllegalArgumentException.class, () -> this.rgbPixel1 = new RGBPixel(0, 0, 256));
    assertThrows(IllegalArgumentException.class, () -> this.rgbPixel1 = new RGBPixel(1000, 1000,
        1000));
  }

  @Test
  public void testGetRed() {
    assertEquals(0, this.rgbPixel1.getRed());
    assertEquals(10, this.rgbPixel2.getRed());
    assertEquals(50, this.rgbPixel3.getRed());
  }

  @Test
  public void testGetGreen() {
    assertEquals(128, this.rgbPixel1.getGreen());
    assertEquals(20, this.rgbPixel2.getGreen());
    assertEquals(100, this.rgbPixel3.getGreen());
  }

  @Test
  public void testGetBlue() {
    assertEquals(255, this.rgbPixel1.getBlue());
    assertEquals(30, this.rgbPixel2.getBlue());
    assertEquals(150, this.rgbPixel3.getBlue());
  }

  @Test
  public void testGetAlpha() {
    assertEquals(99, this.rgbPixel1.getAlpha());
    assertEquals(99, this.rgbPixel2.getAlpha());
    assertEquals(99, this.rgbPixel3.getAlpha());
  }

  @Test public void testEquals() {
    this.rgbPixel2 = new RGBPixel(0, 128, 255);
    assertTrue(this.rgbPixel2.equals(this.rgbPixel1));
    assertTrue(this.rgbPixel1.equals(this.rgbPixel2));

    this.rgbPixel2 = new RGBPixel(50, 100, 150);
    assertTrue(this.rgbPixel2.equals(this.rgbPixel3));
    assertTrue(this.rgbPixel3.equals(this.rgbPixel2));

    this.rgbPixel1 = new RGBPixel(50, 100, 150);
    assertTrue(this.rgbPixel3.equals(this.rgbPixel1));
    assertTrue(this.rgbPixel1.equals(this.rgbPixel3));
  }

  @Test public void testHashCode() {
    assertEquals(48200, this.rgbPixel1.hashCode());
    assertEquals(15900, this.rgbPixel2.hashCode());
    assertEquals(39900, this.rgbPixel3.hashCode());
  }
}
