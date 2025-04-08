package controller;

/**
 * This is the brightness command class which implements the command interface.
 */
public class BrightnessCommand implements Command {

  /**
   * this executes the brightness logic.
   * @param params command line
   */
  @Override
  public void execute(String[] params) {
    try {
      CommandFactory.applyBrightness(params);
    } catch (Exception e) {
      System.err.println("Error applying brightness: " + e.getMessage());
    }
  }
}