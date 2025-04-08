/**
 * This is the flip vertical class which holds logic for flipping vertically.
 */
public class FlipVert {

  /**
   * Flipping vertically (over the vertical axis).
   * @param image image to flip
   * @param newAlias flipped alias
   * @param controller controller
   */
  public void execute(Image image, String newAlias, ImCont controller) {
    for (int col = 0; col < image.getWidth() / 2; col++) {
      for (int row = 0; row < image.getHeight(); row++) {
        int[] leftP = {
                image.getPixel(row, col, 0),
                image.getPixel(row, col, 1),
                image.getPixel(row, col, 2)
        };
        int[] rightP = {
                image.getPixel(row, image.getWidth() - col - 1, 0),
                image.getPixel(row, image.getWidth() - col - 1, 1),
                image.getPixel(row, image.getWidth() - col - 1, 2)
        };

        image.setPixel(row, col, rightP[0], rightP[1], rightP[2]);
        image.setPixel(row, image.getWidth() - col - 1, leftP[0], leftP[1], leftP[2]);
      }
    }
    controller.addImage(image, newAlias);
  }

}