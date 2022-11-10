package cs3500.imageprocessor.operations;

import cs3500.imageprocessor.model.RGBAPixel;
import cs3500.imageprocessor.model.ImageState;

/**
 * A function object used to create a grayscale pixel using the luma of the given pixel.
 */
public class VisualizeLuma implements PixelOperation {

  /**
   * Performs a single pixel transformation at the given position in the image, computing a new
   * grayscale pixel with the old pixel's luma value.
   *
   * @param image the image to transform
   * @param r     the row coordinate of the pixel
   * @param c     the column coordinate of the pixel
   * @return the new pixel
   */
  @Override
  public RGBAPixel apply(ImageState image, int r, int c) {
    int luma = (int) (image.getPixelAt(r, c).getRed() * 0.2126
        + image.getPixelAt(r, c).getGreen() * 0.7152
        + image.getPixelAt(r, c).getBlue() * 0.0722);
    return new RGBAPixel(luma);
  }
}
