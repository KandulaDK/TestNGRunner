package dependencyInjection;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import pageObjectModel.PageObjectManager;

public class DependencyInjection {
	public WebDriver driver;
 	public String landingPgProduct;
 	public String offerPgProduct;
	public TestBase testBase;
	public GenricUtils genricUtils;
	public PageObjectManager pageObjectManager;
//	public SoftAssert a;
	
	
 	public DependencyInjection() throws IOException {
 		testBase = new TestBase();
 		pageObjectManager = new PageObjectManager(testBase.WebDriverManger());
 		genricUtils = new GenricUtils(testBase.WebDriverManger());
 	}
}
