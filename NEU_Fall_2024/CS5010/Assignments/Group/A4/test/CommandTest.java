import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This is the junit test class for our png image manipulations.
 */
public class CommandTest {
  private ImageController controller;
  private Image newImage;
  private Image saved;

  /**
   * setting up.
   */
  @Before
  public void setUP() {
    controller = new ImCont();

    controller.load("src/images/Ruth.png", "ruth");
  }

  /**
   * Testing very simple load and save image commands.
    */
  @Test
  public void testLoadAndSave() {

    controller.load("src/images/Ruth.png", "new-ruth");
    newImage = controller.findImageByAlias("new-ruth");
    controller.save("src/images/new-ruth.png", "new-ruth");
    controller.load("src/images/new-ruth.png", "saved-ruth");
    saved = controller.findImageByAlias("saved-ruth");

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
    String input = "sepia ruth sepia-ruth";
    controller.executeCommand(input);
    controller.save("src/images/sepia-ruth.png", "sepia-ruth");
    newImage = controller.findImageByAlias("sepia-ruth");
    controller.load("src/images/sepia-ruth.png", "sepia-ruth");
    saved = controller.findImageByAlias("sepia-ruth");
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
    String input = "blur ruth blur-ruth";
    controller.executeCommand(input);
    controller.save("src/images/blur-ruth.png", "blur-ruth");
    newImage = controller.findImageByAlias("blur-ruth");
    controller.load("src/images/blur-ruth.png", "blur-ruth");
    saved = controller.findImageByAlias("blur-ruth");
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
    String input = "sharpen ruth sharpen-ruth";
    controller.executeCommand(input);
    controller.save("src/images/sharpen-ruth.png", "sharpen-ruth");
    newImage = controller.findImageByAlias("sharpen-ruth");
    controller.load("src/images/sharpen-ruth.png", "sharpen-ruth");
    saved = controller.findImageByAlias("sharpen-ruth");
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
    String input = "brighten 50 ruth br-ruth";
    controller.executeCommand(input);
    controller.save("src/images/br-ruth.png", "br-ruth");
    newImage = controller.findImageByAlias("br-ruth");
    controller.load("src/images/br-ruth.png", "br-ruth");
    saved = controller.findImageByAlias("br-ruth");
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
    String input = "brighten -50 ruth dark-ruth";
    controller.executeCommand(input);
    controller.save("src/images/dark-ruth.png", "dark-ruth");
    newImage = controller.findImageByAlias("dark-ruth");
    controller.load("src/images/dark-ruth.png", "sepia-ruth");
    saved = controller.findImageByAlias("dark-ruth");
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
    String input = "horizontal-flip ruth horz-ruth";
    controller.executeCommand(input);
    controller.save("src/images/horz-ruth.png", "horz-ruth");
    newImage = controller.findImageByAlias("horz-ruth");
    controller.load("src/images/sepia-ruth.png", "horz-ruth");
    saved = controller.findImageByAlias("horz-ruth");
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
    String input = "vertical-flip ruth vert-ruth";
    controller.executeCommand(input);
    controller.save("src/images/vert-ruth.png", "vert-ruth");
    newImage = controller.findImageByAlias("vert-ruth");
    controller.load("src/images/vert-ruth.png", "vert-ruth");
    saved = controller.findImageByAlias("vert-ruth");
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
    String input1 = "vertical-flip ruth vert-ruth";
    String input2 = "horizontal-flip vert-ruth vert-horz-ruth";
    controller.executeCommand(input1);
    controller.save("src/images/vert-ruth.png", "vert-ruth");
    controller.executeCommand(input2);
    controller.save("src/images/vert-horz-ruth.png", "vert-horz-ruth");

    newImage = controller.findImageByAlias("vert-horz-ruth");
    controller.load("src/images/vert-horz-ruth.png", "vert-horz-ruth");
    saved = controller.findImageByAlias("vert-horz-ruth");
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
    String input = "luma-component ruth lumaGrey-ruth";
    controller.executeCommand(input);
    controller.save("src/images/lumaGrey-ruth.png", "lumaGrey-ruth");
    newImage = controller.findImageByAlias("lumaGrey-ruth");
    controller.load("src/images/lumaGrey-ruth.png", "lumaGrey-ruth");
    saved = controller.findImageByAlias("lumaGrey-ruth");
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
    String input = "value-component ruth value-ruth";
    controller.executeCommand(input);
    controller.save("src/images/valueGrey-ruth.png", "value-ruth");
    newImage = controller.findImageByAlias("value-ruth");
    controller.load("src/images/valueGrey-ruth.png", "value-ruth");
    saved = controller.findImageByAlias("value-ruth");
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
    String input = "intensity-component ruth intensity-ruth";
    controller.executeCommand(input);
    controller.save("src/images/intensityGrey-ruth.png", "intensity-ruth");
    newImage = controller.findImageByAlias("intensity-ruth");
    controller.load("src/images/lumaGrey-ruth.png", "intensit-ruth");
    saved = controller.findImageByAlias("intensity-ruth");
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
    String input = "rgb-split ruth red-ruth green-ruth blue-ruth";
    controller.executeCommand(input);
    controller.save("src/images/red-ruth.png","red-ruth");
    controller.save("src/images/green-ruth.png","green-ruth");
    controller.save("src/images/blue-ruth.png","blue-ruth");

    Image redImage = controller.findImageByAlias("red-ruth");
    controller.load("src/images/red-ruth.png", "red-ruth");
    Image redSaved = controller.findImageByAlias("red-ruth");
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

    Image greenImage = controller.findImageByAlias("green-ruth");
    controller.load("src/images/green-ruth.png", "green-ruth");
    Image savedGreen = controller.findImageByAlias("green-ruth");
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

    Image blueImage = controller.findImageByAlias("blue-ruth");
    controller.load("src/images/blue-ruth.png", "blue-ruth");
    Image blueGreen = controller.findImageByAlias("blue-ruth");
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
    String input = "rgb-combine PNGcombo red-ruth green-ruth blue-ruth";
    controller.load("src/images/red-ruth.png", "red-ruth");
    controller.load("src/images/green-ruth.png", "green-ruth");
    controller.load("src/images/blue-ruth.png", "blue-ruth");
    controller.executeCommand(input);
    controller.save("src/images/PNGcombo.png", "PNGcombo");

    newImage = controller.findImageByAlias("PNGcombo");
    controller.load("src/images/PNGcombo.png", "PNGcombo");
    saved = controller.findImageByAlias("PNGcombo");
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
    String input = "red-component ruth rc-ruth";
    controller.executeCommand(input);
    controller.save("src/images/rc-ruth.png", "rc-ruth");
    newImage = controller.findImageByAlias("rc-ruth");
    controller.load("src/images/rc-ruth.png", "rc-ruth");
    saved = controller.findImageByAlias("rc-ruth");
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
    String input = "green-component ruth gc-ruth";
    controller.executeCommand(input);
    controller.save("src/images/gc-ruth.png", "gc-ruth");
    newImage = controller.findImageByAlias("gc-ruth");
    controller.load("src/images/gc-ruth.png", "gc-ruth");
    saved = controller.findImageByAlias("gc-ruth");
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
    String input = "blue-component ruth bc-ruth";
    controller.executeCommand(input);
    controller.save("src/images/bc-ruth.png", "bc-ruth");
    newImage = controller.findImageByAlias("bc-ruth");
    controller.load("src/images/bc-ruth.png", "bc-ruth");
    saved = controller.findImageByAlias("bc-ruth");
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