import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import cs3500.imageprocessor.controller.BasicEditorController;
import cs3500.imageprocessor.controller.BasicGUIController;
import cs3500.imageprocessor.controller.ImageEditorController;
import cs3500.imageprocessor.model.BasicImageEditor;
import cs3500.imageprocessor.model.ImageEditor;
import cs3500.imageprocessor.view.ImageEditorGUIView;
import cs3500.imageprocessor.view.ImageEditorView;
import cs3500.imageprocessor.view.ImageEditorViewStub;
import cs3500.imageprocessor.view.JFrameView;

/**
 * A main class for running the editor.
 */
public class Main {

  /**
   * The main function to run the editor with support for command line arguments.
   * @param args command-line inputs
   */
  public static void main(String[] args)  {
    ImageEditor model = new BasicImageEditor();
    ImageEditorController controller;
    if (args.length == 0) {
      ImageEditorGUIView view = new JFrameView();
      controller = new BasicGUIController(model, view);
    } else if (args.length == 1) {
      ImageEditorView view = new ImageEditorViewStub();
      controller = new BasicEditorController(model, view);
    } else {
      if (args.length != 2) {
        throw new IllegalArgumentException("Invalid number of arguments");
      } else {
        if (args[0].equals("-file")) {
          try {
            ImageEditorView view = new ImageEditorViewStub();
            controller = new BasicEditorController(model, new FileReader(new File(args[1])), view);
          } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Invalid script file");
          }
        } else {
          throw new IllegalArgumentException("Invalid argument");
        }
      }

    }
    controller.start();

  }
}
