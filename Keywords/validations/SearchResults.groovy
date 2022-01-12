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

public class SearchResults {
	/***
	 * Verify if search results page heading matches with the expected one
	 * @author moham
	 * @param expectedHeading
	 */
	public static void verifysearchResultsPageHeading(String expectedHeading) {
		WebUI.getText(findTestObject('Object Repository/SearchResultPage/h1_pageHeading')).toLowerCase().equals(expectedHeading.toLowerCase())
	}

	/***
	 * Verify if search results page sub heading matches with the expected one
	 * @author moham
	 * @param expectedSubHeading expectedSubHeading or part of expectedSubHeading
	 */
	public static void verifysearchResultsPageSubHeading(String expectedSubHeading) {
		WebUI.getText(findTestObject('Object Repository/SearchResultPage/h2_pageSubHeading')).toLowerCase().contains(expectedSubHeading.toLowerCase())
	}
}
