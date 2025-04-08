package listadt;

/**
 * Represents an immutable list of elements. This interface extends common list ADT
 * and provides a method to obtain a mutable version of the list.
 * The elements in this list cannot be modified, and any operation to mutate
 * the structure of the list will return a new list instead.
 * @param <T> the type of elements in this list
 */
public interface ImmutableListADT<T> extends CommonListADT<T> {

  /**
   * Returns a mutable counterpart of this immutable list. The mutable list returned contains
   * the same elements in the same sequence as this immutable list but can be modified.
   * @return a mutable list containing the same elements as this list
   */
  MutableListADT<T> getMutableList();

}