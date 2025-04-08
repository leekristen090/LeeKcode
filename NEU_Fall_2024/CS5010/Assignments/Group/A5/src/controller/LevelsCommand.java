package controller;

/**
 * This is the levels command class which implements the command interface.
 */
public class LevelsCommand implements Command {

  /**
   * this applies the adjust levels logic to the image.
   * @param params command line
   */
  @Override
  public void execute(String[] params) {
    try {
      CommandFactory.applyLevels(params);
    } catch (Exception e) {
      System.err.println("Error applying levels command: " + e.getMessage());
    }
  }

}