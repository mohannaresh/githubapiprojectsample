$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("classpath:features/githubAPITestcaseScenarios.feature");
formatter.feature({
  "name": "GitHub API functionality testcases",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "Fetch the most starred Github repositories and order the results decreasingly",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@EndToEndTest1"
    }
  ]
});
formatter.step({
  "name": "a user with \"\u003cUser_Name\u003e\" requested github api service to get most starred github repos in decreasing order",
  "keyword": "Given "
});
formatter.step({
  "name": "validate that user got expected repos list based on number of stars",
  "keyword": "Then "
});
formatter.step({
  "name": "validate that the api response for starred list of repos statuscode is \"200 OK\"",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "User_Name"
      ]
    },
    {
      "cells": [
        "mohannaresh"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Fetch the most starred Github repositories and order the results decreasingly",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@EndToEndTest1"
    }
  ]
});
formatter.step({
  "name": "a user with \"mohannaresh\" requested github api service to get most starred github repos in decreasing order",
  "keyword": "Given "
});
formatter.match({
  "location": "com.githubapi.test.StepDefinitions.a_user_with_requested_github_api_service_to_get_most_starred_github_repos_in_decreasing_order(String) in file:/Users/Gudimella.Naresh/Desktop/Mohan/githubapiprojectsample-main/target/test-classes/"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "validate that user got expected repos list based on number of stars",
  "keyword": "Then "
});
formatter.match({
  "location": "com.githubapi.test.StepDefinitions.validate_that_user_got_expected_repos_list_based_on_number_of_stars() in file:/Users/Gudimella.Naresh/Desktop/Mohan/githubapiprojectsample-main/target/test-classes/"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "validate that the api response for starred list of repos statuscode is \"200 OK\"",
  "keyword": "And "
});
formatter.match({
  "location": "com.githubapi.test.StepDefinitions.validate_that_the_api_response_for_starred_list_of_repos_statuscode_is(String) in file:/Users/Gudimella.Naresh/Desktop/Mohan/githubapiprojectsample-main/target/test-classes/"
});
formatter.result({
  "status": "passed"
});
});