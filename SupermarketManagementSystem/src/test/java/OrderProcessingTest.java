import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderProcessingTest {

  Customer customer;
  OrderProcessing orderProcessing;
  HashMap<Product,Integer> testFinalList;
  List<Product> testOutOfStock;
  List<Product> testAgeRestrictedProducts;
  OrderList odl = new OrderList();
  ShoppingCartHelper shopCartHelp = new ShoppingCartHelper();
  Grocery cheeseType1 = new Grocery(1, "Beechers", "Flagship", 50.0, Grocery.TYPE.CHEESE, 120);
  Grocery cheeseType2 = new Grocery(2, "Amul", "Taaza", 100.0, Grocery.TYPE.CHEESE, 100);
  Grocery beerType1 = new Grocery(3, "Bira", "White", 75.0, 21, Grocery.TYPE.BEER, 120);
  Grocery salmonType1 = new Grocery(4, "Seafood", "Sal", 75.0, Grocery.TYPE.SALMON, 120);

  Household towelType1 = new Household(10, "Scott", "ComfortPlus", 50.0, 2,Household.TYPE.PAPERTOWELS);
  Household towelType2 = new Household(11, "Scott", "1000", 60.0, 1,Household.TYPE.PAPERTOWELS);
  Household towelType3 = new Household(12, "Scott", "Rapid", 100.0, 20,2,Household.TYPE.PAPERTOWELS);
  Household shampooType1 = new Household(13, "Dove", "IntensiveRepair", 50.0, 1,Household.TYPE.SHAMPOO);
  @BeforeEach
  void setUp() {
    Inventory.addStock(cheeseType1, 10);
    Inventory.addStock(cheeseType2, 2);
    Inventory.addStock(beerType1, 2);
    Inventory.addStock(salmonType1, 1);

    Inventory.addStock(towelType1, 10);
    Inventory.addStock(towelType2, 2);
    Inventory.addStock(towelType3, 2);
    Inventory.addStock(shampooType1, 1);

    customer = new Customer("Chandler",18);
    customer.getCart().add(2,2);
    customer.getCart().add(3,1);
    customer.getCart().add(4,2);
    customer.getCart().add(11,2);
    customer.getCart().add(12,1);
    customer.getCart().add(13,1);
    customer.placeOrder();


    orderProcessing = new OrderProcessing(customer);
    testFinalList =new HashMap<>();
    testOutOfStock = new ArrayList<>();
    testAgeRestrictedProducts = new ArrayList<>();
  }

  @AfterEach
  void reset(){
    Inventory.resetInventory();
    customer.emptyCart();
    OrderList.removeFromOrderList(customer);
  }


  @Test
  void getCustomer() {
    assertEquals(customer,orderProcessing.getCustomer());
  }

  @Test
  void process() {
    Inventory.updateStock(4,1);
    Inventory.updateStock(2,1);
    Inventory.updateStock(11,1);
    Inventory.updateStock(13,1);

    orderProcessing.process();
    testFinalList.put(cheeseType1,2);
    testFinalList.put(towelType1,2);
    testOutOfStock.add(salmonType1);
    testOutOfStock.add(shampooType1);
    testAgeRestrictedProducts.add(beerType1);
    testAgeRestrictedProducts.add(towelType3);

    assertEquals(testFinalList,orderProcessing.getFinalList());
    assertEquals(testOutOfStock,orderProcessing.getOutOfStock());
    assertEquals(testAgeRestrictedProducts,orderProcessing.getAgeRestrictedProducts());
    assertEquals(200.0,orderProcessing.getTotalCost());
  }


  @Test
  void checkAgeRestriction() {
    assertTrue(orderProcessing.checkAgeRestriction(beerType1, customer.getAge()));
    assertFalse(orderProcessing.checkAgeRestriction(cheeseType1, customer.getAge()));
  }

  @Test
  void updateInventory() {
    Customer customer1 = new Customer("Will",20);
    customer1.getCart().add(2,1);
    customer1.placeOrder();
    OrderProcessing orderProcessing1 = new OrderProcessing(customer1);
    orderProcessing1.process();
    orderProcessing1.updateInventory();
    assertEquals(1,Inventory.getGroceryStock().get(2).getQuantity());
  }

  @Test
  void emptyCart() {
    orderProcessing.emptyCart();
    assertTrue(customer.getCart().cart.size() == 0);
  }

  @Test
  void generateReceipt() {
    orderProcessing.process();

    testFinalList.put(cheeseType2,2);
    testFinalList.put(salmonType1,1);
    testFinalList.put(towelType2,2);
    testFinalList.put(shampooType1,1);

    testAgeRestrictedProducts.add(beerType1);
    testAgeRestrictedProducts.add(towelType3);

    Receipt testReceipt = orderProcessing.generateReceipt();
    assertEquals(testFinalList,testReceipt.getFinalList());
    assertEquals(testOutOfStock,testReceipt.getOutOfStock());
    assertEquals(testAgeRestrictedProducts,testReceipt.getAgeRestrictedProducts());
    assertEquals(445.0,testReceipt.getTotalPrice());
  }

  @Test
  void testEquals_SameObject() {
    assertTrue(orderProcessing.equals(orderProcessing));
  }

  @Test
  void testEquals_DifferentDatatype(){
    String test = "test";
    assertFalse(orderProcessing.equals(test));
  }


  @Test
  void testEquals_DifferentObject(){
    OrderProcessing orderProcessing1 = new OrderProcessing(customer);
    assertTrue(orderProcessing.equals(orderProcessing1));
  }

  @Test
  void testEquals_Null() {
    assertFalse(orderProcessing.equals(null));
  }

  @Test
  void testEquals_DifferentCustomer(){
    Customer customer1 = new Customer("Will",18);
    Customer customer2 = new Customer("Adam",20);
    customer1.placeOrder();
    customer2.placeOrder();
    OrderProcessing orderProcessing1 = new OrderProcessing(customer1);
    OrderProcessing orderProcessing2 = new OrderProcessing(customer2);
    assertFalse(orderProcessing1.equals(orderProcessing2));
  }


  @Test
  void testHashCode() {
    OrderProcessing orderProcessing1 = new OrderProcessing(customer);
    assertEquals(orderProcessing1.hashCode(),orderProcessing.hashCode());
  }

  @Test
  void testToString() {
    Double totalCost = 0.0;
    assertEquals("OrderProcessing{" +
        "customer=" + customer +
        ", finalList=" + testFinalList +
        ", outOfStock=" + testOutOfStock +
        ", ageRestrictedProducts=" + testAgeRestrictedProducts +
        ", totalCost=" + totalCost +
        '}',orderProcessing.toString());
  }
}