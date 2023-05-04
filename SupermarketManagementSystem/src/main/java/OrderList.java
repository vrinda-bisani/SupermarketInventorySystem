/**
 * Tracks customers order
 */

import java.util.HashMap;

/**
 * Tracks customers order
 */
public class OrderList {

  private static HashMap<Customer,ShoppingCart> orderList= new HashMap<>();

  /**
   * Returns list of customers and their order
   * @return list of customers and their order
   */
  public static HashMap<Customer, ShoppingCart> getOrderList() {
    return orderList;
  }

  /**
   * Adds confirmed order to list
   * @param customer customer details
   * @param cart customers cart
   */
  public static void addToOrderList(Customer customer, ShoppingCart cart){
    orderList.put(customer,cart);
  }

  /**
   * removes customer and their order once the order is processed
   * @param customer customer details
   */
  public static void removeFromOrderList(Customer customer){
    orderList.remove(customer);
  }


}
