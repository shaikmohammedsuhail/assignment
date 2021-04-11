package utility;

import java.text.ParseException;


public class Test {

	public static void main(String x[]) {
	CurrencyToValue c= new CurrencyToValue();
	try {
		System.out.println(c.getNum("2,369.00"));
	} catch (ParseException e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
		org.junit.Assert.assertFalse(false);
	}
		
	System.out.println(c.getCurrencyVal(10000369.0));
	}
	
}
