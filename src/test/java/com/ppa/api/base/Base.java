package com.ppa.api.base;

import java.util.Hashtable;
import java.util.List;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import java.lang.reflect.Method;

import com.ppa.api.utilities.XMLReader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base {

	/********************************************* Variable Creation started ******************************************************/

	/* variable declaration */
	public String testName;
	public WebDriver driver;
	protected static ExtentReports report;
	protected ExtentTest logger;
	public static String userWorkingDirectory	= "user.dir";

	/********************************************* Variable Creation started *******************************************************/
	
	/* XMLReader declaration */
	public static final XMLReader objApiTestdata = new com.ppa.api.utilities.XMLReader(System.getProperty(userWorkingDirectory) + "/input/restAPITestdata.xml");
	public static final List<Hashtable<String, String>> restAPITestdata = objApiTestdata.getDataAsList("restAPITestdata");

	public static final XMLReader objconfigurationSetup = new com.ppa.api.utilities.XMLReader(System.getProperty(userWorkingDirectory) + "/input/configurationFile.xml");
	public static final List<Hashtable<String, String>> configurationSetup = objconfigurationSetup.getDataAsList("configurationFile");

	/********************************************* Methods Creation started ********************************************************/
	
	/* partner configuration setup */
	@BeforeSuite
	public void beforeSuite() {
		
		ConfigurationSetup.partnerConfigurationSetup();

	}

	/* initialize reports */
	@BeforeTest(alwaysRun = true)
	public void beforeTest() {
		try {

			Date objNewDateFolder = new Date();

			SimpleDateFormat dateFormatFolder = new SimpleDateFormat("yyyy_MM_dd");
			File file = new File(System.getProperty(userWorkingDirectory) + "/results/" + dateFormatFolder.format(objNewDateFolder));
			boolean status = file.mkdir();

			if (status) {

				Log.info("New directory is present");
			} else {
				
				Log.error("New directory not present");

			}

			String strDatenow = dateFormatFolder.format(objNewDateFolder);

			Date objNewTimeFile = new Date();
			SimpleDateFormat dateFormatFile = new SimpleDateFormat("yyyy_MM_dd @ HH_mm_SS");

			report = new ExtentReports(System.getProperty(userWorkingDirectory) + "/results/" + strDatenow + "/" + dateFormatFile.format(objNewTimeFile) + ".html");
			report.loadConfig(new File(System.getProperty(userWorkingDirectory) + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "extentreport.xml"));

		} catch (Exception e) {

			Log.error("Error message: "+e.getMessage());
			
			throw e;

		}
	}

	/* launch browser */
	@BeforeMethod
	public void beforeMethod(Method method) {

		String operatingSystem;
		String browser;

		try {
			
			/*Test testClass = method.getAnnotation(Test.class); 
			testName = testClass.description();
			logger = report.startTest(testName);
			Log.info("Test Case Name: "+testName);*/
			
			testName = method.getName();
			logger = report.startTest(testName);
			Log.info("Test Case Name: "+testName);

			operatingSystem = System.getProperty("os.name");
			browser = "Chrome";

			if (operatingSystem.startsWith("Windows") && browser.equals("Chrome")) {

				Log.info("operatingSystem: " + operatingSystem + " and browser: " + browser + " is present");

				System.setProperty("webdriver.chrome.driver", System.getProperty(userWorkingDirectory) + File.separator + "chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("disable-infobars", "--no-sandbox");
				driver = new ChromeDriver(options);

			} else {

				Log.info("operatingSystem: " + operatingSystem + " and browser: " + browser + " is not present");

			}

		} catch (Exception e) {
			
			Log.info("Error message: "+e.getMessage());

			throw e;
			
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
	}

	/* quit browser */
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) {

		String base64Screenshot;

		if (result.getStatus() == ITestResult.SUCCESS) {

			// Take base64Screenshot screenshot.
			base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

			// Extentreports log and screenshot operations for failed tests.
			logger.log(LogStatus.PASS, "Testcase : Passed  ", logger.addBase64ScreenShot(base64Screenshot));

		} else if (result.getStatus() == ITestResult.FAILURE) {
			// Take base64Screenshot screenshot.
			base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

			// Extentreports log and screenshot operations for failed tests.
			logger.log(LogStatus.FAIL, "Exception: " + result.getThrowable());
			logger.log(LogStatus.FAIL, "Testcase : Failed , Please find the below screenshot ..! ", logger.addBase64ScreenShot(base64Screenshot));

			
		} else if (result.getStatus() == ITestResult.SKIP) {
			// Take base64Screenshot screenshot.
			base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			
			// Extentreports log and screenshot operations for failed tests.
			logger.log(LogStatus.FAIL, "Testcase : Failed , Please find the below screenshot ..! ", logger.addBase64ScreenShot(base64Screenshot));
		}

		driver.quit();
	}

	/* create reports */
	@AfterTest(alwaysRun = true)
	public void afterTest() {
		report.flush();
		/*report.endTest(logger);*/
	}

	/* log report */
	public void reportStep(String desc, String status, boolean bSnap) {

		if (status.trim().equalsIgnoreCase("PASS")) {
			logger.log(LogStatus.PASS, desc);
		} else if (status.trim().equalsIgnoreCase("FAIL")) {
			logger.log(LogStatus.FAIL, desc);
			throw new RuntimeException();
		} else if (status.trim().equalsIgnoreCase("SKIP")) {
			logger.log(LogStatus.SKIP, desc);
		} else if (status.trim().equalsIgnoreCase("INFO")) {
			logger.log(LogStatus.INFO, desc);
		} else if (status.trim().equalsIgnoreCase("ERROR")) {
			logger.log(LogStatus.FAIL, desc);
		}
	}

	/* log report */
	public void reportStep(String desc, String status) {

		if (status.equalsIgnoreCase("FAIL")) {

			reportStep(desc, status, true);

		} else {
			reportStep(desc, status, false);
		}
	}

}