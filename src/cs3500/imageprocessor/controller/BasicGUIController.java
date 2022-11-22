package cs3500.imageprocessor.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;

import javax.imageio.ImageIO;

import cs3500.imageprocessor.model.BasicImage;
import cs3500.imageprocessor.model.ImageEditor;
import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.model.ImageUtil;
import cs3500.imageprocessor.operations.PixelOperation;
import cs3500.imageprocessor.view.ImageEditorGUIView;

public class BasicGUIController implements GUIEditorController {

  private final Features features;
  private final ImageEditor model;
  private final ImageEditorGUIView view;
  protected final Map<String, BiConsumer<ImageState, String>> writers;

  public BasicGUIController(ImageEditor model, ImageEditorGUIView view) {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
    this.features = new BasicProcessorFeatures(this);
    this.writers = new HashMap<>();
    this.initializeWriters();
  }

  protected void initializeWriters() {
    writers.put("ppm", (state, filename) -> {
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
        renderAndHandleException("Error writing to file: " + filename);
      }
    });
    writers.put("png", (state, filename) -> {
      try {
        ImageIO.write(state.asBufferedImage(BufferedImage.TYPE_INT_ARGB), "png",
                new File(filename));
      } catch (IOException e) {
        renderAndHandleException("Error writing to file: " + filename);
      }
    });
    writers.put("jpg", (state, filename) -> {
      try {
        ImageIO.write(state.asBufferedImage(BufferedImage.TYPE_INT_RGB), "jpg",
                new File(filename));
      } catch (IOException e) {
        renderAndHandleException("Error writing to file: " + filename);
      }
    });
    writers.put("bmp", (state, filename) -> {
      try {
        ImageIO.write(state.asBufferedImage(BufferedImage.TYPE_INT_RGB), "bmp",
                new File(filename));
      } catch (IOException e) {
        renderAndHandleException("Error writing to file: " + filename);
      }
    });
  }

  @Override
  public void load(String path) {
    if (path.substring(path.lastIndexOf(".") + 1).equals("ppm")) {
      this.model.addImage(new BasicImage(ImageUtil.readPPM(path)), "curr-image");
    } else {
      try {
        this.model.addImage(new BasicImage(ImageIO.read(new File(path))), "curr-image");
      } catch (IOException e) {
        renderAndHandleException("Can't read from the given path!");
      }
    }
    this.view.renderImage(this.model.getImage("curr-image").asBufferedImage(BufferedImage.TYPE_INT_ARGB));
  }

  private void renderAndHandleException(String message) {
    try {
      this.view.render(message);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void save(String path) {
    String extension = path.substring(path.lastIndexOf(".") + 1);
    if (!writers.containsKey(extension)) {
      throw new IllegalArgumentException("Invalid file extension!");
    }
    try {
      ImageState state = this.model.getImage("curr-image");
      writers.get(extension).accept(state, path);
    } catch (IllegalArgumentException e) {
      renderAndHandleException("No image loaded!");
    }
  }

  @Override
  public void operate(PixelOperation operation) {
    try {
      ImageState state = this.model.getImage("curr-image");
      this.model.addImage(state.apply(operation), "curr-image");
      this.view.renderImage(this.model.getImage("curr-image").asBufferedImage(BufferedImage.TYPE_INT_ARGB));
    } catch (IllegalArgumentException e) {
      renderAndHandleException("No image loaded!");
    }
  }

  /**
   * Starts the controller, allowing the user to interact with the model through the view.
   *
   * @throws IllegalStateException    if the controller can't read or write anything.
   * @throws IllegalArgumentException if the user enters an invalid input into the commands.
   */
  @Override
  public void start() {
    this.view.addFeatures(this.features);
    this.view.makeVisible();
  }
}
