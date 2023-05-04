/**
 * Household class is a subclass of Product and additionally tracks the type of product and number of units in a package.
 * @author vrinda
 */
import java.util.Objects;

/**
 * Household class is a subclass of Product and additionally tracks the type of product and number of units in a package.
 */
public class Household extends Product{

  /**
   * Types of household products
   */
  public enum TYPE{
    /** type of household product*/
    PAPERTOWELS,
    /** type of household product*/
    SHAMPOO};
  private int unit;
  private TYPE type;

  /**
   * Household constructor that sets the age limit to default value
   * @param id product id
   * @param manufacturer product manufacturer
   * @param productName product name
   * @param price price of the product
   * @param unit number of units in a package
   * @param type type of grocery product
   */
  public Household(Integer id, String manufacturer, String productName, Double price, int unit, TYPE type) {
    super(id, manufacturer, productName, price);
    this.unit = unit;
    this.type = type;
  }

  /**
   * Household constructor that sets the age limit to user inputted value
   * @param id product id
   * @param manufacturer product manufacturer
   * @param productName product name
   * @param price price of the product
   * @param ageLimit minimum age a customer needs to be in order to buy the product
   * @param unit number of units in a package
   * @param type type of grocery product
   */
  public Household(Integer id, String manufacturer, String productName, Double price, int ageLimit, int unit,
      TYPE type) {
    super(id, manufacturer, productName, price, ageLimit);
    this.unit = unit;
    this.type = type;
  }
  /**
   * Returns number of units in a package
   * @return number of units in a package
   */
  public int getUnit() {
    return unit;
  }

  /**
   * Returns type of household product
   * @return type of household product
   */
  public TYPE getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Household household)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    return getUnit() == household.getUnit() && getType() == household.getType();
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getUnit(), getType());
  }

  @Override
  public String toString() {
    return "Household{" +
        "unit=" + unit +
        ", type=" + type +
        '}';
  }
}
