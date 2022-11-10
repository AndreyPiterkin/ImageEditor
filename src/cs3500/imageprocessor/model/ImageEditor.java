package cs3500.imageprocessor.model;


/**
 * An interface that represents an image editor. This editor can show the names of the images it
 * has saved, get any image it has saved, load images from the user's local disk, and add and
 * remove images from the editor.
 */
public interface ImageEditor extends ImageEditorReadOnly {

  /**
   * Adds the given image to the editor and names it the given name.
   *
   * @param image     the image to add
   * @param imageName the name to give the image
   */
  void addImage(ImageState image, String imageName);

  /**
   * Removes the image with the given name from the editor.
   *
   * @param imageName the name of the image to remove
   */
  void removeImage(String imageName);


}

