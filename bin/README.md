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

1. Change the tag name mentioned in the "./src/test/resources/features/cucmber.feature" file, above the scenario that we would like to run Ex: @EndToEndTest
2. Change the tag name to same in the "./src/test/java/com/githubapi/test/CucumberRunnerTest.java" file. Under cucumberOptions Ex: tags="@EndToEndTest"

Technologies & concepts:
Programmin Language : Java
Java Concepts used : Interface, Generics, Collections, Resttemplate
Framework : Spring boot with cucumber BDD framework, Data factory design patterns

