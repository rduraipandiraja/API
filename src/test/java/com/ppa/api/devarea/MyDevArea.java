package com.ppa.api.devarea;

import static io.restassured.RestAssured.given;

import java.awt.AWTException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.admin.pages.AdminCommonFunctions;
import com.admin.pages.AdminCoreFunction;
import com.admin.pages.AffillateNetwork;
import com.admin.pages.Dashboard;
import com.admin.pages.Login;
import com.admin.pages.NetworkStoreDomainMapping;
import com.admin.pages.ProductBrowserEditMode;
import com.admin.pages.Stores;
import com.admin.pages.Vouchers;
import com.ppa.api.base.Base;
import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.base.MakeLinkUrls;
import com.ppa.api.endpoints.helper.HelperAccessToken;
import com.ppa.api.endpoints.helper.SignUpOTP;
import com.ppa.api.endpoints.helper.SignUpV1WithOTP;
import com.ppa.api.endpoints.payments.NEFTPost;
import com.ppa.api.endpoints.users.CreateExternalProfitLink;
import com.ppa.api.endpoints.users.CreateShareLink;
import com.ppa.api.endpoints.users.MyEarnings;
import com.ppa.api.utilities.Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class MyDevArea extends Base {

	@Test(priority=0)
	public void updateSignupBonusReferralSignupBonus() throws InterruptedException, AWTException {
		
		try {

			// Object creations
			AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
			
			// Assigning values
			String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
			String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
			String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
			String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
			String zero						= Utils.getRestApiTestData(31, "zero");
			String frontEndURl				= ConfigurationSetup.FRONTENDURL;

			objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
			
			objAdminCommonFunctions.updateSignUpReferralBonusBonus(frontEndURl, zero, zero);
			
		} catch (Exception e) {

			// Object creations
			AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
			
			// Assigning values
			String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
			String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
			String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
			String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
			String zero						= Utils.getRestApiTestData(31, "zero");
			String frontEndURl				= ConfigurationSetup.FRONTENDURL;

			objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
			
			objAdminCommonFunctions.updateSignUpReferralBonusBonus(frontEndURl, zero, zero);
			
		}

	}


}