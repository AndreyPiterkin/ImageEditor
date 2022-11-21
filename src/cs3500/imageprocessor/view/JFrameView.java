package cs3500.imageprocessor.view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import cs3500.imageprocessor.controller.Features;

public class JFrameView extends JFrame implements ImageEditorGUIView {

  private JPanel canvas;

  private List<JButton> buttons;

  public JFrameView() {
    super();
    buttons = new ArrayList<JButton>();

    setTitle("Image Processor");
    setSize(500, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    canvas = new JPanel();
    canvas.setLayout(new BoxLayout(canvas, BoxLayout.Y_AXIS));
    this.add(new JScrollPane(canvas));

    JPanel controls = new JPanel();
    JButton load = new JButton("Load");
    JButton save = new JButton("Save");
    buttons.add(load);
    buttons.add(save);
    controls.add(load);
    controls.add(save);
    controls.setBorder(BorderFactory.createTitledBorder("Controls"));
    controls.setLayout(new GridLayout(3, 5));
    this.add(controls, BorderLayout.SOUTH);
  }

  private void createButtons() {
    JButton blur = new JButton("Blur");
    JButton sharpen = new JButton("Sharpen");
    JButton load = new JButton("Load");
    JButton save = new JButton("Save");
    JButton grayscale = new JButton("Grayscale");
    JButton sepia = new JButton("Sepia");
    JButton brighten = new JButton("Brighten");
    JButton darken = new JButton("Darken");
    JButton blueComponent = new JButton("Blue Component");
    JButton greenComponent = new JButton("Green Component");
    JButton redComponent = new JButton("Red Component");
    JButton intensity = new JButton("Intensity");
    JButton value = new JButton("Value");
    JButton luma = new JButton("luma");


  }



  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void addFeatures(Features features) {
    // TODO Auto-generated method stub

  }

  @Override
  public void renderImage(BufferedImage image) {
    // TODO Auto-generated method stub

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
