package com.ppa.api.endpoints.helper;

import java.awt.AWTException;

import org.testng.asserts.SoftAssert;

import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.base.Log;
import com.ppa.api.utilities.Restutils;
import com.ppa.api.utilities.Utils;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

public class FBLoginV1 extends Restutils {

	/************************************************************** Constructor ***********************************************************************/

	public FBLoginV1(ExtentTest logger) {

		this.logger = logger;
	}

	/******************************************************** Object Creation started ****************************************************************/

	
	/******************************************************** Variable Creation started ***************************************************************/

	public static final String AUTHORIZATIONKEY;
	public static final String ACCEPTKEY;
	public static final String ACCEPTVALUE;
	public static final String CONTENTTYPEKEY;
	public static final String CONTENTTYPEVALUE;
	public static final String ENDPOINT;
	public static final String OTPFLAG;
	public static final String FRONTENDURL;

	/********************************************************** Gpath Creation started ***************************************************************/

	String token								= "data.attributes.access_token";  
	String refreshToken							= "data.attributes.refresh_token";  
	String type									= "data.type";                                            
	String id									= "data.id";                                              
	String fullName								= "data.attributes.user_fullname";                                          
	String status								= "errors.status";                              
	String code									= "errors.code";                                                    
	String title								= "errors.title";                                         
	String detail								= "errors.detail";                           
	String pointer								= "errors.source.pointer";                 
	String parameter							= "errors.source.parameter";                                          
	String message								= "message";     
	String fbAccessToken						= "data.attributes.fb_access_token";                           
	String href									= "data.links.related.href";      
	String method								= "data.links.meta.method";                                
	String links								= "errors.links.about";     
	
	/******************************************************** Methods Creation started ****************************************************************/
	
	static {
		
		AUTHORIZATIONKEY	= Utils.getRestApiTestData(0, "auth_Key");
		ACCEPTKEY			= Utils.getRestApiTestData(0, "accept_key");
		ACCEPTVALUE			= Utils.getRestApiTestData(0, "accept_value");
		CONTENTTYPEKEY		= Utils.getRestApiTestData(0, "content_type_key");
		CONTENTTYPEVALUE	= Utils.getRestApiTestData(0, "content_type_value");
		ENDPOINT			= Utils.getRestApiTestData(2, "fb_login");
		OTPFLAG				= ConfigurationSetup.OTPFLAG;
		FRONTENDURL			= ConfigurationSetup.FRONTENDURL;
		
	}

	/* postDataWithoutDeviceInfoFBLoginV1 */
	public String postDataWithoutDeviceInfoForFBLoginV1(String type, String fbAccessToken, String fbUserID) {

		logger.log(LogStatus.INFO, "Generated type : " +type.trim());
		logger.log(LogStatus.INFO, "Generated fbAccessToken : " +fbAccessToken.trim());
		logger.log(LogStatus.INFO, "Generated fbUserID : " +fbUserID.trim());

		Log.info("Generated type : " +type.trim());
		Log.info("Generated fbAccessToken : " +fbAccessToken.trim());
		Log.info("Generated fbUserID : " +fbUserID.trim());
		
		logger.log(LogStatus.INFO, "About to post data for POST request");
		Log.info("About to post data for POST request");
		
		String postData =	"{\r\n" + 
							"  \"data\": {\r\n" + 
							"    \"type\": \""+type+"\",\r\n" + 
							"    \"attributes\": {\r\n" + 
							"      \"fb_access_token\": \""+fbAccessToken+"\",\r\n" + 
							"      \"fb_user_id\": "+fbUserID+"\r\n" + 
							"    }\r\n" + 
							"  }\r\n" + 
							"}";

		logger.log(LogStatus.PASS, "Post Data for POST request : " + postData.trim());
		Log.info("Post Data for POST request : " + postData.trim());
		
		return postData;
		
	}

