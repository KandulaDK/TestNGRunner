package pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
	public WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "td p[class='product-name']")
	WebElement txt_itemName;
	
	@FindBy(css = "td p[class='quantity']")
	WebElement txt_quantity;
	
	@FindBy(css = "button[class='promoBtn']")
	WebElement btn_promo;
		
	@FindBy(xpath = "//button[.='Place Order']")
	WebElement btn_placeOrder;

	
	public String getProductName() {
		return (txt_itemName).getText();
	}
	public String getQuantity() {
		return (txt_quantity).getText();
	}
	
	public boolean promoBtn() {
		return btn_promo.isEnabled();
	}
	public boolean placeOrdBtn() {
		return btn_placeOrder.isEnabled();
	}
		
}
