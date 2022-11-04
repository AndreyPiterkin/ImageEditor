import java.io.StringReader;
import cs3500.imageprocessor.controller.BasicEditorController;
import cs3500.imageprocessor.controller.ImageEditorController;
import cs3500.imageprocessor.model.BasicPPMImageEditor;
import cs3500.imageprocessor.model.ImageEditor;
import cs3500.imageprocessor.operations.BrightenPixel;
import cs3500.imageprocessor.operations.DarkenPixel;
import cs3500.imageprocessor.operations.FlipHorizontal;
import cs3500.imageprocessor.operations.FlipVertical;
import cs3500.imageprocessor.operations.GrayscaleRed;
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
  public static void main(String[] args) {
    ImageEditor editor = new BasicPPMImageEditor();
    ImageEditorView view = new ImageEditorViewStub();
    editor.importImageFromDisk("res/bunny.ppm", "bunny");
    editor.applyFilterAndSave("bunny", "bunnydark", new DarkenPixel(100));
    editor.applyFilterAndSave("bunny", "bunnybright", new BrightenPixel(100));
    editor.applyFilterAndSave("bunny", "bunnyflipv", new FlipVertical());
    editor.applyFilterAndSave("bunny", "bunnyfliph", new FlipHorizontal());
    editor.applyFilterAndSave("bunny", "red", new GrayscaleRed());
    editor.getImage("bunnydark").save("res/bunnydark.ppm");
    editor.getImage("bunnybright").save("res/bunnybright.ppm");
    editor.getImage("bunnyflipv").save("res/bunnyflipv.ppm");
    editor.getImage("bunnyfliph").save("res/bunnyfliph.ppm");
    editor.getImage("red").save("res/bunny_red.ppm");

  }
}