	/* postDataWithDeviceInfoForFBLoginV1 */
	public String postDataWithDeviceInfoForFBLoginV1(String type, String fbAccessToken, String fbUserID, String fcmID, String deviceUniqueID, String imeiNumber) {

		logger.log(LogStatus.INFO, "Generated type : " +type.trim());
		logger.log(LogStatus.INFO, "Generated fbAccessToken : " +fbAccessToken.trim());
		logger.log(LogStatus.INFO, "Generated fbUserID : " +fbUserID.trim());
		logger.log(LogStatus.INFO, "Generated deviceUniqueID : " +deviceUniqueID.trim());
		logger.log(LogStatus.INFO, "Generated fcmID : " +fcmID.trim());
		logger.log(LogStatus.INFO, "Generated imeiNumber : " +imeiNumber.trim());

		Log.info("Generated type : " +type.trim());
		Log.info("Generated fbAccessToken : " +fbAccessToken.trim());
		Log.info("Generated fbUserID : " +fbUserID.trim());
		Log.info("Generated deviceUniqueID : " +deviceUniqueID.trim());
		Log.info("Generated fcmID : " +fcmID.trim());
		Log.info("Generated imeiNumber : " +imeiNumber.trim());
		
		logger.log(LogStatus.INFO, "About to post data for POST request");
		Log.info("About to post data for POST request");
		
		String postData =	"{\r\n" + 
							"  \"data\": {\r\n" + 
							"    \"type\": \""+type+"\",\r\n" + 
							"    \"attributes\": {\r\n" + 
							"      \"fb_access_token\": \""+fbAccessToken+"\",\r\n" + 
							"      \"fb_user_id\": "+fbUserID+",\r\n" + 
							"      \"device_info\": {\"fcm_id\": \""+fcmID+"\",\"device_unique_id\": \""+deviceUniqueID+"\",\"imei_number\": \""+imeiNumber+"\"}\r\n" + 
							"    }\r\n" + 
							"  }\r\n" + 
							"}";

		logger.log(LogStatus.PASS, "Post Data for POST request : " + postData.trim());
		Log.info("Post Data for POST request : " + postData.trim());
		
		return postData;
		
	}

	/* newFBLoginV1HelperEndPoint */
	public Response newFBLoginV1HelperEndPoint(String baseURL, String deviceType, int statusCode) throws InterruptedException, AWTException {

		// Getting the OTP with the help Profile setting GETCODE - With Email & Mobile Number
		logger.log(LogStatus.INFO, "******* About to login using user fb login v1 EndPoint ..! *******");
		Log.info("******* About to login using user fb login v1 EndPoint ..! *******");
		
		// Assigning value
		String completedURL				= baseURL+ENDPOINT+deviceType;
		Response fbLoginV1Response		= null;

		switch (OTPFLAG) {
		case "enable":
			
			fbLoginV1Response = postRequestSpecification(completedURL, statusCode);

			logger.log(LogStatus.PASS, "************ Successfully created fbLoginV1Response : " + fbLoginV1Response.asString());
			Log.info("************ Successfully created fbLoginV1Response : " + fbLoginV1Response.asString());
			
			break;
			
		case "disable":
			
			fbLoginV1Response = postRequestSpecification(completedURL, statusCode);

			logger.log(LogStatus.PASS, "************ Successfully created fbLoginV1Response : " + fbLoginV1Response.asString());
			Log.info("************ Successfully created fbLoginV1Response : " + fbLoginV1Response.asString());
			
			break;
			
		}

		return fbLoginV1Response;

	}

	/* newFBLoginV1HelperEndPoint */
	public Response newFBLoginV1HelperEndPoint(String baseURL, String accessTokenValue, String acceptValue, String contentTypeValue, String deviceType, int statusCode) throws InterruptedException, AWTException {

		// Getting the OTP with the help Profile setting GETCODE - With Email & Mobile Number
		logger.log(LogStatus.INFO, "******* About to login using user fb login v1 EndPoint ..! *******");
		Log.info("******* About to login using user fb login v1 EndPoint ..! *******");
		
		// Assigning value
		String authorizationValue		= accessTokenValue;
		String completedURL				= baseURL+ENDPOINT+deviceType;
		Response fbLoginV1Response		= null;

		fbLoginV1Response = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, acceptValue, CONTENTTYPEKEY, contentTypeValue, statusCode);

