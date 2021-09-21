package com.githubapi.test;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "json:target/cucumber.json",
		"html:target/cucumber-reports" }, features = "classpath:features", tags = "@EndToEndTest1")
public class CucumberRunnerTest {
}
