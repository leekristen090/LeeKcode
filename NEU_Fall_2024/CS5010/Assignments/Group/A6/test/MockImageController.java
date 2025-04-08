
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import model.Image;
import model.SimpleImage;
import controller.ImageController;
import view.IView;

/**
 * This is the mock image controller class which simulates the use of the image controller class
 * for junit test.
 */
public class MockImageController extends ImageController {
  IView view;

  private final Map<String, Image> images = new HashMap<>();
  private final Map<String, BufferedImage> histograms = new HashMap<>();
  public boolean loadImageCalled = false;
  public boolean saveImageCalled = false;
  public boolean applyEffectCalled = false;

  private String lastAlias;

  /**
   * Construct the mock image controller.
   * @param view the view
   */
  public MockImageController(IView view) {
    this.view = view;
  }

  /**
   * Load an image from a given file path and associate it with the given alias.
   * @param path image file path
   * @param alias alias for image
   */
  @Override
  public void loadImage(String path, String alias) {
    loadImageCalled = true;
    lastAlias = alias;
    // Simulate loading an image by creating a simple mock object
    images.put(alias, new SimpleImage(alias, 100, 100));
    histograms.put(alias, createMockHistogram(alias));
  }

  /**
   * Get the image with the given alias.
   * @param alias alias of image to retrieve
   * @return the image corresponding to the alias
   */
  @Override
  public Image getImage(String alias) {
    return images.get(alias);
  }

  /**
   * save an image that corresponds with the given alias and save it to the given file path.
   * @param path given image file path
   * @param alias alias to save
   */
  @Override
  public void saveImage(String path, String alias) {
    saveImageCalled = true;
    images.get(alias);
  }

  /**
   * Simulate applying an image effect on ann image.
   * @param alias image alias
   * @param effect effect to apply
   */
  public void applyEffect(String alias, String effect) {
    applyEffectCalled = true;

    //System.out.println("Applying effect: " + effect + " on alias: " + alias);
    if (!images.containsKey(alias)) {
      System.out.println("Image not found for alias: " + alias);
    }

    // Simulate applying an effect
    Image original = images.get(alias);
    if (original == null) {
      throw new IllegalArgumentException("Original image not found with alias: " + alias);
    }

    String newAlias = alias + "_" + effect;
    Image modifiedImage = new SimpleImage(newAlias, original.getWidth(), original.getHeight());
    images.put(newAlias, modifiedImage);
    histograms.put(newAlias, createMockHistogram(newAlias));
    lastAlias = newAlias;
    view.updateImageAndHistogram(newAlias);
  }

  /**
   * Has loadImage() been called.
   * @return true if loadImage() was called, false otherwise
   */
  public boolean isLoadImageCalled() {
    return loadImageCalled;
  }

  /**
   * Has saveImage() been called.
   * @return true if saveImage() was called, false otherwise
   */
  public boolean isSaveImageCalled() {
    return saveImageCalled;
  }

  /**
   * Has applyEffect() been called.
   * @return true if applyEffect() was called, false otherwise
   */
  public boolean isApplyEffectCalled() {
    return applyEffectCalled;
  }

  /**
   * Get the las alias.
   * @return the alias
   */
  public String getLastAlias() {
    return lastAlias;
  }

  /**
   * Is the image with the given alias stored in the hashmap.
   * @param alias alias to find
   * @return true if its been stored, false otherwise
   */
  public boolean hasImage(String alias) {
    return images.containsKey(alias);
  }

  /**
   * Get the last modified image.
   * @return the image that was modified
   */
  public Image getLastModifiedImage() {
    return images.get(lastAlias);
  }

  /**
   * Get the histogram associated with the image with the given alias.
   * @param alias image alias to find
   * @return the histogram
   */
  public BufferedImage getHistogram(String alias) {
    return histograms.get(alias);
  }

  private BufferedImage createMockHistogram(String alias) {
    // Simulate a mock histogram as a BufferedImage
    BufferedImage histogram = new BufferedImage(256, 100, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < 256; x++) {
      for (int y = 0; y < 100; y++) {
        int intensity = alias.hashCode() % 256; // Mock intensity based on alias
        histogram.setRGB(x, y, (intensity << 16) | (intensity << 8) | intensity);
      }
    }
    return histogram;
  }
}
