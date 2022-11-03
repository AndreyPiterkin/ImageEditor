import cs3500.imageprocessor.model.BasicPPMImageEditor;
import cs3500.imageprocessor.model.ImageEditor;
import cs3500.imageprocessor.model.ImageEditorReadOnly;
import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.operations.toGrayscaleBlue;

public class Main {
  public static void main(String[] args) {
    ImageEditor editor = new BasicPPMImageEditor();
    editor.importImageFromDisk("/Users/andrey/Documents/Northeastern/CS3500/ImageProcessor/images/Koala.ppm", "koala");
    System.out.println(editor.getImageNames().toString());
    ImageEditorReadOnly readOnly = editor;
    System.out.println(readOnly.getImage("koala").getHeight());
    System.out.println(readOnly.getImage("koala").getWidth());
    ImageState image = readOnly.getImage("koala");
    ImageState image2 = image.apply(new toGrayscaleBlue());
    editor.addImage(image2, "koala2");
    System.out.println(editor.getImageNames().toString());
    image2.save("/Users/andrey/Documents/Northeastern/CS3500/ImageProcessor/images/Koala2.ppm");
  }
}
