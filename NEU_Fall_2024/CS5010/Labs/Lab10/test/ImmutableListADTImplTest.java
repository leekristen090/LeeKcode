import org.junit.Test;

import listadt.GenericEmptyNode;
import listadt.ImmutableListADT;
import listadt.ImmutableListADTImpl;
import listadt.MutableListADT;

import static org.junit.Assert.assertEquals;

/**
 * This is the junit test class for the methods in ImmutableListADTImpl class.
 */
public class ImmutableListADTImplTest {

  /**
   * Testing addBack and toString methods.
   */
  @Test
  public void testImmutableListAddBack() {
    // Create an empty ImmutableListADT
    ImmutableListADT<Integer> immutableList = new ImmutableListADTImpl<>(new GenericEmptyNode<>());

    // Add elements using the builder
    ImmutableListADTImpl.Builder<Integer> builder = new ImmutableListADTImpl.Builder<>();
    builder.addBack(10);
    builder.addBack(20);
    builder.addBack(30);
    immutableList = builder.build();

    // Verify the list's contents
    assertEquals("10 20 30", immutableList.toString());
  }

  /**
   * Testing getMutableList() method.
   */
  @Test
  public void testImmutableListGetMutableList() {
    // Create an ImmutableListADT and add some elements
    ImmutableListADT<Integer> immutableList = new ImmutableListADTImpl<>(new GenericEmptyNode<>());
    ImmutableListADTImpl.Builder<Integer> builder = new ImmutableListADTImpl.Builder<>();
    builder.addBack(10);
    builder.addBack(20);
    builder.addBack(30);
    immutableList = builder.build();

    // Convert it to a mutable list
    MutableListADT<Integer> mutableList = immutableList.getMutableList();

    // Verify the contents of the mutable list
    assertEquals("10 20 30", mutableList.toString());
  }

  /**
   * Testing map method.
   */
  @Test
  public void testImmutableListMap() {
    // Create an ImmutableListADT with some elements
    ImmutableListADT<Integer> immutableList = new ImmutableListADTImpl<>(new GenericEmptyNode<>());
    ImmutableListADTImpl.Builder<Integer> builder = new ImmutableListADTImpl.Builder<>();
    builder.addBack(10);
    builder.addBack(20);
    builder.addBack(30);
    immutableList = builder.build();

    // Apply a mapping function that doubles the values
    ImmutableListADT<Integer> mappedList =
            (ImmutableListADT<Integer>) immutableList.map(x -> x * 2);

    // Verify the mapped list's contents
    assertEquals("20 40 60", mappedList.toString());
  }

  /**
   * Testing removal does not work on an immutable list.
   */
  @Test
  public void testImmutableListDoesNotAllowMutation() {
    ImmutableListADTImpl.Builder<Integer> builder = new ImmutableListADTImpl.Builder<>();
    builder.addBack(10);
    builder.addBack(20);
    ImmutableListADT<Integer> immutableList = builder.build();

    immutableList.getMutableList().remove(10);

    // Verify immutable list contents remain unchanged
    assertEquals("10 20", immutableList.toString());
  }
}