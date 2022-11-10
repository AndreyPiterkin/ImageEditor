import java.io.IOException;
import java.util.List;

import cs3500.imageprocessor.model.ImageEditor;
import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.operations.PixelOperation;

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
   * Loads an image at the given file path into the editor and names it the given name.
   * In order to support different kinds of image formats, the method will use the file extension
   * to determine which kind of image to create.
   *
   * @param fileName  the filepath from where to load the image
   * @param imageName the name to give the image
   * @throws UnsupportedOperationException if the file extension is not supported, or if the
   *                                       file cannot be read.
   */
  @Override
  public void importImageFromDisk(String fileName, String imageName) {
    try {
      this.log.append("import from disk. file path: " + fileName + " name: " + imageName + "\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    this.model.importImageFromDisk(fileName, imageName);
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
      this.log.append("add image. image: " + image + " name: " + imageName + "\n");
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
   * Performs the given operation on the image with the given name
   * and saves the image with the given.
   *
   * @param name      the name of the image to retrieve
   * @param newName   the name of the new image
   * @param operation the operation to perform
   */
  @Override
  public void applyFilterAndSave(String name, String newName,
                                 PixelOperation operation) {
    try {
      this.log.append("apply filter and save. name: " + name + " new name: " + newName + "\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    this.model.applyFilterAndSave(name, newName, operation);
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
