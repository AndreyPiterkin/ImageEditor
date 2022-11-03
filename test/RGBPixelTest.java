import org.junit.Before;
import org.junit.Test;

import cs3500.imageprocessor.model.RGBPixel;
import cs3500.imageprocessor.model.IPixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class RGBPixelTest {
  private IPixel RGBPixel1;
  private IPixel RGBPixel2;
  private IPixel RGBPixel3;

  @Before
  public void initData() {
    this.RGBPixel1 = new RGBPixel(0, 128, 255);
    this.RGBPixel2 = new RGBPixel(10, 20, 30);
    this.RGBPixel3 = new RGBPixel(50, 100, 150);
  }

  @Test
  public void testConstructorCorrect() {
    assertEquals(0, this.RGBPixel1.getRed());
    assertEquals(128, this.RGBPixel1.getGreen());
    assertEquals(255, this.RGBPixel1.getBlue());
    assertEquals(99, this.RGBPixel1.getAlpha());

    assertEquals(10, this.RGBPixel2.getRed());
    assertEquals(20, this.RGBPixel2.getGreen());
    assertEquals(30, this.RGBPixel2.getBlue());
    assertEquals(99, this.RGBPixel2.getAlpha());

    assertEquals(50, this.RGBPixel3.getRed());
    assertEquals(100, this.RGBPixel3.getGreen());
    assertEquals(150, this.RGBPixel3.getBlue());
    assertEquals(99, this.RGBPixel3.getAlpha());
  }

  @Test
  public void testConstructorIncorrect() {
    assertThrows(IllegalArgumentException.class, () -> this.RGBPixel1 = new RGBPixel(-1, 0, 0));
    assertThrows(IllegalArgumentException.class, () -> this.RGBPixel1 = new RGBPixel(0, -1, 0));
    assertThrows(IllegalArgumentException.class, () -> this.RGBPixel1 = new RGBPixel(0, 0, -1));
    assertThrows(IllegalArgumentException.class, () -> this.RGBPixel1 = new RGBPixel(-100, -100,
        -100));

    assertThrows(IllegalArgumentException.class, () -> this.RGBPixel1 = new RGBPixel(256, 0, 0));
    assertThrows(IllegalArgumentException.class, () -> this.RGBPixel1 = new RGBPixel(0, 256, 0));
    assertThrows(IllegalArgumentException.class, () -> this.RGBPixel1 = new RGBPixel(0, 0, 256));
    assertThrows(IllegalArgumentException.class, () -> this.RGBPixel1 = new RGBPixel(1000, 1000,
        1000));
  }

  @Test
  public void testGetRed() {
    assertEquals(0, this.RGBPixel1.getRed());
    assertEquals(10, this.RGBPixel2.getRed());
    assertEquals(50, this.RGBPixel3.getRed());
  }

  @Test
  public void testGetGreen() {
    assertEquals(128, this.RGBPixel1.getGreen());
    assertEquals(20, this.RGBPixel2.getGreen());
    assertEquals(100, this.RGBPixel3.getGreen());
  }

  @Test
  public void testGetBlue() {
    assertEquals(255, this.RGBPixel1.getBlue());
    assertEquals(30, this.RGBPixel2.getBlue());
    assertEquals(150, this.RGBPixel3.getBlue());
  }

  @Test
  public void testGetAlpha() {
    assertEquals(99, this.RGBPixel1.getAlpha());
    assertEquals(99, this.RGBPixel2.getAlpha());
    assertEquals(99, this.RGBPixel3.getAlpha());
  }

  @Test public void testEquals() {
    this.RGBPixel2 = new RGBPixel(0, 128, 255);
    assertTrue(this.RGBPixel2.equals(this.RGBPixel1));
    assertTrue(this.RGBPixel1.equals(this.RGBPixel2));

    this.RGBPixel2 = new RGBPixel(50, 100, 150);
    assertTrue(this.RGBPixel2.equals(this.RGBPixel3));
    assertTrue(this.RGBPixel3.equals(this.RGBPixel2));

    this.RGBPixel1 = new RGBPixel(50, 100, 150);
    assertTrue(this.RGBPixel3.equals(this.RGBPixel1));
    assertTrue(this.RGBPixel1.equals(this.RGBPixel3));
  }

  @Test public void testHashCode() {
    assertEquals(38300, this.RGBPixel1.hashCode());
    assertEquals(6000, this.RGBPixel2.hashCode());
    assertEquals(30000, this.RGBPixel3.hashCode());
  }
}
