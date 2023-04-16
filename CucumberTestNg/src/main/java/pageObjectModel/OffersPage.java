package pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OffersPage {
	public WebDriver driver;
	
	public OffersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "search-field")
	WebElement inp_searchItem;
	
	@FindBy(css = "tr td:nth-child(1)")
	WebElement txt_productName;
	
	
	public void searchItem(String shortName) {
		inp_searchItem.sendKeys(shortName);
	}
	
	public String getTheProductName() {
		return txt_productName.getText();
	}
	
}
