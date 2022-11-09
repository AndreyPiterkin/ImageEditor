package cs3500.imageprocessor.model;

/**
 * An interface representing a pixel in an image. It guarantees the components red, green, blue,
 * and alpha. This is because RGBA is the most encapsulating form of RGB and allows for both the
 * general RGBA pixel to implemented and more specific versions of pixels that may not
 * care about alpha to still be represented as RGBA.
 */
public interface IPixel {

  /**
   * Gets the red channel of the pixel. We guarantee that every pixel can compute its own red
   * channel, no matter the representation, and return it as an integer between 0 and 255.
   * @return the red channel of the pixel
   */
  int getRed();

  /**
   * Gets the green channel of the pixel. We guarantee that every pixel can compute its own green
   * channel, no matter the representation, and return it as an integer between 0 and 255.
   * @return the red channel of the pixel
   */
  int getGreen();

  /**
   * Gets the blue channel of the pixel. We guarantee that every pixel can compute its own blue
   * channel, no matter the representation, and return it as an integer between 0 and 255.
   * @return the blue channel of the pixel
   */
  int getBlue();

  /**
   * Gets the alpha channel of the pixel. We guarantee that every pixel can compute its own alpha
   * channel, though pixels implementations without knowledge of the alpha channel will always
   * return 100% alpha. We also guarantee that the alpha channel will be returned as an integer from
   * 0 to 255.
   * @return the alpha channel of the pixel
   */
  int getAlpha();

  public static IPixel clampedRGBPixel(int red, int green, int blue) {
    return new IPixel() {
      @Override
      public int getRed() {
        return Math.min(Math.max(red, 0), 255);
      }

      @Override
      public int getGreen() {
        return Math.min(Math.max(green, 0), 255);
      }

      @Override
      public int getBlue() {
        return Math.min(Math.max(blue, 0), 255);
      }

      @Override
      public int getAlpha() {
        return 255;
      }
    };
  }
}
