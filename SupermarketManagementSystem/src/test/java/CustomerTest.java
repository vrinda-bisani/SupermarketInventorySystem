import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {
  Customer customer;
  String name;
  int age;

  Grocery gro = new Grocery(1, "manu", "prnam", 100.0, Grocery.TYPE.CHEESE, 100);
  Grocery cheeseType2 = new Grocery(2, "Amul", "Taaza", 100.0, Grocery.TYPE.CHEESE, 100);
  @BeforeEach
  void setUp() {
    age = 20;
    name = "Will Smith";
    customer = new Customer(name,age);
    Inventory.resetInventory();
    Inventory.addStock(gro,5);
    Inventory.addStock(cheeseType2,2);

  }

  @Test
  void getName() {
    assertEquals(name,customer.getName());
  }

  @Test
  void getAge() {
    assertEquals(age,customer.getAge());
  }

  @Test
  void getCart() {
    customer.getCart().add(1,1);
    assertEquals(1,customer.getCart().cart.get(1));
  }

  @Test
  void emptyCart() {
    customer.getCart().add(1,1);
    assertTrue(customer.getCart().cart.size()!=0);
    customer.emptyCart();
    assertTrue(customer.getCart().cart.size()==0);
  }

  @Test
  void placeOrder() {
    customer.getCart().add(1,1);
    customer.placeOrder();
    assertTrue(OrderList.getOrderList().get(customer) != null);
    assertTrue((OrderList.getOrderList().get(customer)).equals(customer.getCart()));
  }

  @Test
  void testEquals_SameObject() {
    assertTrue(customer.equals(customer));
  }

  @Test
  void testEquals_DifferentDatatype(){
    String test = "test";
    assertFalse(customer.equals(test));
  }


  @Test
  void testEquals_DifferentObject(){
    Customer customer1 = new Customer(name,age);
    assertTrue(customer.equals(customer1));
  }

  @Test
  void testEquals_Null() {
    assertFalse(customer.equals(null));
  }

  @Test
  void testEquals_DifferentName(){
    Customer customer1 = new Customer("Chandler Bing",age);
    assertFalse(customer.equals(customer1));
  }

  @Test
  void testEquals_DifferentAge(){
    Customer customer1 = new Customer(name,45);
    assertFalse(customer.equals(customer1));
  }

  @Test
  void testHashCode() {
    Customer customer1 = new Customer(name,age);
    assertEquals(customer1.hashCode(),customer.hashCode());
  }

  @Test
  void testToString() {
    assertEquals("Customer{" +
        "name='" + name + '\'' +
        ", age=" + age +
        '}',customer.toString());
  }
}