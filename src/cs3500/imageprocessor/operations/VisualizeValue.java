package cs3500.imageprocessor.operations;

import cs3500.imageprocessor.model.GrayscalePixel;
import cs3500.imageprocessor.model.IPixel;
import cs3500.imageprocessor.model.ImageState;

/**
 * A function object used to create a grayscale pixel using the maximum value of the given
 * pixel's RGB components.
 */
public class VisualizeValue implements ImageRCToPixelTransformation {

  /**
   * Performs a single pixel transformation at the given position in the image, computing a new
   * grayscale pixel with the old pixel's maximum RGB component value.
   *
   * @param image the image to transform
   * @param r     the row coordinate of the pixel
   * @param c     the column coordinate of the pixel
   * @return the new pixel
   */
  @Override
  public IPixel apply(ImageState image, Integer r, Integer c) {
    int val = Math.max(image.getPixelAt(r, c).getRed(),
        Math.max(image.getPixelAt(r, c).getGreen(), image.getPixelAt(r, c).getBlue()));
    return new GrayscalePixel(val);
  }
}
