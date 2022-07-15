package stepDefination;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import dependencyInjection.DependencyInjection;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjectModel.LandingPgPOM;


public class LandingPageStepDefination {
	public WebDriver driver;
	public String landingPgProduct;
	public String offerPgProduct;
	DependencyInjection dependencyInjection;

	public LandingPageStepDefination(DependencyInjection dependencyInjection) {
		this.dependencyInjection = dependencyInjection;
	}

	@Given("User is on GreenCart Landing Page")
	public void user_is_on_greencart_landing_page() throws Throwable {
//		WebDriverManager.chromedriver().setup();
//		dependencyInjection.driver = new ChromeDriver();
//		dependencyInjection.driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");

	}

	@When("^User Searched with shortName (.+) and Extracted resultes of the product$")
	public void user_searched_with_shortname_and_extracted_resultes_of_the_product(String shortName) throws Throwable {
		LandingPgPOM landingPg = dependencyInjection.pageObjectManager.getLandingPg(); // new
																						// LandingPgPOM(dependencyInjection.driver);

		landingPg.searchItem(shortName);
		Thread.sleep(2000);
		dependencyInjection.landingPgProduct = landingPg.getTheProductName();
		System.out.println("Found This Product in landing page: " + dependencyInjection.landingPgProduct);
	}
	
	 @And("^add \"([^\"]*)\" items of the searched product to cart$")
	    public void add_something_items_of_the_searched_product_to_cart(String strArg1) throws Throwable {
		 dependencyInjection.pageObjectManager.getLandingPg().increaseTheQuantity(strArg1);
		 dependencyInjection.pageObjectManager.getLandingPg().addToCart();
	    }
	 @Then("^User proceeds to checkout$")
	    public void user_proceeds_to_checkout() throws Throwable {
	        dependencyInjection.pageObjectManager.getLandingPg().proceedToCheckOut();
	    }

}
