package com.ppa.api.testsuites.users;

import java.awt.AWTException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ppa.api.base.Base;
import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.endpoints.helper.HelperAccessToken;
import com.ppa.api.endpoints.helper.SignUpV1WithOTP;
import com.ppa.api.endpoints.helper.UserSignUp;
import com.ppa.api.endpoints.users.VerifyOTP;
import com.ppa.api.utilities.Restutils;
import com.ppa.api.utilities.Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserSignUpTestSuite extends Base {

	@Test(priority=0)
	public void validateBVAForEmailWithMinimumCharactersUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateRandomAlphabets(0)+"@a.in";
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "email_detail_valid_email");
		String errorPointer				= Utils.getRestApiTestData(10, "email_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode422);

		// Validate SignupV1WithOTP error response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();
		
	}

	@Test(priority=1)
	public void validateBVAForEmailWithMinimumCharactersMinusOneUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= "@"+Utils.generateRandomAlphabets(1)+"."+Utils.generateRandomAlphabets(1);
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "email_detail_least_char");
		String errorPointer				= Utils.getRestApiTestData(10, "email_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate SignupV1WithOTP error response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=2)
	public void validateBVAForEmailWithMinimumCharactersPlusOneUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateRandomAlphabets(1)+"@a."+Utils.generateRandomAlphabets(2);
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
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=3)
	public void validateBVAForEmailWithMaximumCharactersUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateRandomAlphabets(45)+"@a.in";
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
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=4)
	public void validateBVAForEmailWithMaximumCharactersMinusOneUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateRandomAlphabets(44)+"@a.in";
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
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=5)
	public void validateBVAForEmailWithMaximumCharactersPlusOneUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateRandomAlphabets(46)+"@a.in";
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "email_detail_more_char");
		String errorPointer				= Utils.getRestApiTestData(10, "email_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate SignupV1WithOTP error response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
	
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	
	@Test(priority=6)
	public void validateBVAForPasswordWithMinimumCharactersUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.generateRandomAlphabets(6);
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
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=7)
	public void validateBVAForPasswordWithMinimumCharactersMinusOneUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.generateRandomAlphabets(5);
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "pwd_detail_least_char");
		String errorPointer				= Utils.getRestApiTestData(10, "pwd_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate SignupV1WithOTP error response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=8)
	public void validateBVAForPasswordWithMinimumCharactersPlusOneUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.generateRandomAlphabets(7);
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
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=9)
	public void validateBVAForPasswordWithMaximumCharactersUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.generateRandomAlphabets(20);
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
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=10)
	public void validateBVAForPasswordWithMaximumCharactersMinusOneUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.generateRandomAlphabets(19);
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
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=11)
	public void validateBVAForPasswordWithMaximumCharactersPlusOneUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.generateRandomAlphabets(21);
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "pwd_detail_more_char");
		String errorPointer				= Utils.getRestApiTestData(10, "pwd_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate SignupV1WithOTP error response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
	
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	
	@Test(priority=12)
	public void validateBVAForFullNameWithMinimumCharactersUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.generateRandomAlphabets(2);
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
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=13)
	public void validateBVAForFullNameWithMinimumCharactersMinusOneUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.generateRandomAlphabets(1);
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "fullname_detail_least_char");
		String errorPointer				= Utils.getRestApiTestData(10, "fullname_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate SignupV1WithOTP error response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=14)
	public void validateBVAForFullNameWithMinimumCharactersPlusOneUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.generateRandomAlphabets(3);
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
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=15)
	public void validateBVAForFullNameWithMaximumCharactersUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.generateRandomAlphabets(50);
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
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=16)
	public void validateBVAForFullNameWithMaximumCharactersMinusOneUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.generateRandomAlphabets(49);
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
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=17)
	public void validateBVAForFullNameWithMaximumCharactersPlusOneUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.generateRandomAlphabets(51);
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "fullname_detail_more_char");
		String errorPointer				= Utils.getRestApiTestData(10, "fullname_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate SignupV1WithOTP error response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
	
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	
	@Test(priority=18)
	public void validateBVAForMobileNumberWithMaximumCharactersUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
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
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=19)
	public void validateBVAForMobileNumberWithMaximumCharactersMinusOneUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
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
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "mobilenumber_detail_least_char");
		String errorPointer				= Utils.getRestApiTestData(10, "mobilenumber_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// Reassigning values
		mobileNumber				= Utils.generateMobileNumber(9);
		
		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate SignupV1WithOTP error response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=20)
	public void validateBVAForMobileNumberWithMaximumCharactersPlusOneUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
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
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "mobilenumber_detail_least_char");
		String errorPointer				= Utils.getRestApiTestData(10, "mobilenumber_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// Reassigning values
		mobileNumber				= Utils.generateMobileNumber(11);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate SignupV1WithOTP error response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	
	@Test(priority=21)
	public void validateFullNameMandatoryFieldUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(8, "emptyValue");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "fullname_detail_mandatory");
		String errorPointer				= Utils.getRestApiTestData(10, "fullname_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate SignupV1WithOTP error response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=22)
	public void validateEmailMandatoryFieldUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.getRestApiTestData(8, "emptyValue");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "email_detail_mandatory");
		String errorPointer				= Utils.getRestApiTestData(10, "email_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate SignupV1WithOTP error response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=23)
	public void validatePasswordMandatoryFieldUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(8, "emptyValue");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "pwd_detail_mandatory");
		String errorPointer				= Utils.getRestApiTestData(10, "pwd_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate SignupV1WithOTP error response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=24)
	public void validateMobileNumberMandatoryFieldUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
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
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "mobilenumber_detail_mandatory");
		String errorPointer				= Utils.getRestApiTestData(10, "mobilenumber_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Reassigning values
		mobileNumber					= Utils.getRestApiTestData(8, "double_quote");

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate SignupV1WithOTP error response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=25)
	public void validateIPAddressMandatoryFieldUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(8, "emptyValue");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "ip_detail_mandatory");
		String errorPointer				= Utils.getRestApiTestData(10, "ip_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate SignupV1WithOTP error response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=26)
	public void validateReferralIDMandatoryFieldUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
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

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
	
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=27)
	public void validateInvalidIPAddressUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "invalid_ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "ip_detail_Valid");
		String errorPointer				= Utils.getRestApiTestData(10, "ip_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate SignupV1WithOTP error response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	
	@Test(priority=28)
	public void validateDeviceInfoMandatoryFieldUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
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
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=29)
	public void validateDeviceInfoMandatoryFieldUserSignUpEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorCode				= Utils.getRestApiTestData(10, "code");
		String errorTitle				= Utils.getRestApiTestData(10, "deviceinfo_title");
		String errorDetail				= Utils.getRestApiTestData(10, "deviceinfo_detail");
		String errorParameter			= Utils.getRestApiTestData(10, "deviceinfo_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint_(objSoftAssertion, userSignUpResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	
	@Test(priority=30)
	public void validateUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
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
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=31)
	public void validateUserSignUpEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
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
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	
	@Test(priority=32)
	public void validateExistingEmailFieldUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
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
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "email_detail_already_taken");
		String errorPointer				= Utils.getRestApiTestData(10, "email_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		// Reassigning values
		mobileNumber				= Utils.generateMobileNumber(10);

		// New User Signup - POST Request
		userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=33)
	public void validateExistingMobileNumberFieldUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		VerifyOTP objVerifyOTP = new VerifyOTP(logger);
		
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
		String type						= Utils.getRestApiTestData(13, "type");
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "mobilenumber_detail_already");
		String errorPointer				= Utils.getRestApiTestData(10, "mobilenumber_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, typeAuth, fullName);  
		
		String  accessTokenValueUserSignUp	= objUserSignUp.getAccessToken(userSignUpResponse);

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);
		
		// Verify OTP - POST Request
		objVerifyOTP.getVerifyOTPResponseUserEndPoint(baseURL, accessTokenValueUserSignUp, type, signUpOTP, statusCode200);

		// Reassigning values
		email						= Utils.generateNewEmail();

		// New User Signup - POST Request
		userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	
	@Test(priority=34)
	public void validateDeviceInfoInvalidFieldUserSignUpEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(8, "emptyValue");
		String deviceUniqueID			= Utils.getRestApiTestData(8, "emptyValue");
		String imeiNumber				= Utils.getRestApiTestData(8, "emptyValue");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "deviceinfo_status");
		String errorCode				= Utils.getRestApiTestData(10, "deviceinfo_code");
		String errorTitle				= Utils.getRestApiTestData(10, "deviceinfo_title1");
		String errorDetail				= Utils.getRestApiTestData(10, "deviceinfo_detail1");
		String errorParameter			= Utils.getRestApiTestData(10, "deviceinfo_parameter");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode422);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	
	@Test(priority=35)
	public void validateInvalidMobileNumberFormatUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
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
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "mobilenumber_detail_invalid");
		String errorPointer				= Utils.getRestApiTestData(10, "mobilenumber_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Reassigning values
		mobileNumber					= "5" + Utils.generateMobileNumber(9);
		
		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate SignupV1WithOTP error response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
	
		// Reassigning values
		mobileNumber					= "4" + Utils.generateMobileNumber(9);
		
		// New User Signup - POST Request
		userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate SignupV1WithOTP error response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorStatus, errorTitle, errorDetail, errorPointer);

		// Reassigning values
		mobileNumber					= "3" + Utils.generateMobileNumber(9);
		
		// New User Signup - POST Request
		userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate SignupV1WithOTP error response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorStatus, errorTitle, errorDetail, errorPointer);

		// Reassigning values
		mobileNumber					= "2" + Utils.generateMobileNumber(9);
		
		// New User Signup - POST Request
		userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate SignupV1WithOTP error response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorStatus, errorTitle, errorDetail, errorPointer);

		// Reassigning values
		mobileNumber					= "1" + Utils.generateMobileNumber(9);
		
		// New User Signup - POST Request
		userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate SignupV1WithOTP error response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorStatus, errorTitle, errorDetail, errorPointer);

		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	
	@Test(priority=36)
	public void validateDeviceTypeDesktopFieldMandatoryUserSignUpEndPoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(8, "emptyValue");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "device_detail");
		String errorPointer				= Utils.getRestApiTestData(10, "device_parameter");
		
		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate SignupV1WithOTP error response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint_(objSoftAssertion, userSignUpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=37)
	public void validateDeviceTypeAppFieldMandatoryUserSignUpEndPoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(8, "emptyValue");
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
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "device_detail");
		String errorPointer				= Utils.getRestApiTestData(10, "device_parameter");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode422);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint_(objSoftAssertion, userSignUpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	
	@Test(priority=38)
	public void validateTypeMandatoryFieldUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(8, "emptyValue");
		String fullName					= Utils.getRestApiTestData(8, "emptyValue");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(11, "type_status");
		String errorTitle				= Utils.getRestApiTestData(11, "type_title");
		String errorDetail				= Utils.getRestApiTestData(11, "type_detail");
		String errorPointer				= Utils.getRestApiTestData(11, "type_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate SignupV1WithOTP error response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=39)
	public void validateResponseHeaderFieldUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
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
		String contentType				= Utils.getRestApiTestData(0, "accept_value");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		objRestutils.validateReponseContentType(objSoftAssertion, userSignUpResponse, contentType);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=40)
	public void validateResponseHeaderFieldUserSignUpEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
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
		String contentType				= Utils.getRestApiTestData(0, "accept_value");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		objRestutils.validateReponseContentType(objSoftAssertion, userSignUpResponse, contentType);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}


	@Test(priority=41)
	public void validateUserSignUpEndpointInvalidDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "invalid_desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status_invalid_device");
		String errorCode				= Utils.getRestApiTestData(10, "code_invalid_device");
		String errorTitle				= Utils.getRestApiTestData(10, "title_invalid_device");
		String errorDetail				= Utils.getRestApiTestData(10, "detail_invalid_device");
		String errorParameter			= Utils.getRestApiTestData(10, "parameter_invalid_device");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode400);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=42)
	public void validateUserSignUpEndpointInvalidDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "invalid_desktop");
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
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status_invalid_device");
		String errorCode				= Utils.getRestApiTestData(10, "code_invalid_device");
		String errorTitle				= Utils.getRestApiTestData(10, "title_invalid_device");
		String errorDetail				= Utils.getRestApiTestData(10, "detail_invalid_device");
		String errorParameter			= Utils.getRestApiTestData(10, "parameter_invalid_device");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode400);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	
	@Test(priority=43)
	public void validateEmptyHeaderUserSignupEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message");
		
		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, deviceType, statusCode401);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorMessage);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=44)
	public void validateInvalidAuthorizationKeyValueUserSignupEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message_invalid_json");
		String accessTokenValue			= Utils.getRestApiTestData(0, "auth_invalid_value");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, deviceType, statusCode401);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorMessage);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=45)
	public void validateInvalidContentTypeKeyValueUserSignupEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode415				= Integer.parseInt(Utils.getRestApiTestData(9, "status_415"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
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
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, deviceType, statusCode415);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpInvalidHeaderErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=46)
	public void validateInvalidAcceptTypeKeyValueUserSignupEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode415				= Integer.parseInt(Utils.getRestApiTestData(9, "status_415"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
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
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, deviceType, statusCode415);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpInvalidHeaderErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=47)
	public void validateEmptyAuthorizationKeyValueUserSignupEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");
		String errorMessage				= Utils.getRestApiTestData(12, "message");

		// Getting Access Token for Helper end point - GET Request
		objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String accessTokenValue		= Utils.getRestApiTestData(8, "emptyValue");

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, deviceType, statusCode401);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorMessage);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=48)
	public void validateEmptyAcceptTypeKeyValueUserSignupEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode406				= Integer.parseInt(Utils.getRestApiTestData(9, "status_406"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
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
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, deviceType, statusCode406);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpInvalidHeaderErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=49)
	public void validateEmptyContentTypeKeyValueUserSignupEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode415				= Integer.parseInt(Utils.getRestApiTestData(9, "status_415"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
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
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, deviceType, statusCode415);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpInvalidHeaderErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=50)
	public void validateInvalidFullNameFormatUserSignUpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.generateRandomAlphabets(1)+"@@";
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(22, "status_422");
		String errorTitle				= Utils.getRestApiTestData(22, "title_invalid_attribute");
		String errorDetail				= Utils.getRestApiTestData(22, "detail_full_name_format");
		String errorPointer				= Utils.getRestApiTestData(22, "pointer_full_name_format");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode422);
		
		// Validate SignupV1WithOTP error response - POST Request
		objUserSignUp.validateUserSignUpErrorResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

}