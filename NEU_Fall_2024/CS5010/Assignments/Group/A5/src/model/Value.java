package model;

/**
 * This is the value class which holds the logic for value which is the maximum value of the three
 * components for each pixel.
 */
public class Value {

  /**
   * Create grey-scale image based on value of each given pixel.
   * @param image image to grey
   * @param splitPercentage optional split preview percentage
   * @return value grey image
   */
  public Image execute(Image image, Integer splitPercentage) {
    int splitPoint = splitPercentage != null ?
            (image.getWidth() * splitPercentage / 100) : image.getWidth();
    int max;
    //Image valueImage = new ConcreteImage(newAlias, image.getHeight(), image.getWidth());
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        if (col < splitPoint) {
          int r = image.getPixel(row, col, 0);
          int g = image.getPixel(row, col, 1);
          int b = image.getPixel(row, col, 2);

          max = Math.max(r, Math.max(g, b));

          image.setPixel(row, col, max, max, max);
        }
      }
    }
    return image;
  }

}