package cs3500.imageprocessor.model;

public interface ImageToPixelTransformation <T, T1, T2, T3> {

    T3 apply(T t, T1 t1, T2 t2);

}
