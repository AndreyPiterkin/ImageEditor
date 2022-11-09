package cs3500.imageprocessor.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageBMP extends AbstractBaseImage {
  /**
   * A constructor that allows for the creation of an image from an array of pixels.
   *
   * @param pixels the array of pixels representing the image
   */
  public ImageBMP(IPixel[][] pixels) {
    super(pixels);
  }

  /**
   * A constructor that allows for the conversion of an image from another format to the current
   * object's format.
   *
   * @param image the image to be converted
   */
  public ImageBMP(ImageState image) {
    super(image);
  }

  @Override
  protected ImageState createImage(IPixel[][] pixels) {
    return new ImageBMP(pixels);
  }

  @Override
  public void save(String filePath) {
    BufferedImage img = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
    for (int r = 0; r < this.getHeight(); r++) {
      for (int c = 0; c < this.getWidth(); c++) {
        Color color = new Color(this.getPixelAt(r, c).getRed(), this.getPixelAt(r, c).getGreen(),
                this.getPixelAt(r, c).getBlue());
        img.setRGB(c, r, color.getRGB());
      }
    }
    try {
      ImageIO.write(img, "bmp", new File(filePath));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
