package spreadsheet;

/**
 * This is the better sparse spreadsheet which extends the existing sparse spreadsheet and
 * implements the better spreadsheet interface.
 */
public class BetterSparseSpreadSheet extends SparseSpreadSheet implements BetterSpreadSheet {

  /**
   * Add a new method that takes a rectangular region in the spreadsheet (e.g. from (0,0) to (1,3))
   * and a value, and sets all cells in that region to the specified value.
   *
   * @param startRow    starting row of the region
   * @param startColumn starting column of the region
   * @param endRow      ending row of the region
   * @param endColumn   ending column of the region
   * @param value       value to set in all cells of the region
   * @throws IllegalArgumentException if region is invalid
   */
  @Override
  public void setRegion(int startRow, int startColumn, int endRow, int endColumn, double value)
          throws IllegalArgumentException {
    if (startRow < 0 || startColumn < 0 || endRow < startRow || endColumn < startColumn) {
      throw new IllegalArgumentException("Invalid region specified");
    }

    for (int row = startRow; row <= endRow; row++ ) {
      for (int col = startColumn; col <= endColumn; col++) {
        this.set(row,col,value);
      }
    }
  }

}