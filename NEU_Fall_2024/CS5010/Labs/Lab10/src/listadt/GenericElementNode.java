package listadt;

import java.util.function.Function;

/**
 * This is a non-empty node in a generic list. It contains the object data
 * and the rest of the list
 */
public class GenericElementNode<T> implements GenericListADTNode<T> {
  private T object;
  private GenericListADTNode<T> rest;

  public GenericElementNode(T p, GenericListADTNode<T> rest) {
    this.object = p;
    this.rest = rest;
  }

  /**
   * Return the number of objects in this list.
   * @return the size of this list
   */
  @Override
  public int count() {
    return 1 + this.rest.count();
  }

  /**
   * Add the given object to the front of this list and return this modified list.
   * @param object the object to be added
   * @return the resulting list
   */
  @Override
  public GenericListADTNode<T> addFront(T object) {
    return new GenericElementNode(object, this);
  }

  /**
   * Add the given object to the back of this list and return this modified list.
   * @param object the object to be added
   * @return the resulting list
   */
  @Override
  public GenericListADTNode<T> addBack(T object) {
    this.rest = this.rest.addBack(object);
    return this;
  }

  /**
   * A method that adds the given object at the given index in this list.
   * If index is 0, it means this object should be added to the front of this list.
   * @param index the position to be occupied by this object, starting at 0
   * @param object the object to be added
   * @return the resulting list
   * @throws IllegalArgumentException if an invalid index is passed
   */
  @Override
  public GenericListADTNode<T> add(int index, T object) {
    if (index == 0) {
      return addFront(object);
    } else {
      this.rest = this.rest.add(index - 1, object);
      return this;
    }
  }

  /**
   * Remove the first instance of this object from the list.
   * @param object the object to be removed
   * @return the resulting list
   */
  @Override
  public GenericListADTNode<T> remove(T object) {
    if (this.object.equals(object)) {
      return this.rest;
    } else {
      this.rest = this.rest.remove(object);
      return this;
    }
  }

  /**
   * Get the object at the specified index, with 0 meaning the first object in
   * this list.
   * @param index the specified index
   * @return the object at the specified index
   * @throws IllegalArgumentException if an invalid index is passed
   */
  @Override
  public T get(int index) throws IllegalArgumentException {
    if (index == 0) {
      return this.object;
    }
    return this.rest.get(index - 1);
  }

  /**
   * A general map higher order function on nodes. This returns a list of
   * identical structure, but each data item of type T converted into R using
   * the provided converter method.
   * @param converter the function needed to convert T into R
   * @param <R> the type of the data in the returned list
   * @return the head of a list that is structurally identical to this list,
   *          but contains data of type R
   */
  @Override
  public <R> GenericListADTNode<R> map(Function<T, R> converter) {
    /* Starting from this list of T, the resulting list of type R is an
    element that contains this data converted to T, followed by the rest of
    the converted list
     */
    return new GenericElementNode(
            converter.apply(this.object),
            this.rest.map(converter));
  }

  @Override
  public String toString() {
    String objstring = this.object.toString();
    String rest = this.rest.toString();
    if (rest.length() > 0) {
      return objstring + " " + rest;
    }
    else {
      return objstring;
    }
  }
}
