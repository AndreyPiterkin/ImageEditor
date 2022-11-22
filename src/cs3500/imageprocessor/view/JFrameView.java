package cs3500.imageprocessor.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileNameExtensionFilter;

import cs3500.imageprocessor.controller.Features;
import cs3500.imageprocessor.model.ImageUtil;

/**
 * Represents a GUI view for the image editor using Java Swing.
 */
public class JFrameView extends JFrame implements ImageEditorGUIView {

  private JPanel imageCanvas;
  private Map<JButton, Consumer<Features>> buttons;

  /**
   * Constructs this image processor view with a button grid, save and load, and panels
   * for the image and the histogram.
   */
  public JFrameView() {
    super();
    buttons = new HashMap<>();

    setTitle("Image Processor");
    setSize(1000, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel canvas = new JPanel();
    canvas.setLayout(new BoxLayout(canvas, BoxLayout.Y_AXIS));
    this.add(new JScrollPane(canvas));

    // Button area
    createButtons();
    JPanel controls = new JPanel();
    buttons.forEach((button, consumer) -> controls.add(button));
    controls.setBorder(BorderFactory.createTitledBorder("Controls"));
    controls.setLayout(new GridLayout(3, 5));
    this.add(controls, BorderLayout.NORTH);

    // Save and load area
    JPanel saveControls = new JPanel();
    JButton save = new JButton("Save");
    JButton load = new JButton("Load");
    this.buttons.put(save, f -> {
      String name = this.chooseFromFileSystem(true);
      if (name != null) {
        f.save(name);
      }
    });
    this.buttons.put(load, f -> {
      String name = this.chooseFromFileSystem(false);
      if (name != null) {
        f.load(name);
      }
    });
    saveControls.add(save);
    saveControls.add(load);
    saveControls.setLayout(new GridLayout(1, 2));
    this.add(saveControls, BorderLayout.SOUTH);

    // Image area
    imageCanvas = new JPanel();
    imageCanvas.setBorder(BorderFactory.createTitledBorder("Image Display"));
    imageCanvas.setLayout(new GridLayout(1, 2, 10, 10));
    this.add(imageCanvas, BorderLayout.CENTER);
  }

  /**
   * Creates the buttons for the GUI with all of the callbacks and names.
   */
  private void createButtons() {
    createButton("Blur", Features::blur);
    createButton("Sharpen", Features::sharpen);
    createButton("Grayscale", Features::grayscale);
    createButton("Sepia", Features::sepia);
    createButton("Brighten", f -> {
      String input = JOptionPane.showInputDialog("Enter a value to brighten by");
      if (input != null) {
        try {
          f.brighten(Integer.parseInt(input));
        } catch (NumberFormatException e) {
          JOptionPane.showMessageDialog(this, "Not a number!");
        }
      }
    });
    createButton("Darken", f -> {
      String input = JOptionPane.showInputDialog("Enter a value to brighten by");
      if (input != null) {
        try {
          f.darken(Integer.parseInt(input));
        } catch (NumberFormatException e) {
          JOptionPane.showMessageDialog(this, "Not a number!");
        }
      }
    });
    createButton("Blue Component", Features::blueComponent);
    createButton("Green Component", Features::greenComponent);
    createButton("Red Component", Features::redComponent);
    createButton("Intensity", Features::intensityComponent);
    createButton("Value", Features::valueComponent);
    createButton("Luma", Features::lumaComponent);
    createButton("Flip Horizontal", Features::flipHorizontal);
    createButton("Flip Vertical", Features::flipVertical);
  }

  /**
   * Creates a button with the given name and callback.
   * @param name the name of the button
   * @param actionEvent the callback for the button
   */
  private void createButton(String name, Consumer<Features> actionEvent) {
    JButton button = new JButton(name);
    this.buttons.put(button, actionEvent);
  }

  /**
   * Uses the file chooser to get a file name from the user (for loading or saving).
   * @param isSaveAction whether we should save or load
   * @return the file name
   */
  private String chooseFromFileSystem(boolean isSaveAction) {
    final JFileChooser chooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG, BMP, PNG, PPM images", "jpeg", "jpg", "png", "ppm", "bmp");
    chooser.setFileFilter(filter);
    int returnVal = isSaveAction ? chooser.showSaveDialog(this) :
        chooser.showOpenDialog(this) ;
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File f = chooser.getSelectedFile();
      return f.getAbsolutePath();
    } else {
      return null;
    }
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void addFeatures(Features features) {
    buttons.forEach((button, consumer) -> button.addActionListener(e -> consumer.accept(features)));
  }

  @Override
  public void renderImage(BufferedImage image) {
    imageCanvas.removeAll();
    JScrollPane imagePanel = new JScrollPane(new JLabel(new ImageIcon(image)));
    imageCanvas.add(imagePanel);
    imageCanvas.add(drawHistogram(ImageUtil.computeFrequencyHistogram(image)));
    imageCanvas.revalidate();
    imageCanvas.repaint();
  }

  /**
   * Draws the histogram for the given image.
   * @param histogram the histogram to draw
   * @return the panel with the histogram
   */
  private JPanel drawHistogram(int[][] histogram) {
    JPanel p = new JPanel() {
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        AffineTransform originalTransform = g2d.getTransform();

        g2d.translate(20, this.getSize().getHeight() - 50);
        g2d.scale(1, -1);

        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.BLACK};
        for (int i = 0; i < histogram.length; i++) {
          for (int j = 0; j < histogram[i].length - 1; j++) {
            g2d.setColor(colors[i]);
            g2d.setStroke(new BasicStroke(1.5f));
            g2d.drawLine(j, (int)(histogram[i][j]), (j + 1), (int)(histogram[i][j + 1]));
            g2d.setColor(Color.BLACK);
          }
        }

        g2d.setTransform(originalTransform);
      }
    };
    p.setBorder(BorderFactory.createTitledBorder("Histogram"));
    p.setPreferredSize(new Dimension(250, 250));
    return p;
  }

  /**
   * Renders the given message to the view.
   *
   * @param message the message to render
   * @throws IOException if the view can't write to the output
   */
  @Override
  public void render(String message) throws IOException {
    JOptionPane.showMessageDialog(this, message, "ERROR", JOptionPane.ERROR_MESSAGE);
  }
}
