package cs3500.imageprocessor.operations;

/**
 * A function object used to create a grayscale pixel using luma expressed as a color transformation
 * in matrix form.
 */
public class Grayscale extends ColorOperation {
  /**
   * Constructs an instance of a color transformation.
   */
  public Grayscale() {
    super(new double[][]{
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722}
    });
  }
}
