@tea @demo
Feature: Shopping Boba Tea

  @boba
  Scenario: Drinking my favorite boba tea
    Given I want to drink "Matcha" tea
    When I go to visit my favorite tea shop
      |tea name| count|
      |Taro Milk| 2   |
      |Mango |3       |
      |Matcha|5       |
    Then I drink my favorite tea
