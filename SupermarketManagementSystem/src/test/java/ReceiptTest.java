import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReceiptTest {
  Receipt receipt;
  double totalPrice;
  HashMap<Product,Integer> testFinallist =new HashMap<>();
  List<Product> testOutOfStock = new ArrayList<>();
  List<Product> testAgeRestrictedproducts = new ArrayList<>();
  Grocery cheeseType1 = new Grocery(1, "Beechers", "Flagship", 50.0, Grocery.TYPE.CHEESE, 120);

  @BeforeEach
  void setUp() {
    testFinallist.put(cheeseType1,1);
    testOutOfStock.add(cheeseType1);
    testAgeRestrictedproducts.add(cheeseType1);
    totalPrice = 50.0;
    receipt = new Receipt(totalPrice,testFinallist,testOutOfStock,testAgeRestrictedproducts);
  }

  @Test
  void getTotalPrice() {
    assertEquals(totalPrice,receipt.getTotalPrice());
  }

  @Test
  void getFinalList() {
    assertEquals(testFinallist,receipt.getFinalList());
  }

  @Test
  void getOutOfStock() {
    assertEquals(testOutOfStock,receipt.getOutOfStock());
  }

  @Test
  void getAgeRestrictedProducts() {
    assertEquals(testAgeRestrictedproducts,receipt.getAgeRestrictedProducts());
  }

  @Test
  void testEquals_SameObject() {
    assertTrue(receipt.equals(receipt));
  }

  @Test
  void testEquals_DifferentDatatype(){
    String test = "test";
    assertFalse(receipt.equals(test));
  }


  @Test
  void testEquals_DifferentObject(){
    Receipt receipt1 = new Receipt(totalPrice,testFinallist,testOutOfStock,testAgeRestrictedproducts);
    assertTrue(receipt.equals(receipt1));
  }

  @Test
  void testEquals_Null() {
    assertFalse(receipt.equals(null));
  }

  @Test
  void testEquals_DifferentPrice(){
    Receipt receipt1 = new Receipt(10.0,testFinallist,testOutOfStock,testAgeRestrictedproducts);
    assertFalse(receipt.equals(receipt1));
  }

  @Test
  void testEquals_DifferentFinalList(){
    Receipt receipt1 = new Receipt(totalPrice,new HashMap<>(),testOutOfStock,testAgeRestrictedproducts);
    assertFalse(receipt.equals(receipt1));
  }

  @Test
  void testEquals_DifferentOutOfStockList(){
    Receipt receipt1 = new Receipt(totalPrice, testFinallist, new ArrayList<>(), testAgeRestrictedproducts);
    assertFalse(receipt.equals(receipt1));
  }

  @Test
  void testEquals_DifferentAgeRestrictedproducts(){
    Receipt receipt1 = new Receipt(totalPrice, testFinallist, testOutOfStock, new ArrayList<>());
    assertFalse(receipt.equals(receipt1));
  }

  @Test
  void testHashCode() {
    Receipt receipt1 = new Receipt(totalPrice,testFinallist,testOutOfStock,testAgeRestrictedproducts);
    assertEquals(receipt1.hashCode(),receipt.hashCode());
  }

  @Test
  void testToString() {
    assertEquals("Receipt{" +
        "totalPrice=" + totalPrice +
        ", finalList=" + testFinallist +
        ", outOfStock=" + testOutOfStock +
        ", ageRestrictedProducts=" + testAgeRestrictedproducts +
        '}',receipt.toString());
  }
}