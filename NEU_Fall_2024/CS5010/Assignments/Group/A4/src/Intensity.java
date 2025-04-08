/**
 * This is the intensity class which hols the logic for finding intensity which the average of the
 * three components for each pixel.
 */
public class Intensity {

  /**
   * Create new grey-scale image based on intensity of each given pixel.
   * @param image to grey
   * @param newAlias grey scale alias
   * @param controller controller
   */
  public void execute(Image image, String newAlias, ImCont controller) {

    int colorIntensity;
    Image intensityImage = new ConcreteImage(newAlias, image.getHeight(), image.getWidth());
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        colorIntensity = image.getPixel(row, col, 0);
        colorIntensity += image.getPixel(row, col, 1);
        colorIntensity += image.getPixel(row, col, 2);

        colorIntensity = colorIntensity / 3;

        intensityImage.setPixel(row, col, colorIntensity,colorIntensity, colorIntensity);
      }
    }
    controller.addImage(intensityImage, newAlias);
  }

}