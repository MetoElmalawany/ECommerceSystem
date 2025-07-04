# E-Commerce System

## Code Examples


### Scenario 1: Normal Checkout with Mixed Items

```
customer.addItemToCart(bread, 5);  
customer.addItemToCart(tv, 1);  
customer.addItemToCart(scratchCard, 2);  
customer.addItemToCart(biscuits, 3);  
  
ECommerceServices.checkout(customer);

==CONSOLE OUTPUT==
Payment received for 1235.00
Shipping Fees received for 50.75

** Shipment notice **
5x Bread          500g
1x Tv             10000g
3x Biscuits       150g

Total package weight: 10.65kg

** Checkout receipt **
5x Bread          	5.00
1x Tv             	1000.00
2x Scratch Card   	200.00
3x Biscuits       	30.00
---------------------------
SubTotal:           1235.00
Shipping:           50.75
Amount:             1285.75
Remaining Balance:  97428.50

```

### Scenario 2: Checkout with negative quantity 

```
customer.addItemToCart(bread, -5);  
customer.addItemToCart(tv, 1);  
customer.addItemToCart(scratchCard, 2);  
customer.addItemToCart(biscuits, 3);  
  
ECommerceServices.checkout(customer);

==CONSOLE OUTPUT==
Exception in thread "main" java.lang.IllegalArgumentException: Quantity cannot be negative
	at Objects.Customer.addItemToCart(Customer.java:30)
	at Main.main(Main.java:17)
```

### Scenario 3: Checkout with Empty Cart

```
ECommerceServices.checkout(customer);

==CONSOLE OUTPUT==
Exception in thread "main" java.lang.IllegalStateException: Can't Checkout an Empty cart!
	at Managers.ECommerceServices.checkout(ECommerceServices.java:43)
	at Main.main(Main.java:22)
```

### Scenario 4: Total Cost Exceeds Balance

```
Customer customer = new Customer("1", "Marwan", 1000.0);

customer.addItemToCart(bread, 5);  
customer.addItemToCart(tv, 1);  
customer.addItemToCart(scratchCard, 2);  
customer.addItemToCart(biscuits, 3);  
  
ECommerceServices.checkout(customer);

==CONSOLE OUTPUT==

Exception in thread "main" java.lang.IllegalStateException: Insufficient Balance!
	at Managers.ECommerceServices.checkout(ECommerceServices.java:48)
	at Main.main(Main.java:22)
```

### Scenario 5: Total Cost is within the Customer's Balance but the Shipment Fees is not within the Balance

```
Customer customer = new Customer("1", "Marwan", 1000.0);
 
customer.addItemToCart(tv, 1);  
   
ECommerceServices.checkout(customer);

==CONSOLE OUTPUT==

Payment received for 1000.00
Exception in thread "main" java.lang.IllegalStateException: Insufficient Balance! Your Payment has been refunded
	at Managers.ECommerceServices.shipmentFees(ECommerceServices.java:31)
	at Managers.ECommerceServices.checkout(ECommerceServices.java:59)
	at Main.main(Main.java:19)
	
```

### Scenario 6: Quantity added to Cart Exceeds the Product's Quantity

```
Product scratchCard = new Product("Scratch Card", 100, 10);  
  
customer.addItemToCart(scratchCard, 11);  
  
ECommerceServices.checkout(customer);

==CONSOLE OUTPUT==

Exception in thread "main" java.lang.IllegalArgumentException: Insufficient quantity in stock!
	at Objects.Cart.addProduct(Cart.java:15)
	at Objects.Customer.addItemToCart(Customer.java:31)
	at Main.main(Main.java:17)
	
```

### Scenario 7: Adding an Expired Product to the Cart

```
Product bread = new Product("Bread", 1, 10, true, LocalDate.of(2025,7,1), true, 100);  
  
customer.addItemToCart(bread, 1);  
  
ECommerceServices.checkout(customer);

==CONSOLE OUTPUT==

Exception in thread "main" java.lang.IllegalArgumentException: Product is expired
	at Objects.Cart.addProduct(Cart.java:19)
	at Objects.Customer.addItemToCart(Customer.java:31)
	at Main.main(Main.java:17)
	
```

