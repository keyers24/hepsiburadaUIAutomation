@Product
Feature: Product
  @Evaluation
  Scenario Outline: Product Review Evaluation
    Given navigate to website
    When  The relevant "<product>" is searched for
    And   Randomly selected product
    And   The detail page of the relevant product is accessed
    And   The Reviews tab is navigated to, and Sort by: Newest Review is selected
    And   One of the options, either thumbsUp or thumbsDown, is selected
    Then  The selection is confirmed

    Examples:
      |product|
      |iphone 13|


    @sellerComparison
    Scenario Outline: Seller Comparison
      Given navigate to website
      When  The relevant "<product>" is searched for
      And   Randomly selected product
      And   Visibility of other vendors is checked.
      And   Price information is compared between sellers
      And   The cheaper product is added to the cart
      Then  Basket is checked

      Examples:
        |product|
        |iphone 13|

  @priceComparison
  Scenario Outline: price Comparison
    Given navigate to website
    When  The relevant "<product>" is searched for
    And   Randomly selected product
    And   Check price of the selected product
    And   Product is added to cart
    Then  Price equality is verified

    Examples:
      |product|
      |iphone 13|