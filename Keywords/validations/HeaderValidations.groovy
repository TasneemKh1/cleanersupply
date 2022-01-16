package validations

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.WebElement

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
import actions.GeneralActions

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

	/***
	 * Verify if search input is empty
	 * @author moham
	 */
	public static void verifySearchInputIsEmpty () {
		TestObject searchInput = findTestObject('Object Repository/Header/input_search')
		assert WebUI.getAttribute(searchInput, "value").isEmpty()
	}

	/***
	 * Verify if search input value matches the typed one
	 * @author moham
	 * @param expectedValue
	 */
	public static void verifySearchInputValue (String expectedValue) {
		GeneralValidations.verifyInputValue(findTestObject('Object Repository/Header/input_search'), expectedValue)
	}

	/***
	 * verify if search autoComplete dropdown is visible
	 * @author moham
	 */
	public static void verifySearchAutoCompleteDropdownVisible () {
		TestObject searchAutoCompleteDropdown = findTestObject('Object Repository/Header/div_searchAutoCompleteDropdown')
		WebUI.waitForElementVisible(searchAutoCompleteDropdown, GlobalVariable.visiablityItemTimeOut)
		WebUI.verifyElementVisible(searchAutoCompleteDropdown)
	}

	/***
	 * Verify if search autoComplete dropdown header includes the search term
	 * @author moham
	 * @param expectedTerm
	 */
	public static void verifySearchAutoCompleteDropdownHeader (String expectedTerm) {
		TestObject searchAutoCompleteHeader = findTestObject('Object Repository/Header/p_searchAutoCompleteHeader')
		assert WebUI.getText(searchAutoCompleteHeader).toLowerCase().contains(expectedTerm.toLowerCase())
	}

	/***
	 * Verify if search autoComplete content labels contain both categories and products
	 * @author moham
	 */
	public static void verifySearchAutoCompleteContentLabels () {
		List<WebElement> searchAutoCompleteContentLabels = WebUI.findWebElements(findTestObject('Object Repository/Header/div_searchAutoCompleteContentLabels'), GlobalVariable.visiablityItemTimeOut)
		TestObject firstLabelTestObject = WebUI.convertWebElementToTestObject(searchAutoCompleteContentLabels.get(0))
		TestObject secondLabelTestObject = WebUI.convertWebElementToTestObject(searchAutoCompleteContentLabels.get(1))
		assert WebUI.getText(firstLabelTestObject).trim().toLowerCase().equals("categories")
		assert WebUI.getText(secondLabelTestObject).trim().toLowerCase().equals("products")
	}
	/*
	 * *@author tasneem
	 * 
	 * **/
	public static void verifySearchAutoCompleteContentLabelsForStockTerm () {
		List<WebElement> searchAutoCompleteContentLabels = WebUI.findWebElements(findTestObject('Object Repository/Header/div_searchAutoCompleteContentLabels'), GlobalVariable.visiablityItemTimeOut)
		TestObject firstLabelTestObject = WebUI.convertWebElementToTestObject(searchAutoCompleteContentLabels.get(0))
		assert WebUI.getText(firstLabelTestObject).trim().toLowerCase().equals("products")
	}
	/***
	 * Verify if search autoComplete categories include the search term
	 * @author moham
	 * @param expectedTerm
	 */
	public static void verifySearchAutoCompleteCategories (String expectedTerm) {
		List<WebElement> searchAutoCompleteCategories = WebUI.findWebElements(findTestObject('Object Repository/Header/a_searchAutoCompleteCategories'), GlobalVariable.visiablityItemTimeOut)
		for (int i = 0; i++; i < searchAutoCompleteCategories.size()) {
			TestObject categoriesTestObject = WebUI.convertWebElementToTestObject(searchAutoCompleteCategories.get(i))
			assert WebUI.getText(categoriesTestObject).toLowerCase().contains(expectedTerm.toLowerCase())
		}
	}
	/**
	 * @author tasneem
	 * */
	public static void hoverOverItemsInSuggestionsAtHeader () {
		List<WebElement> searchAutoCompleteCategories = WebUI.findWebElements(findTestObject('Object Repository/Header/li-boxSuggestions'), GlobalVariable.visiablityItemTimeOut)
		TestObject firstItemInSuggestions=WebUI.convertWebElementToTestObject(searchAutoCompleteCategories.get(0))
		GeneralActions.mouseOverOnElement(firstItemInSuggestions)
		assert WebUI.getCSSValue(firstItemInSuggestions, 'background-color').equals('rgba(82, 36, 127, 1)')
		assert WebUI.getCSSValue(firstItemInSuggestions, 'color').equals('rgba(255, 255, 255, 1)')
	}
	public static void checkClassOpenIsAddedToMyAccount() {
		assert	WebUI.getAttribute(findTestObject('Object Repository/Header/li-MyCart-dropdown'), 'class').contains('open')

	}
	public static void checkMyAccountDropDownIsDisplayed() {
		assert	WebUI.getCSSValue(findTestObject('Object Repository/Header/Cart/div-MyAccountDropDown'), 'display').contains('block')

	}
}
