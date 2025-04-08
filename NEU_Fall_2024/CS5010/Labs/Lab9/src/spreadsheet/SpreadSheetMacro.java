package spreadsheet;

/**
 * Our new interface named SpreadSheetMacro that represents a macro. This is our "command"
 * interface. We will make our spreadsheet support a macro. A macro will be given to the spreadsheet
 * as a function object that operates on it. Thus, the spreadsheet needs to have only one additional
 * functionality: the ability to accept macros as function objects.
 */
public interface SpreadSheetMacro {

  /**
   * This method takes in a spreadsheet object and executes a macro on it.
   * @param sheet our given spreadsheet
   */
  void execute(SpreadSheet sheet);
}