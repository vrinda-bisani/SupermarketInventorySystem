import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShoppingCartTest {
  ShoppingCart cart;
  Grocery gro = new Grocery(1, "manu", "prnam", 100.0, Grocery.TYPE.CHEESE, 100);
  Household house = new Household(2, "papersup", "towel", 50.0, 2,Household.TYPE.PAPERTOWELS);
  @BeforeEach
  void setUp() {
    Inventory.resetInventory();
    Inventory.addStock(gro,5);
    Inventory.addStock(house,5);
    cart = new ShoppingCart();
    cart.emptyCart();
    cart.add(1,3);
  }

  @Test
  void getCart() {
    assertEquals(cart.cart,cart.getCart());
  }

  @Test
  void add() {
    cart.add(2);
    assertTrue(cart.getCart().get(2) != null &&
        cart.getCart().get(2) == 1);

  }

  @Test
  void addExistingProductToCart1() {
    cart.add(1);
    assertTrue(cart.getCart().get(1) != null &&
        cart.getCart().get(1) == 4);
  }

  @Test
  void addExistingProductToCart2() {
    cart.add(1,3);
    assertTrue(cart.getCart().get(1) != null &&
        cart.getCart().get(1) == 5);
  }


  @Test
  void testAdd() {
    assertTrue(cart.getCart().get(1) != null &&
        cart.getCart().get(1) == 3);
  }

  @Test
  void testAddForInsufficientQuantity1() {
    cart.add(2,6);
    assertTrue(cart.getCart().get(2) != null &&
        cart.getCart().get(2) == 5);
  }

  @Test
  void testAddForInsufficientQuantity2() {
    Inventory.updateStock(2,5);
    cart.add(2);
    assertTrue(cart.getCart().get(2) == null);
  }

  @Test
  void testAddForProductNotFound() {
    cart.add(15);
    assertTrue(cart.getCart().get(15) == null);
  }



  @Test
  void totalCost() {
    cart.add(2,2);
    assertEquals(400.0,cart.totalCost());
  }

  @Test
  void testEquals_SameObject() {
    assertTrue(cart.equals(cart));
  }

  @Test
  void testEquals_DifferentDatatype(){
    String test = "test";
    assertFalse(cart.equals(test));
  }

  @Test
  void testEquals_DifferentObject(){
    ShoppingCart cart1 = new ShoppingCart();
    cart1.add(1,3);
    assertTrue(cart.equals(cart1));
  }

  @Test
  void testEquals_Null() {
    assertFalse(cart.equals(null));
  }

  @Test
  void testEquals_DifferentValues(){
    ShoppingCart cart1 = new ShoppingCart();
    cart1.add(2,3);
    assertFalse(cart.equals(cart1));
  }


  @Test
  void testHashCode() {
    ShoppingCart cart1 = new ShoppingCart();
    cart1.add(1,3);
    assertEquals(cart1.hashCode(),cart.hashCode());
  }

  @Test
  void testToString() {
    assertEquals("ShoppingCart{" +
        "cart=" + cart.cart +
        '}',cart.toString());
  }
}