package cs3500.imageprocessor.operations;

import cs3500.imageprocessor.model.GrayscalePixel;
import cs3500.imageprocessor.model.IPixel;
import cs3500.imageprocessor.model.ImageState;

/**
 * A function object used to create a grayscale pixel using the red component of the given pixel.
 */
public class GrayscaleRed implements ImageRCToPixelTransformation {

  /**
   * Performs a single pixel transformation at the given position in the image, computing a new
   * grayscale pixel with the old pixel's red component value.
   *
   * @param image the image to transform
   * @param r     the row coordinate of the pixel
   * @param c     the column coordinate of the pixel
   * @return the new pixel
   */
  @Override
  public IPixel apply(ImageState image, Integer r, Integer c) {
    int grayScaleRedVal = image.getPixelAt(r, c).getRed();
    return new GrayscalePixel(grayScaleRedVal);
  }
}
