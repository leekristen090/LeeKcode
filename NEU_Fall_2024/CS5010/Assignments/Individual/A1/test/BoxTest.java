import box.SimpleBoxSet;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

/**
 * This is a JUnit test for the methods in SimpleBoxSet.
 */
public class BoxTest {

  /**
   * We will create an instance of a SimpleBoxSet.
   */
  private SimpleBoxSet boxy;

  @Before
  public void setUp() {
    boxy = new SimpleBoxSet();
  }

  /**
   * This tests adding just one valid rectangle.
   */
  @Test
  public void testAddSingleNewRect() {
    boxy.add(0, 0, 10, 10);
    int[][] expected = {{0, 0, 10, 10}};
    assertArrayEquals(expected, boxy.getBoxes());
    assertEquals(1, boxy.size());
  }

  /**
   * This tests adding two non-overlapping rectangles.
   */
  @Test
  public void testAddTwoNonOverlapRect() {
    boxy.add(0, 0, 10, 10);
    boxy.add(15,15, 3,3);
    int[][] expected = {{0, 0, 10, 10}, {15, 15, 3, 3}};
    assertArrayEquals(expected, boxy.getBoxes());
    assertEquals(2, boxy.size());
  }

  /**
   * This tests adding rectangles that overlap. The second rectangle is added such that it overlaps
   * with the upper right corner of the original.
   */
  @Test
  public void testAddOverlappingRect() {
    boxy.add(0, 0, 10, 10);
    boxy.add(5,5, 10,10);

    int[][] expected = {
            {0, 0, 5, 10}, // rectangle left corner (0, 0) w = 5 and h = 10
            {5, 0, 5, 5}, // rectangle left corner(5, 0) w = 5 and height = 5
            {5, 5, 10, 10} // this is the newly added rect
    };
    assertArrayEquals(expected, boxy.getBoxes());
    assertEquals(3, boxy.size());
  }

  /**
   * This tests adding rectangles that overlap.The second rectangle is added such that it overlaps
   * the upper left corner of the original.
   */
  @Test
  public void testAddOverlappingRect2() {
    boxy.add(10, 3, 10, 10);
    boxy.add(5, 6, 10, 10);

    int[][] actual = boxy.getBoxes();
    // Print the actual result for debugging
    for (int[] rect : actual) {
      System.out.println(java.util.Arrays.toString(rect));
    }

    int[][] expected = {
            {15, 3, 5, 10}, // rectangle left corner (15, 3) w = 5 and h = 10
            {10, 3, 5, 3}, // rectangle left corner(10, 3) w = 5 and height = 1
            {5, 6, 10, 10} // this is the newly added rect
    };
    assertArrayEquals(expected, boxy.getBoxes());
    assertEquals(3, boxy.size());
  }

  /**
   * This tests adding a smaller rectangle into a larger rectangle.
   */
  @Test
  public void testAddSmallRectInLarge() {
    boxy.add(0, 0, 10, 10);
    boxy.add(5,5, 2,3);

    int[][] actual = boxy.getBoxes();
    // Print the actual result for debugging
    for (int[] rect : actual) {
      System.out.println(java.util.Arrays.toString(rect));
    }

    int[][] expected = {
            {0, 0, 5, 10}, // rectangle left corner coordinate (0, 0) w = 5 and h = 10
            {7, 0, 3, 10}, // rectangle left corner coordinate (7, 0) w = 3 and h = 10
            {5, 0, 2, 5}, // rectangle left corner coordinate (0, 0) w = 5 and h = 10
            {5, 8, 2, 2}, // rectangle left corner coordinate (0, 0) w = 5 and h = 10
            {5, 5, 2, 3} // this is the newly added rect
    };
    assertArrayEquals(expected, boxy.getBoxes());
    assertEquals(5, boxy.size());
  }

