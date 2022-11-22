import java.io.IOException;

import cs3500.imageprocessor.controller.GUIEditorController;
import cs3500.imageprocessor.operations.PixelOperation;

/**
 * Represents a mock controller for testing Feature functionality.
 */
public class BasicGUIControllerMock implements GUIEditorController {
  private final Appendable out;

  /**
   * Constructs a BasicGUIControllerMock object with the given appendable to put to.
   *
   * @param out the appendable to put to
   */
  BasicGUIControllerMock(Appendable out) {
    this.out = out;
  }

  @Override
  public void load(String path) {
    try {
      this.out.append("load " + path);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void save(String path) {
    try {
      this.out.append("save " + path);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void operate(PixelOperation operation) {
    try {
      this.out.append("operate " + operation.getClass());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void start() {
    // does nothing, since this only exists for test purposes and we want to test that the features
    // interface works correctly with these new methods
  }
}
