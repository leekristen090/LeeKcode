/**
 * This is the abstract command class which implements the Command interface and its methods.
 */
public abstract class AbstractCommand implements Command {
  protected ImCont controller;

  /**
   * Construction of abstract command.
   * @param controller the controller
   */
  public AbstractCommand(ImCont controller) {
    this.controller = controller;
  }

  /**
   * Accepting parameters from commands.
   * @param params from command
   */
  @Override
  public abstract void execute(String[] params);
}
