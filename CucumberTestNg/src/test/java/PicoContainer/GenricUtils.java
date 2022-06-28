package PicoContainer;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;

public class GenricUtils {
	public WebDriver driver;
	
	public GenricUtils(WebDriver driver) {
		this.driver = driver;
	}
	
	public void switchWindowToChild() {
		Set<String> activeWindows = driver.getWindowHandles();
		Iterator<String> a = activeWindows.iterator();
		String parent = a.next();
		String child = a.next();
		driver.switchTo().window(child);
	}
	
}
