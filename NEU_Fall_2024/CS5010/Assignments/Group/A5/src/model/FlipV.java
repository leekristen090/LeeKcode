package model;

/**
 * This is the flip vertical class which holds logic for flipping vertically.
 */
public class FlipV {

  /**
   * Flipping vertically (over the vertical axis).
   * @param image image to flip
   * @return vertically flipped image
   */
  public Image execute(Image image) {
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
    return image;
  }

}