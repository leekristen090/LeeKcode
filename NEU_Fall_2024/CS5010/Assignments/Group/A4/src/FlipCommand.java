/**
 * This is the flip command class which implements the abstract command abstract class.
 */
public class FlipCommand extends AbstractCommand {

  /**
   * Construct flip command.
   * @param controller controller
   */
  public FlipCommand(ImCont controller) {
    super(controller);
  }

  /**
   * Execute flip logic using parameters from single line command.
   * @param params from command
   */
  @Override
  public void execute(String[] params) {
    String imageAlias = params[1]; // Original image alias
    String newAlias = params[2];    // Alias for the new image

    // Retrieve the image from the controller
    Image originalImage = controller.findImageByAlias(imageAlias);
    Image newImage = controller.createImageCopy(originalImage, newAlias);
    if (originalImage == null) {
      throw new IllegalArgumentException("Image with alias '" + imageAlias + "' not found.");
    }

    if (params[0].equals("horizontal-flip")) {
      FlipHorz horz = new FlipHorz();
      horz.execute(newImage, newAlias, controller);
    }
    if (params[0].equals("vertical-flip")) {
      FlipVert vert = new FlipVert();
      vert.execute(newImage, newAlias, controller);
    }
  }
}