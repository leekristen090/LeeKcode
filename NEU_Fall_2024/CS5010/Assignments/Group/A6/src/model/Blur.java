package model;

/**
 * This is the blur class which holds blurring logic. We are using the blur kernel from the canvas
 * description in assignment 4. It will also take an option split percentage from the command line
 * and split the image into its preview according to the given percentage.
 */
public class Blur {

  /**
   * Manipulating an image to be blurry based on a blur kernel. An optional split preview percentage
   * to split the image into original and manipulated.
   * @param image image to blur
   * @param splitPercentage optional split preview percentage
   * @return blurred image
   */
  public Image execute(Image image,Integer splitPercentage) {

    double[][] blurKernel = {
            {1 / 16.0, 1 / 8.0, 1 / 16.0},
            {1 / 8.0, 1 / 4.0, 1 / 8.0},
            {1 / 16.0, 1 / 8.0, 1 / 16.0}
    };

    int splitPoint = splitPercentage != null
            ? (image.getWidth() * splitPercentage / 100) : image.getWidth();

    for (int row = 1; row < image.getHeight() - 1; row++) {
      for (int col = 1; col < image.getWidth() - 1; col++) {
        // Apply blur only to the left side of the split
        if (col < splitPoint) {
          int[] newColor = applyBlurKernel(image, row, col, blurKernel);
          image.setPixel(row, col, newColor[0], newColor[1], newColor[2]);
        } else {
          // For the right side of the split, retain original colors
          image.setPixel(row, col, image.getPixel(row, col, 0),
                  image.getPixel(row, col, 1),
                  image.getPixel(row, col, 2));
        }
      }
    }

    return image;
    //controller.addImage(image, newAlias);

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