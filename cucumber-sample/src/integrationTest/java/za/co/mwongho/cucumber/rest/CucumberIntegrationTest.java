package za.co.mwongho.cucumber.rest;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/integrationTest/resources/features",
		glue = {"za.co.mwongho.cucumber.rest.steps"},
		plugin = { "pretty", "html:build/reports/cucumber", "junit:build/reports/junit-report.xml" }
		)
public class CucumberIntegrationTest {

}
