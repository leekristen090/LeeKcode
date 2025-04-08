/**
 * This is combine command class which extends the abstract command abstract class.
 */
public class CombineCommand extends AbstractCommand {

  /**
   * Construct combine command.
   * @param controller controller
   */
  public CombineCommand(ImCont controller) {
    super(controller);
  }

  /**
   * Execute combine logic using the parameters given in the single line command.
   * @param params from command
   */
  @Override
  public void execute(String[] params) {
    /* rgb-combine image-name red-image green-image blue-image */
    String originalAlias = params[1];
    String redAlias = params[2];
    String greenAlias = params[3];
    String blueAlias = params[4];

    Image redImage = controller.findImageByAlias(redAlias);
    Image greenImage = controller.findImageByAlias(greenAlias);
    Image blueImage = controller.findImageByAlias(blueAlias);
    //Image newImage = new ConcreteImage(originalAlias, redImage.getHeight(), redImage.getWidth());
    Combine combo = new Combine();
    combo.execute(redImage, greenImage, blueImage, originalAlias, controller);
  }
}