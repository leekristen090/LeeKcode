/**
 * This is the adjust command class which extends the abstract command abstract class.
 */
public class AdjustCommand extends AbstractCommand {

  /**
   * construct adjust command.
   * @param controller the controller
   */
  public AdjustCommand(ImCont controller) {
    super(controller);
  }

  /**
   * This executes the brighten command using the parameters from the single line command.
   * @param params from command
   */
  @Override
  public void execute(String[] params) {

    int percent = Integer.parseInt(params[1]);
    String imageAlias = params[2]; // Original image alias
    String newAlias = params[3]; // new alias

    Image originalImage = controller.findImageByAlias(imageAlias);
    Image newImage = controller.createImageCopy(originalImage, newAlias);
    if (originalImage == null) {
      throw new IllegalArgumentException("Image with alias '" + imageAlias + "' not found.");
    }

    AdjustBright adjust = new AdjustBright(percent);
    adjust.execute(newImage, newAlias, controller);
  }

}