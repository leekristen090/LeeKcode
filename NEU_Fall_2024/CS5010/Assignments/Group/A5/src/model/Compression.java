package model;

/**
 * This class holds the logic for compressing an image.
 */
public class Compression {

  private final int percent;

  public Compression(int percent) {
    if (percent < 0 || percent > 100) {
      throw new IllegalArgumentException("Compression percentage must be between 0 and 100.");
    }
    this.percent = percent;
  }

  public Image execute(Image image) {
    int height = image.getHeight();
    int width = image.getWidth();

    int[][][] currPixelData = new int[height][width][3];

    // Extract pixel data
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        currPixelData[row][col][0] = image.getPixel(row, col, 0); // Red
        currPixelData[row][col][1] = image.getPixel(row, col, 1); // Green
        currPixelData[row][col][2] = image.getPixel(row, col, 2); // Blue
      }
    }

    // Perform the Haar wavelet transform on each RGB channel
    haarTransform(currPixelData, 0); // Red channel
    haarTransform(currPixelData, 1); // Green channel
    haarTransform(currPixelData, 2); // Blue channel

    // Apply thresholding for lossy compression
    int thresholdIndex = (int) (255 * (percent / 100.0));
    applyThreshold(currPixelData, thresholdIndex);

    // Perform the inverse Haar wavelet transform on each RGB channel
    haarInverseTransform(currPixelData, 0); // Red channel
    haarInverseTransform(currPixelData, 1); // Green channel
    haarInverseTransform(currPixelData, 2); // Blue channel

    // Create a new image with the compressed pixel data
    //SimpleImage newImage = new SimpleImage("compress-mitra", height, width);

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int r = currPixelData[row][col][0];
        int g = currPixelData[row][col][1];
        int b = currPixelData[row][col][2];
        image.setPixel(row, col, r, g, b);
      }
    }

    return image;
  }

  private void haarTransform(int[][][] pixelData, int channel) {
    int height = pixelData.length;
    int width = pixelData[0].length;
    double[] data = new double[width];

    // Perform Haar transform on rows
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        data[col] = pixelData[row][col][channel];
      }
      data = transform(data);
      for (int col = 0; col < width; col++) {
        pixelData[row][col][channel] = (int) data[col];
      }
    }

    // Perform Haar transform on columns
    for (int col = 0; col < width; col++) {
      for (int row = 0; row < height; row++) {
        data[row] = pixelData[row][col][channel];
      }
      data = transform(data);
      for (int row = 0; row < height; row++) {
        pixelData[row][col][channel] = (int) data[row];
      }
    }
  }

  private void haarInverseTransform(int[][][] pixelData, int channel) {
    int height = pixelData.length;
    int width = pixelData[0].length;
    double[] data = new double[width];

    // Perform inverse Haar transform on rows
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        data[col] = pixelData[row][col][channel];
      }
      data = inverseTransform(data);
      for (int col = 0; col < width; col++) {
        pixelData[row][col][channel] = (int) data[col];
      }
    }

    // Perform inverse Haar transform on columns
    for (int col = 0; col < width; col++) {
      for (int row = 0; row < height; row++) {
        data[row] = pixelData[row][col][channel];
      }
      data = inverseTransform(data);
      for (int row = 0; row < height; row++) {
        pixelData[row][col][channel] = (int) data[row];
      }
    }
  }

  private double[] transform(double[] array) {
    int size = array.length;
    while (!isPowerOfTwo(size)) {
      size++;
    }

    double[] transformedArray = new double[size];
    for (int i = 0; i < size / 2; i += 2) {
      transformedArray[i] = (array[i] + array[i + 1]) / Math.sqrt(2);
    }


    return transformedArray;
  }

  private double[] inverseTransform(double[] array) {
    int size = array.length;
    while (!isPowerOfTwo(size)) {
      size++;
    }

    double[] originalArray = new double[size];
    for (int i = 0; i < size / 2; i += 2) {
      originalArray[i] = (array[i] + array[i + 1]) / Math.sqrt(2);
      originalArray[i + 1] = (array[i] - array[i + 1]) / Math.sqrt(2);
    }


    return originalArray;
  }

  private void applyThreshold(int[][][] pixelData, int thresholdIndex) {
    for (int i = 0; i < pixelData.length; i++) {
      for (int j = 0; j < pixelData[i].length; j++) {
        for (int k = 0; k < 3; k++) {
          if (Math.abs(pixelData[i][j][k]) < thresholdIndex) {
            pixelData[i][j][k] = 0;
          }
        }
      }
    }
  }

  private boolean isPowerOfTwo(int n) {
    return n > 0 && (n & (n - 1)) == 0;
  }
}