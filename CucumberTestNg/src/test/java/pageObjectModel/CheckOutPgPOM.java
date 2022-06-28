package pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CheckOutPgPOM {
	public WebDriver driver;
	
	public CheckOutPgPOM(WebDriver driver) {
		this.driver = driver;
	}
	
	private By itemName = By.cssSelector("td p[class='product-name']");
	private By quantity = By.cssSelector("td p[class='quantity']");
	private By promoButton = By.cssSelector("button[class='promoBtn']");
	private By placeOrderBtn = By.xpath("//button[.='Place Order']");
	
	
	public String getProductName() {
		return	driver.findElement(itemName).getText();
	}
	public String getQuantity() {
		return driver.findElement(quantity).getText();
	}
	
	public boolean promoBtn() {
		return driver.findElement(By.cssSelector("button[class='promoBtn']")).isEnabled();
	}
	public boolean placeOrdBtn() {
		return driver.findElement(By.xpath("//button[.='Place Order']")).isEnabled();
	}
	
	
//	Assert.assertEquals(driver.findElement(By.cssSelector("td p[class='product-name']")).getText(), prodcutName);
//	Assert.assertEquals(driver.findElement(By.cssSelector("td p[class='quantity']")).getText(), "3");
//	System.out.println(driver.findElement(By.cssSelector("button[class='promoBtn']")).isEnabled());
//	System.out.println(driver.findElement(By.xpath("//button[.='Place Order']")).isEnabled());

	
	
}
