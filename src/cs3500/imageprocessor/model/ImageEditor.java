package cs3500.imageprocessor.model;

public interface ImageEditor extends ImageEditorReadOnly {

  void importImage(String fileName, String imageName);

  void saveImageAs(ImageState image, String imageName);

  void removeImage(String imageName);

}

/**
 *
 *
 * ImageEditor e = ....
 * e.importImage("image1.jpg", "image1");
 *
 * ImageState i = e.getImage("image1");
 * ImageState i2 = i.apply((image, x, y) -> ...);
 * e.saveImageAs(i2, "image2");
 * i2.save("image2.jpg");
 *
 *
 */
