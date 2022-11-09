package cs3500.imageprocessor.model;

public class RGBAPixel implements IPixel {

  private final int red;
  private final int green;
  private final int blue;
  private final int alpha;

  public RGBAPixel(int red, int green, int blue, int alpha) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.alpha = alpha;
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
    return this.alpha;
  }
}
