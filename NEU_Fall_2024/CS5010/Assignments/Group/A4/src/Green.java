/**
 * This contains the logic to create a new image from a given image's green pixel values.
 */
public class Green {

  /**
   * Creating an image of green value pixels.
   * @param image image
   * @param newAlias new alias after green
   * @param controller controller
   */
  public void execute(Image image, String newAlias, ImCont controller) {
    int greenVal;

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        greenVal = image.getPixel(i, j, 1);
        image.setPixel(i, j, greenVal, greenVal, greenVal);
      }
    }
    controller.addImage(image, newAlias);
  }

}