package stepDefination;

import dependencyInjection.DependencyInjection;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjectModel.LandingPage;

public class LandingPageStepDefination {
	DependencyInjection dependencyInjection;
	LandingPage landingPage;

	public LandingPageStepDefination(DependencyInjection dependencyInjection) {
		this.dependencyInjection = dependencyInjection;
		this.landingPage = dependencyInjection.pageObjectManager.getLandingPage();
	}

	@Given("User is on GreenCart Landing Page")
	public void user_is_on_greencart_landing_page() throws Throwable {
		System.out.println("User is on GreenCart Landing Page");
	}

	@When("^User Searched with shortName (.+) and Extracted resultes of the product$")
	public void user_searched_with_shortname_and_extracted_resultes_of_the_product(String shortName) throws Throwable {
		landingPage.searchItem(shortName);
		Thread.sleep(2000);
		dependencyInjection.testData.put("landingPageProdcut", landingPage.getTheProductName());
		System.out.println("Found This Product in landing page: " + dependencyInjection.testData.get("landingPageProdcut"));
	}

	@And("^add \"([^\"]*)\" items of the searched product to cart$")
	public void add_something_items_of_the_searched_product_to_cart(String qty) throws Throwable {
		landingPage.increaseTheQuantity(qty);
		landingPage.addToCart();
	}

	@Then("^User proceeds to checkout$")
	public void user_proceeds_to_checkout() throws Throwable {
		landingPage.proceedToCheckOut();
	}

}
