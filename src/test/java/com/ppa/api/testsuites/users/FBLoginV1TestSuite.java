package com.ppa.api.testsuites.users;

import java.awt.AWTException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.fb.frontend.pages.FacebookGraphAPIExplorer;
import com.fb.frontend.pages.FacebookHome;
import com.fb.frontend.pages.FacebookLogin;
import com.ppa.api.base.Base;
import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.endpoints.helper.FBLoginV1;
import com.ppa.api.endpoints.helper.FBSignUpV1WithOTP;
import com.ppa.api.endpoints.helper.HelperAccessToken;
import com.ppa.api.endpoints.helper.SignUpOTP;
import com.ppa.api.utilities.Restutils;
import com.ppa.api.utilities.Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FBLoginV1TestSuite extends Base {

	String facebookAccessToken;
	String facebookID;

	@Test(priority=50)
	public void aboutGetFacebookAccessTokenAndID() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FacebookGraphAPIExplorer objGraphAPIExplorer = new FacebookGraphAPIExplorer(driver, logger);
		FacebookHome objFacebookHome = new FacebookHome(driver, logger);
		FacebookLogin objFacebookLogin = new FacebookLogin(driver, logger);
		
		// Assigning values
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		
		try {
			
			// Reset fb email
			objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);
			
			// Redirecting to facebook page
			String facebookAppURL = objGraphAPIExplorer.getfacebookAppURLForSpecifcApp();
			Utils.loadURL(driver, facebookAppURL);
			
			// Logging in using fb developer credentials
			objFacebookHome.clickLinkLogIn();
			objFacebookLogin.enterUserName();
			objFacebookLogin.enterPassword();
			objFacebookLogin.clickSubmit();
			
			// Getting fb access token and fb id
			facebookAccessToken = objGraphAPIExplorer.getFacebookAccessToken();
			facebookID = objGraphAPIExplorer.getFacebookID();
			
		} catch (Exception e) {
			
			// Reset fb email
			objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);
			
			// Redirecting to facebook page
			String facebookAppURL = objGraphAPIExplorer.getfacebookAppURLForSpecifcApp();
			Utils.loadURL(driver, facebookAppURL);
			
			// Logging in using fb developer credentials
			objFacebookHome.clickLinkLogIn();
			objFacebookLogin.enterUserName();
			objFacebookLogin.enterPassword();
			objFacebookLogin.clickSubmit();
			
			// Getting fb access token and fb id
			facebookAccessToken = objGraphAPIExplorer.getFacebookAccessToken();
			facebookID = objGraphAPIExplorer.getFacebookID();
		}

	}
	
	
	@Test(priority=51)
	public void validateNewFacebookUserFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		String fbSignUpV1WithOTPURL		= baseURL + Utils.getRestApiTestData(2, "fb_signup_v1_otp") + deviceType;
		String httpMethod				= "POST";
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		String  accessTokenValue				= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// User Login - POST Request
		Response fbLoginV1Response				= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, deviceType, statusCode200);
		
		objFBLoginV1.validateFBLoginV1SucessResponseHelperEndPoint(objSoftAssertion, fbLoginV1Response, typeAuth, facebookID, facebookAccessToken, fbSignUpV1WithOTPURL, httpMethod);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=52)
	public void validateNewFacebookUserFBSignUpV1WithOTPEndpointDeviceTypeApp() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		String fbSignUpV1WithOTPURL		= baseURL + Utils.getRestApiTestData(2, "fb_signup_v1_otp") + deviceType;
		String httpMethod				= "POST";
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		String  accessTokenValue				= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// User Login - POST Request
		Response fbLoginV1Response				= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, fcmID, deviceUniqueID, imeiNumber, deviceType, statusCode200);
		
		objFBLoginV1.validateFBLoginV1SucessResponseHelperEndPoint(objSoftAssertion, fbLoginV1Response, typeAuth, facebookID, facebookAccessToken, fbSignUpV1WithOTPURL, httpMethod);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=53)
	public void validateBVAForFacebookAccessTokenWithMaximumCharactersFBLoginV1EndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String mobileNumber				= Utils.generateMobileNumber(10);
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// User Login - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);

		// User Login - POST Request
		Response fbLoginV1Response= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, deviceType, statusCode200);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBLoginV1.validateFBLoginV1SucessResponseHelperEndPoint(objSoftAssertion, fbLoginV1Response, type, fullName);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=54)
	public void validateBVAForFacebookAccessTokenWithMaximumCharactersMinusOneFBLoginV1EndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String mobileNumber				= Utils.generateMobileNumber(10);
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		String errorCode				= Utils.getRestApiTestData(16, "error_code");
		String errorTitle				= Utils.getRestApiTestData(16, "error_title");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// User Login - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);

		String fbAccessToken = facebookAccessToken.substring(0, facebookAccessToken.length() - 1);

		// User Login - POST Request
		Response fbLoginV1Response= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, typeAuth, fbAccessToken, facebookID, deviceType, statusCode400);
		
		// Validate FBSignUpV1WithOTP error response - POST Request
		objFBLoginV1.validateFBLoginV1ErrorResponseHelperEndPoint(objSoftAssertion, fbLoginV1Response, errorCode, errorTitle);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=55)
	public void validateInvalidFacebookAccessTokenFBLoginV1EndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String mobileNumber				= Utils.generateMobileNumber(10);
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String type						= Utils.getRestApiTestData(10, "type");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorCode				= Utils.getRestApiTestData(16, "error_code");
		String errorTitle				= Utils.getRestApiTestData(16, "error_title");
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// User Login - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);
		
		// Reassigning values
		String facebookAccessToken = Utils.generateRandomAlphabets(20);
		/*facebookID = objGraphAPIExplorer.getFacebookID();*/
		
		// User Login - POST Request
		Response fbLoginV1Response= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, deviceType, statusCode400);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBLoginV1.validateFBLoginV1ErrorResponseHelperEndPoint(objSoftAssertion, fbLoginV1Response, errorCode, errorTitle);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=56)
	public void validateInvalidFacebookIDFBLoginV1EndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String mobileNumber				= Utils.generateMobileNumber(10);
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(15, "error_status");
		String errorCode				= Utils.getRestApiTestData(15, "error_code");
		String errorTitle				= Utils.getRestApiTestData(15, "error_title");
		String errorDetail				= Utils.getRestApiTestData(15, "error_detail");
		String errorPointer				= Utils.getRestApiTestData(15, "error_pointer");
		String type						= Utils.getRestApiTestData(10, "type");
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// User Login - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);
		
		// Reassigning values
		/*facebookAccessToken = objGraphAPIExplorer.getFacebookAccessToken();*/
		String facebookID = Utils.generateRandomNumber(20);
		
		// User Login - POST Request
		Response fbLoginV1Response= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, deviceType, statusCode400);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBLoginV1.validateFBLoginV1ErrorResponseHelperEndPoint_(objSoftAssertion, fbLoginV1Response, errorStatus, errorCode, errorTitle, errorDetail, errorPointer);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}


	@Test(priority=57)
	public void validateBVAForFacebookIDWithMaximumCharactersFBLoginV1EndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String type						= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse	= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid					= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP			= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);

		// User Login - POST Request
		Response fbLoginV1Response= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, deviceType, statusCode200);
		
		// Validate FBSignUpV1WithOTP error response - POST Request
		objFBLoginV1.validateFBLoginV1SucessResponseHelperEndPoint(objSoftAssertion, fbLoginV1Response, typeAuth, fullName);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=58)
	public void validateBVAForFacebookIDWithMinimumCharactersMinusOneFBLoginV1EndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FacebookGraphAPIExplorer objGraphAPIExplorer = new FacebookGraphAPIExplorer(driver, logger);
		FacebookHome objFacebookHome = new FacebookHome(driver, logger);
		FacebookLogin objFacebookLogin = new FacebookLogin(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String type						= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(15, "error_status");
		String errorCode				= Utils.getRestApiTestData(15, "error_code");
		String errorTitle				= Utils.getRestApiTestData(15, "error_title");
		String errorDetail				= Utils.getRestApiTestData(15, "error_detail");
		String errorPointer				= Utils.getRestApiTestData(15, "error_pointer");
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);
		
		// Redirecting to facebook page
		String facebookAppURL = objGraphAPIExplorer.getfacebookAppURLForSpecifcApp();
		Utils.loadURL(driver, facebookAppURL);
		
		// Logging in using fb developer credentials
		objFacebookHome.clickLinkLogIn();
		objFacebookLogin.enterUserName();
		objFacebookLogin.enterPassword();
		objFacebookLogin.clickSubmit();
		
		// Getting fb access token and fb id
		String facebookAccessToken = objGraphAPIExplorer.getFacebookAccessToken();
		String facebookID = objGraphAPIExplorer.getFacebookID();

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse	= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid					= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP			= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);

		// Reassign values
		String fbID = facebookID.substring(0, facebookID.length() - 1);
		
		// User Login - POST Request
		Response fbLoginV1Response= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, fbID, deviceType, statusCode400);
		
		// Validate FBSignUpV1WithOTP error response - POST Request
		objFBLoginV1.validateFBLoginV1ErrorResponseHelperEndPoint_(objSoftAssertion, fbLoginV1Response, errorStatus, errorCode, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=59)
	public void validateFacebookAccessTokenMandatoryFieldFBLoginV1EndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(15, "fbaccessToken_detail_mandatory");
		String errorPointer				= Utils.getRestApiTestData(15, "fbaccessToken_pointer");
		String facebookAccessToken		= Utils.getRestApiTestData(8, "emptyValue");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// User Login - POST Request
		Response fbLoginV1Response= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, deviceType, statusCode422);
		
		// Validate FBSignUpV1WithOTP error response - POST Request
		objFBLoginV1.validateFBLoginV1ErrorResponseHelperEndPoint(objSoftAssertion, fbLoginV1Response, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=60)
	public void validateFacebookIDMandatoryFieldFBLoginV1EndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(15, "fb_userid_detail_mandatory");
		String errorPointer				= Utils.getRestApiTestData(15, "fb_userid_pointer");
		String facebookID				= Utils.getRestApiTestData(8, "double_quote");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// User Login - POST Request
		Response fbLoginV1Response= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, deviceType, statusCode422);
		
		// Validate FBSignUpV1WithOTP error response - POST Request
		objFBLoginV1.validateFBLoginV1ErrorResponseHelperEndPoint(objSoftAssertion, fbLoginV1Response, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	
	@Test(priority=61)
	public void validateDeviceInfoMandatoryFieldFBLoginV1EndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String mobileNumber				= Utils.generateMobileNumber(10);
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// User Login - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);
				
		// User Login - POST Request
		Response fbLoginV1Response= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, deviceType, statusCode200);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBLoginV1.validateFBLoginV1SucessResponseHelperEndPoint(objSoftAssertion, fbLoginV1Response, type, fullName);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=62)
	public void validateDeviceInfoMandatoryFieldFBLoginV1EndpointDeviceTypeApp() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String mobileNumber				= Utils.generateMobileNumber(10);
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorCode				= Utils.getRestApiTestData(10, "code");
		String errorTitle				= Utils.getRestApiTestData(10, "deviceinfo_title");
		String errorDetail				= Utils.getRestApiTestData(10, "deviceinfo_detail");
		String errorParameter			= Utils.getRestApiTestData(10, "deviceinfo_pointer");
		String type						= Utils.getRestApiTestData(10, "type");
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// User Login - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);

		// User Login - POST Request
		Response fbLoginV1Response= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, deviceType, statusCode422);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBLoginV1.validateFBLoginV1ErrorResponseHelperEndPoint_(objSoftAssertion, fbLoginV1Response, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	
	@Test(priority=63)
	public void validateFBLoginV1EndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String mobileNumber				= Utils.generateMobileNumber(10);
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// User Login - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);

		// User Login - POST Request
		Response fbLoginV1Response= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, deviceType, statusCode200);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBLoginV1.validateFBLoginV1SucessResponseHelperEndPoint(objSoftAssertion, fbLoginV1Response, type, fullName);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=64)
	public void validateFBLoginV1EndpointDeviceTypeApp() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String mobileNumber				= Utils.generateMobileNumber(10);
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// User Login - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);
		
		// User Login - POST Request
		Response fbLoginV1Response= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, fcmID, deviceUniqueID, imeiNumber, deviceType, statusCode200);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBLoginV1.validateFBLoginV1SucessResponseHelperEndPoint(objSoftAssertion, fbLoginV1Response, type, fullName);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}
	
	
	
	@Test(priority=65)
	public void validateDeviceInfoInvalidFieldFBLoginV1EndpointDeviceTypeApp() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String mobileNumber				= Utils.generateMobileNumber(10);
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "deviceinfo_status");
		String errorCode				= Utils.getRestApiTestData(10, "deviceinfo_code");
		String errorTitle				= Utils.getRestApiTestData(10, "deviceinfo_title1");
		String errorDetail				= Utils.getRestApiTestData(10, "deviceinfo_detail1");
		String errorParameter			= Utils.getRestApiTestData(10, "deviceinfo_parameter");
		String type						= Utils.getRestApiTestData(10, "type");
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// User Login - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);


		fcmID					= Utils.getRestApiTestData(8, "emptyValue");
		deviceUniqueID			= Utils.getRestApiTestData(8, "emptyValue");
		imeiNumber				= Utils.getRestApiTestData(8, "emptyValue");
		
		// User Login - POST Request
		Response fbLoginV1Response= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, fcmID, deviceUniqueID, imeiNumber, deviceType, statusCode422);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBLoginV1.validateFBLoginV1ErrorResponseHelperEndPoint(objSoftAssertion, fbLoginV1Response, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}
	
		
	@Test(priority=66)
	public void validateDeviceTypeDesktopFieldMandatoryFBLoginV1EndPoint() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String type						= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "device_detail");
		String errorPointer				= Utils.getRestApiTestData(10, "device_parameter");
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);
		
		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// User Login - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);

		// Reassiging values
		deviceType				= Utils.getRestApiTestData(8, "emptyValue");
		
		// User Login - POST Request
		Response fbLoginV1Response= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, deviceType, statusCode422);
		
		// Validate FBSignUpV1WithOTP error response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint_(objSoftAssertion, fbLoginV1Response, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=67)
	public void validateDeviceTypeAppFieldMandatoryFBLoginV1EndPoint() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String type						= Utils.getRestApiTestData(10, "type");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "device_detail");
		String errorPointer				= Utils.getRestApiTestData(10, "device_parameter");
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// User Login - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);

		// Reassiging values
		deviceType				= Utils.getRestApiTestData(8, "emptyValue");
		
		// User Login - POST Request
		Response fbLoginV1Response= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, fcmID, deviceUniqueID, imeiNumber, deviceType, statusCode422);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint_(objSoftAssertion, fbLoginV1Response, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}
	
	
	@Test(priority=68)
	public void validateTypeMandatoryFieldFBLoginV1EndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String type						= Utils.getRestApiTestData(10, "type");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(11, "type_status");
		String errorTitle				= Utils.getRestApiTestData(11, "type_title");
		String errorDetail				= Utils.getRestApiTestData(11, "type_detail");
		String errorPointer				= Utils.getRestApiTestData(11, "type_pointer");
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// User Login - POST Request
		Response fbSignUpV1WithOTPResponse= objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);

		// Reassiging values
		typeAuth					= Utils.getRestApiTestData(8, "emptyValue");
		
		// User Login - POST Request
		Response fbLoginV1Response= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, deviceType, statusCode422);
		
		// Validate FBSignUpV1WithOTP error response - POST Request
		objFBLoginV1.validateFBLoginV1ErrorResponseHelperEndPoint(objSoftAssertion, fbLoginV1Response, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}
	
	
	@Test(priority=71)
	public void validateResponseHeaderFieldFBLoginV1EndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		Restutils objRestutils = new Restutils(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String type						= Utils.getRestApiTestData(10, "type");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String contentType				= Utils.getRestApiTestData(0, "accept_value");
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// User Login - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);
		
		// User Login - POST Request
		Response fbLoginV1Response= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, deviceType, statusCode200);
		
		objRestutils.validateReponseContentType(objSoftAssertion, fbLoginV1Response, contentType);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=72)
	public void validateResponseHeaderFieldFBLoginV1EndpointDeviceTypeApp() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		Restutils objRestutils = new Restutils(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String type						= Utils.getRestApiTestData(10, "type");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String contentType				= Utils.getRestApiTestData(0, "accept_value");
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// User Login - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);

		// User Login - POST Request
		Response fbLoginV1Response= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, fcmID, deviceUniqueID, imeiNumber, deviceType, statusCode200);
		
		objRestutils.validateReponseContentType(objSoftAssertion, fbLoginV1Response, contentType);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}


	@Test(priority=73)
	public void validateFBLoginV1EndpointInvalidDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String mobileNumber				= Utils.generateMobileNumber(10);
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		String errorStatus				= Utils.getRestApiTestData(10, "status_invalid_device");
		String errorCode				= Utils.getRestApiTestData(10, "code_invalid_device");
		String errorTitle				= Utils.getRestApiTestData(10, "title_invalid_device");
		String errorDetail				= Utils.getRestApiTestData(10, "detail_invalid_device");
		String errorParameter			= Utils.getRestApiTestData(10, "parameter_invalid_device");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// User Login - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);

		deviceType				= Utils.getRestApiTestData(4, "invalid_desktop");
		
		// User Login - POST Request
		Response fbLoginV1Response= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, deviceType, statusCode400);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBLoginV1.validateFBLoginV1ErrorResponseHelperEndPoint(objSoftAssertion, fbLoginV1Response, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=74)
	public void validateFBLoginV1EndpointInvalidDeviceTypeApp() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String mobileNumber				= Utils.generateMobileNumber(10);
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		String errorStatus				= Utils.getRestApiTestData(10, "status_invalid_device");
		String errorCode				= Utils.getRestApiTestData(10, "code_invalid_device");
		String errorTitle				= Utils.getRestApiTestData(10, "title_invalid_device");
		String errorDetail				= Utils.getRestApiTestData(10, "detail_invalid_device");
		String errorParameter			= Utils.getRestApiTestData(10, "parameter_invalid_device");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// User Login - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);

		deviceType				= Utils.getRestApiTestData(4, "invalid_desktop");
		
		// User Login - POST Request
		Response fbLoginV1Response= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, fcmID, deviceUniqueID, imeiNumber, deviceType, statusCode400);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBLoginV1.validateFBLoginV1ErrorResponseHelperEndPoint(objSoftAssertion, fbLoginV1Response, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=75)
	public void validateFBSignUpV1WithOTPEndpointDeviceTypeDesktopUsingfbLoginV1ResponseHREF() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);	
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		String fbSignUpV1WithOTPURL		= baseURL + Utils.getRestApiTestData(2, "fb_signup_v1_otp") + deviceType;
		String httpMethod				= "POST";
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String type						= Utils.getRestApiTestData(10, "type");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		String  accessTokenValue				= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// User Login - POST Request
		Response fbLoginV1Response				= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, deviceType, statusCode200);
		
		objFBLoginV1.validateFBLoginV1SucessResponseHelperEndPoint(objSoftAssertion, fbLoginV1Response, typeAuth, facebookID, facebookAccessToken, fbSignUpV1WithOTPURL, httpMethod);
		
		String fbSignUpEndpointURL = objFBLoginV1.getFBSignUpEndPointURL(fbLoginV1Response);
		
		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(fbSignUpEndpointURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=76)
	public void validateFBSignUpV1WithOTPEndpointDeviceTypeAppUsingfbLoginV1ResponseHREF() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		String fbSignUpV1WithOTPURL		= baseURL + Utils.getRestApiTestData(2, "fb_signup_v1_otp") + deviceType;
		String httpMethod				= "POST";
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String type						= Utils.getRestApiTestData(10, "type");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		String  accessTokenValue				= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// User Login - POST Request
		Response fbLoginV1Response				= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, fcmID, deviceUniqueID, imeiNumber, deviceType, statusCode200);
		
		objFBLoginV1.validateFBLoginV1SucessResponseHelperEndPoint(objSoftAssertion, fbLoginV1Response, typeAuth, facebookID, facebookAccessToken, fbSignUpV1WithOTPURL, httpMethod);
		
		String fbSignUpEndpointURL = objFBLoginV1.getFBSignUpEndPointURL(fbLoginV1Response);

		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(fbSignUpEndpointURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}


	@Test(priority=77)
	public void validateEmptyHeaderFBLoginV1EndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message");

		// User Login - POST Request
		Response fbLoginV1Response= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, deviceType, statusCode401);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBLoginV1.validateFBLoginV1ErrorResponseHelperEndPoint(objSoftAssertion, fbLoginV1Response, errorMessage);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=78)
	public void validateInvalidAuthorizationKeyValueFBLoginV1EndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message_invalid_json");
		String accessTokenValue			= Utils.getRestApiTestData(0, "auth_invalid_value");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");

		// User Login - POST Request
		Response fbLoginV1Response= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, deviceType, statusCode401);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBLoginV1.validateFBLoginV1ErrorResponseHelperEndPoint(objSoftAssertion, fbLoginV1Response, errorMessage);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=79)
	public void validateInvalidContentTypeKeyValueFBLoginV1EndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String mobileNumber				= Utils.generateMobileNumber(10);
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode415				= Integer.parseInt(Utils.getRestApiTestData(9, "status_415"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_invalid_value");
		String errorStatus				= Utils.getRestApiTestData(12, "error_status");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// User Login - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);

		// User Login - POST Request
		Response fbLoginV1Response= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, deviceType, statusCode415);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBLoginV1.validateFBLoginV1InvalidHeaderErrorResponseHelperEndPoint(objSoftAssertion, fbLoginV1Response, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=80)
	public void validateInvalidAcceptTypeKeyValueFBLoginV1EndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String mobileNumber				= Utils.generateMobileNumber(10);
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode415				= Integer.parseInt(Utils.getRestApiTestData(9, "status_415"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_invalid_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");
		String errorStatus				= Utils.getRestApiTestData(12, "error_status");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// User Login - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);

		// User Login - POST Request
		Response fbLoginV1Response= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, deviceType, statusCode415);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBLoginV1.validateFBLoginV1InvalidHeaderErrorResponseHelperEndPoint(objSoftAssertion, fbLoginV1Response, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=81)
	public void validateEmptyAuthorizationKeyValueFBLoginV1EndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String mobileNumber				= Utils.generateMobileNumber(10);
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");
		String errorMessage				= Utils.getRestApiTestData(12, "message");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// User Login - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);
	      
		
		accessTokenValue		= Utils.getRestApiTestData(8, "emptyValue");
			
		// User Login - POST Request
		Response fbLoginV1Response= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, deviceType, statusCode401);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBLoginV1.validateFBLoginV1ErrorResponseHelperEndPoint(objSoftAssertion, fbLoginV1Response, errorMessage);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=82)
	public void validateEmptyAcceptTypeKeyValueFBLoginV1EndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String mobileNumber				= Utils.generateMobileNumber(10);
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode406				= Integer.parseInt(Utils.getRestApiTestData(9, "status_406"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		String acceptValue				= Utils.getRestApiTestData(8, "emptyValue");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");
		String errorStatus				= Utils.getRestApiTestData(12, "error_status_406");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title_notacceptable");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail_accept_header");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// User Login - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);

		// User Login - POST Request
		Response fbLoginV1Response= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, deviceType, statusCode406);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBLoginV1.validateFBLoginV1InvalidHeaderErrorResponseHelperEndPoint(objSoftAssertion, fbLoginV1Response, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=83)
	public void validateEmptyContentTypeKeyValueFBLoginV1EndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String mobileNumber				= Utils.generateMobileNumber(10);
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode415				= Integer.parseInt(Utils.getRestApiTestData(9, "status_415"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(8, "emptyValue");
		String errorStatus				= Utils.getRestApiTestData(12, "error_status");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// User Login - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);

		// User Login - POST Request
		Response fbLoginV1Response= objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, deviceType, statusCode415);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBLoginV1.validateFBLoginV1InvalidHeaderErrorResponseHelperEndPoint(objSoftAssertion, fbLoginV1Response, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}


}