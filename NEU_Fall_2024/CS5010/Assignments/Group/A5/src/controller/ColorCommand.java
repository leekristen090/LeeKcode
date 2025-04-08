package controller;

/**
 * This is the color command class which implements the command interface. this helps to apply the
 * color logic.
 */
public class ColorCommand implements Command {

  /**
   * Apply the color logic to the image using the command line input.
   * @param params command line/script
   */
  @Override
  public void execute(String[] params) {
    try {
      CommandFactory.applyColor(params);
    } catch (Exception e) {
      System.err.println("Error applying color command: " + e.getMessage());
    }
  }

}
