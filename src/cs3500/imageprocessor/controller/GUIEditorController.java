package cs3500.imageprocessor.controller;

import cs3500.imageprocessor.operations.PixelOperation;

public interface GUIEditorController extends ImageEditorController {

  void load(String path);

  void save(String path);

  void operate(PixelOperation operation);

}
