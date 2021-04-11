package stepdefinations;

import java.text.ParseException;
import java.util.Collection;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.Base;
import prop.LoadProperties;
import runner.CommonObjects;

public class BasicStepDefs extends CommonObjects {
	public LoadProperties data;
	public Base Basepage;
	public WebDriver driver;

	private static Logger log = LoggerFactory.getLogger(BasicStepDefs.class);

	@Before
	public void start(Scenario testScenario) {
		// Basepage = new Base();
		data = new LoadProperties();
		scenario = testScenario;
		log.info("*************** Test Scenario Start ***************");
		log.info("Scenario Name:: " + scenario.getName());
		Collection<String> tags = scenario.getSourceTagNames();
		for (String tag : tags) {
			log.info("Scenario Tags:: " + tag);
		}



		switch (browserType) {
		case "firefox":
			log.info("Opening firefox browser");
			break;
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("no-sandbox");
			chromeOptions.addArguments("--start-maximized");
			chromeOptions.addArguments("disable-infobars");
			log.info("Opening chrome browser");
			driver = new ChromeDriver(chromeOptions);

			break;
		case "ie":
			log.info("Opening ie browser");
			break;
		default:
			log.info("Opening default firefox browser");
			driver = new FirefoxDriver();
		}
		wait = new WebDriverWait(driver, 10);

	}

	@After
	public void end() throws Throwable {

		log.info("############ Scenario Status :: " + scenario.getStatus().toUpperCase() + " ############");
		if (scenario.isFailed()) {

			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/jpeg");
		}

		try {

			log.info("Quitting browser");
			driver.quit();
		} catch (Exception e) {

			log.error("Exception while quiting browser");
		}
		log.info("*************** Test Scenario End ***************");
	}

	@Given("^I am on the Application UnderTest$")
	public void i_am_on_the_Application_UnderTest() throws Throwable {
		Basepage = new Base(driver);
		Basepage.openApplication();

	}

	@When("^I verify the count of values appear on the screen$")
	public void i_verify_the_count_of_values_appear_on_the_screen() {
		org.junit.Assert.assertEquals(Basepage.verifyLableFiledsAndValueFields(), true);
		org.junit.Assert.assertEquals(Basepage.getValueFieldCount(), 6);
		org.junit.Assert.assertEquals(Basepage.getValueFieldCount(), 6);
	}

	@Then("^I verify the values on the screen are greater than zero$")
	public void i_verify_the_values_on_the_screen_are_greater_than_zero() {
		org.junit.Assert.assertEquals("Value is zero or less", true, Basepage.checkValueFieldsNotZero());
	}

	@Then("^I verify the values are formatted as currencies$")
	public void i_verify_the_values_are_formatted_as_currencies() {
		try {
			Basepage.checkValuesWithFormatedCurrencies();
		} catch (ParseException e) {

			log.error("Error while converting currency value" + e.getMessage());
			e.printStackTrace();
			org.junit.Assert.fail("Value is not with proper formatted currency" + e.getMessage());

		}
	}

	@Then("^I verify the total balance is matching with sum of the values on the screen$")
	public void i_verify_the_total_balance_is_matching_with_sum_of_the_values_on_the_screen() {
		org.junit.Assert.assertEquals("Total Balance valrification failed.", Basepage.getSumofValueFields(),
				Basepage.getTotalBalance());
	}

}
