package controller;

/**
 * This executes the applyColor() method with the corresponding parameters. It calls red-component,
 * green-component, or blue-component accordingly.
 */
public class ColorCommand implements Command {

  @Override
  public void execute(String[] params) {
    try {
      CommandFactory.applyColor(params);
    } catch (Exception e) {
      System.err.println("Error applying sepia: " + e.getMessage());
    }
  }

}