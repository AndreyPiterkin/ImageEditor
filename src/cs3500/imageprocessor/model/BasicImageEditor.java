package cs3500.imageprocessor.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A class representing an image editor with the functionality of handling images in the .ppm
 * format.
 */
public class BasicImageEditor implements ImageEditor {

  private final Map<String, ImageState> images;

  public BasicImageEditor(Map<String, ImageState> images) {
    this.images = new HashMap<>(Objects.requireNonNull(images));
  }

  public BasicImageEditor() {
    this(new HashMap<String, ImageState>());
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
