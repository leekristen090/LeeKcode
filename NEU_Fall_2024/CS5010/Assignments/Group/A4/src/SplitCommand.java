/**
 * This is the split command class which extends the abstract command abstract class.
 */
public class SplitCommand extends AbstractCommand {

  /**
   * construct split command.
   * @param controller controller.
   */
  public SplitCommand(ImCont controller) {
    super(controller);
  }

  /**
   * Execute split logic using parameters given in single line command.
   * @param params from command
   */
  @Override
  public void execute(String[] params) {

    /* command in script
    * #give the koala a red tint
    * rgb-split koala koala-red koala-green koala-blue
    * */
    String originalAlias = params[1];
    String redAlias = params[2];
    String greenAlias = params[3];
    String blueAlias = params[4];

    Image originalImage = controller.findImageByAlias(originalAlias);
    Image newImage = controller.createImageCopy(originalImage, originalAlias);
    if (originalImage == null) {
      throw new IllegalArgumentException("Image with alias '" + originalAlias + "' not found.");
    }

    Split splitRGB = new Split();
    splitRGB.execute(newImage, redAlias, greenAlias, blueAlias, controller);
  }
}