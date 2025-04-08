package solution;

/**
 * This class represents the pawn chess piece.
 */
public class Pawn extends AbstractChessPiece {


  public Pawn(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color, "pawn");
  }

  @Override
  public boolean canMove(int row, int col) {
    if (!super.canMove(row, col)) {
      return false;
    }
    return ((this.col == col)
            && (((color == Color.BLACK) && (row == this.row - 1)) ||
                ((color == Color.WHITE) && (row == this.row + 1))));
  }

  @Override
  public boolean canKill(ChessPiece piece) {
    if (this.getColor() == piece.getColor()) {
      return false;
    }
    return (((color == Color.BLACK) && (piece.getRow() == this.row - 1)
             && (Math.abs(piece.getColumn() - this.getColumn()) == 1)) ||
            ((color == Color.WHITE) && (piece.getRow() == this.row + 1)
             && (Math.abs(piece.getColumn() - this.getColumn()) == 1)));
  }
}
