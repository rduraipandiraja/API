package com.ppa.api.base;

import com.ppa.api.utilities.Utils;

import io.restassured.RestAssured;

public class ConfigurationSetup {

	/************************************************ Variable Creation started ******************************************************/

	// Variable declaration
	public static final String PARTNER				= Utils.getConfigurationSetupTestData(0, "partner");
	public static final String ENVIRONMENT			= Utils.getConfigurationSetupTestData(0, "environment");

	public static final String FRONTENDURL 			= Utils.getConfigurationSetupTestData(0, "frontend_url");
	public static final String ADMINURL 			= Utils.getConfigurationSetupTestData(0, "admin_url");
	public static final String PARTNERNAME 			= Utils.getConfigurationSetupTestData(0, "partner");
	public static final String APPDOMAINURL 		= Utils.getConfigurationSetupTestData(0, "app_domain_url");
	public static final String DEKSTOPDOMAINURL		= Utils.getConfigurationSetupTestData(0, "dsk_domain_url");
	public static final String FACEBOOKURL 			= Utils.getConfigurationSetupTestData(0, "facebook_url");
	public static final String FACEBOOKID 			= Utils.getConfigurationSetupTestData(0, "facebook_id");
	public static final String FACEBOOKUSERNAME		= Utils.getConfigurationSetupTestData(0, "facebook_username");
	public static final String FACEBOOKPASSWORD		= Utils.getConfigurationSetupTestData(0, "facebook_password");
	public static final String APPAUTHTYPE 			= Utils.getConfigurationSetupTestData(0, "app_auth_type");
	public static final String DESKTOPAUTHTYPE		= Utils.getConfigurationSetupTestData(0, "desk_auth_type");
	public static final String APPVERSION 			= Utils.getConfigurationSetupTestData(0, "app_version");
	public static final String APPUSERNAME 			= Utils.getConfigurationSetupTestData(0, "app_username");
	public static final String APPPASSWORD 			= Utils.getConfigurationSetupTestData(0, "app_password");
	public static final String DESKTOPUSERNAME		= Utils.getConfigurationSetupTestData(0, "dsk_username");
	public static final String DESKTOPPASSWORD 		= Utils.getConfigurationSetupTestData(0, "dsk_password");
	public static final String ADMINUSERNAME 		= Utils.getConfigurationSetupTestData(0, "admin_username");
	public static final String ADMINPASSWORD 		= Utils.getConfigurationSetupTestData(0, "admin_password");
	public static final String OTPFLAG		 		= Utils.getConfigurationSetupTestData(0, "otp_flag");
	public static final String USERNAME	 			= Utils.getConfigurationSetupTestData(0, "username");
	public static final String DROPDOWN		 		= Utils.getConfigurationSetupTestData(0, "dropDown");
	public static final String CURRENCYTYPE			= Utils.getConfigurationSetupTestData(0, "currencyType");
	public static final String CREATESHARELINK		= Utils.getConfigurationSetupTestData(0, "create_sharelink");

	/************************************************ Methods Creation started *******************************************************/

	/* partnerConfigurationSetup */
	public static void partnerConfigurationSetup() {

		Log.info("******* About to do ConfigurationSetup for "+PARTNER+"_"+ENVIRONMENT+"*******");

		RestAssured.baseURI = ConfigurationSetup.DEKSTOPDOMAINURL;
		RestAssured.basePath = "/v1";
		
		Log.info("frontEndURL: "+FRONTENDURL);
		Log.info("adminURL: "+ADMINURL);
		Log.info("partnerName: "+PARTNERNAME);
		Log.info("apiAppDomainURL: "+APPDOMAINURL);
		Log.info("apiDskDomainURL: "+DEKSTOPDOMAINURL);
		Log.info("facebookURL: "+FACEBOOKURL);
		Log.info("facebookID: "+FACEBOOKID);
		Log.info("facebookUserName: "+FACEBOOKUSERNAME);
		Log.info("facebookPassword: "+FACEBOOKPASSWORD);
		Log.info("RestAssured.baseURI: "+RestAssured.baseURI);
		Log.info("RestAssured.basePath: "+RestAssured.basePath);
		Log.info("dskAuthType: "+DESKTOPAUTHTYPE);
		Log.info("appAuthType: "+APPAUTHTYPE);
		Log.info("appVersion: "+APPVERSION);
		Log.info("appUserName: "+APPUSERNAME);
		Log.info("appPassword: "+APPPASSWORD);
		Log.info("dskUserName: "+DESKTOPUSERNAME);
		Log.info("dskPassword: "+DESKTOPPASSWORD);
		Log.info("dskUserName: "+ADMINUSERNAME);
		Log.info("dskPassword: "+ADMINPASSWORD);
		Log.info("otpFlag: "+OTPFLAG);
		Log.info("userName: "+USERNAME);
		Log.info("dropDown: "+DROPDOWN);
		Log.info("currencyType: "+CURRENCYTYPE);
		Log.info("createSharelink: "+CREATESHARELINK);

		Log.info("******* Successfully done ConfigurationSetup for "+PARTNER+"_"+ENVIRONMENT+"*******");

	}

}