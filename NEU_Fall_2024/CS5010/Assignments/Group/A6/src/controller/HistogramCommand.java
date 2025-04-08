package controller;

/**
 * This is the histogram command class which implements the command interface.
 */
public class HistogramCommand implements Command {

  /**
   * this applies the histogram logic from input from the command line.
   * @param params command line
   */
  @Override
  public void execute(String[] params) {
    try {
      CommandFactory.applyHistogram(params);
    } catch (Exception e) {
      System.err.println("Error applying histogram command: " + e.getMessage());
    }
  }
}