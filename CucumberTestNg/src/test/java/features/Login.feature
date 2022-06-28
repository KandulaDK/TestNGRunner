Feature: Instagram Login
  
  @tag1
  Scenario: Login to instagram Page
    Given User is on Insta login page
    When User enters username "nani" and password "issue not rasied"
    Then home page should be displayed
    And  prints the tittle of the page

	@somekTest
  Scenario Outline: Title of your scenario outline
    Given I want to write a step with <name>
    When I check for the <value> in step
    Then I verify the in step <status>

    Examples: 
      | name  | value | status |
      | name1 |     5 |	true	 |
      | name2 |     7 | false  |
      |dinesh |     22| success|
      |Akshay |     26| Pass   |
      |Srikanth|    50| Fail   |
