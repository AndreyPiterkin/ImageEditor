import org.junit.Before;
import org.junit.Test;

import cs3500.imageprocessor.controller.BasicProcessorFeatures;
import cs3500.imageprocessor.controller.GUIEditorController;

import static org.junit.Assert.assertEquals;

/**
 * Tests that BasicProcessorFeatures uses the controller as intended.
 */
public class BasicProcessorFeaturesTest {
  Appendable out;
  GUIEditorController controller;
  BasicProcessorFeatures features;

  @Before
  public void initData() {
    this.out = new StringBuilder();
    this.controller = new BasicGUIControllerMock(this.out);
    this.features = new BasicProcessorFeatures(this.controller);
  }

  @Test
  public void load() {
    this.features.load("test.ppm");
    assertEquals("load test.ppm", this.out.toString());
  }

  @Test
  public void save() {
    this.features.save("test.ppm");
    assertEquals("save test.ppm", this.out.toString());
  }

  @Test
  public void blur() {
    this.features.blur();
    assertEquals("operate class cs3500.imageprocessor.operations.GaussianBlur",
        this.out.toString());
  }

  @Test
  public void sharpen() {
    this.features.sharpen();
    assertEquals("operate class cs3500.imageprocessor.operations.Sharpen",
        this.out.toString());
  }

  @Test
  public void grayscale() {
    this.features.grayscale();
    assertEquals("operate class cs3500.imageprocessor.operations.Grayscale",
        this.out.toString());
  }

  @Test
  public void sepia() {
    this.features.sepia();
    assertEquals("operate class cs3500.imageprocessor.operations.SepiaTone",
        this.out.toString());
  }

  @Test
  public void brighten() {
    this.features.brighten(10);
    assertEquals("operate class cs3500.imageprocessor.operations.BrightenPixel",
        this.out.toString());
  }

  @Test
  public void darken() {
    this.features.darken(10);
    assertEquals("operate class cs3500.imageprocessor.operations.DarkenPixel",
        this.out.toString());
  }

  @Test
  public void flipHorizontal() {
    this.features.flipHorizontal();
    assertEquals("operate class cs3500.imageprocessor.operations.FlipHorizontal",
        this.out.toString());
  }

  @Test
  public void flipVertical() {
    this.features.flipVertical();
    assertEquals("operate class cs3500.imageprocessor.operations.FlipVertical",
        this.out.toString());
  }

  @Test
  public void blueComponent() {
    this.features.blueComponent();
    assertEquals("operate class cs3500.imageprocessor.operations.GrayscaleBlue",
        this.out.toString());
  }

  @Test
  public void greenComponent() {
    this.features.greenComponent();
    assertEquals("operate class cs3500.imageprocessor.operations.GrayscaleGreen",
        this.out.toString());
  }

  @Test
  public void redComponent() {
    this.features.redComponent();
    assertEquals("operate class cs3500.imageprocessor.operations.GrayscaleRed",
        this.out.toString());
  }

  @Test
  public void intensityComponent() {
    this.features.intensityComponent();
    assertEquals("operate class cs3500.imageprocessor.operations.VisualizeIntensity",
        this.out.toString());
  }

  @Test
  public void lumaComponent() {
    this.features.lumaComponent();
    assertEquals("operate class cs3500.imageprocessor.operations.VisualizeLuma",
        this.out.toString());
  }

  @Test
  public void valueComponent() {
    this.features.valueComponent();
    assertEquals("operate class cs3500.imageprocessor.operations.VisualizeValue",
        this.out.toString());
  }
}