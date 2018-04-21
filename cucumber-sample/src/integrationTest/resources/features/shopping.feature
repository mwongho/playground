Feature: Shopping Cart functionality 

	A virtual shopping car that online shoppers can add and remove items to 
	before checking out

	Scenario: Adding one product to the shopping cart
		Given an empty shopping cart
		When customer adds 1 apple to the shopping cart
		Then the shopping cart should contain only 1 apple
		
	Scenario: Adding one product then add addition same product to the shopping cart
		Given an empty shopping cart
		When customer adds 1 apple to the shopping cart
		And customer adds 1 apple to the shopping cart
		Then the shopping cart should contain only 2 apple
		
	Scenario: Adding 2 different products to the shopping cart
		Given an empty shopping cart
		When customer adds 1 apple to the shopping cart
		And customer adds 3 pear to the shopping cart
		Then the shopping cart should contain only 1 apple
		And the shopping cart should contain only 3 pear
		
	Scenario: Adding 2 different products to the shopping cart and sum prices
		Given an empty shopping cart
		When customer adds 1 apple to the shopping cart
		And customer adds 3 pear to the shopping cart
		Then the shopping cart should contain only 1 apple
		And the shopping cart should contain only 3 pear
		And the shopping cart Total Amount should be 25.00
