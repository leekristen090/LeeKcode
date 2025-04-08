
package controller;

/**
 * This is the combine command class which implements the command interface.
 */
public class CombineCommand implements Command {

  /**
   * this executes the combine logic.
   * @param params command line
   */
  @Override
  public void execute(String[] params) {
    try {
      CommandFactory.applyCombine(params);
    } catch (Exception e) {
      System.err.println("Error applying sepia: " + e.getMessage());
    }
  }

}
