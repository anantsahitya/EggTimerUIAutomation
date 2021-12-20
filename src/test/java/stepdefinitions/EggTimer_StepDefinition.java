package stepdefinitions;

import java.util.Properties;
import org.testng.asserts.SoftAssert;
import com.factory.DriverFactory;
import com.pages.HomePage;
import com.pages.TimePage;
import com.util.ConfigReader;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class EggTimer_StepDefinition {
	private String time;
	private TimePage timePage;
	private HomePage homePage = new HomePage(DriverFactory.getDriver());
	SoftAssert softAssert = new SoftAssert();

	@Given("^Open the Egg Timer App$")
	public void open_Egg_Timer_App() {
		ConfigReader cr = new ConfigReader();
		Properties prop = cr.init_prop();
		DriverFactory.getDriver().get(prop.getProperty("url"));
	}

	@Given("^User enter the time \"([^\"]*)\"$")
	public void user_enter_the_time(String time) throws Throwable {
		this.time = time;
		homePage.enterTime(time);
	}

	@When("^Click on start button$")
	public void click_on_start_button() throws Throwable {
		timePage = homePage.clickStartButton();
	}

	@Then("^User navigates to Timer page$")
	public void user_navigates_to_Timer_page() throws Throwable {
		String title = "00";
		System.out.println("Title: " + timePage.getPageTitle());
		boolean status = timePage.getPageTitle().contains(title);
		Assert.assertTrue(status);
	}

	@Then("^Verify that time is decreasing per second$")
	public void verify_that_time_is_decreasing_per_second() throws Throwable {
		while (timePage.alertExceptionNotOccurs()) {
			int currentTimeSeconds = timePage.getSeconds();
			int currentTimeMinutes = timePage.getMinutes();
			if (currentTimeSeconds == 0 && currentTimeMinutes != 0) {
				long start = System.nanoTime();
				while (start + 1000000000 >= System.nanoTime())
					;
				softAssert.assertEquals(59, timePage.getSeconds());
				softAssert.assertEquals(currentTimeMinutes - 1, timePage.getMinutes());
			} else {
				long start = System.nanoTime();
				while (start + 1000000000 >= System.nanoTime())
					;
				softAssert.assertEquals(currentTimeSeconds - 1, timePage.getSeconds());
				softAssert.assertEquals(currentTimeMinutes, timePage.getMinutes());
			}
			softAssert.assertAll();
		}
	}
}
