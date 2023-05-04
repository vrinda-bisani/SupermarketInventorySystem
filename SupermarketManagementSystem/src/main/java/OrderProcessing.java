/**
 * Fulfils the order by gathering all the items in the cart ready for pickup and generates receipt
 * @aurthor vrinda
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Fulfils the order by gathering all the items in the cart ready for pickup and generates receipt
 */
public class OrderProcessing {
  Customer customer;

  private HashMap<Product,Integer> finalList;
  private List<Product> outOfStock;
  private List<Product> ageRestrictedProducts;
  private double totalCost = 0;

  /**
   * Orderprocessing constructor processes order for given customer
   * @param customer customer whose order is being processed
   */
  public OrderProcessing(Customer customer) {
    this.customer = customer;
    this.finalList = new HashMap<>();
    this.outOfStock = new ArrayList<>();
    this.ageRestrictedProducts = new ArrayList<>();
  }

  /**
   * Returns customer whose order is being processed
   * @return customer whose order is being processed
   */
  public Customer getCustomer() {
    return customer;
  }

  /**
   * Returns list of products the customer received when the order was processed
   * @return list of products the customer received
   */
  public HashMap<Product, Integer> getFinalList() {
    return finalList;
  }

  /**
   * List of any products that were out of stock and could not be substituted
   * @return List of any products that were out of stock
   */
  public List<Product> getOutOfStock() {
    return outOfStock;
  }

  /**
   * List of any products that were removed from the order because the customer did not meet minimum age requirements
   * @return List of any products that were removed from the order
   */
  public List<Product> getAgeRestrictedProducts() {
    return ageRestrictedProducts;
  }

  /**
   * Total price paid
   * @return total price paid
   */
  public double getTotalCost() {
    return totalCost;
  }

  /**
   * Checks if item in the cart have age restriction
   * Also checks if the requested quantity is available in inventory else substitutes else adds it out of stock list
   */
  public void process(){
    for(Map.Entry<Integer, Integer> set : OrderList.getOrderList().get(customer).getCart().entrySet()){
      Stock stock = ShoppingCartHelper.getStock(set.getKey());
      Product product = stock.getProduct();
      if(checkAgeRestriction(product, customer.getAge())){
        this.ageRestrictedProducts.add(product);
        continue;
      }
      if(stock.getQuantity() < set.getValue()){
        Product subs_product = substitute(set.getKey(),set.getValue());
        if (subs_product ==null){
          this.outOfStock.add(product);
          continue;
        }
        product = subs_product;
      }
      this.finalList.put(product,set.getValue());
      this.totalCost+= product.getPrice() * set.getValue();
    }
  }

  /**
   * Substitutes the original item with an equivalent item
   * @param id product id
   * @param quantity requested quantity
   * @return substitute product
   */
  public Product substitute(Integer id, Integer quantity){
    Class T = ShoppingCartHelper.getStock(id).getProductAnyType().getClass();
    if(T == Grocery.class){
      return subsGroceryProduct(id,quantity);
    }
    else{
      return subsHouseholdProduct(id,quantity);
    }
  }

  /**
   * Substitutes the original grocery item with an equivalent grocery item
   * @param id product id
   * @param quantity requested quantity
   * @return substitute grocery product
   */
  public Product subsGroceryProduct(Integer id, Integer quantity){
    Grocery mainProduct  = (Grocery) ShoppingCartHelper.getStock(id).getProduct();
    for (Map.Entry<Integer,Stock<Grocery>> set : Inventory.getGroceryStock().entrySet()){
      Grocery subsProduct = set.getValue().getProductAnyType();
      Integer subsQuantity = set.getValue().getQuantity();
      if( subsProduct.getType() == mainProduct.getType() &&
          subsQuantity >= quantity &&
          subsProduct.getPrice() <= mainProduct.getPrice() &&
          subsProduct.getWeight() >= mainProduct.getWeight()
      ){
        return subsProduct;
      }

    }
    return null;

  }

  /**
   * Substitutes the original household item with an equivalent household item
   * @param id product id
   * @param quantity requested quantity
   * @return substitute household product
   */
  public Product subsHouseholdProduct(Integer id, Integer quantity){
    Household mainProduct = (Household) ShoppingCartHelper.getStock(id).getProductAnyType();
    for (Map.Entry<Integer,Stock<Household>> set : Inventory.getHouseholdStock().entrySet()){
      Household subsProduct = set.getValue().getProductAnyType();
      Integer subsQuantity = set.getValue().getQuantity();
      if( subsProduct.getType() == mainProduct.getType() &&
          subsQuantity >= quantity &&
          subsProduct.getPrice() <= mainProduct.getPrice() &&
          subsProduct.getUnit() >= mainProduct.getUnit())
        {
        return subsProduct;
        }
    }
    return null;
  }

  /**
   * Checks if customer is not old enough to buy the given product
   * @param product requested product
   * @param customer_age customer age
   * @return true if customer is not old enough to buy the product
   */
  public Boolean checkAgeRestriction(Product product, int customer_age){
    if(product.getAgeLimit() > customer_age){
      return true;
    }
    return false;
  }

  /**
   * Updates quantities of all stock items purchased in the system
   */
  public void updateInventory(){
    for(Map.Entry<Product,Integer> set : this.finalList.entrySet() ){
      Inventory.updateStock(set.getKey().getId(), set.getValue());
    }
  }

  /**
   * Empties customer cart
   */
  public void emptyCart(){
    this.customer.emptyCart();
  }

  /**
   * Returns receipt summarizing the order
   * @return receipt summarizing the order
   */
  public Receipt generateReceipt(){
    return new Receipt(this.totalCost,this.finalList,this.outOfStock,this.ageRestrictedProducts);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof OrderProcessing that)) {
      return false;
    }
    return Objects.equals(getCustomer(), that.getCustomer());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCustomer(), getFinalList(), getOutOfStock(), getAgeRestrictedProducts(),
        getTotalCost());
  }

  @Override
  public String toString() {
    return "OrderProcessing{" +
        "customer=" + customer +
        ", finalList=" + finalList +
        ", outOfStock=" + outOfStock +
        ", ageRestrictedProducts=" + ageRestrictedProducts +
        ", totalCost=" + totalCost +
        '}';
  }
}
