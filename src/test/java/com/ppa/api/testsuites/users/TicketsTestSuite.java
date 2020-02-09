package com.ppa.api.testsuites.users;

import java.awt.AWTException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.admin.pages.AdminCommonFunctions;
import com.admin.pages.AffillateNetwork;
import com.admin.pages.Dashboard;
import com.admin.pages.Login;
import com.admin.pages.Stores;
import com.admin.pages.Vouchers;
import com.ppa.api.base.Base;
import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.endpoints.helper.HelperAccessToken;
import com.ppa.api.endpoints.helper.SignUpOTP;
import com.ppa.api.endpoints.helper.SignUpV1WithOTP;
import com.ppa.api.endpoints.users.CreateShareLink;
import com.ppa.api.endpoints.users.ExitClicks;
import com.ppa.api.endpoints.users.RaiseTicket;
import com.ppa.api.endpoints.users.Tickets;
import com.ppa.api.utilities.Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TicketsTestSuite extends Base {

	String storeID = "1999";
	String voucherID = "";
	String productID = "";
	String link = "";
	String storeName = "";
	String voucherName = "";
	String affiliateNetworkID = "";
	String orderIDFormat = Utils.generateRandomAlphabets(10);
	String ticketAcceptHours = "72";

	@Test(priority=0)	
	public void storeCreationOne() throws InterruptedException, AWTException{

		try {

			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			Login objLogin = new Login(driver, logger); 
			Dashboard objDashboard = new Dashboard(driver, logger);
			Stores objAddStore = new Stores(driver, logger);
			Vouchers objVouchers = new Vouchers(driver, logger);
			AffillateNetwork objAffillateNetwork = new AffillateNetwork(driver, logger);
			
			/******************************************************************** VARIABLE CREATION ******************************************************************************/

			String adminURL	= Utils.getConfigurationSetupTestData(0, "admin_url");
			String adminDropDown = Utils.getConfigurationSetupTestData(0, "dropDown");
			String adminUsername = Utils.getConfigurationSetupTestData(0, "admin_username");
			String adminPassword = Utils.getConfigurationSetupTestData(0, "admin_password");
			
			/*********************************************************************** LOGIN PAGE*********************************************************************************/

			// Redirecting to admin panel
			Utils.loadURL(driver, adminURL);
			
			Thread.sleep(1000);
			
			objLogin.selectValueAdminDropdown(driver, adminDropDown);
			objLogin.enterUserName(driver, adminUsername);
			objLogin.enterPassword(driver, adminPassword);
			objLogin.clickLogin(driver);

			/********************************************************************* ALL STORE PAGE ******************************************************************************/

			objDashboard.clickMainMenuStore(driver);

			objAddStore.clickSubMenuStores(driver);

			objAddStore.clickAddNewStore(driver);

			objAddStore.clickStoresTab(driver);

			String arrayNewStoreDetails_One[] = objAddStore.enterCashbackStoreDetails(driver, "Active", "Adda52", "", "https://www.amazon.com/", "", "", "Terms and conditions", "All", "999041", ticketAcceptHours, "ACTIVATE CASHBACK", "90", orderIDFormat, "Within 4 Days of Shipment", "check", "check", "check", "check");

			objAddStore.clickCategoryCheckbox(driver);

			objAddStore.clickSEOTab(driver);

			objAddStore.enterKeywordsDetails(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterMetaTags(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterProductMetaTags(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterSocialMediaMessageDetails(driver, "This is "+arrayNewStoreDetails_One[0]+" social message");

			objAddStore.enterNormalPrimaryCashbackDetails(driver, "Percentage", "INR", 1500, 75, "This Is Test User Entering normal Primary cashback");

			objAddStore.enterNormalSecondaryCashbackDetails(driver, 0, "Percentage", 1250, "This Is Test User Entering Normal Secondary cashback", "All", 0);

			objAddStore.enterOthers(driver, "Terms and conditions");

			objAddStore.StoreContent_Creation(driver, arrayNewStoreDetails_One[0]);

			objAddStore.click_Button_AddStore(driver);

			/********************************************************************** STORE PAGE *********************************************************************************/

			objAddStore.enterStoreName(driver, arrayNewStoreDetails_One[0]);

			objAddStore.clickSearch(driver);

			storeID = objAddStore.getStoreID(driver);
			
			storeName = arrayNewStoreDetails_One[0];

			objAddStore.getStoreType(driver, arrayNewStoreDetails_One[0].trim());

			objAddStore.clickPickOfTheDayPickOfTheWeek_SpecificStore(driver, arrayNewStoreDetails_One[0].trim());
			
			driver.navigate().refresh();

			objAddStore.clickEdit(driver);

			objAddStore.clickSecondaryCashbackTab(driver);

			objAddStore.getSecondaryCashbackID(driver, 1);

			/******************************************************************* ALL VOUCHER PAGE ******************************************************************************/

			objVouchers.clickSubMenuVoucher(driver);

			String arrayNewVoucher_Without_Code_One[] = objVouchers.enterVoucherWithCodeDetails_APITesting(driver, arrayNewStoreDetails_One, "Increased Rate", "COUPON CODE", "All", storeID);

			/********************************************************************** VOUCHER PAGE *******************************************************************************/

			//click_Clear_Until_Default_Dropdown_Value_SelectDateRange_By_JavaScriptExecutor(driver, "btn_Clear");

			objVouchers.enterSearchCriteria(driver);

			objVouchers.enterStoreName(driver, arrayNewStoreDetails_One[0]);

			objVouchers.clickSearch(driver);

			voucherID = objVouchers.getVoucherID(driver, 1);

			voucherName = arrayNewVoucher_Without_Code_One[1];

			/******************************************************************* AFFILIATE NW PAGE *****************************************************************************/

			/*objDashboard.clickMainMenuSettings(driver);*/

			objAffillateNetwork.clickSubMenuAffiliateNetwork(driver);	

			objAffillateNetwork.clickAffiliateNetworkEditIcon(driver, arrayNewStoreDetails_One[3].trim());

			affiliateNetworkID = objAffillateNetwork.getAffiliateNetworkID(driver);

			/*************************************************************** STORE CREATION COMPLETED **************************************************************************/

		} catch (Exception e) {



			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			Login objLogin = new Login(driver, logger); 
			Dashboard objDashboard = new Dashboard(driver, logger);
			Vouchers objVouchers = new Vouchers(driver, logger);
			Stores objAddStore = new Stores(driver, logger);
			AffillateNetwork objAffillateNetwork = new AffillateNetwork(driver, logger);
			
			/******************************************************************** VARIABLE CREATION ******************************************************************************/

			String adminURL	= Utils.getConfigurationSetupTestData(0, "admin_url");
			String adminDropDown = Utils.getConfigurationSetupTestData(0, "dropDown");
			String adminUsername = Utils.getConfigurationSetupTestData(0, "admin_username");
			String adminPassword = Utils.getConfigurationSetupTestData(0, "admin_password");
			
			/*********************************************************************** LOGIN PAGE*********************************************************************************/

			// Redirecting to admin panel
			Utils.loadURL(driver, adminURL);
			
			Thread.sleep(1000);
			
			objLogin.selectValueAdminDropdown(driver, adminDropDown);
			objLogin.enterUserName(driver, adminUsername);
			objLogin.enterPassword(driver, adminPassword);
			objLogin.clickLogin(driver);

			/********************************************************************* ALL STORE PAGE ******************************************************************************/

			objDashboard.clickMainMenuStore(driver);

			objAddStore.clickSubMenuStores(driver);

			objAddStore.clickAddNewStore(driver);

			objAddStore.clickStoresTab(driver);

			String arrayNewStoreDetails_One[] = objAddStore.enterCashbackStoreDetails(driver, "Active", "Adda52", "", "https://www.amazon.com/", "", "", "Terms and conditions", "All", "999041", "72", "ACTIVATE CASHBACK", "90", "orderID", "Within 4 Days of Shipment", "check", "check", "check", "check");

			objAddStore.clickCategoryCheckbox(driver);

			objAddStore.clickSEOTab(driver);

			objAddStore.enterKeywordsDetails(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterMetaTags(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterProductMetaTags(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterSocialMediaMessageDetails(driver, "This is "+arrayNewStoreDetails_One[0]+" social message");

			objAddStore.enterNormalPrimaryCashbackDetails(driver, "Percentage", "INR", 1500, 75, "This Is Test User Entering normal Primary cashback");

			objAddStore.enterNormalSecondaryCashbackDetails(driver, 0, "Percentage", 1250, "This Is Test User Entering Normal Secondary cashback", "All", 0);

			objAddStore.enterOthers(driver, "Terms and conditions");

			objAddStore.StoreContent_Creation(driver, arrayNewStoreDetails_One[0]);

			objAddStore.click_Button_AddStore(driver);

			/********************************************************************** STORE PAGE *********************************************************************************/

			objAddStore.enterStoreName(driver, arrayNewStoreDetails_One[0]);

			objAddStore.clickSearch(driver);

			storeID = objAddStore.getStoreID(driver);
			
			storeName = arrayNewStoreDetails_One[0];

			objAddStore.getStoreType(driver, arrayNewStoreDetails_One[0].trim());

			objAddStore.clickPickOfTheDayPickOfTheWeek_SpecificStore(driver, arrayNewStoreDetails_One[0].trim());
			
			driver.navigate().refresh();

			objAddStore.clickEdit(driver);

			objAddStore.clickSecondaryCashbackTab(driver);

			objAddStore.getSecondaryCashbackID(driver, 1);

			/******************************************************************* ALL VOUCHER PAGE ******************************************************************************/

			objVouchers.clickSubMenuVoucher(driver);

			String arrayNewVoucher_Without_Code_One[] = objVouchers.enterVoucherWithCodeDetails_APITesting(driver, arrayNewStoreDetails_One, "Increased Rate", "COUPON CODE", "All", storeID);

			/********************************************************************** VOUCHER PAGE *******************************************************************************/

			//click_Clear_Until_Default_Dropdown_Value_SelectDateRange_By_JavaScriptExecutor(driver, "btn_Clear");

			objVouchers.enterSearchCriteria(driver);

			objVouchers.enterStoreName(driver, arrayNewStoreDetails_One[0]);

			objVouchers.clickSearch(driver);

			voucherID = objVouchers.getVoucherID(driver, 1);

			voucherName = arrayNewVoucher_Without_Code_One[1];

			/******************************************************************* AFFILIATE NW PAGE *****************************************************************************/

			/*objDashboard.clickMainMenuSettings(driver);*/

			objAffillateNetwork.clickSubMenuAffiliateNetwork(driver);	

			objAffillateNetwork.clickAffiliateNetworkEditIcon(driver, arrayNewStoreDetails_One[3].trim());

			affiliateNetworkID = objAffillateNetwork.getAffiliateNetworkID(driver);

		}

	}

	@Test(priority=1)
	public void validateTicketResponseAfterChangingToAllMarkTicketStatusTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		Tickets objTickets = new Tickets(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
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
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeTicket				= Utils.getRestApiTestData(34, "type_ticket");
		String message					= Utils.getRestApiTestData(34, "message");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String totalRecords				= Utils.getRestApiTestData(35, "total_records");
		String pageNumber				= Utils.getRestApiTestData(35, "page_number");
		String pageSize					= Utils.getRestApiTestData(35, "page_size");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "tickets");
		String selfURL					= self;
		String firstURL					= self+Utils.getRestApiTestData(35, "pagenumber")+"&"+Utils.getRestApiTestData(35, "pagesize");
		String lastURL					= self+Utils.getRestApiTestData(35, "pagenumber")+"&"+Utils.getRestApiTestData(35, "pagesize");
		String prevURL					= Utils.getRestApiTestData(31, "null");
		String nextURL					= Utils.getRestApiTestData(31, "null");
		String currentDate				= Utils.currentDateFormat();
		String receivedStatus			= Utils.getRestApiTestData(35, "received");
		String sentToRetailerStatus		= Utils.getRestApiTestData(35, "sent_to_retailer");
		String resolvedStatus			= Utils.getRestApiTestData(35, "resolved");
		String adminReply				= Utils.generateRandomAlphabets(20);
		String currency					= ConfigurationSetup.CURRENCYTYPE;

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
				
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode201);
		
		String ticketID = objRaiseTicket.getTicketID(raiseTicketResponse);
				
		objRaiseTicket.validateRaiseTicketSucessResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, typeTicket, message);
		
		Response ticketResponse = objTickets.getTicketsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objTickets.validateTicketsSuccessResponseUserEndPoint(objSoftAssertion, ticketResponse, totalRecords, pageNumber, pageSize);
		
		objTickets.validateTicketsSuccessResponseUserEndPoint(objSoftAssertion, ticketResponse, selfURL, firstURL, lastURL, prevURL, nextURL);

		String selfLinks = self+"/"+ticketID;
		
		objTickets.validateTicketsSuccessResponseUserEndPoint(objSoftAssertion, ticketResponse, "0", typeTicket, ticketID, currentDate, storeName, orderValue, currency, receivedStatus, selfLinks);
		
		objAdminCommonFunctions.changeStatusForTicket(exitClick, adminReply, sentToRetailerStatus);
		
		ticketResponse = objTickets.getTicketsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objTickets.validateTicketsSuccessResponseUserEndPoint(objSoftAssertion, ticketResponse, totalRecords, pageNumber, pageSize);
		
		objTickets.validateTicketsSuccessResponseUserEndPoint(objSoftAssertion, ticketResponse, selfURL, firstURL, lastURL, prevURL, nextURL);

		objTickets.validateTicketsSuccessResponseUserEndPoint(objSoftAssertion, ticketResponse, "0", typeTicket, ticketID, currentDate, storeName, orderValue, currency, sentToRetailerStatus, selfLinks);
		
		objAdminCommonFunctions.changeStatusForTicket(exitClick, adminReply, resolvedStatus);
		
		ticketResponse = objTickets.getTicketsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objTickets.validateTicketsSuccessResponseUserEndPoint(objSoftAssertion, ticketResponse, totalRecords, pageNumber, pageSize);
		
		objTickets.validateTicketsSuccessResponseUserEndPoint(objSoftAssertion, ticketResponse, selfURL, firstURL, lastURL, prevURL, nextURL);

		objTickets.validateTicketsSuccessResponseUserEndPoint(objSoftAssertion, ticketResponse, "0", typeTicket, ticketID, currentDate, storeName, orderValue, currency, resolvedStatus, selfLinks);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=2)
	public void validateTicketResponseAfterRaisingMoreThanOneTicketsTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		Tickets objTickets = new Tickets(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
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
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeTicket				= Utils.getRestApiTestData(34, "type_ticket");
		String message					= Utils.getRestApiTestData(34, "message");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String totalRecords				= Utils.getRestApiTestData(35, "total_records_3");
		String pageNumber				= Utils.getRestApiTestData(35, "page_number");
		String pageSize					= Utils.getRestApiTestData(35, "page_size");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "tickets");
		String selfURL					= self;
		String firstURL					= self+Utils.getRestApiTestData(35, "pagenumber")+"&"+Utils.getRestApiTestData(35, "pagesize");
		String lastURL					= self+Utils.getRestApiTestData(35, "pagenumber")+"&"+Utils.getRestApiTestData(35, "pagesize");
		String prevURL					= Utils.getRestApiTestData(31, "null");
		String nextURL					= Utils.getRestApiTestData(31, "null");
		String currentDate				= Utils.currentDateFormat();
		String receivedStatus			= Utils.getRestApiTestData(35, "received");
		String currency					= ConfigurationSetup.CURRENCYTYPE;

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.getExitClick(email);
		
		String exitClickOne = objAdminCommonFunctions.getExitClickBasedOnIndex("4");

		objExitClicks.exitClickBackDate(email, exitClickOne, exitClickCreatedDate);
		
		String exitClickTwo = objAdminCommonFunctions.getExitClickBasedOnIndex("3");

		objExitClicks.exitClickBackDate(email, exitClickTwo, exitClickCreatedDate);
		
		String exitClickThree = objAdminCommonFunctions.getExitClickBasedOnIndex("2");
		
		objExitClicks.exitClickBackDate(email, exitClickThree, exitClickCreatedDate);
		
		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClickOne;
				
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode201);
		
		String ticketIDOne = objRaiseTicket.getTicketID(raiseTicketResponse);

		pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClickTwo;
				
		raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode201);
		
		String ticketIDTwo = objRaiseTicket.getTicketID(raiseTicketResponse);

		pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClickThree;
				
		raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode201);
		
		String ticketIDThree = objRaiseTicket.getTicketID(raiseTicketResponse);
				
		objRaiseTicket.validateRaiseTicketSucessResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, typeTicket, message);
		
		Response ticketResponse = objTickets.getTicketsResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objTickets.validateTicketsSuccessResponseUserEndPoint(objSoftAssertion, ticketResponse, totalRecords, pageNumber, pageSize);
		
		objTickets.validateTicketsSuccessResponseUserEndPoint(objSoftAssertion, ticketResponse, selfURL, firstURL, lastURL, prevURL, nextURL);

		String selfLinks = self+"/"+ticketIDThree;
		
		objTickets.validateTicketsSuccessResponseUserEndPoint(objSoftAssertion, ticketResponse, "0", typeTicket, ticketIDThree, currentDate, storeName, orderValue, currency, receivedStatus, selfLinks);

		selfLinks = self+"/"+ticketIDTwo;
		
		objTickets.validateTicketsSuccessResponseUserEndPoint(objSoftAssertion, ticketResponse, "1", typeTicket, ticketIDTwo, currentDate, storeName, orderValue, currency, receivedStatus, selfLinks);

		selfLinks = self+"/"+ticketIDOne;
		
		objTickets.validateTicketsSuccessResponseUserEndPoint(objSoftAssertion, ticketResponse, "2", typeTicket, ticketIDOne, currentDate, storeName, orderValue, currency, receivedStatus, selfLinks);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=3)
	public void validateTicketResponseAfterPassingPageNumberAndPageSizeTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		Tickets objTickets = new Tickets(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
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
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeTicket				= Utils.getRestApiTestData(34, "type_ticket");
		String message					= Utils.getRestApiTestData(34, "message");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String totalRecords				= Utils.getRestApiTestData(35, "total_records_3");
		String pageNumber				= Utils.getRestApiTestData(35, "page_number_2");
		String pageSize					= Utils.getRestApiTestData(35, "page_size_1");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "tickets");
		String pagenumber				= Utils.getRestApiTestData(35, "pagenumber_2");
		String pagesize					= Utils.getRestApiTestData(35, "pagesize_1");
		String queryParameter			= pagenumber+"&"+pagesize;
		String selfURL					= self+Utils.getRestApiTestData(35, "pagenumber_query_parameter")+"2&"+Utils.getRestApiTestData(35, "pagesize_query_parameter")+"1";
		String firstURL					= self+Utils.getRestApiTestData(35, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(35, "pagesize_query_parameter")+"1";
		String lastURL					= self+Utils.getRestApiTestData(35, "pagenumber_query_parameter")+"3&"+Utils.getRestApiTestData(35, "pagesize_query_parameter")+"1";
		String prevURL					= self+Utils.getRestApiTestData(35, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(35, "pagesize_query_parameter")+"1";
		String nextURL					= self+Utils.getRestApiTestData(35, "pagenumber_query_parameter")+"3&"+Utils.getRestApiTestData(35, "pagesize_query_parameter")+"1";
		String currentDate				= Utils.currentDateFormat();
		String receivedStatus			= Utils.getRestApiTestData(35, "received");
		String currency					= ConfigurationSetup.CURRENCYTYPE;

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.getExitClick(email);
		
		String exitClickOne = objAdminCommonFunctions.getExitClickBasedOnIndex("4");

		objExitClicks.exitClickBackDate(email, exitClickOne, exitClickCreatedDate);
		
		String exitClickTwo = objAdminCommonFunctions.getExitClickBasedOnIndex("3");

		objExitClicks.exitClickBackDate(email, exitClickTwo, exitClickCreatedDate);
		
		String exitClickThree = objAdminCommonFunctions.getExitClickBasedOnIndex("2");
		
		objExitClicks.exitClickBackDate(email, exitClickThree, exitClickCreatedDate);
		
		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClickOne;
				
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode201);
		
		objRaiseTicket.getTicketID(raiseTicketResponse);

		pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClickTwo;
				
		raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode201);
		
		String ticketIDTwo = objRaiseTicket.getTicketID(raiseTicketResponse);

		pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClickThree;
				
		raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode201);
		
		objRaiseTicket.getTicketID(raiseTicketResponse);
				
		objRaiseTicket.validateRaiseTicketSucessResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, typeTicket, message);
		
		Response ticketResponse = objTickets.getTicketsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objTickets.validateTicketsSuccessResponseUserEndPoint(objSoftAssertion, ticketResponse, totalRecords, pageNumber, pageSize);
		
		objTickets.validateTicketsSuccessResponseUserEndPoint(objSoftAssertion, ticketResponse, selfURL, firstURL, lastURL, prevURL, nextURL);

		String selfLinks = self+"/"+ticketIDTwo;
		
		objTickets.validateTicketsSuccessResponseUserEndPoint(objSoftAssertion, ticketResponse, "0", typeTicket, ticketIDTwo, currentDate, storeName, orderValue, currency, receivedStatus, selfLinks);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=4)
	public void validateTicketResponseAfterPassingInvalidPageNumberAndPageSizeTicketEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		Tickets objTickets = new Tickets(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeTicket				= Utils.getRestApiTestData(34, "type_ticket");
		String message					= Utils.getRestApiTestData(34, "message");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String queryParameter			= Utils.getRestApiTestData(35, "pagenumber_query_parameter")+"numbersize&"+Utils.getRestApiTestData(35, "pagesize_query_parameter")+"1";
		String errorStatus				= Utils.getRestApiTestData(35, "error_status_400");
		String errorCode				= Utils.getRestApiTestData(35, "error_code_11001");
		String errorTitle				= Utils.getRestApiTestData(35, "error_title_invalid_page_number_size");
		String errorDetail				= Utils.getRestApiTestData(35, "error_detail_invalid_page_number_size");
		String errorParameterPageNumber	= Utils.getRestApiTestData(35, "error_pointer_invalid_page_number");
		String errorParameterPageSize	= Utils.getRestApiTestData(35, "error_pointer_invalid_page_size");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);

		String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
				
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode201);
		
		objRaiseTicket.validateRaiseTicketSucessResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, typeTicket, message);
		
		Response ticketResponse = objTickets.getTicketsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode400);
		
		objTickets.validateTicketsErrorResponseUserEndpoint(objSoftAssertion, ticketResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameterPageNumber);

		queryParameter = Utils.getRestApiTestData(35, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(35, "pagesize_query_parameter")+"size";
		
		ticketResponse = objTickets.getTicketsResponseUserEndPoint(baseURL, accessTokenValue, queryParameter, statusCode400);
		
		objTickets.validateTicketsErrorResponseUserEndpoint(objSoftAssertion, ticketResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameterPageSize);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=5)
	public void validateEmptyHeaderTicketsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		Tickets objTickets = new Tickets(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message");

		Response ticketsResponse = objTickets.getTicketsResponseUserEndPoint(baseURL, statusCode401);
		
		objTickets.validateTicketsErrorResponseUserEndpoint(objSoftAssertion, ticketsResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=6)
	public void validateInvalidAuthorizationKeyValueTicketsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		Tickets objTickets = new Tickets(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message_invalid_json");
		String accessTokenValue			= Utils.getRestApiTestData(0, "auth_invalid_value");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");

		Response ticketsResponse = objTickets.getTicketsResponseUserEndpoint(baseURL, accessTokenValue, acceptValue, statusCode401);
		
		objTickets.validateTicketsErrorResponseUserEndpoint(objSoftAssertion, ticketsResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=7)
	public void validateInvalidAcceptTypeKeyValueTicketsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		Tickets objTickets = new Tickets(logger);
		
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
		int statusCode415				= Integer.parseInt(Utils.getRestApiTestData(9, "status_415"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_invalid_value");
		String errorStatus				= Utils.getRestApiTestData(12, "error_status");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Response ticketsResponse = objTickets.getTicketsResponseUserEndpoint(baseURL, accessTokenValue, acceptValue, statusCode415);
		
		objTickets.validateTicketsErrorResponseUserEndpoint(objSoftAssertion, ticketsResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=8)
	public void validateEmptyAuthorizationKeyValueTicketsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		Tickets objTickets = new Tickets(logger);
		
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
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String errorMessage				= Utils.getRestApiTestData(12, "message");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);

		accessTokenValue = Utils.getRestApiTestData(8, "emptyValue");

		Response ticketsResponse = objTickets.getTicketsResponseUserEndpoint(baseURL, accessTokenValue, acceptValue, statusCode401);
		
		objTickets.validateTicketsErrorResponseUserEndpoint(objSoftAssertion, ticketsResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=9)
	public void validateEmptyAcceptTypeKeyValueTicketsEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		Tickets objTickets = new Tickets(logger);
		
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
		int statusCode406				= Integer.parseInt(Utils.getRestApiTestData(9, "status_406"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String acceptValue				= Utils.getRestApiTestData(8, "emptyValue");
		String errorStatus				= Utils.getRestApiTestData(12, "error_status_406");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title_notacceptable");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail_accept_header");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Response ticketsResponse = objTickets.getTicketsResponseUserEndpoint(baseURL, accessTokenValue, acceptValue, statusCode406);
		
		objTickets.validateTicketsErrorResponseUserEndpoint(objSoftAssertion, ticketsResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		objSoftAssertion.assertAll();

	}

}