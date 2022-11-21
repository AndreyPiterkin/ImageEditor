package cs3500.imageprocessor.view;

import java.awt.image.BufferedImage;

import cs3500.imageprocessor.controller.Features;
import cs3500.imageprocessor.model.ImageState;

public interface ImageEditorGUIView extends ImageEditorView {

  void makeVisible();

  void addFeatures(Features features);

  void renderImage(BufferedImage image);

}
