package pages;

import java.text.ParseException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import prop.LoadProperties;
import runner.CommonObjects;
import utility.CurrencyToValue;

public class Base extends CommonObjects {
	WebDriver driver;
	By valuefields = By.xpath("//*[starts-with(@id,'txt_val_')]");
	By labelfields = By.xpath("//*[starts-with(@id,'lbl_val_')]");

	By totalBalanceLabel = By.id("lbl_ttl_val");
	By totalBalanceValue = By.id("txt_ttl_val");

	public CurrencyToValue currencyToValue = new CurrencyToValue();

	public Base(WebDriver driver) {
		this.driver = driver;

	}

	LoadProperties data;

	private static Logger log = LoggerFactory.getLogger(Base.class);
	List<WebElement> valueFields;
	List<WebElement> labelFields;

	public void setValueFields(String strUserName) {

		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(valuefields)));

	}

	/**
	 * This method is used to open UTP application
	 */
	public void openApplication() {

		driver.get(mData.getProperty("qa"));

	}

	/**
	 * This method will check the presence of element of the page
	 * 
	 * @return int
	 */
	public boolean verifyLableFiledsAndValueFields() {

		boolean result = false;
		valueFields = wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(valuefields)));
		labelFields = wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(labelfields)));

		for (WebElement element : valueFields) {
			if (element.isDisplayed())
				result = true;
		}
		for (WebElement element : labelFields) {
			if (element.isDisplayed())
				result = true;
		}

		if (driver.findElement(totalBalanceLabel).isDisplayed() && driver.findElement(totalBalanceValue).isDisplayed())
			result = true;

		return result;
	}

	/**
	 * This method will get the value filed value
	 * 
	 * @return int
	 */
	public int getValueFieldCount() {
		int valueFieldCount = 0;
		if (driver.findElement(totalBalanceValue).isDisplayed()) {
			valueFieldCount = labelFields.size() + 1;
		}
		return valueFieldCount;
	}

	/**
	 * This method will get the Field label value
	 * 
	 * @return int
	 */

	public int getLabelFieldCount() {
		int labelFieldCount = 0;
		if (driver.findElement(totalBalanceLabel).isDisplayed()) {
			labelFieldCount = labelFields.size() + 1;
		}
		return labelFieldCount;
	}

	/**
	 * This method will Value Fields are Not greater than Zero
	 * 
	 * @return boolean
	 */

	public boolean checkValueFieldsNotZero() {
		boolean result = false;
		for (WebElement element : valueFields) {
			try {
				System.out.println(currencyToValue.getNum(element.getText()));
				if (currencyToValue.getNum(element.getText()) > 0.0) {
					result = true;
				} else {
					result = false;
					break;
				}
			} catch (ParseException e) {
				log.error("Error while converting currency value" + e.getMessage());
				e.printStackTrace();
				org.junit.Assert.fail("Value is not with proper formatted currency" + e.getMessage());
			}
		}

		return result;

	}

	/**
	 * This method will Values are Formated With Currencies signs
	 * 
	 * @return boolean
	 */

	public boolean checkValuesWithFormatedCurrencies() throws ParseException {
		boolean result = false;
		for (WebElement element : valueFields) {

			currencyToValue.getNum(element.getText());
			result = true;
		}

		return result;
	}

	/**
	 * This method will return sum of value fields
	 * 
	 * @return String
	 */
	public String getSumofValueFields() {
		double balance = 0.00;
		for (WebElement element : valueFields) {
			try {
				balance = balance + currencyToValue.getNum(element.getText());
			} catch (ParseException e) {
				log.error("Error while converting currency value" + e.getMessage());
				e.printStackTrace();
				org.junit.Assert.fail("Value is not with proper formatted currency" + e.getMessage());
			}
		}
		return currencyToValue.getCurrencyVal(balance);
	}

	/**
	 * This method will return totalBalance field value
	 * 
	 * @return String
	 */

	public String getTotalBalance() {
		return driver.findElement(totalBalanceValue).getText();
	}

}
