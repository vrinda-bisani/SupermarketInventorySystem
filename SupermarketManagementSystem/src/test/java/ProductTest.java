import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductTest {

  Product product;
  Integer id;
  String manufacturer;
  String productname;
  Double price;

  @BeforeEach
  void setUp() {
    id=1;
    manufacturer = "Beechers";
    productname = "Flagship";
    price = 30.0;
    product = new Product(id, manufacturer, productname, price);
  }

  @Test
  void getId() {
    assertEquals(id,product.getId());
  }

  @Test
  void getManufacturer() {
    assertEquals(manufacturer,product.getManufacturer());
  }

  @Test
  void getProductName() {
    assertEquals(productname,product.getProductName());
  }

  @Test
  void getPrice() {
    assertEquals(price,product.getPrice());
  }

  @Test
  void getAgeLimit() {
    assertEquals(0,product.getAgeLimit());
  }

  @Test
  void testEquals_SameObject() {
    assertTrue(product.equals(product));
  }

  @Test
  void testEquals_DifferentDatatype(){
    String test = "test";
    assertFalse(product.equals(test));
  }

  @Test
  void testEquals_DifferentObject(){
    Product product1 = new Product(id,manufacturer,productname,price);
    assertTrue(product.equals(product1));
  }

  @Test
  void testEquals_Null() {
    assertFalse(product.equals(null));
  }

  @Test
  void testEquals_DifferentID(){
    Product product1 = new Product(2,manufacturer,productname,price);
    assertFalse(product.equals(product1));
  }

  @Test
  void testEquals_DifferentManufacturer() {
    Product product1 = new Product(id,"Amul",productname,price);
    assertFalse(product.equals(product1));
  }

  @Test
  void testEquals_DifferentProductName() {
    Product product1 = new Product(id,manufacturer,"go",price);
    assertFalse(product.equals(product1));
  }
  @Test
  void testEquals_DifferentPrice() {
    Product product1 = new Product(id,manufacturer,productname,40.0);
    assertFalse(product.equals(product1));
  }

  @Test
  void testEquals_DifferentAgeLimit() {
    Product product1 = new Product(id,manufacturer,productname,price,20);
    assertFalse(product.equals(product1));
  }

  @Test
  void testHashCode() {
    Product product1 = new Product(id,manufacturer,productname,price);
    assertEquals(product1.hashCode(),product.hashCode());
  }

  @Test
  void testToString() {
    assertEquals("Product{" +
        "manufacturer='" + manufacturer + '\'' +
        ", id=" + id +
        ", productName='" + productname + '\'' +
        ", price=" + price +
        ", ageLimit=" + 0 +
        '}',product.toString());
  }
}