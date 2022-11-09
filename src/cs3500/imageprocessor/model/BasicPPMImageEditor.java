package cs3500.imageprocessor.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cs3500.imageprocessor.operations.ImageRCToPixelTransformation;

/**
 * A class representing an image editor with the functionality of handling images in the .ppm
 * format.
 */
public class BasicPPMImageEditor implements ImageEditor {

  private final Map<String, ImageState> images;

  public BasicPPMImageEditor(Map<String, ImageState> images) {
    this.images = new HashMap<>(Objects.requireNonNull(images));
  }

  public BasicPPMImageEditor() {
    this(new HashMap<String, ImageState>());
  }

  /**
   * Loads an image at the given file path into the editor and names it the given name.
   * In order to support different kinds of image formats, the method will use the file extension
   * to determine which kind of image to create.
   *
   * @param fileName  the filepath from where to load the image
   * @param imageName the name to give the image
   * @throws UnsupportedOperationException if the file extension is not supported, or if the file
   *                                       cannot be read.
   */
  @Override
  public void importImageFromDisk(String fileName, String imageName) {
    if (fileName.substring(fileName.lastIndexOf(".") + 1).equals("ppm")) {
      this.images.put(imageName, new ImagePPM(ImageUtil.readPPM(fileName)));
    } else {
      throw new UnsupportedOperationException("File types other than PPM not supported");
    }
  }

  /**
   * Adds the given image to the editor and names it the given name.
   *
   * @param image     the image to add
   * @param imageName the name to give the image
   */
  @Override
  public void addImage(ImageState image, String imageName) {
    Objects.requireNonNull(image);
    Objects.requireNonNull(imageName);
    images.put(imageName, image);
  }

  /**
   * Removes the image with the given name from the editor.
   *
   * @param imageName the name of the image to remove
   */
  @Override
  public void removeImage(String imageName) {
    Objects.requireNonNull(imageName);
    images.remove(imageName);
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
       ImageRCToPixelTransformation operation) {
    Objects.requireNonNull(name);
    Objects.requireNonNull(newName);
    Objects.requireNonNull(operation);
    ImageState image = this.getImage(name);
    ImageState newImage = image.apply(operation);
    images.put(newName, newImage);
  }

  /**
   * Saves the image with the given name to the given file path.
   *
   * @param name     the name of the image to save
   * @param fileName the file path to save the image to
   */
  @Override
  public void saveImageAs(String name, String fileName) {
    Objects.requireNonNull(name);
    Objects.requireNonNull(fileName);
    ImageState image = this.getImage(name);
    if (fileName.substring(fileName.lastIndexOf(".") + 1).equals("ppm")) {
      new ImagePPM(image).save(fileName);
    } else {
      throw new UnsupportedOperationException("File types other than PPM not supported");
    }
  }

  /**
   * Gets the names of all the images open in the image editor.
   *
   * @return the names of all  the images open in the image editor
   */
  @Override
  public List<String> getImageNames() {
    return new ArrayList<>(this.images.keySet());
  }

  /**
   * Gets the image with the given name.
   *
   * @param imageName the name of the image to get
   * @return the image with the given name
   * @throws IllegalArgumentException if the image name isn't in the editor
   */
  @Override
  public ImageState getImage(String imageName) {
    if (!images.containsKey(imageName)) {
      throw new IllegalArgumentException("Image name not in editor");
    }
    return this.images.get(imageName);
  }
}
