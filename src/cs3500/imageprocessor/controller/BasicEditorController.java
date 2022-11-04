package cs3500.imageprocessor.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

import cs3500.imageprocessor.model.ImageEditor;
import cs3500.imageprocessor.model.ImagePPM;
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
  private final Map<String, Function<Scanner, ImageRCToPixelTransformation>> commands;

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
   * Sets up all the commands that this controller is capable of executing.
   */
  private void setupCommands() {
    commands.put("brighten", (scanner) -> {
      return new BrightenPixel(scanner.nextInt());
    });
    commands.put("darken", (scanner) -> {
      return new DarkenPixel(scanner.nextInt());
    });
    commands.put("vertical-flip", (scanner) -> {
      return new FlipVertical();
    });
    commands.put("horizontal-flip", (scanner) -> {
      return new FlipHorizontal();
    });
    commands.put("value-component", (scanner) -> {
      return new VisualizeValue();
    });
    commands.put("red-component", (scanner) -> {
      return new GrayscaleRed();
    });
    commands.put("blue-component", (scanner) -> {
      return new GrayscaleBlue();
    });
    commands.put("green-component", (scanner) -> {
      return new GrayscaleGreen();
    });
    commands.put("luma-component", (scanner) -> {
      return new VisualizeLuma();
    });
    commands.put("intensity-component", (scanner) -> {
      return new VisualizeIntensity();
    });
  }

  /**
   * Helper method for saving an image.
   * @param scan the scanner to read from
   * @throws UnsupportedOperationException if the given image is not a PPM
   */
  private void saveImage(Scanner scan) {
    String path = scan.next();
    String name = scan.next();
    if (path.substring(path.lastIndexOf(".") + 1).equals("ppm")) {
      new ImagePPM(this.model.getImage(name)).save(path);
    } else {
      throw new UnsupportedOperationException("File types other than PPM not supported");
    }
  }

  /**
   * Helper method for loading an image.
   * @param scan the scanner to read from
   */
  private void loadImage(Scanner scan) {
    String path = scan.next();
    String name = scan.next();
    this.model.importImageFromDisk(path, name);
  }

  /**
   * Starts the controller, allowing the user to interact with the model through the view.
   *
   * @throws IllegalStateException if the controller can't read or write anything.
   */
  @Override
  public void start() {
    Scanner scan = new Scanner(this.in);
    try {
      while (scan.hasNext()) {
        String command = scan.next();
        // This switch statement has specific clauses for load and save, because they are different
        // from the rest of the commands, in that they don't perform a transformation on an image
        // they just read or save data to the disk
        switch (command) {
          case "load":
            this.loadImage(scan);
            break;
          case "save":
            this.saveImage(scan);
            break;
          default:
            if (this.commands.containsKey(command)) {

              ImageRCToPixelTransformation transformation = this.commands.get(command).apply(scan);
              this.model.applyFilterAndSave(scan.next(), scan.next(),
                      transformation);
            } else {
              try {
                this.view.render("Illegal argument");
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
              // This will later render to a view, but for now just throws an exception
              throw new IllegalArgumentException("Invalid command");

            }
            break;
        }
      }
    } catch (InputMismatchException e) {
      throw new IllegalArgumentException("Invalid input");
    }
  }
}
