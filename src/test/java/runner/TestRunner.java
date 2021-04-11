package runner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;



@RunWith(Cucumber.class)
@CucumberOptions(

		features 	= { "src/test/resources/features/Problem1.feature" }, 
		glue 		= { "stepdefinations" },
		//tags 		= { "" }, 
		dryRun 		= false, 
		strict 		= false,
		monochrome 	= true, 
		plugin 		= { "json:target/cucumber.json","html:target/Cucumber" })
public class TestRunner extends CommonObjects {

	private static Logger log = LoggerFactory.getLogger(TestRunner.class);

	@BeforeClass
	public static void setupSuite() {

		log.info("######################### Test suite execution started ######################");

		log.info("Loading data and field property files..");
		mData = loadPropertyFile(mData,"src/test/resources/data/Test_Data.properties");
		log.info("Setting execution environment and browser..");
		environment = System.getProperty("execution_environment");
		browserType = System.getProperty("execution_browser");
	
		// Below lines used for jUnit runner

		if (environment == null || browserType == null) {
			log.info("Setting execution environment and browser for junit run..");
			environment = "qa";
			browserType = "chrome";
		}
		log.info("Execution environment ::" + environment);
		log.info("Execution browser ::" + browserType);
	}

	@AfterClass
	public static void tearDownSuite() {

		try {
			switch (browserType) {
			case "chrome":
				log.info("Killing chrome driver task from run time..");
				Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
				break;
			case "ie":
				log.info("Killing IE driver task from run time..");
				Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
				break;
			}
		} catch (Exception e) {
			log.error("Exception while killing driver server task from run time");
		}

		log.info("######################### Test suite execution ended ######################");
	}

}
