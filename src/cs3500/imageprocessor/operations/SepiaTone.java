package cs3500.imageprocessor.operations;

public class SepiaTone extends AColorTransformation {
  /**
   * Constructs a Sepia Tone kernel color transformation.
   */
  public SepiaTone() {
    super(new double[][]{
            { 0.393, 0.769, 0.189 },
            { 0.349, 0.686, 0.168 },
            { 0.272, 0.534, 0.131 }
    });
  }
}