package cs3500.imageprocessor.controller;

import cs3500.imageprocessor.operations.PixelOperation;

/**
 * Represents a GUI based controller for the Image Processing program, and supports loading,
 * saving, and operating on images with callbacks from the view.
 */
public interface GUIEditorController extends ImageEditorController {

  /**
   * Loads the image at the given path using the controller into the model as the current image.
   * @param path the path to the image to load
   */
  void load(String path);

  /**
   * Saves the current image in the model to the given path.
   * @param path the path to save the image to
   */
  void save(String path);

  /**
   * Applies the given operation to the current image in the model, and updates the current image
   * in the model to the newly generated image.
   * @param operation the operation to apply
   */
  void operate(PixelOperation operation);

}
