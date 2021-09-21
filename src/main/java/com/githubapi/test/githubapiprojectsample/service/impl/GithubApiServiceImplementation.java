package com.githubapi.test.githubapiprojectsample.service.impl;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import com.githubapi.test.githubapiprojectsample.service.GithubApiService;
import com.githubapi.test.githubapiprojectsample.utilities.Utilities;

@Service
public abstract class GithubApiServiceImplementation implements GithubApiService {
	@Autowired
	Utilities utilities;
	private List<String> repos = new ArrayList<String>();
	private Map<String, Integer> repoLanguageMapOrrepoBasedOnNumberOfStars = new HashMap<String, Integer>();
	private Map<String, String> repoBasedOnCreationDate = new HashMap<String, String>();
	private final Logger LOGGER = LoggerFactory.getLogger(GithubApiServiceImplementation.class);
	private ResponseEntity<String> response;

	public abstract ResponseEntity<String> getGitHubSearchApiResponse(String githubAPIURL, String githubUsername,
			String pathOrQueryParameter);

	public List<String> getUserRepositoriesNames(String githubAPIURL, String githubUsername,
			String pathOrQueryParameter) throws RestClientException, FileNotFoundException, IOException {
		response = utilities.getGitHubSearchApiResponse(githubAPIURL, githubUsername, pathOrQueryParameter);
		List<GithubApiResponseImplementation> myObjects = utilities.performMappingForGitHubAPIResponse(response);
		if (myObjects.size() >= 1) {
			myObjects.forEach(name -> {
				String repoName = name.getName();
				repos.add(repoName);
				LOGGER.info("User repo name is::::::::" + repoName);
			});
		} else {
			LOGGER.info("Specified user doesn't have any github repositories");
		}
		return repos;
	}

	public void verifyUserReposAreDisplayedProperly() {
		assertTrue("There are no repositories for the given user or user name is empty", !repos.isEmpty());
	}

