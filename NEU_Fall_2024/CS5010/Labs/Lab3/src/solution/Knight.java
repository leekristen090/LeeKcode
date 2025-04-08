package solution;

/**
 * This class represents Knight which extends AbstractChessPiece.
 */
public class Knight extends AbstractChessPiece {

  /**
   * Constructing a Knight piece which has a row and column that it is placed in as well as a color.
   * @param row the row on the board
   * @param col the column on the board
   * @param color the color of the chess piece
   */
  public Knight(int row, int col, Color color) {
    super(row, col, color); // calling parent AbstractChessPiece
  }

  /**
   * Can this chess piece be moved from its current location to the location (row,col).
   * A knight moves in L shape.
   * @param row the row to which this chess piece can be moved
   * @param col the col to which this chess piece can be moved
   * @return true if it can be moved to this position, false otherwise
   */
  @Override
  public boolean canMove(int row, int col) {
    if ((row < 0) || (col < 0) || (row >= 8) || (col >= 8)) {
      return false;
    }
    // knight moves in L shape? 2 right 1 up/down or 2 left 1 up/down
    return ((this.getRow() == row) || (this.getColumn() == col));
  }
}