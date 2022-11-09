import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import cs3500.imageprocessor.model.BasicPPMImageEditor;
import cs3500.imageprocessor.model.ImageEditor;
import cs3500.imageprocessor.model.ImagePNG;
import cs3500.imageprocessor.model.ImagePPM;
import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.model.MultiformatImageEditor;
import cs3500.imageprocessor.operations.BrightenPixel;
import cs3500.imageprocessor.operations.DarkenPixel;
import cs3500.imageprocessor.operations.FlipHorizontal;
import cs3500.imageprocessor.operations.FlipVertical;
import cs3500.imageprocessor.operations.Grayscale;
import cs3500.imageprocessor.operations.GrayscaleRed;
import cs3500.imageprocessor.operations.SepiaTone;
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
  public static void main(String[] args) throws IOException {
    ImageEditor model = new MultiformatImageEditor();
    ImageEditorView view = new ImageEditorViewStub();
    model.importImageFromDisk("res/me.jpg", "cat");
    ImageState img = model.getImage("cat");
    new ImagePNG(img).save("res/me4.png");
  }
}
