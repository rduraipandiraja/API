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

public class RaiseTicket extends Restutils {

	/************************************************************** Constructor ***********************************************************************/

	public RaiseTicket(ExtentTest logger) {

		this.logger = logger;
	}

	/******************************************************** Object Creation started ****************************************************************/

	
	/******************************************************** Variable Creation started ***************************************************************/

	public static final String AUTHORIZATIONKEY;
	public static final String CONTENTTYPEKEY;
	public static final String CONTENTTYPEVALUE;
	public static final String ENDPOINTUSERS;
	public static final String ENDPOINTRAISETICKET;
	public static final String FRONTENDURL;

	/********************************************************** Gpath Creation started ***************************************************************/

	String token								= "data.attributes.access_token";  
	String refreshToken							= "data.attributes.refresh_token";  
	String type									= "data.type";                                            
	String id									= "data.id";                                              
	String self									= "data.links.self";                                            
	String mesage								= "meta.message";                                                             
	String status								= "errors.status";                              
	String code									= "errors.code";                                                    
	String title								= "errors.title";                                         
	String detail								= "errors.detail";                           
	String pointer								= "errors.source.pointer";                 
	String parameter							= "errors.source.parameter";                                           
	String message								= "message";                                                        
	String ticketAttachment						= "ticket_attachment";                                               
	String transactionID						= "transaction_id";                                               
	String totalAmountPaid						= "total_amount_paid";                                             
	String couponCodeUsed						= "coupon_code_used";                                               
	String transactionDetails					= "transaction_details";                                 
	String links								= "errors.links.about";    
	
	/******************************************************** Methods Creation started ****************************************************************/
	
	static {
		
		AUTHORIZATIONKEY	= Utils.getRestApiTestData(0, "auth_Key");
		CONTENTTYPEKEY		= Utils.getRestApiTestData(0, "content_type_key");
		CONTENTTYPEVALUE	= Utils.getRestApiTestData(0, "content_type_multipart_value");
		ENDPOINTUSERS		= Utils.getRestApiTestData(2, "users");
		ENDPOINTRAISETICKET	= Utils.getRestApiTestData(2, "tickets");
		FRONTENDURL			= ConfigurationSetup.FRONTENDURL;
		
	}
	
	/* raiseTicketEndPoint */
	public Response raiseMissingTicketRaiseTicketEndPoint(String baseURL, String pathParameter, int statusCode) throws InterruptedException, AWTException {

		// Getting the OTP with the help Profile setting GETCODE - With Email & Mobile Number
		logger.log(LogStatus.INFO, "******* About to raise missing ticket using raiseTicket EndPoint ..! *******");
		Log.info("******* About to raise missing ticket using raiseTicket EndPoint ..! *******");
		
		// Assigning value
		String completedURL				= baseURL+ENDPOINTUSERS+ENDPOINTRAISETICKET+pathParameter;
		Response RaiseTicketResponse= null;
		
		RaiseTicketResponse = postRequestSpecification(completedURL, statusCode);

		logger.log(LogStatus.PASS, "************ Successfully created RaiseTicketResponse : " + RaiseTicketResponse.asString());
		Log.info("************ Successfully created RaiseTicketResponse : " + RaiseTicketResponse.asString());

		return RaiseTicketResponse;

	}

	/* raiseTicketEndPoint */
	public Response raiseMissingTicketRaiseTicketEndPoint(String baseURL, String accessTokenValue, String pathParameter, String transactionID, String totalAmountPaid, String couponCodeUsed, String transactionDetails, int statusCode) throws InterruptedException, AWTException {

		// Getting the OTP with the help Profile setting GETCODE - With Email & Mobile Number
		logger.log(LogStatus.INFO, "******* About to raise missing ticket using raiseTicket EndPoint ..! *******");
		Log.info("******* About to raise missing ticket using raiseTicket EndPoint ..! *******");
		
		// Assigning value
		String authorizationValue		= accessTokenValue;
		String completedURL				= baseURL+ENDPOINTUSERS+ENDPOINTRAISETICKET+pathParameter;
		Response RaiseTicketResponse= null;
		
		RaiseTicketResponse = postRequestSpecificationQueryParameters(completedURL, AUTHORIZATIONKEY, authorizationValue, transactionID, totalAmountPaid, couponCodeUsed, transactionDetails, statusCode);

		logger.log(LogStatus.PASS, "************ Successfully created RaiseTicketResponse : " + RaiseTicketResponse.asString());
		Log.info("************ Successfully created RaiseTicketResponse : " + RaiseTicketResponse.asString());

		return RaiseTicketResponse;

	}
	
