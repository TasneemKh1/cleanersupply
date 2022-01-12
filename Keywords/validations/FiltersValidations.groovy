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

import helpers.FiltersHelpers
import internal.GlobalVariable

public class FiltersValidations {
	/***
	 * Verify current URL, filters groups numbers, if filter is selected, applied criteria and number of products in subheading
	 * @author moham
	 * @param expectedURL
	 * @param packagingProductNumber
	 * @param filtersGroupsNumber
	 * @param filterName
	 */
	public static void verifyPackingProductsFilterApplied (String expectedURL, int packagingProductNumber, int filtersGroupsNumber, String filterName) {
		TestObject packagingProductsLink = findTestObject('Object Repository/Filters/a_packagingProductsLink')
		TestObject packagingProductsLinkParent = findTestObject('Object Repository/Filters/li_packagingProductsLinkParent')
		FiltersHelpers.verifyFilterApplied(expectedURL, packagingProductNumber, filtersGroupsNumber, packagingProductsLinkParent, filterName)
	}

	/***
	 * Verify current URL, filters groups numbers, if filter is selected, applied criteria and number of products in subheading
	 * @author moham
	 * @param expectedURL
	 * @param plasticBagsNumber
	 * @param filtersGroupsNumber
	 * @param filterName
	 */
	public static void verifyBlasticBagsFilterApplied (String expectedURL, int plasticBagsNumber, int filtersGroupsNumber, String filterName) {
		TestObject plasticBagsLink = findTestObject('Object Repository/Filters/a_plasticBagsLink')
		TestObject plasticBagsLinkParent = findTestObject('Object Repository/Filters/li_plasticBagsLinkParent')
		FiltersHelpers.verifyFilterApplied(expectedURL, plasticBagsNumber, filtersGroupsNumber,  plasticBagsLinkParent, filterName)
	}
}
