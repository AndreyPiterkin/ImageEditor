package cs3500.imageprocessor.operations;

import java.util.Objects;

import cs3500.imageprocessor.model.IPixel;
import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.model.RGBPixel;

public class AColorTransformation implements ImageRCToPixelTransformation {

  private final double[][] kernel;

  /**
   * Constructs an instance of a color transformation.
   * @param kernel the kernel to use for the transformation
   *               (must be a 3x3 matrix)
   * @throws NullPointerException if the kernel is null or contains nulls
   * @throws IllegalArgumentException if the kernel is not a 3x3 matrix
   */
  public AColorTransformation(double[][] kernel) {
    Objects.requireNonNull(kernel);

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
  public IPixel apply(ImageState image, Integer r, Integer c) {
    IPixel pixel = image.getPixelAt(r, c);
    int[] colors = new int[]{ pixel.getRed(), pixel.getGreen(), pixel.getBlue() };
    int[] newColors = new int[3];
    for (int i = 0; i < kernel.length; i++) {
      int newColor = 0;
      for (int j = 0; j < kernel[i].length; j++) {
        int color = colors[j];
        newColor += (int) (color * kernel[i][j]);
      }
      newColors[i] = newColor;
    }
    return RGBPixel.clampedRGBPixel(newColors[0], newColors[1], newColors[2]);
  }
}
