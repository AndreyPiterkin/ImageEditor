import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

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
   * The main function to run the editor with support for command line arguments.
   * @param args command-line inputs
   */
  public static void main(String[] args) throws FileNotFoundException {
    ImageEditor model = new BasicImageEditor();
    ImageEditorView view = new ImageEditorViewStub();
    ImageEditorController controller;
    if (args.length == 0) {
      controller = new BasicEditorController(model, view);
    } else {
      if (args.length != 2) {
        throw new IllegalArgumentException("Invalid number of arguments");
      } else {
        if (args[0].equals("-file")) {
          controller = new BasicEditorController(model, new FileReader(new File(args[1])), view);
        } else {
          throw new IllegalArgumentException("Invalid argument");
        }
      }

    }
    controller.start();

  }
}
