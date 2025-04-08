package listadt;

/**
 * Represents a mutable list of elements. This interface extends ListADT
 * and provides a method to obtain an immutable version of the list.
 * The elements and structure of this list can be modified.
 * @param <T> the type of elements in this list
 */
public interface MutableListADT<T> extends ListADT<T> {

  /**
   * Returns an immutable counterpart of this mutable list. The immutable list returned contains
   * the same elements in the same sequence as this mutable list but cannot be modified.
   * @return an immutable list containing the same elements as this list
   */
  ImmutableListADT<T> getImmutableList();

}