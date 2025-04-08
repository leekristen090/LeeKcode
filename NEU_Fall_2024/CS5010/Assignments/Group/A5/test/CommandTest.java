import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import model.ColorCorrect;
import model.Histogram;
import model.Image;
import model.Levels;
import model.Compression;
import model.SimpleImage;

/**
 * This is our junit test class to test image manipulations.
 */
public class CommandTest {

  private SimpleImage newImage;

  /**
   * Creates a 2x2 image to ensure the commands work properly.
   */
  @Before
  public void setup() {
    newImage = new SimpleImage("test", 2, 2);

    newImage.setPixel(0, 0, 255, 0, 0);
    newImage.setPixel(0, 1, 0, 255, 0);
    newImage.setPixel(1, 0, 0, 0, 255);
    newImage.setPixel(1, 1, 255, 255, 0);
  }

  /**
   * Checks to ensure the colorCorrect class works.
   */
  @Test
  public void colorCorrect() {
    ColorCorrect colorCorrect = new ColorCorrect();
    SimpleImage result = (SimpleImage) colorCorrect.execute(newImage, null);

    int red1 = result.getPixel(0, 0, 0);
    int green1 = result.getPixel(0, 0, 1);
    int blue1 = result.getPixel(0, 0, 2);

    int red2 = result.getPixel(0, 1, 0);
    int green2 = result.getPixel(0, 1, 1);
    int blue2 = result.getPixel(0, 1, 2);

    int red3 = result.getPixel(1, 0, 0);
    int green3 = result.getPixel(1, 0, 1);
    int blue3 = result.getPixel(1, 0, 2);

    int red4 = result.getPixel(1, 1, 0);
    int green4 = result.getPixel(1, 1, 1);
    int blue4 = result.getPixel(1, 1, 2);

    assertEquals(255, red1);
    assertEquals(255, green2);
    assertEquals(255, blue3);
    assertEquals(255, red4);

    assertTrue("Red value out of bounds", red1 >= 0 && red1 <= 255);
    assertTrue("Green value out of bounds", green1 >= 0 && green1 <= 255);
    assertTrue("Blue value out of bounds", blue1 >= 0 && blue1 <= 255);

    assertTrue("Red value out of bounds", red2 >= 0 && red2 <= 255);
    assertTrue("Green value out of bounds", green2 >= 0 && green2 <= 255);
    assertTrue("Blue value out of bounds", blue2 >= 0 && blue2 <= 255);

    assertTrue("Red value out of bounds", red3 >= 0 && red3 <= 255);
    assertTrue("Green value out of bounds", green3 >= 0 && green3 <= 255);
    assertTrue("Blue value out of bounds", blue3 >= 0 && blue3 <= 255);

    assertTrue("Red value out of bounds", red4 >= 0 && red4 <= 255);
    assertTrue("Green value out of bounds", green4 >= 0 && green4 <= 255);
    assertTrue("Blue value out of bounds", blue4 >= 0 && blue4 <= 255);
  }

  /**
   * Checks to ensure the histogram class works.
   */
  @Test
  public void histogram() {
    Histogram histogram = new Histogram();

    Image result = histogram.execute(newImage);

    assertNotNull("Histogram result should not be null", result);

    assertEquals("Histogram image width should be 256", 256, result.getWidth());
    assertEquals("Histogram image height should be 256", 256, result.getHeight());

    int redHistogramPixel = result.getPixel(10, 10, 0);
    int greenHistogramPixel = result.getPixel(10, 10, 1);
    int blueHistogramPixel = result.getPixel(10, 10, 2);

    assertTrue("Red histogram should have a value", redHistogramPixel > 0);
    assertTrue("Green histogram should have a value", greenHistogramPixel > 0);
    assertTrue("Blue histogram should have a value", blueHistogramPixel > 0);
  }

