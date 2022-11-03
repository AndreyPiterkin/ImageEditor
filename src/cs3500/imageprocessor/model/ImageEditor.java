package cs3500.imageprocessor.model;

public interface ImageEditor extends ImageEditorReadOnly {

  /**
   * Loads an image at the given file path into the editor and names it the given name.
   * In order to support different kinds of image formats, the method will use the file extension
   * to determine which kind of image to create.
   * @param fileName the filepath from where to load the image
   * @param imageName the name to give the image
   * @throws UnsupportedOperationException if the file extension is not supported, or if the file cannot
   * be read.
   */
  void importImageFromDisk(String fileName, String imageName);

  /**
   * Adds the given image to the editor and names it the given name.
   * @param image the image to add
   * @param imageName the name to give the image
   */
  void addImage(ImageState image, String imageName);

  /**
   * Removes the image with the given name from the editor.
   * @param imageName the name of the image to remove
   */
  void removeImage(String imageName);

}

