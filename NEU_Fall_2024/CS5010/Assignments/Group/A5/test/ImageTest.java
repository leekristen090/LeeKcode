import org.junit.Before;
import org.junit.Test;

import model.Image;
import model.SimpleImage;

import static org.junit.Assert.assertEquals;

/**
 * This is the test class for image pixel stuff.
 */
public class ImageTest {
  private Image newImage;

  /**
   * Set up.
   */
  @Before
  public void setUP() {
    //creates a new object SimpleMoney
    newImage = new SimpleImage("test", 230, 512);

  }

  /**
   * Test getPixel().
   */
  @Test
  public void setGetPix1() {
    int row = (int) (Math.random() * 229) + 1;
    int col = (int) (Math.random() * 511) + 1;

    int red = (int) (Math.random() * 254) + 1;
    int green = (int) (Math.random() * 254) + 1;
    int blue = (int) (Math.random() * 254) + 1;
    newImage.setPixel(row, col, red, green, blue);

    assertEquals(red, newImage.getPixel(row, col, 0), 0);
    assertEquals(green, newImage.getPixel(row, col, 1), 0);
    assertEquals(blue, newImage.getPixel(row, col, 2), 0);
  }

  /**
   * Test get pixel again.
   */
  @Test
  public void setGetPix2() {
    int row = (int) (Math.random() * 229) + 1;
    int col = (int) (Math.random() * 511) + 1;

    int red = (int) (Math.random() * 254) + 1;
    int green = (int) (Math.random() * 254) + 1;
    int blue = (int) (Math.random() * 254) + 1;
    newImage.setPixel(row, col, red, green, blue);

    assertEquals(red, newImage.getPixel(row, col, 0), 0);
    assertEquals(green, newImage.getPixel(row, col, 1), 0);
    assertEquals(blue, newImage.getPixel(row, col, 2), 0);
  }

  /**
   * Test getPixel again.
   */
  @Test
  public void setGetPix3() {
    int row = (int) (Math.random() * 229) + 1;
    int col = (int) (Math.random() * 511) + 1;

    int red = (int) (Math.random() * 254) + 1;
    int green = (int) (Math.random() * 254) + 1;
    int blue = (int) (Math.random() * 254) + 1;
    newImage.setPixel(row, col, red, green, blue);

    assertEquals(red, newImage.getPixel(row, col, 0), 0);
    assertEquals(green, newImage.getPixel(row, col, 1), 0);
    assertEquals(blue, newImage.getPixel(row, col, 2), 0);
  }

  /**
   * Test getWidth().
   */
  @Test
  public void getWidth() {
    assertEquals(512, newImage.getWidth(), 0);
  }

  /**
   * Test getHeight().
   */
  @Test
  public void getHeight() {
    assertEquals(230, newImage.getHeight(), 0);
  }
}