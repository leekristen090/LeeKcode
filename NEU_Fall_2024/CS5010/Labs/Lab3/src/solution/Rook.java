package solution;

/**
 * This class represents Rook which extends AbstractChessPiece.
 */
public class Rook extends AbstractChessPiece {

  /**
   * Constructing a Rook piece which has a row and column that it is placed in as well as a color.
   * @param row the row on the board
   * @param col the column on the board
   * @param color the color of the chess piece
   */
  public Rook(int row, int col, Color color) {
    super(row, col, color); // calling parent AbstractChessPiece
  }

  /**
   * Can this chess piece be moved from its current location to the location (row,col).
   * @param row the row to which this chess piece can be moved
   * @param col the col to which this chess piece can be moved
   * @return true if it can be moved to this position, false otherwise
   */
  @Override
  public boolean canMove(int row, int col) {
    if ((row < 0) || (col < 0) || (row >= 8) || (col >= 8)) {
      return false;
    }
    return ((this.getRow() == row) || (this.getColumn() == col));
  }
}
