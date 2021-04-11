package runner;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.Scenario;


public class CommonObjects {
	public static Scenario scenario;
	public static WebDriverWait wait;
	public static Properties mData;
	// Run Parameters
	public static String environment;
	public static String browserType;
	public static Properties loadPropertyFile(Properties pObjectName, String pfileName) {

		File lFile = new File(pfileName);

		FileInputStream lFileInput = null;
		try {
			lFileInput = new FileInputStream(lFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		pObjectName = new Properties();

		// load properties file
		try {
			pObjectName.load(lFileInput);
		} catch (IOException e) {
			//log.error("Unable to Load Data");
			fail("Unable to Load Data " + e);
			
		}finally {
			if (lFileInput != null) {
				try {
					lFileInput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		
		return pObjectName;
	}
}
