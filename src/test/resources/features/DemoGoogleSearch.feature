@google @demo
Feature: Google Search

  @search @ui
  Scenario: Searching for answers
    Given user is on [Google] home page
    When user enters "Joker" in the search box and then submits
    Then user sees a list of search results

  @search @ui @firefox
  Scenario: Searching for answers
    Given user is on [Google] home page
    When user enters "Joker" in the search box and then submits
    Then user sees a list of search results
