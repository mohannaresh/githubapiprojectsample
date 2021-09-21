# githubapiprojectsample

Agenda: This project is about displaying user repos for the given input parameters that covers below mentioned test cases.

Software prerequisites:
JDK 11
Maven 3.0.0

Testcases:
1. Fetch repositories for a specific user
2. Fetch the total number of Github repositories for a given programming language
3. Filter the user Github repositories by a given date
4. Fetch the most starred Github repositories and order the results decreasingly
5. Specify a maximum number of results using pagination


Steps to run the project:

1. Checkout/download the project
2. Unzip the folder
3. Launch the project in any of the IDE like SpringToolSuit or Webstorm
4. Right click on the project root
5. Select "Maven build" ---> type "clean install" in Goals textfield
5. Right click on the project root ---> Click run as "Maven test"
6. It will run all the test scenarios mentioned in the "./src/test/resources/features/cucmber.feature" file

Steps to run the project via CMD:
1. Navigate to the root directory of the project
2. Run "mvn clean install"
3. Run "mvn test"

Steps to run individual scenario:

1. Change the tag name mentioned in the "./src/test/resources/features/githubAPITestcaseScenarios.feature" file, above the scenario that we would like to run Ex: @EndToEndTest
2. Change the tag name to same in the "./src/test/java/com/githubapi/test/CucumberRunnerTest.java" file. Under cucumberOptions Ex: tags="@EndToEndTest"

Test reports:

1. We have created reports on JSON and an html format. You can find test report for the testcases in "{projectRoot}/target/cucumber.json" or you can view the index.html file in "{projectRoot/target/cucumber-reports/index.html}" 

Technologies & concepts:

Programmin Language : Java
Java Concepts used : Interface, Generics, Collections, Resttemplate
for API, Spring boot with cucumber BDD framework, Data factory design patterns

Edge cases covered: 
1. If github account doesn't exists for the given user while trying to fetch the repositories. In this case we are showing message that user doesn't have github repository.
2. User has github account but he doesn't have any repositories created we are logged an info message that he doesn't have any repositories.
3. While fetching the user repositories based on programming language there are few repositories with "language":null is returned with postman get request. In this case we filtered that repository using code. If we want to check for multiple programming languages then we can add comma separated ones. Ex: Java,C#
4. For creation date scenrio while getting repositories list after the given date and time user timezone is handled at code level to convert to UTC
5.


