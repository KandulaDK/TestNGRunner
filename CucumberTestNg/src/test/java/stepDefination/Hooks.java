package stepDefination;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import dependencyInjection.DependencyInjection;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	DependencyInjection dependencyInjection;
	WebDriver driver;

	public Hooks(DependencyInjection dependencyInjection) throws IOException {
		this.dependencyInjection = dependencyInjection;
		this.driver = dependencyInjection.WebDriverManger();
	}

	@Before
	public void beforeValidation() {
		System.out.println("      Before Validation done");
	}

	@After
	public void afterValidation() throws IOException {
		System.out.println("      After Validation done");
		driver.close();
		driver.quit();
	}

	@AfterStep
	public void AddScreenShot(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			byte[] fileContent = FileUtils.readFileToByteArray(file);
			scenario.attach(fileContent, "image/png", "image");
		}
	}
}
