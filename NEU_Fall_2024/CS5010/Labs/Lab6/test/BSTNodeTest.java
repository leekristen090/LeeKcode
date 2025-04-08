
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


import solution.BSTEmptyNode;
import solution.BSTNode;
import solution.NothingThereException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is the JUnit test class for the methods in lab6.
 */
public class BSTNodeTest {

  /**
   * This is the given test for testing insertions.
   */
  @Test
  public void testInsertions() {
    BSTNode<Integer> root = new BSTEmptyNode<Integer>();
    Set<Integer> expected = new TreeSet<Integer>();

    for (int i = 0; i < 1000; i++) {
      expected.add((int)(Math.random() * 2000 - 1000));
    }

    for (Integer e:expected) {
      root = root.insert(e);
    }

    StringBuilder sb = new StringBuilder();
    for (Integer e:expected) {
      sb.append(e + " ");
    }
    String output = sb.toString();
    output = output.substring(0, output.length() - 1);

    assertEquals(output, root.toString());
  }

  /**
   * Given test for min and max.
   */
  @Test
  public void testMinMax() {
    BSTNode<Integer> root = new BSTEmptyNode<Integer>();
    TreeSet<Integer> expected = new TreeSet<Integer>();

    for (int i = 0; i < 1000; i++) {
      expected.add((int)(Math.random() * 2000 - 1000));
    }

    for (Integer e:expected) {
      root = root.insert(e);
    }

    assertEquals(expected.first(), root.minimum());
    assertEquals(expected.last(), root.maximum());
  }

  @Test (expected = NothingThereException.class)
  public void testMinWhenEmpty() {
    new BSTEmptyNode<Integer>().minimum();
  }

  @Test (expected = NothingThereException.class)
  public void testMaxWhenEmpty() {
    new BSTEmptyNode<Integer>().maximum();
  }

  /**
   * Given test for contains.
   */
  @Test
  public void testContains() {
    BSTNode<Integer> root = new BSTEmptyNode<>();
    List<Integer> expected = new ArrayList<Integer>();

    for (int i = 0; i < 1000; i++) {
      expected.add((int)(Math.random() * 2000 - 1000));
    }

    for (Integer e:expected) {
      root = root.insert(e);
    }


    for (int i = -1000; i <= 1000; i++) {
      assertEquals(expected.contains(i),root.contains(i));
    }
  }

  /*
                              10
                   5                   15
           1             7                      20
                2     6.      8               19
    */

  /**
   * The given test on preorder.
   */
  @Test
  public void testPreorder() {
    BSTNode<Integer> root = new BSTEmptyNode<>();

    root = root.insert(10);
    root = root.insert(15);
    root = root.insert(5);
    root = root.insert(1);
    root = root.insert(2);
    root = root.insert(7);
    root = root.insert(6);
    root = root.insert(8);
    root = root.insert(20);
    root = root.insert(19);
    List<Integer> expectedList = Arrays.asList(10,5,1,2,7,6,8,15,20,19);

    List<Integer> actualList = new ArrayList<Integer>();

    root.preorder(i -> actualList.add(i));

    assertEquals(expectedList, actualList);
  }

  /**
   * Testing preorder on an empty tree.
   */
  @Test
  public void testEmptyPreorder() {
    BSTNode<Integer> root = new BSTEmptyNode<>();
    List<Integer> actualList = new ArrayList<Integer>();
    List<Integer> expectedList = new ArrayList<Integer>();

    root.preorder(i -> actualList.add(i));
    assertEquals(expectedList, actualList);
  }

  /**
   * Additional tests for preorder, here we are counting the nodes in the tree.
   */
  @Test
  public void testPreorderCount() {
    BSTNode<Integer> root = new BSTEmptyNode<>();

    root = root.insert(10);
    root = root.insert(15);
    root = root.insert(5);
    root = root.insert(1);
    root = root.insert(2);
    root = root.insert(7);
    root = root.insert(6);
    root = root.insert(8);
    root = root.insert(20);
    root = root.insert(19);

    final int[] count = {0};
    root.preorder(i -> count[0]++);
    int expectedCount = 10;
    assertEquals(expectedCount, count[0]);
  }

