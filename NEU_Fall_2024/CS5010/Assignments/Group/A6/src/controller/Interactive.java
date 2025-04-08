package controller;

import java.io.IOException;
import java.util.Scanner;

/**
 * This is our interactive class. This allows for interactive input from the command line from a
 * user. It takes the command and executes accordingly. User can also run a script, if they choose
 * to, from the command line. The user can also quit the program by typing exit.
 */
public class Interactive {

  private ImageController controller;
  private ScriptExecutor executor;

  /**
   * Construct our interactive with a controller and a script executor.
   * @param controller our controller
   * @param executor our script executor
   */
  public Interactive(ImageController controller, ScriptExecutor executor) {
    this.controller = controller;
    this.executor = executor;
  }

  /**
   * This is the logic to implement the interactive command line input from user. This is called
   * from the main class.
   * @throws IOException throw exception
   */
  public void executeInteractive() throws IOException {
    System.out.println("** Welcome to the image manipulation program! **");

    Scanner cin = new Scanner(System.in);

    while (true) {
      System.out.println("\nEnter '-file <script_path>' to run a script or enter commands "
              + "interactively.");
      System.out.println("Please enter your command (type 'exit' to quit): \n");
      String commandLine = cin.nextLine().trim();
      if (commandLine.equalsIgnoreCase("exit")) {
        System.out.println("Exiting program. Bye!");
        break;
      }
      String[] tokens = commandLine.split(" ");
      String commandName = tokens[0];
      if (commandName.equals("load")) {
        controller.loadImage(tokens[1], tokens[2]);
      } else if (commandName.equals("save")) {
        controller.saveImage(tokens[1], tokens[2]);
      } else if (commandLine.startsWith("-file")) {
        String scriptPath = commandLine.substring(5).trim();
        //goScript(scriptPath);
        executor.executeScript(scriptPath);
      } else {
        System.out.println("Running '" + commandLine + "'");
        Command command = CommandRegistry.commandMap.get(commandName);
        command.execute(tokens);
      }

    }
  }
}