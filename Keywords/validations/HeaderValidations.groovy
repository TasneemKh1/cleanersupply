package validations

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

import internal.GlobalVariable

public class HeaderValidations {
	/***
	 * Verify if cart items number matches the expected one
	 * @author moham
	 * @param expectedItemsNumber
	 */
	public static void verifyCartItemsNumber (int expectedItemsNumber) {
		TestObject cartBadge = findTestObject('Object Repository/Header/span_cartBadge')
		int cartItemsNumber = Integer.parseInt(WebUI.getText(cartBadge).trim())
		assert WebUI.verifyEqual(expectedItemsNumber, cartItemsNumber)
	}

	/***
	 * Verify if cart label matches the expected one
	 * @author moham
	 * @param expectedLabel
	 */
	public static void verifyCartLabel (String expectedLabel) {
		String cartLabel = WebUI.getText(findTestObject('Object Repository/Header/span_cartLabel')).trim()
		assert cartLabel.equals(expectedLabel)
	}

	/***
	 * Verify if search input placeholder is not empty
	 * @author moham
	 */
	public static void verifySearchInputPlaceholderIsNotEmpty () {
		TestObject searchInput = findTestObject('Object Repository/Header/input_search')
		String placeholder = WebUI.getAttribute(searchInput, "placeholder")
		assert !placeholder.isEmpty()
	}
}
