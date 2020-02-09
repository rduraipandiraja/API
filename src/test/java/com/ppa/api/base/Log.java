package com.ppa.api.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class Log {
	
	static Logger objLog = LogManager.getLogger(Log.class);
	
	public static void startTestCase(String sTestCaseName) {
		
		objLog.info("*****************************************************************************************");
		objLog.info("$$$$$$$$$$$$$$$$$$$$$        " + sTestCaseName + "        $$$$$$$$$$$$$$$$$$$$$$$$$");
		objLog.info("****************************************************************************************");
	}

	// This is to print log for the ending of the test case
	public static void endTestCase() {
		objLog.info("X");
		objLog.info("X");
		objLog.info("XXXXXXXXXXXXXXXXXXXXXXX             " + "-E---N---D-" + "             XXXXXXXXXXXXXXXXXXXXXX");
		objLog.info("X");
		objLog.info("X");
	}

	// Need to create these methods, so that they can be called
	public static void info(String message) {
		objLog.info(message);
	}

	public static void warn(String message) {
		objLog.warn(message);
	}

	public static void error(String message) {
		objLog.error(message);
	}

	public static void fatal(String message) {
		objLog.fatal(message);
	}

	public static void debug(String message) {
		objLog.debug(message);
	}
}