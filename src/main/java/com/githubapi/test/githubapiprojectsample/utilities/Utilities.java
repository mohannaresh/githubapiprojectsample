package com.githubapi.test.githubapiprojectsample.utilities;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.githubapi.test.githubapiprojectsample.service.impl.GithubApiResponseImplementation;
import com.githubapi.test.githubapiprojectsample.service.impl.GithubApiServiceImplementation;

@Component
public class Utilities extends GithubApiServiceImplementation {

	@Autowired
	private RestTemplate restTemplate;
	private final Logger LOGGER = LoggerFactory.getLogger(GithubApiServiceImplementation.class);

	public boolean isUserHasGithubAccount(String githubURL, String githubUserName) {
		boolean isAccountExists = false;
		try {
			restTemplate.getForEntity(githubURL + "/users/" + githubUserName, String.class);
			isAccountExists = true;
		} catch (Exception e) {
			Assert.assertEquals(e.getMessage().substring(0, 3), "404");
			LOGGER.info("Specified user doesn't have a github account. Please check\t" + e.getMessage());
		}
		return isAccountExists;
	}

	public String getInputDateTimeIntoUTCZoneDateTime(String inputDateTime, String zoneId)
			throws DateTimeParseException {
		ZonedDateTime zonedInputDateTime = ZonedDateTime.parse(inputDateTime,
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of(zoneId)));
		ZonedDateTime utcDateTime = zonedInputDateTime.withZoneSameInstant(ZoneId.of("UTC"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
		return formatter.format(utcDateTime);
	}

	public Map<String, Integer> sortAMap(Map<String, Integer> unsortedStarredReposMap) {
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		unsortedStarredReposMap.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
				.forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
		return sortedMap;
	}

	@Override
	public ResponseEntity<String> getGitHubSearchApiResponse(String githubAPIURL, String githubUsername,
			String pathOrQueryParameter) {
		ResponseEntity<String> response = null;
		if (StringUtils.isNotEmpty(githubUsername) && StringUtils.isNotEmpty(githubAPIURL)
				&& isUserHasGithubAccount(githubAPIURL, githubUsername)) {
			response = restTemplate.getForEntity(githubAPIURL + "/users/" + githubUsername.concat(pathOrQueryParameter),
					String.class);
		} else {
			LOGGER.info(
					"Please check one of your githubAPIURL or username is invalid or user doesn't have a Github account");
		}
		return response;
	}

	public List<GithubApiResponseImplementation> performMappingForGitHubAPIResponse(ResponseEntity<String> response)
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		List<GithubApiResponseImplementation> myObjects = objectMapper.readValue(response.getBody().toString(),
				new TypeReference<List<GithubApiResponseImplementation>>() {
				});
		return myObjects;
	}

	public ResponseEntity<String> getAPIResponse(String URL) {
		ResponseEntity<String> response = null;
		response = restTemplate.getForEntity(URL, String.class);
		return response;
	}
}
