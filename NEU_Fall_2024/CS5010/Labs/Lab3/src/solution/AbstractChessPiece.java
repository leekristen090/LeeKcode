package solution;

/**
 * This is AbstractChessPiece which implements the ChessPiece interface.
 */
public abstract class AbstractChessPiece implements ChessPiece {
  int row;
  public int column;
  Color color;

  /**
   * Constructing an AbstractChessPiece that is somewhere on the board (row, column) and this piece
   * also has a color.
   * @param row the row on the chess board
   * @param column the column on the chess board
   * @param color the color of the AbstractChessPiece
   * @throws IllegalArgumentException if the piece is no within the bounds of the chess board
   */
  public AbstractChessPiece(int row, int column, Color color) throws IllegalArgumentException {
    if ((row < 0) || (column < 0) || (row >= 8) || (column >= 8)) {
      throw new IllegalArgumentException("Illegal position.");
    }
    this.row = row;
    this.column = column;
    this.color = color;
  }

  /**
   * Get the row of the current position of this piece. Rows begin with 0.
   * Same implementations in Bishop, Queen, and Rook so we move it to the abstract class.
   * @return the row in which a chess piece is in
   */
  @Override
  public int getRow() {return row;}

  /**
   * Get the column of the current position of this piece Columns begin with 0.
   * Same implementations in Bishop, Queen, and Rook so we move it to the abstract class.
   *
   * @return the column of the current position of this piece
   */
  @Override
  public int getColumn() {return column;}

  /**
   * Get the color of this piece. The color can be one of WHITE or BLACK.
   * Same implementations in Bishop, Queen, and Rook so we move it to the abstract class.
   *
   * @return the color of this chess piece
   */
  @Override
  public Color getColor(){return color;}

  /**
   * Can this chess piece be moved from its current location to the location (row,col).
   * Similar but different implementations in Bishop, Queen, and Rook so we make it abstract.
   *
   * @param row the row to which this chess piece can be moved
   * @param col the col to which this chess piece can be moved
   * @return true if it can be moved to this position, false otherwise
   */
  @Override
  public abstract boolean canMove(int row, int col);

  /**
   * Can this chess piece kill the chess piece passed to this method?. Same implementations in
   * Bishop, Queen, and Rook so we move it to the abstract class.
   *
   * @param piece the piece that may or may not be killed by this piece
   * @return true if this piece can kill the other, false otherwise
   */
  @Override
  public boolean canKill(ChessPiece piece) {
    return (this.getColor() != piece.getColor()) && canMove(piece.getRow(), piece.getColumn());
  }
}