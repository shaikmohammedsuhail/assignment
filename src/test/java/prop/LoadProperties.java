package prop;

import static org.junit.Assert.assertFalse;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadProperties {

	private static Logger log = LoggerFactory.getLogger(LoadProperties.class);
	public static final String TEST_DATA_FILE_NAME = "src/test/resources/data/Test_Data.properties";
	public static Properties testData;

	/**
	 * This method loads property file
	 * 
	 * @param pFileName
	 *            -> File name of property file
	 * @return -> Key value pair of property file
	 */
	public static Properties loadPropFile(String pFileName) {

		Properties lProperty = null;
		InputStream input = null;
		try {

			input = new FileInputStream(pFileName);

			// load prop file
			lProperty = new Properties();
			lProperty.load(input);

		} catch (IOException e) {
			log.error("Exception while loading prop file [" + pFileName
					+ "]");
			log.error(e.getMessage());
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					log.error("Exception while closing prop file ["
							+ pFileName + "]");
					log.error(e.getMessage());
				}
			}
		}
		return lProperty;

	}

	
	/**
	 * This method is used to read test data from test.properties
	 * 
	 * @param pDataKey
	 *            -> Field Locator Key
	 * @return Field name for corresponding pFieldLocatorKey
	 */
	public String getData(String pDataKey) {

		String dataValue = null;
		if (null != pDataKey) {

			dataValue = testData.getProperty(pDataKey);

			if (dataValue == null) {
				assertFalse(
						"< "+ pDataKey+ " > data not defined in test data file. Please re-check data value..",
						true);
			}
		}
		return dataValue;
	}


	static {
		// Load test Data File
		testData = loadPropFile(TEST_DATA_FILE_NAME);

	}

}
