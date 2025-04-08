
/**
 * This contains the logic to create a new image from a given image's red pixel values.
 */
public class Red {

  /**
   * Creating an image of red value pixels.
   * @param image image
   * @param newAlias new alias after red
   * @param controller controller
   */
  public void execute(Image image, String newAlias, ImCont controller) {
    int redVal;

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        redVal = image.getPixel(i, j, 0);
        image.setPixel(i, j, redVal, redVal, redVal);
      }
    }
    controller.addImage(image, newAlias);
  }

}