import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import cs3500.imageprocessor.model.BasicPPMImageEditor;
import cs3500.imageprocessor.model.IPixel;
import cs3500.imageprocessor.model.ImageBMP;
import cs3500.imageprocessor.model.ImageEditor;
import cs3500.imageprocessor.model.ImageJPG;
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
    model.importImageFromDisk("res/me2.ppm", "me");
    model.importImageFromDisk("res/me2.bmp", "me2");
    ImageState i1 = model.getImage("me");
    ImageState i2 = model.getImage("me2");

    for (int r = 0; r < i1.getHeight(); r++) {
      for (int c = 0; c < i1.getWidth(); c++) {
        IPixel p = i1.getPixelAt(r, c);
        IPixel p2 = i2.getPixelAt(r, c);
        if (p.getRed() != p2.getRed() || p.getGreen() != p2.getGreen() || p.getBlue() != p2.getBlue()) {
          System.out.println("Different at " + r + ", " + c);
        }
      }
    }
  }
}