	public void verifyApiResponseWithStatuscode(String expectedStatusCode) {
		Assert.assertTrue(
				"Failed to verify the API Response status code. Expected status code is:::: " + expectedStatusCode
						+ " But received status code is::::" + response.getStatusCode().toString() + "",
				response.getStatusCode().toString().equals(expectedStatusCode));
		Assert.assertTrue("Failed to verify the API Response content type.",
				response.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON_UTF8));
	}

	public Map<String, Integer> listNumberOfRepositoriesBasedOnProgramminLanguage(String githubAPIURL,
			String githubUsername, String programmingLanguages, String pathOrQueryParameter)
			throws FileNotFoundException, IOException {
		String[] programmingLanguagesArray = programmingLanguages.split(",");
		if (StringUtils.isNotEmpty(programmingLanguages) && programmingLanguagesArray.length >= 1) {
			response = utilities.getGitHubSearchApiResponse(githubAPIURL, githubUsername, pathOrQueryParameter);
			List<GithubApiResponseImplementation> myObjects = utilities.performMappingForGitHubAPIResponse(response);
			if (myObjects.size() >= 1) {
				myObjects.forEach(name -> {
					repos.add(name.getProgrammingLanguage());
				});
			}
			for (String progammingLanguage : programmingLanguagesArray) {
				int languageCount = 0;
				for (String apiProgramminglangauge : repos) {
					if (StringUtils.isNotEmpty(apiProgramminglangauge)
							&& apiProgramminglangauge.equalsIgnoreCase(progammingLanguage)) {
						languageCount++;
					}
				}
				LOGGER.info(progammingLanguage + "::::::::" + languageCount);
				repoLanguageMapOrrepoBasedOnNumberOfStars.put(progammingLanguage, languageCount);
			}
		} else {
			LOGGER.info("Please check the input value for programming langauge/username/githubAPIURL");
		}
		return repoLanguageMapOrrepoBasedOnNumberOfStars;
	}

	public void verifyUserReposProgrammingLanguagesDisplyedProperly() {
		assertTrue("There are no repos for the given programming language or input is invalid",
				!repoLanguageMapOrrepoBasedOnNumberOfStars.isEmpty());
	}

	public Map<String, String> listRepositoriesBasedOnCreationDate(String githubAPIURL, String githubUsername,
			String inputCreationDate, String timeZoneId, String pathOrQueryParameter)
			throws FileNotFoundException, IOException {
		Map<String, String> apiResponseOfCreatedDate = new HashMap<String, String>();
		try {
			if (StringUtils.isNotEmpty(inputCreationDate) && StringUtils.isNotEmpty(timeZoneId)) {
				response = utilities.getGitHubSearchApiResponse(githubAPIURL, githubUsername, pathOrQueryParameter);
				List<GithubApiResponseImplementation> myObjects = utilities
						.performMappingForGitHubAPIResponse(response);
				if (myObjects.size() >= 1) {
					myObjects.forEach(name -> {
						apiResponseOfCreatedDate.put(name.getCreatedAt(), name.getName());
					});
					apiResponseOfCreatedDate.forEach((apiResponseCreatedDate, projectName) -> {
						DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
						LocalDateTime inputDate = LocalDateTime.parse(
								utilities.getInputDateTimeIntoUTCZoneDateTime(inputCreationDate, timeZoneId),
								dateFormat);
						DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
						LocalDateTime repoCreatedDate = LocalDateTime.parse(apiResponseCreatedDate, format);
						if (repoCreatedDate.isAfter(inputDate) || inputDate.isEqual(repoCreatedDate)) {
							LOGGER.info(projectName + ":::::::" + apiResponseCreatedDate);
							repoBasedOnCreationDate.put(projectName, apiResponseCreatedDate);
						}
					});
				}
			} else {
				LOGGER.info("Please check the input value for inputCreationDate/timeZoneId");
			}
		} catch (Exception e) {
			LOGGER.info(
					"Input dateformat is invalid. Please enter input date format as yyyy-MM-dd HH:mm:ss Example: 2017-05-02 23:35:05 "
							+ "or There are no repos for the given created date in UTC time. Please provide correct created date input \t"
							+ e.getMessage());
		}
		return repoBasedOnCreationDate;
	}

	public void verifyUserReposDisplayedAfterTheCreationDate() {
		assertTrue("There are no repos for the given created date or input is invalid",
				!repoBasedOnCreationDate.isEmpty());
	}

	public Map<String, Integer> getStarredCountOfAUserRepositories(String githubAPIURL, String githubUsername,
			String pathOrQueryParameter) throws FileNotFoundException, IOException {
		Map<String, Integer> unsortedStarredReposMap = new HashMap<String, Integer>();
		response = utilities.getGitHubSearchApiResponse(githubAPIURL, githubUsername, pathOrQueryParameter);
		List<GithubApiResponseImplementation> myObjects = utilities.performMappingForGitHubAPIResponse(response);
		if (myObjects.size() >= 1) {
			myObjects.forEach(name -> {
				unsortedStarredReposMap.put(name.getName(), Integer.parseInt(name.getStargazersCount()));
			});
			Map<String, Integer> sortedMap = utilities.sortAMap(unsortedStarredReposMap);
			sortedMap.forEach((key, value) -> {
				LOGGER.info(key + ":" + value);
				repoLanguageMapOrrepoBasedOnNumberOfStars.put(key, value);
			});
		} else {
			LOGGER.info("There are no starred repositories present for the user:\t" + githubUsername);
		}
		return repoLanguageMapOrrepoBasedOnNumberOfStars;
	}

	public void verifyUserReposWithStarsDisplayed() {
		assertTrue("There are no starred repos for the given user or input is invalid",
				!repoLanguageMapOrrepoBasedOnNumberOfStars.isEmpty());
	}

	public List<String> getPageLimitReposForUser(String githubAPIURL, String githubUsername, String paginationLimit,
			String pathOrQueryParameter) throws FileNotFoundException, IOException {
		if (StringUtils.isNotEmpty(paginationLimit)) {
			String pathOrQueryParameterToApi = pathOrQueryParameter.concat(paginationLimit);
			repos = getUserRepositoriesNames(githubAPIURL, githubUsername, pathOrQueryParameterToApi);
		} else {
			LOGGER.info("There no repos for the given pagination or input is invalid");
		}
		return repos;
	}

	public void verifyUserReposDisplayedWithInputPagination() {
		assertTrue("There no repos for the given pagination or input is invalid", !repos.isEmpty());
	}

	public void requestAPIUntilRateLimitReached(String githubAPIURL, String githubUsername, String pathOrQueryParameter,
			int numberOfApiRequests) {
		try {
			for (int i = 0; i <= numberOfApiRequests; i++) {
				response = utilities.getAPIResponse(githubAPIURL.concat(pathOrQueryParameter).concat(githubUsername));
			}
		} catch (HttpStatusCodeException httpStatusCodeException) {
			LOGGER.info("Github API ratelimit is reached\t::" + httpStatusCodeException.getMessage());
			Assert.assertTrue("Failed to validate the API responsecode for Ratelimit",
					httpStatusCodeException.getStatusCode().value() == 404
							|| httpStatusCodeException.getStatusCode().value() == 403);
		}
	}
}
