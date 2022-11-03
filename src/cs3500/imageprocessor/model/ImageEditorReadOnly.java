package cs3500.imageprocessor.model;

import java.util.List;

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
