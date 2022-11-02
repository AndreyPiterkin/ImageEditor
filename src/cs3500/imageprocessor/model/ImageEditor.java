package cs3500.imageprocessor.model;

public interface ImageEditor extends ImageEditorReadOnly {

  void importImageFromDisk(String fileName, String imageName);

  void addImage(ImageState image, String imageName);

  void removeImage(String imageName);

}

