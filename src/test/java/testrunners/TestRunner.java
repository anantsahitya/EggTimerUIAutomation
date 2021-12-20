package testrunners;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
    glue = {"src/test/java/stepdefinitions"},
    plugin = {"pretty", "html:target/Destination"},
    features={"src/test/resources/AppFeatures"},
    tags = { "@test1" }
    )
public enum TestRunner {

}
