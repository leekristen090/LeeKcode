import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This is the junit test class for our jpg image manipulations.
 */
public class CommandJPGTest {
  private ImageController controller2;

  private Image newImage;
  private Image saved;

  /**
   * setting up.
   */
  @Before
  public void setUP() {
    controller2 = new ImCont();

    //jpg
    controller2.load("src/images/mitra.jpg","mitra");

  }

  /**
   * Testing very simple load and save image commands.
   */
  @Test
  public void testLoadAndSave() {
    controller2.load("src/images/mitra.jpg", "new-mitra");
    newImage = controller2.findImageByAlias("new-mitra");
    controller2.save("src/images/new-mitra.jpg", "new-mitra");
    controller2.load("src/images/new-mitra.jpg", "saved-mitra");
    saved = controller2.findImageByAlias("saved-mitra");

    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());
  }

  /**
   * This is testing sepia.
   */
  @Test
  public void testSepia() {

    String input2 = "sepia mitra sepia-mitra";
    controller2.executeCommand(input2);
    controller2.save("src/images/sepia-mitra.jpg", "sepia-mitra");
    newImage = controller2.findImageByAlias("sepia-mitra");
    controller2.load("src/images/sepia-mitra.jpg", "sepia-mitra");
    saved = controller2.findImageByAlias("sepia-mitra");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());

  }

  /**
   * Testing blur method.
   */
  @Test
  public void testBlur() {
    String input2 = "blur mitra blur-mitra";
    controller2.executeCommand(input2);
    controller2.save("src/images/blur-mitra.jpg", "blur-mitra");
    newImage = controller2.findImageByAlias("blur-mitra");
    controller2.load("src/images/blur-mitra.jpg", "blur-mitra");
    saved = controller2.findImageByAlias("blur-mitra");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());
  }

  /**
   * Testing sharpen.
   */
  @Test
  public void testSharp() {

    String input2 = "sharpen mitra sharpen-mitra";
    controller2.executeCommand(input2);
    controller2.save("src/images/sharpen-mitra.jpg", "sharpen-mitra");
    newImage = controller2.findImageByAlias("sharpen-mitra");
    controller2.load("src/images/sharpen-mitra.jpg", "sharpen-mitra");
    saved = controller2.findImageByAlias("sharpen-mitra");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());

  }

  /**
   * Testing brighten with positive percent.
   */
  @Test
  public void testBright() {
    String input2 = "brighten 50 mitra br-mitra";
    controller2.executeCommand(input2);
    controller2.save("src/images/br-mitra.jpg", "br-mitra");
    newImage = controller2.findImageByAlias("br-mitra");
    controller2.load("src/images/br-mitra.jpg", "br-mitra");
    saved = controller2.findImageByAlias("br-mitra");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());

  }

  /**
   * Testing brighten with positive percent.
   */
  @Test
  public void testDark() {

    String input2 = "brighten -50 mitra dark-mitra";
    controller2.executeCommand(input2);
    controller2.save("src/images/dark-mitra.jpg", "dark-mitra");
    newImage = controller2.findImageByAlias("dark-mitra");
    controller2.load("src/images/dark-mitra.jpg", "dark-mitra");
    saved = controller2.findImageByAlias("dark-mitra");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());

  }

  /**
   * Test horizontal flip.
   */
  @Test
  public void testHoriz() {

    String input2 = "horizontal-flip mitra horz-mitra";
    controller2.executeCommand(input2);
    controller2.save("src/images/horz-mitra.jpg", "horz-mitra");
    newImage = controller2.findImageByAlias("horz-mitra");
    controller2.load("src/images/horz-mitra.jpg", "horz-mitra");
    saved = controller2.findImageByAlias("horz-mitra");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());

  }

  /**
   * Test vertical flip.
   */
  @Test
  public void testVert() {

    String input2 = "vertical-flip mitra vert-mitra";
    controller2.executeCommand(input2);
    controller2.save("src/images/vert-mitra.jpg", "vert-mitra");
    newImage = controller2.findImageByAlias("vert-mitra");
    controller2.load("src/images/vert-mitra.jpg", "vert-mitra");
    saved = controller2.findImageByAlias("vert-mitra");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());

  }

  /**
   * Testing vertical flip then horizontal flip.
   */
  @Test
  public void testVertHorz() {
    String jpg1 = "vertical-flip mitra vert-mitra";
    String jpg2 = "horizontal-flip vert-mitra vert-horz-mitra";
    controller2.executeCommand(jpg1);
    controller2.save("src/images/vert-mitra.jpg", "vert-mitra");
    controller2.executeCommand(jpg2);
    controller2.save("src/images/vert-horz-mitra.jpg", "vert-horz-mitra");
    newImage = controller2.findImageByAlias("vert-horz-mitra");
    controller2.load("src/images/vert-horz-mitra.jpg", "vert-horz-mitra");
    saved = controller2.findImageByAlias("vert-horz-mitra");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());
  }

  /**
   * Testing luma greyscale.
   */
  @Test
  public void testLuma() {
    String input2 = "luma-component mitra luma-mitra";
    controller2.executeCommand(input2);
    controller2.save("src/images/lumaGrey-mitra.jpg", "luma-mitra");
    newImage = controller2.findImageByAlias("luma-mitra");
    controller2.load("src/images/luma-mitra.jpg", "luma-mitra");
    saved = controller2.findImageByAlias("luma-mitra");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());

  }

  /**
   * Testing value greyscale.
   */
  @Test
  public void testValue() {
    String input2 = "value-component mitra value-mitra";
    controller2.executeCommand(input2);
    controller2.save("src/images/valueGrey-mitra.jpg", "value-mitra");
    newImage = controller2.findImageByAlias("value-mitra");
    controller2.load("src/images/valueGrey-mitra.jpg", "value-mitra");
    saved = controller2.findImageByAlias("value-mitra");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());

  }

  /**
   * Testing intensity greyscale.
   */
  @Test
  public void testIntensity() {

    String input2 = "intensity-component mitra intensity-mitra";
    controller2.executeCommand(input2);
    controller2.save("src/images/intensityGrey-mitra.jpg", "intensity-mitra");
    newImage = controller2.findImageByAlias("intensity-mitra");
    controller2.load("src/images/intensityGrey-mitra.jpg", "intensity-mitra");
    saved = controller2.findImageByAlias("intensity-mitra");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());

  }

  /**
   * This the test for splitting an image into its 3 color components in separate images.
   */
  @Test
  public void testSplit() {

    String input2 = "rgb-split mitra red-mitra green-mitra blue-mitra";
    controller2.executeCommand(input2);
    controller2.save("src/images/red-mitra.jpg","red-mitra");
    controller2.save("src/images/green-mitra.jpg","green-mitra");
    controller2.save("src/images/blue-mitra.jpg","blue-mitra");

    Image redImage = controller2.findImageByAlias("red-mitra");
    controller2.load("src/images/red-mitra.jpg", "red-mitra");
    Image redSaved = controller2.findImageByAlias("red-mitra");
    assertEquals(redImage.getHeight(), redSaved.getHeight());
    assertEquals(redImage.getWidth(), redSaved.getWidth());

    Image greenImage = controller2.findImageByAlias("green-mitra");
    controller2.load("src/images/green-mitra.jpg", "green-mitra");
    Image greenSaved = controller2.findImageByAlias("green-mitra");
    assertEquals(greenImage.getHeight(), greenSaved.getHeight());
    assertEquals(greenImage.getWidth(), greenSaved.getWidth());

    Image blueImage = controller2.findImageByAlias("blue-mitra");
    controller2.load("src/images/blue-mitra.jpg", "blue-mitra");
    Image blueSaved = controller2.findImageByAlias("blue-mitra");
    assertEquals(blueImage.getHeight(), blueSaved.getHeight());
    assertEquals(blueImage.getWidth(), blueSaved.getWidth());

  }

  /**
   * testing combo a red, green and blue image into one.
   */
  @Test
  public void testCombo() {

    String input2 = "rgb-combine JPGcombo red-mitra green-mitra blue-mitra";
    controller2.load("src/images/red-mitra.jpg", "red-mitra");
    controller2.load("src/images/green-mitra.jpg", "green-mitra");
    controller2.load("src/images/blue-mitra.jpg", "blue-mitra");
    controller2.executeCommand(input2);
    controller2.save("src/images/JPGcombo.jpg", "JPGcombo");
    newImage = controller2.findImageByAlias("JPGcombo");
    controller2.load("src/images/JPGcombo-mitra.jpg", "JPGcombo");
    saved = controller2.findImageByAlias("JPGcombo");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());



  }

  /**
   * testing red component command.
   */
  @Test
  public void testRedComp() {
    String input2 = "red-component mitra rc-mitra";
    controller2.executeCommand(input2);
    controller2.save("src/images/rc-mitra.jpg", "rc-mitra");
    newImage = controller2.findImageByAlias("rc-mitra");
    controller2.load("src/images/rc-mitra.jpg", "rc-mitra");
    saved = controller2.findImageByAlias("rc-mitra");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());

  }

  /**
   * testing green component command.
   */
  @Test
  public void testGreenComp() {

    String input2 = "green-component mitra gc-mitra";
    controller2.executeCommand(input2);
    controller2.save("src/images/gc-mitra.jpg", "gc-mitra");
    newImage = controller2.findImageByAlias("gc-mitra");
    controller2.load("src/images/gc-mitra.jpg", "gc-mitra");
    saved = controller2.findImageByAlias("gc-mitra");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());
  }

  /**
   * testing blue component command.
   */
  @Test
  public void testBlueComp() {
    String input2 = "blue-component mitra bc-mitra";
    controller2.executeCommand(input2);
    controller2.save("src/images/bc-mitra.jpg", "bc-mitra");
    newImage = controller2.findImageByAlias("bc-mitra");
    controller2.load("src/images/bc-mitra.jpg", "bc-mitra");
    saved = controller2.findImageByAlias("bc-mitra");
    assertEquals(newImage.getHeight(), saved.getHeight());
    assertEquals(newImage.getWidth(), saved.getWidth());
  }

}