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
import com.ppa.api.endpoints.users.MyEarningsRewards;
import com.ppa.api.utilities.Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class MyEarningsRewardsTestSuite extends Base {

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
	public void validateCashbackConfirmStatusIncludeParameterCashbackReferralMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb_ref");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, twoFifty, zero, zero, twoFifty, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, twoFifty, zero, twoFifty, zero, zero, twoFifty, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, twoFifty, twoFifty, zero, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		objAdminCommonFunctions.createCashout(email);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, twoFifty, twoFifty, zero, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=2)
	public void validateCashbackConfirmStatusIncludeParameterCashbackMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, twoFifty, zero, zero, twoFifty, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, twoFifty, zero, twoFifty, zero, zero, twoFifty, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, twoFifty, twoFifty, zero, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objAdminCommonFunctions.createCashout(email);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, twoFifty, twoFifty, zero, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=3)
	public void validateCashbackConfirmStatusIncludeParameterReferralMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_ref");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objAdminCommonFunctions.createCashout(email);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=4)
	public void validateCashbackCancelStatusIncludeParameterCashbackReferralMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
		
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
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb_ref");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, twoFifty, zero, zero, twoFifty, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=5)
	public void validateCashbackCancelStatusIncludeParameterCashbackMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
		
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
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, twoFifty, zero, zero, twoFifty, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=6)
	public void validateCashbackCancelStatusIncludeParameterReferralMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
		
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
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_ref");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=7)
	public void validateRewardsConfirmStatusIncludeParameterCashbackReferralMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb_ref");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, twoFifty, zero, zero, twoFifty, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, twoFifty, zero, twoFifty, zero, twoFifty, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, twoFifty, twoFifty, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		objAdminCommonFunctions.createCashout(email);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, twoFifty, twoFifty, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=8)
	public void validateRewardsConfirmStatusIncludeParameterCashbackMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, twoFifty, zero, zero, twoFifty, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, twoFifty, zero, twoFifty, zero, twoFifty, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, twoFifty, twoFifty, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objAdminCommonFunctions.createCashout(email);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, twoFifty, twoFifty, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=9)
	public void validateRewardsConfirmStatusIncludeParameterReferralMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_ref");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, twoFifty, zero, zero, twoFifty, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, twoFifty, zero, twoFifty, zero, twoFifty, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, twoFifty, twoFifty, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objAdminCommonFunctions.createCashout(email);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, twoFifty, twoFifty, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=10)
	public void validateRewardsCancelStatusIncludeParameterCashbackReferralMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
		
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
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb_ref");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, twoFifty, zero, zero, twoFifty, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=11)
	public void validateRewardsCancelStatusIncludeParameterCashbackMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
		
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
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, twoFifty, zero, zero, twoFifty, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=12)
	public void validateRewardsCancelStatusIncludeParameterReferralMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
		
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
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_ref");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, twoFifty, zero, zero, twoFifty, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=13)
	public void validateChildUserCashbackConfirmStatusIncludeParameterCashbackReferralMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb_ref");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userIDChild, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userIDChild, twoFiveHundered, zero, twoFiveHundered, zero, zero, twoFiveHundered, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userIDChild, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		self = baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "referrals")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;

		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, twoFifty, zero, twoFifty, zero, twoFifty, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userID, twoFifty, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, twoFifty, twoFifty, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userID, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		objAdminCommonFunctions.createCashout(email);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, twoFifty, twoFifty, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userID, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=14)
	public void validateChildUserCashbackConfirmStatusIncludeParameterCashbackMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb_ref");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userIDChild, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userIDChild, twoFiveHundered, zero, twoFiveHundered, zero, zero, twoFiveHundered, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userIDChild, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);

		queryParameter = Utils.getRestApiTestData(30, "include_parameter_cb");
		
		self = baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "referrals")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;

		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, twoFifty, zero, twoFifty, zero, twoFifty, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, twoFifty, twoFifty, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objAdminCommonFunctions.createCashout(email);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, twoFifty, twoFifty, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=15)
	public void validateChildUserCashbackConfirmStatusIncludeParameterReferralMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb_ref");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userIDChild, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userIDChild, twoFiveHundered, zero, twoFiveHundered, zero, zero, twoFiveHundered, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userIDChild, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);

		queryParameter = Utils.getRestApiTestData(30, "include_parameter_ref");
		
		self = baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "referrals")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;

		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeReferral, userID, twoFifty, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeReferral, userID, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objAdminCommonFunctions.createCashout(email);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeReferral, userID, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=16)
	public void validateChildUserCashbackCancelStatusIncludeParameterCashbackReferralMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb_ref");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userIDChild, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userIDChild, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userIDChild, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=17)
	public void validateChildUserCashbackCancelStatusIncludeParameterCashbackMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb_ref");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userIDChild, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userIDChild, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userIDChild, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);

		queryParameter = Utils.getRestApiTestData(30, "include_parameter_cb");
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=18)
	public void validateChildUserCashbackCancelStatusIncludeParameterReferralMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb_ref");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userIDChild, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userIDChild, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userIDChild, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);

		queryParameter = Utils.getRestApiTestData(30, "include_parameter_ref");
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=19)
	public void validateChildUserRewardsConfirmStatusIncludeParameterCashbackReferralMyEarningsRewardsEndPoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb_ref");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userIDChild, twoFiveHundered, zero, twoFiveHundered, zero, twoFiveHundered, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userIDChild, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userIDChild, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		self = baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "referrals")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;

		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, twoFifty, zero, twoFifty, zero, twoFifty, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userID, twoFifty, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, twoFifty, twoFifty, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userID, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		objAdminCommonFunctions.createCashout(email);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, twoFifty, twoFifty, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userID, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=20)
	public void validateChildUserRewardsConfirmStatusIncludeParameterCashbackMyEarningsRewardsEndPoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userIDChild, twoFiveHundered, zero, twoFiveHundered, zero, twoFiveHundered, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userIDChild, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		self = baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "referrals")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;

		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, twoFifty, zero, twoFifty, zero, twoFifty, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, twoFifty, twoFifty, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objAdminCommonFunctions.createCashout(email);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, twoFifty, twoFifty, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=21)
	public void validateChildUserRewardsConfirmStatusIncludeParameterReferralMyEarningsRewardsEndPoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb_ref");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userIDChild, twoFiveHundered, zero, twoFiveHundered, zero, twoFiveHundered, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userIDChild, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userIDChild, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);

		queryParameter = Utils.getRestApiTestData(30, "include_parameter_ref");
		
		self = baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "referrals")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;

		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeReferral, userID, twoFifty, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeReferral, userID, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objAdminCommonFunctions.createCashout(email);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeReferral, userID, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=22)
	public void validateChildUserRewardsCancelStatusIncludeParameterCashbackReferralMyEarningsRewardsEndPoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb_ref");
		String typeCashback				= Utils.getRestApiTestData(31, "type_my_earnings_cashback");
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userIDChild, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userIDChild, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userIDChild, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=23)
	public void validateChildUserRewardsCancelStatusIncludeParameterCashbackMyEarningsRewardsEndPoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userIDChild, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userIDChild, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userID, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=24)
	public void validateChildUserRewardsCancelStatusIncludeParameterReferralMyEarningsRewardsEndPoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String queryParameter			= Utils.getRestApiTestData(30, "include_parameter_cb_ref");
		String typeCashback				= Utils.getRestApiTestData(31, "type_my_earnings_cashback");
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userIDChild, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeCashback, userIDChild, zero, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "1", typeReferral, userIDChild, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 2);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);

		queryParameter = Utils.getRestApiTestData(30, "include_parameter_ref");
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, "0", typeReferral, userID, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 1);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=25)
	public void validateCashbackConfirmStatusNoIncludeParameterMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 0);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 0);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 0);
		
		objAdminCommonFunctions.createCashout(email);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 0);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=26)
	public void validateCashbackCancelStatusNoIncludeParameterMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
		
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
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 0);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 0);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=27)
	public void validateRewardsConfirmStatusNoIncludeParameterMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
		String twoFifty					= Utils.getRestApiTestData(31, "two_fifty");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String cashbackType				= Utils.getRestApiTestData(31, "rewards");
		String typeMonths				= Utils.getRestApiTestData(31, "type_months");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, twoFifty, zero, zero, twoFifty, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 0);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, twoFifty, zero, twoFifty, zero, twoFifty, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 0);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, twoFifty, twoFifty, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 0);
		
		objAdminCommonFunctions.createCashout(email);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, twoFifty, twoFifty, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 0);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=28)
	public void validateRewardsCancelStatusNoIncludeParameterMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
		
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
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, twoFifty, zero, zero, twoFifty, zero, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 0);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 0);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=29)
	public void validateChildUserCashbackConfirmStatusNoIncludeParameterMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userIDChild, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 0);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 0);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 0);
		
		objAdminCommonFunctions.createCashout(email);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 0);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=30)
	public void validateChildUserCashbackCancelStatusNoIncludeParameterMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userIDChild, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 0);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 0);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=31)
	public void validateChildUserRewardsConfirmStatusNoIncludeParameterMyEarningsRewardsEndPoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userIDChild, twoFiveHundered, zero, twoFiveHundered, zero, twoFiveHundered, currency, typeMonths, month+year, month, year, self);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 0);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		self = baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "referrals")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;

		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 0);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 0);
		
		objAdminCommonFunctions.createCashout(email);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 0);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=32)
	public void validateChildUserRewardsCancelStatusNoIncludeParameterMyEarningsRewardsEndPoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userIDChild, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 0);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objMyEarningsRewards.validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(objSoftAssertion, myEarningsRewardsResponse, 0);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=33)
	public void validateCashbackConfirmStatusMyEarningsCashbackEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
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
		String totalrecords				= Utils.getRestApiTestData(31, "total_records_zero");
		String pagenumber				= Utils.getRestApiTestData(31, "page_number");
		String pagesize					= Utils.getRestApiTestData(31, "page_size");
		String queryParameter			= "/"+month+"/"+year;
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");

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
		
		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, totalrecords, pagenumber, pagesize, nullValue);
				
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);

		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objAdminCommonFunctions.createCashout(email);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=34)
	public void validateCashbackCancelStatusMyEarningsCashbackEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
		
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
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String commision				= Utils.getRestApiTestData(31, "five_hundered");
		String cashback					= Utils.getRestApiTestData(31, "two_fifty");
		String totalrecords				= Utils.getRestApiTestData(31, "total_records_zero");
		String pagenumber				= Utils.getRestApiTestData(31, "page_number");
		String pagesize					= Utils.getRestApiTestData(31, "page_size");
		String queryParameter			= "/"+month+"/"+year;
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");

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
		
		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, totalrecords, pagenumber, pagesize, nullValue);
				
		objSoftAssertion.assertAll();

	}

	@Test(priority=35)
	public void validateRewardsConfirmStatusMyEarningsCashbackEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String typeCashback				= Utils.getRestApiTestData(31, "type_my_earnings_cashback");
		String twoFifty					= Utils.getRestApiTestData(31, "two_fifty");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String cashbackType				= Utils.getRestApiTestData(31, "rewards");
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
		String cashback					= Utils.getRestApiTestData(31, "two_fifty");
		String totalrecords				= Utils.getRestApiTestData(31, "total_records");
		String pagenumber				= Utils.getRestApiTestData(31, "page_number");
		String pagesize					= Utils.getRestApiTestData(31, "page_size");
		String selfUrl					= self;
		String firstUrl					= self+Utils.getRestApiTestData(31, "pagenumber")+"&"+Utils.getRestApiTestData(31, "pagesize");
		String lastUrl					= self+Utils.getRestApiTestData(31, "pagenumber")+"&"+Utils.getRestApiTestData(31, "pagesize");
		String prevUrl					= Utils.getRestApiTestData(31, "null");
		String nextUrl					= Utils.getRestApiTestData(31, "null");
		String cashbackcreateddate		= Utils.currentDateFormat();
		String merchantname				= storeName;
		String orderamount				= Utils.getRestApiTestData(31, "thousand");
		String pendingStatus			= Utils.getRestApiTestData(31, "pending");
		String confirmStatus			= Utils.getRestApiTestData(31, "confirm");
		String requestedStatus			= Utils.getRestApiTestData(31, "request");
		String paidStatus				= Utils.getRestApiTestData(31, "paid");
		String expectedconfirmationdate	= Utils.requiredDateFormat(90);
		String queryParameter			= "/"+month+"/"+year;

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
		
		String cashbackID = objAdminCommonFunctions.getCashbackID(exitClick);

		Response myEarningsCashbackResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsCashbackResponse, totalrecords, pagenumber, pagesize, selfUrl, firstUrl, lastUrl, prevUrl, nextUrl, typeCashback, cashbackID, cashbackcreateddate, merchantname, orderamount, exitClick, twoFifty, pendingStatus, expectedconfirmationdate, currency);
		
		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		expectedconfirmationdate = Utils.getRestApiTestData(8, "emptyValue");

		myEarningsCashbackResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsCashbackResponse, totalrecords, pagenumber, pagesize, selfUrl, firstUrl, lastUrl, prevUrl, nextUrl, typeCashback, cashbackID, cashbackcreateddate, merchantname, orderamount, exitClick, twoFifty, confirmStatus, expectedconfirmationdate, currency);
				
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);

		myEarningsCashbackResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsCashbackResponse, totalrecords, pagenumber, pagesize, selfUrl, firstUrl, lastUrl, prevUrl, nextUrl, typeCashback, cashbackID, cashbackcreateddate, merchantname, orderamount, exitClick, twoFifty, requestedStatus, expectedconfirmationdate, currency);
		
		objAdminCommonFunctions.createCashout(email);
		
		myEarningsCashbackResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsCashbackResponse, totalrecords, pagenumber, pagesize, selfUrl, firstUrl, lastUrl, prevUrl, nextUrl, typeCashback, cashbackID, cashbackcreateddate, merchantname, orderamount, exitClick, twoFifty, paidStatus, expectedconfirmationdate, currency);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=36)
	public void validateRewardsCancelStatusMyEarningsCashbackEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
		
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
		String typeCashback				= Utils.getRestApiTestData(31, "type_my_earnings_cashback");
		String twoFifty					= Utils.getRestApiTestData(31, "two_fifty");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String cashbackType				= Utils.getRestApiTestData(31, "rewards");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "rewards")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String commision				= Utils.getRestApiTestData(31, "five_hundered");
		String cashback					= Utils.getRestApiTestData(31, "two_fifty");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String totalrecords				= Utils.getRestApiTestData(31, "total_records");
		String pagenumber				= Utils.getRestApiTestData(31, "page_number");
		String pagesize					= Utils.getRestApiTestData(31, "page_size");
		String selfUrl					= self;
		String firstUrl					= self+Utils.getRestApiTestData(31, "pagenumber")+"&"+Utils.getRestApiTestData(31, "pagesize");
		String lastUrl					= self+Utils.getRestApiTestData(31, "pagenumber")+"&"+Utils.getRestApiTestData(31, "pagesize");
		String prevUrl					= Utils.getRestApiTestData(31, "null");
		String nextUrl					= Utils.getRestApiTestData(31, "null");
		String cashbackcreateddate		= Utils.currentDateFormat();
		String merchantname				= storeName;
		String orderamount				= Utils.getRestApiTestData(31, "thousand");
		String pendingStatus			= Utils.getRestApiTestData(31, "pending");
		String cancelStatus				= Utils.getRestApiTestData(31, "cancel");
		String expectedconfirmationdate	= Utils.requiredDateFormat(90);
		String queryParameter			= "/"+month+"/"+year;

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
		
		String cashbackID = objAdminCommonFunctions.getCashbackID(exitClick);

		Response myEarningsCashbackResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsCashbackResponse, totalrecords, pagenumber, pagesize, selfUrl, firstUrl, lastUrl, prevUrl, nextUrl, typeCashback, cashbackID, cashbackcreateddate, merchantname, orderamount, exitClick, twoFifty, pendingStatus, expectedconfirmationdate, currency);
		
		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		expectedconfirmationdate = Utils.getRestApiTestData(8, "emptyValue");

		myEarningsCashbackResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsCashbackResponse, totalrecords, pagenumber, pagesize, selfUrl, firstUrl, lastUrl, prevUrl, nextUrl, typeCashback, cashbackID, cashbackcreateddate, merchantname, orderamount, exitClick, zero, cancelStatus, expectedconfirmationdate, currency);
				
		objSoftAssertion.assertAll();

	}

	@Test(priority=37)
	public void validateChildUserCashbackConfirmStatusMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userIDChild, zero, zero, zero, zero, zero, currency, nullValue);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objAdminCommonFunctions.createCashout(email);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=38)
	public void validateChildUserCashbackCancelStatusMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userIDChild, zero, zero, zero, zero, zero, currency, nullValue);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=39)
	public void validateChildUserRewardsConfirmStatusMyEarningsRewardsEndPoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
		String twoFiveHundered			= Utils.getRestApiTestData(31, "two_five_hundered");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userIDChild, twoFiveHundered, zero, twoFiveHundered, zero, twoFiveHundered, currency, typeMonths, month+year, month, year, self);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		self = baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "referrals")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;

		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objAdminCommonFunctions.createCashout(email);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, totalrecords, pagenumber, pagesize, nullValue);

		objSoftAssertion.assertAll();

	}

	@Test(priority=40)
	public void validateChildUserRewardsCancelStatusMyEarningsRewardsEndPoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
		String zero						= Utils.getRestApiTestData(31, "zero");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
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

		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userIDChild, zero, zero, zero, zero, zero, currency, nullValue);
		
		helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=41)
	public void validateAddCashbackBonusNoIncludeParameterMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		MyEarnings objMyEarnings = new MyEarnings(logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
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
		
		objMyEarnings.addBonus(email, cashback, cashbackType);
		
		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndpoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);

		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndpoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);

		objAdminCommonFunctions.createCashoutAfterLoggingin(adminURL, adminDropDown, adminUsername, adminPassword, email);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndpoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);

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
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String twoFifty					= Utils.getRestApiTestData(31, "two_fifty");
		String typeMonths				= Utils.getRestApiTestData(31, "type_months");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "rewards")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;

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
		
		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, twoFifty, zero, twoFifty, zero, twoFifty, currency, typeMonths, month+year, month, year, self);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, twoFifty, twoFifty, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
		objAdminCommonFunctions.createCashoutAfterLoggingin(adminURL, adminDropDown, adminUsername, adminPassword, email);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, twoFifty, twoFifty, zero, zero, zero, currency, typeMonths, month+year, month, year, self);
		
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
			MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
			String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
			String nullValue				= Utils.getRestApiTestData(8, "emptyValue");

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
			
			Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
			
			objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndpoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
			
			objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
			
			myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
			
			objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndpoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
			
			objAdminCommonFunctions.createCashout(emailChild);
			
			myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
			
			objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndpoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
			
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
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");

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
		
		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndpoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndpoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
		objAdminCommonFunctions.createCashout(emailChild);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndpoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
		
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
			MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
			String typeRewards				= Utils.getRestApiTestData(31, "type_my_earnings_rewards");
			String nullValue				= Utils.getRestApiTestData(8, "emptyValue");

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
			
			Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
			
			objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndpoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
			
			objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
			
			myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
			
			objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndpoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
			
			objAdminCommonFunctions.createCashout(email);
			
			myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
			
			objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndpoint(objSoftAssertion, myEarningsRewardsResponse, typeRewards, userID, zero, zero, zero, zero, zero, currency, nullValue);
			
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
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		
		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, totalrecords, pagenumber, pagesize, nullValue);

		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, totalrecords, pagenumber, pagesize, nullValue);

		objAdminCommonFunctions.createCashoutAfterLoggingin(adminURL, adminDropDown, adminUsername, adminPassword, email);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, totalrecords, pagenumber, pagesize, nullValue);

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
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		String currency					= ConfigurationSetup.CURRENCYTYPE;
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
		String typeCashback				= Utils.getRestApiTestData(31, "type_my_earnings_cashback");
		String month					= Utils.currentMonth();
		String year						= Utils.currentYear();
		String twoFifty					= Utils.getRestApiTestData(31, "two_fifty");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "myearnings")+Utils.getRestApiTestData(2, "rewards")+Utils.getRestApiTestData(31, "forward_slash")+month+Utils.getRestApiTestData(31, "forward_slash")+year;
		String totalrecords				= Utils.getRestApiTestData(31, "total_records");
		String pagenumber				= Utils.getRestApiTestData(31, "page_number");
		String pagesize					= Utils.getRestApiTestData(31, "page_size");
		String selfUrl					= self;
		String firstUrl					= self+Utils.getRestApiTestData(31, "pagenumber")+"&"+Utils.getRestApiTestData(31, "pagesize");
		String lastUrl					= self+Utils.getRestApiTestData(31, "pagenumber")+"&"+Utils.getRestApiTestData(31, "pagesize");
		String prevUrl					= Utils.getRestApiTestData(31, "null");
		String nextUrl					= Utils.getRestApiTestData(31, "null");
		String cashbackcreateddate		= Utils.currentDateFormat();
		String merchantname				= Utils.getRestApiTestData(31, "bonus");
		String orderamount				= Utils.getRestApiTestData(31, "zero");
		String confirmStatus			= Utils.getRestApiTestData(31, "confirm");
		String requestedStatus			= Utils.getRestApiTestData(31, "request");
		String paidStatus				= Utils.getRestApiTestData(31, "paid");
		String nullValue				= Utils.getRestApiTestData(8, "emptyValue");
		String expectedconfirmationdate	= Utils.getRestApiTestData(8, "emptyValue");
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

		String cashbackID = objAdminCommonFunctions.getCashbackID(email, "3");
		
		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, totalrecords, pagenumber, pagesize, selfUrl, firstUrl, lastUrl, prevUrl, nextUrl, typeCashback, cashbackID, cashbackcreateddate, merchantname, orderamount, nullValue, twoFifty, confirmStatus, expectedconfirmationdate, currency);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, totalrecords, pagenumber, pagesize, selfUrl, firstUrl, lastUrl, prevUrl, nextUrl, typeCashback, cashbackID, cashbackcreateddate, merchantname, orderamount, nullValue, twoFifty, requestedStatus, expectedconfirmationdate, currency);
		
		objAdminCommonFunctions.createCashout(email);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, totalrecords, pagenumber, pagesize, selfUrl, firstUrl, lastUrl, prevUrl, nextUrl, typeCashback, cashbackID, cashbackcreateddate, merchantname, orderamount, nullValue, twoFifty, paidStatus, expectedconfirmationdate, currency);
		
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
			MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
			
			Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
			
			objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, totalrecords, pagenumber, pagesize, nullValue);
			
			objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
			
			myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
			
			objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, totalrecords, pagenumber, pagesize, nullValue);
			
			objAdminCommonFunctions.createCashout(emailChild);
			
			myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
			
			objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, totalrecords, pagenumber, pagesize, nullValue);
			
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
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
		
		Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
		objAdminCommonFunctions.createCashout(emailChild);
		
		myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, totalrecords, pagenumber, pagesize, nullValue);
		
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
			MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
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
			
			Response myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
			
			objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, totalrecords, pagenumber, pagesize, nullValue);
			
			objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);
			
			myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
			
			objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, totalrecords, pagenumber, pagesize, nullValue);
			
			objAdminCommonFunctions.createCashout(email);
			
			myEarningsRewardsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
			
			objMyEarningsRewards.validateMyEarningsRewardsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsRewardsResponse, totalrecords, pagenumber, pagesize, nullValue);
			
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
	public void validateEmptyHeaderMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message");

		Response myEarningsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, statusCode401);
		
		objMyEarningsRewards.validateMyEarningsRewardsErrorResponseUserEndpoint(objSoftAssertion, myEarningsResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=52)
	public void validateInvalidAuthorizationKeyValueMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message_invalid_json");
		String accessTokenValue			= Utils.getRestApiTestData(0, "auth_invalid_value");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");

		Response myEarningsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndpoint(baseURL, accessTokenValue, acceptValue, statusCode401);
		
		objMyEarningsRewards.validateMyEarningsRewardsErrorResponseUserEndpoint(objSoftAssertion, myEarningsResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=53)
	public void validateInvalidAcceptTypeKeyValueMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
		
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
		
		Response myEarningsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndpoint(baseURL, accessTokenValue, acceptValue, statusCode415);
		
		objMyEarningsRewards.validateMyEarningsRewardsErrorResponseUserEndpoint(objSoftAssertion, myEarningsResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=54)
	public void validateEmptyAuthorizationKeyValueMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
		
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

		Response myEarningsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndpoint(baseURL, accessTokenValue, acceptValue, statusCode401);
		
		objMyEarningsRewards.validateMyEarningsRewardsErrorResponseUserEndpoint(objSoftAssertion, myEarningsResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=55)
	public void validateEmptyAcceptTypeKeyValueMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
		
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
		
		Response myEarningsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndpoint(baseURL, accessTokenValue, acceptValue, statusCode406);
		
		objMyEarningsRewards.validateMyEarningsRewardsErrorResponseUserEndpoint(objSoftAssertion, myEarningsResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=56)
	public void validateInvalidIncludeParameterMyEarningsRewardsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		MyEarningsRewards objMyEarningsRewards = new MyEarningsRewards(logger);
		
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

		Response myEarningsResponse = objMyEarningsRewards.getMyEarningsRewardsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode400);
		
		objMyEarningsRewards.validateMyEarningsRewardsErrorResponseUserEndpoint(objSoftAssertion, myEarningsResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		objSoftAssertion.assertAll();

	}

}