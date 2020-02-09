package com.ppa.api.endpoints.users;

import java.awt.AWTException;

import org.testng.asserts.SoftAssert;

import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.base.Log;
import com.ppa.api.utilities.Restutils;
import com.ppa.api.utilities.Utils;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

public class TicketReply extends Restutils {

	/************************************************************** Constructor **********************************************************************/

	public TicketReply(ExtentTest logger) {

		this.logger = logger;
	}
	
	/******************************************************** Object Creation started ****************************************************************/

	
	/******************************************************** Variable Creation started ***************************************************************/

	public static final String AUTHORIZATIONKEY;
	public static final String ACCEPTKEY;
	public static final String ACCEPTVALUE;
	public static final String CONTENTTYPEKEY;
	public static final String CONTENTTYPEVALUE;
	public static final String ENDPOINTUSERS;
	public static final String ENDPOINTTICKETS;
	public static final String FRONTENDURL;
	
	/********************************************************** Gpath Creation started ***************************************************************/

	String typeData				= "data.type"; 
	String idData				= "data.id"; 
	String message				= "meta.message"; 
	String status				= "errors.status";                              
	String code					= "errors.code";                                                    
	String title				= "errors.title";                                         
	String detail				= "errors.detail";                           
	String parameter			= "errors.source.parameter";  
	String mesage				= "message";                             
	String links				= "errors.links.about";                   
	String data					= "data";                     
	String pointer				= "errors.source.pointer";         
	
	/******************************************************** Methods Creation started ****************************************************************/
	
	static {
		
		AUTHORIZATIONKEY	= Utils.getRestApiTestData(0, "auth_Key");
		ACCEPTKEY			= Utils.getRestApiTestData(0, "accept_key");
		ACCEPTVALUE			= Utils.getRestApiTestData(0, "accept_value");
		CONTENTTYPEKEY		= Utils.getRestApiTestData(0, "content_type_key");
		CONTENTTYPEVALUE	= Utils.getRestApiTestData(0, "content_type_value");
		ENDPOINTUSERS		= Utils.getRestApiTestData(2, "users");
		ENDPOINTTICKETS		= Utils.getRestApiTestData(2, "tickets");
		FRONTENDURL			= ConfigurationSetup.FRONTENDURL;
		
	}
	
	/* postDataForTicketReply */
	public String postDataForTicketReply(String type, String replyMessage) {

		logger.log(LogStatus.INFO, "About to post data for POST request");
		Log.info("About to post data for POST request");

		String postData =	"{\r\n" + 
							"  \"data\": {\r\n" + 
							"    \"type\": \""+type+"\",\r\n" + 
							"    \"attributes\": {\r\n" + 
							"      \"reply_message\": \""+replyMessage+"\"\r\n" + 
							"    }\r\n" + 
							"  }\r\n" + 
							"}";

		logger.log(LogStatus.PASS, "Post Data for POST request : " + postData.trim());
		Log.info("Post Data for POST request : " + postData.trim());
		
		return postData;
		
	}

	/* getTicketReplyResponseUserEndPoint */
	public Response getTicketReplyResponseUserEndPoint(String baseURL, String queryParameter, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get ticket reply response ******* ..!");
		Log.info("******* About to get ticket reply response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTUSERS+ENDPOINTTICKETS+queryParameter;

		// AuthType setup based on conditions
		Response myEarningsResponse = postRequestSpecification(completedURL, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got ticket details END POINT  response :  " + myEarningsResponse.asString());
		Log.info("************Successfully got ticket details END POINT  response :  " + myEarningsResponse.asString());

		return myEarningsResponse;

	}
	
	/* getTicketReplyResponseUserEndPoint */
	public Response getTicketReplyResponseUserEndPoint(String baseURL, String signUpUserToken, String queryParameter, String dataType, String replyMessage, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get ticket reply response ******* ..!");
		Log.info("******* About to get ticket reply response ******* ..!");
		
		// Assigning values
		String postData = null;
		String completedURL = baseURL+ENDPOINTUSERS+ENDPOINTTICKETS+queryParameter;

		// AuthType setup based on conditions
		postData = postDataForTicketReply(dataType, replyMessage);
		Response myEarningsResponse = postRequestSpecification(completedURL, AUTHORIZATIONKEY, signUpUserToken, CONTENTTYPEKEY, CONTENTTYPEVALUE, ACCEPTKEY, ACCEPTVALUE, postData, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got ticket details END POINT  response :  " + myEarningsResponse.asString());
		Log.info("************Successfully got ticket details END POINT  response :  " + myEarningsResponse.asString());

		return myEarningsResponse;

	}

	/* getTicketReplyResponseUserEndPoint */
	public Response getTicketReplyResponseUserEndpoint(String baseURL, String signUpUserToken, String AcceptValue, String contentTypeValue, String queryParameter, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get ticket reply response ******* ..!");
		Log.info("******* About to get ticket reply response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTUSERS+ENDPOINTTICKETS+queryParameter;

		// AuthType setup based on conditions
		Response myEarningsResponse = postRequestSpecification(completedURL, AUTHORIZATIONKEY, signUpUserToken, ACCEPTKEY, AcceptValue, CONTENTTYPEKEY, contentTypeValue, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got ticket details END POINT  response :  " + myEarningsResponse.asString());
		Log.info("************Successfully got ticket details END POINT  response :  " + myEarningsResponse.asString());

		return myEarningsResponse;

	}

	/* validateTicketReplySuccessResponseUserEndPoint */
	public void validateTicketReplySuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String type, String mesage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate ticket reply response ******* ..!");
		Log.info("******* About to validate ticket reply response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(typeData, type, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(message, mesage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated ticket details END POINT  response************");
		Log.info("************Successfully validated ticket details END POINT  response************");

	}

	/* validateTicketReplyErrorResponseUserEndpoint */
	public void validateTicketReplyErrorResponseUserEndpoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate ticket reply error response ******* ..!");
		Log.info("******* About to validate ticket reply error response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated ticket reply END POINT error response************");
		Log.info("************Successfully validated ticket reply END POINT error response************");

	}

	/* validateTicketReplyErrorResponseUserEndpoint */
	public void validateTicketReplyErrorResponseUserEndpoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate ticket reply error response ******* ..!");
		Log.info("******* About to validate ticket reply error response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(mesage, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated ticket reply END POINT error response************");
		Log.info("************Successfully validated ticket reply END POINT error response************");

	}

	/* validateTicketReplyErrorResponseUserEndpoint */
	public void validateTicketReplyErrorResponseUserEndpoint(SoftAssert objSoftAssertion, Response response, String errorLinks, String errorStatus, String errorTitle, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate ticket reply error response ******* ..!");
		Log.info("******* About to validate ticket reply error response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(links, errorLinks, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated ticket reply END POINT error response************");
		Log.info("************Successfully validated ticket reply END POINT error response************");

	}

	/* validateFBLoginV1 error Response HelperEndPoint */
	public void validateTicketReplyErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate TicketReply error Response ******* ..!");
		Log.info("******* About to validate TicketReply error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated TicketReply error Response ************");
		Log.info("************Successfully validated TicketReply error Response ************");

	}

	/* replyTicketID */
	public String replyTicketID(Response response) throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to get ticket reply id ..! *******");
		Log.info("******* About to get ticket reply id ..! *******");
		
		int ticketid = response.path(idData);
		String ticketID = Integer.toString(ticketid);
		
		logger.log(LogStatus.PASS, "Ticket reply id  is :  " + ticketID);
		Log.info("Ticket reply id is :  " + ticketID);

		return ticketID;

	}

	
}