package cs3500.imageprocessor.operations;

/**
 * Represents the traditional Gaussian blur kernel, with the following kernel:
 * [1/16 1/8 1/16
 * 1/8 1/4 1/8
 * 1/16 1/8 1/16]
 */
public class GaussianBlur extends KernelOperation {

  /**
   * Constructs an instance of the Gaussian Blur, with the particular kernel formula for this blur.
   */
  public GaussianBlur() {
    super(new double[][]{
        {1.0/16.0, 1.0/8.0, 1.0/16.0},
        {1.0/8.0, 1.0/4.0, 1.0/8.0},
        {1.0/16.0, 1.0/8.0, 1.0/16.0}
    });
  }
}
