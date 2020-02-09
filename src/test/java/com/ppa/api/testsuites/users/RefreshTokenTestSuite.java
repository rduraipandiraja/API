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
import com.ppa.api.endpoints.helper.RefreshToken;
import com.ppa.api.endpoints.helper.SignUpOTP;
import com.ppa.api.endpoints.helper.SignUpV1WithOTP;
import com.ppa.api.endpoints.helper.UserLogin;
import com.ppa.api.endpoints.helper.UserSignUp;
import com.ppa.api.endpoints.users.MyEarnings;
import com.ppa.api.utilities.Restutils;
import com.ppa.api.utilities.Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RefreshTokenTestSuite extends Base {

	@Test(priority=0)
	public void validateRefreshTokenGeneratedInResponseUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		MyEarnings objMyEarnings = new MyEarnings(logger);
		RefreshToken objRefreshToken = new RefreshToken(logger);
		
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

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		// Getting Access Token from user signup end point - POST Request
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		// Getting My earnings response from my earnings end point - GET Request
		Response myEarningsResponse	= objMyEarnings.getMyEarningsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		// Validate MyEarnings success response - GET Request
		objMyEarnings.validateMyEarningsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsResponse, email);
		
		// Getting refresh token from SignupV1WithOTP success response
		String refreshToken = objUserSignUp.getRefreshToken(userSignUpResponse);

		// Getting Access Token for Helper end point - GET Request
		helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response refreshTokenResponse = objRefreshToken.refreshTokenHelperEndPoint(baseURL, accessTokenValue, typeAuth, refreshToken, statusCode200);
		
		// Getting Access Token for refresh token end point - POST Request
		accessTokenValue		= objRefreshToken.getAccessToken(refreshTokenResponse);
		
		// Getting My earnings response from my earnings end point - GET Request
		myEarningsResponse	= objMyEarnings.getMyEarningsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		// Validate MyEarnings success response - GET Request
		objMyEarnings.validateMyEarningsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsResponse, email);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=1)
	public void validateRefreshTokenGeneratedInResponseUserSignUpEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		MyEarnings objMyEarnings = new MyEarnings(logger);
		RefreshToken objRefreshToken = new RefreshToken(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
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

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		// Getting Access Token from user signup end point - POST Request
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		// Getting My earnings response from my earnings end point - GET Request
		Response myEarningsResponse	= objMyEarnings.getMyEarningsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		// Validate MyEarnings success response - GET Request
		objMyEarnings.validateMyEarningsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsResponse, email);
		
		// Getting refresh token from SignupV1WithOTP success response
		String refreshToken = objUserSignUp.getRefreshToken(userSignUpResponse);

		// Getting Access Token for Helper end point - GET Request
		helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response refreshTokenResponse = objRefreshToken.refreshTokenHelperEndPoint(baseURL, accessTokenValue, typeAuth, refreshToken, statusCode200);
		
		// Getting Access Token for refresh token end point - POST Request
		accessTokenValue		= objRefreshToken.getAccessToken(refreshTokenResponse);
		
		// Getting My earnings response from my earnings end point - GET Request
		myEarningsResponse	= objMyEarnings.getMyEarningsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		// Validate MyEarnings success response - GET Request
		objMyEarnings.validateMyEarningsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsResponse, email);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=2)
	public void validateRefreshTokenGeneratedInResponseUserLoginEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		UserLogin objUserLogin = new UserLogin(logger);
		MyEarnings objMyEarnings = new MyEarnings(logger);
		RefreshToken objRefreshToken = new RefreshToken(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String mobileNumber				= Utils.generateMobileNumber(10);
		String fullName					= Utils.getRestApiTestData(5, "username");
		String email					= Utils.generateNewEmail();
		String password					= Utils.generateRandomAlphabets(10);
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// User Login - POST Request
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);

		// User Login - POST Request
		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, deviceType, statusCode200);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		// Getting Access Token from user signup end point - POST Request
		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		// Getting My earnings response from my earnings end point - GET Request
		Response myEarningsResponse	= objMyEarnings.getMyEarningsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		// Validate MyEarnings success response - GET Request
		objMyEarnings.validateMyEarningsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsResponse, email);
		
		// Getting refresh token from SignupV1WithOTP success response
		String refreshToken = objUserLogin.getRefreshToken(userSignUpResponse);

		// Getting Access Token for Helper end point - GET Request
		helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response refreshTokenResponse = objRefreshToken.refreshTokenHelperEndPoint(baseURL, accessTokenValue, typeAuth, refreshToken, statusCode200);
		
		// Getting Access Token for refresh token end point - POST Request
		accessTokenValue		= objRefreshToken.getAccessToken(refreshTokenResponse);
		
		// Getting My earnings response from my earnings end point - GET Request
		myEarningsResponse	= objMyEarnings.getMyEarningsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		// Validate MyEarnings success response - GET Request
		objMyEarnings.validateMyEarningsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsResponse, email);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=3)
	public void validateRefreshTokenGeneratedInResponseUserLoginEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		UserLogin objUserLogin = new UserLogin(logger);
		MyEarnings objMyEarnings = new MyEarnings(logger);
		RefreshToken objRefreshToken = new RefreshToken(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String mobileNumber				= Utils.generateMobileNumber(10);
		String fullName					= Utils.getRestApiTestData(5, "username");
		String email					= Utils.generateNewEmail();
		String password					= Utils.generateRandomAlphabets(10);
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// User Login - POST Request
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		// User Login - POST Request
		Response userSignUpResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, password, fcmID, deviceUniqueID, imeiNumber, deviceType, statusCode200);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		// Getting Access Token from user signup end point - POST Request
		accessTokenValue = objUserLogin.getAccessToken(userSignUpResponse);
		
		// Getting My earnings response from my earnings end point - GET Request
		Response myEarningsResponse	= objMyEarnings.getMyEarningsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		// Validate MyEarnings success response - GET Request
		objMyEarnings.validateMyEarningsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsResponse, email);
		
		// Getting refresh token from SignupV1WithOTP success response
		String refreshToken = objUserLogin.getRefreshToken(userSignUpResponse);

		// Getting Access Token for Helper end point - GET Request
		helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response refreshTokenResponse = objRefreshToken.refreshTokenHelperEndPoint(baseURL, accessTokenValue, typeAuth, refreshToken, statusCode200);
		
		// Getting Access Token for refresh token end point - POST Request
		accessTokenValue		= objRefreshToken.getAccessToken(refreshTokenResponse);
		
		// Getting My earnings response from my earnings end point - GET Request
		myEarningsResponse	= objMyEarnings.getMyEarningsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		// Validate MyEarnings success response - GET Request
		objMyEarnings.validateMyEarningsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsResponse, email);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=4)
	public void validateRefreshTokenGeneratedInResponseSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		MyEarnings objMyEarnings = new MyEarnings(logger);
		RefreshToken objRefreshToken = new RefreshToken(logger);
		
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
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		// Getting Access Token from user signup end point - POST Request
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		// Getting My earnings response from my earnings end point - GET Request
		Response myEarningsResponse	= objMyEarnings.getMyEarningsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		// Validate MyEarnings success response - GET Request
		objMyEarnings.validateMyEarningsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsResponse, email);
		
		// Getting refresh token from SignupV1WithOTP success response
		String refreshToken = objSignUpV1WithOTP.getRefreshToken(signUpV1WithOTPResponse);

		// Getting Access Token for Helper end point - GET Request
		helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response refreshTokenResponse = objRefreshToken.refreshTokenHelperEndPoint(baseURL, accessTokenValue, typeAuth, refreshToken, statusCode200);
		
		// Getting Access Token for refresh token end point - POST Request
		accessTokenValue		= objRefreshToken.getAccessToken(refreshTokenResponse);
		
		// Getting My earnings response from my earnings end point - GET Request
		myEarningsResponse	= objMyEarnings.getMyEarningsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		// Validate MyEarnings success response - GET Request
		objMyEarnings.validateMyEarningsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsResponse, email);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=5)
	public void validateRefreshTokenGeneratedInResponseSignUpV1WithOTPEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		MyEarnings objMyEarnings = new MyEarnings(logger);
		RefreshToken objRefreshToken = new RefreshToken(logger);
		
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
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		// Getting Access Token from user signup end point - POST Request
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		// Getting My earnings response from my earnings end point - GET Request
		Response myEarningsResponse	= objMyEarnings.getMyEarningsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		// Validate MyEarnings success response - GET Request
		objMyEarnings.validateMyEarningsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsResponse, email);
		
		// Getting refresh token from SignupV1WithOTP success response
		String refreshToken = objSignUpV1WithOTP.getRefreshToken(signUpV1WithOTPResponse);

		// Getting Access Token for Helper end point - GET Request
		helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response refreshTokenResponse = objRefreshToken.refreshTokenHelperEndPoint(baseURL, accessTokenValue, typeAuth, refreshToken, statusCode200);
		
		// Getting Access Token for refresh token end point - POST Request
		accessTokenValue		= objRefreshToken.getAccessToken(refreshTokenResponse);
		
		// Getting My earnings response from my earnings end point - GET Request
		myEarningsResponse	= objMyEarnings.getMyEarningsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		// Validate MyEarnings success response - GET Request
		objMyEarnings.validateMyEarningsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsResponse, email);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	
	@Test(priority=6)
	public void validateRefreshTokenGeneratedInResponseFBLoginV1EndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		FacebookGraphAPIExplorer objGraphAPIExplorer = new FacebookGraphAPIExplorer(driver, logger);
		FacebookHome objFacebookHome = new FacebookHome(driver, logger);
		FacebookLogin objFacebookLogin = new FacebookLogin(driver, logger);
		MyEarnings objMyEarnings = new MyEarnings(logger);
		RefreshToken objRefreshToken = new RefreshToken(logger);
		
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
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// User Login - POST Request
		Response FBSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, FBSignUpV1WithOTPResponse, type, fullName);

		// User Login - POST Request
		Response FBLoginV1Response = objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, deviceType, statusCode200);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBLoginV1.validateFBLoginV1SucessResponseHelperEndPoint(objSoftAssertion, FBLoginV1Response, type, fullName);
		
		// Getting Access Token from user signup end point - POST Request
		accessTokenValue = objFBLoginV1.getAccessToken(FBSignUpV1WithOTPResponse);
		
		// Getting My earnings response from my earnings end point - GET Request
		Response myEarningsResponse	= objMyEarnings.getMyEarningsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		// Validate MyEarnings success response - GET Request
		objMyEarnings.validateMyEarningsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsResponse, fbEmail);
		
		// Getting refresh token from SignupV1WithOTP success response
		String refreshToken = objFBLoginV1.getRefreshToken(FBLoginV1Response);

		// Getting Access Token for Helper end point - GET Request
		helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response refreshTokenResponse = objRefreshToken.refreshTokenHelperEndPoint(baseURL, accessTokenValue, typeAuth, refreshToken, statusCode200);
		
		// Getting Access Token for refresh token end point - POST Request
		accessTokenValue		= objRefreshToken.getAccessToken(refreshTokenResponse);
		
		// Getting My earnings response from my earnings end point - GET Request
		myEarningsResponse	= objMyEarnings.getMyEarningsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		// Validate MyEarnings success response - GET Request
		objMyEarnings.validateMyEarningsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsResponse, fbEmail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=7)
	public void validateRefreshTokenGeneratedInResponseFBLoginV1EndpointDeviceTypeApp() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FBLoginV1 objFBLoginV1 = new FBLoginV1(logger);
		FacebookGraphAPIExplorer objGraphAPIExplorer = new FacebookGraphAPIExplorer(driver, logger);
		FacebookHome objFacebookHome = new FacebookHome(driver, logger);
		FacebookLogin objFacebookLogin = new FacebookLogin(driver, logger);
		MyEarnings objMyEarnings = new MyEarnings(logger);
		RefreshToken objRefreshToken = new RefreshToken(logger);
		
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
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// User Login - POST Request
		Response FBSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, FBSignUpV1WithOTPResponse, type, fullName);
		
		// User Login - POST Request
		Response FBLoginV1Response = objFBLoginV1.newFBLoginV1HelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, fcmID, deviceUniqueID, imeiNumber, deviceType, statusCode200);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBLoginV1.validateFBLoginV1SucessResponseHelperEndPoint(objSoftAssertion, FBLoginV1Response, type, fullName);
		
		// Getting Access Token from user signup end point - POST Request
		accessTokenValue = objFBLoginV1.getAccessToken(FBSignUpV1WithOTPResponse);
		
		// Getting My earnings response from my earnings end point - GET Request
		Response myEarningsResponse	= objMyEarnings.getMyEarningsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		// Validate MyEarnings success response - GET Request
		objMyEarnings.validateMyEarningsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsResponse, fbEmail);
		
		// Getting refresh token from SignupV1WithOTP success response
		String refreshToken = objFBLoginV1.getRefreshToken(FBLoginV1Response);

		// Getting Access Token for Helper end point - GET Request
		helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response refreshTokenResponse = objRefreshToken.refreshTokenHelperEndPoint(baseURL, accessTokenValue, typeAuth, refreshToken, statusCode200);
		
		// Getting Access Token for refresh token end point - POST Request
		accessTokenValue		= objRefreshToken.getAccessToken(refreshTokenResponse);
		
		// Getting My earnings response from my earnings end point - GET Request
		myEarningsResponse	= objMyEarnings.getMyEarningsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		// Validate MyEarnings success response - GET Request
		objMyEarnings.validateMyEarningsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsResponse, fbEmail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	
	@Test(priority=8)
	public void validateRefreshTokenGeneratedInResponseFBSignUpV1WithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FacebookGraphAPIExplorer objGraphAPIExplorer = new FacebookGraphAPIExplorer(driver, logger);
		FacebookHome objFacebookHome = new FacebookHome(driver, logger);
		FacebookLogin objFacebookLogin = new FacebookLogin(driver, logger);
		MyEarnings objMyEarnings = new MyEarnings(logger);
		RefreshToken objRefreshToken = new RefreshToken(logger);
		
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
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response FBSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, FBSignUpV1WithOTPResponse, type, fullName);
		
		// Getting Access Token from user signup end point - POST Request
		accessTokenValue = objFBSignUpV1WithOTP.getAccessToken(FBSignUpV1WithOTPResponse);
		
		// Getting My earnings response from my earnings end point - GET Request
		Response myEarningsResponse	= objMyEarnings.getMyEarningsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		// Validate MyEarnings success response - GET Request
		objMyEarnings.validateMyEarningsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsResponse, fbEmail);
		
		// Getting refresh token from SignupV1WithOTP success response
		String refreshToken = objFBSignUpV1WithOTP.getRefreshToken(FBSignUpV1WithOTPResponse);

		// Getting Access Token for Helper end point - GET Request
		helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response refreshTokenResponse = objRefreshToken.refreshTokenHelperEndPoint(baseURL, accessTokenValue, typeAuth, refreshToken, statusCode200);
		
		// Getting Access Token for refresh token end point - POST Request
		accessTokenValue		= objRefreshToken.getAccessToken(refreshTokenResponse);
		
		// Getting My earnings response from my earnings end point - GET Request
		myEarningsResponse	= objMyEarnings.getMyEarningsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		// Validate MyEarnings success response - GET Request
		objMyEarnings.validateMyEarningsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsResponse, fbEmail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=9)
	public void validateRefreshTokenGeneratedInResponseFBSignUpV1WithOTPEndpointDeviceTypeApp() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		FBSignUpV1WithOTP objFBSignUpV1WithOTP = new FBSignUpV1WithOTP(logger);
		FacebookGraphAPIExplorer objGraphAPIExplorer = new FacebookGraphAPIExplorer(driver, logger);
		FacebookHome objFacebookHome = new FacebookHome(driver, logger);
		FacebookLogin objFacebookLogin = new FacebookLogin(driver, logger);
		MyEarnings objMyEarnings = new MyEarnings(logger);
		RefreshToken objRefreshToken = new RefreshToken(logger);
		
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
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objFBSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response FBSignUpV1WithOTPResponse = objFBSignUpV1WithOTP.newUserFBSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, facebookAccessToken, facebookID, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		// Validate FBSignUpV1WithOTP success response - POST Request
		objFBSignUpV1WithOTP.validateFBSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, FBSignUpV1WithOTPResponse, type, fullName);
		
		// Getting Access Token from user signup end point - POST Request
		accessTokenValue = objFBSignUpV1WithOTP.getAccessToken(FBSignUpV1WithOTPResponse);
		
		// Getting My earnings response from my earnings end point - GET Request
		Response myEarningsResponse	= objMyEarnings.getMyEarningsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		// Validate MyEarnings success response - GET Request
		objMyEarnings.validateMyEarningsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsResponse, fbEmail);
		
		// Getting refresh token from SignupV1WithOTP success response
		String refreshToken = objFBSignUpV1WithOTP.getRefreshToken(FBSignUpV1WithOTPResponse);

		// Getting Access Token for Helper end point - GET Request
		helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response refreshTokenResponse = objRefreshToken.refreshTokenHelperEndPoint(baseURL, accessTokenValue, typeAuth, refreshToken, statusCode200);
		
		// Getting Access Token for refresh token end point - POST Request
		accessTokenValue		= objRefreshToken.getAccessToken(refreshTokenResponse);
		
		// Getting My earnings response from my earnings end point - GET Request
		myEarningsResponse	= objMyEarnings.getMyEarningsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		// Validate MyEarnings success response - GET Request
		objMyEarnings.validateMyEarningsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsResponse, fbEmail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	
	@Test(priority=12)
	public void validateTypeMandatoryFieldRefreshTokenEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		RefreshToken objRefreshToken = new RefreshToken(logger);
		
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
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String errorStatus				= Utils.getRestApiTestData(11, "type_status");
		String errorTitle				= Utils.getRestApiTestData(11, "type_title");
		String errorDetail				= Utils.getRestApiTestData(11, "type_detail");
		String errorPointer				= Utils.getRestApiTestData(11, "type_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		// Getting Access Token from user signup end point - POST Request
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		// Getting refresh token from SignupV1WithOTP success response
		String refreshToken = objUserSignUp.getRefreshToken(userSignUpResponse);

		// Getting Access Token for Helper end point - GET Request
		helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response refreshTokenResponse = objRefreshToken.refreshTokenHelperEndPoint(baseURL, accessTokenValue, "", refreshToken, statusCode422);
		
		objRefreshToken.validateRefreshTokenErrorResponseHelperEndPoint(objSoftAssertion, refreshTokenResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=13)
	public void validateRefreshTokenMandatoryFieldRefreshTokenEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		RefreshToken objRefreshToken = new RefreshToken(logger);
		
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
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String errorStatus				= Utils.getRestApiTestData(18, "status_invalid_refresh_token");
		String errorCode				= Utils.getRestApiTestData(18, "code_invalid_refresh_token");
		String errorTitle				= Utils.getRestApiTestData(18, "title_invalid_token");
		String errorDetail				= Utils.getRestApiTestData(18, "detail_invalid_token");
		String errorPointer				= Utils.getRestApiTestData(18, "parameter_invalid_refresh_token");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		// Getting Access Token from user signup end point - POST Request
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		// Getting refresh token from SignupV1WithOTP success response
		String refreshToken = Utils.getRestApiTestData(8, "emptyValue");

		// Getting Access Token for Helper end point - GET Request
		helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response refreshTokenResponse = objRefreshToken.refreshTokenHelperEndPoint(baseURL, accessTokenValue, typeAuth, refreshToken, statusCode401);
		
		objRefreshToken.validateRefreshTokenErrorResponseHelperEndPoint_(objSoftAssertion, refreshTokenResponse, errorStatus, errorCode, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	
	@Test(priority=14)
	public void validateContentTypeResponseForRefreshTokenEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		MyEarnings objMyEarnings = new MyEarnings(logger);
		RefreshToken objRefreshToken = new RefreshToken(logger);
		Restutils objRestutils = new Restutils(logger);
		
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
		String contentType				= Utils.getRestApiTestData(0, "accept_value");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		// Getting Access Token from user signup end point - POST Request
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		// Getting My earnings response from my earnings end point - GET Request
		Response myEarningsResponse	= objMyEarnings.getMyEarningsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		// Validate MyEarnings success response - GET Request
		objMyEarnings.validateMyEarningsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsResponse, email);
		
		// Getting refresh token from SignupV1WithOTP success response
		String refreshToken = objUserSignUp.getRefreshToken(userSignUpResponse);

		// Getting Access Token for Helper end point - GET Request
		helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response refreshTokenResponse = objRefreshToken.refreshTokenHelperEndPoint(baseURL, accessTokenValue, typeAuth, refreshToken, statusCode200);
		
		// Getting Access Token for refresh token end point - POST Request
		accessTokenValue		= objRefreshToken.getAccessToken(refreshTokenResponse);
		
		// Getting My earnings response from my earnings end point - GET Request
		myEarningsResponse	= objMyEarnings.getMyEarningsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		// Validate MyEarnings success response - GET Request
		objMyEarnings.validateMyEarningsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsResponse, email);
		
		objRestutils.validateReponseContentType(objSoftAssertion, refreshTokenResponse, contentType);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=15)
	public void validateContentTypeResponseForRefreshTokenEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		MyEarnings objMyEarnings = new MyEarnings(logger);
		RefreshToken objRefreshToken = new RefreshToken(logger);
		Restutils objRestutils = new Restutils(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
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
		String contentType				= Utils.getRestApiTestData(0, "accept_value");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		// Getting Access Token from user signup end point - POST Request
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		// Getting My earnings response from my earnings end point - GET Request
		Response myEarningsResponse	= objMyEarnings.getMyEarningsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		// Validate MyEarnings success response - GET Request
		objMyEarnings.validateMyEarningsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsResponse, email);
		
		// Getting refresh token from SignupV1WithOTP success response
		String refreshToken = objUserSignUp.getRefreshToken(userSignUpResponse);

		// Getting Access Token for Helper end point - GET Request
		helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response refreshTokenResponse = objRefreshToken.refreshTokenHelperEndPoint(baseURL, accessTokenValue, typeAuth, refreshToken, statusCode200);
		
		// Getting Access Token for refresh token end point - POST Request
		accessTokenValue		= objRefreshToken.getAccessToken(refreshTokenResponse);
		
		// Getting My earnings response from my earnings end point - GET Request
		myEarningsResponse	= objMyEarnings.getMyEarningsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		// Validate MyEarnings success response - GET Request
		objMyEarnings.validateMyEarningsSuccessResponseUserEndPoint(objSoftAssertion, myEarningsResponse, email);
		
		objRestutils.validateReponseContentType(objSoftAssertion, refreshTokenResponse, contentType);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	
	@Test(priority=16)
	public void validateEmptyHeaderRefreshTokenEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		RefreshToken objRefreshToken = new RefreshToken(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message");
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response refreshTokenResponse = objRefreshToken.refreshTokenHelperEndPoint(baseURL, statusCode401);
		
		objRefreshToken.validateRefreshTokenErrorResponseHelperEndPoint(objSoftAssertion, refreshTokenResponse, errorMessage);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=17)
	public void validateInvalidAuthorizationKeyValueRefreshTokenEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		RefreshToken objRefreshToken = new RefreshToken(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message_invalid_json");
		String accessTokenValue			= Utils.getRestApiTestData(0, "auth_invalid_value");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response refreshTokenResponse = objRefreshToken.refreshTokenHelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode401);
		
		objRefreshToken.validateRefreshTokenErrorResponseHelperEndPoint(objSoftAssertion, refreshTokenResponse, errorMessage);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=18)
	public void validateInvalidContentTypeKeyValueRefreshTokenEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		RefreshToken objRefreshToken = new RefreshToken(logger);
		
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
		int statusCode415				= Integer.parseInt(Utils.getRestApiTestData(9, "status_415"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_invalid_value");
		String errorStatus				= Utils.getRestApiTestData(12, "error_status");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		// Getting Access Token from user signup end point - POST Request
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);

		// Getting Access Token for Helper end point - GET Request
		helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response refreshTokenResponse = objRefreshToken.refreshtokenHelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode415);
		
		objRefreshToken.validateRefreshTokenInvalidHeaderErrorResponseHelperEndPoint(objSoftAssertion, refreshTokenResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=19)
	public void validateInvalidAcceptTypeKeyValueRefreshTokenEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		RefreshToken objRefreshToken = new RefreshToken(logger);
		
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
		int statusCode415				= Integer.parseInt(Utils.getRestApiTestData(9, "status_415"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_invalid_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");
		String errorStatus				= Utils.getRestApiTestData(12, "error_status");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		// Getting Access Token from user signup end point - POST Request
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);

		// Getting Access Token for Helper end point - GET Request
		helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response refreshTokenResponse = objRefreshToken.refreshtokenHelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode415);
		
		objRefreshToken.validateRefreshTokenInvalidHeaderErrorResponseHelperEndPoint(objSoftAssertion, refreshTokenResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=20)
	public void validateEmptyAuthorizationKeyValueRefreshTokenEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		RefreshToken objRefreshToken = new RefreshToken(logger);
		
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
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");
		String errorMessage				= Utils.getRestApiTestData(12, "message");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		accessTokenValue		= Utils.getRestApiTestData(8, "emptyValue");
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response refreshTokenResponse = objRefreshToken.refreshtokenHelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode401);
		
		objRefreshToken.validateRefreshTokenErrorResponseHelperEndPoint(objSoftAssertion, refreshTokenResponse, errorMessage);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=21)
	public void validateEmptyAcceptTypeKeyValueRefreshTokenEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		RefreshToken objRefreshToken = new RefreshToken(logger);
		
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
		int statusCode406				= Integer.parseInt(Utils.getRestApiTestData(9, "status_406"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String acceptValue				= Utils.getRestApiTestData(8, "emptyValue");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");
		String errorStatus				= Utils.getRestApiTestData(12, "error_status_406");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title_notacceptable");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail_accept_header");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		// Getting Access Token from user signup end point - POST Request
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);

		// Getting Access Token for Helper end point - GET Request
		helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response refreshTokenResponse = objRefreshToken.refreshtokenHelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode406);
		
		objRefreshToken.validateRefreshTokenInvalidHeaderErrorResponseHelperEndPoint(objSoftAssertion, refreshTokenResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=22)
	public void validateEmptyContentTypeKeyValueRefreshTokenEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		RefreshToken objRefreshToken = new RefreshToken(logger);
		
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
		int statusCode415				= Integer.parseInt(Utils.getRestApiTestData(9, "status_415"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(8, "emptyValue");
		String errorStatus				= Utils.getRestApiTestData(12, "error_status");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		// Getting Access Token from user signup end point - POST Request
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		// Getting Access Token for Helper end point - GET Request
		helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response refreshTokenResponse = objRefreshToken.refreshtokenHelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode415);
		
		objRefreshToken.validateRefreshTokenInvalidHeaderErrorResponseHelperEndPoint(objSoftAssertion, refreshTokenResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

}