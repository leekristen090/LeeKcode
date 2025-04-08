
package model;

import controller.Image;
import controller.Pixel;
import controller.PixelImpl;

/**
 * This class contains the implementation for dithering manipulation. This operation breaks down
 * an image with many colors into an image composed of dots of just a few colors, known
 * as dithering. It converts the image to grayscale using the intensity of each pixel and
 * applies the Floyd-Steinberg dithering algorithm.
 */
public class Dithering implements ImageProcessing {

  @Override
  public Image[] process(String[] args, ImageDB images) throws IllegalArgumentException {
    if (args.length != 3) {
      throw new IllegalArgumentException("dithering command requires an image name and output.");
    }

    // Fetch the input image
    Image obj = images.get(args[1]);
    int width = obj.getWidth();
    int height = obj.getHeight();

    // Convert the image to grayscale
    Image grayscaleImage = convertIntensity(obj, width, height);

    // Create an array to hold pixel intensity values
    double[][] intensity = new double[height][width];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Pixel pixel = grayscaleImage.getPixel(x, y);
        intensity[y][x] = pixel.getRed(); // Since it's grayscale, all channels are the same
      }
    }

    // Apply Floyd-Steinberg Dithering
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        double oldColor = intensity[y][x];
        int newColor = oldColor < 128 ? 0 : 255; // Closer to 0 or 255?
        double error = oldColor - newColor;

        // Update the pixel to the new color
        Pixel newPixel = new PixelImpl(newColor, newColor, newColor);
        grayscaleImage.setPixel(x, y, newPixel);

        // Diffuse the error to neighboring pixels
        if (x + 1 < width) {
          intensity[y][x + 1] += (7.0 / 16.0) * error;
        }
        if (y + 1 < height) {
          if (x - 1 >= 0) {
            intensity[y + 1][x - 1] += (3.0 / 16.0) * error;
          }
          intensity[y + 1][x] += (5.0 / 16.0) * error;
          if (x + 1 < width) {
            intensity[y + 1][x + 1] += (1.0 / 16.0) * error;
          }
        }
      }
    }

    // Create a new dithered image
    Image ditheredImage = obj.createImage(width, height);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        ditheredImage.setPixel(x, y, grayscaleImage.getPixel(x, y));
      }
    }

    return new Image[]{ditheredImage};
  }

  /**
   * Converts the input image to grayscale using intensity.
   *
   * @param obj    the input image
   * @param width  the image width
   * @param height the image height
   * @return the grayscale image
   */
  private Image convertIntensity(Image obj, int width, int height) {
    Image intensityImage = obj.createImage(width, height);
    Intensity intensity = new Intensity();

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Pixel pixel = obj.getPixel(x, y);

        // Get the grayscale values
        double[] grayscale = intensity.getChannel(pixel);
        Pixel grayscalePixel = new PixelImpl(grayscale[0], grayscale[1], grayscale[2]);
        intensityImage.setPixel(x, y, grayscalePixel);
      }
    }

    return intensityImage;
  }

  @Override
  public String[] getName(String[] args) {
    return new String[]{args[2]};
  }
}
