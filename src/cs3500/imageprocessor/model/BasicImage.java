package cs3500.imageprocessor.model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Objects;

import cs3500.imageprocessor.operations.PixelOperation;

/**
 * Represents a basic image state of a rectangular board of pixels.
 */
public class BasicImage implements ImageState {

  private final RGBAPixel[][] pixels;

  /**
   * A private constructor that allows for the creation of an image from an array of pixels.
   * For methods of this class only.
   * We specifically don't copy every pixel into a new array because we do not have mutations
   * on the arrays, or the pixels, so every new image is a deep copy of the old one (when using
   * filters).
   * @param pixels the array of pixels representing the image
   */
  private BasicImage(RGBAPixel[][] pixels) {

    this.pixels = Objects.requireNonNull(pixels);
  }

  /**
   * A constructor that makes a BasicImage from a buffered image.
   * @param img the buffered image to read from
   * @throws IllegalArgumentException if the image is null
   */
  public BasicImage(BufferedImage img) {
    if (img == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    this.pixels = new RGBAPixel[img.getHeight()][img.getWidth()];
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        int rgba = img.getRGB(j, i);
        Color color = new Color(rgba, true);
        this.pixels[i][j] = new RGBAPixel(color.getRed(), color.getGreen(), color.getBlue(),
            color.getAlpha());
      }
    }
  }

  /**
   * Gets the width of the image.
   *
   * @return the width of the image
   */
  @Override
  public int getWidth() {
    return this.pixels[0].length;
  }

  /**
   * Gets the height of the image.
   *
   * @return the height of the image
   */
  @Override
  public int getHeight() {
    return this.pixels.length;
  }

  /**
   * Gets the pixel at the given coordinates, if they are valid, otherwise throws an error.
   *
   * @param r the r coordinate of the pixel
   * @param c the y coordinate of the pixel
   * @return the pixel at the given coordinates
   * @throws IllegalArgumentException if the coordinates are invalid
   */
  @Override
  public RGBAPixel getPixelAt(int r, int c) {
    if (r < 0 || r >= this.getHeight() || c < 0 || c >= this.getWidth()) {
      throw new IllegalArgumentException("Invalid coordinates");
    }
    return this.pixels[r][c];
  }

  /**
   * Applies the given function to each pixel in the image, and returns a new image with the
   * transformed pixels.
   *
   * @param f the function to apply to each pixel
   * @return a new image with the transformed pixels
   */
  @Override
  public ImageState apply(PixelOperation f) {
    RGBAPixel[][] newPixels = new RGBAPixel[this.getHeight()][this.getWidth()];

    for (int r = 0; r < this.getHeight(); r++) {
      for (int c = 0; c < this.getWidth(); c++) {
        newPixels[r][c] = f.apply(this, r, c);
      }
    }

    return new BasicImage(newPixels);
  }

  /**
   * Creates a BufferedImage for writing to the disk out of the pixels of this image.
   * @param pixelType the type of pixels in the image (i.e. ARGB, RGB, etc)
   * @return the buffered image representation of this image
   */
  public BufferedImage asBufferedImage(int pixelType) {
    BufferedImage img = new BufferedImage(this.getWidth(), this.getHeight(), pixelType);
    for (int r = 0; r < this.getHeight(); r++) {
      for (int c = 0; c < this.getWidth(); c++) {
        RGBAPixel pixel = this.getPixelAt(r, c);
        Color color = new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue(),
            pixel.getAlpha());
        img.setRGB(c, r, color.getRGB());
      }
    }
    return img;
  }

}
