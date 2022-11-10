import java.io.IOException;
import java.util.List;

import cs3500.imageprocessor.model.ImageEditor;
import cs3500.imageprocessor.model.ImageState;

/**
 * Represents a mock for the editor model which wraps a real model and delegates, but also logs
 * all the calls to the model, for purposes of testing the controller.
 */
public class MockEditorModel implements ImageEditor {

  private final ImageEditor model;
  private final Appendable log;

  /**
   * Constructs a MockEditorModel object with the given model to wrap and the given log to
   * append to.
   *
   * @param model the model to wrap
   * @param log   the log to append to
   */
  public MockEditorModel(ImageEditor model, Appendable log) {
    this.model = model;
    this.log = log;
  }

  /**
   * Adds the given image to the editor and names it the given name.
   *
   * @param image     the image to add
   * @param imageName the name to give the image
   */
  @Override
  public void addImage(ImageState image, String imageName) {
    try {
      this.log.append("add image. image name: " + imageName + "\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    this.model.addImage(image, imageName);
  }

  /**
   * Removes the image with the given name from the editor.
   *
   * @param imageName the name of the image to remove
   */
  @Override
  public void removeImage(String imageName) {
    try {
      this.log.append("remove image. name: " + imageName + "\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    this.model.removeImage(imageName);
  }

  /**
   * Gets the names of all the images open in the image editor.
   *
   * @return the names of all  the images open in the image editor
   */
  @Override
  public List<String> getImageNames() {
    try {
      this.log.append("get image names\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return this.model.getImageNames();
  }

  /**
   * Gets the image with the given name.
   *
   * @param imageName the name of the image to get
   * @return the image with the given name
   */
  @Override
  public ImageState getImage(String imageName) {
    try {
      this.log.append("get image. name: " + imageName + "\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return this.model.getImage(imageName);
  }
}
