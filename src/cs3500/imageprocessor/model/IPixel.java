package cs3500.imageprocessor.model;

import java.util.function.Function;

public interface IPixel {

  /**
   * Gets the red channel of the pixel. We guarantee that every pixel can compute its own red
   * channel, no matter the representation, and return it as an integer between 0 and 255.
   * @return the red channel of the pixel
   */
  int getRed();

  /**
   * Gets the green channel of the pixel. We guarantee that every pixel can compute its own green
   * channel, no matter the representation, and return it as an integer between 0 and 255.
   * @return the red channel of the pixel
   */
  int getGreen();

  /**
   * Gets the blue channel of the pixel. We guarantee that every pixel can compute its own blue
   * channel, no matter the representation, and return it as an integer between 0 and 255.
   * @return the blue channel of the pixel
   */
  int getBlue();

  /**
   * Gets the alpha channel of the pixel. We guarantee that every pixel can compute its own alpha
   * channel, though pixels implementations without knowledge of the alpha channel will always
   * return 100% alpha. We also guarantee that the alpha channel will be returned as an integer from
   * 0 to 99.
   * @return the alpha channel of the pixel
   */
  int getAlpha();

}
