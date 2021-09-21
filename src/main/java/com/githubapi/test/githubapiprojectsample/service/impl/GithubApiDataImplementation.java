package com.githubapi.test.githubapiprojectsample.service.impl;

import com.githubapi.test.githubapiprojectsample.service.GithubApiData;

public class GithubApiDataImplementation implements GithubApiData {
	private String userName;
	private String programmingLangauges;
	private String givenDate;
	private String maxPagination;

	public GithubApiDataImplementation(String userName, String programmingLangauges, String givenDate,
			String maxPagination) {
		this.userName = userName;
		this.programmingLangauges = programmingLangauges;
		this.givenDate = givenDate;
		this.maxPagination = maxPagination;
	}

	@Override
	public String getUserName() {
		return this.userName;
	}

	@Override
	public String getProgrammingLanguages() {
		return this.programmingLangauges;
	}

	@Override
	public String getGivenDate() {
		return this.givenDate;
	}

	@Override
	public String getMaxPagination() {
		return this.maxPagination;
	}
}
