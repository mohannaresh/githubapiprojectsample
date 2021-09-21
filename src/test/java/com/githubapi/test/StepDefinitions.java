package com.githubapi.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import com.githubapi.test.githubapiprojectsample.GithubapiprojectsampleApplication;
import com.githubapi.test.githubapiprojectsample.constants.GithubApiApplicationConstants;
import com.githubapi.test.githubapiprojectsample.service.GithubApiData;
import com.githubapi.test.githubapiprojectsample.service.factory.GithubApiDataFactory;
import com.githubapi.test.githubapiprojectsample.service.factory.GithubApiServiceType;
import com.githubapi.test.githubapiprojectsample.service.impl.GithubApiServiceImplementation;
import com.githubapi.test.githubapiprojectsample.utilities.Utilities;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GithubapiprojectsampleApplication.class)
public class StepDefinitions {
	@Autowired
	GithubApiServiceImplementation githubApiServiceImplementation;
	@Autowired
	Utilities utilities;
	@Value("${githubAPIURL}")
	private String githubAPIURL;
	private GithubApiData gitHubApiData = null;

	@Given("a user with {string} requested github api service")
	public void a_user_with_requested_github_api_service(String userName)
			throws RestClientException, FileNotFoundException, IOException {
		gitHubApiData = GithubApiDataFactory.createGithubApiDataFactory(GithubApiServiceType.GET_GITHUB_API_RESPONSE,
				userName, null, null, null);
		githubApiServiceImplementation.getUserRepositoriesNames(githubAPIURL, gitHubApiData.getUserName(),
				GithubApiApplicationConstants.GIT_HUB_REPOS.getValue());
	}

	@Then("validate that user got expected repos list")
	public void validate_that_user_got_expected_repos_list() {
		githubApiServiceImplementation.verifyUserReposAreDisplayedProperly();

	}

	@Then("validate the api response with statuscode {string}")
	public void validate_the_api_response_with_statuscode(String expectedStatuscode) {
		githubApiServiceImplementation.verifyApiResponseWithStatuscode(expectedStatuscode);
	}

	@Given("a user with {string} requested github api service to get list of repositories for {string}")
	public void a_user_with_requested_github_api_service_to_get_list_of_repositories_for(String userName,
			String programmingLanguage) throws FileNotFoundException, IOException {
		gitHubApiData = GithubApiDataFactory.createGithubApiDataFactory(GithubApiServiceType.GET_GITHUB_API_RESPONSE,
				userName, programmingLanguage, null, null);
		githubApiServiceImplementation.listNumberOfRepositoriesBasedOnProgramminLanguage(githubAPIURL,
				gitHubApiData.getUserName(), gitHubApiData.getProgrammingLanguages(),
				GithubApiApplicationConstants.GIT_HUB_PROGRMMINGLANGUAGE.getValue());
	}

	@Then("validate that user got expected repos according to programming language")
	public void validate_that_user_got_expected_repos_according_to_programming_language() {
		githubApiServiceImplementation.verifyUserReposProgrammingLanguagesDisplyedProperly();
	}

	@Given("a user with {string} requested github api service to get list of repostitories for a {string} and {string}")
	public void a_user_with_requested_github_api_service_to_get_list_of_repostitories_for_a_and(String userName,
			String givenDate, String timeZone) throws FileNotFoundException, IOException {
		gitHubApiData = GithubApiDataFactory.createGithubApiDataFactory(GithubApiServiceType.GET_GITHUB_API_RESPONSE,
				userName, null, givenDate, null);
		githubApiServiceImplementation.listRepositoriesBasedOnCreationDate(githubAPIURL, gitHubApiData.getUserName(),
				gitHubApiData.getGivenDate(), timeZone, GithubApiApplicationConstants.GIT_HUB_REPOS.getValue());
	}

	@Then("validate that user got expected repos list based on creation date")
	public void validate_that_user_got_expected_repos_list_based_on_creation_date() {
		githubApiServiceImplementation.verifyUserReposDisplayedAfterTheCreationDate();

	}

