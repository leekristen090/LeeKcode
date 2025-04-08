package controller;

/**
 * This is the compress command class which implements the command interface.
 */
public class CompressCommand implements Command {

  /**
   * this executes the compression logic.
   * @param params command line
   */
  @Override
  public void execute(String[] params) {
    try {
      System.out.println("we are in CompressionCommand.java");
      CommandFactory.applyCompression(params);
    } catch (Exception e) {
      System.err.println("Error applying compression in CompressionCommand.java: " + e.getMessage());
    }
  }

}