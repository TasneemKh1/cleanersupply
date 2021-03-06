package actions

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
import validations.GeneralValidations
import internal.GlobalVariable

public class HeaderActions {
	/**
	 * @author tasneem
	 * 
	 * */
	public static String mouseOverNavItem(String testObjID) {
		TestObject NavItem=findTestObject(testObjID)
		WebUI.mouseOver(NavItem);
		String linkToQuickOrder=WebUI.getAttribute(NavItem, "href")
		return linkToQuickOrder
	}
	/**
	 * @author tasneem
	 *
	 * */
	public static void clickANavItem(String testObjID) {
		TestObject NavItem=findTestObject(testObjID)
		WebUI.click(NavItem)
	}
	/***
	 * Type into search input field
	 * @author moham
	 * @param value
	 */
	public static void typeIntoSearchInput (String value) {
		GeneralActions.typeIntoInputField(findTestObject('Object Repository/Header/input_search'), value)
	}
	public static void cliclOnSearchButton () {
		WebUI.click(findTestObject('Object Repository/Header/button_searchAction'))
	}
	
	public static void mouseOverDescription(TestObject link) {
		WebUI.mouseOver(link);
		assert WebUI.getCSSValue(link, "text-decoration-line").equals("underline")
	}
}
