import org.junit.Test;

import listadt.ImmutableListADT;
import listadt.ListADTImpl;
import listadt.MutableListADT;
import listadt.MutableListADTImpl;

import static org.junit.Assert.assertEquals;

/**
 * This is the junit test class for the methods in MutableListADTImpl class.
 */
public class MutableListADTImplTest {

  @Test
  public void testMutableListEmpty() {
    // Create an empty MutableListADT
    MutableListADT<Integer> mutableList = new MutableListADTImpl<>(new ListADTImpl<>());

    // Verify that the list is empty
    assertEquals("", mutableList.toString());
  }

  /**
   * This is testing adding numbers to the list and getSize().
   */
  @Test
  public void testMutableListADT() {
    ListADTImpl<Integer> list = new ListADTImpl<>();
    MutableListADTImpl<Integer> mutableList = new MutableListADTImpl<>(list);
    mutableList.addBack(1);
    mutableList.addBack(2);
    mutableList.addFront(0);
    assertEquals(3, mutableList.getSize());
  }

  /**
   * This is testing addFront().
   */
  @Test
  public void testMutableListAddFront() {
    // Create a new MutableListADT
    MutableListADT<Integer> mutableList = new MutableListADTImpl<>(new ListADTImpl<>());

    // Add elements to the front of the list
    mutableList.addFront(10);
    mutableList.addFront(20);
    mutableList.addFront(30);

    // Verify the contents of the mutable list
    assertEquals("30 20 10", mutableList.toString());
  }

  /**
   * This is testing addBack() and toString().
   */
  @Test
  public void testMutableListAddBack() {
    MutableListADT<Integer> mutableList = new MutableListADTImpl<>(new ListADTImpl<>());

    mutableList.addBack(10);
    mutableList.addBack(20);
    mutableList.addBack(30);

    assertEquals("10 20 30", mutableList.toString());
  }

  /**
   * Testing mutability.
   */
  @Test
  public void testMutableListMap() {
    MutableListADT<Integer> mutableList = new MutableListADTImpl<>(new ListADTImpl<>());
    mutableList.addFront(10);
    mutableList.addBack(20);
    mutableList.addBack(30);
    MutableListADT<Integer> mappedList = (MutableListADT<Integer>) mutableList.map(x -> x * 2);

    assertEquals("20 40 60", mappedList.toString());
  }

  /**
   * Make sure immutable list is not changed.
   */
  @Test
  public void testMutableListDoesNotAffectImmutableList() {
    MutableListADT<Integer> mutableList = new MutableListADTImpl<>(new ListADTImpl<>());
    mutableList.addFront(10);
    mutableList.addBack(20);

    ImmutableListADT<Integer> immutableList = mutableList.getImmutableList();
    mutableList.addBack(30);

    assertEquals("10 20 30", mutableList.toString());
    assertEquals("10 20", immutableList.toString());
  }

  /**
   * Converting to Immutable list.
   */
  @Test
  public void testMutableListAddAndConvertToImmutable() {
    MutableListADT<Integer> mutableList = new MutableListADTImpl<>(new ListADTImpl<>());

    mutableList.addFront(10);
    mutableList.addBack(20);
    mutableList.addBack(30);

    ImmutableListADT<Integer> immutableList = mutableList.getImmutableList();

    assertEquals("10 20 30", mutableList.toString());
    assertEquals("10 20 30", immutableList.toString());
  }
}