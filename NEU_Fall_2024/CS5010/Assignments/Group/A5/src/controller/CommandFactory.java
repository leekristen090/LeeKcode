package controller;

import java.io.IOException;

import model.Blue;
import model.Blur;
import model.Brightness;
import model.ColorCorrect;
import model.Combine;
import model.Compression;
import model.FlipH;
import model.FlipV;
import model.Green;
import model.Greyscale;
import model.Histogram;
import model.Image;
import model.Intensity;
import model.Levels;
import model.Luma;
import model.Red;
import model.Sepia;
import model.Sharpen;
import model.SimpleImage;
import model.Split;
import model.Value;

/**
 * This is the CommandFactory class. This helps apply image manipulations to images.
 */
public class CommandFactory extends ImageController {

  /**
   * Helper method to create copies of images so we don't overwrite images when doing multiple
   * manipulations.
   * @param image image to copy
   * @param newAlias the image copy alias
   * @return the copied image
   * @throws IOException throw an exception
   */
  private static Image createImageCopy(Image image, String newAlias) throws IOException {
    int height = image.getHeight();
    int width = image.getWidth();
    Image copy = new SimpleImage(newAlias, height, width); // Adjust constructor if necessary

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int r = image.getPixel(row, col, 0);
        int g = image.getPixel(row, col, 1);
        int b = image.getPixel(row, col, 2);
        copy.setPixel(row, col, r, g, b);
      }
    }
    return copy;
  }

  /**
   * Apply sepia manipulation.
   * @param params from command line/script
   * @throws IOException throws exception
   */
  public static void applySepia(String[] params) throws IOException {
    String originalAlias = params[1];
    String newAlias = params[2];
    Integer splitPercentage = params.length > 3 ? Integer.valueOf(params[3]) : null;
    // Check if the original image exists in the images map
    Image originalImage = images.get(originalAlias);
    if (originalImage == null) {
      throw new IOException("Original image not found with alias: " + originalAlias);
    }

    Image copy;
    try {
      copy = createImageCopy(originalImage, newAlias);
    } catch (IOException e) {
      throw new IOException("Error copying the image: " + e.getMessage(), e);
    }

    Sepia sep = new Sepia();
    try {
      sep.execute(copy, splitPercentage);
      images.put(newAlias, copy);
    } catch (Exception e) {
      System.out.println("Error applying sepia: " + e.getMessage());
      throw new IOException("Error applying sepia effect", e);
    }
  }

  /**
   * Apply blur manipulation.
   * @param params from command line/script
   * @throws IOException exception
   */
  public static void applyBlur(String[] params) throws IOException {
    String originalAlias = params[1];
    String newAlias = params[2];
    Integer splitPercentage = params.length > 3 ? Integer.valueOf(params[3]) : null;
    Image originalImage = images.get(originalAlias);
    Image copy;
    try {
      //copy = originalImage.copy();
      copy = createImageCopy(originalImage, newAlias);
    } catch (IOException e) {
      throw new IOException("Error copying the image: " + e.getMessage(), e);
    }

    Blur blur = new Blur();
    blur.execute(copy, splitPercentage);
    images.put(newAlias, copy);
  }

  /**
   * Applying sharpen manipulation.
   * @param params from command line/script
   * @throws IOException exception
   */
  public static void applySharpen(String[] params) throws IOException {
    String originalAlias = params[1];
    String newAlias = params[2];
    Integer splitPercentage = params.length > 3 ? Integer.valueOf(params[3]) : null;
    Image originalImage = images.get(originalAlias);
    Image copy;
    try {
      //copy = originalImage.copy();
      copy = createImageCopy(originalImage, newAlias);
    } catch (IOException e) {
      throw new IOException("Error copying the image: " + e.getMessage(), e);
    }

    Sharpen sharpen = new Sharpen();
    sharpen.execute(copy, splitPercentage);
    images.put(newAlias, copy);
  }

  /**
   * Apply grey manipulations.
   * @param params from command line/script
   * @throws IOException exception
   */
  public static void applyGrey(String[] params) throws IOException {
    String originalAlias = params[1];
    String newAlias = params[2];
    Integer splitPercentage = params.length > 3 ? Integer.valueOf(params[3]) : null;
    Image originalImage = images.get(originalAlias);
    Image copy;
    try {
      //copy = originalImage.copy();
      copy = createImageCopy(originalImage, newAlias);
    } catch (IOException e) {
      throw new IOException("Error copying the image: " + e.getMessage(), e);
    }
    switch (params[0]) {
      case "grey-scale" -> {
        Greyscale grey = new Greyscale();
        grey.execute(copy, splitPercentage);
      }
      case "luma-component" -> {
        Luma luma = new Luma();
        luma.execute(copy, splitPercentage);
      }
      case "intensity-component" -> {
        Intensity intensity = new Intensity();
        intensity.execute(copy, splitPercentage);
      }
      case "value-component" -> {
        Value val = new Value();
        val.execute(copy, splitPercentage);
      }
    }

    images.put(newAlias, copy);
  }

  /**
   * Apply color channel manipulation.
   * @param params from command line/script
   * @throws IOException exception
   */
  public static void applyColor(String[] params) throws IOException {
    String originalAlias = params[1];
    String newAlias = params[2];
    Image originalImage = images.get(originalAlias);
    Image copy;
    try {
      //copy = originalImage.copy();
      copy = createImageCopy(originalImage, newAlias);
    } catch (IOException e) {
      throw new IOException("Error copying the image: " + e.getMessage(), e);
    }
    switch (params[0]) {
      case "red-component" -> {
        Red r = new Red();
        r.execute(copy);
      }
      case "green-component" -> {
        Green g = new Green();
        g.execute(copy);
      }
      case "blue-component" -> {
        Blue b = new Blue();
        b.execute(copy);
      }
    }
    images.put(newAlias, copy);
  }

  /**
   * Apply flip manipulation.
   * @param params from command line/script
   * @throws IOException exception
   */
  public static void flip(String[] params) throws IOException {
    String originalAlias = params[1];
    String newAlias = params[2];
    Image originalImage = images.get(originalAlias);
    Image copy;
    try {
      //copy = originalImage.copy();
      copy = createImageCopy(originalImage, newAlias);
    } catch (IOException e) {
      throw new IOException("Error copying the image: " + e.getMessage(), e);
    }

    switch (params[0]) {
      case "horizontal-flip" -> {
        FlipH horizontal = new FlipH();
        horizontal.execute(copy);
      }
      case "vertical-flip" -> {
        FlipV vertical = new FlipV();
        vertical.execute(copy);
      }
    }
    images.put(newAlias, copy);
  }

  /**
   * Apply brightness manipulation. Positive percentage brightens and negative percentage darkens
   * the given image.
   * @param params from command line/script
   * @throws IOException exception
   */
  public static void applyBrightness(String[] params) throws IOException {
    int percent = Integer.parseInt(params[1]); //percent to brighten or darken
    String originalAlias = params[2]; // Original image alias
    String newAlias = params[3]; // new alias
    Image originalImage = images.get(originalAlias);
    Image copy;
    try {
      copy = createImageCopy(originalImage, newAlias);
    } catch (IOException e) {
      throw new IOException("Error copying the image: " + e.getMessage(), e);
    }
    Brightness bright = new Brightness(percent);
    bright.execute(copy);
    images.put(newAlias, copy);
  }

  /**
   * Apply combine logic. Take three images of red, green, and blue channels and combine into one
   * image.
   * @param params from command line/script
   */
  public static void applyCombine(String[] params) {
    String comboAlias = params[1];
    String redAlias = params[2];
    String greenAlias = params[3];
    String blueAlias = params[4];
    Image redImage = images.get(redAlias);
    Image greenImage = images.get(greenAlias);
    Image blueImage = images.get(blueAlias);

    Image comboImage = new SimpleImage(comboAlias, redImage.getHeight(), redImage.getWidth());
    Combine combo = new Combine();
    combo.execute(redImage, greenImage, blueImage, comboImage);
    images.put(comboAlias, comboImage);

  }

  /**
   * Apply split logic. Split a single image into three separate images which are the red, green,
   * and blue channels.
   * @param params from command line/script
   */
  public static void applySplit(String[] params) {
    String originalAlias = params[1];
    String redAlias = params[2];
    String greenAlias = params[3];
    String blueAlias = params[4];
    Image originalImage = images.get(originalAlias);

    Image redImage = new SimpleImage(redAlias, originalImage.getHeight(),
            originalImage.getWidth());
    Image greenImage = new SimpleImage(greenAlias, originalImage.getHeight(),
            originalImage.getWidth());
    Image blueImage = new SimpleImage(blueAlias, originalImage.getHeight(),
            originalImage.getWidth());

    Split split = new Split();
    split.execute(originalImage, redImage, greenImage, blueImage);

    images.put(redAlias, redImage);
    images.put(greenAlias, greenImage);
    images.put(blueAlias, blueImage);
  }

  /**
   * Apply histogram logic.
   * @param params from command line/script
   * @throws IOException exception
   */
  public static void applyHistogram(String[] params) throws IOException {
    String originalAlias = params[1];
    String newAlias = params[2];
    Image originalImage = images.get(originalAlias);
    Image copy;
    try {
      copy = createImageCopy(originalImage, newAlias);
    } catch (IOException e) {
      throw new IOException("Error copying the image: " + e.getMessage(), e);
    }
    Histogram histogram = new Histogram();
    Image histogramImage = histogram.execute(originalImage); // Get the histogram image
    images.put(newAlias, histogramImage);
  }

  /**
   * Apply levels adjustment.
   * @param params from command line/script
   * @throws IOException exception
   */
  public static void applyLevels(String[] params) throws IOException {
    int b = Integer.parseInt(params[1]); //black
    int m = Integer.parseInt(params[2]); //mid
    int w = Integer.parseInt(params[3]); //white
    String originalAlias = params[4];
    String newAlias = params[5];
    Integer splitPercentage = null;
    // Check if the split percentage parameter is provided
    if (params.length == 7) {
      try {
        splitPercentage = Integer.parseInt(params[6]);
        // Validate split percentage (0 to 100)
        if (splitPercentage < 0 || splitPercentage > 100) {
          throw new IllegalArgumentException("Split percentage must be between 0 and 100.");
        }
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Split percentage must be an integer.");
      }
    }
    Image originalImage = images.get(originalAlias);
    Image copy;
    try {
      copy = createImageCopy(originalImage, newAlias);
    } catch (IOException e) {
      throw new IOException("Error copying the image: " + e.getMessage(), e);
    }
    Levels levels = new Levels(b, m, w);
    levels.execute(copy, splitPercentage);
    images.put(newAlias, copy);
  }

  /**
   * Apply color correction logic.
   * @param params from command line/script
   * @throws IOException exception
   */
  public static void applyCorrect(String[] params) throws IOException {
    String originalAlias = params[1];
    String newAlias = params[2];
    Integer splitPercentage = null;

    // Check if the split percentage parameter is provided
    if (params.length == 4) {
      try {
        splitPercentage = Integer.parseInt(params[3]);
        // Validate split percentage (0 to 100)
        if (splitPercentage < 0 || splitPercentage > 100) {
          throw new IllegalArgumentException("Split percentage must be between 0 and 100.");
        }
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Split percentage must be an integer.");
      }
    }
    Image originalImage = images.get(originalAlias);
    Image copy;
    try {
      copy = createImageCopy(originalImage, newAlias);
    } catch (IOException e) {
      throw new IOException("Error copying the image: " + e.getMessage(), e);
    }
    ColorCorrect correct = new ColorCorrect();
    correct.execute(copy, splitPercentage);
    images.put(newAlias, copy);
  }

  /**
   * Apply compression logic.
   * @param params from command line/script
   * @throws IOException exception
   */
  public static void applyCompression(String[] params) throws IOException {
    System.out.println("we are in apply compression");
    // "compress percent image dest"
    int percent = Integer.parseInt(params[1]); //percent to compress by
    String originalAlias = params[2]; // Original image alias
    String newAlias = params[3]; // new alias
    Image originalImage = images.get(originalAlias);
    Image copy;
    try {
      copy = createImageCopy(originalImage, newAlias);
      System.out.println("copy height: " + copy.getHeight() + " width: " + copy.getWidth());
    } catch (IOException e) {
      throw new IOException("Error copying the image: " + e.getMessage(), e);
    }

    Compression comp = new Compression(percent);
    try {
      comp.execute(copy);
      images.put(newAlias, copy);
    } catch (Exception e) {
      System.out.println("Error applying compression in applyCompression: " + e.getMessage());
      throw new IOException("Error applying compression effect", e);
    }
    //comp.execute(copy);
    //images.put(newAlias, copy);
  }
}