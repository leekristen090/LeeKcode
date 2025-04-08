package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * This is the script executor class. Reads a given script from the file path and executes the
 * commands in the script file.
 */
public class ScriptExecutor {

  private ImageController controller;

  /**
   * Construct the script executor.
   * @param controller our controller.
   */
  public ScriptExecutor(ImageController controller) {
    this.controller = controller;
    new CommandRegistry();
  }

  /**
   * Read the string lines in the script and execute the commands in the script accordingly.
   * @param scriptPath given script file path
   * @throws IOException exception
   */
  public void executeScript(String scriptPath) throws IOException {
    try (Scanner scanner = new Scanner(new File(scriptPath))) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine().trim();

        if (line.startsWith("#") || line.isEmpty()) {
          continue;
        }

        String[] tokens = line.split(" ");
        String commandName = tokens[0];

        if (commandName.equals("load")) {
          controller.loadImage(tokens[1], tokens[2]);
        } else if (commandName.equals("save")) {
          controller.saveImage(tokens[1], tokens[2]);
        } else {
          Command command = CommandRegistry.commandMap.get(commandName);
          if (command != null) {
            command.execute(tokens);
          } else {
            System.out.println("Unknown command: " + commandName);
          }
        }

      }

    } catch (FileNotFoundException e) {
      System.out.println("Script file not found: " + scriptPath);
    }
  }

}