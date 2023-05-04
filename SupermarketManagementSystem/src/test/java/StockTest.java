import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StockTest {
  Grocery gro;
  Integer quantity;
  Stock<Grocery> stock;

  @BeforeEach
  void setUp() {
    gro = new Grocery(1, "manu", "prnam", 100.0, Grocery.TYPE.CHEESE, 100);
    quantity = 2;
    stock = new Stock<>(gro,quantity);
  }

  @Test
  void getProductAnyType() {
    assertEquals(gro,stock.getProductAnyType());
  }

  @Test
  void getProduct() {
    assertEquals(gro,stock.getProduct());
  }

  @Test
  void getQuantity() {
    assertEquals(quantity,stock.getQuantity());
  }

  @Test
  void testEquals_SameObject() {
    assertTrue(stock.equals(stock));
  }

  @Test
  void testEquals_DifferentDatatype(){
    String test = "test";
    assertFalse(stock.equals(test));
  }

  @Test
  void testEquals_DifferentObject(){
    Stock<Grocery> stock1 = new Stock<>(gro,quantity);
    assertTrue(stock.equals(stock1));
  }

  @Test
  void testEquals_Null() {
    assertFalse(stock.equals(null));
  }

  @Test
  void testEquals_DifferentProduct(){
    Grocery groceryProduct1 = new Grocery(1, "manu", "prnam", 100.0, Grocery.TYPE.SALMON, 100);
    Stock<Grocery> stock1 = new Stock<>(groceryProduct1,quantity);
    assertFalse(stock.equals(stock1));
  }

  @Test
  void testEquals_DifferentQuantity(){
    Stock<Grocery> stock1 = new Stock<>(gro,10);
    assertFalse(stock.equals(stock1));
  }

  @Test
  void testHashCode() {
    Stock<Grocery> stock1 = new Stock<>(gro,quantity);
    assertEquals(stock1.hashCode(),stock.hashCode());
  }

  @Test
  void testToString() {
    assertEquals("Stock{" +
        "product=" + gro +
        ", quantity=" + quantity +
        '}',stock.toString());
  }
}