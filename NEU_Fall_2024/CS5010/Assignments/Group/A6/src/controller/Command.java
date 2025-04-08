package controller;

/**
 * This is the command interface. This represents a command that can executed with specific
 * parameters. Implementations of this interface encapsulate specific functionality that can
 * be triggered through the execute(String[]) method.
 */
public interface Command {

  /**
   * Executes the command using the given parameters. The parameters expected format and number of
   * parameters are defined by the specific implementations of this interface.
   * @param params of a string required for execution of the effect
   */
  void execute(String[] params);
}