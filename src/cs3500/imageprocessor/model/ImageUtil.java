package cs3500.imageprocessor.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

//  public static IPixel[][] readStandardImageFormat(String filename) throws IOException {
//    BufferedImage img = ImageIO.read(new File(filename));
//    IPixel[][] pixels = new IPixel[img.getHeight()][img.getWidth()];
//    for (int r = 0; r < img.getHeight(); r++) {
//      for (int c = 0; c < img.getWidth(); c++) {
//        int rgb = img.getRGB(c, r);
//        Color color = new Color(rgb, true);
//        pixels[r][c] = new RGBAPixel(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
//      }
//    }
//    return pixels;
//  }

  public static IPixel[][] readPNG(String filename) throws IOException {
    BufferedImage img = ImageIO.read(new File(filename));
    IPixel[][] pixels = new IPixel[img.getHeight()][img.getWidth()];
    for (int r = 0; r < img.getHeight(); r++) {
      for (int c = 0; c < img.getWidth(); c++) {
        int rgba = img.getRGB(c, r);

        Color color = new Color(rgba, true);
        pixels[r][c] = new RGBAPixel(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
      }
    }
    return pixels;
  }

  public static IPixel[][] readJPG(String filename) throws IOException {
    BufferedImage img = ImageIO.read(new File(filename));
    IPixel[][] pixels = new IPixel[img.getHeight()][img.getWidth()];
    for (int r = 0; r < img.getHeight(); r++) {
      for (int c = 0; c < img.getWidth(); c++) {
        int rgba = img.getRGB(c, r);
        Color color = new Color(rgba);
        pixels[r][c] = new RGBPixel(color.getRed(), color.getGreen(), color.getBlue());
      }
    }
    return pixels;
  }

  public static IPixel[][] readBMP(String filename) throws IOException {
    BufferedImage img = ImageIO.read(new File(filename));
    IPixel[][] pixels = new IPixel[img.getHeight()][img.getWidth()];
    for (int r = 0; r < img.getHeight(); r++) {
      for (int c = 0; c < img.getWidth(); c++) {
        int rgba = img.getRGB(c, r);
        Color color = new Color(rgba);
        pixels[r][c] = new RGBPixel(color.getRed(), color.getGreen(), color.getBlue());
      }
    }
    return pixels;
  }

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static IPixel[][] readPPM(String filename) {
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

    IPixel[][] image = new IPixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        IPixel pixel = new RGBPixel(r, g, b);
        image[i][j] = pixel;
      }
    }
    return image;
  }
}

