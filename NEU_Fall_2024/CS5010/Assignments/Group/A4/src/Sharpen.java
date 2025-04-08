/**
 * This is the sharpen class which holds the logic for sharpening an image.
 */
public class Sharpen {

  /**
   * Sharpen an image using a kernel.
   * @param image image to sharpen
   * @param newAlias sharpened alias
   * @param controller controller
   */
  public void execute(Image image, String newAlias, ImCont controller) {
    double[][] sharpKernel = {
            {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}
    };

    for (int row = 2; row < image.getHeight() - 2; row++) {
      for (int col = 2; col < image.getWidth() - 2; col++) {
        int[] newColor = applySharpKernel(image,row, col, sharpKernel);
        image.setPixel(row, col, newColor[0], newColor[1], newColor[2]);
      }
    }

    //sharper.saveImage(destinationImageName);
    controller.addImage(image, newAlias);
  }

  /**
   * Helper method to apply the sharp kernel.
   * @param im image to sharpen
   * @param row row
   * @param col column
   * @param kernel kernel to apply
   * @return return pixel color
   */
  private int[] applySharpKernel(Image im,int row, int col, double[][] kernel) {
    int[] newColor = {0, 0, 0};

    for (int i = -2; i <= 2; i++) {
      for (int j = -2; j <= 2; j++) {
        for (int channel = 0; channel < 3; channel++) {
          newColor[channel] += (int) (im.getPixel(row + i, col + j, channel)
                  * kernel[i + 2][j + 2]);
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