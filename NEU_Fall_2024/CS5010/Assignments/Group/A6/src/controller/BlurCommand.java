
package controller;

/**
 * This executes the applyBlur() method with the corresponding parameters.
 */
public class BlurCommand implements Command {

  @Override
  public void execute(String[] params) {
    try {
      CommandFactory.applyBlur(params);
    } catch (Exception e) {
      System.err.println("Error applying sepia: " + e.getMessage());
    }
  }
}