  /**
   * This tests subtracting a rectangle from a non overlapping set.
   */
  @Test
  public void testSubNonOverlappingRect() {
    boxy.add(0, 0, 10, 10);
    boxy.add(15, 15, 3, 3);
    boxy.subtract(15,15, 3,3);

    int[][] expected = {{0, 0, 10, 10}};
    assertArrayEquals(expected, boxy.getBoxes());
    assertEquals(1, boxy.size());
  }

  /**
   * This tests subtracting a rectangle from an overlapping set. This subtracts the second
   * rectangle such that it takes away the upper right corner of the original.
   */
  @Test
  public void testSubOverlappingRect() {
    boxy.add(0, 0, 10, 10);
    boxy.subtract(5, 5,10, 10);

    int[][] actual = boxy.getBoxes();
    // Print the actual result for debugging
    for (int[] rect : actual) {
      System.out.println(java.util.Arrays.toString(rect));
    }

    int[][] expected = {
            {0, 0, 5, 10}, // rect coordinate (0, 0) with w = 5 and h = 10
            {5, 0, 5, 5} // rect coordinate (5, 0) with w = 5 and h = 5
    };
    assertArrayEquals(expected, boxy.getBoxes());
    assertEquals(2, boxy.size());
  }

  /**
   * This tests subtracting a rectangle from an overlapping set. This subtracts the second
   * rectangle such that it takes away the lower left corner of the original.
   */
  @Test
  public void testSubOverlappingRect2() {
    boxy.add(5, 5,10, 10);
    boxy.subtract(0, 0, 10, 10);
    int[][] actual = boxy.getBoxes();
    // Print the actual result for debugging
    for (int[] rect : actual) {
      System.out.println(java.util.Arrays.toString(rect));
    }

    int[][] expected = {
            {10, 5, 5, 10}, // rect coordinate (10, 5) with w = 5 and h = 10
            {5, 10, 5, 5} // rect coordinate (5, 10) with w = 5 and h = 5
    };
    assertArrayEquals(expected, boxy.getBoxes());
    assertEquals(2, boxy.size());
  }

  /**
   * This is testing the subtraction of a smaller rectangle from a larger one.
   */
  @Test
  public void testSubSmallRectFromLarge() {
    boxy.add(0, 0, 10, 10);
    boxy.add(5, 5, 2, 3);
    boxy.subtract(5, 5, 2, 3);

    int[][] expected = {
            {0, 0, 5, 10}, // rectangle left corner coordinate (0, 0) w = 5 and h = 10
            {7, 0, 3, 10}, // rectangle left corner coordinate (7, 0) w = 3 and h = 10
            {5, 0, 2, 5}, // rectangle left corner coordinate (5, 0) w = 2 and h = 5
            {5, 8, 2, 2} // rectangle left corner coordinate (5, 8) w = 2 and h = 2
    };

    int[][] actual = boxy.getBoxes();
    // Print the actual result for debugging
    for (int[] rect : actual) {
      System.out.println(java.util.Arrays.toString(rect));
    }
    assertEquals(4, boxy.size());
  }

  /**
   * This is testing methods with exceptions.
   */
  @Test
  public void testValidRect() {
    try {
      boxy.add(0, 0, 10, 10);
      // this test for normal object
    } catch (IllegalArgumentException e) {
      fail("An exception should not have been thrown!");
    }
    try {
      boxy.add(0, 0, -10, 10);
      boxy.add(0, 0, 10, -10);
      fail("This should have thrown an exception");
    } catch (IllegalArgumentException e) {
      // do nothing except catch the exception and let test continue
    }
    try {
      boxy.subtract(0, 0, -10, 10);
      boxy.subtract(0, 0, 10, -10);
      fail("This should have thrown an exception");
    } catch (IllegalArgumentException e) {
      // do nothing except catch the exception and let test continue
    }
  }

  /**
   * This is testing if the set initializes to 0 rectangles.
   */
  @Test
  public void testInitialSize() {
    assertEquals(0, boxy.size());
  }
}