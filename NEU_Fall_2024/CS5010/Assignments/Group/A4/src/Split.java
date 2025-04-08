/**
 * this is the split class which holds logic to split an image into its red, green, and blue values
 * into three separate images.
 */
public class Split {
  /**
   * Get rgb data for each pixel and create three separate images.
   * @param image image to split
   * @param redAlias red image alias
   * @param greenAlias green image alias
   * @param blueAlias blue image alias
   * @param controller controller
   */
  public void execute(Image image, String redAlias, String greenAlias, String blueAlias,
                      ImCont controller) {
    ConcreteImage redImage = new ConcreteImage("red", image.getHeight(), image.getWidth());
    ConcreteImage greenImage = new ConcreteImage("green", image.getHeight(), image.getWidth());
    ConcreteImage blueImage = new ConcreteImage("blue", image.getHeight(), image.getWidth());

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
    controller.addImage(redImage, redAlias);
    controller.addImage(greenImage, greenAlias);
    controller.addImage(blueImage, blueAlias);
  }

}