	@Given("a user with {string} requested github api service to get most starred github repos in decreasing order")
	public void a_user_with_requested_github_api_service_to_get_most_starred_github_repos_in_decreasing_order(
			String userName) throws FileNotFoundException, IOException {
		gitHubApiData = GithubApiDataFactory.createGithubApiDataFactory(GithubApiServiceType.GET_GITHUB_API_RESPONSE,
				userName, null, null, null);
		githubApiServiceImplementation.getStarredCountOfAUserRepositories(githubAPIURL, gitHubApiData.getUserName(),
				GithubApiApplicationConstants.GIT_HUB_STARRED_REPOS.getValue());
	}

	@Then("validate that user got expected repos list based on number of stars")
	public void validate_that_user_got_expected_repos_list_based_on_number_of_stars() {
		githubApiServiceImplementation.verifyUserReposWithStarsDisplayed();
	}

	@Given("a user with {string} requested github api service to get number if repos based on {string} input value")
	public void a_user_with_requested_github_api_service_to_get_number_if_repos_based_on_input_value(String userName,
			String maxPaginationLimit) throws FileNotFoundException, IOException {
		gitHubApiData = GithubApiDataFactory.createGithubApiDataFactory(GithubApiServiceType.GET_GITHUB_API_RESPONSE,
				userName, null, null, maxPaginationLimit);
		githubApiServiceImplementation.getPageLimitReposForUser(githubAPIURL, gitHubApiData.getUserName(),
				gitHubApiData.getMaxPagination(), GithubApiApplicationConstants.GIT_HUB_PAGINATION.getValue());
	}

	@Then("validate that user got expected repos list based on input pagination value")
	public void validate_that_user_got_expected_repos_list_based_on_input_pagination_value() {
		githubApiServiceImplementation.verifyUserReposDisplayedWithInputPagination();
	}

	@Then("validate that the api response for starred list of repos statuscode is {string}")
	public void validate_that_the_api_response_for_starred_list_of_repos_statuscode_is(String expectedStatuscode) {
		githubApiServiceImplementation.verifyApiResponseWithStatuscode(expectedStatuscode);
	}

	@Then("validate that the api response for input pagination value statuscode is {string}")
	public void validate_that_the_api_response_for_input_pagination_value_statuscode_is(String expectedStatuscode) {
		githubApiServiceImplementation.verifyApiResponseWithStatuscode(expectedStatuscode);
	}

	@Given("a user with {string} requested github api service until rate limit had reached")
	public void a_user_with_requested_github_api_service_until_rate_limit_had_reached(String userName) {

	}

	@Given("a user with {string} requested github api service until rate limit of {string} had reached")
	public void a_user_with_requested_github_api_service_until_rate_limit_of_had_reached(String userName,
			String numberOfApiRequests) {
		gitHubApiData = GithubApiDataFactory.createGithubApiDataFactory(GithubApiServiceType.GET_GITHUB_API_RESPONSE,
				userName, null, null, null);
		githubApiServiceImplementation.requestAPIUntilRateLimitReached(githubAPIURL, gitHubApiData.getUserName(),
				GithubApiApplicationConstants.GIT_HUB_RATE_LIMIT.getValue(), Integer.parseInt(numberOfApiRequests));
	}

	@Given("user waits to attain the max rate limit again for {string} milliseconds")
	public void user_waits_to_attain_the_max_rate_limit_again_for_milliseconds(String waitTimeToAttainRateLimit)
			throws NumberFormatException, InterruptedException {
		Thread.sleep(Long.parseLong(waitTimeToAttainRateLimit));
	}

	@Then("request again and validate that the api response statuscode is {string}")
	public void request_again_and_validate_that_the_api_response_statuscode_is(String expectedStatuscode) {
		githubApiServiceImplementation.requestAPIUntilRateLimitReached(githubAPIURL, gitHubApiData.getUserName(),
				GithubApiApplicationConstants.GIT_HUB_RATE_LIMIT.getValue(), 1);
		githubApiServiceImplementation.verifyApiResponseWithStatuscode(expectedStatuscode);
	}
}
