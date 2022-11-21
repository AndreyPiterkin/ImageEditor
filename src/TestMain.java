import cs3500.imageprocessor.controller.BasicGUIController;
import cs3500.imageprocessor.controller.ImageEditorController;
import cs3500.imageprocessor.model.BasicImageEditor;
import cs3500.imageprocessor.model.ImageEditor;
import cs3500.imageprocessor.view.ImageEditorGUIView;
import cs3500.imageprocessor.view.JFrameView;

public class TestMain {

  public static void main(String[] args) {
    ImageEditor editor = new BasicImageEditor();
    ImageEditorGUIView view = new JFrameView();
    ImageEditorController controller = new BasicGUIController(editor, view);
    controller.start();
  }

}
