package model;


/**
 * This contains the logic to create a new image from a given image's red pixel values.
 */
public class Red {

  /**
   * Creating an image of red value pixels.Set all pixel values of the new image to the red pixel
   * values from the original image. Since each individual pixel will have the same value for its r,
   * g, b channels, we will see a grey image.
   * @param image image
   * @return red component image
   */
  public Image execute(Image image) {
    int redVal;

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        redVal = image.getPixel(i, j, 0);
        image.setPixel(i, j, redVal, redVal, redVal);
      }
    }
    return image;
  }

}