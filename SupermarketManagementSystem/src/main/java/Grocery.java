/**
 * Grocery class is a subclass of Product and additionally tracks the type of product and its weight.
 * @author vrinda
 */
import java.util.Objects;

/**
 * Grocery class is a subclass of Product and additionally tracks the type of product and its weight.
 */
public class Grocery extends Product{

  /**
   * Types of grocery products
   */
  public enum TYPE {
    /** type of grocery product*/
    SALMON,
    /** type of grocery product*/
    CHEESE,
    /** type of grocery product*/
    BEER}

  private double weight;
  private TYPE type;

  /**
   * Grocery constructor that sets the age limit to default value
   * @param id product id
   * @param manufacturer product manufacturer
   * @param productName product name
   * @param price price of the product
   * @param type type of grocery product
   * @param weight weight of the product
   */
  public Grocery(Integer id, String manufacturer, String productName, Double price, TYPE type, double weight) {
    super(id,manufacturer, productName, price);
    this.type = type;
    this.weight = weight;
  }

  /**
   * Grocery constructor that sets the age limit to user inputted value
   * @param id product id
   * @param manufacturer product manufacturer
   * @param productName product name
   * @param price price of the product
   * @param ageLimit minimum age a customer needs to be in order to buy the product
   * @param type type of grocery product
   * @param weight weight of the product
   */
  public Grocery(Integer id, String manufacturer, String productName, Double price, int ageLimit, TYPE type,
      double weight) {
    super(id, manufacturer, productName, price, ageLimit);
    this.type = type;
    this.weight = weight;
  }

  /**
   * Returns weight of the product
   * @return weight of the product
   */
  public double getWeight() {
    return weight;
  }

  /**
   * Returns type of grocery product
   * @return type of grocery product
   */
  public TYPE getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Grocery grocery)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    return Double.compare(grocery.getWeight(), getWeight()) == 0
        && getType() == grocery.getType();
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getWeight(), getType());
  }

  @Override
  public String toString() {
    return "Grocery{" +
        "weight=" + weight +
        ", type=" + type +
        '}';
  }
}
