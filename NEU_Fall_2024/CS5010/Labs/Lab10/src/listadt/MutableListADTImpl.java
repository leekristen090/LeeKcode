package listadt;

import java.util.function.Function;

/**
 * This class represents a mutable list implementation that allows modification
 * of the list structure by adding, removing, and modifying elements.
 * This class is an adapter for ListADTImpl.
 * @param <T> the type of elements in this list
 */
public class MutableListADTImpl<T> implements MutableListADT<T> {
  private final ListADTImpl<T> list;

  /**
   * Constructing mutable list with provided list adt.
   * @param list to be mutable
   */
  public MutableListADTImpl(ListADTImpl<T> list) {
    this.list = list;
  }

  /**
   * Returns an immutable counterpart of this mutable list.
   * @return an immutable list containing the same elements as this list
   */
  @Override
  public ImmutableListADT<T> getImmutableList() {
    ImmutableListADTImpl.Builder<T> builder = new ImmutableListADTImpl.Builder<>();

    for (int i = 0; i < list.getSize(); i++) {
      builder.addBack(list.get(i));
    }
    return builder.build();
  }

  /**
   * Add an object to the front of this list.
   * @param b the object to be added to the front of this list
   */
  @Override
  public void addFront(T b) {
    list.addFront(b);
  }

  /**
   * Add an object to the back of this list (so it is the last object in the list).
   * @param b the object to be added to teh back of this list
   */
  @Override
  public void addBack(T b) {
    list.addBack(b);
  }

  /**
   * Add an object to this list so that it occupies the provided index. Index begins with 0.
   * @param index the index to be occupied by this object, beginning at 0
   * @param b the object to be added to the list
   */
  @Override
  public void add(int index, T b) {
    list.add(index, b);
  }

  /**
   * Remove the first instance of this object from this list.
   * @param b the object to be removed
   */
  @Override
  public void remove(T b) {
    list.remove(b);
  }

  /**
   * Get the (index)th object in this list.
   * @param index the index of the object to be returned
   * @return the object at the given index
   * @throws IllegalArgumentException if an invalid index is passed
   */
  @Override
  public T get(int index) {
    return list.get(index);
  }

  /**
   * Return the number of objects currently in this list.
   * @return the size of the list
   */
  @Override
  public int getSize() {
    return list.getSize();
  }

  /**
   * A general purpose map higher order function on this list, that returns
   * the corresponding list of type R.
   * @param converter the function that converts T into R
   * @param <R> the type of data in the resulting list
   * @return the resulting list that is identical in structure to this list,
   *          but has data of type R
   */
  @Override
  public <R> CommonListADT<R> map(Function<T, R> converter) {
    ListADTImpl<R> newList = (ListADTImpl<R>) list.map(converter);
    return new MutableListADTImpl<>(newList);
  }

  /**
   * Returns string representation of this list.
   * @return string of list's contents
   */
  @Override
  public String toString() {
    return list.toString();
  }
}
