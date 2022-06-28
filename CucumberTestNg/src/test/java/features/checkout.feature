Feature: Place the order of the product

@checkOut
Scenario Outline: Search Experience for product search in both home and Offers Page

Given User is on GreenCart Landing Page
When User Searched with shortName <shortName> and Extracted resultes of the product
And add "3" items of the searched product to cart
Then User proceeds to checkout 
And validate the items in the checkout page
And verfiy user has ability enter promo code and place the order

Examples:
  |shortName|
	|	Tom		|
#	|	Car		|
#	|	cau		|
#	|	Wal		|
#	| cas		|
#	| bea		|
#	| ras		|
#	|	pis		|