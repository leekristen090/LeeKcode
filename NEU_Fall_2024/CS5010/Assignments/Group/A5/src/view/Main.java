package view;

import java.io.IOException;

import controller.ImageController;
import controller.Interactive;
import controller.ScriptExecutor;

/**
 * This is our main class in which we call our interactive class to allow for interactive command
 * line input from the user.
 */
public class Main {

  /**
   * This is our main class so we can do interactive user input.
   * @param args
   */
  public static void main(String[] args) {

    ImageController controller = new ImageController();
    ScriptExecutor scriptExecutor = new ScriptExecutor(controller);
    Interactive interactive = new Interactive(controller, scriptExecutor);

    try {
      scriptExecutor.executeScript("../res/Script.txt");
      //interactive.executeInteractive();
    } catch (IOException e) {
      System.err.println("Error executing script: " + e.getMessage());
    }
  }


}

