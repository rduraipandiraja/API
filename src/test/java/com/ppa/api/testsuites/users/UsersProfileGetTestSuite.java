package com.ppa.api.testsuites.users;

import java.awt.AWTException;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.admin.pages.AdminCoreFunction;
import com.admin.pages.Login;
import com.admin.pages.PartnerSettingsPage;
import com.ppa.api.base.Base;
import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.endpoints.helper.HelperAccessToken;
import com.ppa.api.endpoints.helper.SignUpOTP;
import com.ppa.api.endpoints.helper.SignUpV1WithOTP;
import com.ppa.api.endpoints.helper.UserSignUp;
import com.ppa.api.endpoints.users.UsersProfileGET;
import com.ppa.api.utilities.Restutils;
import com.ppa.api.utilities.Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UsersProfileGetTestSuite extends Base {

	@Test(priority=0)
	public void validateUserProfileGetEndpointDeviceTypeDesktopWithoutPartnerInfo() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		UsersProfileGET objUsersProfileGET = new UsersProfileGET(logger);
		
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
		String typeUser					= Utils.getRestApiTestData(21, "type");
		String newsLetter				= Utils.getRestApiTestData(21, "enabled_newsletter_yes");
		String partnerInfo				= Utils.getRestApiTestData(8, "emptyValue");
		String referralEarningsNotification= Utils.getRestApiTestData(21, "enabled_ref_earnings_notification_yes");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Get Access token for new user
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		// Get ID for new user
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		String userID = Integer.toString(userid);
		
		// Get Fullname for new user
		String userFullName = objSignUpV1WithOTP.getUserFullName(signUpV1WithOTPResponse);

		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		// Getting user profile GET - GET Request
		Response userProfileGETResponse = objUsersProfileGET.getUserProfileGETResponseUserEndPoint(baseURL, accessTokenValue, partnerInfo, statusCode200);
		
		// Validate success response
		objUsersProfileGET.validateUserProfileGETSuccessResponseUserEndPoint(objSoftAssertion, userProfileGETResponse, typeUser, userID, userFullName, email, "91"+mobileNumber, newsLetter, referralEarningsNotification);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=1)
	public void validateUserProfileGetEndpointDeviceTypeDesktopWithPartnerInfo() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		UsersProfileGET objUsersProfileGET = new UsersProfileGET(logger);
		
		// Admin Object creations
		Login objLogin								= new Login(driver, logger);
		AdminCoreFunction objAdminCoreFunction = new AdminCoreFunction(driver, logger);
		PartnerSettingsPage objPartnerSettingsPage	= new PartnerSettingsPage(driver, logger);
		
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
		String typeUser					= Utils.getRestApiTestData(21, "type");
		String newsLetter				= Utils.getRestApiTestData(21, "enabled_newsletter_yes");
		String referralEarningsNotification= Utils.getRestApiTestData(21, "enabled_ref_earnings_notification_yes");
		String partnerInfo				= Utils.getRestApiTestData(21, "partner_info");
		String typePartner				= Utils.getRestApiTestData(21, "type_partner");
		String customerInfoEmail		= Utils.getRestApiTestData(5, "fb_email");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String frontEndURl				= ConfigurationSetup.FRONTENDURL;

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Get Access token for new user
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		// Get ID for new user
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		String userID = Integer.toString(userid);
		
		// Get Fullname for new user
		String userFullName = objSignUpV1WithOTP.getUserFullName(signUpV1WithOTPResponse);

		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		// Getting user profile GET - GET Request
		Response userProfileGETResponse = objUsersProfileGET.getUserProfileGETResponseUserEndPoint(baseURL, accessTokenValue, partnerInfo, statusCode200);

		// Redirecting to admin panel
		Utils.loadURL(driver, adminURL);
		
		Thread.sleep(1000);
		
		objLogin.selectValueAdminDropdown(driver, adminDropDown);
		objLogin.enterUserName(driver, adminUsername);
		objLogin.enterPassword(driver, adminPassword);
		objLogin.clickLogin(driver);
		
		// Redirecting to partner settings page
		objAdminCoreFunction.clickOnSettings();
		objAdminCoreFunction.clickOnPartnerSettings();
		
		objPartnerSettingsPage.clickEditPartnerDetails(driver, StringUtils.substringAfter(frontEndURl, "://"));
		objPartnerSettingsPage.enterCustomerEmailID(customerInfoEmail);
		String partnerID = objPartnerSettingsPage.getPartnerID(driver, adminURL);
		
		Thread.sleep(1000);
		
		// Validate success response
		objUsersProfileGET.validateUserProfileGETSuccessResponseUserEndPoint(objSoftAssertion, userProfileGETResponse, typeUser, userID, userFullName, email, "91"+mobileNumber, newsLetter, referralEarningsNotification);
		objUsersProfileGET.validateUserProfileGETSuccessResponseUserEndPoint(objSoftAssertion, userProfileGETResponse, typePartner, partnerID, customerInfoEmail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=2)
	public void validateUserProfileGetEndpointDeviceTypeDesktopInvalidPartnerInfo() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		UsersProfileGET objUsersProfileGET = new UsersProfileGET(logger);
		
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
		String partnerInfo				= Utils.getRestApiTestData(21, "invalid_partner_info");
		String errorStatus				= Utils.getRestApiTestData(21, "status_invalid_include_field");
		String errorCode				= Utils.getRestApiTestData(21, "code_invalid_include_field");
		String errorTitle				= Utils.getRestApiTestData(21, "title_invalid_include_field");
		String errorDetail				= Utils.getRestApiTestData(21, "detail_invalid_include_field");
		String errorParameter			= Utils.getRestApiTestData(21, "parameter_invalid_include_field");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Get Access token for new user
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		// Getting user profile GET - GET Request
		Response userProfileGETResponse = objUsersProfileGET.getUserProfileGETResponseUserEndPoint(baseURL, accessTokenValue, partnerInfo, statusCode400);
		
		// Validate success response
		objUsersProfileGET.validateUserProfileGETErrorResponseUserEndPoint(objSoftAssertion, userProfileGETResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=3)
	public void validateUserProfileEndpointDeviceTypeDesktopWithoutPartnerInfo() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersProfileGET objUsersProfileGET = new UsersProfileGET(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
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
		String typeUser					= Utils.getRestApiTestData(21, "type");
		String newsLetter				= Utils.getRestApiTestData(21, "enabled_newsletter_yes");
		String partnerInfo				= Utils.getRestApiTestData(8, "emptyValue");
		String referralEarningsNotification= Utils.getRestApiTestData(21, "enabled_ref_earnings_notification_yes");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		// Get Access token for new user
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);

		// Get ID for new user
		int userid = objUserSignUp.getUserID(userSignUpResponse);
		String userID = Integer.toString(userid);
		
		// Get Fullname for new user
		String userFullName = objUserSignUp.getUserFullName(userSignUpResponse);

		// Getting user profile GET - GET Request
		Response userProfileGETResponse = objUsersProfileGET.getUserProfileGETResponseUserEndPoint(baseURL, accessTokenValue, partnerInfo, statusCode200);
		
		// Validate success response
		objUsersProfileGET.validateUserProfileGETSuccessResponseUserEndPoint(objSoftAssertion, userProfileGETResponse, typeUser, userID, userFullName, email, "null", newsLetter, referralEarningsNotification);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=3)
	public void validateContentTypeResponseUserProfileGetEndpointDeviceTypeDesktopWithPartnerInfo() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		UsersProfileGET objUsersProfileGET = new UsersProfileGET(logger);
		Restutils objRestutils = new Restutils(logger);
		
		// Admin Object creations
		Login objLogin								= new Login(driver, logger);
		AdminCoreFunction objAdminCoreFunction = new AdminCoreFunction(driver, logger);
		PartnerSettingsPage objPartnerSettingsPage	= new PartnerSettingsPage(driver, logger);
		
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
		String typeUser					= Utils.getRestApiTestData(21, "type");
		String newsLetter				= Utils.getRestApiTestData(21, "enabled_newsletter_yes");
		String referralEarningsNotification= Utils.getRestApiTestData(21, "enabled_ref_earnings_notification_yes");
		String partnerInfo				= Utils.getRestApiTestData(21, "partner_info");
		String typePartner				= Utils.getRestApiTestData(21, "type_partner");
		String customerInfoEmail		= Utils.getRestApiTestData(5, "fb_email");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String frontEndURl				= Utils.getConfigurationSetupTestData(0, "frontend_url");
		String contentType				= Utils.getRestApiTestData(0, "accept_value");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Get Access token for new user
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		// Get ID for new user
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		String userID = Integer.toString(userid);
		
		// Get Fullname for new user
		String userFullName = objSignUpV1WithOTP.getUserFullName(signUpV1WithOTPResponse);

		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		// Getting user profile GET - GET Request
		Response userProfileGETResponse = objUsersProfileGET.getUserProfileGETResponseUserEndPoint(baseURL, accessTokenValue, partnerInfo, statusCode200);

		// Redirecting to admin panel
		Utils.loadURL(driver, adminURL);
		
		Thread.sleep(1000);
		
		objLogin.selectValueAdminDropdown(driver, adminDropDown);
		objLogin.enterUserName(driver, adminUsername);
		objLogin.enterPassword(driver, adminPassword);
		objLogin.clickLogin(driver);
		
		// Redirecting to partner settings page
		objAdminCoreFunction.clickOnSettings();
		objAdminCoreFunction.clickOnPartnerSettings();
		
		objPartnerSettingsPage.clickEditPartnerDetails(driver, StringUtils.substringAfter(frontEndURl, "://"));
		objPartnerSettingsPage.enterCustomerEmailID(customerInfoEmail);
		String partnerID = objPartnerSettingsPage.getPartnerID(driver, adminURL);
		
		Thread.sleep(1000);
		
		// Validate success response
		objUsersProfileGET.validateUserProfileGETSuccessResponseUserEndPoint(objSoftAssertion, userProfileGETResponse, typeUser, userID, userFullName, email, "91"+mobileNumber, newsLetter, referralEarningsNotification);
		objUsersProfileGET.validateUserProfileGETSuccessResponseUserEndPoint(objSoftAssertion, userProfileGETResponse, typePartner, partnerID, customerInfoEmail);
		
		objRestutils.validateReponseContentType(objSoftAssertion, userProfileGETResponse, contentType);

		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=4)
	public void validateEmptyHeaderUserProfileGetEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		UsersProfileGET objUsersProfileGET = new UsersProfileGET(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message");
		
		// Getting user profile GET - GET Request
		Response userProfileGETResponse = objUsersProfileGET.getUserProfileGETResponseUserEndPoint(baseURL, statusCode401);
		
		// Validate success response
		objUsersProfileGET.validateUserProfileGETErrorResponseUserEndPoint(objSoftAssertion, userProfileGETResponse, errorMessage);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=5)
	public void validateInvalidAuthorizationKeyValueUserProfileGetEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		UsersProfileGET objUsersProfileGET = new UsersProfileGET(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message_invalid_json");
		String accessTokenValue			= Utils.getRestApiTestData(0, "auth_invalid_value");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");

		
		// Getting user profile GET - GET Request
		Response userProfileGETResponse = objUsersProfileGET.getUserProfileGetResponseUserEndPoint(baseURL, accessTokenValue, acceptValue, statusCode401);
		
		// Validate success response
		objUsersProfileGET.validateUserProfileGETErrorResponseUserEndPoint(objSoftAssertion, userProfileGETResponse, errorMessage);
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=6)
	public void validateInvalidAcceptTypeKeyValueUserProfileGetEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		UsersProfileGET objUsersProfileGET = new UsersProfileGET(logger);
		
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

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Get Access token for new user
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);

		// Getting user profile GET - GET Request
		Response userProfileGETResponse = objUsersProfileGET.getUserProfileGetResponseUserEndPoint(baseURL, accessTokenValue, acceptValue, statusCode415);
		
		// Validate success response
		objUsersProfileGET.validateUserProfileGETErrorResponseUserEndPoint(objSoftAssertion, userProfileGETResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=7)
	public void validateEmptyAuthorizationKeyValueUserProfileGetEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		UsersProfileGET objUsersProfileGET = new UsersProfileGET(logger);
		
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

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Get Access token for new user
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue		= Utils.getRestApiTestData(8, "emptyValue");
		
		// Getting user profile GET - GET Request
		Response userProfileGETResponse = objUsersProfileGET.getUserProfileGetResponseUserEndPoint(baseURL, accessTokenValue, acceptValue, statusCode401);
		
		// Validate success response
		objUsersProfileGET.validateUserProfileGETErrorResponseUserEndPoint(objSoftAssertion, userProfileGETResponse, errorMessage);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=8)
	public void validateEmptyAcceptTypeKeyValueUserProfileGetEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		UsersProfileGET objUsersProfileGET = new UsersProfileGET(logger);
		
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

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Get Access token for new user
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);


		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);

		// Getting user profile GET - GET Request
		Response userProfileGETResponse = objUsersProfileGET.getUserProfileGetResponseUserEndPoint(baseURL, accessTokenValue, acceptValue, statusCode406);
		
		// Validate success response
		objUsersProfileGET.validateUserProfileGETErrorResponseUserEndPoint(objSoftAssertion, userProfileGETResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
}