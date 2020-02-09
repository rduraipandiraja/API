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

public class TicketDetails extends Restutils {

	/************************************************************** Constructor **********************************************************************/

	public TicketDetails(ExtentTest logger) {

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
	String createdDateData		= "data.attributes.created_date"; 
	String merchantNameData		= "data.attributes.merchant_name"; 
	String referenceIDData		= "data.attributes.reference_id"; 
	String currencyData			= "data.attributes.currency"; 
	String couponUsedData		= "data.attributes.coupon_used"; 
	String detailsData			= "data.attributes.details"; 
	String statusData			= "data.attributes.status"; 
	String updatedDateData		= "data.attributes.updated_date"; 
	String amountData			= "data.attributes.transaction.amount";                                
	String status				= "errors.status";                              
	String code					= "errors.code";                                                    
	String title				= "errors.title";                                         
	String detail				= "errors.detail";                           
	String parameter			= "errors.source.parameter";  
	String mesage				= "message";                             
	String links				= "errors.links.about";    ;                   
	String data					= "data";           
	
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
	
	/* getTicketDetailsResponseUserEndPoint */
	public Response getTicketDetailsResponseUserEndPoint(String baseURL, String queryParameter, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get tickets details response ******* ..!");
		Log.info("******* About to get tickets details response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTUSERS+ENDPOINTTICKETS+queryParameter;

		// AuthType setup based on conditions
		Response myEarningsResponse = getRequestSpecification(completedURL, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got ticket details END POINT  response :  " + myEarningsResponse.asString());
		Log.info("************Successfully got ticket details END POINT  response :  " + myEarningsResponse.asString());

		return myEarningsResponse;

	}
	
	/* getTicketDetailsResponseUserEndPoint */
	public Response getTicketDetailsResponseUserEndPoint(String baseURL, String signUpUserToken, String queryParameter, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get tickets details response ******* ..!");
		Log.info("******* About to get tickets details response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTUSERS+ENDPOINTTICKETS+queryParameter;

		// AuthType setup based on conditions
		Response myEarningsResponse = getRequestSpecification(completedURL, AUTHORIZATIONKEY, signUpUserToken, ACCEPTKEY, ACCEPTVALUE, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got ticket details END POINT  response :  " + myEarningsResponse.asString());
		Log.info("************Successfully got ticket details END POINT  response :  " + myEarningsResponse.asString());

		return myEarningsResponse;

	}

	/* getTicketDetailsResponseUserEndPoint */
	public Response getTicketDetailsResponseUserEndPoint(String baseURL, String signUpUserToken, String AcceptValue, String queryParameter, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get tickets details response ******* ..!");
		Log.info("******* About to get tickets details response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTUSERS+ENDPOINTTICKETS+queryParameter;

		// AuthType setup based on conditions
		Response myEarningsResponse = getRequestSpecification(completedURL, AUTHORIZATIONKEY, signUpUserToken, ACCEPTKEY, AcceptValue, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got ticket details END POINT  response :  " + myEarningsResponse.asString());
		Log.info("************Successfully got ticket details END POINT  response :  " + myEarningsResponse.asString());

		return myEarningsResponse;

	}

	/* validateTicketDetailsSuccessResponseUserEndPoint */
	public void validateTicketDetailsSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String type, String id, String createdDate, String merchantName, String referenceID, String couponUsed, String details, String status, String updatedDate, String currency, String amount) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate tickets details response ******* ..!");
		Log.info("******* About to validate tickets details response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(typeData, type, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(idData, id, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(createdDateData, createdDate, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(merchantNameData, merchantName, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(referenceIDData, referenceID, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(couponUsedData, couponUsed, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detailsData, details, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(statusData, status, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(updatedDateData, updatedDate, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(currencyData, currency, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(amountData, amount, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated ticket details END POINT  response************");
		Log.info("************Successfully validated ticket details END POINT  response************");

	}

	/* validateTicketDetailsSuccessResponseUserEndPoint */
	public void validateTicketDetailsSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String relationships) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate tickets details response ******* ..!");
		Log.info("******* About to validate tickets details response ******* ..!");
		
		assertionContainsWithoutUsingSubstring("data.relationships", relationships, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated ticket details END POINT  response************");
		Log.info("************Successfully validated ticket details END POINT  response************");

	}

	/* validateTicketDetailsSuccessResponseUserEndPoint */
	public void validateTicketDetailsSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String index, String type, String from, String comments) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate tickets details response ******* ..!");
		Log.info("******* About to validate tickets details response ******* ..!");
		
		assertionContainsWithoutUsingSubstring("data.relationships.reply.data.type["+index+"]", type, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.relationships.reply.data.attributes.from["+index+"]", from, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.relationships.reply.data.attributes.comments["+index+"]", comments, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated ticket details END POINT  response************");
		Log.info("************Successfully validated ticket details END POINT  response************");

	}

	/* validateTicketDetailsSuccessResponseUserEndPoint */
	public void validateTicketDetailsSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String index, String type, String id, String from, String comments) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate tickets details response ******* ..!");
		Log.info("******* About to validate tickets details response ******* ..!");
		
		assertionContainsWithoutUsingSubstring("data.relationships.reply.data.type["+index+"]", type, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.relationships.reply.data.id["+index+"]", id, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.relationships.reply.data.attributes.from["+index+"]", from, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.relationships.reply.data.attributes.comments["+index+"]", comments, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated ticket details END POINT  response************");
		Log.info("************Successfully validated ticket details END POINT  response************");

	}

	/* validateTicketDetailsErrorResponseUserEndpoint */
	public void validateTicketDetailsErrorResponseUserEndpoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate Tickets details error response ******* ..!");
		Log.info("******* About to validate Tickets details error response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated Tickets details END POINT error response************");
		Log.info("************Successfully validated Tickets details END POINT error response************");

	}

	/* validateTicketDetailsErrorResponseUserEndpoint */
	public void validateTicketDetailsErrorResponseUserEndpoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate Tickets details error response ******* ..!");
		Log.info("******* About to validate Tickets details error response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(mesage, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated Tickets details END POINT error response************");
		Log.info("************Successfully validated Tickets details END POINT error response************");

	}

	/* validateTicketDetailsErrorResponseUserEndpoint */
	public void validateTicketDetailsErrorResponseUserEndpoint(SoftAssert objSoftAssertion, Response response, String errorLinks, String errorStatus, String errorTitle, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate Tickets details error response ******* ..!");
		Log.info("******* About to validate Tickets details error response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(links, errorLinks, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated Tickets details END POINT error response************");
		Log.info("************Successfully validated Tickets details END POINT error response************");

	}
}