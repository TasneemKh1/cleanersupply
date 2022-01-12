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
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.entity.global.GlobalVariableEntity

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
	 * @author fatma
	 * @param expectedURL
	 * @param pageTitle the title of page
	 */
	public static void newPageIsOpened(String expectedURL,String pageTitle) {
		WebUI.waitForPageLoad(GlobalVariable.pageLoadTimeOut)
		GeneralValidations.verifyCurrentPageTitleIsNotEmpty()
		GeneralValidations.verifyCurrentPageURL(expectedURL)
		GeneralValidations.verifyCurrentPageTitleValue(pageTitle)
	}
}
