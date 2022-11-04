package cs3500.imageprocessor.operations;

import cs3500.imageprocessor.model.IPixel;
import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.model.RGBPixel;

/**
 * A function object used to darken a pixel in an image.
 */
public class DarkenPixel implements ImageRCToPixelTransformation {

  private final int darkenAmount;

  /**
   * Constructs a DarkenImage object.
   * @param darkenAmount the amount to brighten the image by
   */
  public DarkenPixel(int darkenAmount) {
    this.darkenAmount = darkenAmount;
  }


  /**
   * Performs a single pixel transformation at the given position in the image, computing the new
   * pixel with the old pixel's RGB components each decreased by this object's darkenAmount.
   *
   * @param image the image to transform
   * @param r     the row coordinate of the pixel
   * @param c     the column coordinate of the pixel
   * @return the new pixel
   */
  @Override
  public IPixel apply(ImageState image, Integer r, Integer c) {
    return new RGBPixel(
        Math.max(image.getPixelAt(r, c).getRed() - darkenAmount, 0),
        Math.max(image.getPixelAt(r, c).getGreen() - darkenAmount, 0),
        Math.max(image.getPixelAt(r, c).getBlue() - darkenAmount, 0));
  }
}
