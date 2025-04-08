package model;

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
   * @param newImage new image
   * @return combo image of r, g, b images
   */
  public Image execute(Image redImage,Image greenImage,Image blueImage, Image newImage) {
    int h = greenImage.getHeight();
    int w = greenImage.getWidth();

    for (int row = 0; row < h; row++) {
      for (int col = 0; col < w; col++) {
        int redPix = redImage.getPixel(row, col, 0);
        int greenPix = greenImage.getPixel(row, col, 1);
        int bluePix = blueImage.getPixel(row, col, 2);
        int combinedRed = Math.min(255, redPix);
        int combinedGreen = Math.min(255, greenPix);
        int combinedBlue = Math.min(255, bluePix);

        newImage.setPixel(row, col, combinedRed, combinedGreen, combinedBlue);
      }
    }

    return newImage;
  }

}