package com.ppa.api.testsuites.users;

import java.awt.AWTException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ppa.api.base.Base;
import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.endpoints.helper.HelperAccessToken;
import com.ppa.api.endpoints.helper.SignUpOTP;
import com.ppa.api.endpoints.helper.SignUpV1WithOTP;
import com.ppa.api.endpoints.users.WriteTestimonials;
import com.ppa.api.utilities.Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class WriteTestimonialsTestSuite extends Base {

	@Test(priority=0)
	public void validateTestimonialsResponseAfterPassingRating5WriteTestimonialEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		WriteTestimonials objWriteTestimonials = new WriteTestimonials(logger);
		
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
		String typeTestimonial			= Utils.getRestApiTestData(39, "testimonial_type");
		String testimonials				= Utils.generateRandomAlphabets(15);
		String message					= Utils.getRestApiTestData(39, "message");
		String ratings					= Utils.getRestApiTestData(39, "ratings_5");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response writeTestimonialsResponse = objWriteTestimonials.getWriteTestimonialsResponseUserEndPoint(baseURL, accessTokenValue, typeTestimonial, ratings, testimonials, ipAddress, statusCode201);
		
		objWriteTestimonials.validateWriteTestimonialsSuccessResponseUserEndPoint(objSoftAssertion, writeTestimonialsResponse, typeTestimonial, message);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=1)
	public void validateTestimonialsResponseAfterPassingRating4WriteTestimonialEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		WriteTestimonials objWriteTestimonials = new WriteTestimonials(logger);
		
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
		String typeTestimonial			= Utils.getRestApiTestData(39, "testimonial_type");
		String testimonials				= Utils.generateRandomAlphabets(15);
		String message					= Utils.getRestApiTestData(39, "message");
		String ratings					= Utils.getRestApiTestData(39, "ratings_4");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response writeTestimonialsResponse = objWriteTestimonials.getWriteTestimonialsResponseUserEndPoint(baseURL, accessTokenValue, typeTestimonial, ratings, testimonials, ipAddress, statusCode201);
		
		objWriteTestimonials.validateWriteTestimonialsSuccessResponseUserEndPoint(objSoftAssertion, writeTestimonialsResponse, typeTestimonial, message);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=2)
	public void validateTestimonialsResponseAfterPassingRating3WriteTestimonialEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		WriteTestimonials objWriteTestimonials = new WriteTestimonials(logger);
		
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
		String typeTestimonial			= Utils.getRestApiTestData(39, "testimonial_type");
		String testimonials				= Utils.generateRandomAlphabets(15);
		String message					= Utils.getRestApiTestData(39, "message");
		String ratings					= Utils.getRestApiTestData(39, "ratings_3");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response writeTestimonialsResponse = objWriteTestimonials.getWriteTestimonialsResponseUserEndPoint(baseURL, accessTokenValue, typeTestimonial, ratings, testimonials, ipAddress, statusCode201);
		
		objWriteTestimonials.validateWriteTestimonialsSuccessResponseUserEndPoint(objSoftAssertion, writeTestimonialsResponse, typeTestimonial, message);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=3)
	public void validateTestimonialsResponseAfterPassingRating2WriteTestimonialEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		WriteTestimonials objWriteTestimonials = new WriteTestimonials(logger);
		
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
		String typeTestimonial			= Utils.getRestApiTestData(39, "testimonial_type");
		String testimonials				= Utils.generateRandomAlphabets(15);
		String message					= Utils.getRestApiTestData(39, "message");
		String ratings					= Utils.getRestApiTestData(39, "ratings_2");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response writeTestimonialsResponse = objWriteTestimonials.getWriteTestimonialsResponseUserEndPoint(baseURL, accessTokenValue, typeTestimonial, ratings, testimonials, ipAddress, statusCode201);
		
		objWriteTestimonials.validateWriteTestimonialsSuccessResponseUserEndPoint(objSoftAssertion, writeTestimonialsResponse, typeTestimonial, message);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=4)
	public void validateTestimonialsResponseAfterPassingRating1WriteTestimonialEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		WriteTestimonials objWriteTestimonials = new WriteTestimonials(logger);
		
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
		String typeTestimonial			= Utils.getRestApiTestData(39, "testimonial_type");
		String testimonials				= Utils.generateRandomAlphabets(15);
		String message					= Utils.getRestApiTestData(39, "message");
		String ratings					= Utils.getRestApiTestData(39, "ratings_1");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response writeTestimonialsResponse = objWriteTestimonials.getWriteTestimonialsResponseUserEndPoint(baseURL, accessTokenValue, typeTestimonial, ratings, testimonials, ipAddress, statusCode201);
		
		objWriteTestimonials.validateWriteTestimonialsSuccessResponseUserEndPoint(objSoftAssertion, writeTestimonialsResponse, typeTestimonial, message);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=5)
	public void validateTestimonialsResponseAfterPassingRating6WriteTestimonialEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		WriteTestimonials objWriteTestimonials = new WriteTestimonials(logger);
		
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
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeTestimonial			= Utils.getRestApiTestData(39, "testimonial_type");
		String testimonials				= Utils.generateRandomAlphabets(15);
		String errorStatus				= Utils.getRestApiTestData(39, "status_422");
		String errorTitle				= Utils.getRestApiTestData(39, "title_invalid_attribute");
		String errorDetail				= Utils.getRestApiTestData(39, "detail_must_be_bw_1_to_5");
		String errorPointer				= Utils.getRestApiTestData(39, "pointer_ratings");
		String ratings					= Utils.getRestApiTestData(39, "ratings_6");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response writeTestimonialsResponse = objWriteTestimonials.getWriteTestimonialsResponseUserEndPoint(baseURL, accessTokenValue, typeTestimonial, ratings, testimonials, ipAddress, statusCode422);
		
		objWriteTestimonials.validateWriteTestimonialsErrorResponseUserEndPoint(objSoftAssertion, writeTestimonialsResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=6)
	public void validateTestimonialsResponseAfterPassingRatingCharactersWriteTestimonialEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		WriteTestimonials objWriteTestimonials = new WriteTestimonials(logger);
		
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
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeTestimonial			= Utils.getRestApiTestData(39, "testimonial_type");
		String testimonials				= Utils.generateRandomAlphabets(15);
		String errorStatus				= Utils.getRestApiTestData(39, "status_422");
		String errorTitle				= Utils.getRestApiTestData(39, "title_invalid_attribute");
		String errorDetail				= Utils.getRestApiTestData(39, "detail_ratings_must_be_nos");
		String errorPointer				= Utils.getRestApiTestData(39, "pointer_ratings");
		String ratings					= "\""+Utils.generateRandomAlphabets(5)+"\"";

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response writeTestimonialsResponse = objWriteTestimonials.getWriteTestimonialsResponseUserEndPoint(baseURL, accessTokenValue, typeTestimonial, ratings, testimonials, ipAddress, statusCode422);
		
		objWriteTestimonials.validateWriteTestimonialsErrorResponseUserEndPoint(objSoftAssertion, writeTestimonialsResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=7)
	public void validateBVAForTestimonialsMaximumWriteTestimonialEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		WriteTestimonials objWriteTestimonials = new WriteTestimonials(logger);
		
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
		String typeTestimonial			= Utils.getRestApiTestData(39, "testimonial_type");
		String testimonials				= Utils.generateRandomAlphabets(750);
		String message					= Utils.getRestApiTestData(39, "message");
		String ratings					= Utils.getRestApiTestData(39, "ratings_5");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response writeTestimonialsResponse = objWriteTestimonials.getWriteTestimonialsResponseUserEndPoint(baseURL, accessTokenValue, typeTestimonial, ratings, testimonials, ipAddress, statusCode201);
		
		objWriteTestimonials.validateWriteTestimonialsSuccessResponseUserEndPoint(objSoftAssertion, writeTestimonialsResponse, typeTestimonial, message);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=8)
	public void validateBVAForTestimonialsMaximumMinusOneWriteTestimonialEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		WriteTestimonials objWriteTestimonials = new WriteTestimonials(logger);
		
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
		String typeTestimonial			= Utils.getRestApiTestData(39, "testimonial_type");
		String testimonials				= Utils.generateRandomAlphabets(749);
		String message					= Utils.getRestApiTestData(39, "message");
		String ratings					= Utils.getRestApiTestData(39, "ratings_5");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response writeTestimonialsResponse = objWriteTestimonials.getWriteTestimonialsResponseUserEndPoint(baseURL, accessTokenValue, typeTestimonial, ratings, testimonials, ipAddress, statusCode201);
		
		objWriteTestimonials.validateWriteTestimonialsSuccessResponseUserEndPoint(objSoftAssertion, writeTestimonialsResponse, typeTestimonial, message);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=9)
	public void validateBVAForTestimonialsMaximumPlusOneWriteTestimonialEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		WriteTestimonials objWriteTestimonials = new WriteTestimonials(logger);
		
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
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeTestimonial			= Utils.getRestApiTestData(39, "testimonial_type");
		String testimonials				= Utils.generateRandomAlphabets(751);
		String ratings					= Utils.getRestApiTestData(39, "ratings_5");
		String errorStatus				= Utils.getRestApiTestData(39, "status_422");
		String errorTitle				= Utils.getRestApiTestData(39, "title_invalid_attribute");
		String errorDetail				= Utils.getRestApiTestData(39, "detail_testimonial_not_greater_than_750");
		String errorPointer				= Utils.getRestApiTestData(39, "pointer_testimonial");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response writeTestimonialsResponse = objWriteTestimonials.getWriteTestimonialsResponseUserEndPoint(baseURL, accessTokenValue, typeTestimonial, ratings, testimonials, ipAddress, statusCode422);
		
		objWriteTestimonials.validateWriteTestimonialsErrorResponseUserEndPoint(objSoftAssertion, writeTestimonialsResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=10)
	public void validateMandatoryTypeWriteTestimonialEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		WriteTestimonials objWriteTestimonials = new WriteTestimonials(logger);
		
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
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeTestimonial			= Utils.getRestApiTestData(8, "emptyValue");
		String testimonials				= Utils.generateRandomAlphabets(751);
		String ratings					= Utils.getRestApiTestData(39, "ratings_5");
		String errorStatus				= Utils.getRestApiTestData(11, "type_status");
		String errorTitle				= Utils.getRestApiTestData(11, "type_title");
		String errorDetail				= Utils.getRestApiTestData(11, "type_detail");
		String errorPointer				= Utils.getRestApiTestData(11, "type_pointer");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response writeTestimonialsResponse = objWriteTestimonials.getWriteTestimonialsResponseUserEndPoint(baseURL, accessTokenValue, typeTestimonial, ratings, testimonials, ipAddress, statusCode422);
		
		objWriteTestimonials.validateWriteTestimonialsErrorResponseUserEndPoint(objSoftAssertion, writeTestimonialsResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=11)
	public void validateMandatoryRatingsWriteTestimonialEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		WriteTestimonials objWriteTestimonials = new WriteTestimonials(logger);
		
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
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeTestimonial			= Utils.getRestApiTestData(39, "testimonial_type");
		String testimonials				= Utils.generateRandomAlphabets(751);
		String ratings					= Utils.getRestApiTestData(8, "double_quote");
		String errorStatus				= Utils.getRestApiTestData(39, "status_422");
		String errorTitle				= Utils.getRestApiTestData(39, "title_invalid_attribute");
		String errorDetail				= Utils.getRestApiTestData(39, "detail_ratings_required");
		String errorPointer				= Utils.getRestApiTestData(39, "pointer_ratings");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response writeTestimonialsResponse = objWriteTestimonials.getWriteTestimonialsResponseUserEndPoint(baseURL, accessTokenValue, typeTestimonial, ratings, testimonials, ipAddress, statusCode422);
		
		objWriteTestimonials.validateWriteTestimonialsErrorResponseUserEndPoint(objSoftAssertion, writeTestimonialsResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=12)
	public void validateMandatoryTestimonialsWriteTestimonialEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		WriteTestimonials objWriteTestimonials = new WriteTestimonials(logger);
		
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
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeTestimonial			= Utils.getRestApiTestData(39, "testimonial_type");
		String testimonials				= Utils.getRestApiTestData(8, "emptyValue");
		String ratings					= Utils.getRestApiTestData(39, "ratings_5");
		String errorStatus				= Utils.getRestApiTestData(39, "status_422");
		String errorTitle				= Utils.getRestApiTestData(39, "title_invalid_attribute");
		String errorDetail				= Utils.getRestApiTestData(39, "detail_testimonials_required");
		String errorPointer				= Utils.getRestApiTestData(39, "pointer_testimonial");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response writeTestimonialsResponse = objWriteTestimonials.getWriteTestimonialsResponseUserEndPoint(baseURL, accessTokenValue, typeTestimonial, ratings, testimonials, ipAddress, statusCode422);
		
		objWriteTestimonials.validateWriteTestimonialsErrorResponseUserEndPoint(objSoftAssertion, writeTestimonialsResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=13)
	public void validateMandatoryIPAddressWriteTestimonialEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		WriteTestimonials objWriteTestimonials = new WriteTestimonials(logger);
		
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
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeTestimonial			= Utils.getRestApiTestData(39, "testimonial_type");
		String testimonials				= Utils.generateRandomAlphabets(10);
		String ratings					= Utils.getRestApiTestData(39, "ratings_5");
		String errorStatus				= Utils.getRestApiTestData(39, "status_422");
		String errorTitle				= Utils.getRestApiTestData(39, "title_invalid_attribute");
		String errorDetail				= Utils.getRestApiTestData(39, "detail_ipaddress_required");
		String errorPointer				= Utils.getRestApiTestData(39, "pointer_ipaddress");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		ipAddress = Utils.getRestApiTestData(8, "emptyValue");
		
		Response writeTestimonialsResponse = objWriteTestimonials.getWriteTestimonialsResponseUserEndPoint(baseURL, accessTokenValue, typeTestimonial, ratings, testimonials, ipAddress, statusCode422);
		
		objWriteTestimonials.validateWriteTestimonialsErrorResponseUserEndPoint(objSoftAssertion, writeTestimonialsResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=14)
	public void validateInvalidIPAddressWriteTestimonialEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		WriteTestimonials objWriteTestimonials = new WriteTestimonials(logger);
		
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
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeTestimonial			= Utils.getRestApiTestData(39, "testimonial_type");
		String testimonials				= Utils.generateRandomAlphabets(10);
		String ratings					= Utils.getRestApiTestData(39, "ratings_5");
		String errorStatus				= Utils.getRestApiTestData(39, "status_422");
		String errorTitle				= Utils.getRestApiTestData(39, "title_invalid_attribute");
		String errorDetail				= Utils.getRestApiTestData(39, "detail_ipaddress_invalid");
		String errorPointer				= Utils.getRestApiTestData(39, "pointer_ipaddress");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		ipAddress = Utils.getRestApiTestData(10, "invalid_ipaddress");
		
		Response writeTestimonialsResponse = objWriteTestimonials.getWriteTestimonialsResponseUserEndPoint(baseURL, accessTokenValue, typeTestimonial, ratings, testimonials, ipAddress, statusCode422);
		
		objWriteTestimonials.validateWriteTestimonialsErrorResponseUserEndPoint(objSoftAssertion, writeTestimonialsResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=15)
	public void validateEmptyHeaderWriteTestimonialEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		WriteTestimonials objWriteTestimonials = new WriteTestimonials(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message");

		// New User Signup - POST Request
		Response writeTestimonialsResponse = objWriteTestimonials.getWriteTestimonialsResponseUserEndPoint(baseURL, statusCode401);
		
		// Validate SignupV1WithOTP success response - POST Request
		objWriteTestimonials.validateWriteTestimonialsErrorResponseUserEndpoint(objSoftAssertion, writeTestimonialsResponse, errorMessage);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=16)
	public void validateInvalidAuthorizationKeyValueWriteTestimonialEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		WriteTestimonials objWriteTestimonials = new WriteTestimonials(logger);
		
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
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String errorMessage				= Utils.getRestApiTestData(12, "message_invalid_json");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		accessTokenValue = Utils.getRestApiTestData(0, "auth_invalid_value");
		
		Response writeTestimonialsResponse = objWriteTestimonials.getWriteTestimonialsResponseUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode401);
		
		objWriteTestimonials.validateWriteTestimonialsErrorResponseUserEndpoint(objSoftAssertion, writeTestimonialsResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=17)
	public void validateInvalidContentTypeKeyValueWriteTestimonialEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		WriteTestimonials objWriteTestimonials = new WriteTestimonials(logger);
		
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
		int statusCode415				= Integer.parseInt(Utils.getRestApiTestData(9, "status_415"));
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_invalid_value");
		String errorStatus				= Utils.getRestApiTestData(12, "error_status");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		// New User Signup - POST Request
		Response writeTestimonialsResponse = objWriteTestimonials.getWriteTestimonialsResponseUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode415);
		
		// Validate SignupV1WithOTP success response - POST Request
		objWriteTestimonials.validateWriteTestimonialsErrorResponseUserEndpoint(objSoftAssertion, writeTestimonialsResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=18)
	public void validateInvalidAcceptTypeKeyValueWriteTestimonialEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		WriteTestimonials objWriteTestimonials = new WriteTestimonials(logger);
		
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
		int statusCode415				= Integer.parseInt(Utils.getRestApiTestData(9, "status_415"));
		String acceptValue				= Utils.getRestApiTestData(0, "accept_invalid_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");
		String errorStatus				= Utils.getRestApiTestData(12, "error_status");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		// New User Signup - POST Request
		Response writeTestimonialsResponse = objWriteTestimonials.getWriteTestimonialsResponseUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode415);
		
		// Validate SignupV1WithOTP success response - POST Request
		objWriteTestimonials.validateWriteTestimonialsErrorResponseUserEndpoint(objSoftAssertion, writeTestimonialsResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=19)
	public void validateEmptyAuthorizationKeyValueWriteTestimonialEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		WriteTestimonials objWriteTestimonials = new WriteTestimonials(logger);
		
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
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");
		String errorMessage				= Utils.getRestApiTestData(12, "message");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		accessTokenValue = Utils.getRestApiTestData(8, "emptyValue");

		// New User Signup - POST Request
		Response writeTestimonialsResponse = objWriteTestimonials.getWriteTestimonialsResponseUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode401);
		
		// Validate SignupV1WithOTP success response - POST Request
		objWriteTestimonials.validateWriteTestimonialsErrorResponseUserEndpoint(objSoftAssertion, writeTestimonialsResponse, errorMessage);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=20)
	public void validateEmptyAcceptTypeKeyValueWriteTestimonialEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		WriteTestimonials objWriteTestimonials = new WriteTestimonials(logger);
		
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
		int statusCode406				= Integer.parseInt(Utils.getRestApiTestData(9, "status_406"));
		String acceptValue				= Utils.getRestApiTestData(8, "emptyValue");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");
		String errorStatus				= Utils.getRestApiTestData(12, "error_status_406");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title_notacceptable");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail_accept_header");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		// New User Signup - POST Request
		Response writeTestimonialsResponse = objWriteTestimonials.getWriteTestimonialsResponseUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode406);
		
		// Validate SignupV1WithOTP success response - POST Request
		objWriteTestimonials.validateWriteTestimonialsErrorResponseUserEndpoint(objSoftAssertion, writeTestimonialsResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=21)
	public void validateEmptyContentTypeKeyValueWriteTestimonialEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		WriteTestimonials objWriteTestimonials = new WriteTestimonials(logger);
		
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
		int statusCode415				= Integer.parseInt(Utils.getRestApiTestData(9, "status_415"));
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(8, "emptyValue");
		String errorStatus				= Utils.getRestApiTestData(12, "error_status");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		// New User Signup - POST Request
		Response writeTestimonialsResponse = objWriteTestimonials.getWriteTestimonialsResponseUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode415);
		
		// Validate SignupV1WithOTP success response - POST Request
		objWriteTestimonials.validateWriteTestimonialsErrorResponseUserEndpoint(objSoftAssertion, writeTestimonialsResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

}