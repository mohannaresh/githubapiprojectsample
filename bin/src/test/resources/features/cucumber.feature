Feature: GitHub API functionality testcases

  @EndToEndTest
  Scenario Outline:  Fetch repositories for a specific user
    Given a user with "<User_Name>" requested github api service
    Then validate that user got expected repos list
    And validate the api response with statuscode "200 OK"

    Examples: 
      | User_Name       |
      | srikanthpragada |

  @EndToEndTest
  Scenario Outline: Fetch the total number of Github repositories for a given programming language
    Given a user with "<User_Name>" requested github api service to get list of repositories for "<Programming_Language>"
    Then validate that user got expected repos according to programming language
    And validate the api response with statuscode "200 OK"

    Examples: 
      | User_Name       | Programming_Language            |
      | srikanthpragada | Java,Python,ASP,HTML,TypeScript |

  @EndToEndTest
  Scenario Outline: Filter the user Github repositories by a given date
    Given a user with "<User_Name>" requested github api service to get list of repostitories for a "<Given_Date>"
    Then validate that user got expected repos list based on creation date
    And validate the api response with statuscode "200 OK"

    Examples: 
      | User_Name       | Given_Date          |
      | srikanthpragada | 2020-01-31 11:44:44 |

  @EndToEndTest
  Scenario Outline: Fetch the most starred Github repositories and order the results decreasingly
    Given a user with "<User_Name>" requested github api service to get most starred github repos in decreasing order
    Then validate that user got expected repos list based on number of stars
    And validate that the api response for starred list of repos statuscode is "200 OK"

    Examples: 
      | User_Name   |
      | mohannaresh |

  @EndToEndTest
  Scenario Outline: Specify a maximum number of results using pagination
    Given a user with "<User_Name>" requested github api service to get number if repos based on "<max_pagination>" input value
    Then validate that user got expected repos list based on input pagination value
    And validate that the api response for input pagination value statuscode is "200 OK"

    Examples: 
      | User_Name       | max_pagination |
      | srikanthpragada |              5 |
