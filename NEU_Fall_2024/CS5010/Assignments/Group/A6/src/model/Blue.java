package model;

/**
 * This contains the logic to create a new image from a given image's blue pixel values.
 */
public class Blue {

  /**
   * Creating an image of blue value pixels. Set all pixel values of the new image to the blue pixel
   * values from the original image. Since each individual pixel will have the same value for its r,
   * g, b channels, we will see a grey image.
   * @param image image
   * @return return blue value image
   */
  public Image execute(Image image) {
    int blueVal;

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        blueVal = image.getPixel(i, j, 2);
        image.setPixel(i, j, blueVal, blueVal, blueVal);
      }
    }
    return image;
  }

}