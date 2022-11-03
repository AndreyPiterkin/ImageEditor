import java.io.StringReader;
import cs3500.imageprocessor.controller.BasicEditorController;
import cs3500.imageprocessor.controller.ImageEditorController;
import cs3500.imageprocessor.model.BasicPPMImageEditor;
import cs3500.imageprocessor.model.ImageEditor;
import cs3500.imageprocessor.view.ImageEditorView;
import cs3500.imageprocessor.view.ImageEditorViewStub;

public class Main {
  public static void main(String[] args) {
    ImageEditor editor = new BasicPPMImageEditor();
    ImageEditorView view = new ImageEditorViewStub();
    Readable in = new StringReader("load images/Koala.ppm koala\ndarken 100 koala koala\nsave images/koala.ppm koala");
    ImageEditorController controller = new BasicEditorController(editor, in, view);
    controller.start();
  }
}
