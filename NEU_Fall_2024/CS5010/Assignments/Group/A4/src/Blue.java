/**
 * This contains the logic to create a new image from a given image's blue pixel values.
 */
public class Blue {

  /**
   * Creating an image of blue value pixels.
   * @param image image
   * @param newAlias new alias after blue
   * @param controller controller
   */
  public void execute(Image image, String newAlias, ImCont controller) {
    int blueVal;

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        blueVal = image.getPixel(i, j, 1);
        image.setPixel(i, j, blueVal, blueVal, blueVal);
      }
    }
    controller.addImage(image, newAlias);
  }

}