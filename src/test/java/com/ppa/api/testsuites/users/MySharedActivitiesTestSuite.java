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
import com.ppa.api.endpoints.users.SharedActivity;
import com.ppa.api.utilities.Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class MySharedActivitiesTestSuite extends Base {

	String storeID = "2";
	String voucherID = "";
	String productID = "";
	String link = "";
	String storeName = "";
	String voucherName = "";
	String affiliateNetworkID = "";
	String orderIDFormat = Utils.generateRandomAlphabets(10);
	String ticketAcceptHours = "72";

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

	@Test(priority=2)
	public void validateSharedActivityResponseForNewUserSharedActivityEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		SharedActivity objSharedActivity = new SharedActivity(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String queryParameter			= Utils.getRestApiTestData(8, "emptyValue");
		String data						= Utils.getRestApiTestData(8, "emptyValue");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid	= (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response sharedActivityResponse = objSharedActivity.getSharedActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, data);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=4)
	public void validateSharedActivityResponseAfterCreatingExitClickSharedActivityEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		SharedActivity objSharedActivity = new SharedActivity(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String typeSharedList			= Utils.getRestApiTestData(41, "type_sharedlist");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String currentMonth				= Utils.requiredMonth(-4);
		String currentYear				= Utils.requiredYear(-4);
		String queryParameter			= Utils.getRestApiTestData(31, "forward_slash")+currentMonth+Utils.getRestApiTestData(31, "forward_slash")+currentYear;
		String totalRecords				= "1";
		String pageNumber				= "1";
		String pageSize					= "4";
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "mysharedactivities")+queryParameter;
		String selfURL					= self;
		String firstURL					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"4";
		String lastURL 					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"4";
		String prevURL					= Utils.getRestApiTestData(31, "null");
		String nextURL					= Utils.getRestApiTestData(31, "null");
		String clicksCount				= "1";
		String transactionCount			= "0";
		String totalEarnings			= "0";
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String exitClickCreatedDate 	= Utils.requiredDateFormat(-4);

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid	= (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.getExitClick(email);
		
		String exitClickOne = objAdminCommonFunctions.getExitClickBasedOnIndex("3");
		
		objExitClicks.exitClickBackDate(email, exitClickOne, exitClickCreatedDate);
		
		Response sharedActivityResponse = objSharedActivity.getSharedActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, totalRecords, pageNumber, pageSize);
		
		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, selfURL, firstURL, lastURL, prevURL, nextURL);

		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, "0", typeSharedList, exitClickOne, storeName, exitClickCreatedDate, clicksCount, transactionCount, totalEarnings, currency);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=5)
	public void validateSharedActivityResponseForStatusPendingConfirmRequestedPaidSharedActivityEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		SharedActivity objSharedActivity = new SharedActivity(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		NEFTPost objNEFTPost = new NEFTPost(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String typeSharedList			= Utils.getRestApiTestData(41, "type_sharedlist");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String currentMonth				= Utils.requiredMonth(-4);
		String currentYear				= Utils.requiredYear(-4);
		String queryParameter			= Utils.getRestApiTestData(31, "forward_slash")+currentMonth+Utils.getRestApiTestData(31, "forward_slash")+currentYear;
		String totalRecords				= "1";
		String pageNumber				= "1";
		String pageSize					= "4";
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "mysharedactivities")+queryParameter;
		String selfURL					= self;
		String firstURL					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"4";
		String lastURL 					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"4";
		String prevURL					= Utils.getRestApiTestData(31, "null");
		String nextURL					= Utils.getRestApiTestData(31, "null");
		String clicksCount				= "1";
		String transactionCount			= "1";
		String totalEarnings			= "250";
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String exitClickCreatedDate 	= Utils.requiredDateFormat(-4);
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
		voucherID						= Utils.getRestApiTestData(8, "emptyValue");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid	= (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.getExitClick(email);
		
		String sharedExitClick = objAdminCommonFunctions.getExitClickBasedOnIndex("3");
		
		String normalExitClick = objAdminCommonFunctions.getExitClickBasedOnIndex("2");
		
		objExitClicks.exitClickBackDate(email, sharedExitClick, exitClickCreatedDate);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(normalExitClick, cashbackType, orderValue, commision, cashback);
		
		Response sharedActivityResponse = objSharedActivity.getSharedActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, totalRecords, pageNumber, pageSize);
		
		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, selfURL, firstURL, lastURL, prevURL, nextURL);

		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, "0", typeSharedList, sharedExitClick, storeName, exitClickCreatedDate, clicksCount, transactionCount, totalEarnings, currency);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(normalExitClick);
		
		sharedActivityResponse = objSharedActivity.getSharedActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, totalRecords, pageNumber, pageSize);
		
		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, selfURL, firstURL, lastURL, prevURL, nextURL);

		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, "0", typeSharedList, sharedExitClick, storeName, exitClickCreatedDate, clicksCount, transactionCount, totalEarnings, currency);

		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		sharedActivityResponse = objSharedActivity.getSharedActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, totalRecords, pageNumber, pageSize);
		
		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, selfURL, firstURL, lastURL, prevURL, nextURL);

		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, "0", typeSharedList, sharedExitClick, storeName, exitClickCreatedDate, clicksCount, transactionCount, totalEarnings, currency);

		objAdminCommonFunctions.createCashout(email);
		
		sharedActivityResponse = objSharedActivity.getSharedActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, totalRecords, pageNumber, pageSize);
		
		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, selfURL, firstURL, lastURL, prevURL, nextURL);

		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, "0", typeSharedList, sharedExitClick, storeName, exitClickCreatedDate, clicksCount, transactionCount, totalEarnings, currency);

		objSoftAssertion.assertAll();

	}

	@Test(priority=6)
	public void validateSharedActivityResponseForStatusPendingCancelSharedActivityEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		SharedActivity objSharedActivity = new SharedActivity(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String typeSharedList			= Utils.getRestApiTestData(41, "type_sharedlist");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String currentMonth				= Utils.requiredMonth(-4);
		String currentYear				= Utils.requiredYear(-4);
		String queryParameter			= Utils.getRestApiTestData(31, "forward_slash")+currentMonth+Utils.getRestApiTestData(31, "forward_slash")+currentYear;
		String totalRecords				= "1";
		String pageNumber				= "1";
		String pageSize					= "4";
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "mysharedactivities")+queryParameter;
		String selfURL					= self;
		String firstURL					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"4";
		String lastURL 					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"4";
		String prevURL					= Utils.getRestApiTestData(31, "null");
		String nextURL					= Utils.getRestApiTestData(31, "null");
		String clicksCount				= "1";
		String transactionCount			= "1";
		String totalEarnings			= "250";
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String exitClickCreatedDate 	= Utils.requiredDateFormat(-4);
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String commision				= Utils.getRestApiTestData(31, "five_hundered");
		String cashback					= Utils.getRestApiTestData(31, "two_fifty");
		voucherID						= Utils.getRestApiTestData(8, "emptyValue");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid	= (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.getExitClick(email);
		
		String sharedExitClick = objAdminCommonFunctions.getExitClickBasedOnIndex("3");
		
		String normalExitClick = objAdminCommonFunctions.getExitClickBasedOnIndex("2");
		
		objExitClicks.exitClickBackDate(email, sharedExitClick, exitClickCreatedDate);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(normalExitClick, cashbackType, orderValue, commision, cashback);
		
		Response sharedActivityResponse = objSharedActivity.getSharedActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, totalRecords, pageNumber, pageSize);
		
		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, selfURL, firstURL, lastURL, prevURL, nextURL);

		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, "0", typeSharedList, sharedExitClick, storeName, exitClickCreatedDate, clicksCount, transactionCount, totalEarnings, currency);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(normalExitClick);

		totalEarnings = "0";
		
		sharedActivityResponse = objSharedActivity.getSharedActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, totalRecords, pageNumber, pageSize);
		
		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, selfURL, firstURL, lastURL, prevURL, nextURL);

		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, "0", typeSharedList, sharedExitClick, storeName, exitClickCreatedDate, clicksCount, transactionCount, totalEarnings, currency);

		objSoftAssertion.assertAll();

	}

	@Test(priority=7)
	public void validateSharedActivityResponseClickCountIncreasedSharedActivityEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		SharedActivity objSharedActivity = new SharedActivity(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String typeSharedList			= Utils.getRestApiTestData(41, "type_sharedlist");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String currentMonth				= Utils.requiredMonth(-4);
		String currentYear				= Utils.requiredYear(-4);
		String queryParameter			= Utils.getRestApiTestData(31, "forward_slash")+currentMonth+Utils.getRestApiTestData(31, "forward_slash")+currentYear;
		String totalRecords				= "1";
		String pageNumber				= "1";
		String pageSize					= "4";
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "mysharedactivities")+queryParameter;
		String selfURL					= self;
		String firstURL					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"4";
		String lastURL 					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"4";
		String prevURL					= Utils.getRestApiTestData(31, "null");
		String nextURL					= Utils.getRestApiTestData(31, "null");
		String clicksCount				= "1";
		String transactionCount			= "0";
		String totalEarnings			= "0";
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String exitClickCreatedDate 	= Utils.requiredDateFormat(-4);

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid	= (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		String exitClickShared = objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.getExitClick(email);
		
		String exitClickOne = objAdminCommonFunctions.getExitClickBasedOnIndex("3");
		
		objExitClicks.exitClickBackDate(email, exitClickOne, exitClickCreatedDate);
		
		Response sharedActivityResponse = objSharedActivity.getSharedActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, totalRecords, pageNumber, pageSize);
		
		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, selfURL, firstURL, lastURL, prevURL, nextURL);

		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, "0", typeSharedList, exitClickOne, storeName, exitClickCreatedDate, clicksCount, transactionCount, totalEarnings, currency);
		
		Utils.loadURL(driver, exitClickShared);
		
		Utils.waitingTillRequiredURLVisible(driver, "amazon");
		
		Utils.loadURL(driver, exitClickShared);
		
		Utils.waitingTillRequiredURLVisible(driver, "amazon");

		clicksCount = "3";
		
		sharedActivityResponse = objSharedActivity.getSharedActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, totalRecords, pageNumber, pageSize);
		
		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, selfURL, firstURL, lastURL, prevURL, nextURL);

		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, "0", typeSharedList, exitClickOne, storeName, exitClickCreatedDate, clicksCount, transactionCount, totalEarnings, currency);

		objSoftAssertion.assertAll();

	}

	@Test(priority=8)
	public void validateSharedActivityResponseTransactionCountAndTotalEarningsIncreasedSharedActivityEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		SharedActivity objSharedActivity = new SharedActivity(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String typeSharedList			= Utils.getRestApiTestData(41, "type_sharedlist");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String currentMonth				= Utils.requiredMonth(-4);
		String currentYear				= Utils.requiredYear(-4);
		String queryParameter			= Utils.getRestApiTestData(31, "forward_slash")+currentMonth+Utils.getRestApiTestData(31, "forward_slash")+currentYear;
		String totalRecords				= "1";
		String pageNumber				= "1";
		String pageSize					= "4";
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "mysharedactivities")+queryParameter;
		String selfURL					= self;
		String firstURL					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"4";
		String lastURL 					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"4";
		String prevURL					= Utils.getRestApiTestData(31, "null");
		String nextURL					= Utils.getRestApiTestData(31, "null");
		String clicksCount				= "2";
		String transactionCount			= "0";
		String totalEarnings			= "0";
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String exitClickCreatedDate 	= Utils.requiredDateFormat(-4);
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String commision				= Utils.getRestApiTestData(31, "five_hundered");
		String cashback					= Utils.getRestApiTestData(31, "two_fifty");
		voucherID						= Utils.getRestApiTestData(8, "emptyValue");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid	= (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		String sharedExitClick = objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		Utils.loadURL(driver, sharedExitClick);
		
		Utils.waitingTillRequiredURLVisible(driver, "amazon");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.getExitClick(email);
		
		String normalExitClickTwo = objAdminCommonFunctions.getExitClickBasedOnIndex("2");
		
		String normalExitClickOne = objAdminCommonFunctions.getExitClickBasedOnIndex("3");
		
		String sharedExitClickOne = objAdminCommonFunctions.getExitClickBasedOnIndex("4");
		
		objExitClicks.exitClickBackDate(email, sharedExitClickOne, exitClickCreatedDate);
		
		Response sharedActivityResponse = objSharedActivity.getSharedActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, totalRecords, pageNumber, pageSize);
		
		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, selfURL, firstURL, lastURL, prevURL, nextURL);

		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, "0", typeSharedList, sharedExitClickOne, storeName, exitClickCreatedDate, clicksCount, transactionCount, totalEarnings, currency);

		clicksCount = "2";
		
		transactionCount = "2";
		
		totalEarnings = "500";
		
		objAdminCommonFunctions.setStatusPendingForExitClick(normalExitClickOne, cashbackType, orderValue, commision, cashback);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(normalExitClickTwo, cashbackType, orderValue, commision, cashback);
		
		sharedActivityResponse = objSharedActivity.getSharedActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, totalRecords, pageNumber, pageSize);
		
		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, selfURL, firstURL, lastURL, prevURL, nextURL);

		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, "0", typeSharedList, sharedExitClickOne, storeName, exitClickCreatedDate, clicksCount, transactionCount, totalEarnings, currency);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=9)
	public void validateSharedActivityResponseAfterPassingPageNumberPageSizeQueryParameterSharedActivityEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		SharedActivity objSharedActivity = new SharedActivity(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String typeSharedList			= Utils.getRestApiTestData(41, "type_sharedlist");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String currentMonth				= Utils.requiredMonth(-4);
		String currentYear				= Utils.requiredYear(-4);
		String queryParameter			= Utils.getRestApiTestData(31, "forward_slash")+currentMonth+Utils.getRestApiTestData(31, "forward_slash")+currentYear+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"3";
		String totalRecords				= "3";
		String pageNumber				= "1";
		String pageSize					= "3";
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "mysharedactivities")+Utils.getRestApiTestData(31, "forward_slash")+currentMonth+Utils.getRestApiTestData(31, "forward_slash")+currentYear;
		String selfURL					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"3";
		String firstURL					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"3";
		String lastURL 					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"3";
		String prevURL					= Utils.getRestApiTestData(31, "null");
		String nextURL					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"3";
		String clicksCount				= "1";
		String transactionCount			= "0";
		String totalEarnings			= "0";
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String exitClickCreatedDate 	= Utils.requiredDateFormat(0);

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid	= (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
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
		
		String exitClickOne = objAdminCommonFunctions.getExitClickBasedOnIndex("7");
		
		String exitClickTwo = objAdminCommonFunctions.getExitClickBasedOnIndex("5");
		
		String exitClickThree = objAdminCommonFunctions.getExitClickBasedOnIndex("3");
		
		Response sharedActivityResponse = objSharedActivity.getSharedActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, totalRecords, pageNumber, pageSize);
		
		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, selfURL, firstURL, lastURL, prevURL, nextURL);

		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, "0", typeSharedList, exitClickThree, storeName, exitClickCreatedDate, clicksCount, transactionCount, totalEarnings, currency);
		
		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, "1", typeSharedList, exitClickTwo, storeName, exitClickCreatedDate, clicksCount, transactionCount, totalEarnings, currency);

		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, "2", typeSharedList, exitClickOne, storeName, exitClickCreatedDate, clicksCount, transactionCount, totalEarnings, currency);

		objSoftAssertion.assertAll();

	}

	@Test(priority=10)
	public void validateSharedActivityResponseAfterPassingInvalidPageNumberPageSizeQueryParameterSharedActivityEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		SharedActivity objSharedActivity = new SharedActivity(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String currentMonth				= Utils.requiredMonth(-4);
		String currentYear				= Utils.requiredYear(-4);
		String type						= Utils.getRestApiTestData(10, "type");
		String queryParameter			= Utils.getRestApiTestData(8, "emptyValue");
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String errorStatus				= Utils.getRestApiTestData(35, "error_status_400");
		String errorCode				= Utils.getRestApiTestData(35, "error_code_11001");
		String errorTitle				= Utils.getRestApiTestData(35, "error_title_invalid_page_number_size");
		String errorDetail				= Utils.getRestApiTestData(35, "error_detail_invalid_page_number_size");
		String errorParameterPageNumber	= Utils.getRestApiTestData(35, "error_pointer_invalid_page_number");
		String errorParameterPageSize	= Utils.getRestApiTestData(35, "error_pointer_invalid_page_size");
		
		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid	= (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		queryParameter = Utils.getRestApiTestData(31, "forward_slash")+currentMonth+Utils.getRestApiTestData(31, "forward_slash")+currentYear+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"number&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"1";
		
		Response sharedActivityResponse = objSharedActivity.getSharedActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode400);
		
		objSharedActivity.validateSharedActivityErrorResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameterPageNumber);
		
		queryParameter = Utils.getRestApiTestData(31, "forward_slash")+currentMonth+Utils.getRestApiTestData(31, "forward_slash")+currentYear+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"size";
		
		sharedActivityResponse = objSharedActivity.getSharedActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode400);
		
		objSharedActivity.validateSharedActivityErrorResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameterPageSize);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=11)
	public void validateSharedActivityResponseAfterCreatingExitClickNoPathParametersNoQueryParametersAddedSharedActivityEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		SharedActivity objSharedActivity = new SharedActivity(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String typeSharedList			= Utils.getRestApiTestData(41, "type_shredlist");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(8, "emptyValue");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid	= (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
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
		
		String exitClickOne = objAdminCommonFunctions.getExitClickBasedOnIndex("7");
		
		String exitClickCreatedDate = Utils.requiredDateFormat(0);
		
		String currentMonth = Utils.requiredMonth(-4);
		
		String currentYear = Utils.requiredYear(-4);
		
		objExitClicks.exitClickBackDate(email, exitClickOne, exitClickCreatedDate);
		
		exitClickCreatedDate = Utils.requiredDateFormat(-35);
		
		String previousMonth = Utils.requiredMonth(-35);
		
		String previousYear = Utils.requiredYear(-35);
		
		String exitClickTwo = objAdminCommonFunctions.getExitClickBasedOnIndex("5");
		
		objExitClicks.exitClickBackDate(email, exitClickTwo, exitClickCreatedDate);
		
		exitClickCreatedDate = Utils.requiredDateFormat(-65);
		
		String previous2ndMonth = Utils.requiredMonth(-65);
		
		String previous2ndYear = Utils.requiredYear(-65);
		
		String exitClickThree = objAdminCommonFunctions.getExitClickBasedOnIndex("3");
		
		objExitClicks.exitClickBackDate(email, exitClickThree, exitClickCreatedDate);
		
		Response sharedActivityResponse = objSharedActivity.getSharedActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		String selfURL = baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "mysharedactivities")+Utils.getRestApiTestData(31, "forward_slash")+previous2ndMonth+Utils.getRestApiTestData(31, "forward_slash")+previous2ndYear;

		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, "2", typeSharedList, previous2ndMonth+previous2ndYear, previous2ndMonth, previous2ndYear, selfURL);
		
		selfURL = baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "mysharedactivities")+Utils.getRestApiTestData(31, "forward_slash")+previousMonth+Utils.getRestApiTestData(31, "forward_slash")+previousYear;

		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, "1", typeSharedList, previousMonth+previousYear, previousMonth, previousYear, selfURL);
		
		selfURL = baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "mysharedactivities")+Utils.getRestApiTestData(31, "forward_slash")+currentMonth+Utils.getRestApiTestData(31, "forward_slash")+currentYear;

		objSharedActivity.validateSharedActivitySuccessResponseHelperEndPoint(objSoftAssertion, sharedActivityResponse, "0", typeSharedList, currentMonth+currentYear, currentMonth, currentYear, selfURL);

		objSoftAssertion.assertAll();

	}

	@Test(priority=12)
	public void validateEmptyHeaderSharedActivityEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		SharedActivity objSharedActivity = new SharedActivity(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message");

		Response exitClickResponse = objSharedActivity.getSharedActivityResponseHelperEndPoint(baseURL, statusCode401);
		
		objSharedActivity.validateSharedActivityErrorResponseHelperEndPoint(objSoftAssertion, exitClickResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=13)
	public void validateInvalidAuthorizationKeyValueSharedActivityEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		SharedActivity objSharedActivity = new SharedActivity(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message_invalid_json");
		String accessTokenValue			= Utils.getRestApiTestData(0, "auth_invalid_value");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		

		Response exitClickResponse = objSharedActivity.getSharedActivityResponseHelperEndpoint(baseURL, accessTokenValue, acceptValue, statusCode401);
		
		objSharedActivity.validateSharedActivityErrorResponseHelperEndPoint(objSoftAssertion, exitClickResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=14)
	public void validateInvalidAcceptTypeKeyValueSharedActivityEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		SharedActivity objSharedActivity = new SharedActivity(logger);
		
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
		
		Response exitClickResponse = objSharedActivity.getSharedActivityResponseHelperEndpoint(baseURL, accessTokenValue, acceptValue, statusCode415);
		
		objSharedActivity.validateSharedActivityErrorResponseHelperEndPoint(objSoftAssertion, exitClickResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=15)
	public void validateEmptyAuthorizationKeyValueSharedActivityEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		SharedActivity objSharedActivity = new SharedActivity(logger);
		
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

		Response exitClickResponse = objSharedActivity.getSharedActivityResponseHelperEndpoint(baseURL, accessTokenValue, acceptValue, statusCode401);
		
		objSharedActivity.validateSharedActivityErrorResponseHelperEndPoint(objSoftAssertion, exitClickResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=16)
	public void validateEmptyAcceptTypeKeyValueSharedActivityEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		SharedActivity objSharedActivity = new SharedActivity(logger);
		
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
		
		Response exitClickResponse = objSharedActivity.getSharedActivityResponseHelperEndpoint(baseURL, accessTokenValue, acceptValue, statusCode406);
		
		objSharedActivity.validateSharedActivityErrorResponseHelperEndPoint(objSoftAssertion, exitClickResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		objSoftAssertion.assertAll();

	}

}