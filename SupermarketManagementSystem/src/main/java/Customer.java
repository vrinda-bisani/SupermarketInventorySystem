/**
 * Keeps track of customer's name, age and shopping cart
 * @author vrinda
 */

import java.util.Objects;

/**
 * Keeps track of customer's name, age and shopping cart
 */
public class Customer {
  private String name;
  private int age;
  private ShoppingCart cart;

  /**
   * Customer constructor assigns an empty shopping to each customer
   * @param name customer name
   * @param age customer age
   */
  public Customer(String name, int age) {
    this.name = name;
    this.age = age;
    this.cart = new ShoppingCart();
  }

  /**
   * Returns customer name
   * @return customer name
   */
  public String getName() {
    return name;
  }

  /**
   * Returns customer age
   * @return customer age
   */
  public int getAge() {
    return age;
  }

  /**
   * Returns customer shopping cart
   * @return customer shopping cart
   */
  public ShoppingCart getCart() {
    return cart;
  }

  /**
   * Empties customer shopping cart
   */
  public void emptyCart() {
    this.cart.emptyCart();
  }

  /**
   * Places order and adds customer shopping cart to order list
   */
  public void placeOrder(){
    OrderList.addToOrderList(this,cart);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Customer customer)) {
      return false;
    }
    return getAge() == customer.getAge() && Objects.equals(getName(), customer.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getAge());
  }

  @Override
  public String toString() {
    return "Customer{" +
        "name='" + name + '\'' +
        ", age=" + age +
        '}';
  }
}
