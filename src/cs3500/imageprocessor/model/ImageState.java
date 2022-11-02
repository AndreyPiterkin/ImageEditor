package cs3500.imageprocessor.model;

import java.util.function.Function;

public interface ImageState {

  int getWidth();

  int getHeight();

  IPixel getPixelAt(int x, int y);

  ImageState apply(ImageToPixelTransformation<ImageState, Integer, Integer, IPixel> f);

  ImageState apply(Function<ImageState, ImageState> f);

  void save(String fileName);

}
