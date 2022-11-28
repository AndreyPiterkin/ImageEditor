package cs3500.imageprocessor.operations;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import cs3500.imageprocessor.model.ImageState;
import cs3500.imageprocessor.model.RGBAPixel;

public class Mosaic implements PixelOperation {

  private final List<Pair<Double, Double>> seeds;
  private Optional<List<RGBAPixel>> kMeansClusters;


  public Mosaic(int numSeeds) {
    this.seeds = new ArrayList<>(numSeeds);
    for (int i = 0; i < numSeeds; i++) {
      seeds.add(new Pair<Double, Double>(Math.random(), Math.random()));
    }
    this.kMeansClusters = Optional.empty();
  }

  private int getClosestSeedIndex(int r, int c, int width, int height) {
    double minDist = Double.MAX_VALUE;
    int closestIndexIntoKMeansClusters = 0;

    for (int i = 0; i < seeds.size(); i++) {
      Pair<Double, Double> mean = seeds.get(i);
      double dist = Math.sqrt(Math.pow(mean.getFirst() * width - c, 2) +
              Math.pow(mean.getSecond() * height - r, 2));
      if (dist < minDist) {
        minDist = dist;
        closestIndexIntoKMeansClusters = i;
      }
    }
    return closestIndexIntoKMeansClusters;
  }

  private List<RGBAPixel> computeClustering(ImageState image) {
    List<List<RGBAPixel>> clusters = new ArrayList<>(seeds.size());
    for (int i = 0; i < seeds.size(); i++) {
      clusters.add(new ArrayList<>());
    }

    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        RGBAPixel pixel = image.getPixelAt(row, col);
        int closestSeedIndex = getClosestSeedIndex(row, col, image.getWidth(), image.getHeight());
        clusters.get(closestSeedIndex).add(pixel);
      }
    }

    List<RGBAPixel> finalClustering = new ArrayList<>(seeds.size());
    for (List<RGBAPixel> cluster : clusters) {
      int red = 0;
      int green = 0;
      int blue = 0;
      for (RGBAPixel pixel : cluster) {
        red += pixel.getRed();
        green += pixel.getGreen();
        blue += pixel.getBlue();
      }
      finalClustering.add(new RGBAPixel(red / cluster.size(), green / cluster.size(),
              blue / cluster.size()));
    }
    return finalClustering;
  }

  /**
   * Performs a single pixel transformation on the given image based on its position in the image,
   * and computes the new pixel.
   *
   * @param image the image to transform
   * @param r     the row coordinate of the pixel
   * @param c     the column coordinate of the pixel
   * @return the new pixel
   */
  @Override
  public RGBAPixel apply(ImageState image, int r, int c) {
    if (kMeansClusters.isEmpty()) {
      kMeansClusters = Optional.of(computeClustering(image));
    }

    int width = image.getWidth();
    int height = image.getHeight();
    int ind = getClosestSeedIndex(r, c, width, height);
    return kMeansClusters.get().get(ind);
  }
}
