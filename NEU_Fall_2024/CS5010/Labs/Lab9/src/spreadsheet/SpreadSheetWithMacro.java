package spreadsheet;

/**
 * This is the spreadsheet with macro interface which extends the spreadsheet interface. This is
 * the "command" interface for spreadsheet macro.
 */
public interface SpreadSheetWithMacro extends SpreadSheet {

  /**
   * This will accept an object of macro interface so that the function object passed to it is
   * executed on this spreadsheet.
   * @param macro the macro spreadsheet
   */
  void executeMacro(SpreadSheetMacro macro);

}