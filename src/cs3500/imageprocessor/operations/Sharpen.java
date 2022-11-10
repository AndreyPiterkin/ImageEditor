package cs3500.imageprocessor.operations;

/**
 * Represents the classic sharpen kernel.
 */
public class Sharpen extends KernelOperation {
  /**
   * Constructs a Sharpen Kernel.
   */
  public Sharpen() {
    super(new double[][]{
        {-1.0/8.0, -1.0/8.0, -1.0/8.0, -1.0/8.0, -1.0/8.0},
        {-1.0/8.0, 0.25, 0.25, 0.25, -0.125},
        {-0.125, 0.25, 1.0, 0.25, -0.125},
        {-1.0/8.0, 0.25, 0.25, 0.25, -0.125},
        {-1.0/8.0, -1.0/8.0, -1.0/8.0, -1.0/8.0, -1.0/8.0}
    });
  }
}
