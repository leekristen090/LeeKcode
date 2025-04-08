package model;

/**
 * this is the split class which holds logic to split an image into its red, green, and blue values
 * into three separate images.
 */
public class Split {
  /**
   * Get rgb data for each pixel and create three separate images.
   * @param image image to split
   * @param redImage red image
   * @param greenImage green image
   * @param blueImage blue image
   */
  public void execute(Image image, Image redImage, Image greenImage, Image blueImage) {

    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        int redPix = image.getPixel(row, col, 0);
        int greenPix = image.getPixel(row, col, 1);
        int bluePix = image.getPixel(row, col, 2);

        redImage.setPixel(row, col, redPix, redPix, redPix);
        greenImage.setPixel(row, col, greenPix, greenPix, greenPix);
        blueImage.setPixel(row, col, bluePix, bluePix, bluePix);
      }
    }

  }

}