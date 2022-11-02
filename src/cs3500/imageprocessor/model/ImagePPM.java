package cs3500.imageprocessor.model;

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
    // TODO
  }
}
