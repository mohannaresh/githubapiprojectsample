package com.githubapi.test.githubapiprojectsample.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubApiResponseImplementation {
	private String id;
	@JsonProperty(value = "node_id")
	private String nodeId;
	private String name;
	@JsonProperty(value = "full_name")
	private String fullName;
	@JsonProperty(value = "private")
	private boolean privateIndicator;
	private Owner owner;
	@JsonProperty(value = "created_at")
	private String createdAt;
	private String language;
	@JsonProperty(value = "stargazers_count")
	private String stargazersCount;

	public String getName() {
		return name;
	}

	public String getProgrammingLanguage() {
		return language;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public String getStargazersCount() {
		return stargazersCount;
	}
}
