import java.io.IOException;

import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.model.RGBAPixel;
import cs3500.imageprocessor.operations.PixelOperation;

public class ForwardingFilter implements PixelOperation {
  private final PixelOperation delegate;
  private final String name;
  private final Appendable log;

  public ForwardingFilter(PixelOperation delegate, String name, Appendable log) {
    this.delegate = delegate;
    this.name = name;
    this.log = log;
  }

  @Override
  public RGBAPixel apply(ImageState image, int r, int c) {
    try {
      log.append("apply + " + name + " " + r + " " + c + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Unable to write to log", e);
    }
    return delegate.apply(image, r, c);
  }
}
