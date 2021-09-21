package com.githubapi.test.githubapiprojectsample.service.factory;

import com.githubapi.test.githubapiprojectsample.service.GithubApiData;
import com.githubapi.test.githubapiprojectsample.service.impl.GithubApiDataImplementation;

public class GithubApiDataFactory {
	public static GithubApiData createGithubApiDataFactory(GithubApiServiceType type, String userName,
			String programmingLangauges, String givenDate, String maxPagination) {
		GithubApiData githubApiData = null;
		switch (type) {
		case GET_GITHUB_API_RESPONSE:
			githubApiData = new GithubApiDataImplementation(userName, programmingLangauges, givenDate, maxPagination);
		}
		return githubApiData;
	}
}
