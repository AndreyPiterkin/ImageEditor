package cs3500.imageprocessor.view;

import java.awt.image.BufferedImage;

import cs3500.imageprocessor.controller.Features;

/**
 * Represents a view for the image editor. This interface supports making the view visible,
 * setting up feature listeners, and displaying images.
 */
public interface ImageEditorGUIView extends ImageEditorView {

  void makeVisible();

  void addFeatures(Features features);

  void renderImage(BufferedImage image);

}
