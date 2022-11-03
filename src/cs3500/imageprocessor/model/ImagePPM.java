package cs3500.imageprocessor.model;

import java.io.FileWriter;
import java.io.IOException;

/**
 * A class representing an image of pixels that is formatted as a PPM.
 */
public class ImagePPM extends AbstractBaseImage {

  public ImagePPM(IPixel[][] pixels) {
    super(pixels);
  }

  public ImagePPM(ImageState image) {
    super(image);
  }

  @Override
  protected ImageState createImage(IPixel[][] pixels) {
    return new ImagePPM(pixels);
  }

  @Override
  public void save(String filePath) {
    try {
      FileWriter writer = new FileWriter(filePath);
      writer.write("P3\n");
      writer.write(this.getWidth() + " " + this.getHeight() + "\n");
      writer.write("255\n");
      for (int r = 0; r < this.getHeight(); r++) {
        for (int c = 0; c < this.getWidth(); c++) {
          writer.write(this.getPixelAt(r, c).getRed() + " "
              + this.getPixelAt(r, c).getGreen() + " "
              + this.getPixelAt(r, c).getBlue() + "\n");
        }
        writer.write("\n");
      }
      writer.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
