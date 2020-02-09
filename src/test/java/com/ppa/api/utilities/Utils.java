package com.ppa.api.utilities;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ppa.api.base.Base;
import com.ppa.api.base.Log;
import com.relevantcodes.extentreports.LogStatus;

public class Utils extends Base {
	
	/******************************************************** Variable Creation started ****************************************************************/

	private static Random random = new Random();
	private static int intRandNumber;

	/******************************************************** Methods Creation started ****************************************************************/

	public static String getRestApiTestData(int index, String key) {

		return restAPITestdata.get(index).get(key).trim();

	}

	public static String getConfigurationSetupTestData(int index, String key) {

		return configurationSetup.get(index).get(key).trim();

	}

	public static String generateRandomNumber(int lengthOfTheRequiredNumber) {

		lengthOfTheRequiredNumber = lengthOfTheRequiredNumber - 1;
		String randomNum = "";
		String numbers = "6789";

		StringBuffer buffer = new StringBuffer();
		char[] c = null;
		c = new char[1];
		
		for (int i = 0; i < 1; i++) {
			c[i] = numbers.charAt(random.nextInt(numbers.length()));
			buffer.append(c[i]);
			randomNum = buffer.toString();
		}

		String characters = "0123456789";
		String randomNumber = "";
		StringBuffer buffer2 = new StringBuffer();
		char[] text = new char[lengthOfTheRequiredNumber];
		
		for (int i = 0; i < lengthOfTheRequiredNumber; i++) {
			text[i] = characters.charAt(random.nextInt(characters.length()));
			buffer2.append(text[i]);
			randomNumber = buffer2.toString();

		}

		return randomNum + randomNumber;

	}

	public static String generateRandomAlphabets(int value) {

		String characters = "abcdefghijklmnopqrstuvwxyz";
		String randomString = "";
		
		char[] text1 = new char[value];
		for (int i = 0; i < value; i++) {
			text1[i] = characters.charAt(random.nextInt(characters.length()));
			randomString = randomString + text1[i];
		}
		
		return randomString;

	}

	public static String datestamp() {

		// // created Current date
		Date objCurrentDate = new Date();
		SimpleDateFormat objDateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.MM.SSSS");
		String strDatenow = objDateFormat.format(objCurrentDate);
		return strDatenow;

	}

	public static void loadURL(WebDriver driver, String url) {

		driver.get(url);
	
	}

	public static String waitingTillRequiredURLVisible(WebDriver driver, String partOfURL) {

		String currentURL = "";

		try {
			
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.urlContains(partOfURL.trim()));

			driver.getCurrentUrl().contains(partOfURL.trim());

			currentURL = driver.getCurrentUrl();

		} catch (Throwable e) {

			System.out.println(e.getMessage());
			Log.error("Not able to redirect to retailer: "+partOfURL);
		}

