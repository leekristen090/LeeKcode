import controller.Image;
import controller.ImageFactory;
import model.ImageDB;
import controller.OtherImages;
import controller.PixelImpl;
import model.Dithering;
import model.Load;

import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.assertArrayEquals;

/**
 * This is the junit test class for the dither manipulation.
 */
public class DitherTest {

  /**
   * Testing dithering on an image.
   * @throws IOException if image loading is unsuccessful
   */
  @Test
  public void ditherTest() throws IOException {
    ImageDB imageDB = new ImageDB();
    Image expected = new OtherImages(2,2);
    expected.setPixel(0, 0, new PixelImpl(0, 0, 0));
    expected.setPixel(0, 1, new PixelImpl(255, 255, 255));
    expected.setPixel(1, 0, new PixelImpl(0, 0, 0));
    expected.setPixel(1, 1, new PixelImpl(255, 255, 255));

    Image image;
    String path = "../Assign7/res/testing.png";

    try {
      image = ImageFactory.loadImage(path);
    } catch (Exception e) {
      throw new IOException("Failed to load image: " + path + e);
    }
    new Load().loadToDb("testing",image, imageDB);
    Dithering dithering = new Dithering();
    Image[] actual = dithering.process(new String[]{"dither","testing","testing-dither"}, imageDB
    );
    Image actual_dither = actual[0];
    for (int i = 0; i < actual.length; i++) {
      for (int j = 0; j < actual[i].getWidth(); j++) {
        assertArrayEquals(expected.getPixel(i,j).getRGB(),
                actual_dither.getPixel(i,j).getRGB(),1);
      }
    }
  }

  /**
   * Testing intentionally wrong pixel values.
   * @throws IOException if image loading is unsuccessful
   */
  @Test
  public void DitherTestWrongPixels() throws IOException {
    // This test is designed to fail as the expected values are intentionally incorrect.
    ImageDB imageDB = new ImageDB();
    Image expected = new OtherImages(2, 2);
    expected.setPixel(0, 0, new PixelImpl(255, 255, 255)); //wrong
    expected.setPixel(0, 1, new PixelImpl(0, 0, 0));
    expected.setPixel(1, 0, new PixelImpl(255, 255, 255));
    expected.setPixel(1, 1, new PixelImpl(0, 0, 0));

    Image image;
    String path = "../Assign7/res/testing.png";
    try {
      image = ImageFactory.loadImage(path);
    } catch (Exception e) {
      throw new IOException("Failed to load image: " + path + e);
    }
    new Load().loadToDb("testing", image, imageDB);
    Dithering dithering = new Dithering();
    Image[] actual =
            dithering.process(new String[]{ "dither", "testing", "testing-dither"}, imageDB);
    Image actual_dither = actual[0];

    boolean mismatchFound = false;
    try {
      for (int i = 0; i < actual_dither.getHeight(); i++) {
        for (int j = 0; j < actual_dither.getWidth(); j++) {
          assertArrayEquals(expected.getPixel(i, j).getRGB(),
                  actual_dither.getPixel(i, j).getRGB(), 1);
        }
      }
    } catch (AssertionError e) {
      mismatchFound = true; // Expected for this test
    }

    assert mismatchFound : "This test is expected to fail due to mismatched pixel values.";
  }


}
