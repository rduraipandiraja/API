package com.ppa.api.testsuites.users;

import java.awt.AWTException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.admin.pages.AdminCommonFunctions;
import com.admin.pages.Dashboard;
import com.admin.pages.Login;
import com.admin.pages.Stores;
import com.ppa.api.base.Base;
import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.endpoints.helper.HelperAccessToken;
import com.ppa.api.endpoints.helper.SignUpOTP;
import com.ppa.api.endpoints.helper.SignUpV1WithOTP;
import com.ppa.api.endpoints.helper.UserLogin;
import com.ppa.api.endpoints.users.CreateShareLink;
import com.ppa.api.endpoints.users.ExitClicks;
import com.ppa.api.endpoints.users.ReferralNetwork;
import com.ppa.api.utilities.Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ReferralNetworkTestSuite extends Base {

	String storeID = "1999";
	String voucherID = "";
	String productID = "";
	String link = "";

	@Test(priority=0)	
	public void storeCreation() throws InterruptedException, AWTException{

		try {

			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			Login objLogin = new Login(driver, logger); 
			Dashboard objDashboard = new Dashboard(driver, logger);
			Stores objAddStore = new Stores(driver, logger);
			
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

			objAddStore.getStoreType(driver, arrayNewStoreDetails_One[0].trim());

			objAddStore.clickPickOfTheDayPickOfTheWeek_SpecificStore(driver, arrayNewStoreDetails_One[0].trim());

			/*************************************************************** STORE CREATION COMPLETED **************************************************************************/

		} catch (Exception e) {



			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			Login objLogin = new Login(driver, logger); 
			Dashboard objDashboard = new Dashboard(driver, logger);
			Stores objAddStore = new Stores(driver, logger);
			
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

			objAddStore.getStoreType(driver, arrayNewStoreDetails_One[0].trim());

			objAddStore.clickPickOfTheDayPickOfTheWeek_SpecificStore(driver, arrayNewStoreDetails_One[0].trim());

		}

	}

	@Test(priority=1)
	public void validateReferralNetworkResponseAfterAddingCashbackForChildUserConfirmStatusReferralNetworkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		ReferralNetwork objReferralNetwork = new ReferralNetwork(logger);
		UserLogin objUserLogin = new UserLogin(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.generateRandomAlphabets(10);
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String twoFifty					= Utils.getRestApiTestData(31, "two_fifty");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String orderValue				= Utils.getRestApiTestData(31, "ten_thousand");
		String commision				= Utils.getRestApiTestData(31, "five_thousand");
		String cashback					= Utils.getRestApiTestData(31, "two_five_hundered");
		String referralType				= Utils.getRestApiTestData(38, "referral_type");
		String totalRecords				= Utils.getRestApiTestData(38, "total_records");
		String pageNumber				= Utils.getRestApiTestData(38, "page_number");
		String pageSize					= Utils.getRestApiTestData(38, "page_size");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "referralnetworks");
		String selfURL					= self;
		String firstURL					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"10";
		String lastURL					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"10";
		String prevURL					= Utils.getRestApiTestData(31, "null");
		String nextURL					= Utils.getRestApiTestData(31, "null");
		String status					= Utils.getRestApiTestData(38, "status_active"); 
		String joinDate					= Utils.currentDateFormat();

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		String emailChild = Utils.generateNewEmail();
		
		String mobileNumberChild = Utils.generateMobileNumber(10);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumberChild, statusCode201);

		guid = (String) signUpOTPResponse[1] ;

		signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumberChild);

		String fullNameChild = Utils.generateRandomAlphabets(10);
		
		signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullNameChild, emailChild, password, mobileNumberChild, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int useridChild = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userIDChild = Integer.toString(useridChild);
		
		fullNameChild = objSignUpV1WithOTP.getUserFullName(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullNameChild);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, useridChild, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(emailChild);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		Response referralNetworkResponse = objReferralNetwork.getReferralNetworkResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objReferralNetwork.validateReferralNetworkSuccessResponseUserEndPoint(objSoftAssertion, referralNetworkResponse, totalRecords, pageNumber, pageSize);
		
		objReferralNetwork.validateReferralNetworkSuccessResponseUserEndPoint(objSoftAssertion, referralNetworkResponse, selfURL, firstURL, lastURL, prevURL, nextURL);
		
		objReferralNetwork.validateReferralNetworkSuccessResponseUserEndPoint(objSoftAssertion, referralNetworkResponse, referralType, userID, twoFifty, currency);
		
		objReferralNetwork.validateReferralNetworkSuccessResponseUserEndPoint(objSoftAssertion, referralNetworkResponse, "0", referralType, userIDChild, joinDate, fullNameChild, status);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=2)
	public void validateReferralNetworkResponseResponseAfterPassingPageNumberPageSizeReferralNetworkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		ReferralNetwork objReferralNetwork = new ReferralNetwork(logger);
		UserLogin objUserLogin = new UserLogin(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.generateRandomAlphabets(10);
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String sevenFifty				= Utils.getRestApiTestData(31, "seven_fifty");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String orderValue				= Utils.getRestApiTestData(31, "ten_thousand");
		String commision				= Utils.getRestApiTestData(31, "five_thousand");
		String cashback					= Utils.getRestApiTestData(31, "two_five_hundered");
		String referralType				= Utils.getRestApiTestData(38, "referral_type");
		String totalRecords				= Utils.getRestApiTestData(35, "total_records_3");
		String pageNumber				= Utils.getRestApiTestData(35, "page_number_2");
		String pageSize					= Utils.getRestApiTestData(35, "page_size_1");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "referralnetworks");
		String pagenumber				= Utils.getRestApiTestData(35, "pagenumber_2");
		String pagesize					= Utils.getRestApiTestData(35, "pagesize_1");
		String status					= Utils.getRestApiTestData(38, "status_active"); 
		String joinDate					= Utils.currentDateFormat();
		String queryParameter			= pagenumber+"&"+pagesize;
		String selfURL					= self+Utils.getRestApiTestData(35, "pagenumber_query_parameter")+"2&"+Utils.getRestApiTestData(35, "pagesize_query_parameter")+"1";
		String firstURL					= self+Utils.getRestApiTestData(35, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(35, "pagesize_query_parameter")+"1";
		String lastURL					= self+Utils.getRestApiTestData(35, "pagenumber_query_parameter")+"3&"+Utils.getRestApiTestData(35, "pagesize_query_parameter")+"1";
		String prevURL					= self+Utils.getRestApiTestData(35, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(35, "pagesize_query_parameter")+"1";
		String nextURL					= self+Utils.getRestApiTestData(35, "pagenumber_query_parameter")+"3&"+Utils.getRestApiTestData(35, "pagesize_query_parameter")+"1";

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		String emailChild = Utils.generateNewEmail();
		
		String mobileNumberChild = Utils.generateMobileNumber(10);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumberChild, statusCode201);

		guid = (String) signUpOTPResponse[1] ;

		signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumberChild);

		String fullNameChild = Utils.generateRandomAlphabets(10);
		
		signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullNameChild, emailChild, password, mobileNumberChild, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int useridChild = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		fullNameChild = objSignUpV1WithOTP.getUserFullName(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullNameChild);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, useridChild, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(emailChild);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);
		
		String emailChildUser = Utils.generateNewEmail();
		
		String mobileNumberChildUser = Utils.generateMobileNumber(10);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumberChildUser, statusCode201);

		guid = (String) signUpOTPResponse[1] ;

		signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumberChildUser);

		String fullNameChildUser = Utils.generateRandomAlphabets(10);
		
		signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullNameChildUser, emailChildUser, password, mobileNumberChildUser, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int useridChildUser = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userIDChildUser = Integer.toString(useridChildUser);
		
		fullNameChildUser = objSignUpV1WithOTP.getUserFullName(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullNameChildUser);
		
		createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, useridChildUser, shortURL, "amazon", "desktop");
		
		Utils.loadURL(driver, adminURL);
		
		exitClick = objAdminCommonFunctions.getExitClickAfterClickingExpandCollapse(emailChildUser);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);
		
		String emailChildUserOne = Utils.generateNewEmail();
		
		String mobileNumberChildUserOne = Utils.generateMobileNumber(10);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumberChildUserOne, statusCode201);

		guid = (String) signUpOTPResponse[1] ;

		signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumberChildUserOne);

		String fullNameChildUserOne = Utils.generateRandomAlphabets(10);
		
		signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullNameChildUserOne, emailChildUserOne, password, mobileNumberChildUserOne, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int useridChildUserOne = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		fullNameChildUserOne = objSignUpV1WithOTP.getUserFullName(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullNameChildUserOne);
		
		createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, useridChildUserOne, shortURL, "amazon", "desktop");
		
		Utils.loadURL(driver, adminURL);
		
		exitClick = objAdminCommonFunctions.getExitClickAfterClickingExpandCollapse(emailChildUserOne);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		Response referralNetworkResponse = objReferralNetwork.getReferralNetworkResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objReferralNetwork.validateReferralNetworkSuccessResponseUserEndPoint(objSoftAssertion, referralNetworkResponse, totalRecords, pageNumber, pageSize);
		
		objReferralNetwork.validateReferralNetworkSuccessResponseUserEndPoint(objSoftAssertion, referralNetworkResponse, selfURL, firstURL, lastURL, prevURL, nextURL);
		
		objReferralNetwork.validateReferralNetworkSuccessResponseUserEndPoint(objSoftAssertion, referralNetworkResponse, referralType, userID, sevenFifty, currency);
		
		objReferralNetwork.validateReferralNetworkSuccessResponseUserEndPoint(objSoftAssertion, referralNetworkResponse, "0", referralType, userIDChildUser, joinDate, fullNameChildUser, status);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=3)
	public void validateReferralNetworkResponseAfterPassingInvalidPageNumberPageSizeReferralNetworkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		ReferralNetwork objReferralNetwork = new ReferralNetwork(logger);
		UserLogin objUserLogin = new UserLogin(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.generateRandomAlphabets(10);
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String orderValue				= Utils.getRestApiTestData(31, "ten_thousand");
		String commision				= Utils.getRestApiTestData(31, "five_thousand");
		String cashback					= Utils.getRestApiTestData(31, "two_five_hundered");
		String errorStatus				= Utils.getRestApiTestData(35, "error_status_400");
		String errorCode				= Utils.getRestApiTestData(35, "error_code_11001");
		String errorTitle				= Utils.getRestApiTestData(35, "error_title_invalid_page_number_size");
		String errorDetail				= Utils.getRestApiTestData(35, "error_detail_invalid_page_number_size");
		String errorParameterPageNumber	= Utils.getRestApiTestData(35, "error_pointer_invalid_page_number");
		String errorParameterPageSize	= Utils.getRestApiTestData(35, "error_pointer_invalid_page_size");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		String emailChild = Utils.generateNewEmail();
		
		String mobileNumberChild = Utils.generateMobileNumber(10);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumberChild, statusCode201);

		guid = (String) signUpOTPResponse[1] ;

		signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumberChild);

		String fullNameChild = Utils.generateRandomAlphabets(10);
		
		signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullNameChild, emailChild, password, mobileNumberChild, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int useridChild = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		fullNameChild = objSignUpV1WithOTP.getUserFullName(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullNameChild);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, useridChild, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(emailChild);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		String queryParameter = Utils.getRestApiTestData(35, "pagenumber_query_parameter")+"number&"+Utils.getRestApiTestData(35, "pagesize_query_parameter")+"1";
		
		Response referralNetworkResponse = objReferralNetwork.getReferralNetworkResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode400);
		
		objReferralNetwork.validateReferralNetworkErrorResponseUserEndpoint(objSoftAssertion, referralNetworkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameterPageNumber);
		
		queryParameter = Utils.getRestApiTestData(35, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(35, "pagesize_query_parameter")+"size";
		
		referralNetworkResponse = objReferralNetwork.getReferralNetworkResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode400);
		
		objReferralNetwork.validateReferralNetworkErrorResponseUserEndpoint(objSoftAssertion, referralNetworkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameterPageSize);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=4)
	public void validateEmptyHeaderReferralNetworkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		ReferralNetwork objReferralNetwork = new ReferralNetwork(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message");

		Response myEarningsResponse = objReferralNetwork.getReferralNetworkResponseUserEndPoint(baseURL, statusCode401);
		
		objReferralNetwork.validateReferralNetworkErrorResponseUserEndpoint(objSoftAssertion, myEarningsResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=5)
	public void validateInvalidAuthorizationKeyValueReferralNetworkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		ReferralNetwork objReferralNetwork = new ReferralNetwork(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message_invalid_json");
		String accessTokenValue			= Utils.getRestApiTestData(0, "auth_invalid_value");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");

		Response myEarningsResponse = objReferralNetwork.getReferralNetworkResponseUserEndpoint(baseURL, accessTokenValue, acceptValue, statusCode401);
		
		objReferralNetwork.validateReferralNetworkErrorResponseUserEndpoint(objSoftAssertion, myEarningsResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=6)
	public void validateInvalidAcceptTypeKeyValueReferralNetworkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		ReferralNetwork objReferralNetwork = new ReferralNetwork(logger);
		
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

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Response myEarningsResponse = objReferralNetwork.getReferralNetworkResponseUserEndpoint(baseURL, accessTokenValue, acceptValue, statusCode415);
		
		objReferralNetwork.validateReferralNetworkErrorResponseUserEndpoint(objSoftAssertion, myEarningsResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=7)
	public void validateEmptyAuthorizationKeyValueReferralNetworkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		ReferralNetwork objReferralNetwork = new ReferralNetwork(logger);
		
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

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);

		accessTokenValue = Utils.getRestApiTestData(8, "emptyValue");

		Response myEarningsResponse = objReferralNetwork.getReferralNetworkResponseUserEndpoint(baseURL, accessTokenValue, acceptValue, statusCode401);
		
		objReferralNetwork.validateReferralNetworkErrorResponseUserEndpoint(objSoftAssertion, myEarningsResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=8)
	public void validateEmptyAcceptTypeKeyValueReferralNetworkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		ReferralNetwork objReferralNetwork = new ReferralNetwork(logger);
		
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

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Response myEarningsResponse = objReferralNetwork.getReferralNetworkResponseUserEndpoint(baseURL, accessTokenValue, acceptValue, statusCode406);
		
		objReferralNetwork.validateReferralNetworkErrorResponseUserEndpoint(objSoftAssertion, myEarningsResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=9)
	public void validateReferralNetworkResponseAfterChangingUserStatusInactiveReferralNetworkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		ReferralNetwork objReferralNetwork = new ReferralNetwork(logger);
		UserLogin objUserLogin = new UserLogin(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.generateRandomAlphabets(10);
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String twoFifty					= Utils.getRestApiTestData(31, "two_fifty");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String orderValue				= Utils.getRestApiTestData(31, "ten_thousand");
		String commision				= Utils.getRestApiTestData(31, "five_thousand");
		String cashback					= Utils.getRestApiTestData(31, "two_five_hundered");
		String referralType				= Utils.getRestApiTestData(38, "referral_type");
		String totalRecords				= Utils.getRestApiTestData(38, "total_records");
		String pageNumber				= Utils.getRestApiTestData(38, "page_number");
		String pageSize					= Utils.getRestApiTestData(38, "page_size");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "referralnetworks");
		String selfURL					= self;
		String firstURL					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"10";
		String lastURL					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"10";
		String prevURL					= Utils.getRestApiTestData(31, "null");
		String nextURL					= Utils.getRestApiTestData(31, "null");
		String status					= Utils.getRestApiTestData(38, "status_inactive"); 
		String joinDate					= Utils.currentDateFormat();

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		String emailChild = Utils.generateNewEmail();
		
		String mobileNumberChild = Utils.generateMobileNumber(10);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumberChild, statusCode201);

		guid = (String) signUpOTPResponse[1] ;

		signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumberChild);

		String fullNameChild = Utils.generateRandomAlphabets(10);
		
		signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullNameChild, emailChild, password, mobileNumberChild, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int useridChild = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userIDChild = Integer.toString(useridChild);
		
		fullNameChild = objSignUpV1WithOTP.getUserFullName(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullNameChild);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, useridChild, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(emailChild);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);
		
		objAdminCommonFunctions.changeStatusInactiveForUser(emailChild);

		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		Response referralNetworkResponse = objReferralNetwork.getReferralNetworkResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objReferralNetwork.validateReferralNetworkSuccessResponseUserEndPoint(objSoftAssertion, referralNetworkResponse, totalRecords, pageNumber, pageSize);
		
		objReferralNetwork.validateReferralNetworkSuccessResponseUserEndPoint(objSoftAssertion, referralNetworkResponse, selfURL, firstURL, lastURL, prevURL, nextURL);
		
		objReferralNetwork.validateReferralNetworkSuccessResponseUserEndPoint(objSoftAssertion, referralNetworkResponse, referralType, userID, twoFifty, currency);
		
		objReferralNetwork.validateReferralNetworkSuccessResponseUserEndPoint(objSoftAssertion, referralNetworkResponse, "0", referralType, userIDChild, joinDate, fullNameChild, status);
		
		objSoftAssertion.assertAll();

	}

}