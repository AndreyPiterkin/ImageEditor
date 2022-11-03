import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiConsumer;

import cs3500.imageprocessor.controller.BasicEditorController;
import cs3500.imageprocessor.controller.ImageEditorController;
import cs3500.imageprocessor.model.BasicPPMImageEditor;
import cs3500.imageprocessor.model.ImageEditor;
import cs3500.imageprocessor.model.ImageEditorReadOnly;
import cs3500.imageprocessor.model.ImagePPM;
import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.operations.BrightenImage;
import cs3500.imageprocessor.operations.DarkenImage;
import cs3500.imageprocessor.operations.FlipVertical;
import cs3500.imageprocessor.operations.GrayscaleBlue;
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
