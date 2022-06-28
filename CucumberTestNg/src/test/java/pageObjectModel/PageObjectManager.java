package pageObjectModel;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
	public WebDriver driver;
	public LandingPgPOM landingPg;
	public OffersPgPOM offersPg;
	public CheckOutPgPOM checkOutPg;
	
	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}
	
	public LandingPgPOM getLandingPg() {
		landingPg = new LandingPgPOM(driver);
		return landingPg;
	}
	
	public OffersPgPOM getOffersPg() {
		offersPg = new OffersPgPOM(driver);
		return offersPg;
	}
	
	public CheckOutPgPOM getCheckoutPG() {
		checkOutPg = new CheckOutPgPOM(driver);
		return checkOutPg;
	}
}
