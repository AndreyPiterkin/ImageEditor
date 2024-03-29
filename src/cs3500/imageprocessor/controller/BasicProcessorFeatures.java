package cs3500.imageprocessor.controller;

import java.util.Objects;

import cs3500.imageprocessor.operations.BrightenPixel;
import cs3500.imageprocessor.operations.DarkenPixel;
import cs3500.imageprocessor.operations.FlipHorizontal;
import cs3500.imageprocessor.operations.FlipVertical;
import cs3500.imageprocessor.operations.GaussianBlur;
import cs3500.imageprocessor.operations.Grayscale;
import cs3500.imageprocessor.operations.GrayscaleBlue;
import cs3500.imageprocessor.operations.GrayscaleGreen;
import cs3500.imageprocessor.operations.GrayscaleRed;
import cs3500.imageprocessor.operations.SepiaTone;
import cs3500.imageprocessor.operations.Sharpen;
import cs3500.imageprocessor.operations.VisualizeIntensity;
import cs3500.imageprocessor.operations.VisualizeLuma;
import cs3500.imageprocessor.operations.VisualizeValue;

/**
 * Represents a basic implementation of the Features interface.
 */
public class BasicProcessorFeatures implements Features {

  private final GUIEditorController controller;

  /**
   * Constructs a basic implementation of the Features interface.
   * @param controller the controller to use
   */
  public BasicProcessorFeatures(GUIEditorController controller) {
    this.controller = Objects.requireNonNull(controller);
  }

  @Override
  public void load(String path) {
    controller.load(path);
  }

  @Override
  public void save(String path) {
    controller.save(path);
  }

  @Override
  public void blur() {
    controller.operate(new GaussianBlur());
  }

  @Override
  public void sharpen() {
    controller.operate(new Sharpen());
  }

  @Override
  public void grayscale() {
    controller.operate(new Grayscale());
  }

  @Override
  public void sepia() {
    controller.operate(new SepiaTone());
  }

  @Override
  public void brighten(int amount) {
    controller.operate(new BrightenPixel(amount));
  }

  @Override
  public void darken(int amount) {
    controller.operate(new DarkenPixel(amount));
  }

  @Override
  public void flipHorizontal() {
    controller.operate(new FlipHorizontal());
  }

  @Override
  public void flipVertical() {
    controller.operate(new FlipVertical());
  }

  @Override
  public void blueComponent() {
    controller.operate(new GrayscaleBlue());
  }

  @Override
  public void greenComponent() {
    controller.operate(new GrayscaleGreen());
  }

  @Override
  public void redComponent() {
    controller.operate(new GrayscaleRed());
  }

  @Override
  public void intensityComponent() {
    controller.operate(new VisualizeIntensity());
  }

  @Override
  public void lumaComponent() {
    controller.operate(new VisualizeLuma());
  }

  @Override
  public void valueComponent() {
    controller.operate(new VisualizeValue());
  }
}
