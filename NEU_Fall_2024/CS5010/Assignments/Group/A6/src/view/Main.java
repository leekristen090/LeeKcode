package view;

import java.io.IOException;

import controller.ImageController;
import controller.Interactive;
import controller.ScriptExecutor;

import javax.swing.SwingUtilities;

/**
 * The main class for the image processing application.
 * It accepts command-line arguments to determine the mode of operation:
 * - "-file path-of-script-file": Executes a script file and exits.
 * - "-text": Starts the program in interactive text mode.
 * - No arguments: Opens the graphical user interface (GUI).
 * Any other command-line arguments will result in an error message and program termination.
 */
public class Main {

  /**
   * This is our main class so we can do interactive user input.
   * @param args Command-line arguments to determine the mode of operation
   */
  public static void main(String[] args) {

    ImageController controller = new ImageController();
    ScriptExecutor scriptExecutor = new ScriptExecutor(controller);
    Interactive interactive = new Interactive(controller, scriptExecutor);

    if (args.length == 0) {
      launchGUI(controller);
    } else if (args.length == 2 && args[0].equalsIgnoreCase("-file")) {
      String filePath = args[1];
      runScript(scriptExecutor, filePath);
    } else if(args.length == 1 && args[0].equalsIgnoreCase("-text")) {
      runInteractive(interactive);
    } else {
      System.err.println("Invalid argument.");
      System.err.println("Usage: java -jar NameOfJAR.jar");
      System.err.println("Usage: java -jar NameOfJAR.jar [-file]");
      System.err.println("Usage: java -jar NameOfJAR.jar [-text]");
      System.exit(1);
    }

  }

  private static void launchGUI(ImageController controller) {
    SwingUtilities.invokeLater(() -> {
      //ImageController imageController = new ImageController();
      ImageGUIFrame gui = new ImageGUIFrame(null);
      FeatureController featureController = new FeatureController(controller, gui);
      gui.setFeatureController(featureController);
      gui.setVisible(true);
    });
  }

  private static void runScript(ScriptExecutor scriptExecutor, String scriptPath) {
    try {
      scriptExecutor.executeScript(scriptPath);
    } catch (IOException e) {
      System.err.println("Error executing script: " + e.getMessage());
    }
  }

  private static void runInteractive(Interactive interactive) {
    try {
      interactive.executeInteractive();
    } catch (IOException e) {
      System.err.println("Error in interactive mode: " + e.getMessage());
    }
  }

}

