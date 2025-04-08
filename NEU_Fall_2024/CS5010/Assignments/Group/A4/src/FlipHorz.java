/**
 * This is the flip horizontal class which holds horizontal flipping logic.
 */
public class FlipHorz {

  /**
   * Flipping an image horizontally (over the horizontal axis).
   * @param image image to flip
   * @param newAlias flipped alias
   * @param controller controller
   */
  public void execute(Image image, String newAlias, ImCont controller) {
    for (int row = 0; row < image.getHeight() / 2; row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        // Swap pixels vertically
        int[] topPixel = {
                image.getPixel(row, col, 0),
                image.getPixel(row, col, 1),
                image.getPixel(row, col, 2)
        };

        int[] bottomPixel = {
                image.getPixel(image.getHeight() - row - 1, col, 0),
                image.getPixel(image.getHeight() - row - 1, col, 1),
                image.getPixel(image.getHeight() - row - 1, col, 2)
        };

        // Swap top and bottom
        image.setPixel(row, col, bottomPixel[0], bottomPixel[1], bottomPixel[2]);
        image.setPixel(image.getHeight() - row - 1, col, topPixel[0], topPixel[1], topPixel[2]);
      }
    }
    controller.addImage(image, newAlias);
  }
}