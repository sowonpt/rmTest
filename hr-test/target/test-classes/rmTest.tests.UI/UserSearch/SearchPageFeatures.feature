@Search @Default_search
Feature: As an unregistered user, I want see what kind of property I can find on the site

  Background:


  Scenario Outline: Searching for a property without clicking a button
    And the user searches for property and uses the enter key
    Then the user sees the sale page search criteria
      | Location | <Location> |

    Examples:
      | Location |
      | SE3/     |

  @Search @InvalidSearch
  Scenario Outline: Searching for a property with invalid characters
    And And the user searches for property to rent by 'location'
    And user enters search criteria as follows
      | Location | <Location> |

    Then the user sees the error pop

    Examples:
      | Location |
      | /@!£$    |
      |          |

