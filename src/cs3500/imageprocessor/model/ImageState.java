package cs3500.imageprocessor.model;

import java.awt.image.BufferedImage;

import cs3500.imageprocessor.operations.PixelOperation;

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
  RGBAPixel getPixelAt(int r, int c);

  /**
   * Applies the given function to each pixel in the image, and returns a new image with the
   * transformed pixels.
   * @param f the function to apply to each pixel
   * @return a new image with the transformed pixels
   */
  ImageState apply(PixelOperation f);

  /**
   * Creates a BufferedImage for writing to the disk out of the pixels of this image.
   * @param pixelType the type of pixels in the image (i.e. ARGB, RGB, etc)
   * @return the buffered image representation of this image
   */
  BufferedImage asBufferedImage(int pixelType);

}