  /*
                                10
                     5                   15
             1             7                      20
                  2     6.      8               19
    */

  /**
   * The given test for postorder.
   */
  @Test
  public void testPostorder() {
    BSTNode<Integer> root = new BSTEmptyNode<>();

    root = root.insert(10);
    root = root.insert(15);
    root = root.insert(5);
    root = root.insert(1);
    root = root.insert(2);
    root = root.insert(7);
    root = root.insert(6);
    root = root.insert(8);
    root = root.insert(20);
    root = root.insert(19);

    List<Integer> expectedList = Arrays.asList(2,1,6,8,7,5,19,20,15,10);

    List<Integer> actualList = new ArrayList<Integer>();

    root.postorder(i -> actualList.add(i));

    assertEquals(expectedList, actualList);
  }

  /**
   * Testing postorder on an empty tree.
   */
  @Test
  public void testEmptyPostorder() {
    BSTNode<Integer> root = new BSTEmptyNode<>();
    List<Integer> actualList = new ArrayList<Integer>();
    List<Integer> expectedList = new ArrayList<Integer>();

    root.postorder(i -> actualList.add(i));
    assertEquals(expectedList, actualList);
  }

  /**
   * Additional tests for postorder, here we are counting the nodes in the tree.
   */
  @Test
  public void testPostorderCount() {
    BSTNode<Integer> root = new BSTEmptyNode<>();

    root = root.insert(10);
    root = root.insert(15);
    root = root.insert(5);
    root = root.insert(1);
    root = root.insert(2);
    root = root.insert(7);
    root = root.insert(6);
    root = root.insert(8);
    root = root.insert(20);
    root = root.insert(19);

    final int[] count = {0};
    root.postorder(i -> count[0]++);
    int expectedCount = 10;
    assertEquals(expectedCount, count[0]);
  }

  /*
                                10
                     5                   15
             1             7                      20
                  2     6.      8               19
    */

  /**
   * The given test for copy.
   */
  @Test
  public void testCopy() {
    BSTNode<Integer> root = new BSTEmptyNode<>();

    root = root.insert(10);
    root = root.insert(15);
    root = root.insert(5);
    root = root.insert(1);
    root = root.insert(2);
    root = root.insert(7);
    root = root.insert(6);
    root = root.insert(8);
    root = root.insert(20);
    root = root.insert(19);

    // isn't this the same expected output as postorder????
    // AH it is!
    List<Integer> expectedList = Arrays.asList(2,1,6,8,7,5,19,20,15,10);
    List<Integer> actualList = new ArrayList<Integer>();

    BSTNode<Integer> copy = root.copy();

    copy.postorder(i -> actualList.add(i));

    assertEquals(expectedList, actualList);
  }

  /*
                                10
                     5                   15
             1             7                      20
                  2     6.      8               19
    */

  /**
   * The given test for same.
   */
  @Test
  public void testSameness() {

    BSTNode<Integer> root = new BSTEmptyNode<>();
    BSTNode<Integer> anotherRoot = new BSTEmptyNode<Integer>();

    root = root.insert(10);
    root = root.insert(15);
    root = root.insert(5);
    root = root.insert(1);
    root = root.insert(2);
    root = root.insert(7);
    root = root.insert(6);
    root = root.insert(8);
    root = root.insert(20);
    root = root.insert(19);

    anotherRoot = anotherRoot.insert(10);
    anotherRoot = anotherRoot.insert(15);
    anotherRoot = anotherRoot.insert(5);
    anotherRoot = anotherRoot.insert(1);
    anotherRoot = anotherRoot.insert(2);
    anotherRoot = anotherRoot.insert(7);
    anotherRoot = anotherRoot.insert(6);
    anotherRoot = anotherRoot.insert(8);
    anotherRoot = anotherRoot.insert(20);
    anotherRoot = anotherRoot.insert(19);

    // expected output same as postorder output
    List<Integer> expectedList = Arrays.asList(2,1,6,8,7,5,19,20,15,10);
    List<Integer> actualList = new ArrayList<Integer>();

    BSTNode<Integer> copy = root.copy();

    assertTrue(root.same(anotherRoot));
    assertTrue(root.same(copy));
  }

}