package com.githubapi.test.githubapiprojectsample.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Owner {
	String login;
    String id;
    String node_id;
    String avatar_url;
    String gravatar_id;
    String url;
    String html_url;
    String followers_url;
    String following_url;
    String  gists_url;
    String starred_url;
    String subscriptions_url;
    String organizations_url;
    String repos_url;
    String  events_url;
    String  received_events_url;
    String type;
    @JsonProperty(value = "site_admin")
    private boolean site_admin;
}
