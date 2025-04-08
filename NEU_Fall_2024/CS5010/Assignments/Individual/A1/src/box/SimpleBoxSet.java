package box;

/**
 * This class represents a SimpleBoxSet which implements BoxSet.
 */
public final class SimpleBoxSet implements BoxSet {

  /**
   * This is the SimpleBoxSet class which implements the BoxSet interface. Here we will use these
   * methods to create sets of rectangles which have an x-coordinate, y-coordinate, width, and
   * height.
   */

  private int [][] rectangle;
  private int n; // number of rectangles

  public SimpleBoxSet() {
    rectangle = new int [10][4]; // make array with space for 10 rectangles to start
    n = 0; // we start with no rectangles
  }

  /**
   * Here we will add a rectangle to a set.
   * @param x x value of given box from its lower left corner
   * @param y y value of a given box from its lower left corner
   * @param width width of given box
   * @param height height of given box
   * @throws IllegalArgumentException if the width or height is a negative number
   */
  @Override
  public void add(int x, int y, int width, int height)
          throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("No negative values allowed!");
      // making sure we have a valid width and height (no negative numbers)
    }

    int[] newRect = new int[]{x, y, width, height};
    int[][] newRectangles = new int[rectangle.length][4];
    int newSize = 0;

    for (int i = 0; i < n; i++) {
      // we must check for overlapping rectangles when we add
      if (isOverlap(rectangle[i], newRect)) {
        System.out.println("they overlap!");
        /* if they overlap before we add we need to find the contained difference as shown in the
        assignment description */
        int[] inter = intersect(rectangle[i], newRect);
        int[][] difference = getContDiff(rectangle[i], inter);
        for (int [] diff : difference) { // adding non-overlapping rectangles to set
          if (diff != null) {
            if (newSize == newRectangles.length) { // if newRectangle is full then we grow array
              newRectangles = grow(newRectangles);
            }
            newRectangles[newSize++] = diff;
          }
        }
      } else { // if the rectangles don't overlap just add it to the set without modification
        System.out.println("they don't overlap!");
        if (newSize == newRectangles.length) {
          newRectangles = grow(newRectangles);
        }
        newRectangles[newSize++] = rectangle[i];
      }
    }

    if (newSize == newRectangles.length) {
      newRectangles = grow(newRectangles);
    }
    newRectangles[newSize++] = newRect;

    // updating rectangle
    rectangle = new int[newSize][4];
    // copying contents of newRectangles to rectangle
    System.arraycopy(newRectangles,0, rectangle, 0, newSize);
    n = newSize; // update size b/c of the newly added rectangle
  }

  /**
   * Here we will subtract a rectangle from a set.
   * @param x x value of given box from its lower left corner
   * @param y y value of a given box from its lower left corner
   * @param width width of given box
   * @param height height of given box
   * @throws IllegalArgumentException if the width or height is a negative number
   */
  @Override
  public void subtract(int x, int y, int width, int height)
          throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("No negative values allowed here!");
      // making sure we have a valid width and height (no negative numbers)
    }
    int[] sub = new int[]{x, y, width, height};
    int[][] endSub = new int [rectangle.length][4];
    int newSize = 0;

    for (int i = 0; i < n; i++) {
      // check if overlapping
      if (isOverlap(rectangle[i], sub)) {
        int [] inter = intersect(rectangle[i], sub);
        // store the small pieces after a given rectangle is removed
        int[][] difference = getContDiff(rectangle[i], inter);
        for (int[] diff : difference) {
          if (diff != null) {
            if (newSize == endSub.length) {
              endSub = grow(endSub);
            }
            endSub[newSize++] = diff;
          }
        }
      } else { // no overlapping rectangles
        if (newSize == rectangle.length) {
          endSub = grow(endSub);
        }
        endSub[newSize++] = rectangle[i];
      }
    }
    rectangle = new int[newSize][4];
    System.arraycopy(endSub, 0, rectangle, 0, newSize);
    n = newSize;
  }

  /**
   * This will get all the rectangles that are in the set.
   * @return 2D array of rectangles represented by [x, y, width, height]
   */
  @Override
  public int[][] getBoxes() {
    /*
    from "What to do" on canvas assignment description where n is the number of rectangles
     */
    int [][] arr = new int[n][4];
    for (int i = 0; i < n; i++) {
      arr[i] = rectangle[i];
    }
    return arr;
  }

  /**
   * This will get us the number of boxes in a set.
   * @return number of boxes
   */
  @Override
  public int size() {
    return n;
  }

  /**
   * This method will help us to determine if a given rectangle overlaps with another rectangle.
   * @param rect1 our first given rectangle
   * @param rect2 our second given rectangle
   * @return tells us if two rectangles are overlapping or not
   */
  private boolean isOverlap(int[] rect1, int[] rect2) {
    return rect1[0] < rect2[0] + rect2[2] && rect1[0] + rect1[2] > rect2[0]
            && rect1[1] < rect2[1] + rect2[3] && rect1[1] + rect1[3] > rect2[1];
    /*
      Check if rect1 right edge is over left edge of rect2.
      Check if rect 1 left edge is left of rect 2 right edge.
      Check if rect 1 top is above rect 2 bottom
      Check if rect 1 bottom is below rect 2 top
      */
  }

  /**
   * This method will help us to determine where overlapping rectangles intersect.
   * @param rectangle1 first given rectangle
   * @param rectangle2 second given rectangle
   * @return the intersection point(s) of the rectangle, and if no intersection returns null
   */
  private int[] intersect(int[] rectangle1, int[] rectangle2) {
    int x1 = Math.max(rectangle1[0], rectangle2[0]);
    int x2 = Math.min(rectangle1[0] + rectangle1[2], rectangle2[0] + rectangle2[2]);
    int y1 = Math.max(rectangle1[1],rectangle2[1]);
    int y2 = Math.min(rectangle1[1] + rectangle1[3], rectangle2[1] + rectangle2[3]);

    // if the second rectangle has larger coordinate numbers that way we don't have negative numbers
    if (x1 < x2 && y1 < y2) {
      return new int[]{x1, y1, x2 - x1, y2 - y1};
    }
    // if there is no intersection then we do nothing
    return null;
  }

  private int[][] getContDiff(int[] rect, int[] intersection) {
    // if there is no intersection then we don't need to do anything
    if (intersection == null) {
      return new int[][]{rect};
    }

    // original rectangle
    int x = rect[0];
    int y = rect[1];
    int w = rect[2];
    int h = rect[3];
    // intersection points of
    int xInter = intersection[0];
    int yInter = intersection[1];
    int wInter = intersection[2];
    int hInter = intersection[3];

    int[][] rectSplit = new int[4][4];
    // the most we can split a rectangle is into four
    int size = 0;

    /*
    must calc the space surrounding a given rectangle. Could be surrounded on all four sides (top,
    bottom, left, right) or a different combo. In the assignment description it looks like these
    smaller rect were made left and right then top and bottom. Check left and right first then top
    and bottom
     */

    // if there is space to the LEFT of the rectangle intersection point
    // check if the left side of the original rectangle is outside the intersection
    if (x < xInter) {
      rectSplit[size++] = new int[]{x, y, xInter - x, h};
      // the width of the new rectangle is the diff. between the intersection and original rectangle
      // so the width is the original up until the intersection
      // keep the original rect coordinate and height
    }
    // if there is a space to the RIGHT of the rectangle intersection point
    // check if the original rectangle extends past the intersection on the right
    if (x + w > xInter + wInter) {
      rectSplit[size++] = new int[]{xInter + wInter, y, (x + w) - (xInter + wInter), h};
      // new x value is where the intersection begins
      // new width is the diff between the right edge of original and the intersection
      // height is unchanged so y and h remain same as original
    }
    /* if there is space BELOW the rectangle intersection point
     check if the original bottom is below the intersection bottom b/c if it does, then there is
     a portion of the original not overlapping which is below
     */
    int newWidth = Math.min(x + w, xInter + wInter) - Math.max(x, xInter);
    if (y < yInter) {
      rectSplit[size++] = new int[]{Math.max(x, xInter), y, newWidth, yInter - y};
      // new x value is right most value
      // y stays the same
      // width is right most point where either the orig or intersection ends
      // height intersection bottom minus original bottom
    }
    // if there is space ABOVE the rectangle intersection point
    // check if the original rectangle extends past the intersection rectangle
    if (y + h > yInter + hInter) {
      rectSplit[size++] = new int[]{Math.max(x, xInter), yInter + hInter, newWidth, (y + h)
              - (yInter + hInter)};
      // same computation as below but new y value is the top of the intersection rectangle
    }
    return trim(rectSplit, size); // include only valid rectangles
  }

  // increase array size if it is full
  private int[][] grow(int[][] arr) {
    int bigger = arr.length * 2;
    int [][] biggerArr = new int[bigger][4];
    System.arraycopy(arr, 0, biggerArr, 0, arr.length);
    return biggerArr;
  }

  private int[][] trim(int[][] arr, int size) {
    int[][] newArr = new int[size][4];
    System.arraycopy(arr, 0, newArr, 0, size);
    return newArr;
  }
}