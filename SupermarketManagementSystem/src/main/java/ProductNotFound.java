/**
 * Throws this exception if product is not there in inventory
 */
public class ProductNotFound extends Throwable {

  /**
   * Throws this exception if product is not there in inventory
   * @param product_not_found_in_inventory Exception message
   */
  public ProductNotFound(String product_not_found_in_inventory) {
    super(product_not_found_in_inventory);
  }
}
