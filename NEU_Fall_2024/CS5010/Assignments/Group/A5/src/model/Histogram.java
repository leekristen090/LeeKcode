package model;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * This class holds logic to create histogram representations of r, g, b values of a given image.
 * From canvas description:
 * A histogram is a table of (value,frequency) entries. Consider a greyscale image storing 256
 * distinct greyscale values (i.e. uses 8-bits per pixel). One can count the number of pixels with
 * value 0, 1, ...255 and create a table of 256 entries. This would be a histogram of the image
 * showing 256 distinct levels. If the image has color, we create histograms for each component.
 */
public class Histogram {

  private int height = 256;
  private int width = 256;

  /**
   * Execute histogram logic.
   * @param image given image
   * @return histogram of given image
   */
  public Image execute(Image image) {
    // arrays to hold pixel stuff
    int[] redHistogram = new int[256];
    int[] greenHistogram = new int[256];
    int[] blueHistogram = new int[256];

    // pixel colors into histogram
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        int red = image.getPixel(row, col, 0);
        int green = image.getPixel(row, col, 1);
        int blue = image.getPixel(row, col, 2);

        redHistogram[red]++;
        greenHistogram[green]++;
        blueHistogram[blue]++;
      }
    }

    // make histograms fit into 256 pixels
    int maxRed = findMaxValue(redHistogram);
    int maxGreen = findMaxValue(greenHistogram);
    int maxBlue = findMaxValue(blueHistogram);

    // Create a BufferedImage for the histogram
    BufferedImage histogramImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    // Fill background with white
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        histogramImage.setRGB(x, y, Color.WHITE.getRGB());
      }
    }

    // Draw grid lines
    drawGrid(histogramImage, width, height);

    // Plot histogram lines for each color component
    drawHistogramLine(histogramImage, redHistogram, maxRed, Color.RED);
    drawHistogramLine(histogramImage, greenHistogram, maxGreen, Color.GREEN);
    drawHistogramLine(histogramImage, blueHistogram, maxBlue, Color.BLUE);

    // Create and store the histogram image as ConcreteImage
    Image histogramResult = new SimpleImage("", height, width);
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int rgb = histogramImage.getRGB(col, row);
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = rgb & 0xFF;
        histogramResult.setPixel(row, col, r, g, b);
      }
    }

    // add to alias array
    //controller.addImage(histogramResult, newAlias);
    return histogramResult;
  }

  // Helper method to find the maximum value in a histogram array
  private int findMaxValue(int[] histogram) {
    int max = 0;
    for (int value : histogram) {
      if (value > max) {
        max = value;
      }
    }
    return max;
  }

  // Draws a grid on the histogram image
  private void drawGrid(BufferedImage image, int width, int height) {
    // Vertical lines (every 10 pixels)
    for (int x = 0; x < width; x += 10) {
      for (int y = 0; y < height; y++) {
        image.setRGB(x, y, Color.LIGHT_GRAY.getRGB());
      }
    }

    // Horizontal lines (every 50 pixels)
    for (int y = 0; y < height; y += 50) {
      for (int x = 0; x < width; x++) {
        image.setRGB(x, y, Color.LIGHT_GRAY.getRGB());
      }
    }
  }

  // Draws a histogram line for a given color component
  private void drawHistogramLine(BufferedImage image, int[] histogram, int max, Color color) {
    for (int x = 1; x < histogram.length; x++) {
      int y1 = (int) (height - ((double) histogram[x - 1] / max) * (height - 20));
      int y2 = (int) (height - ((double) histogram[x] / max) * (height - 20));

      // Draw the line connecting the two points
      drawLine(image, x - 1, y1, x, y2, color);
    }
  }

  // Draws a line between two points
  private void drawLine(BufferedImage image, int x1, int y1, int x2, int y2, Color color) {
    int dx = Math.abs(x2 - x1);
    int dy = Math.abs(y2 - y1);
    int sx = (x1 < x2) ? 1 : -1;
    int sy = (y1 < y2) ? 1 : -1;
    int err = dx - dy;

    while (true) {
      // Check bounds before setting the pixel
      if (x1 >= 0 && x1 < image.getWidth() && y1 >= 0 && y1 < image.getHeight()) {
        image.setRGB(x1, y1, color.getRGB());
      }

      if (x1 == x2 && y1 == y2) {
        break;
      }
      int err2 = err * 2;
      if (err2 > -dy) {
        err -= dy;
        x1 += sx;
      }
      if (err2 < dx) {
        err += dx;
        y1 += sy;
      }
    }
  }

}
