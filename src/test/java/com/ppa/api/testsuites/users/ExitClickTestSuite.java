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
import com.ppa.api.endpoints.payments.NEFTPost;
import com.ppa.api.endpoints.users.CreateShareLink;
import com.ppa.api.endpoints.users.ExitClicks;
import com.ppa.api.endpoints.users.RaiseTicket;
import com.ppa.api.endpoints.users.Tickets;
import com.ppa.api.utilities.Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ExitClickTestSuite extends Base {

	String storeID = "";
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
			
			Thread.sleep(3000);

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

			String arrayNewStoreDetails_One[] = objAddStore.enterCashbackStoreDetails(driver, "Active", "Adda52", "", "https://www.amazon.com/", "", "", "Terms and conditions", "All", "999041", ticketAcceptHoursTwo, "ACTIVATE CASHBACK", "90", orderIDFormatTwo, "Within 4 Days of Shipment", "check", "check", "check", "check");

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
	public void validateResponseBeforeReachingTicketHoursExitClickEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		
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
		String exitClickCreatedDate		= Utils.currentDateFormat();
		String typeOffers				= Utils.getRestApiTestData(33, "type_offers");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String related					= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "exitClick")+"/"+exitClickCreatedDate+"/"+storeID;
		voucherID						= Utils.getRestApiTestData(8, "emptyValue");

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
		
		Response exitClickResponse = objExitClicks.getExitClickResponseUserEndPoint(baseURL, accessTokenValue, exitClickCreatedDate, statusCode200);
		
		objExitClicks.validateExitClickSuccessResponseUserEndPoint(objSoftAssertion, exitClickResponse, "0", typeOffers, storeID, storeName, affiliateNetworkID, orderIDFormat, ticketAcceptHours, related);
		
		objExitClicks.validateExitClickSuccessResponseUserEndPoint(objSoftAssertion, exitClickResponse, nullValue);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=2)
	public void validateResponseAfterReachingTicketHoursExitClickEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
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
		String typeOffers				= Utils.getRestApiTestData(33, "type_offers");
		String typeTrackingID			= Utils.getRestApiTestData(33, "type_trackingid");
		String method					= Utils.getRestApiTestData(33, "method");
		String nullValue				= Utils.getRestApiTestData(33, "null");
		String related					= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "exitClick")+"/"+exitClickCreatedDate+"/"+storeID;
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		voucherID						= Utils.getRestApiTestData(8, "emptyValue");

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

		String href	= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "tickets")+"/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);
		
		Response exitClickResponse = objExitClicks.getExitClickResponseUserEndPoint(baseURL, accessTokenValue, exitClickCreatedDate, statusCode200);
		
		objExitClicks.validateExitClickSuccessResponseUserEndPoint(objSoftAssertion, exitClickResponse, "0", typeOffers, storeID, storeName, affiliateNetworkID, orderIDFormat, ticketAcceptHours, related);
		
		objExitClicks.validateExitClickSuccessResponseUserendpoint(objSoftAssertion, exitClickResponse, "0", typeTrackingID, exitClick, exitClickCreatedDate, nullValue, currency, nullValue, nullValue, nullValue, nullValue, nullValue, nullValue, href, method);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=3)
	public void validateResponseForStatusPendingConfirmRequestedPaidExitClickEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		NEFTPost objNEFTPost = new NEFTPost(logger);
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
		String typeOffers				= Utils.getRestApiTestData(33, "type_offers");
		String typeTrackingID			= Utils.getRestApiTestData(33, "type_trackingid");
		String method					= Utils.getRestApiTestData(33, "method");
		String nullValue				= Utils.getRestApiTestData(33, "null");
		String related					= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "exitClick")+"/"+exitClickCreatedDate+"/"+storeID;
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String typeNeft					= Utils.getRestApiTestData(32, "type_neft");
		String paymentType				= Utils.getRestApiTestData(32, "payment_type");
		String passcode					= Utils.getRestApiTestData(32, "password");
		String paymentMethodID			= Utils.getRestApiTestData(32, "payment_method_id");
		String accountHolderName		= Utils.getRestApiTestData(32, "account_holder_name");
		String bankName					= Utils.getRestApiTestData(32, "bank_name");
		String branch					= Utils.getRestApiTestData(32, "branch");
		String accountNumber			= Utils.getRestApiTestData(32, "account_number");
		String ifscCode					= Utils.getRestApiTestData(32, "ifsc_code");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String commision				= Utils.getRestApiTestData(31, "five_hundered");
		String cashback					= Utils.getRestApiTestData(31, "two_fifty");
		String pendingStatus			= Utils.getRestApiTestData(31, "pending");
		String confirmStatus			= Utils.getRestApiTestData(31, "confirm");
		String requestedStatus			= Utils.getRestApiTestData(31, "request");
		String paidStatus				= Utils.getRestApiTestData(31, "paid");
		voucherID						= Utils.getRestApiTestData(8, "emptyValue");

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

		String href	= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "tickets")+"/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);
		
		Response exitClickResponse = objExitClicks.getExitClickResponseUserEndPoint(baseURL, accessTokenValue, exitClickCreatedDate, statusCode200);
		
		objExitClicks.validateExitClickSuccessResponseUserEndPoint(objSoftAssertion, exitClickResponse, "0", typeOffers, storeID, storeName, affiliateNetworkID, orderIDFormat, ticketAcceptHours, related);
		
		objExitClicks.validateExitClickSuccessResponseUserendpoint(objSoftAssertion, exitClickResponse, "0", typeTrackingID, exitClick, exitClickCreatedDate, nullValue, currency, nullValue, nullValue, nullValue, nullValue, nullValue, nullValue, href, method);
		
		String transactionID = objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);
		
		exitClickResponse = objExitClicks.getExitClickResponseUserEndPoint(baseURL, accessTokenValue, exitClickCreatedDate, statusCode200);
		
		objExitClicks.validateExitClickSuccessResponseUserEndPoint(objSoftAssertion, exitClickResponse, "0", typeOffers, storeID, storeName, affiliateNetworkID, orderIDFormat, ticketAcceptHours, related);
		
		objExitClicks.validateExitClickSuccessResponseUserendpoint(objSoftAssertion, exitClickResponse, "0", typeTrackingID, exitClick, exitClickCreatedDate, orderValue, currency, cashback, transactionID, pendingStatus, nullValue, nullValue, nullValue, href, method);

		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);
		
		exitClickResponse = objExitClicks.getExitClickResponseUserEndPoint(baseURL, accessTokenValue, exitClickCreatedDate, statusCode200);
		
		objExitClicks.validateExitClickSuccessResponseUserEndPoint(objSoftAssertion, exitClickResponse, "0", typeOffers, storeID, storeName, affiliateNetworkID, orderIDFormat, ticketAcceptHours, related);
		
		objExitClicks.validateExitClickSuccessResponseUserendpoint(objSoftAssertion, exitClickResponse, "0", typeTrackingID, exitClick, exitClickCreatedDate, orderValue, currency, cashback, transactionID, confirmStatus, nullValue, nullValue, nullValue, href, method);

		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);

		exitClickResponse = objExitClicks.getExitClickResponseUserEndPoint(baseURL, accessTokenValue, exitClickCreatedDate, statusCode200);
		
		objExitClicks.validateExitClickSuccessResponseUserEndPoint(objSoftAssertion, exitClickResponse, "0", typeOffers, storeID, storeName, affiliateNetworkID, orderIDFormat, ticketAcceptHours, related);
		
		objExitClicks.validateExitClickSuccessResponseUserendpoint(objSoftAssertion, exitClickResponse, "0", typeTrackingID, exitClick, exitClickCreatedDate, orderValue, currency, cashback, transactionID, requestedStatus, nullValue, nullValue, nullValue, href, method);

		objAdminCommonFunctions.createCashout(email);

		exitClickResponse = objExitClicks.getExitClickResponseUserEndPoint(baseURL, accessTokenValue, exitClickCreatedDate, statusCode200);
		
		objExitClicks.validateExitClickSuccessResponseUserEndPoint(objSoftAssertion, exitClickResponse, "0", typeOffers, storeID, storeName, affiliateNetworkID, orderIDFormat, ticketAcceptHours, related);
		
		objExitClicks.validateExitClickSuccessResponseUserendpoint(objSoftAssertion, exitClickResponse, "0", typeTrackingID, exitClick, exitClickCreatedDate, orderValue, currency, cashback, transactionID, paidStatus, nullValue, nullValue, nullValue, href, method);

		objSoftAssertion.assertAll();

	}

	@Test(priority=4)
	public void validateResponseForStatusPendingCancelExitClickEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
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
		String typeOffers				= Utils.getRestApiTestData(33, "type_offers");
		String typeTrackingID			= Utils.getRestApiTestData(33, "type_trackingid");
		String method					= Utils.getRestApiTestData(33, "method");
		String nullValue				= Utils.getRestApiTestData(33, "null");
		String related					= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "exitClick")+"/"+exitClickCreatedDate+"/"+storeID;
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String commision				= Utils.getRestApiTestData(31, "five_hundered");
		String cashback					= Utils.getRestApiTestData(31, "two_fifty");
		String pendingStatus			= Utils.getRestApiTestData(31, "pending");
		String cancelStatus				= Utils.getRestApiTestData(31, "cancel");
		voucherID						= Utils.getRestApiTestData(8, "emptyValue");

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

		String href	= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "tickets")+"/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);
		
		Response exitClickResponse = objExitClicks.getExitClickResponseUserEndPoint(baseURL, accessTokenValue, exitClickCreatedDate, statusCode200);
		
		objExitClicks.validateExitClickSuccessResponseUserEndPoint(objSoftAssertion, exitClickResponse, "0", typeOffers, storeID, storeName, affiliateNetworkID, orderIDFormat, ticketAcceptHours, related);
		
		objExitClicks.validateExitClickSuccessResponseUserendpoint(objSoftAssertion, exitClickResponse, "0", typeTrackingID, exitClick, exitClickCreatedDate, nullValue, currency, nullValue, nullValue, nullValue, nullValue, nullValue, nullValue, href, method);
		
		String transactionID = objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);
		
		exitClickResponse = objExitClicks.getExitClickResponseUserEndPoint(baseURL, accessTokenValue, exitClickCreatedDate, statusCode200);
		
		objExitClicks.validateExitClickSuccessResponseUserEndPoint(objSoftAssertion, exitClickResponse, "0", typeOffers, storeID, storeName, affiliateNetworkID, orderIDFormat, ticketAcceptHours, related);
		
		objExitClicks.validateExitClickSuccessResponseUserendpoint(objSoftAssertion, exitClickResponse, "0", typeTrackingID, exitClick, exitClickCreatedDate, orderValue, currency, cashback, transactionID, pendingStatus, nullValue, nullValue, nullValue, href, method);

		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);
		
		exitClickResponse = objExitClicks.getExitClickResponseUserEndPoint(baseURL, accessTokenValue, exitClickCreatedDate, statusCode200);
		
		objExitClicks.validateExitClickSuccessResponseUserEndPoint(objSoftAssertion, exitClickResponse, "0", typeOffers, storeID, storeName, affiliateNetworkID, orderIDFormat, ticketAcceptHours, related);
		
		objExitClicks.validateExitClickSuccessResponseUserendpoint(objSoftAssertion, exitClickResponse, "0", typeTrackingID, exitClick, exitClickCreatedDate, orderValue, currency, cashback, transactionID, cancelStatus, nullValue, nullValue, nullValue, href, method);

		objSoftAssertion.assertAll();

	}

	@Test(priority=5)
	public void validateResponseAfterUsingInvalidTransactionDateExitClickEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		
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
		String exitClickCreatedDate		= "2018-0402";
		String errorStatus				= Utils.getRestApiTestData(33, "invalid_transaction_date_status");
		String errorCode				= Utils.getRestApiTestData(33, "invalid_transaction_date_code");
		String errorTitle				= Utils.getRestApiTestData(33, "invalid_transaction_date_title1");
		String errorDetail				= Utils.getRestApiTestData(33, "invalid_transaction_date_detail1");
		String errorParameter			= Utils.getRestApiTestData(33, "invalid_transaction_date_parameter");
		voucherID						= Utils.getRestApiTestData(8, "emptyValue");

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
		
		Response exitClickResponse = objExitClicks.getExitClickResponseUserEndPoint(baseURL, accessTokenValue, exitClickCreatedDate, statusCode400);
		
		objExitClicks.validateExitClickErrorResponseUserEndPoint(objSoftAssertion, exitClickResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=6)
	public void validateSpecificStoreResponseAfterReachingTicketHoursExitClickEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
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
		String typeTrackingID			= Utils.getRestApiTestData(33, "type_trackingid");
		String method					= Utils.getRestApiTestData(33, "method");
		String nullValue				= Utils.getRestApiTestData(33, "null");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String storeid					= storeID;
		String pathParameter			= exitClickCreatedDate+"/"+storeid;
		voucherID						= Utils.getRestApiTestData(8, "emptyValue");

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
		
		createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.getExitClick(email);
		
		String exitClickOne = objAdminCommonFunctions.getExitClickBasedOnIndex("4");

		objExitClicks.exitClickBackDate(email, exitClickOne, exitClickCreatedDate);
		
		String exitClickTwo = objAdminCommonFunctions.getExitClickBasedOnIndex("3");

		objExitClicks.exitClickBackDate(email, exitClickTwo, exitClickCreatedDate);
		
		String exitClickThree = objAdminCommonFunctions.getExitClickBasedOnIndex("2");
		
		objExitClicks.exitClickBackDate(email, exitClickThree, exitClickCreatedDate);

		Response exitClickResponse = objExitClicks.getExitClickResponseUserEndPoint(baseURL, accessTokenValue, pathParameter, statusCode200);
		
		String href	= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "tickets")+"/"+exitClickCreatedDate+"/"+storeID+"/"+exitClickOne;
		
		objExitClicks.validateExitClickSuccessResponseUserEndPoint(objSoftAssertion, exitClickResponse, "0", typeTrackingID, exitClickOne, exitClickCreatedDate, nullValue, currency, nullValue, nullValue, nullValue, nullValue, nullValue, nullValue, href, method);
		
		href	= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "tickets")+"/"+exitClickCreatedDate+"/"+storeID+"/"+exitClickTwo;
		
		objExitClicks.validateExitClickSuccessResponseUserEndPoint(objSoftAssertion, exitClickResponse, "1", typeTrackingID, exitClickTwo, exitClickCreatedDate, nullValue, currency, nullValue, nullValue, nullValue, nullValue, nullValue, nullValue, href, method);
		
		href	= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "tickets")+"/"+exitClickCreatedDate+"/"+storeID+"/"+exitClickThree;
		
		objExitClicks.validateExitClickSuccessResponseUserEndPoint(objSoftAssertion, exitClickResponse, "2", typeTrackingID, exitClickThree, exitClickCreatedDate, nullValue, currency, nullValue, nullValue, nullValue, nullValue, nullValue, nullValue, href, method);

		objSoftAssertion.assertAll();

	}

	@Test(priority=7)
	public void validateResponseHasCouponNameExitClickEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
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
		String typeOffers				= Utils.getRestApiTestData(33, "type_offers");
		String typeTrackingID			= Utils.getRestApiTestData(33, "type_trackingid");
		String method					= Utils.getRestApiTestData(33, "method");
		String nullValue				= Utils.getRestApiTestData(33, "null");
		String related					= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "exitClick")+"/"+exitClickCreatedDate+"/"+storeIDTwo;
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String currency					= ConfigurationSetup.CURRENCYTYPE;

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
		
		objExitClicks.createExitClick(storeIDTwo+"/"+voucherIDTwo, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		String href	= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "tickets")+"/"+exitClickCreatedDate+"/"+storeIDTwo+"/"+exitClick;
		
		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);
		
		Response exitClickResponse = objExitClicks.getExitClickResponseUserEndPoint(baseURL, accessTokenValue, exitClickCreatedDate, statusCode200);
		
		objExitClicks.validateExitClickSuccessResponseUserEndPoint(objSoftAssertion, exitClickResponse, "0", typeOffers, storeIDTwo, storeNameTwo, affiliateNetworkIDTwo, orderIDFormatTwo, ticketAcceptHoursTwo, related);
		
		objExitClicks.validateExitClickSuccessResponseUserendpoint(objSoftAssertion, exitClickResponse, "0", typeTrackingID, exitClick, exitClickCreatedDate, nullValue, currency, nullValue, nullValue, nullValue, nullValue, nullValue, voucherNameTwo, href, method);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=8)
	public void validateResponseDifferentStoreDifferentExitClickExitClickEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
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
		String typeOffers				= Utils.getRestApiTestData(33, "type_offers");
		String typeTrackingID			= Utils.getRestApiTestData(33, "type_trackingid");
		String method					= Utils.getRestApiTestData(33, "method");
		String nullValue				= Utils.getRestApiTestData(33, "null");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		voucherID						= Utils.getRestApiTestData(8, "emptyValue");

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

		Object[] createShareLinkResponseTwo = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeIDTwo, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURLTwo = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponseTwo);
		
		objExitClicks.createExitClick(storeIDTwo, userid, shortURLTwo, "amazon", "desktop");

		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.getExitClick(email);
		
		String exitClickOne = objAdminCommonFunctions.getExitClickBasedOnIndex("3");
		
		objExitClicks.exitClickBackDate(email, exitClickOne, exitClickCreatedDate);
		
		String exitClickTwo = objAdminCommonFunctions.getExitClickBasedOnIndex("2");

		objExitClicks.exitClickBackDate(email, exitClickTwo, exitClickCreatedDate);
		
		Response exitClickResponse = objExitClicks.getExitClickResponseUserEndPoint(baseURL, accessTokenValue, exitClickCreatedDate, statusCode200);

		String href	= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "tickets")+"/"+exitClickCreatedDate+"/"+storeID+"/"+exitClickOne;
		
		String related = baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "exitClick")+"/"+exitClickCreatedDate+"/"+storeID;
		
		objExitClicks.validateExitClickSuccessResponseUserEndPoint(objSoftAssertion, exitClickResponse, "1",  typeOffers, storeID, storeName, affiliateNetworkID, orderIDFormat, ticketAcceptHours, related);
		
		objExitClicks.validateExitClickSuccessResponseUserendpoint(objSoftAssertion, exitClickResponse, "1", typeTrackingID, exitClickOne, exitClickCreatedDate, nullValue, currency, nullValue, nullValue, nullValue, nullValue, nullValue, nullValue, href, method);

		href = baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "tickets")+"/"+exitClickCreatedDate+"/"+storeIDTwo+"/"+exitClickTwo;
		
		related	= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "exitClick")+"/"+exitClickCreatedDate+"/"+storeIDTwo;

		objExitClicks.validateExitClickSuccessResponseUserEndPoint(objSoftAssertion, exitClickResponse, "0", typeOffers, storeIDTwo, storeNameTwo, affiliateNetworkIDTwo, orderIDFormatTwo, ticketAcceptHoursTwo, related);
		
		objExitClicks.validateExitClickSuccessResponseUserendpoint(objSoftAssertion, exitClickResponse, "0", typeTrackingID, exitClickTwo, exitClickCreatedDate, nullValue, currency, nullValue, nullValue, nullValue, nullValue, nullValue, nullValue, href, method);

		objSoftAssertion.assertAll();

	}

	@Test(priority=9)
	public void validateResponseAfterUncheckingAcceptMissingTicketExitClickEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
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
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		voucherID						= Utils.getRestApiTestData(8, "emptyValue");

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
		
		Response exitClickResponse = objExitClicks.getExitClickResponseUserEndPoint(baseURL, accessTokenValue, exitClickCreatedDate, statusCode200);
		
		objExitClicks.validateExitClickSuccessResponseUserEndpoint(objSoftAssertion, exitClickResponse, nullValue);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=10)
	public void validateResponseAfterRequesting10DaysBackExitClickEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
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
		String exitClickCreatedDate		= Utils.requiredDateFormat(-15);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String errorStatus				= Utils.getRestApiTestData(33, "mc_raise_ticket_last_10days_status");
		String errorCode				= Utils.getRestApiTestData(33, "mc_raise_ticket_last_10days_code");
		String errorTitle				= Utils.getRestApiTestData(33, "mc_raise_ticket_last_10days_title1");
		String errorDetail				= Utils.getRestApiTestData(33, "mc_raise_ticket_last_10days_detail1")+" "+exitClickCreatedDate;
		String errorParameter			= Utils.getRestApiTestData(33, "mc_raise_ticket_last_10days_parameter");
		voucherID						= Utils.getRestApiTestData(8, "emptyValue");

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
		
		Response exitClickResponse = objExitClicks.getExitClickResponseUserEndPoint(baseURL, accessTokenValue, exitClickCreatedDate, statusCode400);
		
		objExitClicks.validateExitClickErrorResponseUserEndPoint(objSoftAssertion, exitClickResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=11)
	public void validateEmptyHeaderExitClickEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message");

		Response exitClickResponse = objExitClicks.getExitClickResponseUserEndPoint(baseURL, statusCode401);
		
		objExitClicks.validateExitClickErrorResponseUserEndpoint(objSoftAssertion, exitClickResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=12)
	public void validateInvalidAuthorizationKeyValueExitClickEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message_invalid_json");
		String accessTokenValue			= Utils.getRestApiTestData(0, "auth_invalid_value");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String exitClickCreatedDate		= Utils.currentDateFormat();

		Response exitClickResponse = objExitClicks.getExitClickResponseUserEndpoint(baseURL, accessTokenValue, acceptValue, exitClickCreatedDate, statusCode401);
		
		objExitClicks.validateExitClickErrorResponseUserEndpoint(objSoftAssertion, exitClickResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=13)
	public void validateInvalidAcceptTypeKeyValueExitClickEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		
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
		int statusCode415				= Integer.parseInt(Utils.getRestApiTestData(9, "status_415"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_invalid_value");
		String errorStatus				= Utils.getRestApiTestData(12, "error_status");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");
		String exitClickCreatedDate		= Utils.currentDateFormat();

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Response exitClickResponse = objExitClicks.getExitClickResponseUserEndpoint(baseURL, accessTokenValue, acceptValue, exitClickCreatedDate, statusCode415);
		
		objExitClicks.validateExitClickErrorResponseUserEndpoint(objSoftAssertion, exitClickResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=14)
	public void validateEmptyAuthorizationKeyValueExitClickEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		
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
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String errorMessage				= Utils.getRestApiTestData(12, "message");
		String exitClickCreatedDate		= Utils.currentDateFormat();

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);

		accessTokenValue = Utils.getRestApiTestData(8, "emptyValue");

		Response exitClickResponse = objExitClicks.getExitClickResponseUserEndpoint(baseURL, accessTokenValue, acceptValue, exitClickCreatedDate, statusCode401);
		
		objExitClicks.validateExitClickErrorResponseUserEndpoint(objSoftAssertion, exitClickResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=15)
	public void validateEmptyAcceptTypeKeyValueExitClickEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		
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
		int statusCode406				= Integer.parseInt(Utils.getRestApiTestData(9, "status_406"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String acceptValue				= Utils.getRestApiTestData(8, "emptyValue");
		String errorStatus				= Utils.getRestApiTestData(12, "error_status_406");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title_notacceptable");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail_accept_header");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");
		String exitClickCreatedDate		= Utils.currentDateFormat();

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Response exitClickResponse = objExitClicks.getExitClickResponseUserEndpoint(baseURL, accessTokenValue, acceptValue, exitClickCreatedDate, statusCode406);
		
		objExitClicks.validateExitClickErrorResponseUserEndpoint(objSoftAssertion, exitClickResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=16)
	public void validateExitClickResponseAfterChangingToAllMarkTicketStatusExitClickEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		Tickets objTickets = new Tickets(logger);
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
		String totalRecords				= Utils.getRestApiTestData(35, "total_records");
		String pageNumber				= Utils.getRestApiTestData(35, "page_number");
		String pageSize					= Utils.getRestApiTestData(35, "page_size");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "tickets");
		String selfURL					= self;
		String firstURL					= self+Utils.getRestApiTestData(35, "pagenumber")+"&"+Utils.getRestApiTestData(35, "pagesize");
		String lastURL					= self+Utils.getRestApiTestData(35, "pagenumber")+"&"+Utils.getRestApiTestData(35, "pagesize");
		String prevURL					= Utils.getRestApiTestData(31, "null");
		String nextURL					= Utils.getRestApiTestData(31, "null");
		String currentDate				= Utils.currentDateFormat();
		String receivedStatus			= Utils.getRestApiTestData(35, "received");
		String sentToRetailerStatus		= Utils.getRestApiTestData(35, "sent_to_retailer");
		String resolvedStatus			= Utils.getRestApiTestData(35, "resolved");
		String adminReply				= Utils.generateRandomAlphabets(20);
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String typeOffers				= Utils.getRestApiTestData(33, "type_offers");
		String typeTrackingID			= Utils.getRestApiTestData(33, "type_trackingid");
		String method					= Utils.getRestApiTestData(33, "method");
		String nullValue				= Utils.getRestApiTestData(33, "null");
		String related					= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "exitClick")+"/"+exitClickCreatedDate+"/"+storeID;
		voucherID						= Utils.getRestApiTestData(8, "emptyValue");

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
		
		String ticketID = objRaiseTicket.getTicketID(raiseTicketResponse);
				
		objRaiseTicket.validateRaiseTicketSucessResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, typeTicket, message);
		
		Response ticketResponse = objTickets.getTicketsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objTickets.validateTicketsSuccessResponseUserEndPoint(objSoftAssertion, ticketResponse, totalRecords, pageNumber, pageSize);
		
		objTickets.validateTicketsSuccessResponseUserEndPoint(objSoftAssertion, ticketResponse, selfURL, firstURL, lastURL, prevURL, nextURL);
		
		String selfLinks = self+"/"+ticketID;
		
		objTickets.validateTicketsSuccessResponseUserEndPoint(objSoftAssertion, ticketResponse, "0", typeTicket, ticketID, currentDate, storeName, orderValue, currency, receivedStatus, selfLinks);
		
		Response exitClickResponse = objExitClicks.getExitClickResponseUserEndPoint(baseURL, accessTokenValue, exitClickCreatedDate, statusCode200);

		String href	= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "tickets")+"/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		objExitClicks.validateExitClickSuccessResponseUserEndPoint(objSoftAssertion, exitClickResponse, "0", typeOffers, storeID, storeName, affiliateNetworkID, orderIDFormat, ticketAcceptHours, related);
		
		objExitClicks.validateExitClickSuccessResponseUserEndPoint(objSoftAssertion, exitClickResponse, typeTrackingID, exitClick, exitClickCreatedDate, nullValue, currency, nullValue, nullValue, nullValue, ticketID, receivedStatus, nullValue, href, method);

		objAdminCommonFunctions.changeStatusForTicket(exitClick, adminReply, sentToRetailerStatus);
		
		ticketResponse = objTickets.getTicketsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objTickets.validateTicketsSuccessResponseUserEndPoint(objSoftAssertion, ticketResponse, totalRecords, pageNumber, pageSize);
		
		objTickets.validateTicketsSuccessResponseUserEndPoint(objSoftAssertion, ticketResponse, selfURL, firstURL, lastURL, prevURL, nextURL);

		objTickets.validateTicketsSuccessResponseUserEndPoint(objSoftAssertion, ticketResponse, "0", typeTicket, ticketID, currentDate, storeName, orderValue, currency, sentToRetailerStatus, selfLinks);
		
		exitClickResponse = objExitClicks.getExitClickResponseUserEndPoint(baseURL, accessTokenValue, exitClickCreatedDate, statusCode200);

		href = baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "tickets")+"/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		objExitClicks.validateExitClickSuccessResponseUserEndPoint(objSoftAssertion, exitClickResponse, "0", typeOffers, storeID, storeName, affiliateNetworkID, orderIDFormat, ticketAcceptHours, related);
		
		objExitClicks.validateExitClickSuccessResponseUserEndPoint(objSoftAssertion, exitClickResponse, typeTrackingID, exitClick, exitClickCreatedDate, nullValue, currency, nullValue, nullValue, nullValue, ticketID, sentToRetailerStatus, nullValue, href, method);

		objAdminCommonFunctions.changeStatusForTicket(exitClick, adminReply, resolvedStatus);
		
		ticketResponse = objTickets.getTicketsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objTickets.validateTicketsSuccessResponseUserEndPoint(objSoftAssertion, ticketResponse, totalRecords, pageNumber, pageSize);
		
		objTickets.validateTicketsSuccessResponseUserEndPoint(objSoftAssertion, ticketResponse, selfURL, firstURL, lastURL, prevURL, nextURL);

		objTickets.validateTicketsSuccessResponseUserEndPoint(objSoftAssertion, ticketResponse, "0", typeTicket, ticketID, currentDate, storeName, orderValue, currency, resolvedStatus, selfLinks);
		
		exitClickResponse = objExitClicks.getExitClickResponseUserEndPoint(baseURL, accessTokenValue, exitClickCreatedDate, statusCode200);

		href = baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "tickets")+"/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		objExitClicks.validateExitClickSuccessResponseUserEndPoint(objSoftAssertion, exitClickResponse, "0", typeOffers, storeID, storeName, affiliateNetworkID, orderIDFormat, ticketAcceptHours, related);
		
		objExitClicks.validateExitClickSuccessResponseUserEndPoint(objSoftAssertion, exitClickResponse, typeTrackingID, exitClick, exitClickCreatedDate, nullValue, currency, nullValue, nullValue, nullValue, ticketID, resolvedStatus, nullValue, href, method);

		objSoftAssertion.assertAll();

	}

}