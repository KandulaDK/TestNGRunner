package dependencyInjection;

import java.io.IOException;
import java.util.HashMap;

import driver.TestBase;
import functionalLibrary.GenricUtils;
import pageObjectModel.PageObjectManager;

public class DependencyInjection extends TestBase{
 	public GenricUtils genricUtils;
	public PageObjectManager pageObjectManager;
	public HashMap<String,String> testData = new HashMap<String,String>();
	
 	public DependencyInjection() throws IOException {
 		pageObjectManager = new PageObjectManager(WebDriverManger());
 		genricUtils = new GenricUtils(WebDriverManger());
 	}
}
