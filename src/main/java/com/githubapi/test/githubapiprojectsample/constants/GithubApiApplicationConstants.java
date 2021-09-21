package com.githubapi.test.githubapiprojectsample.constants;

public enum GithubApiApplicationConstants {

	GIT_HUB_REPOS("/repos"), GIT_HUB_PROGRMMINGLANGUAGE("/repos?q=language:"), GIT_HUB_STARRED_REPOS("/starred"),
	GIT_HUB_PAGINATION("/repos?per_page="), GIT_HUB_RATE_LIMIT("/search/repositories?q=user:");

	private final String constants;

	GithubApiApplicationConstants(final String constants) {
		this.constants = constants;
	}

	public String getValue() {
		return constants;
	}

}
