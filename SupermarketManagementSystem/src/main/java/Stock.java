/**
 * System represents the quantities of each Product as a StockItem
 * @author vrinda
 */

import java.util.Objects;

/**
 * System represents the quantities of each Product as a StockItem
 * @param <T> This is generic type as it can be grocery or household product. This is decided at runtime.
 */
public class Stock<T>{
  private T product;
  private int quantity;

  /**
   * Stock constructor with generic product type.
   * @param product grocery or stock product
   * @param quantity quantity of this product that the supermarket has in stock
   */
  public Stock(T product, int quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  /**
   * returns product of type grocery or household
   * @return product of type grocery or household
   */
  public T getProductAnyType() {
    return this.product;
  }

  /**
   * returns product of type product
   * @return product of type product
   */
  public Product getProduct() {
    return (Product) this.product;
  }

  /**
   * Returns quantity of this product that the supermarket has in stock
   * @return quantity of this product that the supermarket has in stock
   */
  public int getQuantity() {
    return quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Stock<?> stock)) {
      return false;
    }
    return getQuantity() == stock.getQuantity() && Objects.equals(getProduct(),
        stock.getProduct());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getProduct(), getQuantity());
  }

  @Override
  public String toString() {
    return "Stock{" +
        "product=" + product +
        ", quantity=" + quantity +
        '}';
  }
}
