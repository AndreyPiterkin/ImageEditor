package cs3500.imageprocessor.operations;

import cs3500.imageprocessor.model.RGBAPixel;
import cs3500.imageprocessor.model.ImageState;

/**
 * A function object used to brighten a pixel in an image.
 */
public class BrightenPixel implements PixelOperation {

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
  public RGBAPixel apply(ImageState image, int r, int c) {
    RGBAPixel pixel = image.getPixelAt(r, c);
    return new RGBAPixel(pixel.getRed() + brightenAmount,
        pixel.getGreen() + brightenAmount,
        pixel.getBlue() + brightenAmount);
  }
}
