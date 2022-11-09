package cs3500.imageprocessor.operations;

import java.util.Objects;

import cs3500.imageprocessor.model.IPixel;
import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.model.RGBPixel;

/**
 * Represents an abstract instance of a kernel transformation, created to minimize code reuse
 * across similar kernel transformations.
 */
public class AKernelTransformation implements ImageRCToPixelTransformation {

  private final double[][] kernel;

  /**
   * Constructs an instance of a kernel transformation with the given kernel.
   *
   * @param kernel the kernel to use for the transformation
   * @throws NullPointerException if the kernel is null or contains nulls
   * @throws IllegalArgumentException if the kernel is not a rectangular matrix
   */
  public AKernelTransformation(double[][] kernel) {
    Objects.requireNonNull(kernel);

    int length = kernel[0].length;
    for (double[] row : kernel) {
      if (row.length != length) {
        throw new IllegalArgumentException("Kernel must be a rectangular matrix");
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
  public IPixel apply(ImageState image, Integer r, Integer c) {
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

    return IPixel.clampedRGBPixel(red, green, blue);
  }
}
