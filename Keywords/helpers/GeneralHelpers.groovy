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

import internal.GlobalVariable
import actions.Navigations
import validations.GeneralValidations
public class GeneralHelpers {
	/***
	 * initial Scenario
	 * @author fatma
	 */
	public static void initScenario() {
		WebUI.openBrowser('');
		WebUI.maximizeWindow()

		Navigations.navigateToHomePage();
		WebUI.waitForPageLoad(GlobalVariable.pageLoadTimeOut)

		GeneralValidations.verifyCurrentPageTitleIsNotEmpty()
		GeneralValidations.verifyCurrentPageURL(GlobalVariable.baseURL)
	}

	/***
	 * check title is not empty  and check the current url for each page when it is opened
	 * @author Tasneem
	 * @param expectedURL
	 */
	public static void newPageIsOpened(String expectedURL) {
		WebUI.waitForPageLoad(GlobalVariable.pageLoadTimeOut)
		GeneralValidations.verifyCurrentPageTitleIsNotEmpty()
		GeneralValidations.verifyCurrentPageURL(expectedURL)
	}

	/***
	 * Verify page title and current URL
	 * @author moham
	 * @param expectedUrl expectedUrl or part of expectedUrl
	 * @param expectedTitle expectedTitle or part of expectedTitle
	 */
	public static void verifyCurrentUrlAndPageTitle (String expectedUrl, String expectedTitle) {
		WebUI.waitForPageLoad(GlobalVariable.pageLoadTimeOut)
		GeneralValidations.verifyCurrentPageURL(expectedUrl)
		GeneralValidations.verifyCurrentPageTitle(expectedTitle)
	}
}
