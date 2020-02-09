package com.ppa.api.endpoints.users;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.base.Log;
import com.ppa.api.utilities.Restutils;
import com.ppa.api.utilities.Utils;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

public class ExitClicks extends Restutils {

	/************************************************************** Constructor **********************************************************************/

	public ExitClicks(WebDriver driver, ExtentTest logger) {

		this.logger = logger;
		this.driver = driver;
	}
	
	/******************************************************** Object Creation started ****************************************************************/

	
	/******************************************************** Variable Creation started ***************************************************************/

	public static final String AUTHORIZATIONKEY;
	public static final String ACCEPTKEY;
	public static final String ACCEPTVALUE;
	public static final String CONTENTTYPEKEY;
	public static final String CONTENTTYPEVALUE;
	public static final String ENDPOINTUSERS;
	public static final String ENDPOINTEXITCLICK;
	public static final String CREATESHARELINK;
	public static final String FRONTENDURL;
	
	/********************************************************** Gpath Creation started ***************************************************************/

	String status								= "errors.status";                              
	String code									= "errors.code";                                                    
	String title								= "errors.title";                                         
	String detail								= "errors.detail";                           
	String parameter							= "errors.source.parameter";  
	String mesage								= "message";                             
	String links								= "errors.links.about";  
	
	/******************************************************** Methods Creation started ****************************************************************/
	
	static {
		
		AUTHORIZATIONKEY	= Utils.getRestApiTestData(0, "auth_Key");
		ACCEPTKEY			= Utils.getRestApiTestData(0, "accept_key");
		ACCEPTVALUE			= Utils.getRestApiTestData(0, "accept_value");
		CONTENTTYPEKEY		= Utils.getRestApiTestData(0, "content_type_key");
		CONTENTTYPEVALUE	= Utils.getRestApiTestData(0, "content_type_value");
		ENDPOINTUSERS		= Utils.getRestApiTestData(2, "users");
		ENDPOINTEXITCLICK	= Utils.getRestApiTestData(2, "exitClick");
		CREATESHARELINK		= Utils.getConfigurationSetupTestData(0, "create_sharelink");
		FRONTENDURL			= ConfigurationSetup.FRONTENDURL;
		
	}

	/* exitClickBackDate */
	public void exitClickBackDate(String email, String exitID, String date) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to backdate exit click ..! *******");
		Log.info("******* About to backdate exit click ..! *******");
		
		// Assigning Values
		int statusCode						= 201;
		String queryParameterPage			= Utils.getRestApiTestData(33, "queryparamOne_key");
		String queryParameterPageValue		= Utils.getRestApiTestData(33, "queryparamOne_value");
		String queryParameterExitID			= Utils.getRestApiTestData(33, "queryparamtwo_key");
		String queryParameterExitIDValue	= exitID;
		String queryParameterDate			= Utils.getRestApiTestData(33, "queryparamthree_key");
		String queryParameterDateValue		= date;
		String baseURL						= FRONTENDURL+Utils.getRestApiTestData(3, "basepath");
		Response response					= null;
		
		response = exitClickBackDate(queryParameterPage, queryParameterPageValue, queryParameterExitID, queryParameterExitIDValue, queryParameterDate, queryParameterDateValue, baseURL, statusCode);
		
