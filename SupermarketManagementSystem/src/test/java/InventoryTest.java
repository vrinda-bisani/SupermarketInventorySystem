import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InventoryTest {
  Grocery gro = new Grocery(1, "manu", "prnam", 100.0, Grocery.TYPE.CHEESE, 100);
  Household house = new Household(2, "papersup", "towel", 50.0, 2,Household.TYPE.PAPERTOWELS);
  Stock<Grocery> groceryStock = new Stock<>(gro,5);
  Stock<Household> householdStock = new Stock<>(house,5);
  Inventory inven = new Inventory();

  @BeforeEach
  void setUp() {
    Inventory.resetInventory();

    Inventory.addStock(gro,5);
    Inventory.addStock(house,5);
  }

  @Test
  void getGroceryStock() {
    assertTrue(Inventory.getGroceryStock().get(1)!=null);
  }

  @Test
  void getHouseholdStock() {
    assertTrue(Inventory.getHouseholdStock().get(2)!=null);
  }

  @Test
  void addStockToGrocery() {
    Grocery gro1 = new Grocery(1, "manu", "prnam", 100.0, Grocery.TYPE.CHEESE, 100);
    Inventory.addStock(gro1,5);
    assertEquals(10,Inventory.getGroceryStock().get(1).getQuantity());
  }

  @Test
  void addStockToHousehold() {
    Household house2 = new Household(2, "papersup", "towel", 100.0, 2,Household.TYPE.PAPERTOWELS);
    Inventory.addStock(house2,5);
    assertEquals(10,Inventory.getHouseholdStock().get(2).getQuantity());
  }

  @Test
  void getGroceryStockRetailPrice() {
    assertEquals(500.0,Inventory.getGroceryStockRetailPrice());
  }

  @Test
  void getHouseholdStockRetailPrice() {
    assertEquals(250.0,Inventory.getHouseholdStockRetailPrice());
  }

  @Test
  void getTotalStockRetailPrice() {
    assertEquals(750.0,Inventory.getTotalStockRetailPrice());
  }

  @Test
  void updateGroceryStock() {
    Inventory.updateStock(1,2);
    Inventory.updateStock(7,2);
    assertEquals(3,Inventory.getGroceryStock().get(1).getQuantity());
  }

  @Test
  void updateHouseholdStock() {
    Inventory.updateStock(2,1);
    assertEquals(4,Inventory.getHouseholdStock().get(2).getQuantity());
  }
}