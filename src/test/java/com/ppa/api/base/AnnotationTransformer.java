package com.ppa.api.base;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import com.ppa.api.utilities.Utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationTransformer implements IAnnotationTransformer {

	/******************************************************** Methods Creation started ****************************************************************/

	/* transform */
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {

		String testMethodName		= testMethod.toString();
		String partnerName			= Utils.getConfigurationSetupTestData(0, "partner");

		if (( ( (testMethodName.contains("DeviceTypeApp")) || (testMethodName.contains("SignUpOTP")) || (testMethodName.contains("MobileNumber")) || (testMethodName.contains("BVAForOTP")) || (testMethodName.contains("BVAForGUID")) || (testMethodName.contains("GUID")) || (testMethodName.contains("OTPGuidMandatory")) || (testMethodName.contains("OTPMandatory")) || (testMethodName.contains("CreateNewOTP"))|| (testMethodName.contains("VerifyOTP")) ||(testMethodName.contains("ResendOTP")) ||(testMethodName.contains("TransactionalOTP")) ||(testMethodName.contains("OTPExpiry")) ||(testMethodName.contains("CreateShareLink")) || (testMethodName.contains("CreateExternalProfitLink")) || (testMethodName.contains("SharedActivity")) ) && (partnerName.equals("pouringpounds")))) {
			
			annotation.setEnabled(false);

		} else {

			annotation.setEnabled(true);

		}
		
	}

}