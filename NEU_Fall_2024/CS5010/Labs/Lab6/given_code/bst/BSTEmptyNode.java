package bst;

import java.util.function.Consumer;

/**
 * This node represents an empty node in the binary search tree (i.e. the leaves).
 */
public class BSTEmptyNode<T extends Comparable<T>> implements BSTNode<T> {
  @Override
  public BSTNode<T> insert(T data) {
    return new BSTElementNode(data,new BSTEmptyNode(),new BSTEmptyNode());
  }

  @Override
  public T minimum() throws NothingThereException {
    throw new NothingThereException("Tree does not have any data");
  }

  @Override
  public T maximum() throws NothingThereException {
    throw new NothingThereException("Tree does not have any data");
  }

  @Override
  public boolean contains(T data) {
    return false;
  }

  @Override
  public String toString() {
    return "";
  }

  @Override
  public void preorder(Consumer<T> consumer) {

  }


  @Override
  public void postorder(Consumer<T> consumer) {

  }

  @Override
  public BSTNode<T> copy() {
    return null;
  }

  @Override
  public boolean same(BSTNode<T> other) {
    return false;
  }
}
