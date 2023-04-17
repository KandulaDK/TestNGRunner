package stepDefination;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import dependencyInjection.DependencyInjection;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;

public class Hooks {
	private static Logger log = (Logger) LogManager.getLogger(Hooks.class.getName());
	DependencyInjection dependencyInjection;
	WebDriver driver;

	public Hooks(DependencyInjection dependencyInjection) throws IOException {
		this.dependencyInjection = dependencyInjection;
		this.driver = dependencyInjection.WebDriverManger();
	}
	
	@BeforeStep
	public void beforeStepValidation() {
	}

	@Before
	public void beforeValidation(Scenario scenario) {
		log.info("      Before Validation done");
	}

	@After
	public void afterValidation() throws IOException {
		log.info("      After Validation done");
		driver.close();
		driver.quit();
	}

	@AfterStep
	public void afterStepValidation(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			byte[] fileContent = FileUtils.readFileToByteArray(file);
			scenario.attach(fileContent, "image/png", "image");
		}
	}
}
