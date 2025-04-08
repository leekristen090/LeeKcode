package box;

/**
 * This interface represents a set of non-overlapping axis-aligned boxes.
 */
public interface BoxSet {
  /**
   * This should throw an IllegalArgumentException if the box passed to it does not have a
   * positive width and height. This method does not return anything. That is, they change the
   * current set to the result. This is adding a rectangle to a set.
   * @param x x value of given box from its lower left corner
   * @param y y value of a given box from its lower left corner
   * @param width width of given box
   * @param height height of given box
   * @throws IllegalArgumentException if box has negative width and height
   */
  void add(int x,int y,int width,int height) throws IllegalArgumentException;

  /**
   * This should throw an IllegalArgumentException if the box passed to it does not have a
   * positive width and height. This method does not return anything. That is, they change the
   * current set to the result. This is subtracting a rectangle from a set.
   * @param x x value of given box from its lower left corner
   * @param y y value of a given box from its lower left corner
   * @param width width of given box
   * @param height height of given box
   * @throws IllegalArgumentException if box has negative width and height
   */
  void subtract(int x, int y, int width, int height) throws IllegalArgumentException;

  /**
   * From assignment description on Canvas:
   * This method should return an array with each element containing exactly four numbers: the x,
   * y, width and height of the rectangle in that order.
   */
  int [][] getBoxes();

  int size();
}
