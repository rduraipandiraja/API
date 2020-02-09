package com.ppa.api.endpoints.payments;

import java.awt.AWTException;

import com.admin.pages.AdminCommonFunctions;
import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.base.Log;
import com.ppa.api.utilities.Restutils;
import com.ppa.api.utilities.Utils;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

public class NEFTPost extends Restutils {

	/************************************************************** Constructor **********************************************************************/

	public NEFTPost(ExtentTest logger) {

		this.logger = logger;
	}
	
	/******************************************************** Object Creation started ****************************************************************/

	
	/******************************************************** Variable Creation started ***************************************************************/

	public static final String AUTHORIZATIONKEY;
	public static final String ACCEPTKEY;
	public static final String ACCEPTVALUE;
	public static final String CONTENTTYPEKEY;
	public static final String CONTENTTYPEVALUE;
	public static final String ENDPOINTPAYMENT;
	public static final String OTPFLAG;
	public static final String FRONTENDURL;

	/********************************************************** Gpath Creation started ***************************************************************/

	/******************************************************** Methods Creation started ****************************************************************/
	
	static {
		
		AUTHORIZATIONKEY	= Utils.getRestApiTestData(0, "auth_Key");
		ACCEPTKEY			= Utils.getRestApiTestData(0, "accept_key");
		ACCEPTVALUE			= Utils.getRestApiTestData(0, "accept_value");
		CONTENTTYPEKEY		= Utils.getRestApiTestData(0, "content_type_key");
		CONTENTTYPEVALUE	= Utils.getRestApiTestData(0, "content_type_value");
		ENDPOINTPAYMENT		= Utils.getRestApiTestData(2, "payment");
		OTPFLAG				= ConfigurationSetup.OTPFLAG;
		FRONTENDURL			= ConfigurationSetup.FRONTENDURL;
		
	}
	
	/* postDataForNEFT */
	public String postDataForNEFT(String type, String paymentType, String password, String paymentMethodID, String accountHolderName, String bankName, String branch, String accountNumber, String ifscCode) {

		logger.log(LogStatus.INFO, "About to post data for POST request");
		Log.info("About to post data for POST request");

		String postData =	"{\r\n" + 
							"  \"data\": {\r\n" + 
							"    \"type\": \""+type+"\",\r\n" + 
							"    \"attributes\": {\r\n" + 
							"      \"payment_type\": \""+paymentType+"\",\r\n" + 
							"      \"password\": \""+password+"\",\r\n" + 
							"      \"payment_method_id\": \""+paymentMethodID+"\",\r\n" + 
							"      \"account_holder_name\": \""+accountHolderName+"\",\r\n" + 
							"      \"bank_name\": \""+bankName+"\",\r\n" + 
							"      \"branch\": \""+branch+"\",\r\n" + 
							"      \"account_number\": \""+accountNumber+"\",\r\n" + 
							"      \"ifsc_code\": \""+ifscCode+"\"\r\n" + 
							"    }\r\n" + 
							"  }\r\n" + 
							"}";

		logger.log(LogStatus.PASS, "Post Data for POST request : " + postData.trim());
		Log.info("Post Data for POST request : " + postData.trim());
		
		return postData;
		
	}

	/* createCashout */
	public void createCashout(String email) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to create createCashout ..! *******");
		Log.info("******* About to create createCashout ..! *******");
		
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		objAdminCommonFunctions.createCashout(email);
		
		logger.log(LogStatus.PASS, "************ Created cashout ************");
		Log.info("************ Created cashout ************");
		
	}
	
	/* validateNEFTPostSucessResponsePaymentEndPoint */
	public Response validateNEFTPostSucessResponsePaymentEndPoint(String baseURL, String accessTokenValue, String type, String paymentType, String password, String paymentMethodID, String accountHolderName, String bankName, String branch, String accountNumber, String ifscCode, int statusCode) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to get neft post response after posting data  ..! *******");
		Log.info("******* About to get neft post response after posting data  ..! *******");

		// Assigning Values
		String authorizationValue	= accessTokenValue;
		String completedURL			= baseURL+ENDPOINTPAYMENT+ENDPOINTPAYMENT;
		String postData				= null;
		Response response			= null;

		postData = postDataForNEFT(type, paymentType, password, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode);
		response = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, ACCEPTVALUE, CONTENTTYPEKEY, CONTENTTYPEVALUE, postData, statusCode);

		logger.log(LogStatus.PASS, "************ Successfully created neft post response :  " + response.asString());
		Log.info("************ Successfully created neft post response :  " + response.asString());
		
		return response;

	}


}