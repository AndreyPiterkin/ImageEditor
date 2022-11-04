package cs3500.imageprocessor.operations;

import cs3500.imageprocessor.model.IPixel;
import cs3500.imageprocessor.model.ImageState;

/**
 * A function object used to get the pixel in an image located at the position reflected
 * horizontally from the given pixel.
 */
public class FlipHorizontal implements ImageRCToPixelTransformation {

  /**
   * Finds the pixel located at the position reflected horizontally from the given pixel.
   *
   * @param image the image to transform
   * @param r     the row coordinate of the pixel
   * @param c     the column coordinate of the pixel
   * @return the new pixel
   */
  @Override
  public IPixel apply(ImageState image, Integer r, Integer c) {
    return image.getPixelAt(r, image.getWidth() - c - 1);
  }
}
