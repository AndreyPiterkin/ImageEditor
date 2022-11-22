package cs3500.imageprocessor.controller;

/**
 * Represents the features of the image editor. This interface is used to
 * set up all feature listeners in the view, and connect them to the controller.
 */
public interface Features {

  /**
   * Loads an image from the given file path.
   * @param path the path to the image file
   */
  void load(String path);

  /**
   * Saves the current image to the given file path.
   * @param path the path to the image file
   */
  void save(String path);

  /**
   * Blurs the current image.
   */
  void blur();

  /**
   * Sharpen the current image.
   */
  void sharpen();

  /**
   * Grayscale the current image.
   */
  void grayscale();

  /**
   * Sepia the current image.
   */
  void sepia();

  /**
   * Brighten the current image.
   * @param amount the amount to brighten the image
   */
  void brighten(int amount);

  /**
   * Darken the current image.
   * @param amount the amount to darken the image
   */
  void darken(int amount);

  /**
   * Flips the image horizontally.
   */
  void flipHorizontal();

  /**
   * Flips the image vertically.
   */
  void flipVertical();

  /**
   * Gets the blue component of every pixel in the image.
   */
  void blueComponent();

  /**
   * Gets the green component of every pixel in the image.
   */
  void greenComponent();

  /**
   * Gets the red component of every pixel in the image.
   */
  void redComponent();

  /**
   * Transforms the image to grayscale using its intensity.
   */
  void intensityComponent();

  /**
   * Transforms the image to grayscale using its luma.
   */
  void lumaComponent();

  /**
   * Transforms the image to grayscale using its value (average of RGB components).
   */
  void valueComponent();

}
