package cs3500.imageprocessor.operations;

import java.util.Objects;

import cs3500.imageprocessor.model.RGBAPixel;
import cs3500.imageprocessor.model.ImageState;

/**
 * A function object used to perform color transformations with a given 3x3 matrix. What
 * color operation does is take the matrix multiplication of the column vector of the pixel's
 * RGB colors, and the 3x3 matrix kernel, to produce a new 3x1 column vector of the new RGB for the
 * new pixel.
 */
public class ColorOperation implements PixelOperation {

  private final double[][] kernel;

  /**
   * Constructs an instance of a color transformation.
   * @param kernel the kernel to use for the transformation
   *               (must be a 3x3 matrix)
   * @throws NullPointerException if the kernel is null or contains nulls
   * @throws IllegalArgumentException if the kernel is not a 3x3 matrix
   */
  public ColorOperation(double[][] kernel) {
    Objects.requireNonNull(kernel);

    if (kernel.length != 3) {
      throw new IllegalArgumentException("Kernel must be a 3x3 matrix");
    }

    for (double[] row : kernel) {
      if (row.length != 3) {
        throw new IllegalArgumentException("Kernel must be a 3x3 matrix");
      }
    }

    double[][] newKernel = new double[kernel.length][kernel[0].length];
    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < kernel[i].length; j++) {
        Objects.requireNonNull(kernel[i][j]);
        newKernel[i][j] = kernel[i][j];
      }
    }
    this.kernel = newKernel;
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
    RGBAPixel pixel = image.getPixelAt(r, c);
    int[] colors = new int[]{ pixel.getRed(), pixel.getGreen(), pixel.getBlue() };
    int[] newColors = new int[3];
    for (int i = 0; i < kernel.length; i++) {
      double newColor = 0;
      for (int j = 0; j < kernel[i].length; j++) {
        int color = colors[j];
        newColor += (color * kernel[i][j]);
      }
      newColors[i] = (int) newColor;
    }
    return new RGBAPixel(newColors[0], newColors[1], newColors[2]);
  }
}
