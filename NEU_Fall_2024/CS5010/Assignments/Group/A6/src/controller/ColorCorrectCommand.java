package controller;

/**
 * This is the color correct command class which implements the command interface.
 */
public class ColorCorrectCommand implements Command {

  /**
   * this executes the color correct logic.
   * @param params command line
   */
  @Override
  public void execute(String[] params) {
    try {
      CommandFactory.applyCorrect(params);
    } catch (Exception e) {
      System.err.println("Error applying color correct: " + e.getMessage());
    }
  }

}