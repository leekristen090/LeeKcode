package solution;

/**
 * Exception thrown when an operation is attempted on a BST that requires data to be present, but
 * the tree is empty.
 */
public class NothingThereException extends RuntimeException {
  /**
   * This will be used on min and max for this lab.
   * @param message to be output
   */
  public NothingThereException(String message) {
    super(message);
  }

}
