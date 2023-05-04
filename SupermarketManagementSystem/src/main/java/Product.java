/**
 * Product class tracks Manufacturer, Product name, Price, minimum age of customer.
 * @author vrinda
 */

import java.util.Objects;

/**
 * **
 *  * Product class tracks Manufacturer, Product name, Price, minimum age of customer.
 */
public class Product {
  private String manufacturer;
  private Integer id;
  private String productName;
  private Double price;
  private int ageLimit;

  private static final Integer DEFAULT_AGE_LIMIT = 0;

  /**
   * Product constructor that sets the age limit to default value
   * @param id product id
   * @param manufacturer product manufacturer
   * @param productName product name
   * @param price price of the product
   */
  public Product(Integer id, String manufacturer, String productName, Double price) {
    this.id = id;
    this.manufacturer = manufacturer;
    this.productName = productName;
    this.price = price;
    this.ageLimit = DEFAULT_AGE_LIMIT;
  }

  /**
   * Product constructor that that sets the age limit to user inputted value
   * @param id product id
   * @param manufacturer product manufacturer
   * @param productName product name
   * @param price price of the product
   * @param ageLimit minimum age a customer needs to be in order to buy the product
   */
  public Product(Integer id, String manufacturer, String productName, Double price, int ageLimit) {
    this.id = id;
    this.manufacturer = manufacturer;
    this.productName = productName;
    this.price = price;
    this.ageLimit = ageLimit;
  }

  /**
   * Returns product ID
   * @return product ID
   */
  public Integer getId() {
    return id;
  }

  /**
   * Returns product manufacturer
   * @return product manufacturer
   */

  public String getManufacturer() {
    return manufacturer;
  }

  /**
   * Returns product name
   * @return product name
   */
  public String getProductName() {
    return productName;
  }

  /**
   * Returns price of the product
   * @return price of the product
   */

  public Double getPrice() {
    return price;
  }

  /**
   * Returns minimum age a customer needs to be in order to buy the product
   * @return minimum age
   */

  public int getAgeLimit() {
    return ageLimit;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Product product)) {
      return false;
    }
    return getAgeLimit() == product.getAgeLimit() && Objects.equals(getManufacturer(),
        product.getManufacturer()) && Objects.equals(getId(), product.getId())
        && Objects.equals(getProductName(), product.getProductName())
        && Objects.equals(getPrice(), product.getPrice());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getManufacturer(), getId(), getProductName(), getPrice(), getAgeLimit());
  }

  @Override
  public String toString() {
    return "Product{" +
        "manufacturer='" + manufacturer + '\'' +
        ", id=" + id +
        ", productName='" + productName + '\'' +
        ", price=" + price +
        ", ageLimit=" + ageLimit +
        '}';
  }
}
