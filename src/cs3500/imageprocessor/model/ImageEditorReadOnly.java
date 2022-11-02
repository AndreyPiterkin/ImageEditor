package cs3500.imageprocessor.model;

import java.util.List;

public interface ImageEditorReadOnly {

  List<String> getImageNames();

  ImageState getImage(String imageName);

}
