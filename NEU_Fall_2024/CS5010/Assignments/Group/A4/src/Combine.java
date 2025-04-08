/**
 * This is the combine class that holds the logic for combing three images, the red image, green
 * image, and blue image into one image.
 */
public class Combine {

  /**
   * This combines the red value image, green value image, and blue value image into one image.
   * @param redImage red value image
   * @param greenImage green value image
   * @param blueImage blue value image
   * @param alias combo alias
   * @param controller controller
   */
  public void execute(Image redImage,Image greenImage,Image blueImage, String alias,
                      ImCont controller) {
    int h = redImage.getHeight();
    int w = redImage.getWidth();
    ConcreteImage newImage = new ConcreteImage("images/redCombo.ppm", h, w);

    for (int row = 0; row < h; row++) {
      for (int col = 0; col < w; col++) {
        int redPix = redImage.getPixel(row, col, 0);
        int greenPix = greenImage.getPixel(row, col, 0);
        int bluePix = blueImage.getPixel(row, col, 0);
        int combinedRed = Math.min(255, redPix);
        int combinedGreen = Math.min(255, greenPix);
        int combinedBlue = Math.min(255, bluePix);

        newImage.setPixel(row, col, combinedRed, combinedGreen, combinedBlue);
      }
    }

    controller.addImage(newImage, alias);
  }

}