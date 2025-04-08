package view;

import controller.CommandFactory;
import controller.ImageController;
import model.Image;
import model.SimpleImage;
import model.Histogram;

import java.awt.Component;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The FeatureController class is responsible for managing the logic behind the image manipulation
 * and updating the view and model. It allows users to load and save images, apply effects, and
 * handle split-view functionality.
 */
public class FeatureController extends Component implements Features {
  final IView view;
  private final ImageController imageController;
  private String currentAlias = "loadedImage";
  String visibleAlias = currentAlias;
  private String lastEffect = "";
  boolean splitViewEnabled = false;
  private BufferedImage splitBufferedImage = null;
  private Set<String> effectsWithSplitView = new HashSet<>();
  private List<String> appliedEffects = new ArrayList<>();

  /**
   * Constructs a FeatureController with a specified ImageController and view.
   * @param imageController The controller that handles image data and transformations.
   * @param view The view interface for updating the GUI with the current image and histogram.
   */
  public FeatureController(ImageController imageController, IView view) {
    this.imageController = imageController;
    this.view = view;
    effectsWithSplitView.add("sepia");
    effectsWithSplitView.add("blur");
    effectsWithSplitView.add("sharpen");
    effectsWithSplitView.add("color_correct");
    effectsWithSplitView.add("luma-component");
    effectsWithSplitView.add("adjust-levels");
  }

