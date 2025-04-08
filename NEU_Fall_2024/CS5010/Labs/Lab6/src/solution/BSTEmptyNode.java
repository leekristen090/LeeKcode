package solution;

import java.util.function.Consumer;

/**
 * This node represents an empty node in the binary search tree (i.e. the leaves).
 */
public class BSTEmptyNode<T extends Comparable<T>> implements BSTNode<T> {
  @Override
  public BSTNode<T> insert(T data) {
    return new BSTElementNode(data,new BSTEmptyNode(),new BSTEmptyNode());
  }

  /**
   * no min element in an empty BSTEmptyNode.
   * @return nothing
   * @throws NothingThereException empty so we throw exception
   */
  @Override
  public T minimum() throws NothingThereException {
    throw new NothingThereException("Tree does not have any data");
  }

  /**
   * no max element in an empty BSTEmptyNode.
   * @return nothing
   * @throws NothingThereException empty so we throw exception
   */
  @Override
  public T maximum() throws NothingThereException {
    throw new NothingThereException("Tree does not have any data");
  }

  /**
   * Search to see if the specific data is present in the tree rooted at this node.
   * No data in an empty BSTEmptyNode.
   * @param data data to be searched
   * @return true if data is present in the tree, false otherwise
   */
  @Override
  public boolean contains(T data) {
    return false;
  }

  /**
   * toString for empty node, we return empty string.
   * @return
   */
  @Override
  public String toString() {
    return "";
  }

  /**
   * preorder for empty node, we do nothing.
   * @param consumer the function object to be applied at each piece of data
   */
  @Override
  public void preorder(Consumer<T> consumer) {
    // do nothing
  }

  /**
   * postorder for empty node, we do nothing.
   * @param consumer the function object to be applied at each piece of data
   */
  @Override
  public void postorder(Consumer<T> consumer) {
    // do nothing
  }

  /**
   * copy method for an empty node in the BST.
   * @return an empty node
   */
  @Override
  public BSTNode<T> copy() {
    return new BSTEmptyNode<>();
    //return null;
  }

  /**
   * Determine whether the tree rooted at this node is the same (content and structure wise) as the
   * tree rooted at the given node. Empty tree should not be the same as a tree at the given node.
   * Only the same as another empty node.
   * @param other the root of the other tree
   * @return true if the two trees are the same, false otherwise
   */
  @Override
  public boolean same(BSTNode<T> other) {
    return other.sameEmpty(this);
    //return false;
  }

  /**
   * Determine if this empty node is the same as an element node.
   * @param other the other element node
   * @return false bc element node can't be the same as empty node
   */
  @Override
  public boolean sameElement(BSTElementNode<T> other) {
    return false;
  }

  /**
   * Determine if an emptyNode is the same as another empty node.
   * @param other the other empty node
   * @return true if both nodes are empty
   */
  @Override
  public boolean sameEmpty(BSTEmptyNode<T> other) {
    return true;
  }
}
