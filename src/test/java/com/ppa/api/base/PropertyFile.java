package com.ppa.api.base;

public class PropertyFile {

	/************************************************ Variable Creation started ******************************************************/

	/************************************************ Methods Creation started *******************************************************/

	//******************************************************* partner ****************************************************************/

	public static String partner() {

		String partner = (String) ReadConfigFile.getProperty("partner");

		return partner;
	}

	//******************************************************* environment ************************************************************/

	public static String environment() {

		String environment = (String) ReadConfigFile.getProperty("environment");

		return environment;
	}

}