/**
 * This is the blur command class which extends the abstract command abstract class.
 */
public class BlurCommand extends AbstractCommand {

  /**
   * Construct blur command.
   * @param controller controller
   */
  public BlurCommand(ImCont controller) {
    super(controller);
  }

  /**
   * This executes the blur command using the parameters from the single line command.
   * @param params from command
   */
  @Override
  public void execute(String[] params) {
    if (params.length != 3) {
      throw new IllegalArgumentException("Invalid number of arguments for blur command.");
    }

    String imageAlias = params[1]; // Original image alias
    String newAlias = params[2];    // Alias for the new image with sepia effect

    // Retrieve the image from the controller
    Image originalImage = controller.findImageByAlias(imageAlias);
    Image newImage = controller.createImageCopy(originalImage, newAlias);
    if (originalImage == null) {
      throw new IllegalArgumentException("Image with alias '" + imageAlias + "' not found.");
    }

    // Create a new Sepia command instance and execute it on the original image
    Blur blur = new Blur();
    blur.execute(newImage, newAlias, controller);
  }


}