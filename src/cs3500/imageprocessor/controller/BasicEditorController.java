package cs3500.imageprocessor.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import cs3500.imageprocessor.model.ImageEditor;
import cs3500.imageprocessor.model.ImagePPM;
import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.operations.BrightenImage;
import cs3500.imageprocessor.operations.DarkenImage;
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
      String name = scanner.next();
      String newName = scanner.next();
      imageEditor.addImage(imageEditor.getImage(name).apply(new BrightenImage(amount)), newName);
    });
    commands.put("darken", (scanner, imageEditor) -> {
      int amount = scanner.nextInt();
      String name = scanner.next();
      String newName = scanner.next();
      imageEditor.addImage(imageEditor.getImage(name).apply(new DarkenImage(amount)), newName);
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
