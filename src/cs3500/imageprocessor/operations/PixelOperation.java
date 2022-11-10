package cs3500.imageprocessor.operations;

import cs3500.imageprocessor.model.RGBAPixel;
import cs3500.imageprocessor.model.ImageState;

/**
 * An interface for transformations that can be applied to a given pixel on a given image.
 */
public interface PixelOperation {

  /**
   * Performs a single pixel transformation on the given image based on its position in the image,
   * and computes the new pixel.
   *
   * @param image the image to transform
   * @param r     the row coordinate of the pixel
   * @param c     the column coordinate of the pixel
   * @return the new pixel
   */
  RGBAPixel apply(ImageState image, int r, int c);

}
