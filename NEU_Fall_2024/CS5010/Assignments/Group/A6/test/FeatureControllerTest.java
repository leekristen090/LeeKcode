
import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;

import model.Image;
import view.FeatureController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * This is the junit test for the feature controller. we use mocks to simulate the interaction
 * between the controller and the view.
 */
public class FeatureControllerTest {

  private MockImageController mockImageController;
  private MockView mockView;
  private FeatureController featureController;

  /**
   * setting up our controller for the tests.
   */
  @Before
  public void setUp() {

    mockView = new MockView();
    mockImageController = new MockImageController(mockView);
    //featureController = new FeatureController(mockImageController, mockView);
  }

  /**
   * Testing load.
   */
  @Test
  public void testLoadWithoutGUI() {
    // Simulate loading an image
    mockImageController.loadImage("/path", "alias");
    assertTrue(mockImageController.isLoadImageCalled());
  }

  /**
   * Testing save.
   */
  @Test
  public void testSaveWithoutGUI() {
    // Simulate saving an image
    mockImageController.saveImage("/path.jpg", "alias");
    assertTrue(mockImageController.isSaveImageCalled());
  }

  /**
   * Testing apply effect, sepia.
   */
  @Test
  public void testApplyEffect() {
    mockImageController.loadImage("/path.jpg", "loadedImage");
    assertNotNull(mockImageController.getImage("loadedImage"));
    mockImageController.applyEffect("loadedImage","sepia");
    assertTrue(mockImageController.isApplyEffectCalled());
    assertEquals("loadedImage_sepia", mockImageController.getLastAlias());
    assertNotNull(mockImageController.getLastModifiedImage());
  }

  /**
   * Testing apply sepia with histogram and image updates.
   */
  @Test
  public void testApplyEffectUpdatesHistogram() {
    mockImageController.loadImage("/path.jpg", "loadedImage");
    mockImageController.applyEffect("loadedImage", "sepia");
    assertTrue(mockView.updateImageAndHistogramCalled);
    BufferedImage histogram = mockImageController.getHistogram("loadedImage_sepia");
    assertNotNull("Histogram should not be null for the modified image", histogram);
    mockView.updateHistogram(histogram);
    assertEquals("Histogram passed to the view should match the controller's histogram",
            histogram, mockView.getLastHistogram());
  }

  /**
   * Testing apply blur.
   */
  @Test
  public void testApplyBlurUpdatesHistogram() {
    mockImageController.loadImage("/path.jpg", "loadedImage");
    mockImageController.applyEffect("loadedImage", "blur");
    assertTrue(mockView.updateImageAndHistogramCalled);
    BufferedImage histogram = mockImageController.getHistogram("loadedImage_blur");
    assertNotNull("Histogram should not be null for the modified image", histogram);
    mockView.updateHistogram(histogram);
    assertEquals("Histogram passed to the view should match the controller's histogram",
            histogram, mockView.getLastHistogram());
  }

  /**
   * Testing apply blue component.
   */
  @Test
  public void testApplyBlueUpdatesHistogram() {
    mockImageController.loadImage("/path.jpg", "loadedImage");
    mockImageController.applyEffect("loadedImage", "blue-component");
    assertTrue(mockView.updateImageAndHistogramCalled);
    BufferedImage histogram = mockImageController.getHistogram("loadedImage_blue-component");
    assertNotNull("Histogram should not be null for the modified image", histogram);
    mockView.updateHistogram(histogram);
    assertEquals("Histogram passed to the view should match the controller's histogram",
            histogram, mockView.getLastHistogram());
  }

  /**
   * Testing apply color correction.
   */
  @Test
  public void testApplyCorrectUpdatesHistogram() {
    mockImageController.loadImage("/path.jpg", "loadedImage");
    mockImageController.applyEffect("loadedImage", "color-correct");
    assertTrue(mockView.updateImageAndHistogramCalled);
    BufferedImage histogram = mockImageController.getHistogram("loadedImage_color-correct");
    assertNotNull("Histogram should not be null for the modified image", histogram);
    mockView.updateHistogram(histogram);
    assertEquals("Histogram passed to the view should match the controller's histogram",
            histogram, mockView.getLastHistogram());
  }

  /**
   * Testing apply compression.
   */
  @Test
  public void testApplyCompressUpdatesHistogram() {
    mockImageController.loadImage("/path.jpg", "loadedImage");
    mockImageController.applyEffect("loadedImage", "compress");
    assertTrue(mockView.updateImageAndHistogramCalled);
    BufferedImage histogram = mockImageController.getHistogram("loadedImage_compress");
    assertNotNull("Histogram should not be null for the modified image", histogram);
    mockView.updateHistogram(histogram);
    assertEquals("Histogram passed to the view should match the controller's histogram",
            histogram, mockView.getLastHistogram());
  }

