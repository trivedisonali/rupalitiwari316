package com.formy.genericutility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
/**
 * This Class Contains All Generic or Common Method Of Java
 * @author shank
 *
 */
public class JavaUtility {
	/**
	 * This Method is used to Generate The Random Number 
	 * @param setLimit
	 * @return
	 */
	public static int randomNumber(int setLimit) {
		Random random = new Random();
		int randomNumber = random.nextInt(setLimit);
		return randomNumber;
	}
	/**
	 * This Method is used to Get the System Date And Time 
	 * @return
	 */
	public static String getDateAndTime() {
		Date date = new Date();
		DateFormat sdf= new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
		String localDateAndTimeFormat = sdf.format(date);
		return localDateAndTimeFormat;	
	}

	public static long convertStringToLong(String timeout) {
		long timelong=Long.parseLong(timeout);
		return timelong;
	}
}
