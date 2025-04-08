package model;

/**
 * This contains the logic to create a new image from a given image's green pixel values.
 */
public class Green {

  /**
   * Creating an image of green value pixels. Set all pixel values of the new image to the green
   * pixel values from the original image. Since each individual pixel will have the same value for
   * its r, g, b channels, we will see a grey image.
   * @param image image
   * @return green component image
   */
  public Image execute(Image image) {
    int greenVal;

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        greenVal = image.getPixel(i, j, 1);
        image.setPixel(i, j, greenVal, greenVal, greenVal);
      }
    }
    return image;
  }

}