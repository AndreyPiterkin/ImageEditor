package cs3500.imageprocessor.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImagePNG extends AbstractBaseImage {
  /**
   * A constructor that allows for the creation of an image from an array of pixels.
   *
   * @param pixels the array of pixels representing the image
   */
  public ImagePNG(IPixel[][] pixels) {
    super(pixels);
  }

  /**
   * A constructor that allows for the conversion of an image from another format to the current
   * object's format.
   *
   * @param image the image to be converted
   */
  public ImagePNG(ImageState image) {
    super(image);
  }

  @Override
  protected ImageState createImage(IPixel[][] pixels) {
    return new ImagePNG(pixels);
  }

  @Override
  public void save(String filePath) {
    BufferedImage img = preprocessForSave(BufferedImage.TYPE_INT_ARGB, (IPixel p) ->
        new Color(p.getRed(), p.getGreen(), p.getBlue(), p.getAlpha()));
    saveToDisk(img, "png", filePath);
  }
}
