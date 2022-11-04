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
    editor.importImageFromDisk("res/graphics_lab.ppm", "graphics_lab");
    editor.applyFilterAndSave("graphics_lab", "graphics_labdark", new DarkenPixel(100));
    editor.applyFilterAndSave("graphics_lab", "graphics_labbright", new BrightenPixel(100));
    editor.applyFilterAndSave("graphics_lab", "graphics_labflipv", new FlipVertical());
    editor.applyFilterAndSave("graphics_lab", "graphics_labfliph", new FlipHorizontal());
    editor.applyFilterAndSave("graphics_lab", "red", new GrayscaleRed());
    editor.getImage("graphics_labdark").save("res/graphics_labdark.ppm");
    editor.getImage("graphics_labbright").save("res/graphics_labbright.ppm");
    editor.getImage("graphics_labflipv").save("res/graphics_labflipv.ppm");
    editor.getImage("graphics_labfliph").save("res/graphics_labfliph.ppm");
    editor.getImage("red").save("res/graphics_lab_red.ppm");

  }
}
