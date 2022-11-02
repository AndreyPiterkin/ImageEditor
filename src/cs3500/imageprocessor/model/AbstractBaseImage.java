package cs3500.imageprocessor.model;

public abstract class AbstractBaseImage implements ImageState {

  private final IPixel[][] pixels;
  private final int width;
  private final int height;

  public AbstractBaseImage(IPixel[][] pixels) {
    if (pixels == null) {
      throw new IllegalArgumentException("Image cannot be null");
    } else if (pixels.length == 0) {
      throw new IllegalArgumentException("Image must have at least one row");
    } else if (pixels[0].length == 0) {
      throw new IllegalArgumentException("Image must have at least one column");
    }

    this.pixels = pixels;
    this.width = pixels.length;
    this.height = pixels[0].length;
  }

  public AbstractBaseImage(ImageState image) {
    IPixel[][] pixels = new IPixel[image.getWidth()][image.getHeight()];
    for (int r = 0; r < image.getWidth(); r++) {
      for (int c = 0; c < image.getHeight(); c++) {
        pixels[r][c] = image.getPixelAt(r, c);
      }
    }
    this.pixels = pixels;
    this.width = image.getWidth();
    this.height = image.getHeight();
  }

  /**
   * Gets the width of the image.
   *
   * @return the width of the image
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Gets the height of the image.
   *
   * @return the height of the image
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Gets the pixel at the given coordinates, if they are valid, otherwise throws an error.
   *
   * @param x the x coordinate of the pixel
   * @param y the y coordinate of the pixel
   * @return the pixel at the given coordinates
   * @throws IllegalArgumentException if the coordinates are invalid
   */
  @Override
  public IPixel getPixelAt(int x, int y) {
    if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
      throw new IllegalArgumentException("Invalid coordinates");
    }

    return this.pixels[x][y];
  }

  /**
   * Applies the given function to each pixel in the image, and returns a new image with the
   * transformed pixels.
   *
   * @param f the function to apply to each pixel
   * @return a new image with the transformed pixels
   */
  @Override
  public ImageState apply(ImageXYToPixelTransformation f) {
    IPixel[][] newPixels = new IPixel[this.width][this.height];

    for (int x = 0; x < this.width; x++) {
      for (int y = 0; y < this.height; y++) {
        newPixels[x][y] = f.apply(this, x, y);
      }
    }

    return createImage(newPixels);
  }

  protected abstract ImageState createImage(IPixel[][] pixels);

  @Override
  public abstract void save(String filePath);
}
