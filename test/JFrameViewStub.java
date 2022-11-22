import java.awt.image.BufferedImage;
import java.io.IOException;

import cs3500.imageprocessor.controller.Features;
import cs3500.imageprocessor.view.ImageEditorGUIView;

/**
 * A mock view used for testing the controller.
 */
public class JFrameViewStub implements ImageEditorGUIView {
  @Override
  public void makeVisible() {

  }

  @Override
  public void addFeatures(Features features) {

  }

  @Override
  public void renderImage(BufferedImage image) {

  }

  @Override
  public void render(String message) throws IOException {

  }
}
