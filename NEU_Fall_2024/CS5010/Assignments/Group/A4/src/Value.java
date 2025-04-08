/**
 * This is the value class which holds the logic for value which is the maximum value of the three
 * components for each pixel.
 */
public class Value {

  /**
   * Create grey-scale image based on value of each given pixel.
   * @param image image to grey
   * @param newAlias grey alias
   * @param controller controller
   */
  public void execute(Image image, String newAlias, ImCont controller) {
    int max;
    Image valueImage = new ConcreteImage(newAlias, image.getHeight(), image.getWidth());
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        int r = image.getPixel(row, col, 0);
        int g = image.getPixel(row, col, 1);
        int b = image.getPixel(row, col, 2);

        max = Math.max(r, Math.max(g, b));

        valueImage.setPixel(row, col, max, max, max);
      }
    }
    controller.addImage(valueImage, newAlias);
  }

}