	/* raiseTicketEndPoint */
	public Response raiseMissingTicketRaiseTicketEndPoint(String baseURL, String accessTokenValue, String pathParameter, String transactionID, String totalAmountPaid, String couponCodeUsed, String transactionDetails, String fileName, int statusCode) throws InterruptedException, AWTException {

		// Getting the OTP with the help Profile setting GETCODE - With Email & Mobile Number
		logger.log(LogStatus.INFO, "******* About to raise missing ticket using raiseTicket EndPoint ..! *******");
		Log.info("******* About to raise missing ticket using raiseTicket EndPoint ..! *******");
		
		// Assigning value
		String authorizationValue		= accessTokenValue;
		String completedURL				= baseURL+ENDPOINTUSERS+ENDPOINTRAISETICKET+pathParameter;
		Response RaiseTicketResponse	= null;
		
		RaiseTicketResponse = postRequestSpecificationQueryParameters(completedURL, AUTHORIZATIONKEY, authorizationValue, CONTENTTYPEKEY, CONTENTTYPEVALUE, transactionID, totalAmountPaid, couponCodeUsed, transactionDetails, fileName, statusCode);

		logger.log(LogStatus.PASS, "************ Successfully created RaiseTicketResponse : " + RaiseTicketResponse.asString());
		Log.info("************ Successfully created RaiseTicketResponse : " + RaiseTicketResponse.asString());

		return RaiseTicketResponse;

	}

