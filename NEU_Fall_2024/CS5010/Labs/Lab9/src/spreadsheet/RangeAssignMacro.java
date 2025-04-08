package spreadsheet;

/**
 * This is the range assign macro class.
 * From Canvas:
 * This will set a row or column of cells to a range of values starting at the given value and
 * advancing by the given increment.
 */
public class RangeAssignMacro implements SpreadSheetMacro {
  private final int startRow;
  private final int startCol;
  private final int endRow;
  private final int endCol;
  private final double startVal;
  private final double increment;

  /**
   * Construct the range assign macro. There will be a starting cell, ending cell, a starting value
   * for the first cell, and an increment.
   */
  public RangeAssignMacro(int startRow, int startCol, int endRow, int endCol, double startVal,
                          double increment) {
    if (startRow < 0 || endRow < 0 || startCol < 0 || endCol < 0 || startRow > endRow
            || startCol > endCol) {
      throw new IllegalArgumentException("startRow and endRow are out of bounds");
    }
    this.startRow = startRow;
    this.startCol = startCol;
    this.endRow = endRow;
    this.endCol = endCol;
    this.startVal = startVal;
    this.increment = increment;
  }

  /**
   * This method takes in a spreadsheet object and executes a macro on it. This will set a row or
   * column of cells to a range of values starting at the given value and advancing by the given
   * increment.
   * For example:
   * range-assign A 1 A 10 1 1 will assign A1:A10 to values 1, 2, 3, ..., 10 respectively.
   *
   * @param sheet our given spreadsheet
   */
  @Override
  public void execute(SpreadSheet sheet) {
    double currentVal = startVal;
    for (int row = startRow; row <= endRow; row++) {
      for (int col = startCol; col <= endCol; col++) {
        sheet.set(row, col, currentVal);
        currentVal += increment;
        //System.out.println("Value at (" + row + ", " + col + "): " + sheet.get(row, col));
      }
    }
  }
}