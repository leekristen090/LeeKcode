package controller;

/**
 * This is the sharpen command class which helps to apply the sharpen logic to the image. This class
 * implements the methods in the command interface.
 */
public class SharpenCommand implements Command {

  /**
   * Apply the sharpen logic to an image given the command line string.
   * @param params command line
   */
  @Override
  public void execute(String[] params) {
    try {
      CommandFactory.applySharpen(params);
    } catch (Exception e) {
      System.err.println("Error applying sharpen command: " + e.getMessage());
    }
  }
}