package cs3500.imageprocessor.operations;

import java.util.Objects;

import cs3500.imageprocessor.model.RGBAPixel;
import cs3500.imageprocessor.model.ImageState;

/**
 * A function object used to perform kernel transformations with a given rectangular matrix.
 */
public class KernelOperation implements PixelOperation {

  private final double[][] kernel;

  /**
   * Constructs an instance of a kernel transformation with the given kernel.
   * The given kernel must be square in shape.
   *
   * @param kernel the kernel to use for the transformation
   * @throws NullPointerException if the kernel is null or contains nulls
   * @throws IllegalArgumentException if the kernel is not a square matrix
   */
  public KernelOperation(double[][] kernel) {
    Objects.requireNonNull(kernel);

    int rowLength = kernel.length;
    int length = kernel[0].length;
    if (rowLength != length) {
      throw new IllegalArgumentException("Kernel must be square");
    }
    for (double[] row : kernel) {
      if (row.length != length) {
        throw new IllegalArgumentException("Kernel must be square");
      }
    }


    double[][] kernelCopy = new double[kernel.length][kernel[0].length];
    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < kernel[i].length; j++) {
        Objects.requireNonNull(kernel[i][j]);
        kernelCopy[i][j] = kernel[i][j];
      }
    }
    this.kernel = kernelCopy;
  }

  /**
   * Performs a single pixel transformation on the given image based on its position in the image,
   * and computes the new pixel.
   *
   * @param image the image to transform
   * @param r     the row coordinate of the pixel
   * @param c     the column coordinate of the pixel
   * @return the new pixel
   */
  @Override
  public RGBAPixel apply(ImageState image, int r, int c) {
    int red = 0;
    int green = 0;
    int blue = 0;

    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < kernel[i].length; j++) {
        int row = r + i - kernel.length / 2;
        int col = c + j - kernel[i].length / 2;
        if (row >= 0 && row < image.getHeight() && col >= 0 && col < image.getWidth()) {
          red += image.getPixelAt(row, col).getRed() * kernel[i][j];
          green += image.getPixelAt(row, col).getGreen() * kernel[i][j];
          blue += image.getPixelAt(row, col).getBlue() * kernel[i][j];
        }
      }
    }

    return new RGBAPixel(red, green, blue);
  }
}
