package stepDefination;

import org.testng.Assert;

import dependencyInjection.DependencyInjection;
import io.cucumber.java.en.Then;
import pageObjectModel.CheckoutPage;

public class CheckoutPageStepDefination {
	DependencyInjection dependencyInjection;
	CheckoutPage checkoutPage;
	
	public CheckoutPageStepDefination(DependencyInjection dependencyInjection) {
		this.dependencyInjection = dependencyInjection;
	    this.checkoutPage = dependencyInjection.pageObjectManager.getCheckoutPG();
	}
	
	
	@Then ("validate the items in the checkout page")
	public void validate_the_items_in_the_checkout_page() throws InterruptedException {
		Thread.sleep(5000);
		String itemName = checkoutPage.getProductName();
		String landingPgproduct = dependencyInjection.testData.get("landingPageProdcut");
		Assert.assertEquals(landingPgproduct, itemName);
	}
	
	@Then ("verfiy user has ability enter promo code and place the order")
	public void verfiy_user_has_ability_enter_promo_code_place_the_order() {
		boolean promo = checkoutPage.promoBtn();
		Assert.assertTrue(promo);
		boolean placeord = checkoutPage.placeOrdBtn();
		Assert.assertTrue(placeord);
	}
}
