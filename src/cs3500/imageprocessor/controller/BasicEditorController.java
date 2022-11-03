package cs3500.imageprocessor.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.BiConsumer;

import cs3500.imageprocessor.model.ImageEditor;
import cs3500.imageprocessor.model.ImagePPM;
import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.operations.BrightenPixel;
import cs3500.imageprocessor.operations.DarkenPixel;
import cs3500.imageprocessor.operations.FlipHorizontal;
import cs3500.imageprocessor.operations.FlipVertical;
import cs3500.imageprocessor.operations.GrayscaleBlue;
import cs3500.imageprocessor.operations.GrayscaleGreen;
import cs3500.imageprocessor.operations.GrayscaleRed;
import cs3500.imageprocessor.operations.ImageRCToPixelTransformation;
import cs3500.imageprocessor.operations.VisualizeIntensity;
import cs3500.imageprocessor.operations.VisualizeLuma;
import cs3500.imageprocessor.operations.VisualizeValue;
import cs3500.imageprocessor.view.ImageEditorView;

/**
 * Represents a basic ppm editor controller that allows users to interact with the model through a
 * Readable and output to a view.
 */
public class BasicEditorController implements ImageEditorController {

  private final ImageEditor model;
  private final ImageEditorView view;
  private final Readable in;
  private final Map<String, BiConsumer<Scanner, ImageEditor>> commands;

  /**
   * Constructs a BasicEditorController object with the model and view to control, as well
   * as where to get commands from.
   * @param model the model to control
   * @param view the view to control
   * @param in the readable to read from
   * @throws NullPointerException PURPOSEFULLY if any of the given parameters are null.
   */
  public BasicEditorController(ImageEditor model, Readable in, ImageEditorView view) {
    Objects.requireNonNull(model);
    Objects.requireNonNull(in);
    Objects.requireNonNull(view);
    this.model = model;
    this.in = in;
    this.view = view;
    this.commands = new HashMap<>();
    this.setupCommands();
  }

  /**
   * Constructs a BasicEditorController object with the model and view to control, and from
   * System.in.
   * @param model the model to control
   * @param view the view to control
   * @throws NullPointerException PURPOSEFULLY if any of the given parameters are null.
   */
  public BasicEditorController(ImageEditor model, ImageEditorView view) {
    this(model, new InputStreamReader(System.in), view);
  }

  /**
   * Performs the given operation and saves the image with the given name.
   * This is a useful private helper method for commands, but isn't necessarily an interface method
   * because we don't always want to save an image we modify to the map (like in the case of
   * save or load commands).
   * @param name the name of the image to retrieve
   * @param newName the name of the new image
   * @param operation the operation to perform
   */
  private void performAndSave(String name, String newName, ImageRCToPixelTransformation operation) {
    ImageState image = this.model.getImage(name);
    this.model.addImage(image.apply(operation), newName);
  }

  /**
   * Sets up all the commands that this controller is capable of executing.
   */
  private void setupCommands() {
    commands.put("load", (scanner, imageEditor) -> {
      String path = scanner.next();
      String name = scanner.next();
      imageEditor.importImageFromDisk(path, name);
    });
    commands.put("save", (scanner, imageEditor) -> {
      String path = scanner.next();
      String name = scanner.next();
      if (path.substring(path.lastIndexOf(".") + 1).equals("ppm")) {
        new ImagePPM(imageEditor.getImage(name)).save(path);
      } else {
        throw new UnsupportedOperationException("File types other than PPM not supported");
      }
    });
    commands.put("brighten", (scanner, imageEditor) -> {
      int amount = scanner.nextInt();
      this.performAndSave(scanner.next(), scanner.next(), new BrightenPixel(amount));
    });
    commands.put("darken", (scanner, imageEditor) -> {
      int amount = scanner.nextInt();
      this.performAndSave(scanner.next(), scanner.next(), new DarkenPixel(amount));
    });
    commands.put("vertical-flip", (scanner, imageEditor) -> {
      this.performAndSave(scanner.next(), scanner.next(), new FlipVertical());
    });
    commands.put("horizontal-flip", (scanner, imageEditor) -> {
      this.performAndSave(scanner.next(), scanner.next(), new FlipHorizontal());
    });
    commands.put("value-component", (scanner, imageEditor) -> {
      this.performAndSave(scanner.next(), scanner.next(), new VisualizeValue());
    });
    commands.put("red-component", (scanner, imageEditor) -> {
      this.performAndSave(scanner.next(), scanner.next(), new GrayscaleRed());
    });
    commands.put("blue-component", (scanner, imageEditor) -> {
      this.performAndSave(scanner.next(), scanner.next(), new GrayscaleBlue());
    });
    commands.put("green-component", (scanner, imageEditor) -> {
      this.performAndSave(scanner.next(), scanner.next(), new GrayscaleGreen());
    });
    commands.put("luma-component", (scanner, imageEditor) -> {
      this.performAndSave(scanner.next(), scanner.next(), new VisualizeLuma());
    });
    commands.put("intensity-component", (scanner, imageEditor) -> {
      this.performAndSave(scanner.next(), scanner.next(), new VisualizeIntensity());
    });
  }

  /**
   * Starts the controller, allowing the user to interact with the model through the view.
   *
   * @throws IllegalStateException if the controller can't read or write anything.
   */
  @Override
  public void start() {
    Scanner scan = new Scanner(this.in);
    while (scan.hasNext()) {
      String command = scan.next();
      if (this.commands.containsKey(command)) {
        this.commands.get(command).accept(scan, this.model);
      }
      else {
        try {
          this.view.render("Invalid command");
        } catch (IOException e) {
          throw new IllegalStateException(e);
        }
      }
    }
  }
}
