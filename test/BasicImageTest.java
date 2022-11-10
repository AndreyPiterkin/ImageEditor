//import org.junit.Before;
//import org.junit.Test;
//
//import cs3500.imageprocessor.model.BasicImage;
//import cs3500.imageprocessor.model.RGBAPixel;
//import cs3500.imageprocessor.model.ImageState;
//import cs3500.imageprocessor.model.ImageUtil;
//import cs3500.imageprocessor.operations.DarkenPixel;
//import cs3500.imageprocessor.operations.FlipHorizontal;
//import cs3500.imageprocessor.operations.GrayscaleRed;
//import cs3500.imageprocessor.operations.VisualizeValue;
//
//import static org.junit.Assert.assertArrayEquals;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThrows;
//
///**
// * Tests for BasicImage.
// */
//public class BasicImageTest {
//  BasicImage image1x1;
//  BasicImage image2x2;
//  BasicImage image3x3;
//  BasicImage image3x3Copy;
//  RGBAPixel[][] initPixels1 = {{new RGBAPixel(0, 128, 240)}};
//  RGBAPixel[][] initPixels2 = {
//      {new RGBAPixel(0, 128, 240),
//          new RGBAPixel(1, 129, 241)},
//      {new RGBAPixel(2, 130, 242),
//          new RGBAPixel(3, 131, 243)}};
//  RGBAPixel[][] initPixels3 = {
//      {new RGBAPixel(0, 128, 240),
//          new RGBAPixel(1, 129, 241),
//          new RGBAPixel(0, 128, 240)},
//      {new RGBAPixel(2, 130, 242),
//          new RGBAPixel(3, 131, 243),
//          new RGBAPixel(2, 130, 242)},
//      {new RGBAPixel(2, 130, 242),
//          new RGBAPixel(3, 131, 243),
//          new RGBAPixel(2, 130, 242)}};
//
//
//  @Before
//  public void initData() {
//
//    this.image1x1 = new ImagePPM(this.initPixels1);
//    this.image2x2 = new ImagePPM(this.initPixels2);
//    this.image3x3 = new ImagePPM(this.initPixels3);
//    this.image3x3Copy = new ImagePPM(this.image3x3);
//  }
//
//  @Test
//  public void testInvalidConstructors() {
//    RGBAPixel[][] nullPixels = null;
//    assertThrows(IllegalArgumentException.class, () -> this.image1x1 = new ImagePPM(nullPixels));
//    RGBAPixel[][] emptyPixels = {};
//    assertThrows(IllegalArgumentException.class, () -> this.image1x1 = new ImagePPM(emptyPixels));
//    RGBAPixel[][] nullOuterPixels = {null};
//    assertThrows(NullPointerException.class, () -> this.image1x1 = new ImagePPM(nullOuterPixels));
//    RGBAPixel[][] nullInnerPixels = {{null}};
//    assertThrows(NullPointerException.class, () -> this.image1x1 = new ImagePPM(nullInnerPixels));
//  }
//
//  @Test
//  public void testGetWidth() {
//    assertEquals(1, this.image1x1.getWidth());
//    assertEquals(2, this.image2x2.getWidth());
//    assertEquals(3, this.image3x3.getWidth());
//    assertEquals(3, this.image3x3Copy.getWidth());
//
//  }
//
//  @Test
//  public void testGetHeight() {
//    assertEquals(1, this.image1x1.getHeight());
//    assertEquals(2, this.image2x2.getHeight());
//    assertEquals(3, this.image3x3.getHeight());
//    assertEquals(3, this.image3x3Copy.getHeight());
//  }
//
//  @Test
//  public void testGetPixelAt() {
//    for (int i = 0; i < this.image1x1.getHeight(); i++) {
//      for (int j = 0; j < this.image1x1.getWidth(); j++) {
//        assertEquals(this.initPixels1[i][j], this.image1x1.getPixelAt(i, j));
//      }
//    }
//    for (int i = 0; i < this.image2x2.getHeight(); i++) {
//      for (int j = 0; j < this.image2x2.getWidth(); j++) {
//        assertEquals(this.initPixels2[i][j], this.image2x2.getPixelAt(i, j));
//      }
//    }
//    for (int i = 0; i < this.image3x3.getHeight(); i++) {
//      for (int j = 0; j < this.image3x3.getWidth(); j++) {
//        assertEquals(this.initPixels3[i][j], this.image3x3.getPixelAt(i, j));
//      }
//    }
//    for (int i = 0; i < this.image3x3Copy.getHeight(); i++) {
//      for (int j = 0; j < this.image3x3Copy.getWidth(); j++) {
//        assertEquals(this.initPixels3[i][j], this.image3x3Copy.getPixelAt(i, j));
//      }
//    }
//  }
//
//  @Test
//  public void testApply() {
//    ImageState darkened = this.image2x2.apply(new DarkenPixel(10));
//    RGBAPixel[][] darkenedPixels = {
//        {new RGBAPixel(0, 118, 230),
//            new RGBAPixel(0, 119, 231)},
//        {new RGBAPixel(0, 120, 232),
//            new RGBAPixel(0, 121, 233)}};
//
//    ImageState flipHorz = this.image2x2.apply(new FlipHorizontal());
//    RGBAPixel[][] flipPixels = {
//        {new RGBAPixel(1, 129, 241),
//            new RGBAPixel(0, 128, 240)},
//        {new RGBAPixel(3, 131, 243),
//            new RGBAPixel(2, 130, 242)}};
//
//    ImageState grayscaleRed = this.image2x2.apply(new GrayscaleRed());
//    RGBAPixel[][] red = {
//        {new RGBAPixel(0, 0, 0),
//            new RGBAPixel(1, 1, 1)},
//        {new RGBAPixel(2, 2, 2),
//            new RGBAPixel(3, 3, 3)}};
//
//    ImageState valueImage = this.image2x2.apply(new VisualizeValue());
//    RGBAPixel[][] value = {
//        {new RGBAPixel(240, 240, 240),
//            new RGBAPixel(241, 241, 241)},
//        {new RGBAPixel(242, 242, 242),
//            new RGBAPixel(243, 243, 243)}};
//
//    for (int i = 0; i < 2; i++) {
//      for (int j = 0; j < 2; j++) {
//        assertEquals(darkenedPixels[i][j], darkened.getPixelAt(i, j));
//        assertEquals(flipPixels[i][j], flipHorz.getPixelAt(i, j));
//        assertEquals(red[i][j], grayscaleRed.getPixelAt(i, j));
//        assertEquals(value[i][j], valueImage.getPixelAt(i, j));
//      }
//    }
//  }
//
//  @Test
//  public void testSave() {
//    ImageUtil util = new ImageUtil();
//    this.image1x1.save("images/testReadPPM2.ppm");
//    RGBAPixel[][] readPixels1 = util.readPPM("images/testReadPPM2.ppm");
//    assertArrayEquals(this.initPixels1, readPixels1);
//
//    this.image2x2.save("images/testReadPPM1.ppm");
//    RGBAPixel[][] readPixels2 = util.readPPM("images/testReadPPM1.ppm");
//    assertArrayEquals(this.initPixels2, readPixels2);
//
//    this.image3x3.save("images/testReadPPM3.ppm");
//    RGBAPixel[][] readPixels3 = util.readPPM("images/testReadPPM3.ppm");
//    assertArrayEquals(this.initPixels3, readPixels3);
//  }
//}
