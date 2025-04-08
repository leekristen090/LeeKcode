package spreadsheet;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the mock spreadsheet class which implements the methods in better spreadsheet.
 */
public class MockSpreadSheet implements BetterSpreadSheet {
  private final List<String> log;

  public MockSpreadSheet() {
    this.log = new ArrayList<>();
  }

  /**
   * Get the number at the specified cell.
   *
   * @param row the row number of the cell, starting with 0
   * @param col the column number of the cell, starting with 0
   * @return the number at the specified cell, as a double. If the cell is empty, it returns a 0
   * @throws IllegalArgumentException if the row or column are negative
   */
  @Override
  public double get(int row, int col) throws IllegalArgumentException {
    log.add("get(" + row + ", " + col + ")");
    return 0;
  }

  /**
   * Set the value of the specified cell to the specified value.
   *
   * @param row   the row number of the cell, starting with 0
   * @param col   the column number of the cell, starting at 0
   * @param value the value that this cell must be set to
   * @throws IllegalArgumentException if the row or column are negative
   */
  @Override
  public void set(int row, int col, double value) throws IllegalArgumentException {
    log.add("set(" + row + ", " + col + ", " + value + ")");
  }

  /**
   * Returns whether the specified cell is empty.
   *
   * @param row the row number of the cell, starting with 0
   * @param col the column number of the cell, starting with 0
   * @return true if the cell is empty, false otherwise
   * @throws IllegalArgumentException if the row or column are negative
   */
  @Override
  public boolean isEmpty(int row, int col) throws IllegalArgumentException {
    log.add("isEmpty(" + row + ", " + col + ")");
    return true;
  }

  /**
   * Return the width of this spreadsheet. The width is defined by the cell with
   * the highest column number that is not empty.
   *
   * @return the width of this spreadsheet
   */
  @Override
  public int getWidth() {
    return 0;
  }

  /**
   * Return the height of this spreadsheet. The height is defined by the cell with
   * the highest row number that is not empty.
   *
   * @return the height of this spreadsheet
   */
  @Override
  public int getHeight() {
    return 0;
  }

  public List<String> getLog() {
    return log;
  }

  public void clearLog() {
    log.clear();
  }

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
    log.add("setRegion(" + startRow + ", " + startColumn + ", " + endRow + ", " + endColumn + ", "
            + value + ")");
  }
}