		logger.log(LogStatus.PASS, "************ Successfully created fbLoginV1Response : " + fbLoginV1Response.asString());
		Log.info("************ Successfully created fbLoginV1Response : " + fbLoginV1Response.asString());

		return fbLoginV1Response;

	}
	
	/* newFBLoginV1HelperEndPoint */
	public Response newFBLoginV1HelperEndPoint(String baseURL, String accessTokenValue, String dataType, String fbAccessToken, String fbUserID, String deviceType, int statusCode) throws InterruptedException, AWTException {

		// Getting the OTP with the help Profile setting GETCODE - With Email & Mobile Number
		logger.log(LogStatus.INFO, "******* About to login using user fb login v1 EndPoint ..! *******");
		Log.info("******* About to login using user fb login v1 EndPoint ..! *******");
		
		// Assigning value
		String authorizationValue		= accessTokenValue;
		String completedURL				= baseURL+ENDPOINT+deviceType;
		Response fbLoginV1Response		= null;
		String postData					= null;

		postData = this.postDataWithoutDeviceInfoForFBLoginV1(dataType, fbAccessToken, fbUserID);
		fbLoginV1Response = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, ACCEPTVALUE, CONTENTTYPEKEY, CONTENTTYPEVALUE, postData, statusCode);

		logger.log(LogStatus.PASS, "************ Successfully created fbLoginV1Response : " + fbLoginV1Response.asString());
		Log.info("************ Successfully created fbLoginV1Response : " + fbLoginV1Response.asString());

		return fbLoginV1Response;

	}

	/* newFBLoginV1HelperEndPoint */
	public Response newFBLoginV1HelperEndPoint(String baseURL, String accessTokenValue, String dataType, String fbAccessToken, String fbUserID, String fcmID, String deviceUniqueID, String imeiNumber, String deviceType, int statusCode) throws InterruptedException, AWTException {

		// Getting the OTP with the help Profile setting GETCODE - With Email & Mobile Number
		logger.log(LogStatus.INFO, "******* About to login using user fb login v1 EndPoint ..! *******");
		Log.info("******* About to login using user fb login v1 EndPoint ..! *******");
		
		// Assigning value
		String authorizationValue		= accessTokenValue;
		String completedURL				= baseURL+ENDPOINT+deviceType;
		Response fbLoginV1Response		= null;
		String postData					= null;

		postData = this.postDataWithDeviceInfoForFBLoginV1(dataType, fbAccessToken, fbUserID, fcmID, deviceUniqueID, imeiNumber);
		fbLoginV1Response = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, ACCEPTVALUE, CONTENTTYPEKEY, CONTENTTYPEVALUE, postData, statusCode);

		logger.log(LogStatus.PASS, "************ Successfully created fbLoginV1Response : " + fbLoginV1Response.asString());
		Log.info("************ Successfully created fbLoginV1Response : " + fbLoginV1Response.asString());

		return fbLoginV1Response;

	}

	/* validateFBLoginV1 Sucess Response HelperEndPoint */
	public void validateFBLoginV1SucessResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String userType, String userFullName) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate FBLoginV1 Sucess Response ******* ..!");
		Log.info("******* About to validate FBLoginV1 Sucess Response ******* ..!");
		
		
		assertionContainsWithoutUsingSubstring(type, userType, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(fullName, userFullName, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated FBLoginV1 Sucess Response ************");
		Log.info("************Successfully validated FBLoginV1 Sucess Response ************");

	}

	/* validateFBLoginV1 Sucess Response HelperEndPoint */
	public void validateFBLoginV1SucessResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String userType, String userID, String facebookAccessToken, String fbSignUpV1WithOTPURL, String httpMethod) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate FBLoginV1 Sucess Response ******* ..!");
		Log.info("******* About to validate FBLoginV1 Sucess Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(type, userType, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(id, userID, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(fbAccessToken, facebookAccessToken, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(href, fbSignUpV1WithOTPURL, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(method, httpMethod, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated FBLoginV1 Sucess Response ************");
		Log.info("************Successfully validated FBLoginV1 Sucess Response ************");

	}

	/* validateFBLoginV1 Error Response HelperEndPoint */
	public void validateFBLoginV1ErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate FBLoginV1 error Response ******* ..!");
		Log.info("******* About to validate FBLoginV1 error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(message, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated FBLoginV1 error Response ************");
		Log.info("************Successfully validated FBLoginV1 error Response ************");

	}

	/* validateFBLoginV1 error Response HelperEndPoint */
	public void validateFBLoginV1ErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorCode, String errorTitle) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate FBLoginV1 error Response ******* ..!");
		Log.info("******* About to validate FBLoginV1 error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated FBLoginV1 error Response ************");
		Log.info("************Successfully validated FBLoginV1 error Response ************");

	}

	/* validateFBLoginV1 error Response HelperEndPoint */
	public void validateFBLoginV1InvalidHeaderErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorLinks, String errorStatus, String errorTitle, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate FBLoginV1 error Response ******* ..!");
		Log.info("******* About to validate FBLoginV1 error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(links, errorLinks, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated FBLoginV1 error Response ************");
		Log.info("************Successfully validated FBLoginV1 error Response ************");

	}
	
	/* validateFBLoginV1 error Response HelperEndPoint */
	public void validateFBLoginV1ErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate FBLoginV1 error Response ******* ..!");
		Log.info("******* About to validate FBLoginV1 error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated FBLoginV1 error Response ************");
		Log.info("************Successfully validated FBLoginV1 error Response ************");

	}

	/* validateFBLoginV1 error Response HelperEndPoint */
	public void validateFBLoginV1ErrorResponseHelperEndPoint_(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate FBLoginV1 error Response ******* ..!");
		Log.info("******* About to validate FBLoginV1 error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated FBLoginV1 error Response ************");
		Log.info("************Successfully validated FBLoginV1 error Response ************");

	}

	/* validateFBLoginV1 error Response HelperEndPoint */
	public void validateFBLoginV1ErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate FBLoginV1 error Response ******* ..!");
		Log.info("******* About to validate FBLoginV1 error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated FBLoginV1 error Response ************");
		Log.info("************Successfully validated FBLoginV1 error Response ************");

	}

	/* validateFBLoginV1 error Response HelperEndPoint */
	public void validateFBLoginV1ErrorResponseHelperEndPoint_(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate FBLoginV1 error Response ******* ..!");
		Log.info("******* About to validate FBLoginV1 error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated FBLoginV1 error Response ************");
		Log.info("************Successfully validated FBLoginV1 error Response ************");

	}

	/* getAccessToken */
	public String getAccessToken(Response response) throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to get Access token ..! *******");
		Log.info("******* About to get Access token ..! *******");
		
		String accessToken = "Bearer " + response.path(token);
		
		logger.log(LogStatus.PASS, "Access token value is :  " + accessToken);
		Log.info("Access token value is :  " + accessToken);

		return accessToken;

	}
	
	/* getRefreshToken */
	public String getRefreshToken(Response response) throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to get refresh token ..! *******");
		Log.info("******* About to get refresh token ..! *******");
		
		String refreshtoken =  response.path(refreshToken);
		
		logger.log(LogStatus.PASS, "refresh token value is :  " + refreshtoken);
		Log.info("refresh token value is :  " + refreshtoken);

		return refreshtoken;

	}

	/* getRefreshToken */
	public String getFBSignUpEndPointURL(Response response) throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to get FBSignUpEndPointURL ..! *******");
		Log.info("******* About to get FBSignUpEndPointURL ..! *******");
		
		String fbSignUpEndPointURL =  response.path(href);
		
		logger.log(LogStatus.PASS, "FBSignUpEndPointURL is :  " + fbSignUpEndPointURL);
		Log.info("FBSignUpEndPointURL is :  " + fbSignUpEndPointURL);

		return fbSignUpEndPointURL;

	}

	
}