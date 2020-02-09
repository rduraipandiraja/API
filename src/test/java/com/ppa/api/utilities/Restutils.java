package com.ppa.api.utilities;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.asserts.SoftAssert;

import com.ppa.api.base.Base;
import com.ppa.api.base.Log;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class Restutils extends Base {
	
	/************************************************************** Constructor ***********************************************************************/

	public Restutils(ExtentTest logger) {

		this.logger = logger;
	}
	
	public Restutils() {

	}

	/******************************************************** Methods Creation started ****************************************************************/

	/* validateStatusCode */
	public void validateStatusCode(Response response, int statusCode){

		logger.log(LogStatus.INFO, "About to validate status code in response: "+response.asString());
		Log.info("About to validate status code in response: "+response.asString());
		
		int statusCodeInResponse = response.getStatusCode();
		
		try {
			
			if (statusCodeInResponse == statusCode) {
				
				logger.log(LogStatus.PASS, "Status code in response: " +statusCodeInResponse);
				Log.info("Status code in response: " +statusCodeInResponse);
				
			} else {

				logger.log(LogStatus.FAIL, "Actual status code in response: " +statusCodeInResponse +" doesnot match expected reponse: "+statusCode);
				Log.info("Actual status code in response: " +statusCodeInResponse +" doesnot match expected reponse: "+statusCode);

			}
			
		} catch (Exception e) {

			logger.log(LogStatus.FAIL, "Actual status code in response: " +statusCodeInResponse +" doesnot match expected reponse: "+statusCode);
			Log.info("Actual status code in response: " +statusCodeInResponse +" doesnot match expected reponse: "+statusCode);
		}
		
		/*validateReponseContentType(response);*/

	}
	
	/* getRequestSpecification */
	public Response getRequestSpecification(String baseURL, int statusCode){
		
		logger.log(LogStatus.INFO, "BaseURL :  "+baseURL.trim());
		Log.info("BaseURL :  "+baseURL.trim());
		
		Response response = when().
								get(baseURL).
							then().
								extract().response();
		
		logger.log(LogStatus.PASS, "Response is : " +response.asString());
		Log.info("Response is : " +response.asString());
		
		this.validateStatusCode(response, statusCode);

		return response;
	}

	/* validate Reponse ContentType */
	public void validateReponseContentType(Response response){
		
		logger.log(LogStatus.INFO, "Response is : " +response.asString());
		Log.info("Response is : " +response.asString());
		
		String actualValue = null;
		String expectedValue = null;
		
		try {
			
			actualValue = response.header("Content-Type").toString();
			expectedValue = Utils.getRestApiTestData(0, "accept_value");

			if (actualValue.trim() == expectedValue.trim()) {
				
				logger.log(LogStatus.PASS, "Sucessfully validated response ContentType: " + actualValue);
				Log.info("Sucessfully validated response ContentType: " +actualValue);
				
			} else {
				
				logger.log(LogStatus.FAIL, "Failed to validate response ContentType: " + actualValue +"found ContentType: "+expectedValue);
				Log.info("Failed to validate response ContentType: " +actualValue +"found ContentType: "+expectedValue);

			}
			
		} catch (Exception e) {
			
			logger.log(LogStatus.FAIL, "Failed to validate response ContentType: " + actualValue +"found ContentType: "+expectedValue);
			Log.info("Failed to validate response ContentType: " +actualValue +"found ContentType: "+expectedValue);
		}

	}

	/* validate Reponse ContentType */
	public void validateReponseContentType(SoftAssert objSoftAssertion, Response response, String expectedValue){
		
		logger.log(LogStatus.INFO, "Response is : " +response.asString());
		Log.info("Response is : " +response.asString());
		
		String actualValue = null;
		
		try {
			
			actualValue = response.header("Content-Type").toString();
			objSoftAssertion.assertEquals(actualValue, expectedValue);
			
			logger.log(LogStatus.PASS, "Sucessfully validated response ContentType: " + actualValue);
			Log.info("Sucessfully validated response ContentType: " +actualValue);
			
		} catch (Exception e) {
			
			logger.log(LogStatus.FAIL, "Failed to validate response ContentType: " + actualValue);
			Log.info("Failed to validate response ContentType: " +actualValue);
		}

	}

	/* getRequestSpecification */
	public Response getRequestSpecification(String baseURL, String header1, String headerValue1, int statusCode){
		
		logger.log(LogStatus.INFO, "Header One - " +header1.trim() + " : " + headerValue1.trim());
		logger.log(LogStatus.INFO, "BaseURL : " +baseURL.trim());

		Log.info("Header One : " +header1.trim() + " : " + headerValue1.trim());
		Log.info("BaseURL : " +baseURL.trim());
		
		Response response = given().
								header(header1, headerValue1).
							when().
								get(baseURL).
							then().
								extract().response();

		logger.log(LogStatus.PASS, "Response is : " +response.asString());
		Log.info("Response is : " +response.asString());
		
		this.validateStatusCode(response, statusCode);

		return response;
	}

	/* getRequestSpecification */
	public Response getRequestSpecification(String baseURL, String header1, String headerValue1, String header2, String headerValue2, int statusCode){
		
		logger.log(LogStatus.INFO, "Header One - " +header1.trim() + " : " + headerValue1.trim());
		logger.log(LogStatus.INFO, "Header Two - " +header2.trim() + " : " + headerValue2.trim());
		logger.log(LogStatus.INFO, "BaseURL : " +baseURL.trim());

		Log.info("Header One - " +header1.trim() + " : " + headerValue1.trim());
		Log.info("Header Two - " +header2.trim() + " : " + headerValue2.trim());
		Log.info("BaseURL : " +baseURL.trim());
		
		Response response = given().
								header(header1, headerValue1).header(header2, headerValue2).
							when().
								get(baseURL).
							then().
								extract().response();

		logger.log(LogStatus.PASS, "Response is : " +response.asString());
		Log.info("Response is : " +response.asString());
		
		this.validateStatusCode(response, statusCode);

		return response;
	}

	/* getRequestSpecificationBasicAuthType */
	public  Response getRequestSpecificationBasicAuthType(String baseURL, String header1, String headerValue1, String userName, String password, int statusCode){
		
		logger.log(LogStatus.INFO, "Header One - " +header1.trim() + " : " + headerValue1.trim());
		logger.log(LogStatus.INFO, "BaseURL : " +baseURL.trim());
		logger.log(LogStatus.INFO, "Username :  " +userName.trim() + " and password : "+password.trim());

		Log.info("Header One - " +header1.trim() + " : " + headerValue1.trim());
		Log.info("BaseURL : " +baseURL.trim());
		Log.info("Username :  "+userName.trim() + " and password : "+password.trim());
		
		Response response = given().
								auth().basic(userName, password).header(header1, headerValue1).
							when().
								get(baseURL).
							then().
								extract().response();
		
		String tokenValue 	= response.path("data.attributes.token");
		
		logger.log(LogStatus.PASS, "Generated Token is : " + tokenValue);
		Log.info("Generated Token is : " + tokenValue);
		
		this.validateStatusCode(response, statusCode);
		
		return response;
	}
	
	/* getOTP */
	public Response getOTP(String param1, String paramValue1, String param2, String paramValue2, String endpoint, int statusCode){
		
		logger.log(LogStatus.INFO, "BaseURL :  "+ endpoint.trim());
		logger.log(LogStatus.INFO, "Qparam One - " +param1.trim() + " : " + paramValue1.trim());
		logger.log(LogStatus.INFO, "Qparam Two - " +param2.trim() + " : " + paramValue2.trim());

		Log.info("BaseURL :  "+endpoint.trim());
		Log.info("Qparam One : " +param1.trim() + " : " + paramValue1.trim());
		Log.info("Qparam Two : " +param2.trim() + " : " + paramValue2.trim());

		Response response = given().
								param(param1, paramValue1).
								param(param2 ,paramValue2).
							when().
								get(endpoint.trim()).
							then().
								extract().
								response();

		logger.log(LogStatus.PASS, "Response is : " +response.asString());
		Log.info("Response is : " +response.asString());

		return response;
	}

	/* addBonus */
	public Response addBonus(String param1, String paramValue1, String param2, String paramValue2, String param3, String paramValue3, String param4, String paramValue4, String endpoint, int statusCode){
		
		logger.log(LogStatus.INFO, "BaseURL :  "+ endpoint.trim());
		logger.log(LogStatus.INFO, "Qparam One - " +param1.trim() + " : " + paramValue1.trim());
		logger.log(LogStatus.INFO, "Qparam Two - " +param2.trim() + " : " + paramValue2.trim());
		logger.log(LogStatus.INFO, "Qparam Three - " +param3.trim() + " : " + paramValue3.trim());
		logger.log(LogStatus.INFO, "Qparam Three - " +param4.trim() + " : " + paramValue4.trim());

		Log.info("BaseURL :  "+endpoint.trim());
		Log.info("Qparam One : " +param1.trim() + " : " + paramValue1.trim());
		Log.info("Qparam Two : " +param2.trim() + " : " + paramValue2.trim());
		Log.info("Qparam Three : " +param3.trim() + " : " + paramValue3.trim());
		Log.info("Qparam Three : " +param4.trim() + " : " + paramValue4.trim());

		Response response = given().
								param(param1, paramValue1).
								param(param2 ,paramValue2).
								param(param3 ,paramValue3).
								param(param4 ,paramValue4).
							when().
								get(endpoint.trim()).
							then().
								extract().
								response();

		logger.log(LogStatus.PASS, "Response is : " +response.asString());
		Log.info("Response is : " +response.asString());

		return response;
	}

	/* exitClickBackDate */
	public Response exitClickBackDate(String param1, String paramValue1, String param2, String paramValue2, String param3, String paramValue3, String endpoint, int statusCode){
		
		logger.log(LogStatus.INFO, "BaseURL :  "+ endpoint.trim());
		logger.log(LogStatus.INFO, "Qparam One - " +param1.trim() + " : " + paramValue1.trim());
		logger.log(LogStatus.INFO, "Qparam Two - " +param2.trim() + " : " + paramValue2.trim());
		logger.log(LogStatus.INFO, "Qparam Three - " +param3.trim() + " : " + paramValue3.trim());

		Log.info("BaseURL :  "+endpoint.trim());
		Log.info("Qparam One : " +param1.trim() + " : " + paramValue1.trim());
		Log.info("Qparam Two : " +param2.trim() + " : " + paramValue2.trim());
		Log.info("Qparam Three : " +param3.trim() + " : " + paramValue3.trim());

		Response response = given().
								param(param1, paramValue1).
								param(param2 ,paramValue2).
								param(param3 ,paramValue3).
							when().
								get(endpoint.trim()).
							then().
								extract().
								response();

		logger.log(LogStatus.PASS, "Response is : " +response.asString());
		Log.info("Response is : " +response.asString());

		return response;
	}

	/* postRequestSpecification */
	public Response postRequestSpecification(String baseURL, int statusCode){

		// Assigning header & URL Values 
		logger.log(LogStatus.INFO, "BaseURL : " +baseURL.trim());
		Log.info("BaseURL : " +baseURL.trim());

		// Status Validation for the POST request
		Response response = when().
								post(baseURL).
							then().
								extract().response();

		logger.log(LogStatus.PASS, "Response is : " +response.asString());
		Log.info("Response is : " +response.asString());
		
		this.validateStatusCode(response, statusCode);
		
		return response;
	}

	/* postRequestSpecification */
	public Response postRequestSpecification(String baseURL, String queryParameter1, String queryParameterValue1, int statusCode){

		// Assigning header & URL Values 
		logger.log(LogStatus.INFO, "BaseURL : " +baseURL.trim());
		Log.info("BaseURL : " +baseURL.trim());

		// Status Validation for the POST request
		Response response = given().
								queryParam(queryParameter1, queryParameterValue1.trim()). 
							when().
								post(baseURL).
							then().
								extract().response();

		logger.log(LogStatus.PASS, "Response is : " +response.asString());
		Log.info("Response is : " +response.asString());
		
		this.validateStatusCode(response, statusCode);
		
		return response;
	}

	/* postRequestSpecification */
	public Response postRequestSpecification(String baseURL, String header1, String headerValue1, String header2, String headerValue2 ,String header3, String headerValue3, String postData, int statusCode){

		// Assigning header & URL Values 
		logger.log(LogStatus.INFO, "Header One  - " +header1.trim() + " : " + headerValue1.trim());
		logger.log(LogStatus.INFO, "Header Two  - " +header2.trim() + " : " + headerValue2.trim());
		logger.log(LogStatus.INFO, "Header Three  - " +header3.trim() + " : " + headerValue3.trim());
		logger.log(LogStatus.INFO, "BaseURL : " +baseURL.trim());

		Log.info("Header One  : " +header1.trim() + " : " + headerValue1.trim());
		Log.info("Header Two  : " +header2.trim() + " : " + headerValue2.trim());
		Log.info("Header Three  : " +header3.trim() + " : " + headerValue3.trim());
		Log.info("BaseURL : " +baseURL.trim());

		logger.log(LogStatus.INFO, "Post Data : " + postData.trim());

		// Setting up multiple header values into Headers Class object
		final Header headerOne = new Header(header1.trim(), headerValue1.trim());
		final Header headerTwo = new Header(header2.trim(), headerValue2.trim());
		final Header headerThree = new Header(header3.trim(), headerValue3.trim());
		Headers headers = new Headers(headerOne, headerTwo, headerThree);

		// Status Validation for the POST request
		Response response = given().
								config(RestAssured.config().encoderConfig(io.restassured.config.EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
								headers(headers).
								body(postData.trim()).
							when().
								post(baseURL).
							then().
								extract().
								response();

		logger.log(LogStatus.PASS, "Response is : " +response.asString());
		Log.info("Response is : " +response.asString());
		
		this.validateStatusCode(response, statusCode);
		
		return response;
	}
	
	/* postRequestSpecification */
	public Response postRequestSpecification(String baseURL, String header1, String headerValue1, String header2, String headerValue2 ,String header3, String headerValue3, int statusCode){

		// Assigning header & URL Values 
		logger.log(LogStatus.INFO, "Header One  - " +header1.trim() + " : " + headerValue1.trim());
		logger.log(LogStatus.INFO, "Header Two  - " +header2.trim() + " : " + headerValue2.trim());
		logger.log(LogStatus.INFO, "Header Three  - " +header3.trim() + " : " + headerValue3.trim());
		logger.log(LogStatus.INFO, "BaseURL : " +baseURL.trim());

		Log.info("Header One  : " +header1.trim() + " : " + headerValue1.trim());
		Log.info("Header Two  : " +header2.trim() + " : " + headerValue2.trim());
		Log.info("Header Three  : " +header3.trim() + " : " + headerValue3.trim());
		Log.info("BaseURL : " +baseURL.trim());

		// Setting up multiple header values into Headers Class object
		final Header headerOne = new Header(header1.trim(), headerValue1.trim());
		final Header headerTwo = new Header(header2.trim(), headerValue2.trim());
		final Header headerThree = new Header(header3.trim(), headerValue3.trim());
		Headers headers = new Headers(headerOne, headerTwo, headerThree);

		// Status Validation for the POST request
		Response response = given().
								config(RestAssured.config().encoderConfig(io.restassured.config.EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
								headers(headers).
							when().
								post(baseURL).
							then().
								extract().
								response();

		logger.log(LogStatus.PASS, "Response is : " +response.asString());
		Log.info("Response is : " +response.asString());
		
		this.validateStatusCode(response, statusCode);
		
		return response;
	}

	/* postRequestSpecification */
	public Response postRequestSpecificationBasicAuth(String baseURL, String header1, String headerValue1, String header2, String headerValue2, String userName, String password, String postData, int statusCode){

		// Assigning header & URL Values 
		logger.log(LogStatus.INFO, "Header One  - " +header1.trim() + " : " + headerValue1.trim());
		logger.log(LogStatus.INFO, "Header Two  - " +header2.trim() + " : " + headerValue2.trim());
		logger.log(LogStatus.INFO, "BaseURL : " +baseURL.trim());

		Log.info("Header One  : " +header1.trim() + " : " + headerValue1.trim());
		Log.info("Header Two  : " +header2.trim() + " : " + headerValue2.trim());
		Log.info("BaseURL : " +baseURL.trim());

		// Setting up multiple header values into Headers Class object
		final Header headerOne = new Header(header1.trim(), headerValue1.trim());
		final Header headerTwo = new Header(header2.trim(), headerValue2.trim());
		Headers headers = new Headers(headerOne, headerTwo);

		// Status Validation for the POST request
		Response response = given().
								auth().
								basic(userName, password).
								config(RestAssured.config().encoderConfig(io.restassured.config.EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
								headers(headers).
								body(postData.trim()).
							when().
								post(baseURL).
							then().
								extract().
								response();

		logger.log(LogStatus.PASS, "Response is : " +response.asString());
		Log.info("Response is : " +response.asString());
		
		this.validateStatusCode(response, statusCode);
		
		return response;
	}

	/* postRequestSpecification */
	public Response postRequestSpecificationBasicAuth(String baseURL, String header1, String headerValue1, String header2, String headerValue2, String userName, String password, int statusCode){

		// Assigning header & URL Values 
		logger.log(LogStatus.INFO, "Header One  - " +header1.trim() + " : " + headerValue1.trim());
		logger.log(LogStatus.INFO, "Header Two  - " +header2.trim() + " : " + headerValue2.trim());
		logger.log(LogStatus.INFO, "BaseURL : " +baseURL.trim());

		Log.info("Header One  : " +header1.trim() + " : " + headerValue1.trim());
		Log.info("Header Two  : " +header2.trim() + " : " + headerValue2.trim());
		Log.info("BaseURL : " +baseURL.trim());

		// Setting up multiple header values into Headers Class object
		final Header headerOne = new Header(header1.trim(), headerValue1.trim());
		final Header headerTwo = new Header(header2.trim(), headerValue2.trim());
		Headers headers = new Headers(headerOne, headerTwo);

		// Status Validation for the POST request
		Response response = given().
								auth().
								basic(userName, password).
								config(RestAssured.config().encoderConfig(io.restassured.config.EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
								headers(headers).
							when().
								post(baseURL).
							then().
								extract().
								response();

		logger.log(LogStatus.PASS, "Response is : " +response.asString());
		Log.info("Response is : " +response.asString());
		
		this.validateStatusCode(response, statusCode);
		
		return response;
	}

	/* postRequestSpecificationQueryParameters */
	public Response postRequestSpecificationQueryParameters(String baseURL, String header1, String headerValue1, String transactionID, String totalAmountPaid, String couponCodeUsed, String transactionDetails,int statusCode){

		// Assigning header & URL Values 
		logger.log(LogStatus.INFO, "Header One  - " +header1.trim() + " : " + headerValue1.trim());
		logger.log(LogStatus.INFO, "BaseURL : " +baseURL.trim());
		logger.log(LogStatus.INFO, "transaction_id : " +transactionID.trim());
		logger.log(LogStatus.INFO, "total_amount_paid : " +totalAmountPaid.trim());
		logger.log(LogStatus.INFO, "coupon_code_used : " +couponCodeUsed.trim());
		logger.log(LogStatus.INFO, "transaction_details : " +transactionDetails.trim());

		Log.info("Header One  : " +header1.trim() + " : " + headerValue1.trim());
		Log.info("BaseURL : " +baseURL.trim());
		Log.info("transaction_id : " +transactionID.trim());
		Log.info("total_amount_paid : " +totalAmountPaid.trim());
		Log.info("coupon_code_used : " +couponCodeUsed.trim());
		Log.info("transaction_details : " +transactionDetails.trim());

		// Status Validation for the POST request
		Response response = given().
								header(header1.trim(), headerValue1.trim()).
								queryParam("transaction_id", transactionID.trim()).
								queryParam("total_amount_paid", totalAmountPaid.trim()).
								queryParam("coupon_code_used", couponCodeUsed.trim()).
								queryParam("transaction_details", transactionDetails.trim()).
							when().
								post(baseURL).
							then().
								extract().
								response();

		logger.log(LogStatus.PASS, "Response is : " +response.asString());
		Log.info("Response is : " +response.asString());
		
		this.validateStatusCode(response, statusCode);
		
		return response;
	}
	
	/* postRequestSpecificationQueryParameters */
	public Response postRequestSpecificationQueryParameters(String baseURL, String header1, String headerValue1, String header2, String headerValue2, String transactionID, String totalAmountPaid, String couponCodeUsed, String transactionDetails, String fileName, int statusCode){

		// Assigning header & URL Values 
		logger.log(LogStatus.INFO, "Header One  - " +header1.trim() + " : " + headerValue1.trim());
		logger.log(LogStatus.INFO, "Header Two  - " +header2.trim() + " : " + headerValue2.trim());
		logger.log(LogStatus.INFO, "BaseURL : " +baseURL.trim());
		logger.log(LogStatus.INFO, "transaction_id : " +transactionID.trim());
		logger.log(LogStatus.INFO, "total_amount_paid : " +totalAmountPaid.trim());
		logger.log(LogStatus.INFO, "coupon_code_used : " +couponCodeUsed.trim());
		logger.log(LogStatus.INFO, "transaction_details : " +transactionDetails.trim());
		logger.log(LogStatus.INFO, "ticket_attachment : " +new File(System.getProperty("user.dir")+ "/files/missingcbticket/"+fileName.trim()));

		Log.info("Header One  : " +header1.trim() + " : " + headerValue1.trim());
		Log.info("Header Two  : " +header2.trim() + " : " + headerValue2.trim());
		Log.info("BaseURL : " +baseURL.trim());
		Log.info("transaction_id : " +transactionID.trim());
		Log.info("total_amount_paid : " +totalAmountPaid.trim());
		Log.info("coupon_code_used : " +couponCodeUsed.trim());
		Log.info("transaction_details : " +transactionDetails.trim());
		Log.info("ticket_attachment : " +new File(System.getProperty("user.dir")+ "/files/missingcbticket/"+fileName.trim()));

		// Status Validation for the POST request
		Response response = given().
								header(header1.trim(), headerValue1.trim()).
								header(header2.trim(), headerValue2.trim()).
								multiPart("transaction_id", transactionID.trim()).
								multiPart("total_amount_paid", totalAmountPaid.trim()).
								multiPart("coupon_code_used", couponCodeUsed.trim()).
								multiPart("transaction_details", transactionDetails.trim()).
								multiPart("ticket_attachment", new File(System.getProperty("user.dir")+ "/files/missingcbticket/"+fileName.trim())).
							when().
								post(baseURL).
							then().
								extract().
								response();

		logger.log(LogStatus.PASS, "Response is : " +response.asString());
		Log.info("Response is : " +response.asString());
		
		this.validateStatusCode(response, statusCode);
		
		return response;
	}

	/* putRequestSpecification */
	public Response putRequestSpecification(String baseURL, String header1, String headerValue1, String header2, String headerValue2 ,String header3, String headerValue3, String putData, int statusCode){

		// Assigning header & URL Values 
		logger.log(LogStatus.INFO, "Header One  : " +header1.trim() + " : " + headerValue1.trim());
		logger.log(LogStatus.INFO, "Header Two  : " +header2.trim() + " : " + headerValue2.trim());
		logger.log(LogStatus.INFO, "Header Three  : " +header3.trim() + " : " + headerValue3.trim());
		logger.log(LogStatus.INFO, "BaseURL : " +baseURL.trim());

		Log.info("Header One  : " +header1.trim() + " : " + headerValue1.trim());
		Log.info("Header Two  : " +header2.trim() + " : " + headerValue2.trim());
		Log.info("Header Three  : " +header3.trim() + " : " + headerValue3.trim());
		Log.info("BaseURL : " +baseURL.trim());

		logger.log(LogStatus.INFO, "PUT data: " + putData.trim());

		// Setting up multiple header values into Headers Class object
		final Header headerOne = new Header(header1.trim(), headerValue1.trim());
		final Header headerTwo = new Header(header2.trim(), headerValue2.trim());
		final Header headerThree = new Header(header3.trim(), headerValue3.trim());
		Headers headers = new Headers(headerOne, headerTwo, headerThree);

		// Status Validation for the POST request
		Response response = given().
								config(RestAssured.config().encoderConfig(io.restassured.config.EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
								headers(headers).
								body(putData.trim()).
							when().
								put(baseURL).
							then().
								extract().
								response();

		logger.log(LogStatus.PASS, "Response is : " +response.asString());
		Log.info("Response is : " +response.asString());
		
		this.validateStatusCode(response, statusCode);
		
		return response;
	}
	
	/* putRequestSpecification */
	public Response putRequestSpecification(String baseURL, String header1, String headerValue1, String header2, String headerValue2 ,String header3, String headerValue3, int statusCode){

		// Assigning header & URL Values 
		logger.log(LogStatus.INFO, "Header One  : " +header1.trim() + " : " + headerValue1.trim());
		logger.log(LogStatus.INFO, "Header Two  : " +header2.trim() + " : " + headerValue2.trim());
		logger.log(LogStatus.INFO, "Header Three  : " +header3.trim() + " : " + headerValue3.trim());
		logger.log(LogStatus.INFO, "BaseURL : " +baseURL.trim());

		Log.info("Header One  : " +header1.trim() + " : " + headerValue1.trim());
		Log.info("Header Two  : " +header2.trim() + " : " + headerValue2.trim());
		Log.info("Header Three  : " +header3.trim() + " : " + headerValue3.trim());
		Log.info("BaseURL : " +baseURL.trim());

		// Setting up multiple header values into Headers Class object
		final Header headerOne = new Header(header1.trim(), headerValue1.trim());
		final Header headerTwo = new Header(header2.trim(), headerValue2.trim());
		final Header headerThree = new Header(header3.trim(), headerValue3.trim());
		Headers headers = new Headers(headerOne, headerTwo, headerThree);

		// Status Validation for the POST request
		Response response = given().
								config(RestAssured.config().encoderConfig(io.restassured.config.EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
								headers(headers).
							when().
								put(baseURL).
							then().
								extract().
								response();

		logger.log(LogStatus.PASS, "Response is : " +response.asString());
		Log.info("Response is : " +response.asString());
		
		this.validateStatusCode(response, statusCode);
		
		return response;
	}

	/* putRequestSpecification */
	public Response putRequestSpecification(String baseURL, int statusCode){

		// Assigning header & URL Values 
		logger.log(LogStatus.INFO, "BaseURL : " +baseURL.trim());
		Log.info("BaseURL : " +baseURL.trim());


		// Status Validation for the POST request
		Response response = given().
								config(RestAssured.config().encoderConfig(io.restassured.config.EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
							when().
								put(baseURL).
							then().
								extract().response();

		logger.log(LogStatus.PASS, "Response is : " +response.asString());
		Log.info("Response is : " +response.asString());
		
		this.validateStatusCode(response, statusCode);
		
		return response;
	}

	/* assertionContainsUsingSubstring */
	public void assertionContainsUsingSubstring(String gpath, String expectedString ,SoftAssert objSoftAssertion , Response response){
		
		String actualString  	=  response.path(gpath).toString().substring(1, response.path(gpath).toString().length() - 1);

		logger.log(LogStatus.INFO, "About to do assertions for actual value: "+ actualString.trim() + " and expected value  :   " + expectedString.trim());
		Log.info("About to do assertions for actual value: "+ actualString.trim() + " and expected value  :   " + expectedString.trim());
		
		objSoftAssertion.assertTrue(actualString.trim().contains(expectedString.trim()),"Expected Assertions Value is : " + expectedString.trim() + " : but Found : " + actualString.trim() + " : & In Boolean values : ");

		/*response.then().body(gpath, contains(expectedString));*/

		logger.log(LogStatus.PASS, "Assertions done for actual value : "+ actualString.trim() + " and expected value  :   " + expectedString.trim());
		Log.info("Assertions done for actual value : "+ actualString.trim() + " and expected value  :   " + expectedString.trim());

	}

	/* assertionContainsWithoutUsingSubstring */
	public void assertionContainsWithoutUsingSubstring(String gpath, String expectedString ,SoftAssert objSoftAssertion , Response response){
		
		String actualString  	=  response.path(gpath).toString();

		logger.log(LogStatus.INFO, "About to do assertions for actual value: "+ actualString.trim() + " and expected value  :   " + expectedString.trim());
		Log.info("About to do assertions for actual value: "+ actualString.trim() + " and expected value  :   " + expectedString.trim());
		
		objSoftAssertion.assertTrue(actualString.trim().contains(expectedString.trim()),"Expected Assertions Value is : " + expectedString.trim() + " : but Found : " + actualString.trim() + " : & In Boolean values : ");
		
		/*response.then().body(gpath, contains(expectedString));*/
		
		logger.log(LogStatus.PASS, "Assertions done for actual value : "+ actualString.trim() + " and expected value  :   " + expectedString.trim());
		Log.info("Assertions done for actual value : "+ actualString.trim() + " and expected value  :   " + expectedString.trim());

	}

	/* assertionForNullValues */
	public void assertionForNullValues(String gpath, String expectedString ,SoftAssert objSoftAssertion , Response response){
		
		String actualString = null;
		
		try {

			actualString  	=  response.path(gpath).toString();
			
			objSoftAssertion.assertTrue(actualString.trim().contains(expectedString.trim()),"Expected Assertions Value is : " + expectedString.trim() + " : but Found : " + actualString.trim() + " : & In Boolean values : ");

			logger.log(LogStatus.PASS, "About to do assertions for actual value: "+ actualString.trim() + " and expected value  :   " + expectedString.trim());
			Log.info("About to do assertions for actual value: "+ actualString.trim() + " and expected value  :   " + expectedString.trim());
			
			
		} catch (NullPointerException e) {

			logger.log(LogStatus.PASS, "Assertions done for actual value : null and expected value  :   " + expectedString.trim());
			Log.info("Assertions done for actual value : null and expected value  :   " + expectedString.trim());
			
		}
		
	}

}