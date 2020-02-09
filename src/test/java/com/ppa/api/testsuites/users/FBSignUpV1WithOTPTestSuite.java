package com.ppa.api.testsuites.users;

import java.awt.AWTException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.admin.pages.AdminCoreFunction;
import com.admin.pages.Login;
import com.admin.pages.Users;
import com.fb.frontend.pages.FacebookGraphAPIExplorer;
import com.fb.frontend.pages.FacebookHome;
import com.fb.frontend.pages.FacebookLogin;
import com.fb.frontend.pages.FacebookTestUsers;
import com.ppa.api.base.Base;
import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.endpoints.helper.FBSignUpV1WithOTP;
import com.ppa.api.endpoints.helper.HelperAccessToken;
import com.ppa.api.endpoints.helper.SignUpOTP;
import com.ppa.api.endpoints.helper.SignUpV1WithOTP;
import com.ppa.api.utilities.Restutils;
import com.ppa.api.utilities.Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FBSignUpV1WithOTPTestSuite extends Base {

	String facebookAccessToken;
	String facebookID;

	@Test(priority=0)
	public void aboutGetFacebookAccessTokenAndID() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FacebookGraphAPIExplorer objGraphAPIExplorer = new FacebookGraphAPIExplorer(driver, logger);
		FacebookHome objFacebookHome = new FacebookHome(driver, logger);
		FacebookLogin objFacebookLogin = new FacebookLogin(driver, logger);
		
		// Assigning values
		String fbEmail	= Utils.getRestApiTestData(5, "fb_email");
		
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

	
	
	@Test(priority=1)
	public void validateBVAForMobileNumberWithMaximumCharactersFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
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

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=2)
	public void validateBVAForMobileNumberWithMaximumCharactersMinusOneFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "mobilenumber_detail_least_char");
		String errorPointer				= Utils.getRestApiTestData(10, "mobilenumber_pointer");
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

		// Reassigning values
		mobileNumber				= Utils.generateMobileNumber(9);
		
		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate FBSignUpV1WithOTP error response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=3)
	public void validateBVAForMobileNumberWithMaximumCharactersPlusOneFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "mobilenumber_detail_least_char");
		String errorPointer				= Utils.getRestApiTestData(10, "mobilenumber_pointer");
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

		// Reassigning values
		mobileNumber				= Utils.generateMobileNumber(11);

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate FBSignUpV1WithOTP error response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	
	@Test(priority=4)
	public void validateBVAForOTPWithMaximumCharactersFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
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

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=5)
	public void validateBVAForOTPWithMaximumCharactersMinusOneFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "otp_detail_least_char");
		String errorPointer				= Utils.getRestApiTestData(10, "otp_pointer");
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

		// Reassigning values
		signUpOTP				= Utils.generateRandomNumber(5);
		
		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate FBSignUpV1WithOTP error response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=6)
	public void validateBVAForOTPWithMaximumCharactersPlusOneFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "otp_detail_least_char");
		String errorPointer				= Utils.getRestApiTestData(10, "otp_pointer");
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

		// Reassigning values
		signUpOTP				= Utils.generateRandomNumber(7);

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate FBSignUpV1WithOTP error response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	
	@Test(priority=7)
	public void validateBVAForGUIDWithMaximumCharactersFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
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

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=8)
	public void validateBVAForGUIDWithMaximumCharactersMinusOneFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "guid_status");
		String errorCode				= Utils.getRestApiTestData(10, "guid_code");
		String errorTitle				= Utils.getRestApiTestData(10, "guid_title");
		String errorDetail				= Utils.getRestApiTestData(10, "guid_detail");
		String errorParameter			= Utils.getRestApiTestData(10, "guid_parameter");
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

		// Reassigning values
		guid							= guid.substring(0, guid.length() - 1);

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);
		
		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode400);
		
		// Validate FBSignUpV1WithOTP error response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	
	@Test(priority=10)
	public void validateBVAForFBAccessTokenWithMaximumCharactersFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
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
		Object[] signUpOTPResponse	= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid					= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP			= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=11)
	public void validateBVAForFBAccessTokenWithMaximumCharactersMinusOneFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorCode				= Utils.getRestApiTestData(16, "error_code");
		String errorTitle				= Utils.getRestApiTestData(16, "error_title");
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);
		
		// Reassign values
		String facebookAccesstoken = Utils.generateRandomAlphabets(20);

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccesstoken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode400);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorCode, errorTitle);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	
	@Test(priority=12)
	public void validateBVAForFBIDWithMaximumCharactersFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
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
		Object[] signUpOTPResponse	= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid					= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP			= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=13)
	public void validateBVAForFBIDWithMaximumCharactersMinusOneFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
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
		
		// Reassign values
		String facebookid = facebookID.substring(0, facebookID.length() - 1);

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookid, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode400);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint_(objSoftAssertion, fbSignUpV1WithOTPResponse, errorStatus, errorCode, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=14)
	public void validateFacebookAccessTokenMandatoryFieldFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(15, "fbaccessToken_detail_mandatory");
		String errorPointer				= Utils.getRestApiTestData(15, "fbaccessToken_pointer");
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
		
		String fbAccessToken = Utils.getRestApiTestData(8, "emptyValue");

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fbAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate FBSignUpV1WithOTP error response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=15)
	public void validateFacebookIDMandatoryFieldFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(15, "fb_userid_detail_mandatory");
		String errorPointer				= Utils.getRestApiTestData(15, "fb_userid_pointer");
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);
		
		String facebookID = Utils.getRestApiTestData(8, "double_quote");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate FBSignUpV1WithOTP error response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=16)
	public void validateMobileNumberMandatoryFieldFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "mobilenumber_detail_mandatory");
		String errorPointer				= Utils.getRestApiTestData(10, "mobilenumber_pointer");
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

		// Reassigning values
		mobileNumber					= Utils.getRestApiTestData(8, "double_quote");

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate FBSignUpV1WithOTP error response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=17)
	public void validateOTPGuidMandatoryFieldFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "guid_detail_mandatory");
		String errorPointer				= Utils.getRestApiTestData(10, "guid_pointer");
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);
		
		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= Utils.getRestApiTestData(8, "emptyValue");

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate FBSignUpV1WithOTP error response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=18)
	public void validateOTPMandatoryFieldFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "otp_detail_mandatory");
		String errorPointer				= Utils.getRestApiTestData(10, "otp_pointer");
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
		String signUpOTP				= Utils.getRestApiTestData(8, "emptyValue");

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate FBSignUpV1WithOTP error response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=19)
	public void validateIPAddressMandatoryFieldFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(8, "emptyValue");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "ip_detail_mandatory");
		String errorPointer				= Utils.getRestApiTestData(10, "ip_pointer");
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

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate FBSignUpV1WithOTP error response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=20)
	public void validateReferralIDMandatoryFieldFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
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

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);
	
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=21)
	public void validateInvalidIPAddressFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "invalid_ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "ip_detail_Valid");
		String errorPointer				= Utils.getRestApiTestData(10, "ip_pointer");
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

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate FBSignUpV1WithOTP error response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	
	@Test(priority=22)
	public void validateDeviceInfoMandatoryFieldFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
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

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=23)
	public void validateDeviceInfoMandatoryFieldFBSignUpV1WithOTPEndpointDeviceTypeApp() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorCode				= Utils.getRestApiTestData(10, "code");
		String errorTitle				= Utils.getRestApiTestData(10, "deviceinfo_title");
		String errorDetail				= Utils.getRestApiTestData(10, "deviceinfo_detail");
		String errorParameter			= Utils.getRestApiTestData(10, "deviceinfo_pointer");
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

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint_(objSoftAssertion, fbSignUpV1WithOTPResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=24)
	public void validateFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
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

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=25)
	public void validateFBSignUpV1WithOTPEndpointDeviceTypeApp() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		String mobileNumber				= Utils.generateMobileNumber(10);
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

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}


	
	@Test(priority=26)
	public void validateExistingFacebookFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
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

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);

		// Reassigning values
		mobileNumber				= Utils.generateMobileNumber(10);
		
		// Get GUID for mobile number - POST Request
		signUpOTPResponse			= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode200);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=27)
	public void validateExistingMobileNumberFieldFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "mobilenumber_detail_already");
		String errorPointer				= Utils.getRestApiTestData(10, "mobilenumber_pointer");
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

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);
		
		// Reassigning values
		String mblNumber			= Utils.generateMobileNumber(10);
		
		// Get GUID for mobile number - POST Request
		signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mblNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		guid					= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mblNumber);

		// New User Signup - POST Request
		fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	
	@Test(priority=28)
	public void validateDeviceInfoInvalidFieldFBSignUpV1WithOTPEndpointDeviceTypeApp() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(8, "emptyValue");
		String deviceUniqueID			= Utils.getRestApiTestData(8, "emptyValue");
		String imeiNumber				= Utils.getRestApiTestData(8, "emptyValue");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "deviceinfo_status");
		String errorCode				= Utils.getRestApiTestData(10, "deviceinfo_code");
		String errorTitle				= Utils.getRestApiTestData(10, "deviceinfo_title1");
		String errorDetail				= Utils.getRestApiTestData(10, "deviceinfo_detail1");
		String errorParameter			= Utils.getRestApiTestData(10, "deviceinfo_parameter");
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

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode422);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=29)
	public void validateOTPExpiryFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "otp_status");
		String errorCode				= Utils.getRestApiTestData(10, "otp_code");
		String errorTitle				= Utils.getRestApiTestData(10, "otp_title");
		String errorDetail				= Utils.getRestApiTestData(10, "otp_detail");
		String errorParameter			= Utils.getRestApiTestData(10, "otp_parameter");
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
		
		Thread.sleep(181000);

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode400);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	
	@Test(priority=30)
	public void validateSecondGeneratedOTPandGUIDValidFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
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
		
		// Get GUID for mobile number - POST Request
		signUpOTPResponse				= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		guid							= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		signUpOTP						= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse= objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=31)
	public void validateFirstGeneratedGUIDandSecondGeneratedOTPCombinationFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "guid_status");
		String errorCode				= Utils.getRestApiTestData(10, "guid_code");
		String errorTitle				= Utils.getRestApiTestData(10, "guid_title");
		String errorDetail				= Utils.getRestApiTestData(10, "guid_detail");
		String errorParameter			= Utils.getRestApiTestData(10, "guid_parameter");
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
		
		// Get GUID for mobile number - POST Request
		signUpOTPResponse				= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Get OTP for mobile number - GET Request
		signUpOTP						= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse= objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode400);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	
	@Test(priority=32)
	public void validateInvalidMobileNumberFormatFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "mobilenumber_detail_invalid");
		String errorPointer				= Utils.getRestApiTestData(10, "mobilenumber_pointer");
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

		// Reassigning values
		mobileNumber					= "5" + Utils.generateMobileNumber(9);
		
		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate FBSignUpV1WithOTP error response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
	
		// Reassigning values
		mobileNumber					= "4" + Utils.generateMobileNumber(9);
		
		// New User Signup - POST Request
		fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate FBSignUpV1WithOTP error response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);

		// Reassigning values
		mobileNumber					= "3" + Utils.generateMobileNumber(9);
		
		// New User Signup - POST Request
		fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate FBSignUpV1WithOTP error response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);

		// Reassigning values
		mobileNumber					= "2" + Utils.generateMobileNumber(9);
		
		// New User Signup - POST Request
		fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate FBSignUpV1WithOTP error response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);

		// Reassigning values
		mobileNumber					= "1" + Utils.generateMobileNumber(9);
		
		// New User Signup - POST Request
		fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate FBSignUpV1WithOTP error response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);

		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=33)
	public void validateDeviceTypeDesktopFieldMandatoryFBSignUpV1WithOTPEndPoint() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(8, "emptyValue");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
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
		String guid						= (String) signUpOTPResponse[1];

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate FBSignUpV1WithOTP error response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint_(objSoftAssertion, fbSignUpV1WithOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=34)
	public void validateDeviceTypeAppFieldMandatoryFBSignUpV1WithOTPEndPoint() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(8, "emptyValue");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
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

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode422);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint_(objSoftAssertion, fbSignUpV1WithOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=35)
	public void validateTypeMandatoryFieldFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(8, "emptyValue");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
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

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate FBSignUpV1WithOTP error response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=38)
	public void validateResponseHeaderFieldFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		Restutils objRestutils = new Restutils(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
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

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objRestutils.validateReponseContentType(objSoftAssertion, fbSignUpV1WithOTPResponse, contentType);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=39)
	public void validateResponseHeaderFieldFBSignUpV1WithOTPEndpointDeviceTypeApp() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		Restutils objRestutils = new Restutils(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
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

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		objRestutils.validateReponseContentType(objSoftAssertion, fbSignUpV1WithOTPResponse, contentType);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=40)
	public void validateFacebookIDDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Admin Object creations
		Users objUsers					= new Users(driver, logger);
		AdminCoreFunction objAdminCoreFunction = new AdminCoreFunction(driver, logger);
		Login objLogin					= new Login(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword			= Utils.getConfigurationSetupTestData(0, "admin_password");
		
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

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);

		// Redirecting to admin panel
		Utils.loadURL(driver, adminURL);
		
		Thread.sleep(1000);
		
		objLogin.selectValueAdminDropdown(driver, adminDropDown);
		objLogin.enterUserName(driver, adminUsername);
		objLogin.enterPassword(driver, adminPassword);
		objLogin.clickLogin(driver);
		
		// Redirecting to user page
		objAdminCoreFunction.clickUsersMenu();
		objAdminCoreFunction.clickUsersSubMenu();
		
		Thread.sleep(1000);
		
		objUsers.selectSearchByDropDown("User Email");
		objUsers.enterKeyword(fbEmail);
		objUsers.clickSubmitButton();
		objUsers.clickEyeButton();
		String adminFacebookID = objUsers.extractFacebookID();
		
		// Asserting facebook id taken from user page
		objFBSignUpV1WithOTP.validateFacebookIDAfterMerge(facebookID, adminFacebookID);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=41)
	public void validateFacebookIDDeviceTypeApp() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Admin Object creations
		Users objUsers					= new Users(driver, logger);
		AdminCoreFunction objAdminCoreFunction = new AdminCoreFunction(driver, logger);
		Login objLogin					= new Login(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword			= Utils.getConfigurationSetupTestData(0, "admin_password");
		
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

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);

		// Redirecting to admin panel
		Utils.loadURL(driver, adminURL);

		
		Thread.sleep(1000);
		
		objLogin.selectValueAdminDropdown(driver, adminDropDown);
		objLogin.enterUserName(driver, adminUsername);
		objLogin.enterPassword(driver, adminPassword);
		objLogin.clickLogin(driver);
		
		// Redirecting to user page
		objAdminCoreFunction.clickUsersMenu();
		objAdminCoreFunction.clickUsersSubMenu();
		
		Thread.sleep(1000);
		
		objUsers.selectSearchByDropDown("User Email");
		objUsers.enterKeyword(fbEmail);
		objUsers.clickSubmitButton();
		objUsers.clickEyeButton();
		String adminFacebookID = objUsers.extractFacebookID();
		
		// Asserting facebook id taken from user page
		objFBSignUpV1WithOTP.validateFacebookIDAfterMerge(facebookID, adminFacebookID);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=42)
	public void validateFacebookMergeDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FacebookHome objFacebookHome = new FacebookHome(driver, logger);
		FacebookLogin objFacebookLogin = new FacebookLogin(driver, logger);
		FacebookTestUsers objFacebookTestUsers = new FacebookTestUsers(driver, logger);
		
		// Admin Object creations
		Users objUsers					= new Users(driver, logger);
		AdminCoreFunction objAdminCoreFunction = new AdminCoreFunction(driver, logger);
		Login objLogin					= new Login(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword			= Utils.getConfigurationSetupTestData(0, "admin_password");
		
		// Redirecting to facebook page
		Utils.loadURL(driver, Utils.getConfigurationSetupTestData(0, "facebook_url"));
		
		// Logging in using fb developer credentials
		objFacebookHome.clickLinkLogIn();
		objFacebookLogin.enterUserName();
		objFacebookLogin.enterPassword();
		objFacebookLogin.clickSubmit();

		String facebookAppURLForTestUsers = objFacebookTestUsers.getfacebookAppURLForCreatingTestUsers();
		Utils.loadURL(driver, facebookAppURLForTestUsers);
		
		// Create new test user
		objFacebookTestUsers.clickAddButton();
		objFacebookTestUsers.clickCreateTestUserButton();
		
		// Getting fb access token and fb id
		objFacebookTestUsers.changePassword(password, password);
		objFacebookTestUsers.addPermission("email");
		String facebookAccessToken = objFacebookTestUsers.getFacebookAccessToken();
		String facebookID = objFacebookTestUsers.getFacebookID();
		String facebookEmail = objFacebookTestUsers.getFacebookEmail();
		
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
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, facebookEmail, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		// Redirecting to admin panel
		Utils.loadURL(driver, adminURL);
		
		Thread.sleep(1000);
		
		objLogin.selectValueAdminDropdown(driver, adminDropDown);
		objLogin.enterUserName(driver, adminUsername);
		objLogin.enterPassword(driver, adminPassword);
		objLogin.clickLogin(driver);
		
		// Redirecting to user page
		objAdminCoreFunction.clickUsersMenu();
		objAdminCoreFunction.clickUsersSubMenu();
		
		Thread.sleep(1000);
		
		objUsers.selectSearchByDropDown("User Email");
		objUsers.enterKeyword(facebookEmail);
		objUsers.clickSubmitButton();
		objUsers.clickEyeButton();
		String adminFacebookID = objUsers.extractFacebookID();
		
		// Asserting facebook id taken from user page
		/*objFBSignUpV1WithOTP.validateFacebookIDBeforeMerge(facebookID, adminFacebookID);*/
		
		// Getting Access Token for Helper end point - GET Request
		helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		mobileNumber				= Utils.generateMobileNumber(10);
		
		// Get GUID for mobile number - POST Request
		signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		guid						= (String) signUpOTPResponse[1];

		// Get OTP for mobile number - GET Request
		signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode200);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);

		// Redirecting to user page
		objAdminCoreFunction.clickUsersMenu();
		objAdminCoreFunction.clickUsersSubMenu();
		
		Thread.sleep(1000);
		
		objUsers.clickClearButtonUserForm();
		objUsers.selectSearchByDropDown("User Email");
		objUsers.enterKeyword(facebookEmail);
		objUsers.clickSubmitButton();
		objUsers.clickEyeButton();
		adminFacebookID = objUsers.extractFacebookID();
		
		// Asserting facebook id taken from user page
		objFBSignUpV1WithOTP.validateFacebookIDAfterMerge(facebookID, adminFacebookID);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=43)
	public void validateFacebookMergeDeviceTypeApp() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FacebookHome objFacebookHome = new FacebookHome(driver, logger);
		FacebookLogin objFacebookLogin = new FacebookLogin(driver, logger);
		FacebookTestUsers objFacebookTestUsers = new FacebookTestUsers(driver, logger);
		
		// Admin Object creations
		Users objUsers					= new Users(driver, logger);
		AdminCoreFunction objAdminCoreFunction = new AdminCoreFunction(driver, logger);
		Login objLogin					= new Login(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword			= Utils.getConfigurationSetupTestData(0, "admin_password");
		
		// Redirecting to facebook page
		Utils.loadURL(driver, Utils.getConfigurationSetupTestData(0, "facebook_url"));
		
		// Logging in using fb developer credentials
		objFacebookHome.clickLinkLogIn();
		objFacebookLogin.enterUserName();
		objFacebookLogin.enterPassword();
		objFacebookLogin.clickSubmit();

		String facebookAppURLForTestUsers = objFacebookTestUsers.getfacebookAppURLForCreatingTestUsers();
		Utils.loadURL(driver, facebookAppURLForTestUsers);
		
		// Create new test user
		objFacebookTestUsers.clickAddButton();
		objFacebookTestUsers.clickCreateTestUserButton();
		
		// Getting fb access token and fb id
		objFacebookTestUsers.changePassword(password, password);
		objFacebookTestUsers.addPermission("email");
		String facebookAccessToken = objFacebookTestUsers.getFacebookAccessToken();
		String facebookID = objFacebookTestUsers.getFacebookID();
		String facebookEmail = objFacebookTestUsers.getFacebookEmail();
		
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
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, facebookEmail, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);

		// Redirecting to admin panel
		Utils.loadURL(driver, adminURL);
		
		Thread.sleep(1000);
		
		objLogin.selectValueAdminDropdown(driver, adminDropDown);
		objLogin.enterUserName(driver, adminUsername);
		objLogin.enterPassword(driver, adminPassword);
		objLogin.clickLogin(driver);
		
		// Redirecting to user page
		objAdminCoreFunction.clickUsersMenu();
		objAdminCoreFunction.clickUsersSubMenu();
		
		Thread.sleep(1000);
		
		objUsers.selectSearchByDropDown("User Email");
		objUsers.enterKeyword(facebookEmail);
		objUsers.clickSubmitButton();
		objUsers.clickEyeButton();
		String adminFacebookID = objUsers.extractFacebookID();
		
		// Asserting facebook id taken from user page
		objFBSignUpV1WithOTP.validateFacebookIDBeforeMerge(facebookID, adminFacebookID);

		// Getting Access Token for Helper end point - GET Request
		helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		mobileNumber			= Utils.generateMobileNumber(10);
		
		// Get GUID for mobile number - POST Request
		signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);
		
		// Assigning signUpOTP response values to variables
		guid					= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode200);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, fullName);


		// Redirecting to user page
		objAdminCoreFunction.clickUsersMenu();
		objAdminCoreFunction.clickUsersSubMenu();
		
		Thread.sleep(1000);
		
		objUsers.clickClearButtonUserForm();
		objUsers.selectSearchByDropDown("User Email");
		objUsers.enterKeyword(facebookEmail);
		objUsers.clickSubmitButton();
		objUsers.clickEyeButton();
		adminFacebookID = objUsers.extractFacebookID();
		
		// Asserting facebook id taken from user page
		objFBSignUpV1WithOTP.validateFacebookIDAfterMerge(facebookID, adminFacebookID);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=44)
	public void validateSignUpMergeDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FacebookHome objFacebookHome = new FacebookHome(driver, logger);
		FacebookLogin objFacebookLogin = new FacebookLogin(driver, logger);
		FacebookTestUsers objFacebookTestUsers = new FacebookTestUsers(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "email_detail_existing");
		String errorPointer				= Utils.getRestApiTestData(10, "email_pointer");
		
		// Redirecting to facebook page
		Utils.loadURL(driver, Utils.getConfigurationSetupTestData(0, "facebook_url"));
		
		// Logging in using fb developer credentials
		objFacebookHome.clickLinkLogIn();
		objFacebookLogin.enterUserName();
		objFacebookLogin.enterPassword();
		objFacebookLogin.clickSubmit();

		String facebookAppURLForTestUsers = objFacebookTestUsers.getfacebookAppURLForCreatingTestUsers();
		Utils.loadURL(driver, facebookAppURLForTestUsers);
		
		// Create new test user
		objFacebookTestUsers.clickAddButton();
		objFacebookTestUsers.clickCreateTestUserButton();
		
		// Getting fb access token and fb id
		objFacebookTestUsers.changePassword(password, password);
		objFacebookTestUsers.addPermission("email");
		String facebookAccessToken = objFacebookTestUsers.getFacebookAccessToken();
		String facebookUserName = objFacebookTestUsers.getFacebookUserName();
		String facebookID = objFacebookTestUsers.getFacebookID();
		String facebookEmail = objFacebookTestUsers.getFacebookEmail();
		
		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		String accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1];

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, facebookUserName);
		
		// Getting Access Token for Helper end point - GET Request
		helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		mobileNumber				= Utils.generateMobileNumber(10);
		
		// Get GUID for mobile number - POST Request
		signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		signUpOTP				= objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, facebookEmail, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=45)
	public void validateSignUpMergeDeviceTypeApp() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FacebookHome objFacebookHome = new FacebookHome(driver, logger);
		FacebookLogin objFacebookLogin = new FacebookLogin(driver, logger);
		FacebookTestUsers objFacebookTestUsers = new FacebookTestUsers(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "fb_username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "email_detail_existing");
		String errorPointer				= Utils.getRestApiTestData(10, "email_pointer");
		
		// Redirecting to facebook page
		Utils.loadURL(driver, Utils.getConfigurationSetupTestData(0, "facebook_url"));
		
		// Logging in using fb developer credentials
		objFacebookHome.clickLinkLogIn();
		objFacebookLogin.enterUserName();
		objFacebookLogin.enterPassword();
		objFacebookLogin.clickSubmit();

		String facebookAppURLForTestUsers = objFacebookTestUsers.getfacebookAppURLForCreatingTestUsers();
		Utils.loadURL(driver, facebookAppURLForTestUsers);
		
		// Create new test user
		objFacebookTestUsers.clickAddButton();
		objFacebookTestUsers.clickCreateTestUserButton();
		
		// Getting fb access token and fb id
		objFacebookTestUsers.changePassword(password, password);
		objFacebookTestUsers.addPermission("email");
		String facebookAccessToken = objFacebookTestUsers.getFacebookAccessToken();
		String facebookUserName = objFacebookTestUsers.getFacebookUserName();
		String facebookID = objFacebookTestUsers.getFacebookID();
		String facebookEmail = objFacebookTestUsers.getFacebookEmail();

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);
		
		// Assigning signUpOTP response values to variables
		String guid					= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, type, facebookUserName);
		
		
		// Getting Access Token for Helper end point - GET Request
		helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		mobileNumber			= Utils.generateMobileNumber(10);
		
		// Get GUID for mobile number - POST Request
		signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		signUpOTP				= objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, facebookEmail, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode422);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}
	
	
	@Test(priority=46)
	public void validateFBSignUpV1WithOTPEndpointInvalidDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "invalid_desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
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

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode400);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=47)
	public void validateFBSignUpV1WithOTPEndpointInvalidDeviceTypeApp() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "invalid_desktop");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
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

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode400);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=48)
	public void validateFBSignUpV1WithOTPDeviceTypeDesktopEmailPermissionDenied() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FacebookHome objFacebookHome = new FacebookHome(driver, logger);
		FacebookLogin objFacebookLogin = new FacebookLogin(driver, logger);
		FacebookTestUsers objFacebookTestUsers = new FacebookTestUsers(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorCode				= Utils.getRestApiTestData(16, "error_code");
		String errorTitle				= Utils.getRestApiTestData(16, "error_title");
		
		// Redirecting to facebook page
		Utils.loadURL(driver, Utils.getConfigurationSetupTestData(0, "facebook_url"));
		
		// Logging in using fb developer credentials
		objFacebookHome.clickLinkLogIn();
		objFacebookLogin.enterUserName();
		objFacebookLogin.enterPassword();
		objFacebookLogin.clickSubmit();

		String facebookAppURLForTestUsers = objFacebookTestUsers.getfacebookAppURLForCreatingTestUsers();
		Utils.loadURL(driver, facebookAppURLForTestUsers);
		
		// Create new test user
		objFacebookTestUsers.clickAddButton();
		objFacebookTestUsers.clickCreateTestUserButton();
		
		// Getting fb access token and fb id
		objFacebookTestUsers.changePassword(password, password);
		objFacebookTestUsers.addPermission("");
		String facebookAccessToken = objFacebookTestUsers.getFacebookAccessToken();
		String facebookID = objFacebookTestUsers.getFacebookID();
		
		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		String accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1];

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode400);
		
		// Validate FBSignUpV1WithOTP error response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorCode, errorTitle);
				
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=49)
	public void validateFBSignUpV1WithOTPDeviceTypeAppEmailPermissionDenied() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FacebookHome objFacebookHome = new FacebookHome(driver, logger);
		FacebookLogin objFacebookLogin = new FacebookLogin(driver, logger);
		FacebookTestUsers objFacebookTestUsers = new FacebookTestUsers(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String errorCode				= Utils.getRestApiTestData(16, "error_code");
		String errorTitle				= Utils.getRestApiTestData(16, "error_title");
		
		// Redirecting to facebook page
		Utils.loadURL(driver, Utils.getConfigurationSetupTestData(0, "facebook_url"));
		
		// Logging in using fb developer credentials
		objFacebookHome.clickLinkLogIn();
		objFacebookLogin.enterUserName();
		objFacebookLogin.enterPassword();
		objFacebookLogin.clickSubmit();

		String facebookAppURLForTestUsers = objFacebookTestUsers.getfacebookAppURLForCreatingTestUsers();
		Utils.loadURL(driver, facebookAppURLForTestUsers);
		
		// Create new test user
		objFacebookTestUsers.clickAddButton();
		objFacebookTestUsers.clickCreateTestUserButton();
		
		// Getting fb access token and fb id
		objFacebookTestUsers.changePassword(password, password);
		//objFacebookTestUsers.addPermission("email");
		String facebookAccessToken = objFacebookTestUsers.getFacebookAccessToken();
		String facebookID = objFacebookTestUsers.getFacebookID();

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);
		
		// Assigning signUpOTP response values to variables
		String guid					= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode400);
		
		// Validate FBSignUpV1WithOTP error response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorCode, errorTitle);
				
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=50)
	public void validateEmptyHeaderFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message");

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, deviceType, statusCode401);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorMessage);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=51)
	public void validateInvalidAuthorizationKeyValueFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message_invalid_json");
		String accessTokenValue			= Utils.getRestApiTestData(0, "auth_invalid_value");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, deviceType, statusCode401);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorMessage);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=52)
	public void validateInvalidContentTypeKeyValueFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode415				= Integer.parseInt(Utils.getRestApiTestData(9, "status_415"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
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

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, deviceType, statusCode415);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPInvalidHeaderErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=53)
	public void validateInvalidAcceptTypeKeyValueFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode415				= Integer.parseInt(Utils.getRestApiTestData(9, "status_415"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
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

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, deviceType, statusCode415);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPInvalidHeaderErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=54)
	public void validateEmptyAuthorizationKeyValueFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String fbEmail					= Utils.getRestApiTestData(5, "fb_email");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");
		String errorMessage				= Utils.getRestApiTestData(12, "message");
		
		// Reset fb email
		objFBSignUpV1WithOTP.resetFacebookAccount(fbEmail);

		// Getting Access Token for Helper end point - GET Request
		objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String accessTokenValue		= Utils.getRestApiTestData(8, "emptyValue");

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, deviceType, statusCode401);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorMessage);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=55)
	public void validateEmptyAcceptTypeKeyValueFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode406				= Integer.parseInt(Utils.getRestApiTestData(9, "status_406"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
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

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, deviceType, statusCode406);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPInvalidHeaderErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=56)
	public void validateEmptyContentTypeKeyValueFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode415				= Integer.parseInt(Utils.getRestApiTestData(9, "status_415"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
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

		// New User Signup - POST Request
		Response fbSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, deviceType, statusCode415);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPInvalidHeaderErrorResponseHelperEndPoint(objSoftAssertion, fbSignUpV1WithOTPResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

}