  /**
   * Checks to ensure the compression class works with a percent of 0.
   */
  @Test
  public void compressZero() {
    Compression compression = new Compression(0);

    SimpleImage result = (SimpleImage) compression.execute(newImage);

    assertEquals("Red value should remain unchanged", newImage.getPixel(0, 0,
            0), result.getPixel(0, 0, 0));
    assertEquals("Green value should remain unchanged", newImage.getPixel(0, 1,
            1), result.getPixel(0, 1, 1));
    assertEquals("Blue value should remain unchanged", newImage.getPixel(1, 0,
            2), result.getPixel(1, 0, 2));
  }

  /**
   * Checks to ensure the compression class works with a percent of 50.
   */
  @Test
  public void compress() {
    Compression compression = new Compression(50);

    SimpleImage result = (SimpleImage) compression.execute(newImage);

    int redBefore = newImage.getPixel(0, 0, 0);
    int greenBefore = newImage.getPixel(0, 1, 1);
    int blueBefore = newImage.getPixel(1, 0, 2);

    int redAfter = result.getPixel(0, 0, 0);
    int greenAfter = result.getPixel(0, 1, 1);
    int blueAfter = result.getPixel(1, 0, 2);

    assertTrue("Red value out of bounds", redAfter >= 0 && redAfter <= 255);
    assertTrue("Green value out of bounds", greenAfter >= 0 && greenAfter <= 255);
    assertTrue("Blue value out of bounds", blueAfter >= 0 && blueAfter <= 255);
  }

  /**
   * Checks to ensure the compression class works with a percent of 100.
   */
  @Test
  public void compressFull() {
    Compression compression = new Compression(100);

    SimpleImage result = (SimpleImage) compression.execute(newImage);

    assertTrue("Red value should be significantly reduced",
            result.getPixel(0, 0, 0) < 255);
    assertTrue("Green value should be significantly reduced",
            result.getPixel(0, 1, 1) < 255);
    assertTrue("Blue value should be significantly reduced",
            result.getPixel(1, 0, 2) < 255);
  }

  /**
   * Checks to ensure the levels class.
   */
  @Test
  public void levels() {
    Levels levels = new Levels(50, 128, 200);

    Image result = levels.execute(newImage, null);

    assertNotNull("Levels adjustment result should not be null", result);

    int red1 = result.getPixel(0, 0, 0);
    int green1 = result.getPixel(0, 0, 1);
    int blue1 = result.getPixel(0, 0, 2);

    int red2 = result.getPixel(0, 1, 0);
    int green2 = result.getPixel(0, 1, 1);
    int blue2 = result.getPixel(0, 1, 2);

    int red3 = result.getPixel(1, 0, 0);
    int green3 = result.getPixel(1, 0, 1);
    int blue3 = result.getPixel(1, 0, 2);

    int red4 = result.getPixel(1, 1, 0);
    int green4 = result.getPixel(1, 1, 1);
    int blue4 = result.getPixel(1, 1, 2);

    assertEquals("Red channel at (0, 0) should be adjusted", 255, red1);
    assertTrue("Green channel at (0, 0) should remain unchanged", green1 != 255);
    assertTrue("Blue channel at (0, 0) should remain unchanged", blue1 != 255);

    assertTrue("Red channel at (0, 1) should remain unchanged", red2 != 255);
    assertEquals("Green channel at (0, 1) should be adjusted", 255, green2);
    assertTrue("Blue channel at (0, 1) should remain unchanged", blue2 != 255);

    assertTrue("Red channel at (1, 0) should remain unchanged", red3 != 255);
    assertTrue("Green channel at (1, 0) should remain unchanged", green3 != 255);
    assertEquals("Blue channel at (1, 0) should be adjusted", 255, blue3);

    assertEquals("Red channel at (1, 1) should be adjusted", 255, red4);
    assertEquals("Green channel at (1, 1) should be adjusted", 255, green4);
    assertTrue("Blue channel at (1, 1) should remain unchanged", blue4 != 255);
  }
}