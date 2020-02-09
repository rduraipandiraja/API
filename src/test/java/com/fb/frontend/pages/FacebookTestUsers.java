package com.fb.frontend.pages;

import java.awt.AWTException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import com.ppa.api.base.Log;
import com.ppa.api.utilities.Utils;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class FacebookTestUsers extends Utils {
	
	/************************************************************** Constructor ***********************************************************************/

	public FacebookTestUsers(WebDriver driver, ExtentTest logger) {

		this.driver = driver;
		this.logger = logger;
	}

	/******************************************************** Object Creation started ****************************************************************/
	
	/********************************************************** Xpath Creation started ***************************************************************/

	public static final String linkChangePassword				= "//span[contains(text(),'Change the name or password for this test user')]";   
	String linkGetAccessToken									= "//span[contains(text(),'Get an access token for this test user')]";  
	String linkChangePermission									= "//span[contains(text(),'Change permissions this test user granted to app')]";  
	String titleEditTestUser									= "//h3[text()='Edit Test User']";  
	String buttonEdit											= "(//button[@role='button'])[1]"; 
	String buttonAdd											= "(//div/a[@role='button'])[4]";  
	String titleCreateTestUser									= "//h3[text()='Create Test Users']";   
	String buttonCreateTestUser									= "//td/button";         
	String textID												= "//span[contains(.,'}')]/span/span/a";                                                                              
	String buttonGetAccessToken									= "//div[text()='Get Access Token']";                              
	String copyTokenButton										= "//div[text()='Get Access Token']/parent::div/parent::button/parent::div/div/div[2]";                                                                               
	public static final String textFieldNewPwd					= "(//input[@type='password'])[1]";                                                                                
	public static final String textFieldConfirmPwd				= "(//input[@type='password'])[2]";                                                                            
	String buttonSave											= "//form/div/button[@type='submit']";                                                                         
	String buttonSubmit											= "//form/div/table/tbody/tr/td/button[@type='submit']";  
	String titleTestUserAccessToken								= "//h3[text()='Test User Access Token']";     
	String titleUpdateTestUserPermissions						= "//h3[text()='Update Test User Permissions']";     
	String textAccessToken										= "(//input[@type='text'])[2]";   
	String textFieldEmail										= "//div[@id='permissions']/div/div/div/span/label/input";         
	String textFacebookUserName									= "//tr[2]/td[2]";                    
	String textFacebookID										= "//tr[2]/td[3]";               
	String textFacebookEmail									= "//tr[2]/td[4]";                                                                            
	String buttonUpdate											= "(//button[@type='submit'])[7]";                         

	/******************************************************** Methods Creation started ****************************************************************/
	
	/* getfacebookAppURLForCreatingTestUsers */
	public String getfacebookAppURLForCreatingTestUsers() throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to form facebook app url for creating test users ..! *******");
		Log.info("******* About to form facebook app url for creating test users ..! *******");
		
		String facebookAppURLForCreatingTestUsers = Utils.getConfigurationSetupTestData(0, "facebook_url") + Utils.getConfigurationSetupTestData(0, "facebook_app_url") + Utils.getConfigurationSetupTestData(0, "facebook_id") + Utils.getConfigurationSetupTestData(0, "facebook_testuser_url");
		
		logger.log(LogStatus.PASS, "Formed facebook URL for creating test user :  " + facebookAppURLForCreatingTestUsers);
		Log.info("Formed facebook URL  for creating test user :  " + facebookAppURLForCreatingTestUsers);

		return facebookAppURLForCreatingTestUsers;

	}

	/* clickAddButton */
	public void clickAddButton() throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to click AddButton ..! *******");
		Log.info("******* About to click AddButton ..! *******");

		isElementLocatedByXpathPresent(driver, buttonAdd);
		jsClickXpath(driver, buttonAdd);

		isElementLocatedByXpathPresent(driver, buttonCreateTestUser);
		
		logger.log(LogStatus.PASS, "Successfully clicked AddButton");
		Log.info("Successfully clicked AddButton");

	}
	
	/* clickAddButton */
	public void clickCreateTestUserButton() throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to click CreateTestUserButton ..! *******");
		Log.info("******* About to click CreateTestUserButton ..! *******");

		isElementLocatedByXpathPresent(driver, buttonCreateTestUser);
		jsClickXpath(driver, buttonCreateTestUser);

		isElementLocatedByXpathPresent(driver, textFacebookID);
		
		logger.log(LogStatus.PASS, "Successfully clicked CreateTestUserButton");
		Log.info("Successfully clicked CreateTestUserButton");

	}
	
	/* clickEditButton */
	public void clickEditButton() throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to click EditButton ..! *******");
		Log.info("******* About to click EditButton ..! *******");

		isElementLocatedByXpathPresent(driver, buttonEdit);
		jsClickXpath(driver, buttonEdit);

		isElementLocatedByXpathPresent(driver, linkChangePassword);
		
		logger.log(LogStatus.PASS, "Successfully clicked edit button");
		Log.info("Successfully clicked edit button");

	}

	/* clickChangePasswordLink */
	public void clickChangePasswordLink() throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to click ChangePasswordLink ..! *******");
		Log.info("******* About to click ChangePasswordLink ..! *******");

		isElementLocatedByXpathPresent(driver, linkChangePassword);
		jsClickXpath(driver, linkChangePassword);

		isElementLocatedByXpathPresent(driver, titleEditTestUser);
		
		logger.log(LogStatus.PASS, "Successfully clicked ChangePasswordLink");
		Log.info("Successfully clicked ChangePasswordLink");

	}

	/* enterNewPasswordAndConfirmPassword */
	public void enterNewPasswordAndConfirmPassword(String newPassword, String confirmPassword) throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to enter NewPassword And ConfirmPassword ..! *******");
		Log.info("******* About to enter NewPassword And ConfirmPassword ..! *******");

		isElementLocatedByXpathPresent(driver, textFieldNewPwd);
		enterTextByXpath(driver, textFieldNewPwd, newPassword);

		isElementLocatedByXpathPresent(driver, textFieldConfirmPwd);
		enterTextByXpath(driver, textFieldConfirmPwd, confirmPassword);
		
		logger.log(LogStatus.PASS, "Successfully entered NewPassword And ConfirmPassword");
		Log.info("Successfully entered NewPassword And ConfirmPassword");

	}

	/* clickSave */
	public void clickSave() throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to click save ..! *******");
		Log.info("******* About to click save ..! *******");

		isElementLocatedByXpathPresent(driver, buttonSave);
		jsClickXpath(driver, buttonSave);
		
		logger.log(LogStatus.PASS, "Successfully clicked save");
		Log.info("Successfully clicked save");

	}
	
	/* clickGetAccessToken */
	public void clickGetAccessToken() throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to click GetAccessToken ..! *******");
		Log.info("******* About to click GetAccessToken ..! *******");

		isElementLocatedByXpathPresent(driver, linkGetAccessToken);
		jsClickXpath(driver, linkGetAccessToken);

		isElementLocatedByXpathPresent(driver, buttonSubmit);
		
		logger.log(LogStatus.PASS, "Successfully clicked GetAccessToken");
		Log.info("Successfully clicked GetAccessToken");

	}

	/* clickChangePermission */
	public void clickChangePermission() throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to click ChangePermission ..! *******");
		Log.info("******* About to click ChangePermission ..! *******");

		isElementLocatedByXpathPresent(driver, linkChangePermission);
		jsClickXpath(driver, linkChangePermission);

		isElementLocatedByXpathPresent(driver, titleUpdateTestUserPermissions);
		
		logger.log(LogStatus.PASS, "Successfully clicked ChangePermission");
		Log.info("Successfully clicked ChangePermission");

	}

	/* clickChangePermission */
	public void enterPermissionCategory(String category) throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to enterPermissionCategory ..! *******");
		Log.info("******* About to enterPermissionCategory ..! *******");

		isElementLocatedByXpathPresent(driver, textFieldEmail);
		enterTextByXpath(driver, textFieldEmail, category);
		Thread.sleep(500);
		driver.findElement(By.xpath(textFieldEmail)).sendKeys(Keys.ENTER);
		Thread.sleep(500);
		isElementLocatedByXpathPresent(driver, titleUpdateTestUserPermissions);
		
		logger.log(LogStatus.PASS, "Successfully entered PermissionCategory");
		Log.info("Successfully entered PermissionCategory");

	}

	/* clickUpdate */
	public void clickUpdate() throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to click Update ..! *******");
		Log.info("******* About to click Update ..! *******");

		isElementLocatedByXpathPresent(driver, buttonUpdate);
		jsClickXpath(driver, buttonUpdate);
		
		logger.log(LogStatus.PASS, "Successfully clicked Update");
		Log.info("Successfully clicked Update");

	}
	
	/* clickSubmit */
	public void clickSubmit() throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to click Submit ..! *******");
		Log.info("******* About to click Submit ..! *******");

		isElementLocatedByXpathPresent(driver, buttonSubmit);
		jsClickXpath(driver, buttonSubmit);

		isElementLocatedByXpathPresent(driver, titleTestUserAccessToken);
		
		logger.log(LogStatus.PASS, "Successfully clicked Submit");
		Log.info("Successfully clicked Submit");

	}

	/* changePassword */
	public void changePassword(String newPassword, String confirmPassword) throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {
		
		/*logger.log(LogStatus.INFO, "******* About to change password ..! *******");
		Log.info("******* About to change password ..! *******");

		clickEditButton();
		
		clickChangePasswordLink();
		
		enterNewPasswordAndConfirmPassword(newPassword, confirmPassword);
		
		clickSave();
		
		logger.log(LogStatus.PASS, "Successfully changes password");
		Log.info("Successfully changes password");*/

	}
	
	/* getFacebookAccessToken */
	public String getFacebookAccessToken() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {
		
		logger.log(LogStatus.INFO, "******* About to get FB Access token ..! *******");
		Log.info("******* About to get FB Access token ..! *******");

		clickEditButton();
		
		clickGetAccessToken();
		
		clickSubmit();

		isElementLocatedByXpathPresent(driver, titleTestUserAccessToken);
		String facebookAccessToken = getTextByAttribute(driver, textAccessToken);
		
		driver.navigate().refresh();
		
		logger.log(LogStatus.PASS, "FB access token value is :  " + facebookAccessToken);
		Log.info("FB access token value is :  " + facebookAccessToken);

		return facebookAccessToken;

	}

	/* getFacebookUserName */
	public String getFacebookUserName() throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to get FB username ..! *******");
		Log.info("******* About to get FB username ..! *******");

		isElementLocatedByXpathPresent(driver, textFacebookUserName);
		String facebookUserName = getTextByXpath(driver, textFacebookUserName);
		
		String[] arr = facebookUserName.split(" ", 2);

		facebookUserName = arr[0];
		
		logger.log(LogStatus.PASS, "FB username is :  " + facebookUserName);
		Log.info("FB username value is :  " + facebookUserName);

		return facebookUserName;

	}
	
	/* getFacebookID */
	public String getFacebookID() throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to get FB id ..! *******");
		Log.info("******* About to get FB id ..! *******");

		isElementLocatedByXpathPresent(driver, textFacebookID);
		String facebookID = getTextByXpath(driver, textFacebookID);
		
		logger.log(LogStatus.PASS, "FB id is :  " + facebookID);
		Log.info("FB id value is :  " + facebookID);

		return facebookID;

	}

	/* getFacebookID */
	public String getFacebookEmail() throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to get FB Email ..! *******");
		Log.info("******* About to get FB Email ..! *******");

		isElementLocatedByXpathPresent(driver, textFacebookEmail);
		String facebookID = getTextByXpath(driver, textFacebookEmail);
		
		logger.log(LogStatus.PASS, "FB Email :  " + facebookID);
		Log.info("FB Email :  " + facebookID);

		return facebookID;

	}

	/* addPermission */
	public void addPermission(String category) throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {
		
		logger.log(LogStatus.INFO, "******* About to addPermission ..! *******");
		Log.info("******* About to addPermission ..! *******");

		clickEditButton();
		
		clickChangePermission();
		
		enterPermissionCategory(category);
		
		clickUpdate();
		
		driver.navigate().refresh();
		
		Thread.sleep(1500);
		
		logger.log(LogStatus.PASS, "Successfully addPermission");
		Log.info("Successfully addPermission");

	}

	
}