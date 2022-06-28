package stepDefination;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class StepDefination {
	@Given("User is on Insta login page")
	public void user_is_on_insta_login_page() {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Login Success");
		
	}

	@When("User enters username {string} and password {string}")
	public void user_enters_username_and_password(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
		System.out.println("userName " + string);
		System.out.println("Password " + string2);
	}

	@Then("home page should be displayed")
	public void home_page_should_be_displayed() {
	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
		System.out.println("ok Home page displayed");
	}

	

	@Then("prints the tittle of the page")
	public void prints_the_tittle_of_the_page() {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Tittle is printed");
	}
	
	@Given("^I want to write a step with (.+)$")
    public void i_want_to_write_a_step_with(String name) throws Throwable {
//        throw new PendingException();
		System.out.println(name);
    }

    @When("^I check for the (.+) in step$")
    public void i_check_for_the_in_step(String value) throws Throwable {
//        throw new PendingException();
    	System.out.println(value);
    }
    
    @Then("^I verify the in step (.+)$")
    public void i_verify_the_in_step(String status) throws Throwable {
//        throw new PendingException();
    	System.out.println("Secanrio outline " + status);
    }
}
