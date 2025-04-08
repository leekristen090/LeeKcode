package controller;

/**
 * This is the command interface.
 */
public interface Command {
  void execute(String[] params);
}