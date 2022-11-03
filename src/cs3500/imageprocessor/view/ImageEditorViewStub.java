package cs3500.imageprocessor.view;

import java.io.IOException;

/**
 * A mock view used for testing the controller.
 */
public class ImageEditorViewStub implements ImageEditorView {
  /**
   * Renders the given message to the view.
   *
   * @param message the message to render
   * @throws IOException if the view can't write to the output
   */
  @Override
  public void render(String message) throws IOException {
    // do nothing
  }
}
