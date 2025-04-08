/**
 * This is the luma class which holds logic for luma which is the weighted sum
 * 0.2126r + 0.7152g + 0.0722b.
 */
public class Luma {

  /**
   * Create grey-scale image using luma value for each given pixel.
   * @param image image to grey
   * @param newAlias grey alias
   * @param controller controller
   */
  public void execute(Image image, String newAlias, ImCont controller) {
    double imageLuma;

    Image lumaImage = new ConcreteImage(newAlias, image.getHeight(), image.getWidth());
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        imageLuma = image.getPixel(row, col, 0) * 0.2126;
        imageLuma += image.getPixel(row, col, 1) * 0.7152;
        imageLuma += image.getPixel(row, col, 2) * 0.0772;

        lumaImage.setPixel(row, col, (int)imageLuma,(int)imageLuma,(int)imageLuma);
      }
    }
    controller.addImage(lumaImage, newAlias);
  }

}