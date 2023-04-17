package stepDefination;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import dependencyInjection.DependencyInjection;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjectModel.LandingPage;

public class LandingPageStepDefination {
	private static Logger log = (Logger) LogManager.getLogger(LandingPageStepDefination.class.getName());
	DependencyInjection dependencyInjection;
	LandingPage landingPage;
	
	public LandingPageStepDefination(DependencyInjection dependencyInjection) {
		this.dependencyInjection = dependencyInjection;
		this.landingPage = dependencyInjection.pageObjectManager.getLandingPage();
	}

	@Given("User is on GreenCart Landing Page")
	public void user_is_on_greencart_landing_page() throws Throwable {
		log.info("User is on GreenCart Landing Page");
	}

	@When("^User Searched with shortName (.+) and Extracted resultes of the product$")
	public void user_searched_with_shortname_and_extracted_resultes_of_the_product(String shortName) throws Throwable {
		landingPage.searchItem(shortName);
		Thread.sleep(2000);
		dependencyInjection.testData.put("landingPageProdcut", landingPage.getTheProductName());
		log.info("Found This Product in landing page: " + dependencyInjection.testData.get("landingPageProdcut"));
	}

	@And("^add \"([^\"]*)\" items of the searched product to cart$")
	public void add_something_items_of_the_searched_product_to_cart(String qty) throws Throwable {
		landingPage.increaseTheQuantity(qty);
		log.info("Quantity was increased to : " + qty);
		landingPage.addToCart();
	}

	@Then("^User proceeds to checkout$")
	public void user_proceeds_to_checkout() throws Throwable {
		landingPage.proceedToCheckOut();
	}

}
