import org.junit.Before;
import org.junit.Test;

import cs3500.imageprocessor.model.RGBAPixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

/**
 * Tests for GrayscalePixel.
 */
public class GrayscalePixelTest {
  private RGBAPixel grayPixel1 = new GrayscalePixel(0);
  private RGBAPixel grayPixel2;
  private RGBAPixel grayPixel3;

  @Before
  public void initData() {
    this.grayPixel1 = new GrayscalePixel(0);
    this.grayPixel2 = new GrayscalePixel(128);
    this.grayPixel3 = new GrayscalePixel(255);
  }

  @Test
  public void testConstructorCorrect() {
    assertEquals(0, this.grayPixel1.getRed());
    assertEquals(0, this.grayPixel1.getBlue());
    assertEquals(0, this.grayPixel1.getGreen());
    assertEquals(99, this.grayPixel1.getAlpha());

    assertEquals(128, this.grayPixel2.getRed());
    assertEquals(128, this.grayPixel2.getBlue());
    assertEquals(128, this.grayPixel2.getGreen());
    assertEquals(99, this.grayPixel2.getAlpha());

    assertEquals(255, this.grayPixel3.getRed());
    assertEquals(255, this.grayPixel3.getBlue());
    assertEquals(255, this.grayPixel3.getGreen());
    assertEquals(99, this.grayPixel3.getAlpha());
  }

  @Test
  public void testConstructorIncorrect() {
    assertThrows(IllegalArgumentException.class, () -> this.grayPixel1 = new GrayscalePixel(-1));
    assertThrows(IllegalArgumentException.class, () -> this.grayPixel1 = new GrayscalePixel(256));
  }

  @Test
  public void testGetRed() {
    assertEquals(0, this.grayPixel1.getRed());
    assertEquals(128, this.grayPixel2.getRed());
    assertEquals(255, this.grayPixel3.getRed());
  }

  @Test
  public void testGetGreen() {
    assertEquals(0, this.grayPixel1.getGreen());
    assertEquals(128, this.grayPixel2.getGreen());
    assertEquals(255, this.grayPixel3.getGreen());
  }

  @Test
  public void testGetBlue() {
    assertEquals(0, this.grayPixel1.getBlue());
    assertEquals(128, this.grayPixel2.getBlue());
    assertEquals(255, this.grayPixel3.getBlue());
  }

  @Test
  public void testGetAlpha() {
    assertEquals(99, this.grayPixel1.getAlpha());
    assertEquals(99, this.grayPixel2.getAlpha());
    assertEquals(99, this.grayPixel3.getAlpha());
  }

  @Test public void testEquals() {
    this.grayPixel2 = new GrayscalePixel(0);
    assertTrue(this.grayPixel2.equals(this.grayPixel1));
    assertTrue(this.grayPixel1.equals(this.grayPixel2));

    this.grayPixel2 = new GrayscalePixel(255);
    assertTrue(this.grayPixel2.equals(this.grayPixel3));
    assertTrue(this.grayPixel3.equals(this.grayPixel2));

    this.grayPixel1 = new GrayscalePixel(255);
    assertTrue(this.grayPixel3.equals(this.grayPixel1));
    assertTrue(this.grayPixel1.equals(this.grayPixel3));
  }

  @Test public void testHashCode() {
    assertEquals(9900, this.grayPixel1.hashCode());
    assertEquals(48300, this.grayPixel2.hashCode());
    assertEquals(86400, this.grayPixel3.hashCode());
  }
}
