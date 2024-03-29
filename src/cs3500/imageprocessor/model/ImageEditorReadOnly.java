package cs3500.imageprocessor.model;

import java.util.List;

/**
 * Represents the immutable state of the image editor, to separate mutation functionality
 * for the controller from the read only functionality of the view.
 */
public interface ImageEditorReadOnly {

  /**
   * Gets the names of all the images open in the image editor.
   * @return the names of all  the images open in the image editor
   */
  List<String> getImageNames();

  /**
   * Gets the image with the given name.
   * @param imageName the name of the image to get
   * @return the image with the given name
   */
  ImageState getImage(String imageName);

}
