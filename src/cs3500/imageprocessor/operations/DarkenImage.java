package cs3500.imageprocessor.operations;

import cs3500.imageprocessor.model.IPixel;
import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.model.RGBPixel;

public class DarkenImage implements ImageXYToPixelTransformation {

  private final int darkenAmount;

  /**
   * Constructs a BrightenImage object.
   * @param darkenAmount the amount to brighten the image by
   */
  public DarkenImage(int darkenAmount) {
    this.darkenAmount = darkenAmount;
  }


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
    return new RGBPixel(
        Math.max(image.getPixelAt(r, c).getRed() - darkenAmount, 0),
        Math.max(image.getPixelAt(r, c).getGreen() - darkenAmount, 0),
        Math.max(image.getPixelAt(r, c).getBlue() - darkenAmount, 0));
  }
}
