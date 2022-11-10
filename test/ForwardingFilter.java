import java.io.IOException;

import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.model.RGBAPixel;
import cs3500.imageprocessor.operations.PixelOperation;

/**
 * A filter that logs the name of the operation that is being executed and delegates
 * to the given filter.
 */
public class ForwardingFilter implements PixelOperation {
  private final PixelOperation delegate;
  private final String name;
  private final Appendable log;

  /**
   * Constructs a ForwardingFilter that logs the given name and delegates to the given filter.
   *
   * @param delegate the filter to delegate to
   * @param name     the name of the operation
   * @param log      the log to put to
   */
  public ForwardingFilter(PixelOperation delegate, String name, Appendable log) {
    this.delegate = delegate;
    this.name = name;
    this.log = log;
  }

  /**
   * Applies the forwarding operation to the given pixel, and logs that a change has been made.
   * @param image the image to transform
   * @param r     the row coordinate of the pixel
   * @param c     the column coordinate of the pixel
   * @return the transformed pixel
   */
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
