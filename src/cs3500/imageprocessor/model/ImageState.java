package cs3500.imageprocessor.model;

import cs3500.imageprocessor.operations.ImageRCToPixelTransformation;

/**
 * Represents an image, with all of its image data. It guarantees that we can get image dimensions
 * and valid pixels, and make function applications to pixels.
 */
public interface ImageState {

  /**
   * Gets the width of the image.
   * @return the width of the image
   */
  int getWidth();

  /**
   * Gets the height of the image.
   * @return the height of the image
   */
  int getHeight();

  /**
   * Gets the pixel at the given coordinates, if they are valid, otherwise throws an error.
   * @param r the x coordinate of the pixel
   * @param c the y coordinate of the pixel
   * @return the pixel at the given coordinates
   * @throws IllegalArgumentException if the coordinates are invalid
   */
  IPixel getPixelAt(int r, int c);

  /**
   * Applies the given function to each pixel in the image, and returns a new image with the
   * transformed pixels.
   * @param f the function to apply to each pixel
   * @return a new image with the transformed pixels
   */
  ImageState apply(ImageRCToPixelTransformation f);

  /**
   * Saves the image to the given file path on the disk using the given formatting function.
   * @param filePath the file name to save the image to
   */
  void save(String filePath);

}
