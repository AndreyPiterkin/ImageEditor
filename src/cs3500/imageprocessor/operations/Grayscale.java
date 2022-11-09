package cs3500.imageprocessor.operations;

public class Grayscale extends AColorTransformation {
  /**
   * Constructs an instance of a color transformation.
   *
   */
  public Grayscale() {
    super(new double[][]{
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722}
    });
  }
}
