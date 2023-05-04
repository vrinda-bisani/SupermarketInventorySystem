/**
 * Shopping cart helper class
 * @author vrinda
 */
public class ShoppingCartHelper {

  /**
   * fetches the stock from inventory given the product id
   * @param id product id
   * @return stock item
   */
  public static Stock getStock(Integer id){
    if(Inventory.getGroceryStock().containsKey(id)){
      return Inventory.getGroceryStock().get(id);}
    return Inventory.getHouseholdStock().get(id);
  }


  /**
   * Checks if given product has sufficient quantity in inventory else throws exception
   *
   * @param id       product id
   * @param quantity customer chosen quantity
   * @throws InsufficientProductQuantityException Insufficient product quantity in inventory
   * @throws ProductNotFound Product not present in inventory
   */
  public static void checkStock(Integer id, int quantity)
      throws InsufficientProductQuantityException, ProductNotFound {
    if (getStock(id) == null){
      throw new ProductNotFound("Product not found in inventory");
    }
    else if(getStock(id).getQuantity() < quantity){
       throw new InsufficientProductQuantityException("Insufficient Quantity");
     }
  }

}
