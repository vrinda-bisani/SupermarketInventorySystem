/**
 * Shopping cart contains a list of products the customer has added to their cart
 * @author vrinda
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Shopping cart contains a list of products and their quantities the customer has added to their cart
 */
public class ShoppingCart {

  /**
   * contains a list of products the customer has added to their cart
   */
  public HashMap<Integer,Integer> cart;
  private static final int DEFAULT_QUANTITY = 1;

  /**
   * Generates empty cart for each customer
   */
  public ShoppingCart() {
    this.cart = new HashMap<>();
  }

  /**
   * returns cart
   * @return cart
   */
  public HashMap<Integer, Integer> getCart() {
    return cart;
  }

  /**
   * adds product to the cart given product ID. Setting quantity to default.
   * @param id product id
   */
  public void add(Integer id) {
    add(id,DEFAULT_QUANTITY);
  }

  /**
   * Adds product to the cart given product ID and quantity.
   * Throws exception if inventory has insufficient stock and adds available quantity to cart.
   * @param id product id
   * @param quantity customer chosen quantity
   */
  public void add(Integer id, int quantity){
    try{
      if(cart.containsKey(id)){
        quantity+= cart.get(id);
      }
      ShoppingCartHelper.checkStock(id, quantity);
      cart.put(id,quantity);
    }

    catch (ProductNotFound e) {
      System.out.println(e.getMessage());
    }

    catch (InsufficientProductQuantityException e){
      System.out.println(e.getMessage());
      if(quantity!= DEFAULT_QUANTITY) {
        cart.put(id, ShoppingCartHelper.getStock(id).getQuantity());
      }
    }
  }

  /**
   * Calculates total cost of all items in the cart
   * @return total cost of all items in the cart
   */
  public double totalCost(){
    double sum = 0.0;
    for (Map.Entry<Integer, Integer> set : cart.entrySet()) {
        sum+= ShoppingCartHelper.getStock(set.getKey()).getProduct().getPrice() * set.getValue();
    }
    return sum;
  }

  /**
   * Empties customer’s shopping cart
   */
  public void emptyCart(){
    this.cart.clear();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ShoppingCart that)) {
      return false;
    }
    return Objects.equals(getCart(), that.getCart());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCart());
  }

  @Override
  public String toString() {
    return "ShoppingCart{" +
        "cart=" + cart +
        '}';
  }
}
