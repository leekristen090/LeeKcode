package spreadsheet;

/**
 * This is the spreadsheet with macro implementation class which reuses the provided spreadsheet
 * implementation in some way. Added a method void executeMacro(SpreadSheetMacro macro) that will
 * accept an object of macro interface and execute it on the spreadsheet.
 */
public class SpreadSheetWithMacroImpl implements SpreadSheetWithMacro {
  private final SpreadSheet sheet; // Delegate to existing sheet

  /**
   * Spreadsheet with macro implementation constructor.
   */
  public SpreadSheetWithMacroImpl() {
    this.sheet = new SparseSpreadSheet();
  }

  /**
   * This will accept an object of macro interface so that the function object passed to it is
   * executed on this spreadsheet.
   * @param macro the macro spreadsheet
   */
  @Override
  public void executeMacro(SpreadSheetMacro macro) {
    macro.execute(sheet); // Delegate the macro execution to the given macro
  }

  /**
   * Get the number at the specified cell.
   * @param row the row number of the cell, starting with 0
   * @param col the column number of the cell, starting with 0
   * @return the number at the specified cell, as a double. If the cell is empty, it returns a 0
   * @throws IllegalArgumentException if the row or column are negative
   */
  @Override
  public double get(int row, int col) {
    return sheet.get(row, col);
  }

  /**
   * Set the value of the specified cell to the specified value.
   * @param row the row number of the cell, starting with 0
   * @param col the column number of the cell, starting at 0
   * @param value the value that this cell must be set to
   * @throws IllegalArgumentException if the row or column are negative
   */
  @Override
  public void set(int row, int col, double value) {
    sheet.set(row, col, value);
  }

  /**
   * Returns whether the specified cell is empty.
   * @param row the row number of the cell, starting with 0
   * @param col the column number of the cell, starting with 0
   * @return true if the cell is empty, false otherwise
   * @throws IllegalArgumentException if the row or column are negative
   */
  @Override
  public boolean isEmpty(int row, int col) {
    return sheet.isEmpty(row, col);
  }

  /**
   * Return the width of this spreadsheet. The width is defined by the cell with
   * the highest column number that is not empty.
   * @return the width of this spreadsheet
   */
  @Override
  public int getWidth() {
    return sheet.getWidth();
  }

  /**
   * Return the height of this spreadsheet. The height is defined by the cell with
   * the highest row number that is not empty.
   * @return the height of this spreadsheet
   */
  @Override
  public int getHeight() {
    return sheet.getHeight();
  }
}