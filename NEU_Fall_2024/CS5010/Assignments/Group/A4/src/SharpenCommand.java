/**
 * This is the sharpen command class which extends the abstract command abstract class.
 */
public class SharpenCommand extends AbstractCommand {

  /**
   * construct sharpen command.
   * @param controller controller
   */
  public SharpenCommand(ImCont controller) {
    super(controller);
  }

  /**
   * Execute sharpen logic using parameters from single line command.
   * @param params from command
   */
  @Override
  public void execute(String[] params) {
    String imageAlias = params[1]; // Original image alias
    String newAlias = params[2];    // Alias for the new image with sepia effect

    // Retrieve the image from the controller
    Image originalImage = controller.findImageByAlias(imageAlias);
    Image newImage = controller.createImageCopy(originalImage, newAlias);
    if (originalImage == null) {
      throw new IllegalArgumentException("Image with alias '" + imageAlias + "' not found.");
    }

    // Create a new Sepia command instance and execute it on the original image
    Sharpen sharp = new Sharpen();
    sharp.execute(newImage, newAlias, controller);

  }

}