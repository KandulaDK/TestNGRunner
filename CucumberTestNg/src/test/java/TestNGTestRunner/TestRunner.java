package TestNGTestRunner;

import org.testng.annotations.DataProvider;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/features",
		glue = "stepDefination",monochrome=true,dryRun=false,
		plugin = {"pretty",
				"html:target/TestNGcucumber.html",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", 
				"rerun:target/failed_scenarios.txt"},
		tags= "@SearchProducts")

//step notification was not supported in testNG
//for jUnit we have @RunWith(Cucumber.class)
//but in testNG we have to extend a class
//in TestNG we extends AbstractTestNGCucumberTests to trigger our feature files

public class TestRunner extends AbstractTestNGCucumberTests{
	
	@Override
	@DataProvider(parallel=true)
	public Object[][] scenarios(){
		return super.scenarios();
	}
}
