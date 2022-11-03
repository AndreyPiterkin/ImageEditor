package cs3500.imageprocessor.operations;

import cs3500.imageprocessor.model.IPixel;
import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.model.RGBPixel;

/**
 * A function object used to brighten a pixel in an image.
 */
public class BrightenPixel implements ImageRCToPixelTransformation {

  private final int brightenAmount;

  /**
   * Constructs a BrightenImage object.
   * @param brightenAmount the amount to brighten the image by
   */
  public BrightenPixel(int brightenAmount) {
    this.brightenAmount = brightenAmount;
  }


  /**
   * Performs a single pixel transformation at the given position in the image, computing the new
   * pixel with the old pixel's RGB components each increased by this object's brightenAmount.
   *
   * @param image the image to transform
   * @param r     the row coordinate of the pixel
   * @param c     the column coordinate of the pixel
   * @return the new pixel
   */
  @Override
  public IPixel apply(ImageState image, Integer r, Integer c) {
    return new RGBPixel(
        Math.min(image.getPixelAt(r, c).getRed() + brightenAmount, 255),
        Math.min(image.getPixelAt(r, c).getGreen() + brightenAmount, 255),
        Math.min(image.getPixelAt(r, c).getBlue() + brightenAmount, 255));
  }
}
