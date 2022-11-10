package cs3500.imageprocessor.operations;

import cs3500.imageprocessor.model.RGBAPixel;
import cs3500.imageprocessor.model.ImageState;

/**
 * A function object used to get the pixel in an image located at the position reflected
 * vertically from the given pixel.
 */
public class FlipVertical implements PixelOperation {

  /**
   * Finds the pixel located at the position reflected vertically from the given pixel.
   *
   * @param image the image to transform
   * @param r     the row coordinate of the pixel
   * @param c     the column coordinate of the pixel
   * @return the new pixel
   */
  @Override
  public RGBAPixel apply(ImageState image, int r, int c) {
    return image.getPixelAt(image.getHeight() - r - 1, c);
  }
}
