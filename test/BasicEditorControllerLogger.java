

import java.io.IOException;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Function;

import cs3500.imageprocessor.controller.BasicEditorController;
import cs3500.imageprocessor.model.ImageEditor;
import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.operations.PixelOperation;
import cs3500.imageprocessor.view.ImageEditorView;

/**
 * A controller for the Image Editor that logs all operations to an appendable.
 * This is necessary for testing command design functionality, to test that
 * the right commands are being called, and that saving and loading is accessing the model
 * properly and is writing things to disk.
 * This is a subclass because all the functionality is the same, except we have to modify
 * the setupCommands method to log the commands.
 */
public class BasicEditorControllerLogger extends BasicEditorController {

  private final Appendable log;

  /**
   * Constructs a BasicEditorControllerLogger object with the model and view to control, as well
   * as where to get commands from, and a log to put to.
   *
   * @param model the model to control
   * @param in    the readable to read from
   * @param view  the view to control
   * @param log   the log to put to
   * @throws NullPointerException PURPOSEFULLY if any of the given parameters are null.
   */
  public BasicEditorControllerLogger(ImageEditor model, Readable in, ImageEditorView view,
       Appendable log) {
    super(model, in, view);
    this.log = log;
  }

  /**
   * Constructs a BasicEditorController object with the model and view to control, and from
   * System.in.
   *
   * @param model the model to control
   * @param view  the view to control
   * @throws NullPointerException PURPOSEFULLY if any of the given parameters are null.
   */
  public BasicEditorControllerLogger(ImageEditor model, ImageEditorView view, Appendable log) {
    super(model, view);
    this.log = log;
  }

  @Override
  protected void setupCommands() {
    super.setupCommands();
    for (String s : this.commands.keySet()) {
      Function<Scanner, PixelOperation> func = this.commands.get(s);
      this.commands.put(s, (scanner) -> {
        return new ForwardingFilter(func.apply(scanner), s, this.log);
      });
    }

    for (String s : this.imageWriters.keySet()) {
      BiConsumer<ImageState, String> func = this.imageWriters.get(s);
      this.imageWriters.put(s, (state, filename) -> {
        try {
          this.log.append("Writing image to " + filename + " in " + s + " format.\n");
        } catch (IOException e) {
          throw new IllegalStateException("Can't write to log.");
        }
        func.accept(state, filename);
      });
    }
  }
}
