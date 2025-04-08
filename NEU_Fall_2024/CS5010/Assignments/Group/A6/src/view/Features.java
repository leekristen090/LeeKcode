package view;

import java.io.IOException;

/**
 * This is the Features interface which holds methods for image effects.
 */
public interface Features {

  /**
   * Load an image into the gui from a filepath the user chooses. This method uses a file chooser
   * to let the user select an image file. The selected image is then loaded into the system
   * and displayed in the GUI.
   */
  void loadImage();

  /**
   * Save the visible image from the gui to a user chosen file path.
   * This method uses a file chooser to let the user specify where to save the image.
   * The visible image (after any effects are applied) is saved to the specified file.
   */
  void saveImage();

  /**
   * Update the image and histogram. This method retrieves the image and histogram associated
   * with the provided alias,and then updates the image and histogram in the GUI accordingly.
   * @param alias image alias
   */
  void updateImageAndHistogram(String alias);

  /**
   * Apply the given image effect to an image.
   * This method takes the name of an effect (such as "Sepia Effect" or "Blur Effect") and applies
   * that effect to the current image, updating the view with the modified image. The effects
   * are applied sequentially, modifying the image progressively.
   * @param effect image effect to use
   * @throws IOException If an error occurs while applying the effect or processing the image.
   */
  void applyEffect(String effect) throws IOException;

  /**
   * This applies the levels adjust affect to a given image,
   * @param effect image effect to use
   * @param b black value
   * @param m mid value
   * @param w white value
   * @throws IOException if an error occurs when applying the effect
   */
  void applyLevels(String effect, int b, int m, int w) throws IOException;

  /**
   * This applies the compression effect to a given image.
   * @param effectAlias effect
   * @param percent to compress image
   * @throws IOException if an error occurs when applying the effect
   */
  void applyCompression(String effectAlias, int percent) throws IOException;

  /**
   * Clears all applied effects and restores the image to its original state.
   * This method removes all previously applied effects and resets the image to the state it was
   * in when first loaded into the system.
   */
  void clearEffects();

  /**
   * Checks if the split view is currently enabled.
   * This method checks whether the application is currently in split view mode, where the image
   * is displayed with part of it showing the original image and the other part showing the effect
   * applied to the image.
   * @return true if the split view is enabled, false otherwise.
   */
  boolean checkSplit();

  /**
   * Updates the split view based on the specified percentage.
   * This method updates the display to show a split view of the image, with one side showing
   * the original image and the other side showing the modified image (after an effect is applied).
   * The splitPercentage determines where the image is divided.
   * @param splitPercentage The percentage of the image width at which to split the view.
   * @throws IOException If an error occurs while updating the split view.
   */
  void updateSplit(int splitPercentage) throws IOException;

}