	/* validateRaiseTicket Sucess Response HelperEndPoint */
	public void validateRaiseTicketSucessResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String userType, String message) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate RaiseTicket Sucess Response ******* ..!");
		Log.info("******* About to validate RaiseTicket Sucess Response ******* ..!");
		
		
		assertionContainsWithoutUsingSubstring(type, userType, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(mesage, message, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated RaiseTicket Sucess Response ************");
		Log.info("************Successfully validated RaiseTicket Sucess Response ************");

	}

	/* validateRaiseTicket Error Response HelperEndPoint */
	public void validateRaiseTicketTicketAttachmentErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorMessageOne, String errorMessageTwo) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate RaiseTicket error Response ******* ..!");
		Log.info("******* About to validate RaiseTicket error Response ******* ..!");
		
		try {
			
			assertionContainsWithoutUsingSubstring(ticketAttachment, errorMessageOne, objSoftAssertion, response);
			
		} catch (Exception e) {
			
			assertionContainsWithoutUsingSubstring(ticketAttachment, errorMessageTwo, objSoftAssertion, response);
		}
		
		logger.log(LogStatus.PASS, "************Successfully validated RaiseTicket error Response ************");
		Log.info("************Successfully validated RaiseTicket error Response ************");

	}

	/* validateRaiseTicket Error Response HelperEndPoint */
	public void validateRaiseTicketTicketAttachmentErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate RaiseTicket error Response ******* ..!");
		Log.info("******* About to validate RaiseTicket error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(ticketAttachment, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated RaiseTicket error Response ************");
		Log.info("************Successfully validated RaiseTicket error Response ************");

	}

	/* validateRaiseTicket Error Response HelperEndPoint */
	public void validateRaiseTicketErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate RaiseTicket error Response ******* ..!");
		Log.info("******* About to validate RaiseTicket error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(message, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated RaiseTicket error Response ************");
		Log.info("************Successfully validated RaiseTicket error Response ************");

	}

	/* validateRaiseTicket Error Response HelperEndPoint */
	public void validateRaiseTicketTransactionIDErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate RaiseTicket error Response ******* ..!");
		Log.info("******* About to validate RaiseTicket error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(transactionID, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated RaiseTicket error Response ************");
		Log.info("************Successfully validated RaiseTicket error Response ************");

	}

	/* validateRaiseTicket Error Response HelperEndPoint */
	public void validateRaiseTicketTotalAmountPaidErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate RaiseTicket error Response ******* ..!");
		Log.info("******* About to validate RaiseTicket error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(totalAmountPaid, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated RaiseTicket error Response ************");
		Log.info("************Successfully validated RaiseTicket error Response ************");

	}

	/* validateRaiseTicket Error Response HelperEndPoint */
	public void validateRaiseTicketCouponCodeUsedErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate RaiseTicket error Response ******* ..!");
		Log.info("******* About to validate RaiseTicket error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(couponCodeUsed, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated RaiseTicket error Response ************");
		Log.info("************Successfully validated RaiseTicket error Response ************");

	}

	/* validateRaiseTicket Error Response HelperEndPoint */
	public void validateRaiseTicketTransactionDetailsErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate RaiseTicket error Response ******* ..!");
		Log.info("******* About to validate RaiseTicket error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(transactionDetails, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated RaiseTicket error Response ************");
		Log.info("************Successfully validated RaiseTicket error Response ************");

	}

	/* validateRaiseTicket error Response HelperEndPoint */
	public void validateRaiseTicketInvalidHeaderErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorLinks, String errorStatus, String errorTitle, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate RaiseTicket error Response ******* ..!");
		Log.info("******* About to validate RaiseTicket error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(links, errorLinks, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated RaiseTicket error Response ************");
		Log.info("************Successfully validated RaiseTicket error Response ************");

	}
	
	/* validateRaiseTicket error Response HelperEndPoint */
	public void validateRaiseTicketErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate RaiseTicket error Response ******* ..!");
		Log.info("******* About to validate RaiseTicket error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated RaiseTicket error Response ************");
		Log.info("************Successfully validated RaiseTicket error Response ************");

	}

	/* validateRaiseTicket error Response HelperEndPoint */
	public void validateRaiseTicketErrorResponseHelperEndPoint_(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate RaiseTicket error Response ******* ..!");
		Log.info("******* About to validate RaiseTicket error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated RaiseTicket error Response ************");
		Log.info("************Successfully validated RaiseTicket error Response ************");

	}

	/* validateRaiseTicket error Response HelperEndPoint */
	public void validateRaiseTicketErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate RaiseTicket error Response ******* ..!");
		Log.info("******* About to validate RaiseTicket error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated RaiseTicket error Response ************");
		Log.info("************Successfully validated RaiseTicket error Response ************");

	}

	/* validateRaiseTicket error Response HelperEndPoint */
	public void validateRaiseTicketErrorResponseHelperEndPoint_(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate RaiseTicket error Response ******* ..!");
		Log.info("******* About to validate RaiseTicket error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated RaiseTicket error Response ************");
		Log.info("************Successfully validated RaiseTicket error Response ************");

	}

	/* validateRaiseTicket error Response HelperEndPoint */
	public void validateRaiseTicketErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorCode, String errorTitle) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate RaiseTicket error Response ******* ..!");
		Log.info("******* About to validate RaiseTicket error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated RaiseTicket error Response ************");
		Log.info("************Successfully validated RaiseTicket error Response ************");

	}

	/* getTicketID */
	public String getTicketID(Response response) throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to get ticket id ..! *******");
		Log.info("******* About to get ticket id ..! *******");
		
		int ticketid = response.path(id);
		String ticketID = Integer.toString(ticketid);
		
		logger.log(LogStatus.PASS, "Ticket id is :  " + ticketID);
		Log.info("Ticket id is :  " + ticketID);

		return ticketID;

	}

}