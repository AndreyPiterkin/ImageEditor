package cs3500.imageprocessor.model;

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

    this.pixels = pixels;
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

  protected abstract ImageState createImage(IPixel[][] pixels);

  @Override
  public abstract void save(String filePath);
}
