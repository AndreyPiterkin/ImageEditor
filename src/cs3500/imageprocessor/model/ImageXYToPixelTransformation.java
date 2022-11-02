package cs3500.imageprocessor.model;

public interface ImageXYToPixelTransformation {

    IPixel apply(ImageState t, Integer t1, Integer t2);

}
