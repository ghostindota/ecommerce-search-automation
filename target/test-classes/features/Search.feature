Feature: Amazon search functionality

  Background:
    Given I am on the Amazon home page

  Scenario: Valid search term
    When I search for "laptop"
    Then I should see search results for "laptop"

  Scenario: Invalid search term
    When I search for ">XDWaSz???$%#@!@$#SAASD@#@#@"
    Then I should see no search results for ">XDWaSz???$%#@!@$#SAASD@#@#@"

  Scenario: Search suggestions
    When I start typing "laptop" in the search box
    Then I should see search suggestions

  Scenario: No search term
    When I search for ""
    Then I should still be on the home page

  Scenario: Search by category
    When I search for "laptop" in the "Electronics" category
    Then I should see search results for "laptop" in "Electronics"

  Scenario: Search with filters
    When I search for "laptop"
    When I apply a price filter from "minPrice" to "maxPrice"
    Then I should see search results for "searchTerm" within the price range "minPrice" and "maxPrice"

  Scenario: Search with sort options
    When I search for "laptop"
    When I sort the results by "Price: Low to High"
    Then I should see search results sorted by "Price: Low to High"

  Scenario: Search history
    When I search for "laptop"
    And I delete the entered search term
    Then I should see "laptop" in my search history

  Scenario: Edge case search term
    When I search for a very long search term "assupercalifragilisticexpialidociouadsfaasdfadsfaafdsaf"
    Then I should see no search results for long term
