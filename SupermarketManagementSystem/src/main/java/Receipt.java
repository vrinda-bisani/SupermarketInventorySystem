/**
 * Summaries the order
 * @author vrinda
 */

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Summaries the order
 */
public class Receipt {
  private double totalPrice;
  private HashMap<Product,Integer> finalList;
  private List<Product> outOfStock;
  private List<Product> ageRestrictedProducts;

  /**
   * Summaries the order
   * @param totalPrice total price paid
   * @param finalList list of products the customer received when the order was processed
   * @param outOfStock list of any products that were out of stock and could not be substituted
   * @param ageRestrictedProducts list of any products that were removed from the order because the customer did not meet minimum age requirements
   */
  public Receipt(double totalPrice, HashMap<Product, Integer> finalList, List<Product> outOfStock,
      List<Product> ageRestrictedProducts) {
    this.totalPrice = totalPrice;
    this.finalList = finalList;
    this.outOfStock = outOfStock;
    this.ageRestrictedProducts = ageRestrictedProducts;
  }

  /**
   * returns total price paid
   * @return total price paid
   */
  public double getTotalPrice() {
    return totalPrice;
  }

  /**
   * Returns list of products the customer received when the order was processed
   * @return list of products the customer received when the order was processed
   */
  public HashMap<Product,Integer> getFinalList() {
    return finalList;
  }

  /**
   * Returns list of any products that were out of stock and could not be substituted
   * @return list of any products that were out of stock and could not be substituted
   */
  public List<Product> getOutOfStock() {
    return outOfStock;
  }

  /**
   * Returns list of any products that were removed from the order because the customer did not meet minimum age requirements
   * @return list of any products that were removed
   */
  public List<Product> getAgeRestrictedProducts() {
    return ageRestrictedProducts;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Receipt receipt)) {
      return false;
    }
    return Double.compare(receipt.getTotalPrice(), getTotalPrice()) == 0
        && Objects.equals(getFinalList(), receipt.getFinalList())
        && Objects.equals(getOutOfStock(), receipt.getOutOfStock())
        && Objects.equals(getAgeRestrictedProducts(), receipt.getAgeRestrictedProducts());
  }


  @Override
  public int hashCode() {
    return Objects.hash(getTotalPrice(), getFinalList(), getOutOfStock(),
        getAgeRestrictedProducts());
  }

  @Override
  public String toString() {
    return "Receipt{" +
        "totalPrice=" + totalPrice +
        ", finalList=" + finalList +
        ", outOfStock=" + outOfStock +
        ", ageRestrictedProducts=" + ageRestrictedProducts +
        '}';
  }
}
