/**
 * This is the sepia class which holds logic for sepia using a matrix.
 */
public class Sepia {

  /**
   * Manipulate image pixel data using a matrix.
   * @param image image to sepia
   * @param newAlias sepia alias
   * @param controller controller
   */
  public void execute(Image image, String newAlias, ImCont controller) {
    double[][] sepiaMatrix = sepiaMatrix();
    // Apply the sepia transformation to the image
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        // Get the original pixel values
        int r = image.getPixel(row, col, 0);
        int g = image.getPixel(row, col, 1);
        int b = image.getPixel(row, col, 2);

        // Calculate the new pixel values
        int newRed = (int) (r * sepiaMatrix[0][0] + g * sepiaMatrix[0][1]
                + b * sepiaMatrix[0][2]);
        int newGreen = (int) (r * sepiaMatrix[1][0] + g * sepiaMatrix[1][1]
                + b * sepiaMatrix[1][2]);
        int newBlue = (int) (r * sepiaMatrix[2][0] + g * sepiaMatrix[2][1]
                + b * sepiaMatrix[2][2]);

        // Clamp the values to [0, 255]
        newRed = Math.min(255, Math.max(0, newRed));
        newGreen = Math.min(255, Math.max(0, newGreen));
        newBlue = Math.min(255, Math.max(0, newBlue));

        image.setPixel(row, col, newRed, newGreen, newBlue);
      }
    }

    // After processing, store the new image if necessary
    controller.addImage(image, newAlias);
  }

  private double[][] sepiaMatrix() {
    return new double[][] {
            {0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}
    };
  }
}
