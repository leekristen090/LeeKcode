package model;

/**
 * This is the Simple image class which implements the methods in the Image interface. We have our
 * pixel data for an image.
 */
public class SimpleImage implements Image {
  String imageName;
  int height;
  int width;
  protected int[][][] pixels;

  /**
   * Constructing instance of image.
   * @param name image name
   * @param h height
   * @param w width
   */
  public SimpleImage(String name, int h, int w) {
    this.imageName = name;
    this.height = h;
    this.width = w;
    this.pixels = new int[height][width][3];
  }


  /**
   * This will set the pixel values for an image at a given row and col.
   * @param row row
   * @param col column
   * @param r red value for pixel
   * @param g green value for pixel
   * @param b blue value for pixel
   */
  public void setPixel(int row, int col, int r, int g, int b) throws IllegalArgumentException {
    if (row < 0 || row >= height || col < 0 || col >= width) {
      throw new IllegalArgumentException("Invalid pixel coordinates!");
    }
    pixels[row][col][0] = Math.max(0, Math.min(255, r));
    pixels[row][col][1] = Math.max(0, Math.min(255, g));
    pixels[row][col][2] = Math.max(0, Math.min(255, b));
  }

  /**
   * This will get a pixel at a given location and its color component.
   * @param row row
   * @param col column
   * @param component color component
   * @return return the pixel
   */
  public int getPixel(int row, int col, int component) throws IllegalArgumentException {

    if (row < 0 || row >= height || col < 0 || col >= width) {
      throw new IllegalArgumentException("Invalid pixel coordinates or component");
    }
    return pixels[row][col][component];
  }

  /**
   * Get the height of an image.
   * @return the height
   */
  public int getHeight() {
    return height;
  }

  /**
   * Get the width of an image.
   * @return the width
   */
  public int getWidth() {
    return width;
  }

}