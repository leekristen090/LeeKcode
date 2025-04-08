
import java.awt.image.BufferedImage;

import view.IView;

/**
 * This is the mock view class which simulates the view class for junit tests.
 */
public class MockView implements IView {

  public boolean updateImageCalled = false;
  public boolean updateHistogramCalled = false;
  public boolean updateImageAndHistogramCalled = false;

  private BufferedImage lastImage;
  private BufferedImage lastHistogram;
  private String lastAlias;

  /**
   * Simulate updating an image in the GUI.
   * @param image given image
   */
  @Override
  public void updateImage(BufferedImage image) {
    updateImageCalled = true;
    this.lastImage = image;
  }

  /**
   * Simulate updating the histogram in the GUI.
   * @param histogram the histogram
   */
  @Override
  public void updateHistogram(BufferedImage histogram) {
    updateHistogramCalled = true;
    this.lastHistogram = histogram;
  }

  /**
   * Simulate updating the image and histogram in the GUI.
   * @param alias image alias
   */
  @Override
  public void updateImageAndHistogram(String alias) {
    updateImageAndHistogramCalled = true;
    this.lastAlias = alias;
  }

  /**
   * get the last image to update.
   * @return buffered image
   */
  public BufferedImage getLastImage() {
    return lastImage;
  }

  /**
   * Get the last histogram to update.
   * @return the histogram
   */
  public BufferedImage getLastHistogram() {
    return lastHistogram;
  }

  /**
   * Get the last alias and update that.
   * @return the alias
   */
  public String getLastAlias() {
    return lastAlias;
  }
}

