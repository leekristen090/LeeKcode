package view;

import java.awt.image.BufferedImage;

/**
 * This is the IView interface which holds methods for updating the GUI view that the user sees.
 */
public interface IView {

  /**
   * Update the image in the gui.
   * This method is used to refresh the image in the view, typically after a new image is loaded
   * or an effect is applied.
   * @param image given image
   */
  void updateImage(BufferedImage image);

  /**
   * Update the histogram in the gui.
   * This method is used to refresh the histogram view, which visually represents the distribution
   * of pixel intensities in the image.
   * @param histogram the histogram
   */
  void updateHistogram(BufferedImage histogram);

  /**
   * This method is used to update both the image and the histogram at the same time, using
   * the alias to fetch the relevant image and histogram data for display.
   * @param alias image alias
   */
  void updateImageAndHistogram(String alias);
}