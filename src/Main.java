import java.io.IOException;

import cs3500.imageprocessor.controller.BasicEditorController;
import cs3500.imageprocessor.controller.ImageEditorController;
import cs3500.imageprocessor.model.BasicImageEditor;
import cs3500.imageprocessor.model.ImageEditor;
import cs3500.imageprocessor.view.ImageEditorView;
import cs3500.imageprocessor.view.ImageEditorViewStub;

/**
 * A main class for running the editor.
 */
public class Main {
  /**
   * The main function to run the editor.
   * @param args command-line inputs
   */
  public static void main(String[] args) throws IOException {
    ImageEditor model = new BasicImageEditor();
    ImageEditorView view = new ImageEditorViewStub();
    ImageEditorController controller = new BasicEditorController(model, view);
    controller.start();

  }
}
