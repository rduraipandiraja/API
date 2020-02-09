package com.ppa.api.testsuites.users;

import java.awt.AWTException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.admin.pages.AdminCommonFunctions;
import com.admin.pages.AffillateNetwork;
import com.admin.pages.Dashboard;
import com.admin.pages.Login;
import com.admin.pages.Stores;
import com.admin.pages.Vouchers;
import com.ppa.api.base.Base;
import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.endpoints.helper.HelperAccessToken;
import com.ppa.api.endpoints.helper.SignUpOTP;
import com.ppa.api.endpoints.helper.SignUpV1WithOTP;
import com.ppa.api.endpoints.users.CreateShareLink;
import com.ppa.api.endpoints.users.ExitClicks;
import com.ppa.api.endpoints.users.RaiseTicket;
import com.ppa.api.utilities.Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RaiseTicketTestSuite extends Base {

	String storeID = "1999";
	String voucherID = "";
	String productID = "";
	String link = "";
	String storeName = "";
	String voucherName = "";
	String affiliateNetworkID = "";
	String orderIDFormat = Utils.generateRandomAlphabets(10);
	String ticketAcceptHours = "72";
	
	String storeIDTwo = "";
	String voucherIDTwo = "";
	String productIDTwo = "";
	String linkTwo = "";
	String storeNameTwo = "";
	String voucherNameTwo = "";
	String affiliateNetworkIDTwo = "";
	String orderIDFormatTwo = Utils.generateRandomAlphabets(10);
	String ticketAcceptHoursTwo = "72";

	@Test(priority=0)	
	public void storeCreationOne() throws InterruptedException, AWTException{

		try {

			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			Login objLogin = new Login(driver, logger); 
			Dashboard objDashboard = new Dashboard(driver, logger);
			Stores objAddStore = new Stores(driver, logger);
			Vouchers objVouchers = new Vouchers(driver, logger);
			AffillateNetwork objAffillateNetwork = new AffillateNetwork(driver, logger);
			
			/******************************************************************** VARIABLE CREATION ******************************************************************************/

			String adminURL	= Utils.getConfigurationSetupTestData(0, "admin_url");
			String adminDropDown = Utils.getConfigurationSetupTestData(0, "dropDown");
			String adminUsername = Utils.getConfigurationSetupTestData(0, "admin_username");
			String adminPassword = Utils.getConfigurationSetupTestData(0, "admin_password");
			
			/*********************************************************************** LOGIN PAGE*********************************************************************************/

			// Redirecting to admin panel
			Utils.loadURL(driver, adminURL);
			
			Thread.sleep(1000);
			
			objLogin.selectValueAdminDropdown(driver, adminDropDown);
			objLogin.enterUserName(driver, adminUsername);
			objLogin.enterPassword(driver, adminPassword);
			objLogin.clickLogin(driver);

			/********************************************************************* ALL STORE PAGE ******************************************************************************/

			objDashboard.clickMainMenuStore(driver);

			objAddStore.clickSubMenuStores(driver);

			objAddStore.clickAddNewStore(driver);

			objAddStore.clickStoresTab(driver);

			String arrayNewStoreDetails_One[] = objAddStore.enterCashbackStoreDetails(driver, "Active", "Adda52", "", "https://www.amazon.com/", "", "", "Terms and conditions", "All", "999041", ticketAcceptHours, "ACTIVATE CASHBACK", "90", orderIDFormat, "Within 4 Days of Shipment", "check", "check", "check", "check");

			objAddStore.clickCategoryCheckbox(driver);

			objAddStore.clickSEOTab(driver);

			objAddStore.enterKeywordsDetails(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterMetaTags(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterProductMetaTags(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterSocialMediaMessageDetails(driver, "This is "+arrayNewStoreDetails_One[0]+" social message");

			objAddStore.enterNormalPrimaryCashbackDetails(driver, "Percentage", "INR", 1500, 75, "This Is Test User Entering normal Primary cashback");

			objAddStore.enterNormalSecondaryCashbackDetails(driver, 0, "Percentage", 1250, "This Is Test User Entering Normal Secondary cashback", "All", 0);

			objAddStore.enterOthers(driver, "Terms and conditions");

			objAddStore.StoreContent_Creation(driver, arrayNewStoreDetails_One[0]);

			objAddStore.click_Button_AddStore(driver);

			/********************************************************************** STORE PAGE *********************************************************************************/

			objAddStore.enterStoreName(driver, arrayNewStoreDetails_One[0]);

			objAddStore.clickSearch(driver);

			storeID = objAddStore.getStoreID(driver);
			
			storeName = arrayNewStoreDetails_One[0];

			objAddStore.getStoreType(driver, arrayNewStoreDetails_One[0].trim());

			objAddStore.clickPickOfTheDayPickOfTheWeek_SpecificStore(driver, arrayNewStoreDetails_One[0].trim());
			
			driver.navigate().refresh();

			objAddStore.clickEdit(driver);

			objAddStore.clickSecondaryCashbackTab(driver);

			objAddStore.getSecondaryCashbackID(driver, 1);

			/******************************************************************* ALL VOUCHER PAGE ******************************************************************************/

			objVouchers.clickSubMenuVoucher(driver);

			String arrayNewVoucher_Without_Code_One[] = objVouchers.enterVoucherWithCodeDetails_APITesting(driver, arrayNewStoreDetails_One, "Increased Rate", "COUPON CODE", "All", storeID);

			/********************************************************************** VOUCHER PAGE *******************************************************************************/

			//click_Clear_Until_Default_Dropdown_Value_SelectDateRange_By_JavaScriptExecutor(driver, "btn_Clear");

			objVouchers.enterSearchCriteria(driver);

			objVouchers.enterStoreName(driver, arrayNewStoreDetails_One[0]);

			objVouchers.clickSearch(driver);

			voucherID = objVouchers.getVoucherID(driver, 1);

			voucherName = arrayNewVoucher_Without_Code_One[1];

			/******************************************************************* AFFILIATE NW PAGE *****************************************************************************/

			/*objDashboard.clickMainMenuSettings(driver);*/

			objAffillateNetwork.clickSubMenuAffiliateNetwork(driver);	

			objAffillateNetwork.clickAffiliateNetworkEditIcon(driver, arrayNewStoreDetails_One[3].trim());

			affiliateNetworkID = objAffillateNetwork.getAffiliateNetworkID(driver);

			/*************************************************************** STORE CREATION COMPLETED **************************************************************************/

		} catch (Exception e) {



			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			Login objLogin = new Login(driver, logger); 
			Dashboard objDashboard = new Dashboard(driver, logger);
			Vouchers objVouchers = new Vouchers(driver, logger);
			Stores objAddStore = new Stores(driver, logger);
			AffillateNetwork objAffillateNetwork = new AffillateNetwork(driver, logger);
			
			/******************************************************************** VARIABLE CREATION ******************************************************************************/

			String adminURL	= Utils.getConfigurationSetupTestData(0, "admin_url");
			String adminDropDown = Utils.getConfigurationSetupTestData(0, "dropDown");
			String adminUsername = Utils.getConfigurationSetupTestData(0, "admin_username");
			String adminPassword = Utils.getConfigurationSetupTestData(0, "admin_password");
			
			/*********************************************************************** LOGIN PAGE*********************************************************************************/

			// Redirecting to admin panel
			Utils.loadURL(driver, adminURL);
			
			Thread.sleep(1000);
			
			objLogin.selectValueAdminDropdown(driver, adminDropDown);
			objLogin.enterUserName(driver, adminUsername);
			objLogin.enterPassword(driver, adminPassword);
			objLogin.clickLogin(driver);

			/********************************************************************* ALL STORE PAGE ******************************************************************************/

			objDashboard.clickMainMenuStore(driver);

			objAddStore.clickSubMenuStores(driver);

			objAddStore.clickAddNewStore(driver);

			objAddStore.clickStoresTab(driver);

			String arrayNewStoreDetails_One[] = objAddStore.enterCashbackStoreDetails(driver, "Active", "Adda52", "", "https://www.amazon.com/", "", "", "Terms and conditions", "All", "999041", "72", "ACTIVATE CASHBACK", "90", "orderID", "Within 4 Days of Shipment", "check", "check", "check", "check");

			objAddStore.clickCategoryCheckbox(driver);

			objAddStore.clickSEOTab(driver);

			objAddStore.enterKeywordsDetails(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterMetaTags(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterProductMetaTags(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterSocialMediaMessageDetails(driver, "This is "+arrayNewStoreDetails_One[0]+" social message");

			objAddStore.enterNormalPrimaryCashbackDetails(driver, "Percentage", "INR", 1500, 75, "This Is Test User Entering normal Primary cashback");

			objAddStore.enterNormalSecondaryCashbackDetails(driver, 0, "Percentage", 1250, "This Is Test User Entering Normal Secondary cashback", "All", 0);

			objAddStore.enterOthers(driver, "Terms and conditions");

			objAddStore.StoreContent_Creation(driver, arrayNewStoreDetails_One[0]);

			objAddStore.click_Button_AddStore(driver);

			/********************************************************************** STORE PAGE *********************************************************************************/

			objAddStore.enterStoreName(driver, arrayNewStoreDetails_One[0]);

			objAddStore.clickSearch(driver);

			storeID = objAddStore.getStoreID(driver);
			
			storeName = arrayNewStoreDetails_One[0];

			objAddStore.getStoreType(driver, arrayNewStoreDetails_One[0].trim());

			objAddStore.clickPickOfTheDayPickOfTheWeek_SpecificStore(driver, arrayNewStoreDetails_One[0].trim());
			
			driver.navigate().refresh();

			objAddStore.clickEdit(driver);

			objAddStore.clickSecondaryCashbackTab(driver);

			objAddStore.getSecondaryCashbackID(driver, 1);

			/******************************************************************* ALL VOUCHER PAGE ******************************************************************************/

			objVouchers.clickSubMenuVoucher(driver);

			String arrayNewVoucher_Without_Code_One[] = objVouchers.enterVoucherWithCodeDetails_APITesting(driver, arrayNewStoreDetails_One, "Increased Rate", "COUPON CODE", "All", storeID);

			/********************************************************************** VOUCHER PAGE *******************************************************************************/

			//click_Clear_Until_Default_Dropdown_Value_SelectDateRange_By_JavaScriptExecutor(driver, "btn_Clear");

			objVouchers.enterSearchCriteria(driver);

			objVouchers.enterStoreName(driver, arrayNewStoreDetails_One[0]);

			objVouchers.clickSearch(driver);

			voucherID = objVouchers.getVoucherID(driver, 1);

			voucherName = arrayNewVoucher_Without_Code_One[1];

			/******************************************************************* AFFILIATE NW PAGE *****************************************************************************/

			/*objDashboard.clickMainMenuSettings(driver);*/

			objAffillateNetwork.clickSubMenuAffiliateNetwork(driver);	

			objAffillateNetwork.clickAffiliateNetworkEditIcon(driver, arrayNewStoreDetails_One[3].trim());

			affiliateNetworkID = objAffillateNetwork.getAffiliateNetworkID(driver);

		}

	}

	@Test(priority=0)	
	public void storeCreationTwo() throws InterruptedException, AWTException{

		try {

			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			Login objLogin = new Login(driver, logger); 
			Dashboard objDashboard = new Dashboard(driver, logger);
			Stores objAddStore = new Stores(driver, logger);
			Vouchers objVouchers = new Vouchers(driver, logger);
			AffillateNetwork objAffillateNetwork = new AffillateNetwork(driver, logger);
			
			/******************************************************************** VARIABLE CREATION ******************************************************************************/

			String adminURL	= Utils.getConfigurationSetupTestData(0, "admin_url");
			String adminDropDown = Utils.getConfigurationSetupTestData(0, "dropDown");
			String adminUsername = Utils.getConfigurationSetupTestData(0, "admin_username");
			String adminPassword = Utils.getConfigurationSetupTestData(0, "admin_password");
			
			/*********************************************************************** LOGIN PAGE*********************************************************************************/

			// Redirecting to admin panel
			Utils.loadURL(driver, adminURL);
			
			Thread.sleep(1000);
			
			objLogin.selectValueAdminDropdown(driver, adminDropDown);
			objLogin.enterUserName(driver, adminUsername);
			objLogin.enterPassword(driver, adminPassword);
			objLogin.clickLogin(driver);

			/********************************************************************* ALL STORE PAGE ******************************************************************************/

			objDashboard.clickMainMenuStore(driver);

			objAddStore.clickSubMenuStores(driver);

			objAddStore.clickAddNewStore(driver);

			objAddStore.clickStoresTab(driver);

			String arrayNewStoreDetails_One[] = objAddStore.enterNetwork18StoreDetails(driver, "Active", "Vouchers and Offers", "", "https://www.amazon.com/", "https://www.myntra.com/", "", "Terms and conditions", "All", "999041", ticketAcceptHoursTwo, "GRAB DEAL", "90", orderIDFormatTwo, "Within 4 Days of Shipment", "check", "check", "check", "check");

			objAddStore.clickCategoryCheckbox(driver);

			objAddStore.clickSEOTab(driver);

			objAddStore.enterKeywordsDetails(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterMetaTags(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterProductMetaTags(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterSocialMediaMessageDetails(driver, "This is "+arrayNewStoreDetails_One[0]+" social message");

			objAddStore.enterNormalPrimaryCashbackDetails(driver, "Percentage", "INR", 1500, 75, "This Is Test User Entering normal Primary cashback");

			objAddStore.enterNormalSecondaryCashbackDetails(driver, 0, "Percentage", 1250, "This Is Test User Entering Normal Secondary cashback", "All", 0);

			objAddStore.enterOthers(driver, "Terms and conditions");

			objAddStore.StoreContent_Creation(driver, arrayNewStoreDetails_One[0]);

			objAddStore.click_Button_AddStore(driver);

			/********************************************************************** STORE PAGE *********************************************************************************/

			objAddStore.enterStoreName(driver, arrayNewStoreDetails_One[0]);

			objAddStore.clickSearch(driver);

			storeIDTwo = objAddStore.getStoreID(driver);
			
			storeNameTwo = arrayNewStoreDetails_One[0];

			objAddStore.getStoreType(driver, arrayNewStoreDetails_One[0].trim());

			objAddStore.clickPickOfTheDayPickOfTheWeek_SpecificStore(driver, arrayNewStoreDetails_One[0].trim());
			
			driver.navigate().refresh();

			objAddStore.clickEdit(driver);

			objAddStore.clickSecondaryCashbackTab(driver);

			objAddStore.getSecondaryCashbackID(driver, 1);

			/******************************************************************* ALL VOUCHER PAGE ******************************************************************************/

			objVouchers.clickSubMenuVoucher(driver);

			String arrayNewVoucher_Without_Code_One[] = objVouchers.enterVoucherWithCodeDetails_APITesting(driver, arrayNewStoreDetails_One, "Increased Rate", "COUPON CODE", "All", storeIDTwo);

			/********************************************************************** VOUCHER PAGE *******************************************************************************/

			//click_Clear_Until_Default_Dropdown_Value_SelectDateRange_By_JavaScriptExecutor(driver, "btn_Clear");

			objVouchers.enterSearchCriteria(driver);

			objVouchers.enterStoreName(driver, arrayNewStoreDetails_One[0]);

			objVouchers.clickSearch(driver);

			voucherIDTwo = objVouchers.getVoucherID(driver, 1);

			voucherNameTwo = arrayNewVoucher_Without_Code_One[1];

			/******************************************************************* AFFILIATE NW PAGE *****************************************************************************/

			/*objDashboard.clickMainMenuSettings(driver);*/

			objAffillateNetwork.clickSubMenuAffiliateNetwork(driver);	

			objAffillateNetwork.clickAffiliateNetworkEditIcon(driver, arrayNewStoreDetails_One[3].trim());

			affiliateNetworkIDTwo = objAffillateNetwork.getAffiliateNetworkID(driver);

			/*************************************************************** STORE CREATION COMPLETED **************************************************************************/

		} catch (Exception e) {



			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			Login objLogin = new Login(driver, logger); 
			Dashboard objDashboard = new Dashboard(driver, logger);
			Vouchers objVouchers = new Vouchers(driver, logger);
			Stores objAddStore = new Stores(driver, logger);
			AffillateNetwork objAffillateNetwork = new AffillateNetwork(driver, logger);
			
			/******************************************************************** VARIABLE CREATION ******************************************************************************/

			String adminURL	= Utils.getConfigurationSetupTestData(0, "admin_url");
			String adminDropDown = Utils.getConfigurationSetupTestData(0, "dropDown");
			String adminUsername = Utils.getConfigurationSetupTestData(0, "admin_username");
			String adminPassword = Utils.getConfigurationSetupTestData(0, "admin_password");
			
			/*********************************************************************** LOGIN PAGE*********************************************************************************/

			// Redirecting to admin panel
			Utils.loadURL(driver, adminURL);
			
			Thread.sleep(1000);
			
			objLogin.selectValueAdminDropdown(driver, adminDropDown);
			objLogin.enterUserName(driver, adminUsername);
			objLogin.enterPassword(driver, adminPassword);
			objLogin.clickLogin(driver);

			/********************************************************************* ALL STORE PAGE ******************************************************************************/

			objDashboard.clickMainMenuStore(driver);

			objAddStore.clickSubMenuStores(driver);

			objAddStore.clickAddNewStore(driver);

			objAddStore.clickStoresTab(driver);

			String arrayNewStoreDetails_One[] = objAddStore.enterCashbackStoreDetails(driver, "Active", "Adda52", "", "https://www.amazon.com/", "", "", "Terms and conditions", "All", "999041", "72", "ACTIVATE CASHBACK", "90", "orderID", "Within 4 Days of Shipment", "check", "check", "check", "check");

			objAddStore.clickCategoryCheckbox(driver);

			objAddStore.clickSEOTab(driver);

			objAddStore.enterKeywordsDetails(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterMetaTags(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterProductMetaTags(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterSocialMediaMessageDetails(driver, "This is "+arrayNewStoreDetails_One[0]+" social message");

			objAddStore.enterNormalPrimaryCashbackDetails(driver, "Percentage", "INR", 1500, 75, "This Is Test User Entering normal Primary cashback");

			objAddStore.enterNormalSecondaryCashbackDetails(driver, 0, "Percentage", 1250, "This Is Test User Entering Normal Secondary cashback", "All", 0);

			objAddStore.enterOthers(driver, "Terms and conditions");

			objAddStore.StoreContent_Creation(driver, arrayNewStoreDetails_One[0]);

			objAddStore.click_Button_AddStore(driver);

			/********************************************************************** STORE PAGE *********************************************************************************/

			objAddStore.enterStoreName(driver, arrayNewStoreDetails_One[0]);

			objAddStore.clickSearch(driver);

			storeIDTwo = objAddStore.getStoreID(driver);
			
			storeNameTwo = arrayNewStoreDetails_One[0];

			objAddStore.getStoreType(driver, arrayNewStoreDetails_One[0].trim());

			objAddStore.clickPickOfTheDayPickOfTheWeek_SpecificStore(driver, arrayNewStoreDetails_One[0].trim());
			
			driver.navigate().refresh();

			objAddStore.clickEdit(driver);

			objAddStore.clickSecondaryCashbackTab(driver);

			objAddStore.getSecondaryCashbackID(driver, 1);

			/******************************************************************* ALL VOUCHER PAGE ******************************************************************************/

			objVouchers.clickSubMenuVoucher(driver);

			String arrayNewVoucher_Without_Code_One[] = objVouchers.enterVoucherWithCodeDetails_APITesting(driver, arrayNewStoreDetails_One, "Increased Rate", "COUPON CODE", "All", storeIDTwo);

			/********************************************************************** VOUCHER PAGE *******************************************************************************/

			//click_Clear_Until_Default_Dropdown_Value_SelectDateRange_By_JavaScriptExecutor(driver, "btn_Clear");

			objVouchers.enterSearchCriteria(driver);

			objVouchers.enterStoreName(driver, arrayNewStoreDetails_One[0]);

			objVouchers.clickSearch(driver);

			voucherIDTwo = objVouchers.getVoucherID(driver, 1);

			voucherNameTwo = arrayNewVoucher_Without_Code_One[1];

			/******************************************************************* AFFILIATE NW PAGE *****************************************************************************/

			/*objDashboard.clickMainMenuSettings(driver);*/

			objAffillateNetwork.clickSubMenuAffiliateNetwork(driver);	

			objAffillateNetwork.clickAffiliateNetworkEditIcon(driver, arrayNewStoreDetails_One[3].trim());

			affiliateNetworkIDTwo = objAffillateNetwork.getAffiliateNetworkID(driver);

		}

	}

	@Test(priority=1)
	public void validateRaiseMissingTicketWithoutUploadingImageRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeTicket				= Utils.getRestApiTestData(34, "type_ticket");
		String message					= Utils.getRestApiTestData(34, "message");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
				
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode201);
		
		objRaiseTicket.validateRaiseTicketSucessResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, typeTicket, message);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=2)
	public void validateRaiseMissingTicketAfterUploadingJPEGFileLessThan2MBRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeTicket				= Utils.getRestApiTestData(34, "type_ticket");
		String message					= Utils.getRestApiTestData(34, "message");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String fileName					= Utils.getRestApiTestData(34, "img_less_than_2mb_jpeg");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
				
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, fileName, statusCode201);
		
		objRaiseTicket.validateRaiseTicketSucessResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, typeTicket, message);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=3)
	public void validateRaiseMissingTicketAfterUploadingPNGFileLessThan2MBRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeTicket				= Utils.getRestApiTestData(34, "type_ticket");
		String message					= Utils.getRestApiTestData(34, "message");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String fileName					= Utils.getRestApiTestData(34, "img_less_than_2mb_png");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
				
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, fileName, statusCode201);
		
		objRaiseTicket.validateRaiseTicketSucessResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, typeTicket, message);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=4)
	public void validateRaiseMissingTicketAfterUploadingJPGFileLessThan2MBRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeTicket				= Utils.getRestApiTestData(34, "type_ticket");
		String message					= Utils.getRestApiTestData(34, "message");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String fileName					= Utils.getRestApiTestData(34, "img_less_than_2mb_jpg");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
				
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, fileName, statusCode201);
		
		objRaiseTicket.validateRaiseTicketSucessResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, typeTicket, message);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=5)
	public void validateRaiseMissingTicketAfterUploadingPDFFileLessThan2MBRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeTicket				= Utils.getRestApiTestData(34, "type_ticket");
		String message					= Utils.getRestApiTestData(34, "message");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String fileName					= Utils.getRestApiTestData(34, "pdf_less_than_2mb_pdf");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
				
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, fileName, statusCode201);
		
		objRaiseTicket.validateRaiseTicketSucessResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, typeTicket, message);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=6)
	public void validateRaiseMissingTicketAfterUploadingJPEGFileMoreThan2MBRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String fileName					= Utils.getRestApiTestData(34, "img_more_than_2mb_jpeg");
		String errorMessageOne			= Utils.getRestApiTestData(34, "error_message_one_more_than_2mb");
		String errorMessageTwo			= Utils.getRestApiTestData(34, "error_message_two_more_than_2mb");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
				
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, fileName, statusCode422);
		
		objRaiseTicket.validateRaiseTicketTicketAttachmentErrorResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, errorMessageOne, errorMessageTwo);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=7)
	public void validateRaiseMissingTicketAfterUploadingPNGFileMoreThan2MBRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String fileName					= Utils.getRestApiTestData(34, "img_more_than_2mb_png");
		String errorMessageOne			= Utils.getRestApiTestData(34, "error_message_one_more_than_2mb");
		String errorMessageTwo			= Utils.getRestApiTestData(34, "error_message_two_more_than_2mb");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, fileName, statusCode422);
		
		objRaiseTicket.validateRaiseTicketTicketAttachmentErrorResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, errorMessageOne, errorMessageTwo);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=8)
	public void validateRaiseMissingTicketAfterUploadingJPGFileMoreThan2MBRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String fileName					= Utils.getRestApiTestData(34, "img_more_than_2mb_jpg");
		String errorMessageOne			= Utils.getRestApiTestData(34, "error_message_one_more_than_2mb");
		String errorMessageTwo			= Utils.getRestApiTestData(34, "error_message_two_more_than_2mb");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, fileName, statusCode422);
		
		objRaiseTicket.validateRaiseTicketTicketAttachmentErrorResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, errorMessageOne, errorMessageTwo);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=9)
	public void validateRaiseMissingTicketAfterUploadingPDFFileMoreThan2MBRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String fileName					= Utils.getRestApiTestData(34, "pdf_more_than_2mb_pdf");
		String errorMessageOne			= Utils.getRestApiTestData(34, "error_message_one_more_than_2mb");
		String errorMessageTwo			= Utils.getRestApiTestData(34, "error_message_two_more_than_2mb");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, fileName, statusCode422);
		
		objRaiseTicket.validateRaiseTicketTicketAttachmentErrorResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, errorMessageOne, errorMessageTwo);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=10)
	public void validateBVAForTransactionIDWithMinimumCharactersRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeTicket				= Utils.getRestApiTestData(34, "type_ticket");
		String message					= Utils.getRestApiTestData(34, "message");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		String orderIDFormat = Utils.generateRandomAlphabets(25);
				
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode201);
		
		objRaiseTicket.validateRaiseTicketSucessResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, typeTicket, message);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=11)
	public void validateBVAForTransactionIDWithMinimumCharactersMinusOneRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeTicket				= Utils.getRestApiTestData(34, "type_ticket");
		String message					= Utils.getRestApiTestData(34, "message");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		String orderIDFormat = Utils.generateRandomAlphabets(24);
				
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode201);
		
		objRaiseTicket.validateRaiseTicketSucessResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, typeTicket, message);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=12)
	public void validateBVAForTransactionIDWithMinimumCharactersPlusOneRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String errorMessage					= Utils.getRestApiTestData(34, "error_message_transaction_id_25_character");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		String orderIDFormat = Utils.generateRandomAlphabets(26);
				
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode422);
		
		objRaiseTicket.validateRaiseTicketTransactionIDErrorResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=13)
	public void validateAfterPassingInvalidSpecialCharacterForTransactionIDRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String errorMessage				= Utils.getRestApiTestData(34, "error_message_transaction_id_invalid_character");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		String orderIDFormat = "Trans id "+"`~!@$%^&*()_+[]{}|;':,<>?"+"\""+"\"";
				
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode422);
		
		objRaiseTicket.validateRaiseTicketTransactionIDErrorResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=14)
	public void validateAfterPassingValidSpecialCharacterForTransactionIDRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String typeTicket				= Utils.getRestApiTestData(34, "type_ticket");
		String message					= Utils.getRestApiTestData(34, "message");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;

		String orderIDFormat = "Trans id "+"#-./";
				
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode201);
		
		objRaiseTicket.validateRaiseTicketSucessResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, typeTicket, message);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=15)
	public void validateBVAForTotalAmountPaidWithMinimumCharactersRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeTicket				= Utils.getRestApiTestData(34, "type_ticket");
		String message					= Utils.getRestApiTestData(34, "message");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		String totalAmount = "1";
				
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, totalAmount, couponCode, transactionDetails, statusCode201);
		
		objRaiseTicket.validateRaiseTicketSucessResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, typeTicket, message);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=16)
	public void validateBVAForTotalAmountPaidWithMinimumMinusOneCharactersRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String errorMessage				= Utils.getRestApiTestData(34, "error_message_total_amount_paid_bva");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		String totalAmount = "0";
				
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, totalAmount, couponCode, transactionDetails, statusCode422);
		
		objRaiseTicket.validateRaiseTicketTotalAmountPaidErrorResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=17)
	public void validateBVAForTotalAmountPaidWithMinimumPlusOneCharactersRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeTicket				= Utils.getRestApiTestData(34, "type_ticket");
		String message					= Utils.getRestApiTestData(34, "message");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		String totalAmount = "2";
				
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, totalAmount, couponCode, transactionDetails, statusCode201);
		
		objRaiseTicket.validateRaiseTicketSucessResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, typeTicket, message);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=18)
	public void validateBVAForTotalAmountPaidWithMaximumCharactersRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeTicket				= Utils.getRestApiTestData(34, "type_ticket");
		String message					= Utils.getRestApiTestData(34, "message");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		String totalAmount = "99999999";
				
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, totalAmount, couponCode, transactionDetails, statusCode201);
		
		objRaiseTicket.validateRaiseTicketSucessResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, typeTicket, message);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=19)
	public void validateBVAForTotalAmountPaidWithMaximumMinusOneCharactersRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String typeTicket				= Utils.getRestApiTestData(34, "type_ticket");
		String message					= Utils.getRestApiTestData(34, "message");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		String totalAmount = "99999998";
		
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, totalAmount, couponCode, transactionDetails, statusCode201);

		objRaiseTicket.validateRaiseTicketSucessResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, typeTicket, message);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=20)
	public void validateBVAForTotalAmountPaidWithMaximumPlusOneCharactersRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String errorMessage				= Utils.getRestApiTestData(34, "error_message_total_amount_paid_invalid");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		String totalAmount = "100000000";
		
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, totalAmount, couponCode, transactionDetails, statusCode422);

		objRaiseTicket.validateRaiseTicketTotalAmountPaidErrorResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=21)
	public void validateAfterPassingCharactersForTotalAmountPaidRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String errorMessage				= Utils.getRestApiTestData(34, "error_message_total_amount_must_nos");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		String totalAmount = "thousand";
		
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, totalAmount, couponCode, transactionDetails, statusCode422);

		objRaiseTicket.validateRaiseTicketTotalAmountPaidErrorResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=22)
	public void validateBVAForCouponCodeUsedWithMinimumCharactersRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeTicket				= Utils.getRestApiTestData(34, "type_ticket");
		String message					= Utils.getRestApiTestData(34, "message");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		couponCode = Utils.generateRandomAlphabets(15);
				
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode201);
		
		objRaiseTicket.validateRaiseTicketSucessResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, typeTicket, message);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=23)
	public void validateBVAForCouponCodeUsedWithMinimumMinusOneCharactersRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeTicket				= Utils.getRestApiTestData(34, "type_ticket");
		String message					= Utils.getRestApiTestData(34, "message");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		couponCode = Utils.generateRandomAlphabets(14);
				
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode201);
		
		objRaiseTicket.validateRaiseTicketSucessResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, typeTicket, message);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=24)
	public void validateBVAForCouponCodeUsedWithMinimumPlusOneCharactersRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String errorMessage				= Utils.getRestApiTestData(34, "error_message_coupons_code_used_bva");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		couponCode = Utils.generateRandomAlphabets(16);
				
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode422);
		
		objRaiseTicket.validateRaiseTicketCouponCodeUsedErrorResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=25)
	public void validateCouponCodeHavingSpaceRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String errorMessage				= Utils.getRestApiTestData(34, "error_message_coupons_code_only_nos_letter");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		couponCode = Utils.generateRandomAlphabets(5) + " " + Utils.generateRandomAlphabets(5);
		
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode422);

		objRaiseTicket.validateRaiseTicketCouponCodeUsedErrorResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=26)
	public void validateRaiseMissingTicketAfterUploadingTextFileMoreThan2MBRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String fileName					= Utils.getRestApiTestData(34, "file_text_txt");
		String errorMessageOne			= Utils.getRestApiTestData(34, "error_message_diff_file_format");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, fileName, statusCode422);
		
		objRaiseTicket.validateRaiseTicketTicketAttachmentErrorResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, errorMessageOne);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=27)
	public void validateRaiseMissingTicketAfterUploadingCorruptedFileFileMoreThan2MBRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String fileName					= Utils.getRestApiTestData(34, "img_corrupted");
		String errorMessageOne			= Utils.getRestApiTestData(34, "error_message_diff_file_format");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, fileName, statusCode422);
		
		objRaiseTicket.validateRaiseTicketTicketAttachmentErrorResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, errorMessageOne);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=28)
	public void validateRaiseMissingTicketAfterUploadingJPEGFileHavingSpaceFileNameRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String fileName					= Utils.getRestApiTestData(34, "img_less_than_2mb_jpeg_with_space");
		String errorStatus				= Utils.getRestApiTestData(34, "error_status_400");
		String errorCode				= Utils.getRestApiTestData(34, "error_code_1009");
		String errorTitle				= Utils.getRestApiTestData(34, "error_title_invalid_attachment");
		String errorDetail				= Utils.getRestApiTestData(34, "error_detail_invalid_attachment");
		String errorPointer				= Utils.getRestApiTestData(34, "error_pointer_invalid_attachment");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, fileName, statusCode400);
		
		objRaiseTicket.validateRaiseTicketErrorResponseHelperEndPoint_(objSoftAssertion, raiseTicketResponse, errorStatus, errorCode, errorTitle, errorDetail, errorPointer);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=29)
	public void validateRaiseMissingTicketAfterAddingCashbackRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String commision				= Utils.getRestApiTestData(31, "five_hundered");
		String cashback					= Utils.getRestApiTestData(31, "two_fifty");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String errorStatus				= Utils.getRestApiTestData(34, "error_status_400");
		String errorCode				= Utils.getRestApiTestData(34, "error_code_1007");
		String errorTitle				= Utils.getRestApiTestData(34, "error_title_transaction_already_tracked");
		String errorDetail				= Utils.getRestApiTestData(34, "error_detail_transaction_already_tracked");
		String errorParameter			= Utils.getRestApiTestData(34, "error_parameter_transaction_already_tracked");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode400);
		
		objRaiseTicket.validateRaiseTicketErrorResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, errorStatus, errorCode, errorTitle, errorDetail+" "+exitClick, errorParameter);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=30)
	public void validateResponseAfterTryingToRaiseMissingTicketBefore10DaysRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-11);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String errorStatus				= Utils.getRestApiTestData(34, "error_status_400");
		String errorCode				= Utils.getRestApiTestData(34, "error_code_1003");
		String errorTitle				= Utils.getRestApiTestData(34, "error_title_missing_ticket_claim");
		String errorDetail				= Utils.getRestApiTestData(34, "error_detail_missing_ticket_claim");
		String errorParameter			= Utils.getRestApiTestData(34, "error_parameter_missing_ticket_claim");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode400);
		
		objRaiseTicket.validateRaiseTicketErrorResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, errorStatus, errorCode, errorTitle, errorDetail+" "+exitClickCreatedDate, errorParameter);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=31)
	public void validateRaiseMissingTicketTwiceRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeTicket				= Utils.getRestApiTestData(34, "type_ticket");
		String message					= Utils.getRestApiTestData(34, "message");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String errorCode				= Utils.getRestApiTestData(34, "error_code_1006");
		String errorTitle				= Utils.getRestApiTestData(34, "error_title_missing_ticket_already_raised");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
				
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode201);
		
		objRaiseTicket.validateRaiseTicketSucessResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, typeTicket, message);
		
		raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode400);

		objRaiseTicket.validateRaiseTicketErrorResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, errorCode, errorTitle);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=32)
	public void validateRaiseMissingTicketAfterPassingInvalidExitIDRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String commision				= Utils.getRestApiTestData(31, "five_hundered");
		String cashback					= Utils.getRestApiTestData(31, "two_fifty");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String errorStatus				= Utils.getRestApiTestData(34, "error_status_400");
		String errorCode				= Utils.getRestApiTestData(34, "error_code_1008");
		String errorTitle				= Utils.getRestApiTestData(34, "error_title_invalid_ticket_details");
		String errorDetail				= Utils.getRestApiTestData(34, "error_detail_invalid_ticket_details");
		String errorParameter			= Utils.getRestApiTestData(34, "error_parameter_invalid_ticket_details");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick+"a";
		
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode400);
		
		objRaiseTicket.validateRaiseTicketErrorResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, errorStatus, errorCode, errorTitle, errorDetail+" "+exitClick+"a", errorParameter);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=33)
	public void validateRaiseMissingTicketAfterPassingInvalidDateFormatRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String commision				= Utils.getRestApiTestData(31, "five_hundered");
		String cashback					= Utils.getRestApiTestData(31, "two_fifty");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String errorStatus				= Utils.getRestApiTestData(34, "error_status_400");
		String errorCode				= Utils.getRestApiTestData(34, "error_code_1002");
		String errorTitle				= Utils.getRestApiTestData(34, "error_title_invalid_date_format");
		String errorDetail				= Utils.getRestApiTestData(34, "error_detail_invalid_date_format");
		String errorParameter			= Utils.getRestApiTestData(34, "error_parameter_invalid_date_format");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);

		String pathParameter = "/"+exitClickCreatedDate+"012"+"/"+storeID+"/"+exitClick;
		
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode400);
		
		objRaiseTicket.validateRaiseTicketErrorResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, errorStatus, errorCode, errorTitle, errorDetail+" "+exitClickCreatedDate+"012", errorParameter);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=34)
	public void validateRaiseMissingTicketForN18StoreRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String errorCode				= Utils.getRestApiTestData(34, "error_code_1005");
		String errorTitle				= Utils.getRestApiTestData(34, "error_title_raise_ticket_n18_store");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeIDTwo, voucherIDTwo, productIDTwo, linkTwo, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeIDTwo, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeIDTwo+"/"+exitClick;
				
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode400);
		
		objRaiseTicket.validateRaiseTicketErrorResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, errorCode, errorTitle);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=35)
	public void validateRaiseMissingTicketBeforeGivenHourRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-1);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String errorCode				= Utils.getRestApiTestData(34, "error_code_1011");
		String errorTitle				= Utils.getRestApiTestData(34, "error_title_tracks_within");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode400);

		objRaiseTicket.validateRaiseTicketErrorResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, errorCode, "Cashback for "+storeName+" "+errorTitle);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=36)
	public void validateRaiseMissingTicketMandatoryRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String errorMessageTransactionIdRequired		= Utils.getRestApiTestData(34, "error_message_transactionid_required");
		String errorMessageTotalAmountPaidRequired		= Utils.getRestApiTestData(34, "error_message_total_amount_paid_required");
		String errorMessageTransactionDetailsRequired	= Utils.getRestApiTestData(34, "error_message_transaction_details_required");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		String orderIDFormat = Utils.getRestApiTestData(8, "emptyValue");
		orderValue = Utils.getRestApiTestData(8, "emptyValue");
		couponCode = Utils.getRestApiTestData(8, "emptyValue");
		transactionDetails = Utils.getRestApiTestData(8, "emptyValue");
				
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode422);
		
		objRaiseTicket.validateRaiseTicketTransactionIDErrorResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, errorMessageTransactionIdRequired);

		objRaiseTicket.validateRaiseTicketTotalAmountPaidErrorResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, errorMessageTotalAmountPaidRequired);

		objRaiseTicket.validateRaiseTicketTransactionDetailsErrorResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, errorMessageTransactionDetailsRequired);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=37)
	public void validateBVAForTransactionDetailsWithMaximumCharactersRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeTicket				= Utils.getRestApiTestData(34, "type_ticket");
		String message					= Utils.getRestApiTestData(34, "message");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.generateRandomAlphabets(750);

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
				
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode201);
		
		objRaiseTicket.validateRaiseTicketSucessResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, typeTicket, message);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=38)
	public void validateBVAForTransactionDetailsWithMaximumMinusOneCharactersRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeTicket				= Utils.getRestApiTestData(34, "type_ticket");
		String message					= Utils.getRestApiTestData(34, "message");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.generateRandomAlphabets(749);

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
				
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode201);
		
		objRaiseTicket.validateRaiseTicketSucessResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, typeTicket, message);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=39)
	public void validateBVAForTransactionDetailsWithMaximumPlusOneCharactersRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.generateRandomAlphabets(751);
		String errorMessage				= Utils.getRestApiTestData(34, "error_msg_transaction_details_not_more_than_750character");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode422);

		objRaiseTicket.validateRaiseTicketTransactionDetailsErrorResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=40)
	public void validateAfterPassingInvalidSpecialCharactersForTransactionDetailsRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= "Trans details "+"`~$%^*_+[]{}|;':,<>?"+"\""+"\"";
		String errorMessage				= Utils.getRestApiTestData(34, "error_message_transaction_details_invalid_character");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode422);

		objRaiseTicket.validateRaiseTicketTransactionDetailsErrorResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=41)
	public void validateAfterPassingValidSpecialCharactersForTransactionDetailsRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeTicket				= Utils.getRestApiTestData(34, "type_ticket");
		String message					= Utils.getRestApiTestData(34, "message");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= "Trans details "+"!@#&()-./,";

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
				
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode201);
		
		objRaiseTicket.validateRaiseTicketSucessResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, typeTicket, message);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=42)
	public void validateRaiseMissingTicketAfterUncheckingAcceptTicketRaiseTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String errorCode				= Utils.getRestApiTestData(34, "error_code_1010");
		String errorTitle				= Utils.getRestApiTestData(34, "error_title_doesnt_accept_ticket");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.uncheckAcceptMissingTicket(storeName);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
				
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode400);
		
		objRaiseTicket.validateRaiseTicketErrorResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, errorCode, "The "+storeName+" "+errorTitle);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=43)
	public void validateEmptyHeaderExitClickEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message");
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		
		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;

		Response exitClickResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, pathParameter, statusCode401);
		
		objRaiseTicket.validateRaiseTicketErrorResponseHelperEndPoint(objSoftAssertion, exitClickResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=44)
	public void validateInvalidAuthorizationKeyValueExitClickEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message_invalid_json");
		
		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;

		accessTokenValue = Utils.getRestApiTestData(0, "auth_invalid_value");
		
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode401);

		objRaiseTicket.validateRaiseTicketErrorResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=45)
	public void validateEmptyAuthorizationKeyValueExitClickEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String errorMessage				= Utils.getRestApiTestData(12, "message");
		String exitClickCreatedDate		= Utils.currentDateFormat();
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		
		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;

		accessTokenValue = Utils.getRestApiTestData(8, "emptyValue");
		
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode401);

		objRaiseTicket.validateRaiseTicketErrorResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, errorMessage);

		objSoftAssertion.assertAll();

	}


}