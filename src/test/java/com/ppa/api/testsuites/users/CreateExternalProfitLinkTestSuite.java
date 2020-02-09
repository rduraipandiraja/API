package com.ppa.api.testsuites.users;

import java.awt.AWTException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.admin.pages.AdminCommonFunctions;
import com.admin.pages.AdminCoreFunction;
import com.admin.pages.Login;
import com.admin.pages.NetworkStoreDomainMapping;
import com.ppa.api.base.Base;
import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.base.MakeLinkUrls;
import com.ppa.api.endpoints.helper.HelperAccessToken;
import com.ppa.api.endpoints.helper.SignUpOTP;
import com.ppa.api.endpoints.helper.SignUpV1WithOTP;
import com.ppa.api.endpoints.users.CreateExternalProfitLink;
import com.ppa.api.utilities.Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CreateExternalProfitLinkTestSuite extends Base {

	/**
	 * 
	 * Updating shorten domain URL in nw domain mapping admin panel 
	 * Shorten domain URL: bit.do,bit.ly,tiny.cc,rebrand.ly,j.mp,amzn.to,clnk.in,tinyurl.com,urlzs.com,fkrt.it
	 *	
	 */	
	@Test(priority=0, description="Updating shorten domain URL in nw domain mapping admin panel")	
	public void configurationShortenURLToNetworkStoreDomain() throws InterruptedException, AWTException{

		try {

			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			Login objLogin = new Login(driver, logger); 
			NetworkStoreDomainMapping objNetworkStoreDomainMapping = new NetworkStoreDomainMapping(driver, logger);
			
			/******************************************************************** VARIABLE CREATION ******************************************************************************/

			String adminURL	= Utils.getConfigurationSetupTestData(0, "admin_url");
			String adminDropDown = Utils.getConfigurationSetupTestData(0, "dropDown");
			String adminUsername = Utils.getConfigurationSetupTestData(0, "admin_username");
			String adminPassword = Utils.getConfigurationSetupTestData(0, "admin_password");
			String shortDomainUrls = Utils.getRestApiTestData(42, "shorten_domain_urls");
			
			/*********************************************************************** LOGIN PAGE*********************************************************************************/

			// Redirecting to admin panel
			Utils.loadURL(driver, adminURL);
			
			Thread.sleep(1000);
			
			objLogin.selectValueAdminDropdown(driver, adminDropDown);
			objLogin.enterUserName(driver, adminUsername);
			objLogin.enterPassword(driver, adminPassword);
			objLogin.clickLogin(driver);

			objNetworkStoreDomainMapping.clickOnNetworkDomainMapping();
			objNetworkStoreDomainMapping.clickOnToolsButton();
			objNetworkStoreDomainMapping.clickOnUpdateShortenURLDomain();
			objNetworkStoreDomainMapping.enterShortenURL(shortDomainUrls);
			objNetworkStoreDomainMapping.clickOnSubmit();
			objNetworkStoreDomainMapping.validateShortenURLSuccessMessage();

		} catch (Exception e) {

			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			NetworkStoreDomainMapping objNetworkStoreDomainMapping = new NetworkStoreDomainMapping(driver, logger);
			
			/******************************************************************** VARIABLE CREATION ******************************************************************************/

			String shortDomainUrls = Utils.getRestApiTestData(42, "shorten_domain_urls");
			
			/*********************************************************************** LOGIN PAGE*********************************************************************************/

			objNetworkStoreDomainMapping.clickOnNetworkDomainMapping();
			objNetworkStoreDomainMapping.clickOnToolsButton();
			objNetworkStoreDomainMapping.clickOnUpdateShortenURLDomain();
			objNetworkStoreDomainMapping.enterShortenURL(shortDomainUrls);
			objNetworkStoreDomainMapping.clickOnSubmit();
			objNetworkStoreDomainMapping.validateShortenURLSuccessMessage();


			

		}

	}

	/**
	 * 
	 * Updating amazon product cashback URL in affiliate nw admin panel
	 * Product URL: {PRDURL}&tag={AFFLTID}&ascsubtag={EXTID}{(ihre_partner_id)AFFLTID}
	 *
	 */
	@Test(priority=1, description="Updating amazon product cashback URL in affiliate nw admin panel")	
	public void configurationAmazonNewCashbackURL() throws InterruptedException, AWTException{

		try {

			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			Login objLogin = new Login(driver, logger); 
			AdminCoreFunction objAdminCoreFunction = new AdminCoreFunction(driver, logger);
			
			/******************************************************************** VARIABLE CREATION ******************************************************************************/

			String adminURL	= Utils.getConfigurationSetupTestData(0, "admin_url");
			String adminDropDown = Utils.getConfigurationSetupTestData(0, "dropDown");
			String adminUsername = Utils.getConfigurationSetupTestData(0, "admin_username");
			String adminPassword = Utils.getConfigurationSetupTestData(0, "admin_password");
			String storeNameAmazon = Utils.getRestApiTestData(42, "AmazonNew");
			
			/*********************************************************************** LOGIN PAGE*********************************************************************************/

			// Redirecting to admin panel
			Utils.loadURL(driver, adminURL);
			
			Thread.sleep(500);
			
			objLogin.selectValueAdminDropdown(driver, adminDropDown);
			objLogin.enterUserName(driver, adminUsername);
			objLogin.enterPassword(driver, adminPassword);
			objLogin.clickLogin(driver);

			objAdminCoreFunction.clickOnSettings();
			objAdminCoreFunction.clickOnAffiliateNetworks();
			Thread.sleep(500);
			objAdminCoreFunction.editTheAffiliateNetwork(storeNameAmazon);
			Thread.sleep(500);
			objAdminCoreFunction.enterProductCashbackURL(MakeLinkUrls.AMAZON_NEW_PRODUCT_CASHABACK_URL);
			objAdminCoreFunction.clickOnSubmitButton();
			objAdminCoreFunction.successMessageValidation();

		} catch (Exception e) {



			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			AdminCoreFunction objAdminCoreFunction = new AdminCoreFunction(driver, logger);
			
			/******************************************************************** VARIABLE CREATION ******************************************************************************/

			String storeNameAmazon = Utils.getRestApiTestData(42, "AmazonNew");
			
			/*********************************************************************** LOGIN PAGE*********************************************************************************/

			objAdminCoreFunction.clickOnSettings();
			objAdminCoreFunction.clickOnAffiliateNetworks();
			Thread.sleep(500);
			objAdminCoreFunction.editTheAffiliateNetwork(storeNameAmazon);
			Thread.sleep(500);
			objAdminCoreFunction.enterProductCashbackURL(MakeLinkUrls.AMAZON_NEW_PRODUCT_CASHABACK_URL);
			objAdminCoreFunction.clickOnSubmitButton();
			objAdminCoreFunction.successMessageValidation();


			

		}

	}
	
	/**
	 * 
	 * Updating flipkart product cashback URL in affiliate nw admin panel
	 * Product URL: {PRDURL}&affid=rohanpouri&affExtParam1={EXTID}&aid={AFFLTID}
	 *
	 */
	@Test(priority=2, description="Updating flipkart product cashback URL in affiliate nw admin panel")	
	public void configurationFlipKartNewCashbackURL() throws InterruptedException, AWTException{

		try {

			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			Login objLogin = new Login(driver, logger); 
			AdminCoreFunction objAdminCoreFunction = new AdminCoreFunction(driver, logger);
			
			/******************************************************************** VARIABLE CREATION ******************************************************************************/

			String adminURL	= Utils.getConfigurationSetupTestData(0, "admin_url");
			String adminDropDown = Utils.getConfigurationSetupTestData(0, "dropDown");
			String adminUsername = Utils.getConfigurationSetupTestData(0, "admin_username");
			String adminPassword = Utils.getConfigurationSetupTestData(0, "admin_password");
			String storeNameFlipkart = Utils.getRestApiTestData(42, "FlipKart");
			
			/*********************************************************************** LOGIN PAGE*********************************************************************************/

			// Redirecting to admin panel
			Utils.loadURL(driver, adminURL);
			
			Thread.sleep(500);
			
			objLogin.selectValueAdminDropdown(driver, adminDropDown);
			objLogin.enterUserName(driver, adminUsername);
			objLogin.enterPassword(driver, adminPassword);
			objLogin.clickLogin(driver);

			objAdminCoreFunction.clickOnSettings();
			objAdminCoreFunction.clickOnAffiliateNetworks();
			Thread.sleep(500);
			objAdminCoreFunction.editTheAffiliateNetwork(storeNameFlipkart);
			Thread.sleep(500);
			objAdminCoreFunction.enterProductCashbackURL(MakeLinkUrls.FLIPKART_PRODUCT_CASHBACK_URL);
			objAdminCoreFunction.clickOnSubmitButton();
			objAdminCoreFunction.successMessageValidation();

		} catch (Exception e) {



			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			AdminCoreFunction objAdminCoreFunction = new AdminCoreFunction(driver, logger);
			
			/******************************************************************** VARIABLE CREATION ******************************************************************************/

			String storeNameFlipkart = Utils.getRestApiTestData(42, "FlipKart");
			
			/*********************************************************************** LOGIN PAGE*********************************************************************************/

			objAdminCoreFunction.clickOnSettings();
			objAdminCoreFunction.clickOnAffiliateNetworks();
			Thread.sleep(500);
			objAdminCoreFunction.editTheAffiliateNetwork(storeNameFlipkart);
			Thread.sleep(500);
			objAdminCoreFunction.enterProductCashbackURL(MakeLinkUrls.FLIPKART_PRODUCT_CASHBACK_URL);
			objAdminCoreFunction.clickOnSubmitButton();
			objAdminCoreFunction.successMessageValidation();

		}

	}

	/**
	 * 
	 * Passing amazon product link URL without tags in post data and validating 200 status code with its response
	 * Amazon product URL: https://www.amazon.in/gp/product/B00KAUFMQC/ref=s9_acsd_al_bw_c_x_5_w?pf_rd_m=A1K21FY43GMZF8&pf_rd_s=merchandised-search-5&pf_rd_r=M7HJTAWSWTRT4EGVC8CX&pf_rd_t=101&pf_rd_p=d16aaf23-0405-4b0c-83b2-c37c0c9137ad&pf_rd_i=6802110031
	 *
	 */
	@Test(priority=3, description = "Passing amazon product link URL without tags in post data and validating 200 status code with its response")
	public void validateExternalProfitLinkResponseAfterPassingAmazonProductLinkWithoutTagsCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
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
		String link						= MakeLinkUrls.VALIDPRODUCTURL;
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeEarnLink				= Utils.getRestApiTestData(43, "type_earn_link");
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);
		
		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode200);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkSuccessResponseHelperEndPoint(objSoftAssertion, createShareLinkResponse, typeEarnLink);

		String shortLinkURL = objCreateExternalProfitLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateExternalProfitLink.waitingTillRequiredURLVisible(driver, "amazon");
		
		String sellerURL = Utils.getCurrentURL(driver);
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objCreateExternalProfitLink.assertProfitLinkURLWithSellerLandedURL(sellerURL, MakeLinkUrls.VALIDPRODUCTURL_SELLER + exitClick);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing invalid ipaddress in post data and validating 422 status code with its response
	 * ipaddress: 10.10.10.101010
	 *
	 */
	@Test(priority=4, description="Passing invalid ipaddress in post data and validating 422 status code with its response")
	public void validateExternalProfitLinkResponseAfterPassingInvalidIPAddressCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
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
		String link						= Utils.getRestApiTestData(8, "emptyValue");
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "ip_detail_Valid");
		String errorPointer				= Utils.getRestApiTestData(10, "ip_pointer");
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);

		ipAddress = Utils.getRestApiTestData(10, "invalid_ipaddress");
		
		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode422);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndPoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing non working domain URL in post data and validating 400 status code with its response
	 * Non working domain URL: https://www.google.com, https://www.amazon.comm, https://www.flipkart.co, https://www.cashkaro.com, https://www.myntraa.com, http://fkrt.ito/3IH~b2NNNN, https://amzn.tio/2FphiBB, https://www.45amazon.in & https://amazon.org
	 *
	 */
	@Test(priority=5, description="Passing non working domain URL in post data and validating 400 status code with its response")
	public void validateExternalProfitLinkResponseAfterPassingNonWorkingDomainURLCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
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
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String errorStatus				= Utils.getRestApiTestData(42, "status_invalid_retailer_url");
		String errorCode				= Utils.getRestApiTestData(42, "code_invalid_retailer_url");
		String errorTitle				= Utils.getRestApiTestData(42, "title_invalid_retailer_url");
		String errorDetail				= Utils.getRestApiTestData(42, "detail_invalid_retailer_url");
		String errorParameter			= Utils.getRestApiTestData(42, "parameter_invalid_retailer_url");
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);

		String link = Utils.getRestApiTestData(42, "non_working_domain_url_1");
		
		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode400);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = Utils.getRestApiTestData(42, "non_working_domain_url_2");
		
		createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode400);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = Utils.getRestApiTestData(42, "non_working_domain_url_3");
		
		createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode400);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = Utils.getRestApiTestData(42, "non_working_domain_url_4");
		
		createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode400);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = Utils.getRestApiTestData(42, "non_working_domain_url_5");
		
		createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode400);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = Utils.getRestApiTestData(42, "non_working_domain_url_6");
		
		createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode400);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = Utils.getRestApiTestData(42, "non_working_domain_url_7");
		
		createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode400);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = Utils.getRestApiTestData(42, "non_working_domain_url_8");
		
		createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode400);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = Utils.getRestApiTestData(42, "non_working_domain_url_9");
		
		createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode400);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = Utils.getRestApiTestData(42, "invalid_link_format_9");
		
		createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode400);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = MakeLinkUrls.DOUBLEQUESTIONMARK_INVALID_URL;
		
		createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode400);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing invalid URL format in post data and validating 422 status code with its response
	 * Invalid URL format: 55https://..com, http//:..cmo, 127.0.0.1, hptts://amazon.com, https://www. amazon .in, https:// www.amazon.in, amazon.in, http://www..com
	 *
	 */
	@Test(priority=6, description="Passing invalid URL format in post data and validating 422 status code with its response")
	public void validateExternalProfitLinkResponseAfterPassingInvalidLinkFormatCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
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
		String errorStatus				= Utils.getRestApiTestData(42, "status_invalid_link_format");
		String errorTitle				= Utils.getRestApiTestData(42, "title_invalid_link_format");
		String errorDetail				= Utils.getRestApiTestData(42, "detail_invalid_link_format");
		String errorPointer				= Utils.getRestApiTestData(42, "pointer_invalid_link_format");
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);

		String link = Utils.getRestApiTestData(42, "invalid_link_format_1");
		
		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode422);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndPoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorTitle, errorDetail, errorPointer);

		link = Utils.getRestApiTestData(42, "invalid_link_format_2");
		
		createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode422);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndPoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorTitle, errorDetail, errorPointer);

		link = Utils.getRestApiTestData(42, "invalid_link_format_3");
		
		createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode422);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndPoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorTitle, errorDetail, errorPointer);

		link = Utils.getRestApiTestData(42, "invalid_link_format_4");
		
		createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode422);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndPoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorTitle, errorDetail, errorPointer);

		link = Utils.getRestApiTestData(42, "invalid_link_format_5");
		
		createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode422);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndPoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorTitle, errorDetail, errorPointer);

		link = Utils.getRestApiTestData(42, "invalid_link_format_6");
		
		createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode422);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndPoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorTitle, errorDetail, errorPointer);

		link = Utils.getRestApiTestData(42, "invalid_link_format_8");
		
		createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode422);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndPoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing amazon product link URL with tags in post data and validating 200 status code with its response
	 * Amazon product URL: https://www.amazon.in/gp/product/B00KAUFMQC/ref=s9_acsd_al_bw_c_x_5_w?pf_rd_m=A1K21FY43GMZF8&pf_rd_s=merchandised-search-5&pf_rd_r=M7HJTAWSWTRT4EGVC8CX&pf_rd_t=101&pf_rd_p=d16aaf23-0405-4b0c-83b2-c37c0c9137ad&pf_rd_i=6802110031&tag=coupo07-21&ascsubtag=CU3265d8c9d45cd1
	 *
	 */
	@Test(priority=7, description="Passing amazon product link URL with tags in post data and validating 200 status code with its response")
	public void validateExternalProfitLinkResponseAfterPassingAmazonProductLinkWithTagsCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
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
		String link						= MakeLinkUrls.VALIDPRODUCTURLWITHTAGS;
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeEarnLink				= Utils.getRestApiTestData(43, "type_earn_link");
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);
		
		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode200);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkSuccessResponseHelperEndPoint(objSoftAssertion, createShareLinkResponse, typeEarnLink);

		String shortLinkURL = objCreateExternalProfitLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateExternalProfitLink.waitingTillRequiredURLVisible(driver, "amazon");
		
		String sellerURL = Utils.getCurrentURL(driver);
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objCreateExternalProfitLink.assertProfitLinkURLWithSellerLandedURL(sellerURL, MakeLinkUrls.VALIDPRODUCTURL_SELLER + exitClick);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing flipkart product link URL having coupondunia tags in post data and validating 200 status code with its response
	 * Flipkart product URL: https://www.flipkart.com/?affid=contact&affExtParam1=CU79a9ea080f05ba
	 *
	 */
	@Test(priority=8, description="Passing flipkart product link URL having coupondunia tags in post data and validating 200 status code with its response")
	public void validateExternalProfitLinkResponseAfterPassingFlipkartProductLinkWithCouponDuniaTagsCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
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
		String link						= MakeLinkUrls.FLIPKARTMAKELINKSWITHCOUOPONDUNIATAG;
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeEarnLink				= Utils.getRestApiTestData(43, "type_earn_link");
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);
		
		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode200);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkSuccessResponseHelperEndPoint(objSoftAssertion, createShareLinkResponse, typeEarnLink);

		String shortLinkURL = objCreateExternalProfitLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateExternalProfitLink.waitingTillRequiredURLVisible(driver, "flipkart");
		
		String sellerURL = Utils.getCurrentURL(driver);
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objCreateExternalProfitLink.assertProfitLinkURLWithSellerLandedURL(sellerURL, MakeLinkUrls.FLIPKARTMAKELINKSWITHCOUOPONDUNIATAG_AFTERREPLACED + exitClick + "&aid=rohanpouri");
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing flipkart product link URL without tags in post data and validating 200 status code with its response
	 * Flipkart product URL: https://www.flipkart.com/lg-6-2-kg-inverter-fully-automatic-top-load-silver-white/p/itmewv2u8wddak9h?pid=WMNEWV2UQWXURHJQ&srno=b_1_3&otracker=hp_bannerads_4_deskt-homep-3bcff_Whirlpool_70N0DC8LWDVG&lid=LSTWMNEWV2UQWXURHJQ1C8VIZ&fm=organic&iid=e8d169f4-d3f3-4f04-a1a8-5dc3f28a4d0a.WMNEWV2UQWXURHJQ.SEARCH&ppt=Homepage&ppn=Homepage&ssid=vn0tor8yts0000001555309381050
	 *
	 */
	@Test(priority=9, description="Passing flipkart product link URL without tags in post data and validating 200 status code with its response")
	public void validateExternalProfitLinkResponseAfterPassingFlipkartProductLinkWithoutTagsCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
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
		String link						= MakeLinkUrls.FLIPKARTMAKELINK;
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeEarnLink				= Utils.getRestApiTestData(43, "type_earn_link");
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);
		
		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode200);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkSuccessResponseHelperEndPoint(objSoftAssertion, createShareLinkResponse, typeEarnLink);

		String shortLinkURL = objCreateExternalProfitLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateExternalProfitLink.waitingTillRequiredURLVisible(driver, "flipkart");
		
		String sellerURL = Utils.getCurrentURL(driver);
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objCreateExternalProfitLink.assertProfitLinkURLWithSellerLandedURL(sellerURL, MakeLinkUrls.FLIPKARTMAKELINK_SELLER + exitClick + "&aid=rohanpouri");
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing non working shorten domain URL in post data and validating 400 status code with its response
	 * Non working domain shorten URL: http://bit.ly/2UCExCF, http://tiny.cc/e3634y, http://bit.do/ePaCA, https://rebrand.ly/e8e42, https://urlzs.com/TkAL & http://tinyurl.com/y6wpyvl7
	 *
	 */
	@Test(priority=10, description="Passing non working shorten domain URL in post data and validating 400 status code with its response")
	public void validateExternalProfitLinkResponseAfterPassingNonWorkingDomainShortenURLCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
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
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String errorStatus				= Utils.getRestApiTestData(42, "status_invalid_retailer_url");
		String errorCode				= Utils.getRestApiTestData(42, "code_invalid_retailer_url");
		String errorTitle				= Utils.getRestApiTestData(42, "title_invalid_retailer_url");
		String errorDetail				= Utils.getRestApiTestData(42, "detail_invalid_retailer_url");
		String errorParameter			= Utils.getRestApiTestData(42, "parameter_invalid_retailer_url");
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);

		String link = Utils.getRestApiTestData(42, "shortenbitly_google_Invalid");
		
		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode400);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = Utils.getRestApiTestData(42, "shortentinycc_google_Invalid");
		
		createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode400);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = Utils.getRestApiTestData(42, "shortenbitdo_google_Invalid");
		
		createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode400);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = Utils.getRestApiTestData(42, "shortenrebrandly_google_Invalid");
		
		createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode400);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = Utils.getRestApiTestData(42, "shortenurlZs_google_Invalid");
		
		createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode400);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = Utils.getRestApiTestData(42, "shortentinyURL_google_Invalid");
		
		createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode400);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing amazon bitly URL in post data and validating 200 status code with its response
	 * Bitly URL: https://amzn.to/2UCs5mf
	 *
	 */
	@Test(priority=11, description="Passing amazon bitly URL in post data and validating 200 status code with its response")
	public void validateExternalProfitLinkResponseAfterPassingAmazonBitlyProductLinkShortenURLCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
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
		String link						= MakeLinkUrls.VALIDSHORTENURLWITHTAGS_BITLY;
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeEarnLink				= Utils.getRestApiTestData(43, "type_earn_link");
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);
		
		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode200);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkSuccessResponseHelperEndPoint(objSoftAssertion, createShareLinkResponse, typeEarnLink);

		String shortLinkURL = objCreateExternalProfitLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateExternalProfitLink.waitingTillRequiredURLVisible(driver, "amazon");
		
		String sellerURL = Utils.getCurrentURL(driver);
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objCreateExternalProfitLink.assertProfitLinkURLWithSellerLandedURL(sellerURL, MakeLinkUrls.VALIDPRODUCTURL_SELLER + exitClick);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing bitdo URL in post data and validating 200 status code with its response
	 * Bitdo URL: http://bit.do/ePp7p
	 *
	 */
	@Test(priority=12, description="Passing bitdo URL in post data and validating 200 status code with its response")
	public void validateExternalProfitLinkResponseAfterPassingAmazonBitdoProductLinkShortenURLCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
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
		String link						= MakeLinkUrls.VALIDSHORTENURLWITHTAGS_BITDO;
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeEarnLink				= Utils.getRestApiTestData(43, "type_earn_link");
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);
		
		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode200);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkSuccessResponseHelperEndPoint(objSoftAssertion, createShareLinkResponse, typeEarnLink);

		String shortLinkURL = objCreateExternalProfitLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateExternalProfitLink.waitingTillRequiredURLVisible(driver, "amazon");
		
		String sellerURL = Utils.getCurrentURL(driver);
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objCreateExternalProfitLink.assertProfitLinkURLWithSellerLandedURL(sellerURL, MakeLinkUrls.VALIDPRODUCTURL_SELLER + exitClick);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing bit tiny cc URL in post data and validating 200 status code with its response
	 * Bitdo URL: http://tiny.cc/xwm74y
	 *
	 */
	@Test(priority=13, description="Passing bit tiny cc URL in post data and validating 200 status code with its response")
	public void validateExternalProfitLinkResponseAfterPassingAmazonBitTinyCCProductLinkShortenURLCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
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
		String link						= MakeLinkUrls.VALIDSHORTENURLWITHTAGS_TINYCC;
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeEarnLink				= Utils.getRestApiTestData(43, "type_earn_link");
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);
		
		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode200);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkSuccessResponseHelperEndPoint(objSoftAssertion, createShareLinkResponse, typeEarnLink);

		String shortLinkURL = objCreateExternalProfitLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateExternalProfitLink.waitingTillRequiredURLVisible(driver, "amazon");
		
		String sellerURL = Utils.getCurrentURL(driver);
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objCreateExternalProfitLink.assertProfitLinkURLWithSellerLandedURL(sellerURL, MakeLinkUrls.VALIDPRODUCTURL_SELLER + exitClick);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing bit tiny URL in post data and validating 200 status code with its response
	 * Bitdo URL: http://tinyurl.com/y2jn3m48
	 *
	 */
	@Test(priority=14, description="Passing bit tiny URL in post data and validating 200 status code with its response")
	public void validateExternalProfitLinkResponseAfterPassingAmazonBitTinyURLProductLinkShortenURLCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
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
		String link						= MakeLinkUrls.VALIDSHORTENURLWITHTAGS_TINYURL;
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeEarnLink				= Utils.getRestApiTestData(43, "type_earn_link");
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);
		
		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode200);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkSuccessResponseHelperEndPoint(objSoftAssertion, createShareLinkResponse, typeEarnLink);

		String shortLinkURL = objCreateExternalProfitLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateExternalProfitLink.waitingTillRequiredURLVisible(driver, "amazon");
		
		String sellerURL = Utils.getCurrentURL(driver);
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objCreateExternalProfitLink.assertProfitLinkURLWithSellerLandedURL(sellerURL, MakeLinkUrls.VALIDPRODUCTURL_SELLER + exitClick);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing myntra product URL in post data and validating 200 status code with its response
	 * Myntra URL: https://www.myntra.com/bedsheets/storyhome/storyhome-teal-blue--white-flat-120-tc-cotton-1-queen-bedsheet-with-2-pillow-covers/6786809/buy
	 *
	 */
	@Test(priority=15, description="Passing myntra product URL in post data and validating 200 status code with its response")
	public void validateExternalProfitLinkResponseAfterPassingMyntraProductLinkCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
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
		String link						= MakeLinkUrls.MYNTRAVALIDURL;
		String typeEarnLink				= Utils.getRestApiTestData(43, "type_earn_link");
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);
		
		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode200);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkSuccessResponseHelperEndPoint(objSoftAssertion, createShareLinkResponse, typeEarnLink);

		String shortLinkURL = objCreateExternalProfitLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateExternalProfitLink.waitingTillRequiredURLVisible(driver, "myntra");
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing myntra product shorten URL in post data and validating 200 status code with its response
	 * Myntra URL: https://bit.ly/2VOrIRU
	 *
	 */
	@Test(priority=16, description="Passing myntra product shorten URL in post data and validating 200 status code with its response")
	public void validateExternalProfitLinkResponseAfterPassingMyntraShortenProductLinkCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
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
		String link						= MakeLinkUrls.MYNTRAVALIDURL_BITLY;
		String typeEarnLink				= Utils.getRestApiTestData(43, "type_earn_link");
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);
		
		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode200);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkSuccessResponseHelperEndPoint(objSoftAssertion, createShareLinkResponse, typeEarnLink);

		String shortLinkURL = objCreateExternalProfitLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateExternalProfitLink.waitingTillRequiredURLVisible(driver, "myntra");
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing multiple product URL one valid other invalid URL in post data and validating 200 status code with its response
	 * Multiple URL: https://jabong.go2affise.com/click?pid=6&offer_id=1&sub1=69e421139ec9664d7c32eb6496e615d2&sub2=296692&path=https%3A%2F%2Fwww.myntra.com%2Froadster-maroon-striped-polo-collar-tshirt-
	 *
	 */
	@Test(priority=17, description="Passing multiple product URL one valid other invalid URL in post data and validating 200 status code with its response")
	public void validateExternalProfitLinkResponseAfterPassingMyntraMultipleProductLinkCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
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
		String link						= MakeLinkUrls.MYNTRAMULTIPLEURLWITHONLYONEVALID;
		String typeEarnLink				= Utils.getRestApiTestData(43, "type_earn_link");
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);
		
		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode200);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkSuccessResponseHelperEndPoint(objSoftAssertion, createShareLinkResponse, typeEarnLink);

		String shortLinkURL = objCreateExternalProfitLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateExternalProfitLink.waitingTillRequiredURLVisible(driver, "myntra");
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing multiple product shorten URL having one valid and other invalid in post data and validating 200 status code with its response
	 * Multiple URL: https://bit.ly/2KHSJph
	 *
	 */
	@Test(priority=18, description="Passing multiple product shorten URL having one valid and other invalid in post data and validating 200 status code with its response")
	public void validateExternalProfitLinkResponseAfterPassingMyntraMultipleShortenProductLinkCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
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
		String link						= MakeLinkUrls.MYNTRAMULTIPLEURLWITHONLYONEVALID_BILTY;
		String typeEarnLink				= Utils.getRestApiTestData(43, "type_earn_link");
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);
		
		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode200);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkSuccessResponseHelperEndPoint(objSoftAssertion, createShareLinkResponse, typeEarnLink);

		String shortLinkURL = objCreateExternalProfitLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateExternalProfitLink.waitingTillRequiredURLVisible(driver, "myntra");
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing jabong product URL in post data and validating 200 status code with its response
	 * Jabong URL: https://www.jabong.com/roadster-navy-blue-colourblocked-regular-fit-polo-t-shirt-4828564.htm?pos=2
	 *
	 */
	@Test(priority=19, description="Passing jabong product URL in post data and validating 200 status code with its response")
	public void validateExternalProfitLinkResponseAfterPassingJabongProductLinkCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
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
		String link						= MakeLinkUrls.JABONGVALIDURL;
		String typeEarnLink				= Utils.getRestApiTestData(43, "type_earn_link");
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);
		
		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode200);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkSuccessResponseHelperEndPoint(objSoftAssertion, createShareLinkResponse, typeEarnLink);

		String shortLinkURL = objCreateExternalProfitLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateExternalProfitLink.waitingTillRequiredURLVisible(driver, "jabong");
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing jabong product shorten URL in post data and validating 200 status code with its response
	 * Jabong URL: https://bit.ly/2VOwRtg
	 *
	 */
	@Test(priority=20, description="Passing jabong product shorten URL in post data and validating 200 status code with its response")
	public void validateExternalProfitLinkResponseAfterPassingJabongShortenProductLinkCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
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
		String link						= MakeLinkUrls.JABONGVALIDURL_BITLY;
		String typeEarnLink				= Utils.getRestApiTestData(43, "type_earn_link");
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);
		
		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode200);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkSuccessResponseHelperEndPoint(objSoftAssertion, createShareLinkResponse, typeEarnLink);

		String shortLinkURL = objCreateExternalProfitLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateExternalProfitLink.waitingTillRequiredURLVisible(driver, "jabong");
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing multiple jabong product URL one valid and other invalid URL in post data and validating 200 status code with its response
	 * Jabong URL: https://jabong.go2affise.com/click?pid=6&offer_id=1&sub1=69e421139ec9664d7c32eb6496e615d2&sub2=296692&path=https%3A%2F%2Fwww.jabong.com%2Froadster-maroon-striped-polo-collar-tshirt-
	 *
	 */
	@Test(priority=21, description="Passing multiple jabong product URL one valid and other invalid URL in post data and validating 200 status code with its response")
	public void validateExternalProfitLinkResponseAfterPassingJabongMultipleProductLinkCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
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
		String link						= MakeLinkUrls.JABONGMULTIPLEURLWITHONLYONEVALID;
		String typeEarnLink				= Utils.getRestApiTestData(43, "type_earn_link");
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);
		
		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode200);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkSuccessResponseHelperEndPoint(objSoftAssertion, createShareLinkResponse, typeEarnLink);

		String shortLinkURL = objCreateExternalProfitLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateExternalProfitLink.waitingTillRequiredURLVisible(driver, "jabong");
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing multiple jabong product shorten URL having one valid and other invalid URL in post data and validating 200 status code with its response
	 * Jabong URL: https://bit.ly/2D4rKOf
	 *
	 */
	@Test(priority=22, description="Passing multiple jabong product shorten URL having one valid and other invalid URL in post data and validating 200 status code with its response")
	public void validateExternalProfitLinkResponseAfterPassingJabongMultipleShortenProductLinkCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
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
		String link						= MakeLinkUrls.JABONGMULTIPLEURLWITHONLYONEVALID_BILTY;
		String typeEarnLink				= Utils.getRestApiTestData(43, "type_earn_link");
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);
		
		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode200);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkSuccessResponseHelperEndPoint(objSoftAssertion, createShareLinkResponse, typeEarnLink);

		String shortLinkURL = objCreateExternalProfitLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateExternalProfitLink.waitingTillRequiredURLVisible(driver, "jabong");
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing myntra & jabong product URL in post data and validating 400 status code with its response
	 * Jabong URL: https://myntra.com/click?pid=6&offer_id=1&sub1=69e421139ec9664d7c32eb6496e615d2&sub2=296692&path=https%3A%2F%2Fwww.jabong.com%2Froadster-maroon-striped-polo-collar-tshirt-
	 *
	 */
	@Test(priority=23, description="Passing myntra & jabong product URL in post data and validating 400 status code with its response")
	public void validateExternalProfitLinkResponseAfterPassingTooManyLongValidURLErrorWhenHavingTooManyValidURLWithDifferentDomainCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
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
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String link						= MakeLinkUrls.JABONGANDMYNTRA_TWO_VALID_URL;
		String errorStatus				= Utils.getRestApiTestData(42, "status_too_may_url");
		String errorCode				= Utils.getRestApiTestData(42, "code_too_may_url");
		String errorTitle				= Utils.getRestApiTestData(42, "title_too_may_url");
		String errorDetail				= Utils.getRestApiTestData(42, "detail_too_may_url");
		String errorParameter			= Utils.getRestApiTestData(42, "parameter_too_may_url");
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);
		
		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode400);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing myntra & jabong product shorten URL in post data and validating 400 status code with its response
	 * Jabong URL: https://bit.ly/2GaXuSw
	 *
	 */
	@Test(priority=24, description="Passing myntra & jabong product shorten URL in post data and validating 400 status code with its response")
	public void validateExternalProfitLinkResponseAfterPassingTooManyShortenValidURLErrorWhenHavingTooManyValidURLWithDifferentDomainCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
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
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String link						= MakeLinkUrls.JABONGANDMYNTRA_TWO_VALID_URL_BITLY;
		String errorStatus				= Utils.getRestApiTestData(42, "status_too_may_url");
		String errorCode				= Utils.getRestApiTestData(42, "code_too_may_url");
		String errorTitle				= Utils.getRestApiTestData(42, "title_too_may_url");
		String errorDetail				= Utils.getRestApiTestData(42, "detail_too_may_url");
		String errorParameter			= Utils.getRestApiTestData(42, "parameter_too_may_url");
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);
		
		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode400);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing multiple product URL in post data and validating 400 status code with its response
	 * Multiple URL: https://myntra.com/click?pid=6&offer_id=1&sub1=69e421139ec9664d7c32eb6496e615d2&sub2=296692&path=https%3A%2F%2Fwww.jabong.com%2Froadster-maroon-striped-polo-collar-tshirt-
	 *
	 */
	@Test(priority=25, description="Passing multiple product URL in post data and validating 400 status code with its response")
	public void validateExternalProfitLinkResponseAfterPassingTooManyLongValidURLErrorWhenHavingTooManyValidURLWithSameDomainCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
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
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String link						= MakeLinkUrls.HAVING_MULTIPLE_URLS_WITH_SAME_VALID_DOMAIN;
		String errorStatus				= Utils.getRestApiTestData(42, "status_too_may_url");
		String errorCode				= Utils.getRestApiTestData(42, "code_too_may_url");
		String errorTitle				= Utils.getRestApiTestData(42, "title_too_may_url");
		String errorDetail				= Utils.getRestApiTestData(42, "detail_too_may_url");
		String errorParameter			= Utils.getRestApiTestData(42, "parameter_too_may_url");
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);
		
		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode400);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing myntra & jabong product URL in post data and validating 400 status code with its response
	 * Multiple URL: https://myntra.com/click?pid=6&offer_id=1&sub1=69e421139ec9664d7c32eb6496e615d2&sub2=296692&path=https%3A%2F%2Fwww.jabong.com%2Froadster-maroon-striped-polo-collar-tshirt-
	 *
	 */
	@Test(priority=26, description="Passing myntra & jabong product URL in post data and validating 400 status code with its response")
	public void validateExternalProfitLinkResponseAfterPassingTooManyShortenValidURLErrorWhenHavingTooManyValidURLWithSameDomainCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
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
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String link						= MakeLinkUrls.HAVING_MULTIPLE_URLS_WITH_SAME_VALID_DOMAIN_BITLY;
		String errorStatus				= Utils.getRestApiTestData(42, "status_too_may_url");
		String errorCode				= Utils.getRestApiTestData(42, "code_too_may_url");
		String errorTitle				= Utils.getRestApiTestData(42, "title_too_may_url");
		String errorDetail				= Utils.getRestApiTestData(42, "detail_too_may_url");
		String errorParameter			= Utils.getRestApiTestData(42, "parameter_too_may_url");
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);
		
		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode400);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing empty string for ipaddress in post data and validating 422 status code with its response
	 * ipaddress: ""
	 *
	 */
	@Test(priority=27, description="Passing empty string for ipaddress in post data and validating 422 status code with its response")
	public void validateExternalProfitLinkResponseMandatoryIPAddressCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
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
		String link						= MakeLinkUrls.VALIDPRODUCTURL;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "ip_detail_mandatory");
		String errorPointer				= Utils.getRestApiTestData(10, "ip_pointer");
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);

		ipAddress = Utils.getRestApiTestData(8, "emptyValue");
		
		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode422);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndPoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing empty string for type in post data and validating 422 status code with its response
	 * Type: ""
	 *
	 */
	@Test(priority=28, description="Passing empty string for type in post data and validating 422 status code with its response")
	public void validateExternalProfitLinkResponseMandatoryTypeCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
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
		String link						= MakeLinkUrls.VALIDPRODUCTURL;
		String errorStatus				= Utils.getRestApiTestData(11, "type_status");
		String errorTitle				= Utils.getRestApiTestData(11, "type_title");
		String errorDetail				= Utils.getRestApiTestData(11, "type_detail");
		String errorPointer				= Utils.getRestApiTestData(11, "type_pointer");
		String typeCreateExternalPftLink= Utils.getRestApiTestData(8, "emptyValue");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);

		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode422);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndPoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing empty string for user id in post data and validating 422 status code with its response
	 * user id: ""
	 *
	 */
	@Test(priority=29, description="Passing empty string for user id in post data and validating 422 status code with its response")
	public void validateExternalProfitLinkResponseMandatoryUserIDCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
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
		String link						= MakeLinkUrls.VALIDPRODUCTURL;
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");
		String errorStatus				= Utils.getRestApiTestData(43, "status_user_id_required");
		String errorTitle				= Utils.getRestApiTestData(43, "title_user_id_required");
		String errorDetail				= Utils.getRestApiTestData(43, "detail_user_id_required");
		String errorPointer				= Utils.getRestApiTestData(43, "pointer_user_id_required");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		String userID = Utils.getRestApiTestData(8, "double_quote");

		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode422);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndPoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing empty string for link in post data and validating 422 status code with its response
	 * link: ""
	 *
	 */
	@Test(priority=30, description="Passing empty string for link in post data and validating 422 status code with its response")
	public void validateExternalProfitLinkResponseMandatoryLinkCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
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
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String link						= Utils.getRestApiTestData(8, "emptyValue");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");
		String errorStatus				= Utils.getRestApiTestData(43, "status_link_required");
		String errorTitle				= Utils.getRestApiTestData(43, "title_link_required");
		String errorDetail				= Utils.getRestApiTestData(43, "detail_link_required");
		String errorPointer				= Utils.getRestApiTestData(43, "pointer_link_required");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);

		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode422);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndPoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing alphabets for userid in post data and validating 422 status code with its response
	 * user id: abc
	 *
	 */
	@Test(priority=31, description="Passing alphabets for userid in post data and validating 422 status code with its response")
	public void validateExternalProfitLinkResponseAfterPassingAlphabetsForUserIDCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
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
		String link						= MakeLinkUrls.VALIDPRODUCTURL;
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");
		String errorStatus				= Utils.getRestApiTestData(43, "status_user_id_in_integer");
		String errorTitle				= Utils.getRestApiTestData(43, "title_user_id_in_integer");
		String errorDetail				= Utils.getRestApiTestData(43, "detail_user_id_in_integer");
		String errorPointer				= Utils.getRestApiTestData(43, "pointer_user_id_in_integer");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		String userID = "\"abc\"";

		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode422);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndPoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing invalid device type and validating 400 status code with its response
	 * device type: desk
	 *
	 */
	@Test(priority=32, description="Passing invalid device type and validating 400 status code with its response")
	public void validateExternalProfitLinkResponseAfterPassingInvalidDeviceTypeCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
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
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String link						= MakeLinkUrls.VALIDPRODUCTURL;
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");
		String errorStatus				= Utils.getRestApiTestData(10, "status_invalid_device");
		String errorCode				= Utils.getRestApiTestData(10, "code_invalid_device");
		String errorTitle				= Utils.getRestApiTestData(10, "title_invalid_device");
		String errorDetail				= Utils.getRestApiTestData(10, "detail_invalid_device");
		String errorParameter			= Utils.getRestApiTestData(10, "parameter_invalid_device");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);

		deviceType = Utils.getRestApiTestData(4, "invalid_desktop");
		
		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode400);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing empty string for api key and validating 401 status code with its response
	 * api key: ""
	 *
	 */
	@Test(priority=33, description="Passing empty string for api key and validating 401 status code with its response")
	public void validateExternalProfitLinkResponseMandatoryAPIKeyCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
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
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String link						= MakeLinkUrls.VALIDPRODUCTURL;
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(8, "emptyValue");
		String errorMessage				= Utils.getRestApiTestData(43, "error_message_no_api_key_found");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);

		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode401);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, createShareLinkResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing invalid api key and validating 401 status code with its response
	 * api key: asdfgh
	 *
	 */
	@Test(priority=34, description="Passing invalid api key and validating 401 status code with its response")
	public void validateExternalProfitLinkResponseAfterPassingInvalidAPIKeyCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
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
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String link						= MakeLinkUrls.VALIDPRODUCTURL;
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value")+Utils.generateRandomAlphabets(5);
		String errorMessage				= Utils.getRestApiTestData(43, "error_message_invalid_credentials");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);

		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, statusCode401);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, createShareLinkResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing empty header and validating 401 status code with its response
	 *
	 */
	@Test(priority=35, description="Passing empty header and validating 401 status code with its response")
	public void validateEmptyHeaderCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");

		// New User Signup - POST Request
		Response CreateSharedLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, deviceType, apiKeyValue, statusCode401);
		
		// Validate SignupV1WithOTP success response - POST Request
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, CreateSharedLinkResponse, errorMessage);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing invalid content type and validating 415 status code with its response
	 *
	 */
	@Test(priority=36, description="Passing invalid content type and validating 415 status code with its response")
	public void validateInvalidContentTypeKeyValueCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
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
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");
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
		Response CreateSharedLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, acceptValue, contentTypeValue, deviceType, apiKeyValue, statusCode415);
		
		// Validate SignupV1WithOTP success response - POST Request
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, CreateSharedLinkResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing invalid accept type and validating 415 status code with its response
	 *
	 */
	@Test(priority=37, description="Passing invalid accept type and validating 415 status code with its response")
	public void validateInvalidAcceptTypeKeyValueCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
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
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");
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
		Response CreateSharedLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, acceptValue, contentTypeValue, deviceType, apiKeyValue, statusCode415);
		
		// Validate SignupV1WithOTP success response - POST Request
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, CreateSharedLinkResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing empty string accept type and validating 406 status code with its response
	 *
	 */
	@Test(priority=38, description="Passing empty string accept type and validating 406 status code with its response")
	public void validateEmptyAcceptTypeKeyValueCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
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
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");
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
		Response CreateSharedLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, acceptValue, contentTypeValue, deviceType, apiKeyValue, statusCode406);
		
		// Validate SignupV1WithOTP success response - POST Request
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, CreateSharedLinkResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing empty string content type and validating 415 status code with its response
	 *
	 */
	@Test(priority=39, description="Passing empty string content type and validating 415 status code with its response")
	public void validateEmptyContentTypeKeyValueCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
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
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");
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
		Response CreateSharedLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, acceptValue, contentTypeValue, deviceType, apiKeyValue, statusCode415);
		
		// Validate SignupV1WithOTP success response - POST Request
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, CreateSharedLinkResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing invalid basic auth username and password and validating 401 status code with its response
	 * api key: asdfgh
	 *
	 */
	@Test(priority=40, description="Passing invalid basic auth username and password and validating 401 status code with its response")
	public void validateExternalProfitLinkResponseAfterPassingInvalidUsernamePasswordCreateExternalProfitLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateExternalProfitLink objCreateExternalProfitLink = new CreateExternalProfitLink(logger);
		
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
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String link						= MakeLinkUrls.VALIDPRODUCTURL;
		String typeCreateExternalPftLink= Utils.getRestApiTestData(43, "type_create_external_profit_link");
		String apiKeyValue				= Utils.getRestApiTestData(43, "api_key_value");
		String errorMessage				= Utils.getRestApiTestData(43, "error_message_invalid_credentials");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		String userID = Integer.toString(userid);

		Response createShareLinkResponse = objCreateExternalProfitLink.getCreateExternalProfitLinkHelperEndpoint(baseURL, typeCreateExternalPftLink, userID, link, ipAddress, deviceType, apiKeyValue, fullName, password,statusCode401);
		
		objCreateExternalProfitLink.validateCreateExternalProfitLinkErrorResponseHelperEndpoint(objSoftAssertion, createShareLinkResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

}