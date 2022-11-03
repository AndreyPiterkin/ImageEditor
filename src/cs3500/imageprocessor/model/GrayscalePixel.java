package cs3500.imageprocessor.model;

public class GrayscalePixel implements IPixel {

  private final int value;

  /**
   * Constructs a grayscale pixel with the given value.
   *
   * @param value the value of the pixel
   */
  public GrayscalePixel(int value) {
    if (value < 0 || value > 255) {
      throw new IllegalArgumentException("Invalid pixel value");
    }
    this.value = value;
  }

  /**
   * Gets the red channel of the pixel. We guarantee that every pixel can compute its own red
   * channel, no matter the representation, and return it as an integer between 0 and 255.
   *
   * @return the red channel of the pixel
   */
  @Override
  public int getRed() {
    return this.value;
  }

  /**
   * Gets the green channel of the pixel. We guarantee that every pixel can compute its own green
   * channel, no matter the representation, and return it as an integer between 0 and 255.
   *
   * @return the red channel of the pixel
   */
  @Override
  public int getGreen() {
    return this.value;
  }

  /**
   * Gets the blue channel of the pixel. We guarantee that every pixel can compute its own blue
   * channel, no matter the representation, and return it as an integer between 0 and 255.
   *
   * @return the blue channel of the pixel
   */
  @Override
  public int getBlue() {
    return this.value;
  }

  /**
   * Gets the alpha channel of the pixel. We guarantee that every pixel can compute its own alpha
   * channel, though pixels implementations without knowledge of the alpha channel will always
   * return 100% alpha. We also guarantee that the alpha channel will be returned as an integer from
   * 0 to 99.
   *
   * @return the alpha channel of the pixel
   */
  @Override
  public int getAlpha() {
    return 99;
  }
}
