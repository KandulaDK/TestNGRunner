package pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	public WebDriver driver;
	
	public  LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}	
	
	@FindBy(css = "input[type='search']")
	WebElement inp_searchBox;
	
	@FindBy(xpath = "//h4[@class='product-name']")
	WebElement txt_productName;
	
	@FindBy(partialLinkText = "Top Deals")
	WebElement lnk_topDeals;

	@FindBy(css = "input[class='quantity']")
	WebElement inp_quantity;

	@FindBy(css = "a.increment")
	WebElement btn_increment;
	
	@FindBy(xpath = "//button[.='ADD TO CART']")
	WebElement btn_addToCart;
	
	@FindBy(css = "[alt='Cart']")
	WebElement bag;
	
	@FindBy(xpath = "//button[.='PROCEED TO CHECKOUT']")
	WebElement btn_checkout;
	
	public void searchItem(String shortName) {
		inp_searchBox.sendKeys(shortName);
	}

	public String getTheProductName() {
		return txt_productName.getText();
	}

	public void goTOTopDealsPg() {
		lnk_topDeals.click();
	}

	public String increaseTheQuantity(String qty) {
		while(!inp_quantity.getAttribute("value").contains(qty)){
			btn_increment.click();
		}
		return inp_quantity.getAttribute("value");
	}

	public void addToCart() {
		btn_addToCart.click();
	}

	public void proceedToCheckOut() {
		bag.click();
		btn_checkout.click();
	}

}
