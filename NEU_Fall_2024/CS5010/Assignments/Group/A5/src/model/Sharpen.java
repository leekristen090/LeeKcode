package model;

/**
 * This is the sharpen class which holds the logic for sharpening an image. We use the sharpen
 * kernel which was given to us on canvas.
 */
public class Sharpen {

  /**
   * Sharpen an image using a kernel. We also can take an optional split percentage parameter to
   * split the image at a given spot for a preview of the manipulation.
   * @param image image to sharpen
   * @param splitPercentage optional split preview percentage
   * @return sharpened image
   */
  public Image execute(Image image, Integer splitPercentage) {
    double[][] sharpKernel = {
            {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}
    };

    int splitPoint = splitPercentage != null ?
            (image.getWidth() * splitPercentage / 100) : image.getWidth();


    for (int row = 2; row < image.getHeight() - 2; row++) {
      for (int col = 2; col < image.getWidth() - 2; col++) {
        if (col < splitPoint) {
          int[] newColor = applySharpKernel(image, row, col, sharpKernel);
          image.setPixel(row, col, newColor[0], newColor[1], newColor[2]);
        }
      }
    }

    return image;
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