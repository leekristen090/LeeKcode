package controller;


/**
 * This is the sepia command class which helps to apply sepia logic to an image. It implements the
 * methods in the command interface.
 */
public class SepiaCommand implements Command  {

  /**
   * Apply the sepia logic to a given image given the command string line.
   * @param params command line
   */
  @Override
  public void execute(String[] params) {
    try {
      CommandFactory.applySepia(params);
    } catch (Exception e) {
      System.err.println("Error applying sepia: " + e.getMessage());
    }
  }

}