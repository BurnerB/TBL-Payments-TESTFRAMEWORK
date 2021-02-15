


package runner;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src\\test\\resources\\FeatureFiles",
		glue = "StepDefinitions" ,
		tags = "@trial2",
		dryRun = false,    //checks whether each feature has a mapped step definition
		monochrome = true,// neat output after tc run
		plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","json:target/positive/cucumber.json", "pretty", "html:target/positive/cucumber.html"}
		)


public class TestRunner{

}

	