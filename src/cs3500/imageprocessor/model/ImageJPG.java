package cs3500.imageprocessor.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageJPG extends AbstractBaseImage {
  /**
   * A constructor that allows for the creation of an image from an array of pixels.
   *
   * @param pixels the array of pixels representing the image
   */
  public ImageJPG(IPixel[][] pixels) {
    super(pixels);
  }

  public ImageJPG(ImageState image) {
    super(image);
  }

  @Override
  protected ImageState createImage(IPixel[][] pixels) {
    return new ImageJPG(pixels);
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
      ImageIO.write(img, "jpg", new File(filePath));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