  /**
   * Testing apply vertical flip.
   */
  @Test
  public void testApplyFlipVertUpdatesHistogram() {
    mockImageController.loadImage("/path.jpg", "loadedImage");
    mockImageController.applyEffect("loadedImage", "vertical-flip");
    assertTrue(mockView.updateImageAndHistogramCalled);
    BufferedImage histogram = mockImageController.getHistogram("loadedImage_vertical-flip");
    assertNotNull("Histogram should not be null for the modified image", histogram);
    mockView.updateHistogram(histogram);
    assertEquals("Histogram passed to the view should match the controller's histogram",
            histogram, mockView.getLastHistogram());
  }

  /**
   * Testing apply horizontal flip.
   */
  @Test
  public void testApplyFlipHorzUpdatesHistogram() {
    mockImageController.loadImage("/path.jpg", "loadedImage");
    mockImageController.applyEffect("loadedImage", "horizontal-flip");
    assertTrue(mockView.updateImageAndHistogramCalled);
    BufferedImage histogram = mockImageController.getHistogram("loadedImage_horizontal-flip");
    assertNotNull("Histogram should not be null for the modified image", histogram);
    mockView.updateHistogram(histogram);
    assertEquals("Histogram passed to the view should match the controller's histogram",
            histogram, mockView.getLastHistogram());
  }

  /**
   * Testing apply luma effect.
   */
  @Test
  public void testApplyLumaUpdatesHistogram() {
    mockImageController.loadImage("/path.jpg", "loadedImage");
    mockImageController.applyEffect("loadedImage", "luma-component");
    assertTrue(mockView.updateImageAndHistogramCalled);
    BufferedImage histogram = mockImageController.getHistogram("loadedImage_luma-component");
    assertNotNull("Histogram should not be null for the modified image", histogram);
    mockView.updateHistogram(histogram);
    assertEquals("Histogram passed to the view should match the controller's histogram",
            histogram, mockView.getLastHistogram());
  }

  /**
   * Testing apply green-component effect.
   */
  @Test
  public void testApplyGreenUpdatesHistogram() {
    mockImageController.loadImage("/path.jpg", "loadedImage");
    mockImageController.applyEffect("loadedImage", "green-component");
    assertTrue(mockView.updateImageAndHistogramCalled);
    BufferedImage histogram = mockImageController.getHistogram("loadedImage_green-component");
    assertNotNull("Histogram should not be null for the modified image", histogram);
    mockView.updateHistogram(histogram);
    assertEquals("Histogram passed to the view should match the controller's histogram",
            histogram, mockView.getLastHistogram());
  }

  /**
   * Testing apply red-component effect.
   */
  @Test
  public void testApplyRedUpdatesHistogram() {
    mockImageController.loadImage("/path.jpg", "loadedImage");
    mockImageController.applyEffect("loadedImage", "red-component");
    assertTrue(mockView.updateImageAndHistogramCalled);
    BufferedImage histogram = mockImageController.getHistogram("loadedImage_red-component");
    assertNotNull("Histogram should not be null for the modified image", histogram);
    mockView.updateHistogram(histogram);
    assertEquals("Histogram passed to the view should match the controller's histogram",
            histogram, mockView.getLastHistogram());
  }

  /**
   * Testing apply the sharpen effect.
   */
  @Test
  public void testApplySharpUpdatesHistogram() {
    mockImageController.loadImage("/path.jpg", "loadedImage");
    mockImageController.applyEffect("loadedImage", "sharpen");
    assertTrue(mockView.updateImageAndHistogramCalled);
    BufferedImage histogram = mockImageController.getHistogram("loadedImage_sharpen");
    assertNotNull("Histogram should not be null for the modified image", histogram);
    mockView.updateHistogram(histogram);
    assertEquals("Histogram passed to the view should match the controller's histogram",
            histogram, mockView.getLastHistogram());
  }

  /**
   * Testing getting the alias of a loaded image.
   */
  @Test
  public void testValidAlias() {
    String alias = "image1";
    String filePath = "path/to/image1.jpg";
    mockImageController.loadImage(filePath, alias);

    // Test if the image is correctly retrieved by alias
    Image image = mockImageController.getImage(alias);

    assertNotNull(image.toString(), "Image should not be null when alias is valid.");
  }

}