		logger.log(LogStatus.PASS, "************ Exit click back date response: "+response.asString()+"************");
		Log.info("************ Exit click back date response: "+response.asString()+"************");
		
	}

	/* createExitClick */
	public String createExitClick(String storeID, int referralID, String shareExitClick, String retailer, String partnerHas) throws InterruptedException, AWTException {

		String exitClickURL			= null;
		
		switch (CREATESHARELINK) {
		case "enable":
			
			exitClickURL = shareExitClick;

			break;
			
		case "disable":
			
			switch (partnerHas) {
			case "app":
				exitClickURL = ConfigurationSetup.FRONTENDURL + Utils.getRestApiTestData(6, "visit_retailer_app") + storeID + Utils.getRestApiTestData(6, "id") + referralID;

				break;
				
			case "desktop":
				exitClickURL = ConfigurationSetup.FRONTENDURL + Utils.getRestApiTestData(6, "visit_retailer_dsk") + storeID + Utils.getRestApiTestData(6, "id") + referralID;

				break;
			}
			
			break;
		}

		logger.log(LogStatus.INFO, "******* About to create exit click using URL formed: "+exitClickURL+ "******* ..!");
		Log.info("******* About to create exit click using URL formed: "+exitClickURL+ "******* ..!");
		
		Utils.loadURL(driver, exitClickURL);
		Utils.waitingTillRequiredURLVisible(driver, retailer);
		
		logger.log(LogStatus.PASS, "************Successfully redirected to retailer :  " + exitClickURL);
		Log.info("************Successfully redirected to retailer :  " + exitClickURL);
		
		return exitClickURL;

	}
	
	/* getExitClickResponseUserEndPoint */
	public Response getExitClickResponseUserEndPoint(String baseURL, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get Exit Click response ******* ..!");
		Log.info("******* About to get Exit Click response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTUSERS+ENDPOINTEXITCLICK;

		// AuthType setup based on conditions
		Response myEarningsResponse = getRequestSpecification(completedURL, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got Exit Click END POINT  response :  " + myEarningsResponse.asString());
		Log.info("************Successfully got Exit Click END POINT  response :  " + myEarningsResponse.asString());

		return myEarningsResponse;

	}
	
	/* getExitClickResponseUserEndPoint */
	public Response getExitClickResponseUserEndPoint(String baseURL, String signUpUserToken, String pathParameter, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get Exit Click response ******* ..!");
		Log.info("******* About to get Exit Click response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTUSERS+ENDPOINTEXITCLICK+"/"+pathParameter;

		// AuthType setup based on conditions
		Response myEarningsResponse = getRequestSpecification(completedURL, AUTHORIZATIONKEY, signUpUserToken, ACCEPTKEY, ACCEPTVALUE, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got Exit Click END POINT  response :  " + myEarningsResponse.asString());
		Log.info("************Successfully got Exit Click END POINT  response :  " + myEarningsResponse.asString());

		return myEarningsResponse;

	}

	/* getExitClickResponseUserEndPoint */
	public Response getExitClickResponseUserEndpoint(String baseURL, String signUpUserToken, String AcceptValue, String pathParameter, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get Exit Click response ******* ..!");
		Log.info("******* About to get Exit Click response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTUSERS+ENDPOINTEXITCLICK+"/"+pathParameter;

		// AuthType setup based on conditions
		Response myEarningsResponse = getRequestSpecification(completedURL, AUTHORIZATIONKEY, signUpUserToken, ACCEPTKEY, AcceptValue, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got Exit Click END POINT  response :  " + myEarningsResponse.asString());
		Log.info("************Successfully got Exit Click END POINT  response :  " + myEarningsResponse.asString());

		return myEarningsResponse;

	}

	/* validateExitClickSuccessResponseUserEndPoint */
	public void validateExitClickSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String data) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate Exit Click response ******* ..!");
		Log.info("******* About to validate Exit Click response ******* ..!");
		
		assertionContainsUsingSubstring("data.relationships.trackingid.data", data, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated Exit Click END POINT  response************");
		Log.info("************Successfully validated Exit Click END POINT  response************");

	}

	/* validateExitClickSuccessResponseUserEndPoint */
	public void validateExitClickSuccessResponseUserEndpoint(SoftAssert objSoftAssertion, Response response, String data) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate Exit Click response ******* ..!");
		Log.info("******* About to validate Exit Click response ******* ..!");
		
		assertionContainsUsingSubstring("data", data, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated Exit Click END POINT  response************");
		Log.info("************Successfully validated Exit Click END POINT  response************");

	}

	/* validateExitClickSuccessResponseUserEndPoint */
	public void validateExitClickSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String index, String type, String id, String name, String nwID, String orderIdFormat, String ticketAcceptHours, String related) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate Exit Click response ******* ..!");
		Log.info("******* About to validate Exit Click response ******* ..!");
		
		assertionContainsWithoutUsingSubstring("data.type["+index+"]", type, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.id["+index+"]", id, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.attributes.name["+index+"]", name, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.attributes.network_id["+index+"]", nwID, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.attributes.orderid_format["+index+"]", orderIdFormat, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.attributes.ticket_accept_in_hours["+index+"]", ticketAcceptHours, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.relationships.trackingid.links.related["+index+"]", related, objSoftAssertion, response);

		logger.log(LogStatus.PASS, "************Successfully validated Exit Click END POINT  response************");
		Log.info("************Successfully validated Exit Click END POINT  response************");

	}
	
	/* validateExitClickSuccessResponseUserEndPoint */
	public void validateExitClickSuccessResponseUserendpoint(SoftAssert objSoftAssertion, Response response, String index, String typeTrackingID, String exitID, String exitClickDate, String transactionamount, String curency, String earnedamount, String cashbackUniquecode, String cashbackstatus, String ticketId, String ticketstatus, String coupondetails, String hrefURL, String httpMethod) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate Exit Click response ******* ..!");
		Log.info("******* About to validate Exit Click response ******* ..!");

		assertionContainsUsingSubstring("data.relationships.trackingid.data.type["+index+"]", typeTrackingID, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.relationships.trackingid.data.id["+index+"]", exitID, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.relationships.trackingid.data.attributes.click_date["+index+"]", exitClickDate, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.relationships.trackingid.data.attributes.transaction_amount["+index+"]", transactionamount, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.relationships.trackingid.data.attributes.currency["+index+"]", curency, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.relationships.trackingid.data.attributes.earned_amount["+index+"]", earnedamount, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.relationships.trackingid.data.attributes.cashback_unique_code["+index+"]", cashbackUniquecode, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.relationships.trackingid.data.attributes.cashback_status["+index+"]", cashbackstatus, objSoftAssertion, response);
		assertionForNullValues("data.relationships.trackingid.data.attributes.ticket_id["+index+"]", ticketId, objSoftAssertion, response);
		assertionForNullValues("data.relationships.trackingid.data.attributes.ticket_status["+index+"]", ticketstatus, objSoftAssertion, response);
		assertionForNullValues("data.relationships.trackingid.data.attributes.coupon_details["+index+"]", coupondetails, objSoftAssertion, response);

		assertionContainsUsingSubstring("data.relationships.trackingid.data.relationships.ticket.links.related.href["+index+"]", hrefURL, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.relationships.trackingid.data.relationships.ticket.links.related.meta.method["+index+"]", httpMethod, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated Exit Click END POINT  response************");
		Log.info("************Successfully validated Exit Click END POINT  response************");

	}
	
	/* validateExitClickSuccessResponseUserEndPoint */
	public void validateExitClickSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String type, String id, String name, String nwID, String orderIdFormat, String ticketAcceptHours, String related) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate Exit Click response ******* ..!");
		Log.info("******* About to validate Exit Click response ******* ..!");
		
		assertionContainsWithoutUsingSubstring("data.type[0]", type, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.id[0]", id, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.attributes.name[0]", name, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.attributes.network_id[0]", nwID, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.attributes.orderid_format[0]", orderIdFormat, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.attributes.ticket_accept_in_hours[0]", ticketAcceptHours, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.relationships.trackingid.links.related[0]", related, objSoftAssertion, response);

		logger.log(LogStatus.PASS, "************Successfully validated Exit Click END POINT  response************");
		Log.info("************Successfully validated Exit Click END POINT  response************");

	}

	/* validateExitClickSuccessResponseUserEndPoint */
	public void validateExitClickSuccessResponseUserendpoint(SoftAssert objSoftAssertion, Response response, String typeTrackingID, String exitID, String exitClickDate, String transactionamount, String curency, String earnedamount, String cashbackUniquecode, String cashbackstatus, String ticketId, String ticketstatus, String coupondetails, String hrefURL, String httpMethod) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate Exit Click response ******* ..!");
		Log.info("******* About to validate Exit Click response ******* ..!");

		assertionContainsUsingSubstring("data.relationships.trackingid.data.type[0]", typeTrackingID, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.relationships.trackingid.data.id[0]", exitID, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.relationships.trackingid.data.attributes.click_date[0]", exitClickDate, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.relationships.trackingid.data.attributes.transaction_amount[0]", transactionamount, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.relationships.trackingid.data.attributes.currency[0]", curency, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.relationships.trackingid.data.attributes.earned_amount[0]", earnedamount, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.relationships.trackingid.data.attributes.cashback_unique_code[0]", cashbackUniquecode, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.relationships.trackingid.data.attributes.cashback_status[0]", cashbackstatus, objSoftAssertion, response);
		assertionForNullValues("data.relationships.trackingid.data.attributes.ticket_id[0]", ticketId, objSoftAssertion, response);
		assertionForNullValues("data.relationships.trackingid.data.attributes.ticket_status[0]", ticketstatus, objSoftAssertion, response);
		assertionForNullValues("data.relationships.trackingid.data.attributes.coupon_details[0]", coupondetails, objSoftAssertion, response);

		assertionContainsUsingSubstring("data.relationships.trackingid.data.relationships.ticket.links.related.href[0]", hrefURL, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.relationships.trackingid.data.relationships.ticket.links.related.meta.method[0]", httpMethod, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated Exit Click END POINT  response************");
		Log.info("************Successfully validated Exit Click END POINT  response************");

	}

	/* validateExitClickSuccessResponseUserEndPoint */
	public void validateExitClickSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String typeTrackingID, String exitID, String exitClickDate, String transactionamount, String curency, String earnedamount, String cashbackUniquecode, String cashbackstatus, String ticketId, String ticketstatus, String coupondetails, String hrefURL, String httpMethod) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate Exit Click response ******* ..!");
		Log.info("******* About to validate Exit Click response ******* ..!");

		assertionContainsUsingSubstring("data.relationships.trackingid.data.type[0]", typeTrackingID, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.relationships.trackingid.data.id[0]", exitID, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.relationships.trackingid.data.attributes.click_date[0]", exitClickDate, objSoftAssertion, response);
		assertionForNullValues("data.relationships.trackingid.data.attributes.transaction_amount[0]", transactionamount, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.relationships.trackingid.data.attributes.currency[0]", curency, objSoftAssertion, response);
		assertionForNullValues("data.relationships.trackingid.data.attributes.earned_amount[0]", earnedamount, objSoftAssertion, response);
		assertionForNullValues("data.relationships.trackingid.data.attributes.cashback_unique_code[0]", cashbackUniquecode, objSoftAssertion, response);
		assertionForNullValues("data.relationships.trackingid.data.attributes.cashback_status[0]", cashbackstatus, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.relationships.trackingid.data.attributes.ticket_id[0]", ticketId, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.relationships.trackingid.data.attributes.ticket_status[0]", ticketstatus, objSoftAssertion, response);
		assertionForNullValues("data.relationships.trackingid.data.attributes.coupon_details[0]", coupondetails, objSoftAssertion, response);

		assertionContainsUsingSubstring("data.relationships.trackingid.data.relationships.ticket.links.related.href[0]", hrefURL, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.relationships.trackingid.data.relationships.ticket.links.related.meta.method[0]", httpMethod, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated Exit Click END POINT  response************");
		Log.info("************Successfully validated Exit Click END POINT  response************");

	}

	/* validateExitClickSuccessResponseUserEndPoint */
	public void validateExitClickSuccessResponseuserEndPoint(SoftAssert objSoftAssertion, Response response, String index, String typeTrackingID, String exitID, String exitClickDate, String transactionamount, String curency, String earnedamount, String cashbackUniquecode, String cashbackstatus, String ticketId, String ticketstatus, String coupondetails, String hrefURL, String httpMethod) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate Exit Click response ******* ..!");
		Log.info("******* About to validate Exit Click response ******* ..!");

		assertionContainsWithoutUsingSubstring("data.type["+index+"]", typeTrackingID, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.id["+index+"]", exitID, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.attributes.click_date["+index+"]", exitClickDate, objSoftAssertion, response);
		assertionForNullValues("data.attributes.transaction_amount["+index+"]", transactionamount, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.attributes.currency["+index+"]", curency, objSoftAssertion, response);
		assertionForNullValues("data.attributes.earned_amount["+index+"]", earnedamount, objSoftAssertion, response);
		assertionForNullValues("data.attributes.cashback_unique_code["+index+"]", cashbackUniquecode, objSoftAssertion, response);
		assertionForNullValues("data.attributes.cashback_status["+index+"]", cashbackstatus, objSoftAssertion, response);
		assertionForNullValues("data.attributes.ticket_id["+index+"]", ticketId, objSoftAssertion, response);
		assertionForNullValues("data.attributes.ticket_status["+index+"]", ticketstatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.attributes.coupon_details["+index+"]", coupondetails, objSoftAssertion, response);

		assertionContainsWithoutUsingSubstring("data.relationships.ticket.links.related.href["+index+"]", hrefURL, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.relationships.ticket.links.related.meta.method["+index+"]", httpMethod, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated Exit Click END POINT  response************");
		Log.info("************Successfully validated Exit Click END POINT  response************");
		
	}

	/* validateExitClickSuccessResponseUserEndPoint */
	public void validateExitClickSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String index, String typeTrackingID, String exitID, String exitClickDate, String transactionamount, String curency, String earnedamount, String cashbackUniquecode, String cashbackstatus, String ticketId, String ticketstatus, String coupondetails, String hrefURL, String httpMethod) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate Exit Click response ******* ..!");
		Log.info("******* About to validate Exit Click response ******* ..!");

		assertionContainsWithoutUsingSubstring("data.type["+index+"]", typeTrackingID, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.id["+index+"]", exitID, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.attributes.click_date["+index+"]", exitClickDate, objSoftAssertion, response);
		assertionForNullValues("data.attributes.transaction_amount["+index+"]", transactionamount, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.attributes.currency["+index+"]", curency, objSoftAssertion, response);
		assertionForNullValues("data.attributes.earned_amount["+index+"]", earnedamount, objSoftAssertion, response);
		assertionForNullValues("data.attributes.cashback_unique_code["+index+"]", cashbackUniquecode, objSoftAssertion, response);
		assertionForNullValues("data.attributes.cashback_status["+index+"]", cashbackstatus, objSoftAssertion, response);
		assertionForNullValues("data.attributes.ticket_id["+index+"]", ticketId, objSoftAssertion, response);
		assertionForNullValues("data.attributes.ticket_status["+index+"]", ticketstatus, objSoftAssertion, response);
		assertionForNullValues("data.attributes.coupon_details["+index+"]", coupondetails, objSoftAssertion, response);

		assertionContainsWithoutUsingSubstring("data.relationships.ticket.links.related.href["+index+"]", hrefURL, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.relationships.ticket.links.related.meta.method["+index+"]", httpMethod, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated Exit Click END POINT  response************");
		Log.info("************Successfully validated Exit Click END POINT  response************");

	}

	/* validateExitClickSuccessResponseUserEndPoint */
	public void validateExitClickSuccessResponseUserEndpoint(SoftAssert objSoftAssertion, Response response, String index, String typeTrackingID, String exitID, String exitClickDate, String transactionamount, String curency, String earnedamount, String cashbackUniquecode, String cashbackstatus, String ticketId, String ticketstatus, String coupondetails, String hrefURL, String httpMethod) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate Exit Click response ******* ..!");
		Log.info("******* About to validate Exit Click response ******* ..!");

		assertionContainsUsingSubstring("data.type["+index+"]", typeTrackingID, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.id["+index+"]", exitID, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.attributes.click_date["+index+"]", exitClickDate, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.attributes.transaction_amount["+index+"]", transactionamount, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.attributes.currency["+index+"]", curency, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.attributes.earned_amount", earnedamount, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.attributes.cashback_unique_code["+index+"]", cashbackUniquecode, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.attributes.cashback_status["+index+"]", cashbackstatus, objSoftAssertion, response);
		assertionForNullValues("data.attributes.ticket_id["+index+"]", ticketId, objSoftAssertion, response);
		assertionForNullValues("data.attributes.ticket_status["+index+"]", ticketstatus, objSoftAssertion, response);
		assertionForNullValues("data.attributes.coupon_details["+index+"]", coupondetails, objSoftAssertion, response);

		assertionContainsUsingSubstring("data.relationships.ticket.links.related.href["+index+"]", hrefURL, objSoftAssertion, response);
		assertionContainsUsingSubstring("data.relationships.ticket.links.related.meta.method["+index+"]", httpMethod, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated Exit Click END POINT  response************");
		Log.info("************Successfully validated Exit Click END POINT  response************");

	}
	
	/* validateExitClickErrorResponseUserEndPoint */
	public void validateExitClickErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate Exit Click error response ******* ..!");
		Log.info("******* About to validate Exit Click error response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated Exit Click END POINT error response************");
		Log.info("************Successfully validated Exit Click END POINT error response************");

	}

	/* validateExitClickErrorResponseUserEndpoint */
	public void validateExitClickErrorResponseUserEndpoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate ExitClick error response ******* ..!");
		Log.info("******* About to validate ExitClick error response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(mesage, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated ExitClick END POINT error response************");
		Log.info("************Successfully validated ExitClick END POINT error response************");

	}

	/* validateExitClickErrorResponseUserEndpoint */
	public void validateExitClickErrorResponseUserEndpoint(SoftAssert objSoftAssertion, Response response, String errorLinks, String errorStatus, String errorTitle, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate ExitClick error response ******* ..!");
		Log.info("******* About to validate ExitClick error response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(links, errorLinks, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated ExitClick END POINT error response************");
		Log.info("************Successfully validated ExitClick END POINT error response************");

	}
}