package controller;

/**
 * This is the flip command class which implements the command interface.
 */
public class FlipCommand implements Command {

  /**
   * this applies the flip logic to the image.
   * @param params command line
   */
  @Override
  public void execute(String[] params) {
    try {
      CommandFactory.flip(params);
    } catch (Exception e) {
      System.err.println("Error applying flip command: " + e.getMessage());
    }
  }

}