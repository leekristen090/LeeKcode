package controller;

/**
 * This is the split command class which helps to apply split image logic to an image. This class
 * implements the methods in the command interface.
 */
public class SplitCommand implements Command {

  /**
   * this executes the split logic given a command line string.
   * @param params command line
   */
  @Override
  public void execute(String[] params) {
    try {
      CommandFactory.applySplit(params);
    } catch (Exception e) {
      System.err.println("Error applying split command: " + e.getMessage());
    }
  }

}