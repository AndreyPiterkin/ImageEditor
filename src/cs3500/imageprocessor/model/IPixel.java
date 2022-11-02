package cs3500.imageprocessor.model;

import java.util.function.Function;

public interface IPixel {

  int[] getAsRGB();

  int compute(Function<IPixel, Integer> f);

}
