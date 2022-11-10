package cs3500.imageprocessor.controller;

/**
 * Represents a controller for the image editor interface, allowing users to interact with the model
 * through a Readable and output to a view.
 */
public interface ImageEditorController {

  /**
   * Starts the controller, allowing the user to interact with the model through the view.
   * @throws IllegalStateException if the controller can't read or write anything.
   * @throws IllegalArgumentException if the user enters an invalid input into the commands.
   */
  void start();
}
