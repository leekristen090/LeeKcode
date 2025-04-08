package spreadsheet;

/**
 * We are now extending our Lab 9 functionality with average following the same sequence of changes
 * as with bulk assign macro. This will compute the average of a range of cells and put it in the
 * specified destination cell. For example average A 1 B 10 C 2 computes the average of the 20
 * values in A1:B10 and stores it in the cell C2.
 * Support the following input command:
 * average from-row-num from-col-num to-row-num to-col-num dest-row-num dest-col-num.
 */
public class AverageMacro implements SpreadSheetMacro {

  private final int startRow;
  private final int startCol;
  private final int endRow;
  private final int endCol;
  private final int cRow;
  private final int cCol;

  /**
   * This is the average macro constructor.
   * @param startRow row to start range
   * @param startCol col to start range
   * @param endRow row to end range
   * @param endCol col to end range
   * @param cRow row where average is stored
   * @param cCol col where average is stored
   */
  public AverageMacro(int startRow, int startCol, int endRow, int endCol, int cRow, int cCol) {
    if (startRow < 0 || endRow < 0 || startCol < 0 || endCol < 0 || startRow > endRow
            || startCol > endCol) {
      throw new IllegalArgumentException("startRow and endRow are out of bounds");
    }
    this.startRow = startRow;
    this.startCol = startCol;
    this.endRow = endRow;
    this.endCol = endCol;
    this.cRow = cRow;
    this.cCol = cCol;
  }

  /**
   * This method takes in a spreadsheet object and executes a macro on it. We will be averaging a
   * given cell and storing that value in a different cell.
   * @param sheet our given spreadsheet
   */
  @Override
  public void execute(SpreadSheet sheet) {
    double sum = 0;
    int count = 0;
    for (int row = startRow; row <= endRow; row++) {
      for (int col = startCol; col <= endCol; col++) {
        double cellValue = sheet.get(row, col);
        //System.out.println("Value at (" + row + ", " + col + "): " + cellValue); // Debugging
        sum += cellValue;
        count++;
      }
    }
    double avg = sum / count;
    //System.out.println("Computed average: " + avg); // Debugging
    sheet.set(cRow, cCol, avg);
    //System.out.println("Value at (" + cRow + ", " + cCol + "): " + sheet.get(cRow, cCol));

  }

}