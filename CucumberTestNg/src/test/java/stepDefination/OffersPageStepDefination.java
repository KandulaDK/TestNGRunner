package stepDefination;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.testng.Assert;

import dependencyInjection.DependencyInjection;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjectModel.LandingPage;
import pageObjectModel.OffersPage;

public class OffersPageStepDefination {
	private static Logger log = (Logger) LogManager.getLogger(OffersPageStepDefination.class.getName());
	DependencyInjection dependencyInjection;
	OffersPage offersPage;
	LandingPage landingPage;
	
	public OffersPageStepDefination(DependencyInjection dependencyInjection) {
		this.dependencyInjection = dependencyInjection;
		this.offersPage = dependencyInjection.pageObjectManager.getOffersPage();
		this.landingPage = dependencyInjection.pageObjectManager.getLandingPage();
	}

	@Then("^User searched for same shortname (.+) in offers page$")
	public void user_searched_for_same_shortname_in_offers_page(String shortName) throws Throwable {
		landingPage.goTOTopDealsPg();
		dependencyInjection.genricUtils.switchWindowToChild();
		offersPage.searchItem(shortName);
		Thread.sleep(2000);
		dependencyInjection.testData.put("offersPageProduct", offersPage.getTheProductName());
		log.info("Offers page product is: " + dependencyInjection.testData.get("offersPageProduct"));
	}
	

	@And("^check is both products are same or not$")
	public void check_is_both_products_are_same_or_not() throws Throwable {
		Assert.assertEquals(dependencyInjection.testData.get("offersPageProduct"), dependencyInjection.testData.get("landingPageProdcut").split("-")[0].trim());

	}

	

}
