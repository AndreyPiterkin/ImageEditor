package cs3500.imageprocessor.model;

public class RGBPixel implements IPixel {

  private final int red, green, blue;

  /**
   * Constructs an RGB pixel with the given red, green, and blue channels on a 0 - 255 scale.
   *
   * @param red   the red channel value of the RGB pixel
   * @param green the green channel value of the RGB pixel
   * @param blue  the blue channel value of the RGB pixel
   * @throws IllegalArgumentException if any of the channel values are not between 0 and 255
   *                                  (inclusive)
   */
  public RGBPixel(int red, int green, int blue) throws IllegalArgumentException {
    if (red < 0 || red > 255) {
      throw new IllegalArgumentException("Red channel must be between 0 and 255");
    }
    if (green < 0 || green > 255) {
      throw new IllegalArgumentException("Green channel must be between 0 and 255");
    }
    if (blue < 0 || blue > 255) {
      throw new IllegalArgumentException("Blue channel must be between 0 and 255");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Gets the red channel of the pixel. We guarantee that every pixel can compute its own red
   * channel, no matter the representation, and return it as an integer between 0 and 255.
   *
   * @return the red channel of the pixel
   */
  @Override
  public int getRed() {
    return this.red;
  }

  /**
   * Gets the green channel of the pixel. We guarantee that every pixel can compute its own green
   * channel, no matter the representation, and return it as an integer between 0 and 255.
   *
   * @return the red channel of the pixel
   */
  @Override
  public int getGreen() {
    return this.green;
  }

  /**
   * Gets the blue channel of the pixel. We guarantee that every pixel can compute its own blue
   * channel, no matter the representation, and return it as an integer between 0 and 255.
   *
   * @return the blue channel of the pixel
   */
  @Override
  public int getBlue() {
    return this.blue;
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

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof IPixel)) {
      return false;
    }
    IPixel that = (IPixel) o;
    return this.getRed() == that.getRed()
        && this.getBlue() == that.getBlue()
        && this.getGreen() == that.getGreen();
  }

  @Override
  public int hashCode() {
    return this.getRed() * 100 + this.getGreen() * 100 + this.getBlue() * 100;
  }
}
