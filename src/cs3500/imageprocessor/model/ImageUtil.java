package cs3500.imageprocessor.model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  public static int[][] computeFrequencyHistogram(BufferedImage image) {
      int[][] histogram = new int[4][256];
      int[] rgb = image.getRGB(0, 0, image.getWidth(), image.getHeight(),
        null, 0, image.getWidth());
      for (int i = 0; i < rgb.length; i++) {
          Color color = new Color(rgb[i]);
          histogram[0][color.getRed()]++;
          histogram[1][color.getGreen()]++;
          histogram[2][color.getBlue()]++;
          double intensity = (color.getRed() + color.getGreen() + color.getBlue()) / 3.0;
          histogram[3][(int) Math.round(intensity)]++;
      }

      int[] maxes = new int[4];
      for (int i = 0; i < histogram.length; i++) {
        for (int j = 0; j < histogram[i].length; j++) {
          if (histogram[i][j] > maxes[i]) {
            maxes[i] = histogram[i][j];
          }
        }
      }

      for (int i = 0; i < histogram.length; i++) {
          for (int j = 0; j < histogram[i].length; j++) {
              histogram[i][j] = (int)((histogram[i][j] * 100.0) / (double) maxes[i]);
          }
      }
      return histogram;
  }

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static BufferedImage readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      throw new IllegalArgumentException("File " + filename + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        Color c = new Color(r, g, b);
        image.setRGB(j, i, c.getRGB());
      }
    }
    return image;
  }
}

