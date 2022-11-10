package cs3500.imageprocessor.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Function;

import javax.imageio.ImageIO;

import cs3500.imageprocessor.model.BasicImage;
import cs3500.imageprocessor.model.ImageEditor;
import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.model.ImageUtil;
import cs3500.imageprocessor.operations.BrightenPixel;
import cs3500.imageprocessor.operations.DarkenPixel;
import cs3500.imageprocessor.operations.FlipHorizontal;
import cs3500.imageprocessor.operations.FlipVertical;
import cs3500.imageprocessor.operations.GaussianBlur;
import cs3500.imageprocessor.operations.Grayscale;
import cs3500.imageprocessor.operations.GrayscaleBlue;
import cs3500.imageprocessor.operations.GrayscaleGreen;
import cs3500.imageprocessor.operations.GrayscaleRed;
import cs3500.imageprocessor.operations.PixelOperation;
import cs3500.imageprocessor.operations.SepiaTone;
import cs3500.imageprocessor.operations.Sharpen;
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
  protected final Map<String, Function<Scanner, PixelOperation>> commands;
  protected final Map<String, BiConsumer<ImageState, String>> imageWriters;

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
    this.imageWriters = new HashMap<>();
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
  protected void setupCommands() {
    commands.put("brighten", (scanner) -> new BrightenPixel(scanner.nextInt()));
    commands.put("darken", (scanner) -> new DarkenPixel(scanner.nextInt()));
    commands.put("vertical-flip", (scanner) -> new FlipVertical());
    commands.put("horizontal-flip", (scanner) -> new FlipHorizontal());
    commands.put("value-component", (scanner) -> new VisualizeValue());
    commands.put("red-component", (scanner) -> new GrayscaleRed());
    commands.put("blue-component", (scanner) -> new GrayscaleBlue());
    commands.put("green-component", (scanner) -> new GrayscaleGreen());
    commands.put("luma-component", (scanner) -> new VisualizeLuma());
    commands.put("intensity-component", (scanner) -> new VisualizeIntensity());
    commands.put("sharpen", (scanner) -> new Sharpen());
    commands.put("blur", (scanner) -> new GaussianBlur());
    commands.put("grayscale", (scanner) -> new Grayscale());
    commands.put("sepia", (scanner) -> new SepiaTone());

    imageWriters.put("ppm", (state, filename) -> {
      try {
        FileWriter writer = new FileWriter(filename);
        writer.write("P3\n");
        writer.write(state.getWidth() + " " + state.getHeight() + "\n");
        writer.write("255\n");
        for (int r = 0; r < state.getHeight(); r++) {
          for (int c = 0; c < state.getWidth(); c++) {
            writer.write(state.getPixelAt(r, c).getRed() + " "
                    + state.getPixelAt(r, c).getGreen() + " "
                    + state.getPixelAt(r, c).getBlue() + "\n");
          }
        }
        writer.close();
      } catch (IOException e) {
        throw new IllegalStateException("Can't write to the given path!");
      }
    });
    imageWriters.put("png", (state, filename) -> {
      try {
        ImageIO.write(state.asBufferedImage(BufferedImage.TYPE_INT_ARGB), "png",
            new File(filename));
      } catch (IOException e) {
        throw new IllegalStateException("Can't write to the given path!");
      }
    });
    imageWriters.put("jpg", (state, filename) -> {
      try {
        ImageIO.write(state.asBufferedImage(BufferedImage.TYPE_INT_RGB), "jpg",
                new File(filename));
      } catch (IOException e) {
        throw new IllegalStateException("Can't write to the given path!");
      }
    });
    imageWriters.put("bmp", (state, filename) -> {
      try {
        ImageIO.write(state.asBufferedImage(BufferedImage.TYPE_INT_RGB), "bmp",
                new File(filename));
      } catch (IOException e) {
        throw new IllegalStateException("Can't write to the given path!");
      }
    });

  }

  /**
   * Helper method for loading an image.
   * @param scan the scanner to read from
   * @throws IllegalStateException if the controller can't read the image
   */
  private void loadImage(Scanner scan) {
    String path = scan.next();
    String name = scan.next();
    if (path.substring(path.lastIndexOf(".")+1).equals("ppm")) {
      this.model.addImage(new BasicImage(ImageUtil.readPPM(path)), name);
    } else {
      try {
        this.model.addImage(new BasicImage(ImageIO.read(new File(path))), name);
      } catch (IOException e) {
        throw new IllegalStateException("Could not load image.");
      }
    }
  }

  /**
   * Helper method for saving an image.
   * @param scan the scanner to read from
   */
  private void saveImage(Scanner scan) {
    String name = scan.next();
    String path = scan.next();
    ImageState image = this.model.getImage(name);

    String extension = path.substring(path.lastIndexOf(".")+1);
    if (imageWriters.containsKey(extension)) {
      imageWriters.get(extension).accept(image, path);
    } else {
      throw new UnsupportedOperationException("Can't encode the given extension!");
    }
  }

  private void applyOperation(String command, Scanner scan) {
    PixelOperation operation;
    if (this.commands.containsKey(command)) {
      operation = this.commands.get(command).apply(scan);
    } else {
      throw new IllegalArgumentException("Invalid operation: " + command);
    }

    String name = scan.next();
    String newName = scan.next();
    ImageState image = this.model.getImage(name);
    this.model.addImage(image.apply(operation), newName);
  }

  /**
   * Starts the controller, allowing the user to interact with the model through the view.
   *
   * @throws IllegalStateException if the controller can't read or write anything.
   * @throws IllegalArgumentException if the user enters an invalid input into the commands.
   */
  @Override
  public void start() {
    Scanner scan = new Scanner(this.in);
    try {
      while (scan.hasNext()) {
        String command = scan.next();
        switch (command) {
          case "load":
            this.loadImage(scan);
            break;
          case "save":
            this.saveImage(scan);
            break;
          default:
            this.applyOperation(command, scan);
            break;
        }
      }
    } catch (InputMismatchException e) {
      try {
        this.view.render("Invalid input.");
      } catch (IOException ex) {
        throw new IllegalStateException("Can't write to view");
      }
      throw new IllegalArgumentException("Invalid input");
    } catch (NoSuchElementException e) {
      throw new IllegalArgumentException("Not enough arguments");
    }
  }
}
