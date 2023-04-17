package stepDefination;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class StepDefination {
	private static Logger log = (Logger) LogManager.getLogger(StepDefination.class.getName());
	
	@Given("User is on Insta login page")
	public void user_is_on_insta_login_page() {
		log.info("Login Success");		
	}
	
	@When("User enters username {string} and password {string}")
	public void user_enters_username_and_password(String string, String string2) {
		log.info("userName " + string);
		log.info("Password " + string2);
	}

	@Then("home page should be displayed")
	public void home_page_should_be_displayed() {
		log.info("ok Home page displayed");
	}	

	@Then("prints the tittle of the page")
	public void prints_the_tittle_of_the_page() {
		log.info("Tittle is printed");
	}
	
	@Given("^I want to write a step with (.+)$")
    public void i_want_to_write_a_step_with(String name) throws Throwable {
		log.info(name);
    }

	@And("user click login")
	public void user_click_login() {
		log.info("Clicked on Login Button");
	}
	
	
    @When("^I check for the (.+) in step$")
    public void i_check_for_the_in_step(String value) throws Throwable {
    	log.info(value);
    }
    
    @Then("^I verify the in step (.+)$")
    public void i_verify_the_in_step(String status) throws Throwable {
    	log.info("Secanrio outline " + status);
    }
}
