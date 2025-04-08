/**
 * This is the color command class which extends the abstract command abstract class.
 */
public class ColorCommand extends AbstractCommand {

  /**
   * Construct color command.
   * @param controller controller
   */
  public ColorCommand(ImCont controller) {
    super(controller);
  }

  /**
   * This executes the color command commands using the parameters from the single line command.
   * Red, green, and blue components.
   * @param params from command
   */
  @Override
  public void execute(String[] params) {
    /* red-component image-name dest-image-name */
    String imageAlias = params[1];
    String newAlias = params[2];
    Image originalImage = controller.findImageByAlias(imageAlias);
    Image newImage = controller.createImageCopy(originalImage, newAlias);

    if (originalImage == null) {
      throw new IllegalArgumentException("Image with alias '" + imageAlias + "' not found.");
    }

    if (params[0].equals("red-component")) {
      Red r = new Red();
      r.execute(newImage, newAlias, controller);
    }
    if (params[0].equals("green-component")) {
      Green g = new Green();
      g.execute(newImage, newAlias, controller);
    }
    if (params[0].equals("blue-component")) {
      Blue b = new Blue();
      b.execute(newImage, newAlias, controller);
    }
  }

}