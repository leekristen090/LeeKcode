package model;

/**
 * This is the greyscale class which holds logic for greyscale using a matrix.
 */
public class Greyscale {

  /**
   * Manipulate image pixel data using the grey scale matrix.
   * @param image image to greyscale
   * @param splitPercentage optional split preview parameter
   * @return grey scale image
   */
  public Image execute(Image image, Integer splitPercentage) {
    int splitPoint = splitPercentage != null
            ? (image.getWidth() * splitPercentage / 100) : image.getWidth();

    double[][] greyscaleMatrix = greyscaleMatrix();
    // Apply the greyscale transformation to the image
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        if (col < splitPoint) {
          // Get the original pixel values
          int r = image.getPixel(row, col, 0);
          int g = image.getPixel(row, col, 1);
          int b = image.getPixel(row, col, 2);

          // Calculate the new pixel values
          int newRed = (int) (r * greyscaleMatrix[0][0] + g * greyscaleMatrix[0][1]
                  + b * greyscaleMatrix[0][2]);
          int newGreen = (int) (r * greyscaleMatrix[1][0] + g * greyscaleMatrix[1][1]
                  + b * greyscaleMatrix[1][2]);
          int newBlue = (int) (r * greyscaleMatrix[2][0] + g * greyscaleMatrix[2][1]
                  + b * greyscaleMatrix[2][2]);

          // Clamp the values to [0, 255]
          newRed = Math.min(255, Math.max(0, newRed));
          newGreen = Math.min(255, Math.max(0, newGreen));
          newBlue = Math.min(255, Math.max(0, newBlue));

          image.setPixel(row, col, newRed, newGreen, newBlue);
        } else {
          // For the right side of the split, retain original colors
          image.setPixel(row, col, image.getPixel(row, col, 0),
                  image.getPixel(row, col, 1),
                  image.getPixel(row, col, 2));
        }
      }
    }

    return image;
  }

  private double[][] greyscaleMatrix() {
    double[][] matrix = new double[3][3];

    matrix[0][0] = 0.2126;
    matrix[0][1] = 0.7152;
    matrix[0][2] = 0.0772;
    matrix[1][0] = 0.2126;
    matrix[1][1] = 0.7152;
    matrix[1][2] = 0.0772;
    matrix[2][0] = 0.2126;
    matrix[2][1] = 0.7152;
    matrix[2][2] = 0.0772;

    return matrix;
  }
}