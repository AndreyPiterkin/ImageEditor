import java.awt.image.BufferedImage;
import java.io.IOException;

import cs3500.imageprocessor.controller.Features;
import cs3500.imageprocessor.view.ImageEditorGUIView;

/**
 * A mock view used for testing the controller. None of the methods do anything just to
 * be able to construct a controller with a GUI view for testing it.
 */
public class JFrameViewStub implements ImageEditorGUIView {
  @Override
  public void makeVisible() {
    // does nothing
  }

  @Override
  public void addFeatures(Features features) {
    // does nothing
  }

  @Override
  public void renderImage(BufferedImage image) {
    // does nothing
  }

  @Override
  public void render(String message) throws IOException {
    // does nothing
  }
}
