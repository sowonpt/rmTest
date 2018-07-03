@BuySearch @UserSearch_ExactLocation
Feature: As a user, I want find property to buy

  Background:
    Given user visits homepage

  Scenario Outline: Searching for a property for purchase using a precise location
    And the user searches for property to buy by 'location'
    Then the user sees the sale search criteria
    And user enters search criteria as follows
      | Location            | <Location>     |
      | Search radius       | <Radius>       |
      | Price range (min)   | <minPrice>     |
      | Price range (max)   | <maxPrice>     |
      | Min No. of bedrooms | <minBedrooms>  |
      | Max No. of bedrooms | <maxBedrooms>  |
      | Property type       | <PropertyType> |


    Then user sees search results

    Examples:
      | Location | Radius | minPrice | maxPrice | minBedrooms | maxBedrooms | PropertyType |
      | SE3      | 10     |          | 450000   | 2           |             |              |

  @BuySearch @UserSearch_non-featured
  Scenario Outline: Searching for a non-featured property for purchase
    And the user searches for property to buy by 'location'
    Then the user sees the sale search criteria
    And user enters search criteria as follows
      | Location            | <Location>     |
      | Search radius       | <Radius>       |
      | Price range (min)   | <minPrice>     |
      | Price range (max)   | <maxPrice>     |
      | Min No. of bedrooms | <minBedrooms>  |
      | Max No. of bedrooms | <maxBedrooms>  |
      | Property type       | <PropertyType> |

    Then user sees search results
    And the user selects a non-featured property
    Then the user see the property details page

    Examples:
      | Location | Radius | minPrice | maxPrice | minBedrooms | maxBedrooms | PropertyType |
      | SE3      | 5     |          | 700   | 3           |             |              |


#  @BuySearch @UserSearch_PartialValidLocation
#  Scenario Outline: Searching for a property for purchase using a partially valid location
#    And the user searches for property to buy by 'location'
#    Then the users sees the location options page
#    And the user selects a location
#    And user enters search criteria as follows
#      | Location            | <Location>     |
#      | Search radius       | <Radius>       |
#      | Price range (min)   | <minPrice>     |
#      | Price range (max)   | <maxPrice>     |
#      | Min No. of bedrooms | <minBedrooms>  |
#      | Max No. of bedrooms | <maxBedrooms>  |
#      | Property type       | <PropertyType> |
#      | Include Let Agreed  | <IncludeLet>   |
#
#    Then user sees search results
#
#    Examples:
#      | Location | Radius | minPrice | maxPrice | minBedrooms | maxBedrooms | PropertyType | IncludeLet |
#      | SE3      |        |          |          |             |             |              |            |
#
#  @BuySearch @UserSearch_PartialValidLocation @ChangeLocation
#  Scenario Outline: Searching for a property for purchase
#    And the user searches for property to buy by 'location'
#    Then the users sees the location options page
#    And the user selects to change location
#    Then user sees the property rent page
#      | Location | <Location> |
#
#    Examples:
#      | Location |
#      | SE3      |
#
#  @BuySearch @UserSearch_InvalidLocation
#  Scenario Outline: Searching for a property for purchase using an invalid location
#    And the user searches for property to buy by 'location'
#    Then the user sees the sale search error page
#      | Location | <Location> |
#
#    Examples:
#      | Location |
#      | SE3      |
#
#  @BuySearch @UserSearch_featured
#  Scenario Outline: Searching for a featured property for purchase
#    And the user searches for property to buy by 'location'
#    Then the user sees the sale search criteria
#    And user enters search criteria as follows
#      | Location            | <Location>     |
#      | Search radius       | <Radius>       |
#      | Price range (min)   | <minPrice>     |
#      | Price range (max)   | <maxPrice>     |
#      | Min No. of bedrooms | <minBedrooms>  |
#      | Max No. of bedrooms | <maxBedrooms>  |
#      | Property type       | <PropertyType> |
#      | Include Let Agreed  | <IncludeLet>   |
#
#    Then user sees search results
#    And the user selects a featured property
#    Then the user see the property details page
#
#    Examples:
#      | Location | Radius | minPrice | maxPrice | minBedrooms | maxBedrooms | PropertyType | IncludeLet |
#      | SE3      |        |          |          |             |             |              |            |





