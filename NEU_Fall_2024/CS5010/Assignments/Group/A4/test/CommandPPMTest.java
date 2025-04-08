import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This is the junit test class for our ppm image manipulations.
 */
public class CommandPPMTest {

  private ImageController controller3;
  private Image newImage;
  private Image saved;

  /**
   * setting up.
   */
  @Before
  public void setUP() {

    controller3 = new ImCont();

    controller3.load("src/images/snail.ppm","snail");
  }

  /**
   * Testing very simple load and save image commands.
   */
  @Test
  public void testLoadAndSave() {
    controller3.load("src/images/snail.ppm", "new-snail");
    newImage = controller3.findImageByAlias("new-snail");
    controller3.save("src/images/new-snail.ppm", "new-snail");
    controller3.load("src/images/new-snail.ppm", "saved-snail");
    saved = controller3.findImageByAlias("saved-snail");

    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());
    // comparing pixels
    for (int row = 0; row < newImage.getHeight(); row++) {
      for (int col = 0; col < newImage.getWidth(); col++) {
        for (int component = 0; component < 3; component++) { // R, G, B channels
          assertEquals(newImage.getPixel(row, col, component), saved.getPixel(row, col, component));
        }
      }
    }
  }

  /**
   * This is testing sepia.
   */
  @Test
  public void testSepia() {
    String input3 = "sepia snail sepia-snail";
    controller3.executeCommand(input3);
    controller3.save("src/images/sepia-snail.ppm", "sepia-snail");
    newImage = controller3.findImageByAlias("sepia-snail");
    controller3.load("src/images/sepia-ruth.png", "sepia-snail");
    saved = controller3.findImageByAlias("sepia-snail");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());
    // comparing pixels
    for (int row = 0; row < newImage.getHeight(); row++) {
      for (int col = 0; col < newImage.getWidth(); col++) {
        for (int component = 0; component < 3; component++) { // R, G, B channels
          assertEquals(newImage.getPixel(row, col, component), saved.getPixel(row, col, component));
        }
      }
    }
  }

  /**
   * Testing blur method.
   */
  @Test
  public void testBlur() {

    String input3 = "blur snail blur-snail";
    controller3.executeCommand(input3);
    controller3.save("src/images/blur-snail.ppm", "blur-snail");
    newImage = controller3.findImageByAlias("blur-snail");
    controller3.load("src/images/blur-snail.ppm", "blur-snail");
    saved = controller3.findImageByAlias("blur-snail");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());
    // comparing pixels
    for (int row = 0; row < newImage.getHeight(); row++) {
      for (int col = 0; col < newImage.getWidth(); col++) {
        for (int component = 0; component < 3; component++) { // R, G, B channels
          assertEquals(newImage.getPixel(row, col, component), saved.getPixel(row, col, component));
        }
      }
    }
  }

  /**
   * Testing sharpen.
   */
  @Test
  public void testSharp() {

    String input3 = "sharpen snail sharpen-snail";
    controller3.executeCommand(input3);
    controller3.save("src/images/sharpen-snail.ppm", "sharpen-snail");
    newImage = controller3.findImageByAlias("sharpen-snail");
    controller3.load("src/images/sharpen-snail.ppm", "sharpen-snail");
    saved = controller3.findImageByAlias("sharpen-snail");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());
    // comparing pixels
    for (int row = 0; row < newImage.getHeight(); row++) {
      for (int col = 0; col < newImage.getWidth(); col++) {
        for (int component = 0; component < 3; component++) { // R, G, B channels
          assertEquals(newImage.getPixel(row, col, component), saved.getPixel(row, col, component));
        }
      }
    }
  }

  /**
   * Testing brighten with positive percent.
   */
  @Test
  public void testBright() {

    String input3 = "brighten 50 snail br-snail";
    controller3.executeCommand(input3);
    controller3.save("src/images/br-snail.ppm", "br-snail");
    newImage = controller3.findImageByAlias("br-snail");
    controller3.load("src/images/br-snail.ppm", "br-snail");
    saved = controller3.findImageByAlias("br-snail");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());
    // comparing pixels
    for (int row = 0; row < newImage.getHeight(); row++) {
      for (int col = 0; col < newImage.getWidth(); col++) {
        for (int component = 0; component < 3; component++) { // R, G, B channels
          assertEquals(newImage.getPixel(row, col, component), saved.getPixel(row, col, component));
        }
      }
    }
  }

  /**
   * Testing brighten with positive percent.
   */
  @Test
  public void testDark() {

    String input3 = "brighten -50 snail dark-snail";
    controller3.executeCommand(input3);
    controller3.save("src/images/dark-snail.ppm", "dark-snail");
    newImage = controller3.findImageByAlias("dark-snail");
    controller3.load("src/images/dark-snail.ppm", "dark-snail");
    saved = controller3.findImageByAlias("dark-snail");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());
    // comparing pixels
    for (int row = 0; row < newImage.getHeight(); row++) {
      for (int col = 0; col < newImage.getWidth(); col++) {
        for (int component = 0; component < 3; component++) { // R, G, B channels
          assertEquals(newImage.getPixel(row, col, component), saved.getPixel(row, col, component));
        }
      }
    }
  }

  /**
   * Test horizontal flip.
   */
  @Test
  public void testHoriz() {

    String input3 = "horizontal-flip snail horz-snail";
    controller3.executeCommand(input3);
    controller3.save("src/images/horz-snail.ppm", "horz-snail");
    newImage = controller3.findImageByAlias("horz-snail");
    controller3.load("src/images/horz-snail.ppm", "horz-snail");
    saved = controller3.findImageByAlias("horz-snail");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());
    // comparing pixels
    for (int row = 0; row < newImage.getHeight(); row++) {
      for (int col = 0; col < newImage.getWidth(); col++) {
        for (int component = 0; component < 3; component++) { // R, G, B channels
          assertEquals(newImage.getPixel(row, col, component), saved.getPixel(row, col, component));
        }
      }
    }
  }

  /**
   * Test vertical flip.
   */
  @Test
  public void testVert() {

    String input3 = "vertical-flip snail vert-snail";
    controller3.executeCommand(input3);
    controller3.save("src/images/vert-snail.ppm", "vert-snail");
    newImage = controller3.findImageByAlias("vert-snail");
    controller3.load("src/images/vert-snail.ppm", "vert-snail");
    saved = controller3.findImageByAlias("vert-snail");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());
    // comparing pixels
    for (int row = 0; row < newImage.getHeight(); row++) {
      for (int col = 0; col < newImage.getWidth(); col++) {
        for (int component = 0; component < 3; component++) { // R, G, B channels
          assertEquals(newImage.getPixel(row, col, component), saved.getPixel(row, col, component));
        }
      }
    }
  }

  /**
   * Testing vertical flip then horizontal flip.
   */
  @Test
  public void testVertHorz() {

    String ppm1 = "vertical-flip snail vert-snail";
    String ppm2 = "horizontal-flip vert-snail vert-horz-snail";
    controller3.executeCommand(ppm1);
    controller3.save("src/images/vert-snail.ppm", "vert-snail");
    controller3.executeCommand(ppm2);
    controller3.save("src/images/vert-horz-snail.ppm", "vert-horz-snail");
    newImage = controller3.findImageByAlias("vert-horz-snail");
    controller3.load("src/images/vert-horz-snail.ppm", "vert-horz-snail");
    saved = controller3.findImageByAlias("vert-horz-snail");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());
    // comparing pixels
    for (int row = 0; row < newImage.getHeight(); row++) {
      for (int col = 0; col < newImage.getWidth(); col++) {
        for (int component = 0; component < 3; component++) { // R, G, B channels
          assertEquals(newImage.getPixel(row, col, component), saved.getPixel(row, col, component));
        }
      }
    }
  }

  /**
   * Testing luma greyscale.
   */
  @Test
  public void testLuma() {

    String input3 = "luma-component snail luma-snail";
    controller3.executeCommand(input3);
    controller3.save("src/images/lumaGrey-snail.ppm", "luma-snail");
    newImage = controller3.findImageByAlias("luma-snail");
    controller3.load("src/images/lumaGrey-snail.ppm", "luma-snail");
    saved = controller3.findImageByAlias("luma-snail");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());
    // comparing pixels
    for (int row = 0; row < newImage.getHeight(); row++) {
      for (int col = 0; col < newImage.getWidth(); col++) {
        for (int component = 0; component < 3; component++) { // R, G, B channels
          assertEquals(newImage.getPixel(row, col, component), saved.getPixel(row, col, component));
        }
      }
    }
  }

  /**
   * Testing value greyscale.
   */
  @Test
  public void testValue() {

    String input3 = "value-component snail value-snail";
    controller3.executeCommand(input3);
    controller3.save("src/images/valueGrey-snail.ppm", "value-snail");
    newImage = controller3.findImageByAlias("value-snail");
    controller3.load("src/images/value-snail.ppm", "value-snail");
    saved = controller3.findImageByAlias("value-snail");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());
    // comparing pixels
    for (int row = 0; row < newImage.getHeight(); row++) {
      for (int col = 0; col < newImage.getWidth(); col++) {
        for (int component = 0; component < 3; component++) { // R, G, B channels
          assertEquals(newImage.getPixel(row, col, component), saved.getPixel(row, col, component));
        }
      }
    }
  }

  /**
   * Testing intensity greyscale.
   */
  @Test
  public void testIntensity() {

    String input3 = "intensity-component snail intensity-snail";
    controller3.executeCommand(input3);
    controller3.save("src/images/intensityGrey-snail.ppm", "intensity-snail");
    newImage = controller3.findImageByAlias("intensity-snail");
    controller3.load("src/images/intensity-snail.ppm", "intensity-snail");
    saved = controller3.findImageByAlias("intensity-snail");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());
    // comparing pixels
    for (int row = 0; row < newImage.getHeight(); row++) {
      for (int col = 0; col < newImage.getWidth(); col++) {
        for (int component = 0; component < 3; component++) { // R, G, B channels
          assertEquals(newImage.getPixel(row, col, component), saved.getPixel(row, col, component));
        }
      }
    }
  }

  /**
   * This the test for splitting an image into its 3 color components in separate images.
   */
  @Test
  public void testSplit() {

    String input3 = "rgb-split snail red-snail green-snail blue-snail";
    controller3.executeCommand(input3);
    controller3.save("src/images/red-snail.ppm","red-snail");
    controller3.save("src/images/green-snail.ppm","green-snail");
    controller3.save("src/images/blue-snail.ppm","blue-snail");

    Image redImage = controller3.findImageByAlias("red-snail");
    controller3.load("src/images/red-snail.ppm", "red-snail");
    Image redSaved = controller3.findImageByAlias("red-snail");
    assertEquals(redImage.getHeight(), redSaved.getHeight());
    assertEquals(redImage.getWidth(), redSaved.getWidth());
    // comparing pixels
    for (int row = 0; row < redImage.getHeight(); row++) {
      for (int col = 0; col < redImage.getWidth(); col++) {
        for (int component = 0; component < 3; component++) { // R, G, B channels
          assertEquals(redImage.getPixel(row, col, component),
                  redSaved.getPixel(row, col, component));
        }
      }
    }

    Image greenImage = controller3.findImageByAlias("green-snail");
    controller3.load("src/images/green-snail.ppm", "green-snail");
    Image savedGreen = controller3.findImageByAlias("green-snail");
    assertEquals(greenImage.getHeight(), savedGreen.getHeight());
    assertEquals(greenImage.getWidth(), savedGreen.getWidth());
    // comparing pixels
    for (int row = 0; row < greenImage.getHeight(); row++) {
      for (int col = 0; col < greenImage.getWidth(); col++) {
        for (int component = 0; component < 3; component++) { // R, G, B channels
          assertEquals(greenImage.getPixel(row, col, component),
                  savedGreen.getPixel(row, col, component));
        }
      }
    }

    Image blueImage = controller3.findImageByAlias("blue-snail");
    controller3.load("src/images/blue-snail.ppm", "blue-snail");
    Image blueGreen = controller3.findImageByAlias("blue-snail");
    assertEquals(blueImage.getHeight(), blueGreen.getHeight());
    assertEquals(blueImage.getWidth(), blueGreen.getWidth());
    // comparing pixels
    for (int row = 0; row < blueImage.getHeight(); row++) {
      for (int col = 0; col < blueImage.getWidth(); col++) {
        for (int component = 0; component < 3; component++) { // R, G, B channels
          assertEquals(blueImage.getPixel(row, col, component),
                  blueGreen.getPixel(row, col, component));
        }
      }
    }
  }

  /**
   * testing combo a red, green and blue image into one.
   */
  @Test
  public void testCombo() {

    String input3 = "rgb-combine PPMcombo red-snail green-snail blue-snail";
    controller3.load("src/images/red-snail.ppm", "red-snail");
    controller3.load("src/images/green-snail.ppm", "green-snail");
    controller3.load("src/images/blue-snail.ppm", "blue-snail");
    controller3.executeCommand(input3);
    controller3.save("src/images/PPMcombo.ppm", "PPMcombo");
    newImage = controller3.findImageByAlias("PPMcombo");
    controller3.load("src/images/PPMcombo.ppm", "PPMcombo");
    saved = controller3.findImageByAlias("PPMcombo");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());
    // comparing pixels
    for (int row = 0; row < newImage.getHeight(); row++) {
      for (int col = 0; col < newImage.getWidth(); col++) {
        for (int component = 0; component < 3; component++) { // R, G, B channels
          assertEquals(newImage.getPixel(row, col, component), saved.getPixel(row, col, component));
        }
      }
    }
  }

  /**
   * testing red component command.
   */
  @Test
  public void testRedComp() {

    String input3 = "red-component snail rc-snail";
    controller3.executeCommand(input3);
    controller3.save("src/images/rc-snail.ppm", "rc-snail");
    newImage = controller3.findImageByAlias("rc-snail");
    controller3.load("src/images/rc-snail.ppm", "rc-snail");
    saved = controller3.findImageByAlias("rc-snail");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());
    // comparing pixels
    for (int row = 0; row < newImage.getHeight(); row++) {
      for (int col = 0; col < newImage.getWidth(); col++) {
        for (int component = 0; component < 3; component++) { // R, G, B channels
          assertEquals(newImage.getPixel(row, col, component), saved.getPixel(row, col, component));
        }
      }
    }
  }

  /**
   * testing green component command.
   */
  @Test
  public void testGreenComp() {

    String input3 = "green-component snail gc-snail";
    controller3.executeCommand(input3);
    controller3.save("src/images/gc-snail.ppm", "gc-snail");
    newImage = controller3.findImageByAlias("gc-snail");
    controller3.load("src/images/gc-snail.ppm", "gc-snail");
    saved = controller3.findImageByAlias("gc-snail");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());
    // comparing pixels
    for (int row = 0; row < newImage.getHeight(); row++) {
      for (int col = 0; col < newImage.getWidth(); col++) {
        for (int component = 0; component < 3; component++) { // R, G, B channels
          assertEquals(newImage.getPixel(row, col, component), saved.getPixel(row, col, component));
        }
      }
    }
  }

  /**
   * testing blue component command.
   */
  @Test
  public void testBlueComp() {

    String input3 = "blue-component snail bc-snail";
    controller3.executeCommand(input3);
    controller3.save("src/images/bc-snail.ppm", "bc-snail");
    newImage = controller3.findImageByAlias("bc-snail");
    controller3.load("src/images/bc-snail.ppm", "bc-snail");
    saved = controller3.findImageByAlias("bc-snail");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());
    // comparing pixels
    for (int row = 0; row < newImage.getHeight(); row++) {
      for (int col = 0; col < newImage.getWidth(); col++) {
        for (int component = 0; component < 3; component++) { // R, G, B channels
          assertEquals(newImage.getPixel(row, col, component), saved.getPixel(row, col, component));
        }
      }
    }
  }

}