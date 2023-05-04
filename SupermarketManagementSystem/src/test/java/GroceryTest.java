import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GroceryTest {

  Grocery groceryProduct;
  Integer id;
  String manufacturer;
  String productname;
  Double price;
  Grocery.TYPE type;
  Double weight;
  @BeforeEach
  void setUp() {
    id=1;
    manufacturer = "Beechers";
    productname = "Flagship";
    price = 30.0;
    type = Grocery.TYPE.SALMON;
    weight = 10.0;
    groceryProduct = new Grocery(id, manufacturer, productname, price, type, weight);
  }

  @Test
  void getWeight() {
    assertEquals(weight,groceryProduct.getWeight());
  }

  @Test
  void getType() {
    assertEquals(type,groceryProduct.getType());
  }

  @Test
  void testEquals_SameObject() {
    assertTrue(groceryProduct.equals(groceryProduct));
  }

  @Test
  void testEquals_DifferentDatatype(){
    String test = "test";
    assertFalse(groceryProduct.equals(test));
  }

  @Test
  void testEquals_DifferentObject(){
    Grocery groceryProduct1 = new Grocery(id,manufacturer,productname,price,type,weight);
    assertTrue(groceryProduct.equals(groceryProduct1));
  }

  @Test
  void testEquals_Null() {
    assertFalse(groceryProduct.equals(null));
  }

  @Test
  void testEquals_Super(){
    Grocery groceryProduct1 = new Grocery(id,manufacturer,productname,price, 20,Grocery.TYPE.BEER,weight);
    assertFalse(groceryProduct.equals(groceryProduct1));
  }

  @Test
  void testEquals_DifferentType(){
    Grocery groceryProduct1 = new Grocery(id,manufacturer,productname,price, Grocery.TYPE.BEER,weight);
    assertFalse(groceryProduct.equals(groceryProduct1));
  }

  @Test
  void testEquals_DifferentWeight(){
    Grocery groceryProduct1 = new Grocery(id,manufacturer,productname,price, type,90.0);
    assertFalse(groceryProduct.equals(groceryProduct1));
  }

  @Test
  void testHashCode() {
    Grocery groceryProduct1 = new Grocery(id,manufacturer,productname,price, type,weight);
    assertEquals(groceryProduct.hashCode(),groceryProduct1.hashCode());
  }

  @Test
  void testToString() {
    assertEquals("Grocery{" +
        "weight=" + weight +
        ", type=" + type +
        '}',groceryProduct.toString());
  }
}