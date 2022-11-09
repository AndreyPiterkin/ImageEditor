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
    BufferedImage img = preprocessForSave(BufferedImage.TYPE_INT_RGB, (IPixel p) ->
        new Color(p.getRed(), p.getGreen(), p.getBlue()));
    saveToDisk(img, "jpg", filePath);
  }
}
