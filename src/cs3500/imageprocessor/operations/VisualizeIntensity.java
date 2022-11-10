package cs3500.imageprocessor.operations;

import cs3500.imageprocessor.model.RGBAPixel;
import cs3500.imageprocessor.model.ImageState;

/**
 * A function object used to create a grayscale pixel using the intensity of the given pixel.
 */
public class VisualizeIntensity implements PixelOperation {

  /**
   * Performs a single pixel transformation at the given position in the image, computing a new
   * grayscale pixel with the old pixel's intensity value.
   *
   * @param image the image to transform
   * @param r     the row coordinate of the pixel
   * @param c     the column coordinate of the pixel
   * @return the new pixel
   */
  @Override
  public RGBAPixel apply(ImageState image, int r, int c) {
    int val = (image.getPixelAt(r, c).getRed() + image.getPixelAt(r, c).getGreen()
        + image.getPixelAt(r, c).getBlue()) / 3;
    return new RGBAPixel(val);
  }
}
