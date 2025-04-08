package controller;

/**
 * This is the grey command class which is called for greyscale, luma, value, and intensity.
 */
public class GreyCommand implements Command {

  /**
   * Apply the grey logic to the image according to the command line input.
   * @param params input
   */
  @Override
  public void execute(String[] params) {
    try {
      CommandFactory.applyGrey(params);
    } catch (Exception e) {
      System.err.println("Error applying grey scale command: " + e.getMessage());
    }
  }
}