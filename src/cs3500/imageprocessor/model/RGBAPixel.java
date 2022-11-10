package cs3500.imageprocessor.model;

/**
 * An interface representing a pixel in an image. It guarantees the components red, green, blue,
 * and alpha. This is because RGBA is the most encapsulating form of RGB and allows for both the
 * general RGBA pixel to implemented and more specific versions of pixels that may not
 * care about alpha to still be represented as RGBA.
 */
public class RGBAPixel {

  private int red;
  private int green;
  private int blue;
  private int alpha;

  /**
   * A constructor for an RGBA pixel.
   *
   * @param red   the red component of the pixel
   * @param green the green component of the pixel
   * @param blue  the blue component of the pixel
   * @param alpha the alpha component of the pixel
   */
  public RGBAPixel(int red, int green, int blue, int alpha) {
    this.red = Math.max(0, Math.min(255, red));
    this.green = Math.max(0, Math.min(255, green));
    this.blue = Math.max(0, Math.min(255, blue));
    this.alpha = Math.max(0, Math.min(255, alpha));
  }

  /**
   * A constructor for an RGB pixel.
   *
   * @param red   the red component of the pixel
   * @param green the green component of the pixel
   * @param blue  the blue component of the pixel
   */
  public RGBAPixel(int red, int green, int blue) {
    this(red, green, blue, 255);
  }

  /**
   * A constructor for a single-valued grayscale pixel.
   * @param gray the grayscale value of the pixel
   */
  public RGBAPixel(int gray) {
    this(gray, gray, gray, 255);
  }

  /**
   * Gets the red channel of the pixel.
   * @return the red channel of the pixel
   */
  public int getRed() {
    return this.red;
  }

  /**
   * Gets the green channel of the pixel.
   * @return the red channel of the pixel
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * Gets the blue channel of the pixel.
   * @return the blue channel of the pixel
   */
  public int getBlue() {
    return this.blue;
  }

  /**
   * Gets the alpha channel of the pixel.
   * @return the alpha channel of the pixel
   */
  public int getAlpha() {
    return this.alpha;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof RGBAPixel)) {
      return false;
    }
    RGBAPixel that = (RGBAPixel) o;
    return this.getRed() == that.getRed()
            && this.getBlue() == that.getBlue()
            && this.getGreen() == that.getGreen()
            && this.getAlpha() == that.getAlpha();
  }

  @Override
  public int hashCode() {
    return this.getRed() * 100 + this.getGreen() * 99 + this.getBlue() * 98
            + this.getAlpha() * 97;
  }

}
