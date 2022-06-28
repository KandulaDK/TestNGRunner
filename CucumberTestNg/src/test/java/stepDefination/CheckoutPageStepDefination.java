package stepDefination;

import org.testng.Assert;

import PicoContainer.DependencyInjection;
import io.cucumber.java.en.Then;
import pageObjectModel.CheckOutPgPOM;

public class CheckoutPageStepDefination {
	DependencyInjection dependencyInjection;
	CheckOutPgPOM checkoutPg;
	
	public CheckoutPageStepDefination(DependencyInjection dependencyInjection) {
		this.dependencyInjection = dependencyInjection;
	    this.checkoutPg = dependencyInjection.pageObjectManager.getCheckoutPG();
	}
	
	@Then ("validate the items in the checkout page")
	public void validate_the_items_in_the_checkout_page() throws InterruptedException {
		Thread.sleep(5000);
		String itemName = checkoutPg.getProductName();
		String landingPgproduct = dependencyInjection.landingPgProduct;
		Assert.assertEquals(landingPgproduct, itemName);
	}
	
	@Then ("verfiy user has ability enter promo code and place the order")
	public void verfiy_user_has_ability_enter_promo_code_place_the_order() {
		boolean promo = checkoutPg.promoBtn();
		Assert.assertTrue(promo);
		boolean placeord = checkoutPg.placeOrdBtn();
		Assert.assertTrue(placeord);
	}
}
