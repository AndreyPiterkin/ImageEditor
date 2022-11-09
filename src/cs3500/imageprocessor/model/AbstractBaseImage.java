package cs3500.imageprocessor.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Function;

import javax.imageio.ImageIO;

import cs3500.imageprocessor.operations.ImageRCToPixelTransformation;

/**
 * An abstract class representing an image.
 */
public abstract class AbstractBaseImage implements ImageState {

  private final IPixel[][] pixels;

  /**
   * A constructor that allows for the creation of an image from an array of pixels.
   * @param pixels the array of pixels representing the image
   */
  public AbstractBaseImage(IPixel[][] pixels) {
    if (pixels == null) {
      throw new IllegalArgumentException("Image cannot be null");
    } else if (pixels.length == 0) {
      throw new IllegalArgumentException("Image must have at least one row");
    } else if (pixels[0].length == 0) {
      throw new IllegalArgumentException("Image must have at least one column");
    }
    this.pixels = new IPixel[pixels.length][pixels[0].length];
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        this.pixels[i][j] = Objects.requireNonNull(pixels[i][j]);
      }
    }
  }

  /**
   * A constructor that allows for the conversion of an image from another format to the current
   * object's format.
   * @param image the image to be converted
   */
  public AbstractBaseImage(ImageState image) {
    IPixel[][] pixels = new IPixel[image.getHeight()][image.getWidth()];
    for (int r = 0; r < image.getHeight(); r++) {
      for (int c = 0; c < image.getWidth(); c++) {
        pixels[r][c] = image.getPixelAt(r, c);
      }
    }
    this.pixels = pixels;
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
  public IPixel getPixelAt(int r, int c) {
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
  public ImageState apply(ImageRCToPixelTransformation f) {
    IPixel[][] newPixels = new IPixel[this.getHeight()][this.getWidth()];

    for (int r = 0; r < this.getHeight(); r++) {
      for (int c = 0; c < this.getWidth(); c++) {
        newPixels[r][c] = f.apply(this, r, c);
      }
    }

    return createImage(newPixels);
  }

  /**
   * Creates a BufferedImage for writing to the disk out of the pixels of this image.
   * This is a protected method of the abstract class, not a promise of the interface,
   * because this would leak implementation details of this set of image classes.
   * The user doesn't need to know that we use BufferedImage to save images to disk,
   * and they shouldn't be able to call this method.
   * Thus, this is a protected method only for use by subclasses, and for testing.
   * @param bufferedImageType the type of BufferedImage to create (RGB, ARGB, etc)
   *                          @see BufferedImage
   * @param colorFunc the function that converts from pixel data to a color (this is used for
   *                  parametrizing the color, as in RGBA vs RGB for PNG vs everything else).
   * @return the buffered image representation of this image
   */
  protected BufferedImage preprocessForSave(int bufferedImageType, Function<IPixel, Color> colorFunc) {
    BufferedImage img = new BufferedImage(this.getWidth(), this.getHeight(), bufferedImageType);
    for (int r = 0; r < this.getHeight(); r++) {
      for (int c = 0; c < this.getWidth(); c++) {
        IPixel pixel = this.getPixelAt(r, c);
        Color color = colorFunc.apply(pixel);
        img.setRGB(c, r, color.getRGB());
      }
    }
    return img;
  }

  protected void saveToDisk(BufferedImage img, String format, String filename) {
    try {
      ImageIO.write(img, format, new File(filename));
    } catch (IOException e) {
      throw new IllegalArgumentException("Invalid file path");
    }
  }

  protected abstract ImageState createImage(IPixel[][] pixels);

  @Override
  public abstract void save(String filePath);
}
