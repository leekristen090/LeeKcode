/**
 * This is the Image interface with all pixel data for a given image.
  */
public interface Image {
  /**
   * This will set the pixel values for an image at a given row and col.
   * @param row row
   * @param col column
   * @param r red value for pixel
   * @param g green value for pixel
   * @param b blue value for pixel
   */
  void setPixel(int row, int col, int r, int g, int b);

  /**
   * This will get a pixel at a given location and its color component.
   * @param row row
   * @param col column
   * @param component color component
   * @return return the pixel
   */
  int getPixel(int row, int col, int component);

  /**
   * Get the height of an image.
   * @return the height
   */
  int getHeight();

  /**
   * Get the width of an image.
   * @return the width
   */
  int getWidth();

}