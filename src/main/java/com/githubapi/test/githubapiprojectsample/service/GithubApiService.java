package com.githubapi.test.githubapiprojectsample.service;

import org.springframework.http.ResponseEntity;

public interface GithubApiService {
	public <T> ResponseEntity<T> getGitHubSearchApiResponse(String githubAPIURL, String githubUsername,
			String pathOrQueryParameter);
}
