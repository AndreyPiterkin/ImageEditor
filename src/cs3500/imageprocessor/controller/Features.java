package cs3500.imageprocessor.controller;

public interface Features {

  void load(String path);

  void save(String path);

  void blur();

  void sharpen();

  void grayscale();

  void sepia();

  void brighten();

  void darken();

  void flipHorizontal();

  void flipVertical();

  void blueComponent();

  void greenComponent();

  void redComponent();

  void intensityComponent();

  void lumaComponent();

  void valueComponent();

}
