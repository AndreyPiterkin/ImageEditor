package cs3500.imageprocessor.operations;

import cs3500.imageprocessor.model.GrayscalePixel;
import cs3500.imageprocessor.model.IPixel;
import cs3500.imageprocessor.model.ImageState;

public class GrayscaleGreen implements ImageXYToPixelTransformation {

  /**
   * Performs a single pixel transformation on the given image based on its position in the image,
   * and computes the new pixel.
   *
   * @param image the image to transform
   * @param r     the row coordinate of the pixel
   * @param c     the column coordinate of the pixel
   * @return the new pixel
   */
  @Override
  public IPixel apply(ImageState image, Integer r, Integer c) {
    int grayScaleGreen = image.getPixelAt(r, c).getGreen();
    return new GrayscalePixel(grayScaleGreen);
  }
}
