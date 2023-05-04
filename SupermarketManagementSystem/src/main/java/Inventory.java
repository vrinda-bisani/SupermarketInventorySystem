/**
 * Keeps track of list of all grocery StockItems and household StockItems
 * @author vrinda
 */
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * keep track of list of all grocery StockItems and household StockItems
 */
public class Inventory {

  /**
   * track of list of all household StockItems
   */
  private static HashMap<Integer, Stock<Household>> householdStock = new HashMap<>();
  /**
   * track of list of all grocery StockItems
   */
  private static HashMap<Integer, Stock<Grocery>> groceryStock = new HashMap<>();

  /**
   * returns list of household stock items
   * @return list of household stock items
   */
  public static HashMap<Integer, Stock<Household>> getHouseholdStock() {
    return householdStock;
  }
  /**
   * returns list of grocery stock items
   * @return list of grocery stock items
   */
  public static HashMap<Integer, Stock<Grocery>> getGroceryStock() {
    return groceryStock;
  }

  /**
   * Adds new stock or updates the existing stock in the appropriate map.
   * @param product product to be added to the map
   * @param quantity quantity of product
   */
  public static void addStock(Product product, int quantity){

    if (product.getClass() == Grocery.class){
      if (groceryStock.get(product.getId()) == null){
      groceryStock.put(product.getId(), new Stock(product, quantity));}
      else{
        groceryStock.put(product.getId(), new Stock(product,
            groceryStock.get(product.getId()).getQuantity() + quantity));}
    }
    else{
      if (householdStock.get(product.getId()) == null){
        householdStock.put(product.getId(), new Stock(product,quantity));}
      else{
        householdStock.put(product.getId(), new Stock(product,
            householdStock.get(product.getId()).getQuantity() + quantity));
      }
    }
  }

  /**
   * calculates the total retail value of all items in household stock.
   * @return  total retail value of all items in household stock
   */
  public static double getGroceryStockRetailPrice(){
    double sum = 0;
    for (Entry<Integer, Stock<Grocery>> set : groceryStock.entrySet()) {
      sum+= set.getValue().getProduct().getPrice() * set.getValue().getQuantity();
    }
    return sum;
  }

  /**
   * calculates the total retail value of all items in household stock.
   * @return  total retail value of all items in household stock
   */
  public static double getHouseholdStockRetailPrice(){
    double sum = 0;
    for (Entry<Integer, Stock<Household>> set : householdStock.entrySet()) {
      sum+= set.getValue().getProduct().getPrice() * set.getValue().getQuantity();
    }
    return sum;

  }

  /**
   * calculates the total retail value of all items in stock.
   * @return  total retail value of all items in stock
   */
  public static double getTotalStockRetailPrice(){
    return getGroceryStockRetailPrice() + getHouseholdStockRetailPrice();
  }

  /**
   * Updates quantities of all stock items purchased in the system
   * @param id product id
   * @param quantity quantity purchased
   */
  public static void updateStock(Integer id, Integer quantity){
    if(groceryStock.get(id) !=null){
      Integer newQuantity = groceryStock.get(id).getQuantity() - quantity;
      groceryStock.put(id, new Stock(groceryStock.get(id).getProduct(),newQuantity));
      return;
    }
    if(householdStock.get(id) !=null){
      Integer newQuantity = householdStock.get(id).getQuantity() - quantity;
      householdStock.put(id, new Stock(householdStock.get(id).getProduct(),newQuantity));
    }

  }

  /**
   * Empties groceryStock and householdStock map
   */
  public static void resetInventory(){
    householdStock.clear();
    groceryStock.clear();
  }

}
