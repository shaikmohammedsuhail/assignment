package utility;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CurrencyToValue {
	private static Logger log = LoggerFactory.getLogger(CurrencyToValue.class);
	private Locale locale;
	private int minimumFractionDigits;
	private NumberFormat numberFormat;

	public CurrencyToValue() {
		this.locale = Locale.US;
		this.minimumFractionDigits = 2;
	}

	CurrencyToValue(Locale locale) {
		this.locale = locale;
		this.minimumFractionDigits = 2;
	}

	CurrencyToValue(Locale locale, int minimumFractionDigits) {
		this.locale = locale;
		this.minimumFractionDigits = minimumFractionDigits;
	}

	/**
	 * This method will convert the String with Currency value into double
	 * @return double
	 * */
	public double getNum(String val) throws ParseException {
		double value = 0.00;

		numberFormat = NumberFormat.getCurrencyInstance(locale);
		numberFormat.setMinimumFractionDigits(this.minimumFractionDigits);
		value = numberFormat.parse(val).doubleValue();

		return value;
	}
	/**
	 * This method will convert the double value into String  with Currency sign
	 * @return double
	 * */

	public String getCurrencyVal(double val) {
		String value = "--ERROR--";
		numberFormat = NumberFormat.getCurrencyInstance(locale);
		numberFormat.setMinimumFractionDigits(this.minimumFractionDigits);
		value = numberFormat.format(val);
		log.info("converted value is "+value);
		return value;
	}

}
