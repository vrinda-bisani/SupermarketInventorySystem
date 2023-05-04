import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HouseholdTest {

  Household householdProduct;
  Integer id;
  String manufacturer;
  String productname;
  Double price;
  Household.TYPE type;
  Integer unit;

  @BeforeEach
  void setUp() {
    id=1;
    manufacturer = "Beechers";
    productname = "Flagship";
    price = 30.0;
    type = Household.TYPE.PAPERTOWELS;
    unit = 1;
    householdProduct = new Household(id, manufacturer, productname, price, unit,type);
  }

  @Test
  void getUnit() {
    assertEquals(unit,householdProduct.getUnit());
  }

  @Test
  void getType() {
    assertEquals(type,householdProduct.getType());
  }

  @Test
  void testEquals_SameObject() {
    assertTrue(householdProduct.equals(householdProduct));
  }

  @Test
  void testEquals_DifferentDatatype(){
    String test = "test";
    assertFalse(householdProduct.equals(test));
  }

  @Test
  void testEquals_DifferentObject(){
    Household householdProduct1 = new Household(id,manufacturer,productname,price,unit,type);
    assertTrue(householdProduct.equals(householdProduct1));
  }

  @Test
  void testEquals_Null() {
    assertFalse(householdProduct.equals(null));
  }

  @Test
  void testEquals_Super(){
    Household householdProduct1 = new Household(id,manufacturer,productname,price,20,unit,type);
    assertFalse(householdProduct.equals(householdProduct1));
  }

  @Test
  void testEquals_DifferentType(){
    Household householdProduct1 = new Household(id,manufacturer,productname,price,unit, Household.TYPE.SHAMPOO);
    assertFalse(householdProduct.equals(householdProduct1));
  }

  @Test
  void testEquals_DifferentUnit(){
    Household householdProduct1 = new Household(id,manufacturer,productname,price,2, type);
    assertFalse(householdProduct.equals(householdProduct1));
  }

  @Test
  void testHashCode() {
    Household householdProduct1 = new Household(id,manufacturer,productname,price,unit,type);
    assertEquals(householdProduct1.hashCode(),householdProduct1.hashCode());
  }

  @Test
  void testToString() {
    assertEquals("Household{" +
        "unit=" + unit +
        ", type=" + type +
        '}', householdProduct.toString());
  }
}