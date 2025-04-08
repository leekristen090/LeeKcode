/**
 * This is the blur class which holds blurring logic.
 */
public class Blur {

  /**
   * Manipulating an image to be blurry based on a blur kernel.
   * @param image image to blur
   * @param newAlias blurred alias
   * @param controller controller
   */
  public void execute(Image image, String newAlias, ImCont controller) {

    double[][] blurKernel = {
            {1 / 16.0, 1 / 8.0, 1 / 16.0},
            {1 / 8.0, 1 / 4.0, 1 / 8.0},
            {1 / 16.0, 1 / 8.0, 1 / 16.0}
    };

    //blurredImage = new ConcreteImage("blurred",image.getHeight(), image.getWidth());

    for (int row = 1; row < image.getHeight() - 1; row++) {
      for (int col = 1; col < image.getWidth() - 1; col++) {
        int[] newColor = applyBlurKernel(image, row, col, blurKernel);
        image.setPixel(row, col, newColor[0], newColor[1], newColor[2]);
      }
    }

    //blurredImage.saveImage(destinationImageName);
    controller.addImage(image, newAlias);

  }

  /**
   * Helper method to apply blur kernel.
   * @param image image to blur
   * @param row row
   * @param col col
   * @param kernel blur kernel to apply
   * @return return pixel color
   */
  private int[] applyBlurKernel(Image image, int row, int col, double[][] kernel) {
    int[] newColor = {0, 0, 0};

    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        for (int channel = 0; channel < 3; channel++) {
          newColor[channel] += (int) (image.getPixel(row + i, col + j, channel)
                  * kernel[i + 1][j + 1]);
        }
      }
    }

    // Clamp values to [0, 255]
    for (int i = 0; i < 3; i++) {
      newColor[i] = Math.min(255, Math.max(0, newColor[i]));
    }

    return newColor;
  }
}