import org.junit.Before;
import org.junit.Test;

import cs3500.imageprocessor.model.RGBAPixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

/**
 * Tests for RGBAPixel.
 */
public class RGBAPixelTest {
  private RGBAPixel RGBAPixel1;
  private RGBAPixel RGBAPixel2;
  private RGBAPixel RGBAPixel3;
  private RGBAPixel grayPixel = new RGBAPixel(28);

  @Before
  public void initData() {
    this.RGBAPixel1 = new RGBAPixel(0, 128, 255);
    this.RGBAPixel2 = new RGBAPixel(10, 20, 30);
    this.RGBAPixel3 = new RGBAPixel(50, 100, 150, 234);
  }

  @Test
  public void testConstructorCorrect() {
    assertEquals(0, this.RGBAPixel1.getRed());
    assertEquals(128, this.RGBAPixel1.getGreen());
    assertEquals(255, this.RGBAPixel1.getBlue());
    assertEquals(255, this.RGBAPixel1.getAlpha());

    assertEquals(10, this.RGBAPixel2.getRed());
    assertEquals(20, this.RGBAPixel2.getGreen());
    assertEquals(30, this.RGBAPixel2.getBlue());
    assertEquals(255, this.RGBAPixel2.getAlpha());

    assertEquals(50, this.RGBAPixel3.getRed());
    assertEquals(100, this.RGBAPixel3.getGreen());
    assertEquals(150, this.RGBAPixel3.getBlue());
    assertEquals(234, this.RGBAPixel3.getAlpha());

    assertEquals(28, this.grayPixel.getRed());
    assertEquals(28, this.grayPixel.getGreen());
    assertEquals(28, this.grayPixel.getBlue());
    assertEquals(255, this.grayPixel.getAlpha());
  }

//  @Test
//  public void testConstructorIncorrect() {
//    assertThrows(IllegalArgumentException.class, () -> this.RGBAPixel1 = new RGBAPixel(-1, 0, 0));
//    assertThrows(IllegalArgumentException.class, () -> this.RGBAPixel1 = new RGBAPixel(0, -1, 0));
//    assertThrows(IllegalArgumentException.class, () -> this.RGBAPixel1 = new RGBAPixel(0, 0, -1));
//    assertThrows(IllegalArgumentException.class, () -> this.RGBAPixel1 = new RGBAPixel(-100, -100,
//        -100));
//
//    assertThrows(IllegalArgumentException.class, () -> this.RGBAPixel1 = new RGBAPixel(256, 0, 0));
//    assertThrows(IllegalArgumentException.class, () -> this.RGBAPixel1 = new RGBAPixel(0, 256, 0));
//    assertThrows(IllegalArgumentException.class, () -> this.RGBAPixel1 = new RGBAPixel(0, 0, 256));
//    assertThrows(IllegalArgumentException.class, () -> this.RGBAPixel1 = new RGBAPixel(1000, 1000,
//        1000));
//
//    assertThrows(IllegalArgumentException.class, () -> this.RGBAPixel3 = new RGBAPixel(-1, 0, 0,
//        0));
//    assertThrows(IllegalArgumentException.class, () -> this.RGBAPixel3 = new RGBAPixel(0, -1, 0,
//        0));
//    assertThrows(IllegalArgumentException.class, () -> this.RGBAPixel3 = new RGBAPixel(0, 0, -1,
//        0));
//    assertThrows(IllegalArgumentException.class, () -> this.RGBAPixel3 = new RGBAPixel(0, 0,
//        0, -1));
//    assertThrows(IllegalArgumentException.class, () -> this.RGBAPixel3 = new RGBAPixel(-100, -100,
//        -100, -100));
//
//    assertThrows(IllegalArgumentException.class, () -> this.RGBAPixel3 = new RGBAPixel(256, 0, 0));
//    assertThrows(IllegalArgumentException.class, () -> this.RGBAPixel3 = new RGBAPixel(0, 256, 0));
//    assertThrows(IllegalArgumentException.class, () -> this.RGBAPixel3 = new RGBAPixel(0, 0, 256));
//    assertThrows(IllegalArgumentException.class, () -> this.RGBAPixel3 = new RGBAPixel(0, 0,
//        0, 256));
//    assertThrows(IllegalArgumentException.class, () -> this.RGBAPixel3 = new RGBAPixel(1000, 1000,
//        1000));
//
//    assertThrows(IllegalArgumentException.class, () -> this.grayPixel = new RGBAPixel(-1));
//    assertThrows(IllegalArgumentException.class, () -> this.grayPixel = new RGBAPixel(256));
//  }

  @Test
  public void testGetRed() {
    assertEquals(0, this.RGBAPixel1.getRed());
    assertEquals(10, this.RGBAPixel2.getRed());
    assertEquals(50, this.RGBAPixel3.getRed());
  }

  @Test
  public void testGetGreen() {
    assertEquals(128, this.RGBAPixel1.getGreen());
    assertEquals(20, this.RGBAPixel2.getGreen());
    assertEquals(100, this.RGBAPixel3.getGreen());
  }

  @Test
  public void testGetBlue() {
    assertEquals(255, this.RGBAPixel1.getBlue());
    assertEquals(30, this.RGBAPixel2.getBlue());
    assertEquals(150, this.RGBAPixel3.getBlue());
  }

  @Test
  public void testGetAlpha() {
    assertEquals(255, this.RGBAPixel1.getAlpha());
    assertEquals(255, this.RGBAPixel2.getAlpha());
    assertEquals(234, this.RGBAPixel3.getAlpha());
  }

  @Test public void testEquals() {
    this.RGBAPixel2 = new RGBAPixel(0, 128, 255);
    assertTrue(this.RGBAPixel2.equals(this.RGBAPixel1));
    assertTrue(this.RGBAPixel1.equals(this.RGBAPixel2));

    this.RGBAPixel2 = new RGBAPixel(50, 100, 150, 234);
    assertTrue(this.RGBAPixel2.equals(this.RGBAPixel3));
    assertTrue(this.RGBAPixel3.equals(this.RGBAPixel2));

    this.RGBAPixel1 = new RGBAPixel(50, 100, 150, 234);
    assertTrue(this.RGBAPixel3.equals(this.RGBAPixel1));
    assertTrue(this.RGBAPixel1.equals(this.RGBAPixel3));
  }

  @Test public void testHashCode() {
    assertEquals(62397, this.RGBAPixel1.hashCode());
    assertEquals(30655, this.RGBAPixel2.hashCode());
    assertEquals(52298, this.RGBAPixel3.hashCode());
  }
}
