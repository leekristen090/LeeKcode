package listadt;

import java.util.function.Function;

/**
 * This is the implementation of the methods in immutable list adt interface. Once created, the
 * structure of this list cannot be modified. It provides a builder to create instances
 * of this class.
 * @param <T> the type of elements in this list
 */
public class ImmutableListADTImpl<T> implements ImmutableListADT<T> {
  private final GenericListADTNode<T> head;

  /**
   * Construct immutable list.
   * @param head of the list
   */
  public ImmutableListADTImpl(GenericListADTNode<T> head) {
    this.head = head;
  }

  /**
   * Returns a mutable counterpart of this immutable list.
   * @return a mutable list containing the same elements as this list
   */
  @Override
  public MutableListADT<T> getMutableList() {
    return new MutableListADTImpl<>(new ListADTImpl<>(head));
  }

  /**
   * Return the number of objects currently in this list.
   * @return the size of the list
   */
  @Override
  public int getSize() {
    return head.count();
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
    // Implement the map operation
    GenericListADTNode<R> newHead = head.map(converter);
    return new ImmutableListADTImpl<>(newHead);
  }

  /**
   * Get the (index)th object in this list.
   * @param index the index of the object to be returned
   * @return the object at the given index
   * @throws IllegalArgumentException if an invalid index is passed
   */
  @Override
  public T get(int index) throws IllegalArgumentException {
    return head.get(index);
  }

  /**
   * Returns string representation of this list.
   * @return string of list's contents
   */
  @Override
  public String toString() {
    return head.toString();
  }

  /**
   * Builder pattern for adding elements.
   * @param <T> element to add
   */
  public static class Builder<T> {
    private GenericListADTNode<T> head = new GenericEmptyNode<>();

    public Builder<T> addBack(T element) {
      head = head.addBack(element);
      return this;
    }

    public ImmutableListADT<T> build() {
      return new ImmutableListADTImpl<>(head);
    }
  }
}
