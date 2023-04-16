package functionalLibrary;

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
		a.next();
		String child = a.next();
		driver.switchTo().window(child);
	}

	public void switchWindowToSecondChild() {
		Set<String> activeWindows = driver.getWindowHandles();
		Iterator<String> a = activeWindows.iterator();
		a.next();
		a.next();
		String child = a.next();
		driver.switchTo().window(child);
	}
	
	public void acceptTheAlert() {
		driver.switchTo().alert().accept();
	}

	public void dismissTheAlert() {
		driver.switchTo().alert().dismiss();
	}
	
	public void getTextFromAlert() {
		driver.switchTo().alert().getText();
	}
	
}
