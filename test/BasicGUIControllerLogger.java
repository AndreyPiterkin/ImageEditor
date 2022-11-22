import java.io.IOException;
import java.util.function.BiConsumer;

import cs3500.imageprocessor.controller.BasicGUIController;
import cs3500.imageprocessor.model.ImageEditor;
import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.operations.PixelOperation;
import cs3500.imageprocessor.view.ImageEditorGUIView;

public class BasicGUIControllerLogger extends BasicGUIController {
  private final Appendable log;

  /**
   * Constructs a BasicGUIControllerLogger object with the model and view to control, as well
   * as where to get commands from, and a log to put to.
   *
   * @param model the model to control
   * @param view  the view to control
   * @param log   the log to put to
   * @throws NullPointerException PURPOSEFULLY if any of the given parameters are null.
   */
  public BasicGUIControllerLogger(ImageEditor model, ImageEditorGUIView view,
                                     Appendable log) {
    super(model, view);
    this.log = log;
  }

  /**
   * Takes the commands that the super() call set up,
   * and modifies them to wrap them in a ForwardingFilter with this controller's log
   * to log what operations are happening to test if the controller is using the model
   * correctly. Similarly, it wraps the save and load commands in a log.
   */
  @Override
  protected void initializeWriters() {
    super.initializeWriters();
    for (String s : this.writers.keySet()) {
      BiConsumer<ImageState, String> func = this.writers.get(s);
      this.writers.put(s, (state, filename) -> {
        try {
          this.log.append("Writing image to " + filename + " in " + s + " format.\n");
        } catch (IOException e) {
          throw new IllegalStateException("Can't write to log.");
        }
        func.accept(state, filename);
      });
    }
  }

  @Override
  public void operate(PixelOperation pixelOperation) {
    try {
      this.log.append("apply " + pixelOperation.getClass() + "\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
