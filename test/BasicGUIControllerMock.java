import java.io.IOException;

import cs3500.imageprocessor.controller.GUIEditorController;
import cs3500.imageprocessor.operations.PixelOperation;

public class BasicGUIControllerMock implements GUIEditorController {
  private final Appendable out;

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

  }
}
