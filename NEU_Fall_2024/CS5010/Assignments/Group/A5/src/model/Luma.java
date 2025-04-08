package model;
/**
 * This is the luma class which holds logic for luma which is the weighted sum
 * 0.2126r + 0.7152g + 0.0722b.
 */
public class Luma {

  /**
   * Create grey-scale image using luma value for each given pixel.
   * @param image image to grey
   * @param splitPercentage optional split preview percentage
   * @return luma grey image
   */
  public Image execute(Image image, Integer splitPercentage) {
    double imageLuma;
    int splitPoint = splitPercentage != null ?
            (image.getWidth() * splitPercentage / 100) : image.getWidth();

    //Image lumaImage = new ConcreteImage(newAlias, image.getHeight(), image.getWidth());
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        if (col < splitPoint) {
          imageLuma = image.getPixel(row, col, 0) * 0.2126;
          imageLuma += image.getPixel(row, col, 1) * 0.7152;
          imageLuma += image.getPixel(row, col, 2) * 0.0772;

          image.setPixel(row, col, (int) imageLuma, (int) imageLuma, (int) imageLuma);
        } else {
          // For the right side of the split, retain original colors
          image.setPixel(row, col, image.getPixel(row, col, 0),
                  image.getPixel(row, col, 1),
                  image.getPixel(row, col, 2));
        }
      }
    }
    //controller.addImage(image, newAlias);
    return image;
  }

}