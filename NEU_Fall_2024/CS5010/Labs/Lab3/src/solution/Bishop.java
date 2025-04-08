package solution;

/**
 * This class represents Bishop which extends AbstractChessPiece.
 */
public class Bishop extends AbstractChessPiece {

  /**
   * This is constructing a Bishop piece which has a row and column in which it is placed as well as
   * a color.
   * @param row the row on the chess board
   * @param col the column of the chess board
   * @param color the color of the chess piece
   */
  public Bishop(int row, int col, Color color)  {
    super(row, col, color); // calling parent AbstractChessPiece
  }

  /**
   * Can this chess piece be moved from its current location to the location
   * (row,col).
   * @param row the row to which this chess piece can be moved
   * @param col the col to which this chess piece can be moved
   * @return true if it can be moved to this position, false otherwise
   */
  @Override
  public boolean canMove(int row, int col) {
    if ((row < 0) || (col < 0) || (row >= 8) || (col >= 8)) {
      return false;
    }
    return (Math.abs(this.getRow() - row) == Math.abs(this.getColumn() - col));
  }
}
