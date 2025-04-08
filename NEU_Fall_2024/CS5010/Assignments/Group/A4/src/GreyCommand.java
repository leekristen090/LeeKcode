/**
 * This is the grey scale command class which extends the abstract command abstract class.
 */
public class GreyCommand extends AbstractCommand {

  /**
   * construct grey command.
   * @param controller controller
   */
  public GreyCommand(ImCont controller) {
    super(controller);
  }

  /**
   * Execute the grey scale commands using parameters from the single line command. Use value, luma,
   * or intensity to create a grey scale image.
   * @param params from command
   */
  @Override
  public void execute(String[] params) {
    /* value-component koala koala-greyscale */
    String originalAlias = params[1];
    String newAlias = params[2];
    Image originalImage = controller.findImageByAlias(originalAlias);
    Image newImage = controller.createImageCopy(originalImage, newAlias);
    if (originalImage == null) {
      throw new IllegalArgumentException("Image with alias '" + originalAlias + "' not found.");
    }

    if (params[0].equals("value-component")) {
      Value v = new Value();
      v.execute(newImage, newAlias, controller);
    }
    if (params[0].equals("luma-component")) {
      Luma l = new Luma();
      l.execute(newImage, newAlias, controller);
    }
    if (params[0].equals("intensity-component")) {
      Intensity i = new Intensity();
      i.execute(newImage, newAlias, controller);
    }
    if (params[0].equals("grey-scale")) {
      Greyscale grey = new Greyscale();
      grey.execute(newImage, newAlias, controller);
    }

  }

}