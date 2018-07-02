@RentSearch @UserSearch_ExactLocation
Feature: As a user, I want find property to rent

  Background:
    Given user visits homepage

  Scenario Outline: Searching for a property for rent using using a precise location
    And the user searches for property to rent by 'location'
    Then the user sees the rent search criteria
    And user enters search criteria as follows
      | Location            | <Location>     |
      | Search radius       | <Radius>       |
      | Price range (min)   | <minPrice>     |
      | Price range (max)   | <maxPrice>     |
      | Min No. of bedrooms | <minBedrooms>  |
      | Max No. of bedrooms | <maxBedrooms>  |
      | Property type       | <PropertyType> |
      | Include Let Agreed  | <IncludeLet>   |

    Then user sees search results

    Examples:
      | Location | Radius | minPrice | maxPrice | minBedrooms | maxBedrooms | PropertyType | IncludeLet |
      | SE3      |        |          |          |             |             |              |            |


  @RentSearch @UserSearch_PartialValidLocation
  Scenario Outline: Searching for a property for rent using a partially valid location
    And the user searches for property to rent by 'location'
    Then the users sees the location options page
    And the user selects a location
    And user enters search criteria as follows
      | Location            | <Location>     |
      | Search radius       | <Radius>       |
      | Price range (min)   | <minPrice>     |
      | Price range (max)   | <maxPrice>     |
      | Min No. of bedrooms | <minBedrooms>  |
      | Max No. of bedrooms | <maxBedrooms>  |
      | Property type       | <PropertyType> |
      | Include Let Agreed  | <IncludeLet>   |

    Then user sees search results

    Examples:
      | Location | Radius | minPrice | maxPrice | minBedrooms | maxBedrooms | PropertyType | IncludeLet |
      | SE3      |        |          |          |             |             |              |            |

  @RentSearch @UserSearch_PartialValidLocation @ChangeLocation
  Scenario Outline: Searching for a property for rent
    And the user searches for property to rent by 'location'
    Then the users sees the location options page
    And the user selects to change location
    Then user sees the property rent page
      | Location | <Location> |

    Examples:
      | Location |
      | SE3      |

  @RentSearch @UserSearch_InvalidLocation
  Scenario Outline: Searching for a property for rent using an invalid location
    And the user searches for property to rent by 'location'
    Then the user sees the rent search error page
      | Location           | <Location>     |

    Examples:
      | Location |
      | SE3      |

  @RentSearch @UserSearch_featured
  Scenario Outline: Searching for a featured property for rent
    And the user searches for property to rent by 'location'
    Then the user sees the rent search criteria
    And user enters search criteria as follows
      | Location            | <Location>     |
      | Search radius       | <Radius>       |
      | Price range (min)   | <minPrice>     |
      | Price range (max)   | <maxPrice>     |
      | Min No. of bedrooms | <minBedrooms>  |
      | Max No. of bedrooms | <maxBedrooms>  |
      | Property type       | <PropertyType> |
      | Include Let Agreed  | <IncludeLet>   |

    Then the user sees search results
    And the user selects a featured property
    Then the user sees the property details page

    Examples:
      | Location | Radius | minPrice | maxPrice | minBedrooms | maxBedrooms | PropertyType | IncludeLet |
      | SE3      |        |          |          |             |             |              |            |

  @RentSearch @UserSearch_non-featured
  Scenario Outline: Searching for a non-featured property for rent
    And the user searches for property to rent by 'location'
    Then the user sees the rent search criteria
    And user enters search criteria as follows
      | Location            | <Location>     |
      | Search radius       | <Radius>       |
      | Price range (min)   | <minPrice>     |
      | Price range (max)   | <maxPrice>     |
      | Min No. of bedrooms | <minBedrooms>  |
      | Max No. of bedrooms | <maxBedrooms>  |
      | Property type       | <PropertyType> |
      | Include Let Agreed  | <IncludeLet>   |

    Then user sees search results
    And the user selects a non-featured property
    Then the user see the property details page

    Examples:
      | Location | Radius | minPrice | maxPrice | minBedrooms | maxBedrooms | PropertyType | IncludeLet |
      | SE3      |        |          |          |             |             |              |            |

