package model;

/**
 * This is the class that holds the logic for color correction using the histogram peaks.
 * From canvas description:
 * Color correction is the process of aligning the histogram peaks of individual channels. We can do
 * this as follows.
 * First we find the peaks (and the values at which they occur) of each channel in the histogram.
 * Then we compute the average value across peaks. This is the position on the horizontal axes where
 * the peaks should occur in the result. Then we find how much to offset each channel's values so
 * that their histogram peak occurs at this average value. Finally, we change the values in each
 * channel in the image accordingly.
 */
public class ColorCorrect {

  /**
   * execute the logic to do color correction on an image using histogram peaks.
   * @param image given image
   * @param splitPercentage optional split preview percentage
   * @return color corrected image
   */
  public Image execute(Image image, Integer splitPercentage) {
    int splitPoint = splitPercentage != null
            ? (image.getWidth() * splitPercentage / 100) : image.getWidth();

    // Calculate histograms for each channel
    int[] redHistogram = calculateHistogram(image, 0);
    int[] greenHistogram = calculateHistogram(image, 1);
    int[] blueHistogram = calculateHistogram(image, 2);

    // Find peaks for each color
    int redPeak = findPeak(redHistogram);
    int greenPeak = findPeak(greenHistogram);
    int bluePeak = findPeak(blueHistogram);

    // avg peak position
    int[] peaks = {redPeak, greenPeak, bluePeak};
    int averagePeak = computeAveragePeakPosition(peaks);

    // offsets
    int[] offsets = calculateOffsets(peaks, averagePeak);

    // Adjust pixel values
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        if (col < splitPoint) {
          int red = image.getPixel(row, col, 0);
          int green = image.getPixel(row, col, 1);
          int blue = image.getPixel(row, col, 2);

          red = Math.max(0, Math.min(255, red + offsets[0]));
          green = Math.max(0, Math.min(255, green + offsets[1]));
          blue = Math.max(0, Math.min(255, blue + offsets[2]));

          image.setPixel(row, col, red, green, blue);
        }
      }
    }

    return image;
  }

  private int[] calculateHistogram(Image image, int channel) {
    int[] histogram = new int[256];

    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        int value = image.getPixel(row, col, channel);
        if (value >= 0 && value < 256) {
          histogram[value]++;
        }
      }
    }

    return histogram;
  }

  // finding meaningful peaks
  private int findPeak(int[] histogram) {
    int peakIndex = -1;
    int maxValue = 0;

    for (int i = 10; i < 245; i++) {  // Ignore extremes
      if (histogram[i] > maxValue) {
        maxValue = histogram[i];
        peakIndex = i;
      }
    }

    return peakIndex;
  }

  private int computeAveragePeakPosition(int[] peaks) {
    int sum = 0;
    int count = 0;

    for (int peak : peaks) {
      if (peak != -1) {
        sum += peak;
        count++;
      }
    }

    return count > 0 ? sum / count : 0;
  }

  private int[] calculateOffsets(int[] peaks, int averagePeak) {
    int[] offsets = new int[3]; // For RGB
    for (int i = 0; i < peaks.length; i++) {
      offsets[i] = averagePeak - peaks[i];
    }
    return offsets;
  }
}