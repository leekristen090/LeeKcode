package spreadsheet;

/**
 * This class represents the "bulk assign macro". This class will allow us to assign a specific
 * value to an entire range of cells
 */
public class BulkAssignMacro implements SpreadSheetMacro {

  private final int startRow;
  private final int startCol;
  private final int endRow;
  private final int endCol;
  private final double value;

  /**
   * This is the bulk assign constructor.
   * From canvas description:
   * Write a constructor that will take in the necessary arguments for this operation: the range of
   * cells to set, and the value to set them to. The constructor should have the usual checks for
   * invalid parameters.
   * @param startRow row to start
   * @param startCol col to start
   * @param endRow row to end
   * @param endCol col to end
   * @param value given value to set at given cell
   */
  public BulkAssignMacro(int startRow, int startCol, int endRow, int endCol, double value) {
    if (startRow < 0 || endRow < 0 || startCol < 0 || endCol < 0 || startRow > endRow
            || startCol > endCol) {
      throw new IllegalArgumentException("startRow and endRow are out of bounds");
    }
    this.startRow = startRow;
    this.startCol = startCol;
    this.endRow = endRow;
    this.endCol = endCol;
    this.value = value;
  }

  /**
   * This method takes in a spreadsheet object and executes a macro on it.
   * @param sheet our given spreadsheet
   */
  @Override
  public void execute(SpreadSheet sheet) {
    for (int row = startRow; row <= endRow; row++) {
      for (int col = startCol; col <= endCol; col++) {
        sheet.set(row, col, value);
      }
    }
  }
}