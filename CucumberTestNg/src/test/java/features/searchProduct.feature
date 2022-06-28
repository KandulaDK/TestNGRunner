Feature: Search and Place the order of the product

@SearchProducts
Scenario Outline: Search Experience for product search in both home and Offers Page

Given User is on GreenCart Landing Page
When User Searched with shortName <shortName> and Extracted resultes of the product
Then User searched for same shortname <shortName> in offers page
And check is both products are same or not


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