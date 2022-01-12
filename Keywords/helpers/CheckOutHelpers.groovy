package helpers

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import actions.Navigations

import internal.GlobalVariable
import validations.GeneralValidations

public class CheckOutHelpers {
	public static void checkOutSenario() {
		//Navigate to the cart
		Navigations.navigateToCart();
		//Click on 'Proceed To Checkout'
		GeneralValidations.verifyCartProductData(['f'], ['20'], ['10'])
//		TestObject processedToCheckOutButton = findTestObject('Object Repository/Cart/button_ProceedToCheckout')
//		WebUI.click(processedToCheckOutButton)
//		GeneralHelpers.newPageIsOpened(GlobalVariable.cartUrl, ' Shopping Cart ')
//		GeneralValidations.verifyTitleOfHeading(' Shopping Cart ')
	
	}
}
