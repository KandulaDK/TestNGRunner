package stepDefination;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import PicoContainer.DependencyInjection;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjectModel.LandingPgPOM;
import pageObjectModel.OffersPgPOM;
import pageObjectModel.PageObjectManager;

public class OffersPageStepDefination {
	public WebDriver driver;
	public String landingPgProduct;
	public String offerPgProduct;
	DependencyInjection dependencyInjection;
//	public SoftAssert a;
	public PageObjectManager pageObjectManager;

	public OffersPageStepDefination(DependencyInjection dependencyInjection) {
		this.dependencyInjection = dependencyInjection;
	}

	@Then("^User searched for same shortname (.+) in offers page$")
	public void user_searched_for_same_shortname_in_offers_page(String shortName) throws Throwable {
		
//		pageObjectManager = new PageObjectManager(dependencyInjection.driver);
		
		LandingPgPOM landingPg = dependencyInjection.pageObjectManager.getLandingPg(); //pageObjectManager.getLandingPg();
		OffersPgPOM offersPg = dependencyInjection.pageObjectManager.getOffersPg(); //pageObjectManager.getOffersPg();
		
		landingPg.goTOTopDealsPg();
//		landingPg.switchToOffersPage();
		dependencyInjection.genricUtils.switchWindowToChild();
		
		
		offersPg.searchItem(shortName);
		Thread.sleep(2000);
		dependencyInjection.offerPgProduct = offersPg.getTheProductName();
		
		System.out.println("Offers page product is: " + dependencyInjection.offerPgProduct);
	}
	

	@And("^check is both products are same or not$")
	public void check_is_both_products_are_same_or_not() throws Throwable {
//		dependencyInjection.a = new SoftAssert();
		Assert.assertEquals(dependencyInjection.offerPgProduct, dependencyInjection.landingPgProduct.split(" ")[0].trim());

	}

	

}
