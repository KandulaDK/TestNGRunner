package PicoContainer;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import pageObjectModel.PageObjectManager;

public class DependencyInjection {
	public WebDriver driver;
 	public String landingPgProduct;
 	public String offerPgProduct;
	public PageObjectManager pageObjectManager;
	public TestBase testBase;
	public GenricUtils genricUtils;
//	public SoftAssert a;
	
 	public DependencyInjection() throws IOException {
 		testBase = new TestBase();
 		pageObjectManager = new PageObjectManager(testBase.WebDriverManger());
 		genricUtils = new GenricUtils(testBase.WebDriverManger());
 	}
}
