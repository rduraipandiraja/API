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
import com.ppa.api.endpoints.payments.NEFTPost;
import com.ppa.api.endpoints.users.CreateShareLink;
import com.ppa.api.endpoints.users.ExitClicks;
import com.ppa.api.endpoints.users.MyEarnings;
import com.ppa.api.endpoints.users.MyEarningsReferral;
import com.ppa.api.utilities.Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class MyEarningsReferralTestSuite extends Base {

	String storeID = "";
	String voucherID = "";
	String productID = "";
	String link = "";
	String storeName = "";

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
			
			storeName = arrayNewStoreDetails_One[0];

			objAddStore.getStoreType(driver, arrayNewStoreDetails_One[0].trim());

			objAddStore.clickPickOfTheDayPickOfTheWeek_SpecificStore(driver, arrayNewStoreDetails_One[0].trim());

			/*************************************************************** STORE CREATION COMPLETED **************************************************************************/

		} catch (Exception e) {

			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			Dashboard objDashboard = new Dashboard(driver, logger);
			Stores objAddStore = new Stores(driver, logger);
			
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

		}

	}

	@Test(priority=0)
	public void updateSignupBonusReferralSignupBonus() throws InterruptedException, AWTException {
		
		try {

			// Object creations
			AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
			
			// Assigning values
			String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
			String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
			String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
			String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
			String zero						= Utils.getRestApiTestData(31, "zero");
			String frontEndURl				= ConfigurationSetup.FRONTENDURL;

			objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
			
			objAdminCommonFunctions.updateSignUpReferralBonusBonus(frontEndURl, zero, zero);
			
		} catch (Exception e) {

			// Object creations
			AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
			
			// Assigning values
			String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
			String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
			String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
			String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
			String zero						= Utils.getRestApiTestData(31, "zero");
			String frontEndURl				= ConfigurationSetup.FRONTENDURL;

			objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
			
			objAdminCommonFunctions.updateSignUpReferralBonusBonus(frontEndURl, zero, zero);
			
		}

	}

	@Test(priority=1)
	public void validateCashbackConfirmStatusIncludeParameterCashbackRewardsMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		NEFTPost objNEFTPost = new NEFTPost(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb_rw");
		String typeCashback				= Utils.getRestApiTestData(31, "type_my_earnings_cashback");
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String twoFifty					= Utils.getRestApiTestData(31, "two_fifty");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String typeMonths				= Utils.getRestApiTestData(31, "type_months");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "cashback")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;
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
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, twoFifty, zero, zero, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "1", typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 2);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, twoFifty, zero, twoFifty, zero, zero, twoFifty, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "1", typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 2);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, twoFifty, twoFifty, zero, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "1", typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 2);
		
		objAdminCommonFunctions.createCashout(email);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, twoFifty, twoFifty, zero, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "1", typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 2);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=1)
	public void validateCashbackConfirmStatusIncludeParameterCashbackMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		NEFTPost objNEFTPost = new NEFTPost(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb");
		String typeCashback				= Utils.getRestApiTestData(31, "type_my_earnings_cashback");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String twoFifty					= Utils.getRestApiTestData(31, "two_fifty");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String typeMonths				= Utils.getRestApiTestData(31, "type_months");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "cashback")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;
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
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, twoFifty, zero, zero, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, twoFifty, zero, twoFifty, zero, zero, twoFifty, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, twoFifty, twoFifty, zero, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objAdminCommonFunctions.createCashout(email);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, twoFifty, twoFifty, zero, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=1)
	public void validateCashbackConfirmStatusIncludeParameterRewardsMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		NEFTPost objNEFTPost = new NEFTPost(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_rw");
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
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
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objAdminCommonFunctions.createCashout(email);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=4)
	public void validateCashbackCancelStatusIncludeParameterCashbackRewardsMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb_rw");
		String typeCashback				= Utils.getRestApiTestData(31, "type_my_earnings_cashback");
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String twoFifty					= Utils.getRestApiTestData(31, "two_fifty");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String typeMonths				= Utils.getRestApiTestData(31, "type_months");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "cashback")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String commision				= Utils.getRestApiTestData(31, "five_hundered");
		String cashback					= Utils.getRestApiTestData(31, "two_fifty");

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
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, twoFifty, zero, zero, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "1", typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 2);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "1", typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 2);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=5)
	public void validateCashbackCancelStatusIncludeParameterCashbackMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb");
		String typeCashback				= Utils.getRestApiTestData(31, "type_my_earnings_cashback");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String twoFifty					= Utils.getRestApiTestData(31, "two_fifty");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String typeMonths				= Utils.getRestApiTestData(31, "type_months");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "cashback")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String commision				= Utils.getRestApiTestData(31, "five_hundered");
		String cashback					= Utils.getRestApiTestData(31, "two_fifty");

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
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, twoFifty, zero, zero, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=6)
	public void validateCashbackCancelStatusIncludeParameterRewardsMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_rw");
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String commision				= Utils.getRestApiTestData(31, "five_hundered");
		String cashback					= Utils.getRestApiTestData(31, "two_fifty");

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
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=7)
	public void validateRewardsConfirmStatusIncludeParameterCashbackRewardsMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		NEFTPost objNEFTPost = new NEFTPost(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb_rw");
		String typeCashback				= Utils.getRestApiTestData(31, "type_my_earnings_cashback");
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String twoFifty					= Utils.getRestApiTestData(31, "two_fifty");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String cashbackType				= Utils.getRestApiTestData(31, "rewards");
		String typeMonths				= Utils.getRestApiTestData(31, "type_months");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "rewards")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;
		String typeNeft					= Utils.getRestApiTestData(32, "type_neft");
		String paymentType				= Utils.getRestApiTestData(32, "payment_type_rw");
		String passcode					= Utils.getRestApiTestData(32, "password");
		String paymentMethodID			= Utils.getRestApiTestData(32, "payment_method_id");
		String accountHolderName		= Utils.getRestApiTestData(32, "account_holder_name");
		String bankName					= Utils.getRestApiTestData(32, "bank_name");
		String branch					= Utils.getRestApiTestData(32, "branch");
		String accountNumber			= Utils.getRestApiTestData(32, "account_number");
		String ifscCode					= Utils.getRestApiTestData(32, "ifsc_code");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String commision				= Utils.getRestApiTestData(31, "five_hundered");
		String rewards					= Utils.getRestApiTestData(31, "two_fifty");

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
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, rewards);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);

		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "1", typeRewards, userID, twoFifty, zero, zero, twoFifty, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 2);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "1", typeRewards, userID, twoFifty, zero, twoFifty, zero, twoFifty, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 2);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "1", typeRewards, userID, twoFifty, twoFifty, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 2);
		
		objAdminCommonFunctions.createCashout(email);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "1", typeRewards, userID, twoFifty, twoFifty, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 2);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=8)
	public void validateRewardsConfirmStatusIncludeParameterCashbackMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		NEFTPost objNEFTPost = new NEFTPost(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb");
		String typeCashback				= Utils.getRestApiTestData(31, "type_my_earnings_cashback");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String cashbackType				= Utils.getRestApiTestData(31, "rewards");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String typeNeft					= Utils.getRestApiTestData(32, "type_neft");
		String paymentType				= Utils.getRestApiTestData(32, "payment_type_rw");
		String passcode					= Utils.getRestApiTestData(32, "password");
		String paymentMethodID			= Utils.getRestApiTestData(32, "payment_method_id");
		String accountHolderName		= Utils.getRestApiTestData(32, "account_holder_name");
		String bankName					= Utils.getRestApiTestData(32, "bank_name");
		String branch					= Utils.getRestApiTestData(32, "branch");
		String accountNumber			= Utils.getRestApiTestData(32, "account_number");
		String ifscCode					= Utils.getRestApiTestData(32, "ifsc_code");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String commision				= Utils.getRestApiTestData(31, "five_hundered");
		String rewards					= Utils.getRestApiTestData(31, "two_fifty");

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
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, rewards);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);

		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objAdminCommonFunctions.createCashout(email);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=9)
	public void validateRewardsConfirmStatusIncludeParameterRewardsMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		NEFTPost objNEFTPost = new NEFTPost(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_rw");
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String twoFifty					= Utils.getRestApiTestData(31, "two_fifty");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String cashbackType				= Utils.getRestApiTestData(31, "rewards");
		String typeMonths				= Utils.getRestApiTestData(31, "type_months");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "rewards")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;
		String typeNeft					= Utils.getRestApiTestData(32, "type_neft");
		String paymentType				= Utils.getRestApiTestData(32, "payment_type_rw");
		String passcode					= Utils.getRestApiTestData(32, "password");
		String paymentMethodID			= Utils.getRestApiTestData(32, "payment_method_id");
		String accountHolderName		= Utils.getRestApiTestData(32, "account_holder_name");
		String bankName					= Utils.getRestApiTestData(32, "bank_name");
		String branch					= Utils.getRestApiTestData(32, "branch");
		String accountNumber			= Utils.getRestApiTestData(32, "account_number");
		String ifscCode					= Utils.getRestApiTestData(32, "ifsc_code");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String commision				= Utils.getRestApiTestData(31, "five_hundered");
		String rewards					= Utils.getRestApiTestData(31, "two_fifty");

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
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, rewards);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeRewards, userID, twoFifty, zero, zero, twoFifty, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeRewards, userID, twoFifty, zero, twoFifty, zero, twoFifty, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeRewards, userID, twoFifty, twoFifty, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objAdminCommonFunctions.createCashout(email);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeRewards, userID, twoFifty, twoFifty, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=10)
	public void validateRewardsCancelStatusIncludeParameterCashbackRewardsMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb_rw");
		String typeCashback				= Utils.getRestApiTestData(31, "type_my_earnings_cashback");
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String twoFifty					= Utils.getRestApiTestData(31, "two_fifty");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String cashbackType				= Utils.getRestApiTestData(31, "rewards");
		String typeMonths				= Utils.getRestApiTestData(31, "type_months");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "rewards")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String commision				= Utils.getRestApiTestData(31, "five_hundered");
		String rewards					= Utils.getRestApiTestData(31, "two_fifty");

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
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, rewards);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);

		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "1", typeRewards, userID, twoFifty, zero, zero, twoFifty, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 2);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "1", typeRewards, userID, zero, zero, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 2);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=11)
	public void validateRewardsCancelStatusIncludeParameterCashbackMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb");
		String typeCashback				= Utils.getRestApiTestData(31, "type_my_earnings_cashback");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String cashbackType				= Utils.getRestApiTestData(31, "rewards");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String commision				= Utils.getRestApiTestData(31, "five_hundered");
		String rewards					= Utils.getRestApiTestData(31, "two_fifty");

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
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, rewards);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);

		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=12)
	public void validateRewardsCancelStatusIncludeParameterRewardsMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_rw");
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String twoFifty					= Utils.getRestApiTestData(31, "two_fifty");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String cashbackType				= Utils.getRestApiTestData(31, "rewards");
		String typeMonths				= Utils.getRestApiTestData(31, "type_months");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "rewards")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String commision				= Utils.getRestApiTestData(31, "five_hundered");
		String rewards					= Utils.getRestApiTestData(31, "two_fifty");

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
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, rewards);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeRewards, userID, twoFifty, zero, zero, twoFifty, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeRewards, userID, zero, zero, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=13)
	public void validateChildUserCashbackConfirmStatusIncludeParameterCashbackRewardsMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		NEFTPost objNEFTPost = new NEFTPost(logger);
		UserLogin objUserLogin = new UserLogin(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb_rw");
		String typeCashback				= Utils.getRestApiTestData(31, "type_my_earnings_cashback");
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String twoFifty					= Utils.getRestApiTestData(31, "two_fifty");
		String twoFiveHundered			= Utils.getRestApiTestData(31, "two_five_hundered");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String typeMonths				= Utils.getRestApiTestData(31, "type_months");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "cashback")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;
		String typeNeft					= Utils.getRestApiTestData(32, "type_neft");
		String paymentType				= Utils.getRestApiTestData(32, "payment_type");
		String passcode					= Utils.getRestApiTestData(32, "password");
		String paymentMethodID			= Utils.getRestApiTestData(32, "payment_method_id");
		String accountHolderName		= Utils.getRestApiTestData(32, "account_holder_name");
		String bankName					= Utils.getRestApiTestData(32, "bank_name");
		String branch					= Utils.getRestApiTestData(32, "branch");
		String accountNumber			= Utils.getRestApiTestData(32, "account_number");
		String ifscCode					= Utils.getRestApiTestData(32, "ifsc_code");
		String orderValue				= Utils.getRestApiTestData(31, "ten_thousand");
		String commision				= Utils.getRestApiTestData(31, "five_thousand");
		String cashback					= Utils.getRestApiTestData(31, "two_five_hundered");

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
		
		signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, emailChild, password, mobileNumberChild, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int useridChild = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userIDChild = Integer.toString(useridChild);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, useridChild, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(emailChild);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userIDChild, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userIDChild, twoFiveHundered, zero, twoFiveHundered, zero, zero, twoFiveHundered, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "1", typeRewards, userIDChild, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 2);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		self = baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "referrals")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, twoFifty, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, twoFifty, zero, twoFifty, zero, twoFifty, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "1", typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 2);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, twoFifty, twoFifty, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "1", typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 2);
		
		objAdminCommonFunctions.createCashout(email);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, twoFifty, twoFifty, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "1", typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 2);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=14)
	public void validateChildUserCashbackConfirmStatusIncludeParameterCashbackMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		NEFTPost objNEFTPost = new NEFTPost(logger);
		UserLogin objUserLogin = new UserLogin(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb");
		String typeCashback				= Utils.getRestApiTestData(31, "type_my_earnings_cashback");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String twoFifty					= Utils.getRestApiTestData(31, "two_fifty");
		String twoFiveHundered			= Utils.getRestApiTestData(31, "two_five_hundered");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String typeMonths				= Utils.getRestApiTestData(31, "type_months");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "cashback")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;
		String typeNeft					= Utils.getRestApiTestData(32, "type_neft");
		String paymentType				= Utils.getRestApiTestData(32, "payment_type");
		String passcode					= Utils.getRestApiTestData(32, "password");
		String paymentMethodID			= Utils.getRestApiTestData(32, "payment_method_id");
		String accountHolderName		= Utils.getRestApiTestData(32, "account_holder_name");
		String bankName					= Utils.getRestApiTestData(32, "bank_name");
		String branch					= Utils.getRestApiTestData(32, "branch");
		String accountNumber			= Utils.getRestApiTestData(32, "account_number");
		String ifscCode					= Utils.getRestApiTestData(32, "ifsc_code");
		String orderValue				= Utils.getRestApiTestData(31, "ten_thousand");
		String commision				= Utils.getRestApiTestData(31, "five_thousand");
		String cashback					= Utils.getRestApiTestData(31, "two_five_hundered");

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
		
		signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, emailChild, password, mobileNumberChild, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int useridChild = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userIDChild = Integer.toString(useridChild);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, useridChild, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(emailChild);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userIDChild, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userIDChild, twoFiveHundered, zero, twoFiveHundered, zero, zero, twoFiveHundered, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		self = baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "referrals")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, twoFifty, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, twoFifty, zero, twoFifty, zero, twoFifty, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, twoFifty, twoFifty, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objAdminCommonFunctions.createCashout(email);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, twoFifty, twoFifty, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=15)
	public void validateChildUserCashbackConfirmStatusIncludeParameterRewardsMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		NEFTPost objNEFTPost = new NEFTPost(logger);
		UserLogin objUserLogin = new UserLogin(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_rw");
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String twoFifty					= Utils.getRestApiTestData(31, "two_fifty");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String typeMonths				= Utils.getRestApiTestData(31, "type_months");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "cashback")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;
		String typeNeft					= Utils.getRestApiTestData(32, "type_neft");
		String paymentType				= Utils.getRestApiTestData(32, "payment_type");
		String passcode					= Utils.getRestApiTestData(32, "password");
		String paymentMethodID			= Utils.getRestApiTestData(32, "payment_method_id");
		String accountHolderName		= Utils.getRestApiTestData(32, "account_holder_name");
		String bankName					= Utils.getRestApiTestData(32, "bank_name");
		String branch					= Utils.getRestApiTestData(32, "branch");
		String accountNumber			= Utils.getRestApiTestData(32, "account_number");
		String ifscCode					= Utils.getRestApiTestData(32, "ifsc_code");
		String orderValue				= Utils.getRestApiTestData(31, "ten_thousand");
		String commision				= Utils.getRestApiTestData(31, "five_thousand");
		String cashback					= Utils.getRestApiTestData(31, "two_five_hundered");

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
		
		signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, emailChild, password, mobileNumberChild, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int useridChild = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userIDChild = Integer.toString(useridChild);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, useridChild, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(emailChild);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userIDChild, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeRewards, userIDChild, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		self = baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "referrals")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, twoFifty, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objAdminCommonFunctions.createCashout(email);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=16)
	public void validateChildUserCashbackCancelStatusIncludeParameterCashbackRewardsMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		UserLogin objUserLogin = new UserLogin(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb_rw");
		String typeCashback				= Utils.getRestApiTestData(31, "type_my_earnings_cashback");
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String orderValue				= Utils.getRestApiTestData(31, "ten_thousand");
		String commision				= Utils.getRestApiTestData(31, "five_thousand");
		String cashback					= Utils.getRestApiTestData(31, "two_five_hundered");

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
		
		signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, emailChild, password, mobileNumberChild, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int useridChild = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userIDChild = Integer.toString(useridChild);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, useridChild, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(emailChild);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userIDChild, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userIDChild, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "1", typeRewards, userIDChild, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 2);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "1", typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 2);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=17)
	public void validateChildUserCashbackCancelStatusIncludeParameterCashbackMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		UserLogin objUserLogin = new UserLogin(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb");
		String typeCashback				= Utils.getRestApiTestData(31, "type_my_earnings_cashback");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String orderValue				= Utils.getRestApiTestData(31, "ten_thousand");
		String commision				= Utils.getRestApiTestData(31, "five_thousand");
		String cashback					= Utils.getRestApiTestData(31, "two_five_hundered");

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
		
		signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, emailChild, password, mobileNumberChild, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int useridChild = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userIDChild = Integer.toString(useridChild);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, useridChild, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(emailChild);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userIDChild, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userIDChild, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=18)
	public void validateChildUserCashbackCancelStatusIncludeParameterRewardsMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		UserLogin objUserLogin = new UserLogin(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_rw");
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String orderValue				= Utils.getRestApiTestData(31, "ten_thousand");
		String commision				= Utils.getRestApiTestData(31, "five_thousand");
		String cashback					= Utils.getRestApiTestData(31, "two_five_hundered");

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
		
		signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, emailChild, password, mobileNumberChild, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int useridChild = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userIDChild = Integer.toString(useridChild);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, useridChild, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(emailChild);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userIDChild, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeRewards, userIDChild, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=19)
	public void validateChildUserRewardsConfirmStatusIncludeParameterCashbackRewardsMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		NEFTPost objNEFTPost = new NEFTPost(logger);
		UserLogin objUserLogin = new UserLogin(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb_rw");
		String typeCashback				= Utils.getRestApiTestData(31, "type_my_earnings_cashback");
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String twoFifty					= Utils.getRestApiTestData(31, "two_fifty");
		String twoFiveHundered			= Utils.getRestApiTestData(31, "two_five_hundered");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String cashbackType				= Utils.getRestApiTestData(31, "rewards");
		String typeMonths				= Utils.getRestApiTestData(31, "type_months");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "rewards")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;
		String typeNeft					= Utils.getRestApiTestData(32, "type_neft");
		String paymentType				= Utils.getRestApiTestData(32, "payment_type");
		String passcode					= Utils.getRestApiTestData(32, "password");
		String paymentMethodID			= Utils.getRestApiTestData(32, "payment_method_id");
		String accountHolderName		= Utils.getRestApiTestData(32, "account_holder_name");
		String bankName					= Utils.getRestApiTestData(32, "bank_name");
		String branch					= Utils.getRestApiTestData(32, "branch");
		String accountNumber			= Utils.getRestApiTestData(32, "account_number");
		String ifscCode					= Utils.getRestApiTestData(32, "ifsc_code");
		String orderValue				= Utils.getRestApiTestData(31, "ten_thousand");
		String commision				= Utils.getRestApiTestData(31, "five_thousand");
		String cashback					= Utils.getRestApiTestData(31, "two_five_hundered");

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
		
		signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, emailChild, password, mobileNumberChild, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int useridChild = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userIDChild = Integer.toString(useridChild);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, useridChild, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(emailChild);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userIDChild, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userIDChild, zero, zero, zero, zero, zero, zero, currency, nullValue);

		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "1", typeRewards, userIDChild, twoFiveHundered, zero, twoFiveHundered, zero, twoFiveHundered, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 2);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		self = baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "referrals")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, twoFifty, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, twoFifty, zero, twoFifty, zero, twoFifty, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "1", typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 2);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, twoFifty, twoFifty, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "1", typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 2);
		
		objAdminCommonFunctions.createCashout(email);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, twoFifty, twoFifty, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "1", typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 2);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=20)
	public void validateChildUserRewardsConfirmStatusIncludeParameterCashbackMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		NEFTPost objNEFTPost = new NEFTPost(logger);
		UserLogin objUserLogin = new UserLogin(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb");
		String typeCashback				= Utils.getRestApiTestData(31, "type_my_earnings_cashback");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String twoFifty					= Utils.getRestApiTestData(31, "two_fifty");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String cashbackType				= Utils.getRestApiTestData(31, "rewards");
		String typeMonths				= Utils.getRestApiTestData(31, "type_months");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "rewards")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;
		String typeNeft					= Utils.getRestApiTestData(32, "type_neft");
		String paymentType				= Utils.getRestApiTestData(32, "payment_type");
		String passcode					= Utils.getRestApiTestData(32, "password");
		String paymentMethodID			= Utils.getRestApiTestData(32, "payment_method_id");
		String accountHolderName		= Utils.getRestApiTestData(32, "account_holder_name");
		String bankName					= Utils.getRestApiTestData(32, "bank_name");
		String branch					= Utils.getRestApiTestData(32, "branch");
		String accountNumber			= Utils.getRestApiTestData(32, "account_number");
		String ifscCode					= Utils.getRestApiTestData(32, "ifsc_code");
		String orderValue				= Utils.getRestApiTestData(31, "ten_thousand");
		String commision				= Utils.getRestApiTestData(31, "five_thousand");
		String cashback					= Utils.getRestApiTestData(31, "two_five_hundered");

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
		
		signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, emailChild, password, mobileNumberChild, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int useridChild = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userIDChild = Integer.toString(useridChild);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, useridChild, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(emailChild);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userIDChild, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userIDChild, zero, zero, zero, zero, zero, zero, currency, nullValue);

		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		self = baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "referrals")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, twoFifty, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, twoFifty, zero, twoFifty, zero, twoFifty, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, twoFifty, twoFifty, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objAdminCommonFunctions.createCashout(email);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, twoFifty, twoFifty, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=21)
	public void validateChildUserRewardsConfirmStatusIncludeParameterRewardsMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		NEFTPost objNEFTPost = new NEFTPost(logger);
		UserLogin objUserLogin = new UserLogin(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_rw");
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String twoFifty					= Utils.getRestApiTestData(31, "two_fifty");
		String twoFiveHundered			= Utils.getRestApiTestData(31, "two_five_hundered");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String cashbackType				= Utils.getRestApiTestData(31, "rewards");
		String typeMonths				= Utils.getRestApiTestData(31, "type_months");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "rewards")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;
		String typeNeft					= Utils.getRestApiTestData(32, "type_neft");
		String paymentType				= Utils.getRestApiTestData(32, "payment_type");
		String passcode					= Utils.getRestApiTestData(32, "password");
		String paymentMethodID			= Utils.getRestApiTestData(32, "payment_method_id");
		String accountHolderName		= Utils.getRestApiTestData(32, "account_holder_name");
		String bankName					= Utils.getRestApiTestData(32, "bank_name");
		String branch					= Utils.getRestApiTestData(32, "branch");
		String accountNumber			= Utils.getRestApiTestData(32, "account_number");
		String ifscCode					= Utils.getRestApiTestData(32, "ifsc_code");
		String orderValue				= Utils.getRestApiTestData(31, "ten_thousand");
		String commision				= Utils.getRestApiTestData(31, "five_thousand");
		String cashback					= Utils.getRestApiTestData(31, "two_five_hundered");

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
		
		signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, emailChild, password, mobileNumberChild, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int useridChild = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userIDChild = Integer.toString(useridChild);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, useridChild, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(emailChild);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userIDChild, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeRewards, userIDChild, twoFiveHundered, zero, twoFiveHundered, zero, twoFiveHundered, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		self = baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "referrals")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, twoFifty, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objAdminCommonFunctions.createCashout(email);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=22)
	public void validateChildUserRewardsCancelStatusIncludeParameterCashbackRewardsMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		UserLogin objUserLogin = new UserLogin(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb_rw");
		String typeCashback				= Utils.getRestApiTestData(31, "type_my_earnings_cashback");
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String cashbackType				= Utils.getRestApiTestData(31, "rewards");
		String typeMonths				= Utils.getRestApiTestData(31, "type_months");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "rewards")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;
		String orderValue				= Utils.getRestApiTestData(31, "ten_thousand");
		String commision				= Utils.getRestApiTestData(31, "five_thousand");
		String cashback					= Utils.getRestApiTestData(31, "two_five_hundered");

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
		
		signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, emailChild, password, mobileNumberChild, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int useridChild = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userIDChild = Integer.toString(useridChild);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, useridChild, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(emailChild);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userIDChild, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userIDChild, zero, zero, zero, zero, zero, zero, currency, nullValue);

		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "1", typeRewards, userIDChild, zero, zero, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 2);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		self = baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "referrals")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "1", typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 2);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=23)
	public void validateChildUserRewardsCancelStatusIncludeParameterCashbackMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		UserLogin objUserLogin = new UserLogin(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb");
		String typeCashback				= Utils.getRestApiTestData(31, "type_my_earnings_cashback");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String cashbackType				= Utils.getRestApiTestData(31, "rewards");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String orderValue				= Utils.getRestApiTestData(31, "ten_thousand");
		String commision				= Utils.getRestApiTestData(31, "five_thousand");
		String cashback					= Utils.getRestApiTestData(31, "two_five_hundered");

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
		
		signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, emailChild, password, mobileNumberChild, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int useridChild = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userIDChild = Integer.toString(useridChild);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, useridChild, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(emailChild);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userIDChild, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userIDChild, zero, zero, zero, zero, zero, zero, currency, nullValue);

		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=24)
	public void validateChildUserRewardsCancelStatusIncludeParameterRewardsMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		UserLogin objUserLogin = new UserLogin(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_rw");
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String cashbackType				= Utils.getRestApiTestData(31, "rewards");
		String typeMonths				= Utils.getRestApiTestData(31, "type_months");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "rewards")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;
		String orderValue				= Utils.getRestApiTestData(31, "ten_thousand");
		String commision				= Utils.getRestApiTestData(31, "five_thousand");
		String cashback					= Utils.getRestApiTestData(31, "two_five_hundered");

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
		
		signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, emailChild, password, mobileNumberChild, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int useridChild = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userIDChild = Integer.toString(useridChild);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, useridChild, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(emailChild);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userIDChild, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndpoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeRewards, userIDChild, zero, zero, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		self = baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "referrals")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=25)
	public void validateCashbackConfirmStatusNoIncludeParameterMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		NEFTPost objNEFTPost = new NEFTPost(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(8, "emptyValue");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
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
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 0);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 0);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 0);
		
		objAdminCommonFunctions.createCashout(email);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 0);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=26)
	public void validateCashbackCancelStatusNoIncludeParameterMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(8, "emptyValue");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String commision				= Utils.getRestApiTestData(31, "five_hundered");
		String cashback					= Utils.getRestApiTestData(31, "two_fifty");

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
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 0);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 0);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=27)
	public void validateRewardsConfirmStatusNoIncludeParameterMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		NEFTPost objNEFTPost = new NEFTPost(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(8, "emptyValue");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String cashbackType				= Utils.getRestApiTestData(31, "rewards");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String typeNeft					= Utils.getRestApiTestData(32, "type_neft");
		String paymentType				= Utils.getRestApiTestData(32, "payment_type_rw");
		String passcode					= Utils.getRestApiTestData(32, "password");
		String paymentMethodID			= Utils.getRestApiTestData(32, "payment_method_id");
		String accountHolderName		= Utils.getRestApiTestData(32, "account_holder_name");
		String bankName					= Utils.getRestApiTestData(32, "bank_name");
		String branch					= Utils.getRestApiTestData(32, "branch");
		String accountNumber			= Utils.getRestApiTestData(32, "account_number");
		String ifscCode					= Utils.getRestApiTestData(32, "ifsc_code");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String commision				= Utils.getRestApiTestData(31, "five_hundered");
		String rewards					= Utils.getRestApiTestData(31, "two_fifty");

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
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, rewards);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 0);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 0);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 0);
		
		objAdminCommonFunctions.createCashout(email);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 0);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=28)
	public void validateRewardsCancelStatusNoIncludeParameterMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(8, "emptyValue");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String cashbackType				= Utils.getRestApiTestData(31, "rewards");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String commision				= Utils.getRestApiTestData(31, "five_hundered");
		String rewards					= Utils.getRestApiTestData(31, "two_fifty");

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
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, rewards);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 0);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 0);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=29)
	public void validateChildUserCashbackConfirmStatusNoIncludeParameterMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		NEFTPost objNEFTPost = new NEFTPost(logger);
		UserLogin objUserLogin = new UserLogin(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(8, "emptyValue");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String twoFifty					= Utils.getRestApiTestData(31, "two_fifty");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String typeMonths				= Utils.getRestApiTestData(31, "type_months");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "cashback")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;
		String typeNeft					= Utils.getRestApiTestData(32, "type_neft");
		String paymentType				= Utils.getRestApiTestData(32, "payment_type");
		String passcode					= Utils.getRestApiTestData(32, "password");
		String paymentMethodID			= Utils.getRestApiTestData(32, "payment_method_id");
		String accountHolderName		= Utils.getRestApiTestData(32, "account_holder_name");
		String bankName					= Utils.getRestApiTestData(32, "bank_name");
		String branch					= Utils.getRestApiTestData(32, "branch");
		String accountNumber			= Utils.getRestApiTestData(32, "account_number");
		String ifscCode					= Utils.getRestApiTestData(32, "ifsc_code");
		String orderValue				= Utils.getRestApiTestData(31, "ten_thousand");
		String commision				= Utils.getRestApiTestData(31, "five_thousand");
		String cashback					= Utils.getRestApiTestData(31, "two_five_hundered");

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
		
		signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, emailChild, password, mobileNumberChild, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int useridChild = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userIDChild = Integer.toString(useridChild);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, useridChild, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(emailChild);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userIDChild, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 0);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		self = baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "referrals")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, twoFifty, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 0);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 0);
		
		objAdminCommonFunctions.createCashout(email);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 0);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=30)
	public void validateChildUserCashbackCancelStatusNoIncludeParameterMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		UserLogin objUserLogin = new UserLogin(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(8, "emptyValue");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String orderValue				= Utils.getRestApiTestData(31, "ten_thousand");
		String commision				= Utils.getRestApiTestData(31, "five_thousand");
		String cashback					= Utils.getRestApiTestData(31, "two_five_hundered");

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
		
		signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, emailChild, password, mobileNumberChild, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int useridChild = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userIDChild = Integer.toString(useridChild);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, useridChild, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(emailChild);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userIDChild, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 0);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 0);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=31)
	public void validateChildUserRewardsConfirmStatusNoIncludeParameterMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		NEFTPost objNEFTPost = new NEFTPost(logger);
		UserLogin objUserLogin = new UserLogin(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(8, "emptyValue");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String twoFifty					= Utils.getRestApiTestData(31, "two_fifty");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String cashbackType				= Utils.getRestApiTestData(31, "rewards");
		String typeMonths				= Utils.getRestApiTestData(31, "type_months");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "rewards")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;
		String typeNeft					= Utils.getRestApiTestData(32, "type_neft");
		String paymentType				= Utils.getRestApiTestData(32, "payment_type");
		String passcode					= Utils.getRestApiTestData(32, "password");
		String paymentMethodID			= Utils.getRestApiTestData(32, "payment_method_id");
		String accountHolderName		= Utils.getRestApiTestData(32, "account_holder_name");
		String bankName					= Utils.getRestApiTestData(32, "bank_name");
		String branch					= Utils.getRestApiTestData(32, "branch");
		String accountNumber			= Utils.getRestApiTestData(32, "account_number");
		String ifscCode					= Utils.getRestApiTestData(32, "ifsc_code");
		String orderValue				= Utils.getRestApiTestData(31, "ten_thousand");
		String commision				= Utils.getRestApiTestData(31, "five_thousand");
		String cashback					= Utils.getRestApiTestData(31, "two_five_hundered");

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
		
		signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, emailChild, password, mobileNumberChild, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int useridChild = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userIDChild = Integer.toString(useridChild);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, useridChild, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(emailChild);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userIDChild, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 0);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		self = baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "referrals")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, twoFifty, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 0);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 0);
		
		objAdminCommonFunctions.createCashout(email);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 0);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=32)
	public void validateChildUserRewardsCancelStatusNoIncludeParameterMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		UserLogin objUserLogin = new UserLogin(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(8, "emptyValue");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String cashbackType				= Utils.getRestApiTestData(31, "rewards");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String orderValue				= Utils.getRestApiTestData(31, "ten_thousand");
		String commision				= Utils.getRestApiTestData(31, "five_thousand");
		String cashback					= Utils.getRestApiTestData(31, "two_five_hundered");

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
		
		signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, emailChild, password, mobileNumberChild, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int useridChild = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userIDChild = Integer.toString(useridChild);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, useridChild, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(emailChild);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userIDChild, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 0);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsReferrals.validateIncludeQueryParameterCountResponseMyEarningsReferralsEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, 0);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=33)
	public void validateCashbackConfirmStatusMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		NEFTPost objNEFTPost = new NEFTPost(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String queryParameter			= "/"+month+"/"+year;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
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
		String totalrecords				= Utils.getRestApiTestData(31, "total_records_zero");
		String pagenumber				= Utils.getRestApiTestData(31, "page_number");
		String pagesize					= Utils.getRestApiTestData(31, "page_size");

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
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objAdminCommonFunctions.createCashout(email);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=34)
	public void validateCashbackCancelStatusMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String queryParameter			= "/"+month+"/"+year;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String commision				= Utils.getRestApiTestData(31, "five_hundered");
		String cashback					= Utils.getRestApiTestData(31, "two_fifty");
		String totalrecords				= Utils.getRestApiTestData(31, "total_records_zero");
		String pagenumber				= Utils.getRestApiTestData(31, "page_number");
		String pagesize					= Utils.getRestApiTestData(31, "page_size");

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
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=35)
	public void validateRewardsConfirmStatusMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		NEFTPost objNEFTPost = new NEFTPost(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String queryParameter			= "/"+month+"/"+year;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String cashbackType				= Utils.getRestApiTestData(31, "rewards");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String typeNeft					= Utils.getRestApiTestData(32, "type_neft");
		String paymentType				= Utils.getRestApiTestData(32, "payment_type_rw");
		String passcode					= Utils.getRestApiTestData(32, "password");
		String paymentMethodID			= Utils.getRestApiTestData(32, "payment_method_id");
		String accountHolderName		= Utils.getRestApiTestData(32, "account_holder_name");
		String bankName					= Utils.getRestApiTestData(32, "bank_name");
		String branch					= Utils.getRestApiTestData(32, "branch");
		String accountNumber			= Utils.getRestApiTestData(32, "account_number");
		String ifscCode					= Utils.getRestApiTestData(32, "ifsc_code");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String commision				= Utils.getRestApiTestData(31, "five_hundered");
		String rewards					= Utils.getRestApiTestData(31, "two_fifty");
		String totalrecords				= Utils.getRestApiTestData(31, "total_records_zero");
		String pagenumber				= Utils.getRestApiTestData(31, "page_number");
		String pagesize					= Utils.getRestApiTestData(31, "page_size");

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
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, rewards);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objAdminCommonFunctions.createCashout(email);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=36)
	public void validateRewardsCancelStatusMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String queryParameter			= "/"+month+"/"+year;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String cashbackType				= Utils.getRestApiTestData(31, "rewards");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String commision				= Utils.getRestApiTestData(31, "five_hundered");
		String rewards					= Utils.getRestApiTestData(31, "two_fifty");
		String totalrecords				= Utils.getRestApiTestData(31, "total_records_zero");
		String pagenumber				= Utils.getRestApiTestData(31, "page_number");
		String pagesize					= Utils.getRestApiTestData(31, "page_size");

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
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, rewards);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=37)
	public void validateChildUserCashbackConfirmStatusMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		NEFTPost objNEFTPost = new NEFTPost(logger);
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
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String queryParameter			= "/"+month+"/"+year;
		String twoFifty					= Utils.getRestApiTestData(31, "two_fifty");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "referrals")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;
		String typeNeft					= Utils.getRestApiTestData(32, "type_neft");
		String paymentType				= Utils.getRestApiTestData(32, "payment_type");
		String passcode					= Utils.getRestApiTestData(32, "password");
		String paymentMethodID			= Utils.getRestApiTestData(32, "payment_method_id");
		String accountHolderName		= Utils.getRestApiTestData(32, "account_holder_name");
		String bankName					= Utils.getRestApiTestData(32, "bank_name");
		String branch					= Utils.getRestApiTestData(32, "branch");
		String accountNumber			= Utils.getRestApiTestData(32, "account_number");
		String ifscCode					= Utils.getRestApiTestData(32, "ifsc_code");
		String orderValue				= Utils.getRestApiTestData(31, "ten_thousand");
		String commision				= Utils.getRestApiTestData(31, "five_thousand");
		String cashback					= Utils.getRestApiTestData(31, "two_five_hundered");
		String totalrecords				= Utils.getRestApiTestData(31, "total_records");
		String pagenumber				= Utils.getRestApiTestData(31, "page_number");
		String pagesize					= Utils.getRestApiTestData(31, "page_size");
		String selfUrl					= self;
		String firstUrl					= self+Utils.getRestApiTestData(31, "pagenumber")+"&"+Utils.getRestApiTestData(31, "pagesize");
		String lastUrl					= self+Utils.getRestApiTestData(31, "pagenumber")+"&"+Utils.getRestApiTestData(31, "pagesize");
		String prevUrl					= Utils.getRestApiTestData(31, "null");
		String nextUrl					= Utils.getRestApiTestData(31, "null");
		String cashbackcreateddate		= Utils.currentDateFormat();
		String confirmStatus			= Utils.getRestApiTestData(31, "confirm");
		String requestedStatus			= Utils.getRestApiTestData(31, "request");
		String paidStatus				= Utils.getRestApiTestData(31, "paid");
		String typeCashback				= Utils.getRestApiTestData(31, "type_my_earnings_cashback");

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
		
		signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, emailChild, password, mobileNumberChild, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int useridChild = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, useridChild, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(emailChild);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", pagenumber, pagesize, nullValue);
		
		String cashbackID = objAdminCommonFunctions.getCashbackID(email, "2");
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		self = baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "referrals")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, totalrecords, pagenumber, pagesize, selfUrl, firstUrl, lastUrl, prevUrl, nextUrl, typeCashback, cashbackID, cashbackcreateddate, fullName, twoFifty, confirmStatus, currency);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, totalrecords, pagenumber, pagesize, selfUrl, firstUrl, lastUrl, prevUrl, nextUrl, typeCashback, cashbackID, cashbackcreateddate, fullName, twoFifty, requestedStatus, currency);
		
		objAdminCommonFunctions.createCashout(email);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, totalrecords, pagenumber, pagesize, selfUrl, firstUrl, lastUrl, prevUrl, nextUrl, typeCashback, cashbackID, cashbackcreateddate, fullName, twoFifty, paidStatus, currency);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=38)
	public void validateChildUserCashbackCancelStatusMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
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
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String queryParameter			= "/"+month+"/"+year;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String orderValue				= Utils.getRestApiTestData(31, "ten_thousand");
		String commision				= Utils.getRestApiTestData(31, "five_thousand");
		String cashback					= Utils.getRestApiTestData(31, "two_five_hundered");
		String totalrecords				= Utils.getRestApiTestData(31, "total_records");
		String pagenumber				= Utils.getRestApiTestData(31, "page_number");
		String pagesize					= Utils.getRestApiTestData(31, "page_size");

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
		
		signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, emailChild, password, mobileNumberChild, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int useridChild = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, useridChild, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(emailChild);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", pagenumber, pagesize, nullValue);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=39)
	public void validateChildUserRewardsConfirmStatusMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		NEFTPost objNEFTPost = new NEFTPost(logger);
		UserLogin objUserLogin = new UserLogin(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String queryParameter			= "/"+month+"/"+year;
		String twoFifty					= Utils.getRestApiTestData(31, "two_fifty");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String cashbackType				= Utils.getRestApiTestData(31, "rewards");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "referrals")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;
		String typeNeft					= Utils.getRestApiTestData(32, "type_neft");
		String paymentType				= Utils.getRestApiTestData(32, "payment_type");
		String passcode					= Utils.getRestApiTestData(32, "password");
		String paymentMethodID			= Utils.getRestApiTestData(32, "payment_method_id");
		String accountHolderName		= Utils.getRestApiTestData(32, "account_holder_name");
		String bankName					= Utils.getRestApiTestData(32, "bank_name");
		String branch					= Utils.getRestApiTestData(32, "branch");
		String accountNumber			= Utils.getRestApiTestData(32, "account_number");
		String ifscCode					= Utils.getRestApiTestData(32, "ifsc_code");
		String orderValue				= Utils.getRestApiTestData(31, "ten_thousand");
		String commision				= Utils.getRestApiTestData(31, "five_thousand");
		String cashback					= Utils.getRestApiTestData(31, "two_five_hundered");
		String totalrecords				= Utils.getRestApiTestData(31, "total_records");
		String pagenumber				= Utils.getRestApiTestData(31, "page_number");
		String pagesize					= Utils.getRestApiTestData(31, "page_size");
		String selfUrl					= self;
		String firstUrl					= self+Utils.getRestApiTestData(31, "pagenumber")+"&"+Utils.getRestApiTestData(31, "pagesize");
		String lastUrl					= self+Utils.getRestApiTestData(31, "pagenumber")+"&"+Utils.getRestApiTestData(31, "pagesize");
		String prevUrl					= Utils.getRestApiTestData(31, "null");
		String nextUrl					= Utils.getRestApiTestData(31, "null");
		String cashbackcreateddate		= Utils.currentDateFormat();
		String confirmStatus			= Utils.getRestApiTestData(31, "confirm");
		String requestedStatus			= Utils.getRestApiTestData(31, "request");
		String paidStatus				= Utils.getRestApiTestData(31, "paid");
		String typeCashback				= Utils.getRestApiTestData(31, "type_my_earnings_cashback");

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
		
		signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, emailChild, password, mobileNumberChild, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int useridChild = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, useridChild, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(emailChild);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, "0", pagenumber, pagesize, nullValue);
		
		String cashbackID = objAdminCommonFunctions.getCashbackID(email, "2");
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		self = baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "referrals")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;

		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, totalrecords, pagenumber, pagesize, selfUrl, firstUrl, lastUrl, prevUrl, nextUrl, typeCashback, cashbackID, cashbackcreateddate, fullName, twoFifty, confirmStatus, currency);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, totalrecords, pagenumber, pagesize, selfUrl, firstUrl, lastUrl, prevUrl, nextUrl, typeCashback, cashbackID, cashbackcreateddate, fullName, twoFifty, requestedStatus, currency);
		
		objAdminCommonFunctions.createCashout(email);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, totalrecords, pagenumber, pagesize, selfUrl, firstUrl, lastUrl, prevUrl, nextUrl, typeCashback, cashbackID, cashbackcreateddate, fullName, twoFifty, paidStatus, currency);
		
		objSoftAssertion.assertAll();
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=40)
	public void validateChildUserRewardsCancelStatusMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		UserLogin objUserLogin = new UserLogin(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String queryParameter			= "/"+month+"/"+year;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String cashbackType				= Utils.getRestApiTestData(31, "rewards");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String orderValue				= Utils.getRestApiTestData(31, "ten_thousand");
		String commision				= Utils.getRestApiTestData(31, "five_thousand");
		String cashback					= Utils.getRestApiTestData(31, "two_five_hundered");
		String totalrecords				= Utils.getRestApiTestData(31, "total_records_zero");
		String pagenumber				= Utils.getRestApiTestData(31, "page_number");
		String pagesize					= Utils.getRestApiTestData(31, "page_size");

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
		
		signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, emailChild, password, mobileNumberChild, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int useridChild = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, useridChild, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(emailChild);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=41)
	public void validateAddCashbackBonusNoIncludeParameterMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		MyEarnings objMyEarnings = new MyEarnings(logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		NEFTPost objNEFTPost = new NEFTPost(logger);
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String typeNeft					= Utils.getRestApiTestData(32, "type_neft");
		String paymentType				= Utils.getRestApiTestData(32, "payment_type");
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String passcode					= Utils.getRestApiTestData(32, "password");
		String paymentMethodID			= Utils.getRestApiTestData(32, "payment_method_id");
		String accountHolderName		= Utils.getRestApiTestData(32, "account_holder_name");
		String bankName					= Utils.getRestApiTestData(32, "bank_name");
		String branch					= Utils.getRestApiTestData(32, "branch");
		String accountNumber			= Utils.getRestApiTestData(32, "account_number");
		String ifscCode					= Utils.getRestApiTestData(32, "ifsc_code");
		String cashback					= Utils.getRestApiTestData(31, "two_fifty");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
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
		
		String userID = Integer.toString(userid);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		objMyEarnings.addBonus(email, cashback, cashbackType);
		
		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);

		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);

		objAdminCommonFunctions.createCashoutAfterLoggingin(adminURL, adminDropDown, adminUsername, adminPassword, email);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);

		objSoftAssertion.assertAll();

	}

	@Test(priority=42)
	public void validateAddRewardsBonusNoIncludeParameterMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		MyEarnings objMyEarnings = new MyEarnings(logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		NEFTPost objNEFTPost = new NEFTPost(logger);
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String cashbackType				= Utils.getRestApiTestData(31, "rewards");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String typeNeft					= Utils.getRestApiTestData(32, "type_neft");
		String paymentType				= Utils.getRestApiTestData(32, "payment_type_rw");
		String passcode					= Utils.getRestApiTestData(32, "password");
		String paymentMethodID			= Utils.getRestApiTestData(32, "payment_method_id");
		String accountHolderName		= Utils.getRestApiTestData(32, "account_holder_name");
		String bankName					= Utils.getRestApiTestData(32, "bank_name");
		String branch					= Utils.getRestApiTestData(32, "branch");
		String accountNumber			= Utils.getRestApiTestData(32, "account_number");
		String ifscCode					= Utils.getRestApiTestData(32, "ifsc_code");
		String rewards					= Utils.getRestApiTestData(31, "two_fifty");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
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
		
		String userID = Integer.toString(userid);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		objMyEarnings.addBonus(email, rewards, cashbackType);
		
		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objAdminCommonFunctions.createCashoutAfterLoggingin(adminURL, adminDropDown, adminUsername, adminPassword, email);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=43)
	public void validateReferralSignUpBonusNoIncludeParameterMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {
		
		try {

			// Object creations
			SoftAssert objSoftAssertion = new SoftAssert();
			HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
			SignUpOTP objSignUpOTP = new SignUpOTP(logger);
			SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
			AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
			MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
			NEFTPost objNEFTPost = new NEFTPost(logger);
			
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
			String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
			String type						= Utils.getRestApiTestData(10, "type");
			String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
			String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
			String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
			String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
			String zero						= Utils.getRestApiTestData(31, "zero");
			String currency					= ConfigurationSetup.CURRENCYTYPE;
			String frontEndURl				= ConfigurationSetup.FRONTENDURL;
			String typeNeft					= Utils.getRestApiTestData(32, "type_neft");
			String paymentType				= Utils.getRestApiTestData(32, "payment_type");
			String passcode					= Utils.getRestApiTestData(32, "password");
			String paymentMethodID			= Utils.getRestApiTestData(32, "payment_method_id");
			String accountHolderName		= Utils.getRestApiTestData(32, "account_holder_name");
			String bankName					= Utils.getRestApiTestData(32, "bank_name");
			String branch					= Utils.getRestApiTestData(32, "branch");
			String accountNumber			= Utils.getRestApiTestData(32, "account_number");
			String ifscCode					= Utils.getRestApiTestData(32, "ifsc_code");
			String cashback					= Utils.getRestApiTestData(31, "two_fifty");
			String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
			String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");

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

			objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
			
			/*objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);*/
			
			objAdminCommonFunctions.setReferralSignUpBonus(frontEndURl, cashback);
			
			String emailChild = Utils.generateNewEmail();
			
			String mobileNumberChild = Utils.generateMobileNumber(10);
			
			helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
			
			accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
			
			signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumberChild, statusCode201);

			guid = (String) signUpOTPResponse[1] ;

			signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumberChild);
			
			signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, emailChild, password, mobileNumberChild, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
			
			userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
			
			userID = Integer.toString(userid);
			
			accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
			
			objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);

			accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
			
			Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
			
			objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
			
			objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
			
			objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
			
			objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
			
			objAdminCommonFunctions.createCashout(emailChild);
			
			objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
			
			objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
			
			objAdminCommonFunctions.updateSignUpReferralBonusBonus(frontEndURl, zero, zero);

			objSoftAssertion.assertAll();
			
		} catch (Exception e) {

			// Object creations
			AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
			
			// Assigning values
			String zero						= Utils.getRestApiTestData(31, "zero");
			String frontEndURl				= ConfigurationSetup.FRONTENDURL;

			objAdminCommonFunctions.updateSignUpReferralBonusBonus(frontEndURl, zero, zero);
			
		}

	}

	@Test(priority=44)
	public void validateUserSignUpBonusNoIncludeParameterMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		NEFTPost objNEFTPost = new NEFTPost(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String typeNeft					= Utils.getRestApiTestData(32, "type_neft");
		String paymentType				= Utils.getRestApiTestData(32, "payment_type");
		String passcode					= Utils.getRestApiTestData(32, "password");
		String paymentMethodID			= Utils.getRestApiTestData(32, "payment_method_id");
		String accountHolderName		= Utils.getRestApiTestData(32, "account_holder_name");
		String bankName					= Utils.getRestApiTestData(32, "bank_name");
		String branch					= Utils.getRestApiTestData(32, "branch");
		String accountNumber			= Utils.getRestApiTestData(32, "account_number");
		String ifscCode					= Utils.getRestApiTestData(32, "ifsc_code");
		String cashback					= Utils.getRestApiTestData(31, "two_fifty");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");

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

		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setUserSignUpBonus(email, cashback);
		
		/*objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);*/
		
		String emailChild = Utils.generateNewEmail();
		
		String mobileNumberChild = Utils.generateMobileNumber(10);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumberChild, statusCode201);

		guid = (String) signUpOTPResponse[1] ;

		signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumberChild);
		
		signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, emailChild, password, mobileNumberChild, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		userID = Integer.toString(userid);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);

		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objAdminCommonFunctions.createCashout(emailChild);
		
		objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=45)
	public void validateSignUpBonusNoIncludeParameterMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {
		
		try {

			// Object creations
			SoftAssert objSoftAssertion = new SoftAssert();
			HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
			SignUpOTP objSignUpOTP = new SignUpOTP(logger);
			SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
			AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
			MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
			NEFTPost objNEFTPost = new NEFTPost(logger);
			
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
			String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
			String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
			String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
			String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
			String zero						= Utils.getRestApiTestData(31, "zero");
			String currency					= ConfigurationSetup.CURRENCYTYPE;
			String frontEndURl				= ConfigurationSetup.FRONTENDURL;
			String typeNeft					= Utils.getRestApiTestData(32, "type_neft");
			String paymentType				= Utils.getRestApiTestData(32, "payment_type");
			String passcode					= Utils.getRestApiTestData(32, "password");
			String paymentMethodID			= Utils.getRestApiTestData(32, "payment_method_id");
			String accountHolderName		= Utils.getRestApiTestData(32, "account_holder_name");
			String bankName					= Utils.getRestApiTestData(32, "bank_name");
			String branch					= Utils.getRestApiTestData(32, "branch");
			String accountNumber			= Utils.getRestApiTestData(32, "account_number");
			String ifscCode					= Utils.getRestApiTestData(32, "ifsc_code");
			String cashback					= Utils.getRestApiTestData(31, "two_fifty");
			String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
			String typeReferral				= Utils.getRestApiTestData(31, "type_my_earnings_referral");

			objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
			
			/*objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);*/
			
			objAdminCommonFunctions.setSignUpBonus(frontEndURl, cashback);
			
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
			
			Response objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
			
			objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
			
			objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
			
			objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
			
			objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
			
			objAdminCommonFunctions.createCashout(email);
			
			objMyEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
			
			objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, objMyEarningsReferralsResponse, typeReferral, userID, zero, currency, nullValue);
			
			objAdminCommonFunctions.updateSignUpReferralBonusBonus(frontEndURl, zero, zero);

			objSoftAssertion.assertAll();
			
		} catch (Exception e) {

			// Object creations
			AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
			
			// Assigning values
			String zero						= Utils.getRestApiTestData(31, "zero");
			String frontEndURl				= ConfigurationSetup.FRONTENDURL;

			objAdminCommonFunctions.updateSignUpReferralBonusBonus(frontEndURl, zero, zero);
			
		}

	}

	@Test(priority=46)
	public void validateAddCashbackBonusMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		MyEarnings objMyEarnings = new MyEarnings(logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		NEFTPost objNEFTPost = new NEFTPost(logger);
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeNeft					= Utils.getRestApiTestData(32, "type_neft");
		String paymentType				= Utils.getRestApiTestData(32, "payment_type");
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String passcode					= Utils.getRestApiTestData(32, "password");
		String paymentMethodID			= Utils.getRestApiTestData(32, "payment_method_id");
		String accountHolderName		= Utils.getRestApiTestData(32, "account_holder_name");
		String bankName					= Utils.getRestApiTestData(32, "bank_name");
		String branch					= Utils.getRestApiTestData(32, "branch");
		String accountNumber			= Utils.getRestApiTestData(32, "account_number");
		String ifscCode					= Utils.getRestApiTestData(32, "ifsc_code");
		String cashback					= Utils.getRestApiTestData(31, "two_fifty");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String queryParameter			= "/"+month+"/"+year;
		String totalrecords				= Utils.getRestApiTestData(31, "total_records_zero");
		String pagenumber				= Utils.getRestApiTestData(31, "page_number");
		String pagesize					= Utils.getRestApiTestData(31, "page_size");
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
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		objMyEarnings.addBonus(email, cashback, cashbackType);
		
		Response myEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);

		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		myEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);

		objAdminCommonFunctions.createCashoutAfterLoggingin(adminURL, adminDropDown, adminUsername, adminPassword, email);
		
		myEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);

		objSoftAssertion.assertAll();

	}

	@Test(priority=47)
	public void validateAddRewardsBonusMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		MyEarnings objMyEarnings = new MyEarnings(logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		NEFTPost objNEFTPost = new NEFTPost(logger);
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String cashbackType				= Utils.getRestApiTestData(31, "rewards");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeNeft					= Utils.getRestApiTestData(32, "type_neft");
		String paymentType				= Utils.getRestApiTestData(32, "payment_type_rw");
		String passcode					= Utils.getRestApiTestData(32, "password");
		String paymentMethodID			= Utils.getRestApiTestData(32, "payment_method_id");
		String accountHolderName		= Utils.getRestApiTestData(32, "account_holder_name");
		String bankName					= Utils.getRestApiTestData(32, "bank_name");
		String branch					= Utils.getRestApiTestData(32, "branch");
		String accountNumber			= Utils.getRestApiTestData(32, "account_number");
		String ifscCode					= Utils.getRestApiTestData(32, "ifsc_code");
		String rewards					= Utils.getRestApiTestData(31, "two_fifty");
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String totalrecords				= Utils.getRestApiTestData(31, "total_records_zero");
		String pagenumber				= Utils.getRestApiTestData(31, "page_number");
		String pagesize					= Utils.getRestApiTestData(31, "page_size");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String queryParameter			= "/"+month+"/"+year;
		
		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		objMyEarnings.addBonus(email, rewards, cashbackType);
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);

		Response myEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		myEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objAdminCommonFunctions.createCashout(email);
		
		myEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=48)
	public void validateReferralSignUpBonusMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {
		
		try {

			// Object creations
			SoftAssert objSoftAssertion = new SoftAssert();
			HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
			SignUpOTP objSignUpOTP = new SignUpOTP(logger);
			SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
			AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
			MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
			NEFTPost objNEFTPost = new NEFTPost(logger);
			
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
			String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
			String type						= Utils.getRestApiTestData(10, "type");
			String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
			String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
			String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
			String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
			String frontEndURl				= ConfigurationSetup.FRONTENDURL;
			String typeNeft					= Utils.getRestApiTestData(32, "type_neft");
			String paymentType				= Utils.getRestApiTestData(32, "payment_type");
			String passcode					= Utils.getRestApiTestData(32, "password");
			String paymentMethodID			= Utils.getRestApiTestData(32, "payment_method_id");
			String accountHolderName		= Utils.getRestApiTestData(32, "account_holder_name");
			String bankName					= Utils.getRestApiTestData(32, "bank_name");
			String branch					= Utils.getRestApiTestData(32, "branch");
			String accountNumber			= Utils.getRestApiTestData(32, "account_number");
			String ifscCode					= Utils.getRestApiTestData(32, "ifsc_code");
			String cashback					= Utils.getRestApiTestData(31, "two_fifty");
			String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
			String month					= Utils.currentMonth();
			String year						= Utils.currentYear();
			String queryParameter			= "/"+month+"/"+year;
			String totalrecords				= Utils.getRestApiTestData(31, "total_records_zero");
			String pagenumber				= Utils.getRestApiTestData(31, "page_number");
			String pagesize					= Utils.getRestApiTestData(31, "page_size");
			String zero						= Utils.getRestApiTestData(31, "zero");

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

			objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
			
			/*objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);*/
			
			objAdminCommonFunctions.setReferralSignUpBonus(frontEndURl, cashback);
			
			String emailChild = Utils.generateNewEmail();
			
			String mobileNumberChild = Utils.generateMobileNumber(10);
			
			helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
			
			accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
			
			signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumberChild, statusCode201);

			guid = (String) signUpOTPResponse[1] ;

			signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumberChild);
			
			signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, emailChild, password, mobileNumberChild, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
			
			userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
			
			userID = Integer.toString(userid);
			
			accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
			
			objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);

			accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
			
			Response myEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
			
			objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);
			
			objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
			
			myEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
			
			objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);
			
			objAdminCommonFunctions.createCashout(emailChild);
			
			myEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
			
			objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);
			
			objAdminCommonFunctions.updateSignUpReferralBonusBonus(frontEndURl, zero, zero);

			objSoftAssertion.assertAll();
			
		} catch (Exception e) {

			// Object creations
			AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
			
			// Assigning values
			String zero						= Utils.getRestApiTestData(31, "zero");
			String frontEndURl				= ConfigurationSetup.FRONTENDURL;

			objAdminCommonFunctions.updateSignUpReferralBonusBonus(frontEndURl, zero, zero);
			
		}

	}

	@Test(priority=49)
	public void validateUserSignUpBonusMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		NEFTPost objNEFTPost = new NEFTPost(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeNeft					= Utils.getRestApiTestData(32, "type_neft");
		String paymentType				= Utils.getRestApiTestData(32, "payment_type");
		String passcode					= Utils.getRestApiTestData(32, "password");
		String paymentMethodID			= Utils.getRestApiTestData(32, "payment_method_id");
		String accountHolderName		= Utils.getRestApiTestData(32, "account_holder_name");
		String bankName					= Utils.getRestApiTestData(32, "bank_name");
		String branch					= Utils.getRestApiTestData(32, "branch");
		String accountNumber			= Utils.getRestApiTestData(32, "account_number");
		String ifscCode					= Utils.getRestApiTestData(32, "ifsc_code");
		String cashback					= Utils.getRestApiTestData(31, "two_fifty");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String queryParameter			= "/"+month+"/"+year;
		String totalrecords				= Utils.getRestApiTestData(31, "total_records_zero");
		String pagenumber				= Utils.getRestApiTestData(31, "page_number");
		String pagesize					= Utils.getRestApiTestData(31, "page_size");

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

		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.setUserSignUpBonus(email, cashback);
		
		/*objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);*/
		
		String emailChild = Utils.generateNewEmail();
		
		String mobileNumberChild = Utils.generateMobileNumber(10);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumberChild, statusCode201);

		guid = (String) signUpOTPResponse[1] ;

		signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumberChild);
		
		signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, emailChild, password, mobileNumberChild, guid, signUpOTP, userID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		userID = Integer.toString(userid);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);

		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response myEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		myEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objAdminCommonFunctions.createCashout(emailChild);
		
		myEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=50)
	public void validateSignUpBonusMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {
		
		try {

			// Object creations
			SoftAssert objSoftAssertion = new SoftAssert();
			HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
			SignUpOTP objSignUpOTP = new SignUpOTP(logger);
			SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
			AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
			MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
			NEFTPost objNEFTPost = new NEFTPost(logger);
			
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
			String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
			String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
			String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
			String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
			String frontEndURl				= ConfigurationSetup.FRONTENDURL;
			String typeNeft					= Utils.getRestApiTestData(32, "type_neft");
			String paymentType				= Utils.getRestApiTestData(32, "payment_type");
			String passcode					= Utils.getRestApiTestData(32, "password");
			String paymentMethodID			= Utils.getRestApiTestData(32, "payment_method_id");
			String accountHolderName		= Utils.getRestApiTestData(32, "account_holder_name");
			String bankName					= Utils.getRestApiTestData(32, "bank_name");
			String branch					= Utils.getRestApiTestData(32, "branch");
			String accountNumber			= Utils.getRestApiTestData(32, "account_number");
			String ifscCode					= Utils.getRestApiTestData(32, "ifsc_code");
			String cashback					= Utils.getRestApiTestData(31, "two_fifty");
			String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
			String month					= Utils.currentMonth();
			String year						= Utils.currentYear();
			String queryParameter			= "/"+month+"/"+year;
			String totalrecords				= Utils.getRestApiTestData(31, "total_records_zero");
			String pagenumber				= Utils.getRestApiTestData(31, "page_number");
			String pagesize					= Utils.getRestApiTestData(31, "page_size");
			String zero						= Utils.getRestApiTestData(31, "zero");

			objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
			
			/*objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);*/
			
			objAdminCommonFunctions.setSignUpBonus(frontEndURl, cashback);
			
			Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
			
			String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
			
			Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

			String guid = (String) signUpOTPResponse[1] ;

			String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

			Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
			
			accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
			
			objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
			
			Response myEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
			
			objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);
			
			objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
			
			myEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
			
			objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);
			
			objAdminCommonFunctions.createCashout(email);
			
			myEarningsReferralsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
			
			objMyEarningsReferrals.validateMyEarningsReferralsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsReferralsResponse, totalrecords, pagenumber, pagesize, nullValue);
			
			objAdminCommonFunctions.updateSignUpReferralBonusBonus(frontEndURl, zero, zero);

			objSoftAssertion.assertAll();
			
		} catch (Exception e) {

			// Object creations
			AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
			
			// Assigning values
			String zero						= Utils.getRestApiTestData(31, "zero");
			String frontEndURl				= ConfigurationSetup.FRONTENDURL;

			objAdminCommonFunctions.updateSignUpReferralBonusBonus(frontEndURl, zero, zero);
			
		}

	}

	@Test(priority=51)
	public void validateEmptyHeaderMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message");

		Response myEarningsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, statusCode401);
		
		objMyEarningsReferrals.validateMyEarningsReferralsErrorResponseUserEndpoint(objSoftAssertion, myEarningsResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=52)
	public void validateInvalidAuthorizationKeyValueMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message_invalid_json");
		String accessTokenValue			= Utils.getRestApiTestData(0, "auth_invalid_value");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");

		Response myEarningsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndpoint(baseURL, accessTokenValue, acceptValue, statusCode401);
		
		objMyEarningsReferrals.validateMyEarningsReferralsErrorResponseUserEndpoint(objSoftAssertion, myEarningsResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=53)
	public void validateInvalidAcceptTypeKeyValueMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		
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
		
		Response myEarningsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndpoint(baseURL, accessTokenValue, acceptValue, statusCode415);
		
		objMyEarningsReferrals.validateMyEarningsReferralsErrorResponseUserEndpoint(objSoftAssertion, myEarningsResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=54)
	public void validateEmptyAuthorizationKeyValueMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		
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

		Response myEarningsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndpoint(baseURL, accessTokenValue, acceptValue, statusCode401);
		
		objMyEarningsReferrals.validateMyEarningsReferralsErrorResponseUserEndpoint(objSoftAssertion, myEarningsResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=55)
	public void validateEmptyAcceptTypeKeyValueMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		
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
		
		Response myEarningsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndpoint(baseURL, accessTokenValue, acceptValue, statusCode406);
		
		objMyEarningsReferrals.validateMyEarningsReferralsErrorResponseUserEndpoint(objSoftAssertion, myEarningsResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=56)
	public void validateInvalidIncludeParameterMyEarningsReferralEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsReferral objMyEarningsReferrals = new MyEarningsReferral(logger);
		
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
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String commision				= Utils.getRestApiTestData(31, "five_hundered");
		String cashback					= Utils.getRestApiTestData(31, "two_fifty");
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_invalid");
		String errorStatus				= Utils.getRestApiTestData(31, "status_400");
		String errorCode				= Utils.getRestApiTestData(31, "code_11004");
		String errorTitle				= Utils.getRestApiTestData(31, "title_Invalid_Include_Field");
		String errorDetail				= Utils.getRestApiTestData(31, "detail_Invalid_Include_Field");
		String errorParameter			= Utils.getRestApiTestData(31, "parameter_Invalid_Include_Field");

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
		
		objAdminCommonFunctions.setRequiredPaymentActive(frontEndURl);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);

		Response myEarningsResponse = objMyEarningsReferrals.getMyEarningsReferralsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode400);
		
		objMyEarningsReferrals.validateMyEarningsReferralsErrorResponseUserEndpoint(objSoftAssertion, myEarningsResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		objSoftAssertion.assertAll();

	}

}