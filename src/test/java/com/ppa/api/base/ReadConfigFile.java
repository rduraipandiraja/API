package com.ppa.api.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfigFile {
		
	/************************************************ Methods Creation started *******************************************************/

	//*************************************************** getProperty ****************************************************************/

	public static Object getProperty(String key) {

		try (InputStream input = new FileInputStream("./config.properties")) {

			Properties prop = new Properties();

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println();
			return prop.getProperty(key);

		} catch (IOException ex) {
			System.out.println("Either Config file is missing or You are facing the issue in config file... ");
			return null;

		}

	}

}