		return currentURL;

	}

	public static String getCurrentURL(WebDriver driver) {

		return driver.getCurrentUrl();

	}

	public static String generateNewEmail() {

		String userName			 		= Utils.generateRandomAlphabets(2);
		String userNameAndDate 		 	= Utils.datestamp() + intRandNumber + userName;
		String email 					= userNameAndDate+ "_user@ppa.com";
		
		return email; 	
	}

	public static String generateMobileNumber(int value) {

		String mobileNumber				= Utils.generateRandomNumber(value);
		
		return mobileNumber; 	
	}

	public boolean isElementLocatedByXpathPresent(WebDriver driver, String xpath) {

		try {
			
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			return true;

		}catch (Exception e) {
			
			
			logger.log(LogStatus.FAIL, "Exception: "+ e.getMessage());
			Log.error("Exception: "+ e.getMessage());
			
		}
		
		return false;

	}
	
	public boolean clickByXpath(WebDriver driver, String xpath) {
		
		try {
			
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			driver.findElement(By.xpath(xpath)).click();
			return true;
			
		} catch (Exception e) {
			
			
			logger.log(LogStatus.FAIL, "Exception: "+ e.getMessage());
			Log.error("Exception: "+ e.getMessage());
		}
		
		return false;
	}
	
	public String getTextByXpath(WebDriver driver, String xpath) {

		try {
			
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			return driver.findElement(By.xpath(xpath)).getText().trim();

		}catch (Exception e) {

			
			logger.log(LogStatus.FAIL, "Exception: "+ e.getMessage());
			Log.error("Exception: "+ e.getMessage());
		}

		return null;
	}

	public String getTextByAttribute(WebDriver driver, String xpath) {

		try {
			
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			return driver.findElement(By.xpath(xpath)).getAttribute("value");

		}catch (Exception e) {

			
			logger.log(LogStatus.FAIL, "Exception: "+ e.getMessage());
			Log.error("Exception: "+ e.getMessage());
		}

		return null;
	}

	public String getClipBoardText(WebDriver driver) throws UnsupportedFlavorException, IOException {

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		String text = (String) clipboard.getData(DataFlavor.stringFlavor);

		return text;
	}
	
	public boolean enterTextByXpath(WebDriver driver, String xpath, String value) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			driver.findElement(By.xpath(xpath)).clear();
			driver.findElement(By.xpath(xpath)).sendKeys(value);
			return true;
		} catch (Exception e) {
			
			logger.log(LogStatus.FAIL, "Exception: "+ e.getMessage());
			Log.error("Exception: "+ e.getMessage());
		}
		return false;
	}

	public boolean validateActualValueContainsExpectedValue(String actual, String expected) {

		if (expected.trim().contains(actual.trim()) || actual.trim().contains(expected.trim())) {

			Log.info("Successfully Assered the actual value : " + actual + " Contains the expected value : " + expected + " Or vise versa");

		} else {

			Log.error(" Assertion Failed for the actual value : " + actual + " does not contain the expected value : " + expected + "Or vise Versa");

			return false;
		}
		return true;

	}

	public boolean validateActualValueDoesNotEqualsExpectedValue(String actual, String expected) {

		if (!expected.trim().equals(actual.trim()) || !actual.trim().equals(expected.trim())) {

			Log.info("Successfully Assered the actual value : " + actual + " Contains the expected value : " + expected + " Or vise versa");

		} else {

			Log.error(" Assertion Failed for the actual value : " + actual + " does not contain the expected value : " + expected + "Or vise Versa");

			return false;
		}
		return true;

	}

	public boolean jsClickXpath(WebDriver driver, String xpath) {

		try {

			System.out.println("About to verify the presence of the "+xpath+"");

			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeSelected(driver.findElement(By.xpath(xpath))));

		}catch (Exception e) {

			System.out.println("Not able to verify the presence of the "+xpath+"");

		}

		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(xpath)));
		return true;
	}

	public static String currentMonth() {
		
		DateFormat objDateFormating = new SimpleDateFormat("MMMM"); 
		objDateFormating.setTimeZone(TimeZone.getTimeZone("Europe/London"));
		String strCurrentMonth_Name = objDateFormating.format(new Date());
		
		return strCurrentMonth_Name.trim();
	}

	public static String currentYear() {
		
		DateFormat objDateFormating = new SimpleDateFormat("yyyy"); 
		objDateFormating.setTimeZone(TimeZone.getTimeZone("Europe/London"));
		String currentYear = objDateFormating.format(new Date());
		
		return currentYear;
	}

	public static String currentDateFormat() {
		
		Calendar objCalender		= Calendar.getInstance();
		Date currentDate 			= objCalender.getTime();
		
		DateFormat objDateFormating = new SimpleDateFormat("yyyy-MM-dd"); 
		objDateFormating.setTimeZone(TimeZone.getTimeZone("Europe/London"));
		String strCurrentDate 		= objDateFormating.format(currentDate);
		
		return strCurrentDate;
	}

	public static String requiredDateFormat(int aheadDays) {

		Calendar objCalender		= Calendar.getInstance();
		objCalender.add(Calendar.DATE, +aheadDays);
		Date currentDate 			= objCalender.getTime();
		DateFormat objDateFormating = new SimpleDateFormat("yyyy-MM-dd");
		objDateFormating.setTimeZone(TimeZone.getTimeZone("Europe/London"));
		String strCurrentDate 		= objDateFormating.format(currentDate);

		return strCurrentDate;
	}

	public static String requiredMonth(int aheadDays) {

		Calendar objCalender		= Calendar.getInstance();
		objCalender.add(Calendar.DATE, +aheadDays);
		Date currentDate 			= objCalender.getTime();
		DateFormat objDateFormating = new SimpleDateFormat("MMMM");
		objDateFormating.setTimeZone(TimeZone.getTimeZone("Europe/London"));
		String strCurrentDate 		= objDateFormating.format(currentDate);

		return strCurrentDate;
	}

	public static String requiredYear(int aheadDays) {

		Calendar objCalender		= Calendar.getInstance();
		objCalender.add(Calendar.DATE, +aheadDays);
		Date currentDate 			= objCalender.getTime();
		DateFormat objDateFormating = new SimpleDateFormat("yyyy");
		objDateFormating.setTimeZone(TimeZone.getTimeZone("Europe/London"));
		String strCurrentDate 		= objDateFormating.format(currentDate);

		return strCurrentDate;
	}



}