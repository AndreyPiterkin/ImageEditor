package cs3500.imageprocessor.view;

import java.io.IOException;

/**
 * Represents a view for the image editor.
 * WARNING: This is currently an unsupported feature, and exists as a placeholder stub
 * for future development, and to describe the interface for the controller.
 */
public interface ImageEditorView {

  /**
   * Renders the given message to the view.
   * @param message the message to render
   * @throws IOException if the view can't write to the output
   */
  void render(String message) throws IOException;


}
