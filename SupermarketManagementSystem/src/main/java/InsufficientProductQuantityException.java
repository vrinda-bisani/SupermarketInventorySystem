/**
 * Exception to handle insufficient stock in inventory.
 */
public class InsufficientProductQuantityException extends Exception {

  /**
   * Exception to handle insufficient stock in inventory.
   * @param message Exception message
   */
  public InsufficientProductQuantityException(String message) {
    super(message);
  }
}
