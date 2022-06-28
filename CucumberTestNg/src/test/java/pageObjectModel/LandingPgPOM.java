package pageObjectModel;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPgPOM {
	public WebDriver driver;
	
	private By searchBox =  By.cssSelector("input[type='search']");
	private By productName = By.xpath("//h4[@class='product-name']");
	private By topDeals = By.partialLinkText("Top Deals");
	private By quantity = By.cssSelector("input[class='quantity']");
	private By increment = By.cssSelector("a.increment");
	private By addToCart = By.xpath("//button[.='ADD TO CART']");
	private By bag = By.cssSelector("[alt='Cart']");
	private By checkOut = By.xpath("//button[.='PROCEED TO CHECKOUT']");
	
	
	public  LandingPgPOM(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public void searchItem(String shortName) {
		driver.findElement(searchBox).sendKeys(shortName);
		
	}
	
	public String getTheProductName() {
		
		return driver.findElement(productName).getText();
	}
	
	public void goTOTopDealsPg() {
		driver.findElement(topDeals).click();
	}
	
	public String increaseTheQuantity(String q) {
		
		while(!driver.findElement(quantity).getAttribute("value").contains(q)){
			driver.findElement(increment).click();
		}
		return driver.findElement(quantity).getAttribute("value");
		
	}
	
	public void addToCart() {
		driver.findElement(addToCart).click();
	}
	
	public void proceedToCheckOut() {
		driver.findElement(bag).click();
		driver.findElement(checkOut).click();
	}
	
	
	
	
	
	
	
	
}
