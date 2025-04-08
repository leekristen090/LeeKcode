package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.imageio.ImageIO;

import model.SimpleImage;
import model.Image;

/**
 * This is our image controller class. It holds the implementations for load and save from the
 * controller interface.
 */
public class ImageController implements Controller {
  public static Map<String, Image> IMAGE_MAP; // Keep track of loaded images
  private Image currentImage;

  /**
   * Construct our image controller with a hash map of our images and their aliases.
   */
  public ImageController() {
    IMAGE_MAP = new HashMap<>();
  }

  /**
   * Load an image.
   * @param filepath image file path
   * @param alias alias for image
   */
  @Override
  public void loadImage(String filepath, String alias) {
    String extension = getFileExtension(filepath).toLowerCase();
    if (extension.equals("ppm")) {
      loadPPM(filepath, alias);
    }
    else if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")) {
      loadOther(filepath, alias);
    }
    else {
      throw new IllegalArgumentException("Unsupported file format: " + extension);
    }
    IMAGE_MAP.put(alias, currentImage);
    System.out.println("Loaded image with alias " + alias); // Add this line

  }

  /**
   * Save the given image.
   * @param filepath given image file path
   * @param alias alias to save
   */
  public void saveImage(String filepath, String alias) throws IOException {
    String fileExt = getFileExtension(filepath).toLowerCase();
    if (fileExt.equals("ppm")) {
      savePPM(filepath, alias);
    }
    else if (fileExt.equals("jpg") || fileExt.equals("jpeg") || fileExt.equals("png")) {
      saveOther(filepath, alias, fileExt);
    }
    else {
      throw new IllegalArgumentException("Unsupported file format: " + fileExt);
    }

  }

  /**
   * Load a file with extension ppm.
   * @param path image path
   * @param alias image alias
   */
  private void loadPPM(String path, String alias) {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(path));
    } catch (FileNotFoundException e) {
      System.out.println("File " + path + " not found!");
      return;
    }

    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    sc = new Scanner(builder.toString());

    String token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3. Yours is: "
              + token);
      return;
    }

    int imWidth = sc.nextInt();
    int imHeight = sc.nextInt();
    int maxValue = sc.nextInt();

    currentImage = new SimpleImage(alias, imHeight, imWidth);
    // Read pixel data
    for (int i = 0; i < imHeight; i++) {
      for (int j = 0; j < imWidth; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();

        currentImage.setPixel(i, j, r, g, b); // Update this line

      }
    }
    IMAGE_MAP.put(alias, currentImage);
    System.out.println("Image loaded successfully with dimensions: " + imWidth + "x" + imHeight);
  }

  /**
   * Load a file with extensions jpg, jpeg, or png.
   * @param path image path
   * @param alias image alias
   */
  private void loadOther(String path, String alias) {

    try {

      BufferedImage im = ImageIO.read(new File(path));

      if (im == null) {
        throw new IllegalArgumentException("Could not load image: " + path);
      }

      int imHeight = im.getHeight();
      int imWidth = im.getWidth();
      currentImage = new SimpleImage(alias, imHeight, imWidth);

      for (int row = 0; row < imHeight; row++) {
        for (int col = 0; col < imWidth; col++) {
          int rgb = im.getRGB(col, row);
          int r = (rgb >> 16) & 0x000000ff;
          int g = (rgb >> 8) & 0x000000ff;
          int b = rgb & 0x000000ff;
          currentImage.setPixel(row, col, r, g, b);
        }
      }

      //addImage(myIm, alias);  // Add image to your controller
      IMAGE_MAP.put(alias, currentImage);
      System.out.println("Image loaded successfully with dimensions: " + imWidth + "x" + imHeight);

    } catch (IOException e) {
      System.out.println("Error loading image: " + e.getMessage());
    }

  }

  /**
   * Save file with extension ppm.
   * @param dest destination to save
   * @param alias alias
   */
  private void savePPM(String dest, String alias) {
    Image image = IMAGE_MAP.get(alias);
    if (image == null) {
      throw new IllegalArgumentException("Image with alias '" + alias + "' not found.");
    }
    try (FileOutputStream fos = new FileOutputStream(dest)) {
      StringBuilder sb = new StringBuilder();

      // Write PPM header
      sb.append("P3" + System.lineSeparator());
      sb.append(image.getWidth()).append(" ").append(image.getHeight()).append("\n");
      sb.append("255" + System.lineSeparator());  // Max color value

      // Write pixel data
      for (int row = 0; row < image.getHeight(); row++) {
        for (int col = 0; col < image.getWidth(); col++) {
          int r = image.getPixel(row, col, 0);  // Get Red component
          int g = image.getPixel(row, col, 1);  // Get Green component
          int b = image.getPixel(row, col, 2);  // Get Blue component

          sb.append(r).append(" ").append(g).append(" ").append(b).append(" ");
        }
        sb.append("System.lineSeparator()");
      }

      // Write to file
      fos.write(sb.toString().getBytes());
      System.out.println("Image saved successfully to " + dest + "!");

    } catch (IOException e) {
      System.err.println("Error saving image: " + e.getMessage());
    }
  }

  /**
   * Save file with extensions jpg, jpeg, or png.
   * @param path path to save
   * @param alias alias
   * @param format file ext
   */
  private void saveOther(String path, String alias, String format) {
    if (!IMAGE_MAP.containsKey(alias)) {
      System.out.println("Available aliases: " + IMAGE_MAP.keySet());
      throw new IllegalArgumentException("Image with alias '" + alias + "' not found.");
    }
    Image image = IMAGE_MAP.get(alias);
    if (image == null) {
      throw new IllegalArgumentException("Image with alias '" + alias + "' not found!");
    }
    try {
      BufferedImage im = new BufferedImage(image.getWidth(), image.getHeight(),
              BufferedImage.TYPE_INT_RGB);
      for (int row = 0; row < image.getHeight(); row++) {
        for (int col = 0; col < image.getWidth(); col++) {
          int r = image.getPixel(row, col, 0);
          int g = image.getPixel(row, col, 1);
          int b = image.getPixel(row, col, 2);
          int rgb = (r << 16) | (g << 8) | b;
          im.setRGB(col, row, rgb);
        }
      }
      ImageIO.write(im, format, new File(path));
    } catch (IOException e) {
      System.err.println("Error saving image: " + e.getMessage());
    }
  }

  /**
   * Helper function to get the file extension of an image.
   * @param filepath the given image file
   * @return return the extension
   */
  private String getFileExtension(String filepath) {
    return filepath.substring(filepath.lastIndexOf('.') + 1);
  }

  public Image getImage(String alias) throws IOException {
    return IMAGE_MAP.get(alias);//images.get(alias); // Return the image corresponding to the alias
  }

}