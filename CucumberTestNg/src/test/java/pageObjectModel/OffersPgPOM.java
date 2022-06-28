package pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OffersPgPOM {
	public WebDriver driver;
	
	public OffersPgPOM(WebDriver driver) {
		this.driver = driver;
	}
	
	private By searchItem = By.id("search-field");
	private By productName = By.cssSelector("tr td:nth-child(1)");
	
	public void searchItem(String shortName) {
		driver.findElement(searchItem).sendKeys(shortName);
	}
	
	public String getTheProductName() {
		return driver.findElement(productName).getText();
	}
	
	
}
