package bst;

import java.util.function.Consumer;

/**
 * This class represents a data-containing node of the binary search tree. It mutates on all
 * relevant operations.
 */
public class BSTElementNode<T extends Comparable<T>> implements BSTNode<T> {
  private BSTNode<T> left;
  private BSTNode<T> right;
  private T data;

  public BSTElementNode(T data,BSTNode<T> left,BSTNode<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }

  /**
   * Inserts new data into the tree rooted at this node, and return the resulting tree.
   * @param data to be inserted
   * @return resulting tree
   */
  @Override
  public BSTNode insert(T data) {
    if (data.compareTo(this.data)<0) {
      this.left = this.left.insert(data);
    } else if (data.compareTo(this.data)>0) {
      this.right = this.right.insert(data);
    }
    return this;
  }

  /**
   * Determine and return the minimum element in the tree rooted at this node.
   * @return the min element
   * @throws NothingThereException if the tree does not have any data
   */
  @Override
  public T minimum() {
    T minimum;

    try {
      minimum = this.left.minimum();
    }
    catch (NothingThereException e) {
      minimum = this.data;
    }
    return minimum;
  }

  /**
   * Determine and return the maximum element in the tree rooted at this node.
   * @return the max element
   * @throws NothingThereException if the tree does not have any data
   */
  @Override
  public T maximum() {
    T maximum;

    try {
      maximum = this.right.maximum();
    }
    catch (NothingThereException e) {
      maximum = this.data;
    }

    return maximum;
  }

  /**
   * Search to see if the specific data is present in the tree rooted at this node.
   * @param data data to be searched
   * @return true if data is present in the tree, false otherwise
   */
  @Override
  public boolean contains(T data) {
    int compareResult = data.compareTo(this.data);

    if (compareResult == 0)  {
      return true;
    }
    else if (compareResult < 0) {
      return this.left.contains(data);
    }
    else {
      return this.right.contains(data);
    }
  }

  /**
   * Returns a string containing all the data in the tree rooted at this node.
   * The string is formatted as d1 d2 ... dn.
   * @return string will all tree data
   */
  @Override
  public String toString() {
    String left, right, middle;

    middle = this.data.toString();
    left = this.left.toString();
    right = this.right.toString();
    if (left.length() > 0) left = left + " ";
    if (right.length() > 0) right = " " + right;
    return left + middle + right;
  }

  /**
   * Traverse the tree rooted at this node in preorder and apply the consumer at each piece of data.
   * @param consumer the function object to be applied at each piece of data
   */
  @Override
  public void preorder(Consumer<T> consumer) {

  }

  /**
   * Traverse the tree rooted at this node in postorder and apply the consumer at each piece of
   * data.
   * @param consumer the function object to be applied at each piece of data
   */
  @Override
  public void postorder(Consumer<T> consumer) {

  }

  /**
   * Create an exact copy (in terms of data and structure) of the tree rooted at this node.
   * @return the copy of the tree rooted here
   */
  @Override
  public BSTNode<T> copy() {
    return null;
  }

  /**
   * Determine whether the tree rooted at this node is the same (content and structure wise) as the
   * tree rooted at the given node.
   * @param other the root of the other tree
   * @return true if the two trees are the same, false otherwise
   */
  @Override
  public boolean same(BSTNode<T> other) {
    return false;
  }
}
