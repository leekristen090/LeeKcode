package controller;

/**
 * This is the blur command class which implements the command interface. Which helps to apply the
 * blur logic to an image.
 */
public class BlurCommand implements Command {

  /**
   * We apply the command using the given parameters from the command line.
   * @param params from command line/script
   */
  @Override
  public void execute(String[] params) {
    try {
      CommandFactory.applyBlur(params);
    } catch (Exception e) {
      System.err.println("Error applying blur: " + e.getMessage());
    }
  }
}