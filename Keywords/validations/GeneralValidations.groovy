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

public class GeneralValidations {
	/***
	 * Verify Current Page Title Is Not Empty
	 * @author fatma
	 */
	public static void verifyCurrentPageTitleIsNotEmpty() {
		assert !WebUI.getWindowTitle().isEmpty()
	}
	/***
	 * verify current page title match the expected title
	 * @param expectedTitle
	 * @author fatma
	 */
	public static void verifyCurrentPageTitleValue(String expectedTitle) {
		assert WebUI.getWindowTitle().equals(expectedTitle)
	}

	/**
	 * Verify Current Page URL matched the passed url
	 * @param expectedURL expectedURL or part of expectedURL
	 * @author fatma
	 */
	public static void verifyCurrentPageURL(String expectedURL) {
		assert WebUI.getUrl().contains(expectedURL)
	}
	/***
	 * Verify current page title match the expected title
	 * @author moham
	 * @param expectedTitle expectedTitle or part of expectedTitle
	 */
	public static void verifyCurrentPageTitle(String expectedTitle) {
		assert WebUI.getWindowTitle().toLowerCase().contains(expectedTitle.toLowerCase())
	}
	/***
	 * Verify if input value matches the typed one
	 * @author moham
	 * @param inputTestObject
	 * @param expectedValue
	 */
	public static void verifyInputValue (TestObject inputTestObject, String expectedValue) {
		assert WebUI.getAttribute(inputTestObject, "value").trim().equals(expectedValue)
	}
	/**
	 * @author tasneem
	 * */
	public static void verifyCurrentPageHeading(String testObjID,String expectedHeading) {
		assert WebUI.getText(findTestObject('Object Repository/QuickOrder/span-QuickOrderHeading')).contains(expectedHeading)
	}
	/***
	 * verify Hover On element
	 * @param elementID elementID
	 * @param backGround background of element style
	 * @param color color of element style
	 * @param elementIDOfAttribute element ID Of Attribute
	 * @param attribute attribute of element
	 * @author fatma
	 */
	public static void verifyHover(String elementID,String backGround ,String color,String elementIDOfAttribute,String attribute) {
		TestObject Element = findTestObject(elementID)
		assert WebUI.getCSSValue(Element, 'color').contains(color)
		System.out.println(WebUI.getCSSValue(Element, 'color'))
		if (attribute != " " && elementIDOfAttribute != " " && backGround != " ") {
			TestObject ElementIDOfAttribute = findTestObject(elementIDOfAttribute)
			WebUI.waitForElementAttributeValue(ElementIDOfAttribute, "class", attribute, GlobalVariable.pageLoadTimeOut)
			assert WebUI.getAttribute(ElementIDOfAttribute, "class").contains(attribute)
			assert WebUI.getCSSValue(Element, 'background').contains(backGround)
			System.out.println(WebUI.getCSSValue(Element, 'background'))
		}
	}

	/***
	 * verify Breadcrump Text
	 * @param element id of element
	 * @param BreadCrumbText the text of breadcrump
	 * @author fatma
	 */
	public static void verifyBreadcrump(String elementID,String BreadcrumbText) {
		TestObject Element = findTestObject(elementID)
		assert WebUI.getText(Element).contains(BreadcrumbText);
	}
	/***
	 * verify Title Of Heading
	 * @param Title the title of heading section
	 * @author fatma
	 */
	public static void verifyTitleOfHeading(String Title) {
		assert WebUI.getText(findTestObject('//*[@id="search-container"]//h1')).contains(Title);
	}
}