  /**
   * Loads an image file into the model and updates the view with the image and its histogram.
   * Allows the user to choose an image file from the system.
   */
  @Override
  public void loadImage() {
    JFileChooser fileChooser = new JFileChooser();
    int returnValue = fileChooser.showOpenDialog(null);

    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      try {
        // Load image into the model
        imageController.loadImage(selectedFile.getAbsolutePath(), currentAlias);

        // Update view with loaded image and generated histogram
        updateImageAndHistogram(currentAlias);
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error loading image: "
                + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  /**
   * Saves the current image to a file. The image saved will either be the original image or
   * an image with effects applied, depending on the split view status.
   */
  @Override
  public void saveImage() {
    JFileChooser fileChooser = new JFileChooser();
    int returnValue = fileChooser.showSaveDialog(this);

    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File fileToSave = fileChooser.getSelectedFile();
      String filePath = fileToSave.getAbsolutePath();

      try {
        if (splitViewEnabled && splitBufferedImage != null) {
          // Save the split image
          ImageIO.write(splitBufferedImage, "png", fileToSave);
        } else {
          // Save the image using the controller
          imageController.saveImage(filePath, visibleAlias);
        }
        JOptionPane.showMessageDialog(this, "Image saved successfully at "
                + filePath, "Success", JOptionPane.INFORMATION_MESSAGE);

      } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error saving image: "
                + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  /**
   * Update the image and histogram. This method retrieves the image and histogram associated
   * with the provided alias,and then updates the image and histogram in the GUI accordingly.
   * @param alias image alias
   */
  @Override
  public void updateImageAndHistogram(String alias) {
    try {
      Image image = imageController.getImage(alias);
      Histogram histogramGenerator = new Histogram();
      SimpleImage histogramImage = (SimpleImage) histogramGenerator.execute(image);

      view.updateImage(convertToBufferedImage((SimpleImage) image));
      view.updateHistogram(convertToBufferedImage(histogramImage));
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(null, "Error updating view: "
              + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Apply the given image effect to an image.
   * This method takes the name of an effect and applies
   * that effect to the current image, updating the view with the modified image. The effects
   * are applied sequentially, modifying the image progressively.
   * @param effect image effect to use
   * @throws IOException If an error occurs while applying the effect or processing the image.
   */
  @Override
  public void applyEffect(String effect) throws IOException {
    String effectAlias = visibleAlias + "_" + effect;
    System.out.println("Effect alias: " + effectAlias);
    String[] params = { effect, visibleAlias, effectAlias };
    switch (effect) {
      case "sepia":
        CommandFactory.applySepia(params);
        break;
      case "blur":
        CommandFactory.applyBlur(params);
        break;
      case "sharpen":
        CommandFactory.applySharpen(params);
        break;
      case "horizontal-flip","vertical-flip":
        CommandFactory.flip(params);
        break;
      case "luma-component":
        CommandFactory.applyGrey(params);
        break;
      case "red-component","green-component","blue-component":
        CommandFactory.applyColor(params);
        break;
      case "color-correct":
        CommandFactory.applyCorrect(params);
        break;
      default:
        JOptionPane.showMessageDialog(this,
                "Effect not supported: " + effect,
                "Error", JOptionPane.ERROR_MESSAGE);
    }

    lastEffect = effect;
    appliedEffects.add(effect);
    visibleAlias = effectAlias;

    // Update the view after applying the effect
    view.updateImageAndHistogram(visibleAlias);
  }

  /**
   * Applying the adjust levels effect.
   * takes the black, mid, and white values to apply to an image. This will apply the effect
   * then update the visible image in the GUI as well as the corresponding histogram.
   * @param effect image effect to use
   * @param b black value
   * @param m mid value
   * @param w white value
   * @throws IOException if an error occurs if there is an error when applying
   */
  @Override
  public void applyLevels(String effect, int b, int m, int w) throws IOException {
    String effectAlias = visibleAlias + "_" + effect;
    String[] params = { "adjust-levels", String.valueOf(b), String.valueOf(m), String.valueOf(w),
            visibleAlias, effectAlias };
    CommandFactory.applyLevels(params);

    lastEffect = "adjust-levels";
    appliedEffects.add(lastEffect);
    visibleAlias = effectAlias;

    // Update the view after applying the effect
    view.updateImageAndHistogram(visibleAlias);
  }

  /**
   * Apply compression effect to an image.
   * takes the effect name and percentage to compress by. It will then update the visible image
   * in the GUI as well as the corresponding histogram.
   * @param effectAlias effect
   * @param percent to compress image
   * @throws IOException if there is an error when applying
   */
  @Override
  public void applyCompression(String effectAlias, int percent) throws IOException {
    String effect = visibleAlias + "_" + effectAlias;
    String[] params = { "compression", String.valueOf(percent), visibleAlias, effect };
    CommandFactory.applyCompression(params);

    lastEffect = "compression";
    appliedEffects.add(lastEffect);
    visibleAlias = effect;

    // Update the view after applying the effect
    view.updateImageAndHistogram(visibleAlias);

  }

  /**
   * Clears all applied effects and restores the image to its original state.
   * This method removes all previously applied effects and resets the image to the state it was
   * in when first loaded into the system.
   */
  @Override
  public void clearEffects() {
    appliedEffects.clear();

    visibleAlias = currentAlias;

    updateImageAndHistogram(currentAlias);

    splitViewEnabled = false;
    splitBufferedImage = null;

    JOptionPane.showMessageDialog(null, "Effects cleared successfully!",
            "Success", JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * Checks if the split view is currently enabled.
   * This method checks whether the application is currently in split view mode, where the image
   * is displayed with part of it showing the original image and the other part showing the effect
   * applied to the image.
   * @return true if the split view is enabled, false otherwise.
   */
  @Override
  public boolean checkSplit() {
    return splitViewEnabled;
  }

  /**
   * Updates the split view based on the specified percentage.
   * This method updates the display to show a split view of the image, with one side showing
   * the original image and the other side showing the modified image (after an effect is applied).
   * The splitPercentage determines where the image is divided.
   * @param splitPercentage The percentage of the image width at which to split the view.
   * @throws IOException If an error occurs while updating the split view.
   */
  @Override
  public void updateSplit(int splitPercentage) throws IOException {
    if (splitViewEnabled) {
      applySplitViewEffect(splitPercentage);
    }
  }

  private void applySplitViewEffect(int splitPercentage) throws IOException {
    if (!effectsWithSplitView.contains(lastEffect)) {
      JOptionPane.showMessageDialog(null,
              "This effect does not support split view", "Error",
              JOptionPane.ERROR_MESSAGE);
      return;
    }

    Image originalImage = imageController.getImage(currentAlias);
    Image effectImage = imageController.getImage(visibleAlias);

    if (originalImage == null || effectImage == null) {
      return;
    }

    BufferedImage originalBufferedImage = convertToBufferedImage((SimpleImage) originalImage);
    BufferedImage effectBufferedImage = convertToBufferedImage((SimpleImage) effectImage);

    int width = originalBufferedImage.getWidth();
    int height = originalBufferedImage.getHeight();

    int splitX = (int) (width * splitPercentage / 100.0);

    BufferedImage splitImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        if (x > splitX) {
          splitImage.setRGB(x, y, originalBufferedImage.getRGB(x, y));
        } else {
          splitImage.setRGB(x, y, effectBufferedImage.getRGB(x, y));
        }
      }
    }

    splitBufferedImage = splitImage;
    view.updateImage(splitImage);
    Histogram histogramGenerator = new Histogram();
    SimpleImage histogramImage =
            (SimpleImage) histogramGenerator.execute(convertToSimpleImage(splitImage));
    view.updateHistogram(convertToBufferedImage(histogramImage));
  }


  private BufferedImage convertToBufferedImage(SimpleImage image) {
    int width = image.getWidth();
    int height = image.getHeight();
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int r = image.getPixel(row, col, 0);
        int g = image.getPixel(row, col, 1);
        int b = image.getPixel(row, col, 2);
        int rgb = (r << 16) | (g << 8) | b;
        bufferedImage.setRGB(col, row, rgb);
      }
    }
    return bufferedImage;
  }

  private SimpleImage convertToSimpleImage(BufferedImage bufferedImage) {
    int width = bufferedImage.getWidth();
    int height = bufferedImage.getHeight();
    SimpleImage simpleImage = new SimpleImage("name",height, width);

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int rgb = bufferedImage.getRGB(col, row);
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = rgb & 0xFF;
        simpleImage.setPixel(row, col, r,g,b);  // Red channel

      }
    }
    return simpleImage;
  }

}