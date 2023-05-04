# Supermarket’s Inventory System
Supermarket has 2 types of products - Grocery and Household. The system represents the quantities of each Product as a StockItem. 
- System checks if there are enough items in stock to complete a purchase.
- It also reduce the quantity of the item in the event of a purchase and does not allow a purchase to go ahead if there are not enough items in stock.

The Inventory pulls together all of the information about the supermarket’s stock and implements some of the core functionality for the system. It keeps track of the Household staock and the Grocery Stock.

When the supermarket decides to stock a new product, the inventory allows a new StockItem to be added to the appropriate list. The system has a fetaure to return the total retail value of all items in stock.

# Shopping Cart
A customer can view a product and decides to add the product to their shopping cart. By default, the customer will not have to specify a quantity. In this case the system will add one item of the product to the cart. However, the customer can choose to add larger quantities to their cart.

If the customer tries to add more items than are in stock, an error message will be thrown but the customer will be able to continue shopping.

Adding an item to a cart does not change the stock quantity for that item. Stock will be adjusted when the order is actually processed. The customer continues shopping, adding more items to their cart.

# In case the product is out of stock during delivery
If the product is out of stock during the time of delivery the system will substitute the product with the same kind of another product is the same price as or cheaper than the original product. 
- For Grocery products, the weight of the substitution will be the same as or greater than the original product.
- For Household products, the number of units in the package will be the same as or greater than the original product. 
If no suitable substitution is found, the item will be removed from the customer’s cart.

# Receipt generation
Once all the items in the shopping cart and necessary substitutions have been gathered the order is processed, which involves the following steps.
- Any items the customer is not old enough to buy is removed from the cart.
- The quantities of all stock items purchased are updated in the system.
- The customer’s shopping cart is emptied.
- A receipt is returned summarizing the order.