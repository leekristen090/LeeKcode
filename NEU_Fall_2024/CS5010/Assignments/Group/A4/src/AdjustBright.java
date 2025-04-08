/**
 * This is the adjust brightness class which holds the logic for adjusting brightness.
 */
public class AdjustBright {

  private final int percent;

  /**
   * Construct brightening.
   * @param increment amount to adjust brightness
   */
  public AdjustBright(int increment) {
    this.percent = increment;
  }

  /**
   * This manipulates an image's brightness by a given percent. It takes positive and negative
   * values to brighten and darken an image respectively.
   * @param image image to adjust
   * @param newAlias its new alias
   * @param controller the controller
   */
  public void execute(Image image, String newAlias, ImCont controller) {
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        int r = image.getPixel(row, col, 0) + percent;
        int g = image.getPixel(row, col, 1) + percent;
        int b = image.getPixel(row, col, 2) + percent;

        // Clamp the values to [0, 255]
        r = Math.min(255, Math.max(0, r));
        g = Math.min(255, Math.max(0, g));
        b = Math.min(255, Math.max(0, b));

        image.setPixel(row, col, r, g, b);
      }
    }
    controller.addImage(image, newAlias);
  }

}