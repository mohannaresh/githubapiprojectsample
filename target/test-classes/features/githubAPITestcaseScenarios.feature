Feature: GitHub API functionality testcases

  @EndToEndTest
  Scenario Outline:  Fetch repositories for a specific user
    Given a user with "<User_Name>" requested github api service
    Then validate that user got expected repos list
    And validate the api response with statuscode "200 OK"

    Examples: 
      | User_Name   |
      | mohannaresh |
    

  @EndToEndTest
  Scenario Outline: Fetch the total number of Github repositories for a given programming language
    Given a user with "<User_Name>" requested github api service to get list of repositories for "<Programming_Language>"
    Then validate that user got expected repos according to programming language
    And validate the api response with statuscode "200 OK"

    Examples: 
      | User_Name   | Programming_Language |
      | mohannaresh | Java                 |

  @EndToEndTest
  Scenario Outline: Filter the user Github repositories by a given date
    Given a user with "<User_Name>" requested github api service to get list of repostitories for a "<Given_Date>" and "<Time_Zone>"
    Then validate that user got expected repos list based on creation date
    And validate the api response with statuscode "200 OK"

    Examples: 
      | User_Name   | Given_Date          | Time_Zone        |
      | mohannaresh | 2021-05-25 08:28:14 | Europe/Amsterdam |

  @EndToEndTest1
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
      | User_Name   | max_pagination |
      | mohannaresh |              2 |

  @EndToEndTest
  Scenario Outline: Handle API ratelimit for unauthorised user
    Given a user with "<User_Name>" requested github api service until rate limit of "<Number_Of_Requests>" had reached
    And user waits to attain the max rate limit again for "<Wait_In_MilliSeconds>" milliseconds
    Then request again and validate that the api response statuscode is "200 OK"

    Examples: 
      | User_Name   | Number_Of_Requests | Wait_In_MilliSeconds |
      | mohannaresh |                 10 |                60000 |
