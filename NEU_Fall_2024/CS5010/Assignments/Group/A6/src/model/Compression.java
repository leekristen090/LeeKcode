package model;

/**
 * This class holds the logic for compressing an image.
 */
public class Compression {

  private final int percent;

  /**
   * Constructing compression object to carry out the compression logic when called as an image
   * effect.
   * @param percent to compress image by
   */
  public Compression(int percent) {
    if (percent < 0 || percent > 100) {
      throw new IllegalArgumentException("Compression percentage must be between 0 and 100.");
    }
    this.percent = percent;
  }

  /**
   * Execute the compression logic on an image and then return the compressed image.
   * @param image to be compressed
   * @return the compressed image
   */
  public Image execute(Image image) {
    Image square = makeSquareImage(image);

    int size = square.getHeight();

    double[][][] currPixelData = new double[size][size][3];

    // Extract pixel data
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        currPixelData[row][col][0] = square.getPixel(row, col, 0); // Red
        currPixelData[row][col][1] = square.getPixel(row, col, 1); // Green
        currPixelData[row][col][2] = square.getPixel(row, col, 2); // Blue
      }
    }

    double[][] red = haarTransform(currPixelData, 0); // Red channel
    double[][] green = haarTransform(currPixelData, 1); // Green channel
    double[][] blue = haarTransform(currPixelData, 2); // Blue channel

    int thresholdIndex = (int) (255 * (((double) percent) / 100.0));
    applyThreshold(red, thresholdIndex);
    applyThreshold(green, thresholdIndex);
    applyThreshold(blue, thresholdIndex);

    double[][][] haarResult = new double[size][size][3];

    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        haarResult[row][col][0] = red[row][col];
        haarResult[row][col][1] = green[row][col];
        haarResult[row][col][2] = blue[row][col];
      }
    }

    int[][] redInv = haarInverseTransform(haarResult, 0);
    int[][] greenInv = haarInverseTransform(haarResult, 1);
    int[][] blueInv = haarInverseTransform(haarResult, 2);

    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        double r = redInv[row][col];
        double g = greenInv[row][col];
        double b = blueInv[row][col];
        image.setPixel(row, col, (int) r, (int) g, (int) b);
      }
    }

    return image;
  }


  private Image makeSquareImage(Image image) {
    int width = image.getWidth();
    int height = image.getHeight();

    int size = Math.max(width, height);

    while (!isPowerOfTwo(size)) {
      size++;
    }

    Image newImage = new SimpleImage("new", size, size);

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int r = image.getPixel(row, col, 0);  // Red
        int g = image.getPixel(row, col, 1);  // Green
        int b = image.getPixel(row, col, 2);  // Blue

        newImage.setPixel(row, col, r, g, b);
      }
    }

    return newImage;
  }

  private double[] transform(double[] array) {
    int size = array.length;
    while (!isPowerOfTwo(size)) {
      size++;
    }

    double a = 0.0;
    double b = 0.0;

    double[] transformedArray = new double[size];
    int count = 0;
    for (int i = 0; i < size; i += 2) {
      if (i >= array.length) {
        a = 0.0;
      } else {
        a = array[i];
      }

      if (i + 1 >= array.length) {
        b = 0.0;
      } else {
        b = array[i + 1];
      }
      transformedArray[count] = (a + b) / Math.sqrt(2);
      count++;
    }

    count = size / 2;
    for (int j = 0; j < size; j += 2) {
      if (j >= array.length) {
        a = 0.0;
      } else {
        a = array[j];
      }

      if (j + 1 >= array.length) {
        b = 0.0;
      } else {
        b = array[j + 1];
      }
      transformedArray[count] = (a - b) / Math.sqrt(2);
      count++;
    }

    return transformedArray;
  }

  private double[][] haarTransform(double[][][] pixelData, int channel) {
    int size = pixelData.length;

    double[][] newPixelData = new double[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        newPixelData[i][j] = pixelData[i][j][channel];
      }
    }

    while (size > 1) {

      for (int row = 0; row < size; row++) {
        double[] rowList = new double[size];
        for (int col = 0; col < size; col++) {
          rowList[col] = newPixelData[row][col];
        }

        rowList = transform(rowList);
        for (int col = 0; col < size; col++) {
          newPixelData[row][col] = rowList[col];
        }
      }

      for (int colNum = 0; colNum < size; colNum++) {
        double[] column = new double[size];
        for (int row = 0; row < size; row++) {
          column[row] = newPixelData[row][colNum];
        }
        column = transform(column);

        for (int row = 0; row < size; row++) {
          newPixelData[row][colNum] = column[row];
        }
      }

      size /= 2;
    }

    return newPixelData;
  }

  private int[][] haarInverseTransform(double[][][] transformedPixelData, int channel) {
    int size = 2;

    double[][] newPixelData = new double[transformedPixelData.length][transformedPixelData.length];
    for (int i = 0; i < transformedPixelData.length; i++) {
      for (int j = 0; j < transformedPixelData.length; j++) {
        newPixelData[i][j] = transformedPixelData[i][j][channel];
      }
    }

    while (size <= transformedPixelData.length) {

      for (int colNum = 0; colNum < size; colNum++) {
        double[] column = new double[size];
        for (int row = 0; row < size; row++) {
          column[row] = newPixelData[row][colNum];
        }

        column = inverseTransform(column);

        for (int row = 0; row < size; row++) {
          newPixelData[row][colNum] = column[row];
        }
      }

      for (int row = 0; row < size; row++) {
        double[] rowList = new double[size];
        for (int col = 0; col < size; col++) {
          rowList[col] = newPixelData[row][col];
        }

        rowList = inverseTransform(rowList);

        for (int col = 0; col < size; col++) {
          newPixelData[row][col] = rowList[col];
        }
      }

      size *= 2;
    }

    int[][] intPixelData = new int[transformedPixelData.length][transformedPixelData.length];
    for (int i = 0; i < transformedPixelData.length; i++) {
      for (int j = 0; j < transformedPixelData.length; j++) {
        intPixelData[i][j] = (int) Math.max(0, Math.min(255, newPixelData[i][j]));
      }
    }

    return intPixelData;
  }

  private double[] inverseTransform(double[] transformedArray) {
    int size = transformedArray.length;

    double[] originalArray = new double[size];


    int count = 0;
    for (int i = 0; i < (size / 2); i++) {
      double avg = transformedArray[i];
      double diff = transformedArray[i + (size / 2)];

      originalArray[count] = (avg + diff) * Math.sqrt(2) / 2;
      if (count + 1 < size) {
        originalArray[count + 1] = (avg - diff) * Math.sqrt(2) / 2;
      }

      count += 2;
    }

    return originalArray;
  }

  private void applyThreshold(double[][] pixelData, int thresholdIndex) {
    for (int i = 0; i < pixelData.length; i++) {
      for (int j = 0; j < pixelData[i].length; j++) {
        if (Math.abs(pixelData[i][j]) < thresholdIndex) {
          pixelData[i][j] = 0;
        }
      }
    }
  }

  private boolean isPowerOfTwo(int n) {
    return n > 0 && (n & (n - 1)) == 0;
  }
}