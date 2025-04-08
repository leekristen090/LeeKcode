/**
 * This is the sepia command class which extends the abstract command abstract class.
 */
public class SepiaCommand extends AbstractCommand {

  /**
   * construct sepia command.
   * @param controller controller
   */
  public SepiaCommand(ImCont controller) {
    super(controller);
  }

  /**
   * Execute sepia logic based on parameters given in single line command.
   * @param params from command
   */
  @Override
  public void execute(String[] params) {
    if (params.length != 3) {
      throw new IllegalArgumentException("Invalid number of arguments for sepia command.");
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
    Sepia sepia = new Sepia();
    sepia.execute(newImage, newAlias, controller); // Execute Sepia effect
  }
}
