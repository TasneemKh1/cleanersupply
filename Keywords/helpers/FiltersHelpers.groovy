package helpers

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.awt.geom.GeneralPath

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

import actions.FiltersActions
import actions.GeneralActions
import internal.GlobalVariable
import validations.GeneralValidations

public class FiltersHelpers {
	/***
	 * @author moham
	 * @param categoryLinkTestObject
	 * @return filtered product number
	 */
	public static Integer storeFilteredProductNumber (TestObject categoryLinkTestObject) {
		String categoryLinkText = WebUI.getText(categoryLinkTestObject)
		String [] stringArray = categoryLinkText.split("\\s+")
		String stringNumber = stringArray.getAt(stringArray.length-1).trim()
		return Integer.parseInt(stringNumber.substring(1, stringNumber.length() - 1))
	}

	/***
	 * @author moham
	 * @param categoryLinkTestObject
	 * @return filtered product link Url
	 */
	public static String storeFilteredProductLinkUrl (TestObject categoryLinkTestObject) {
		return WebUI.getAttribute(categoryLinkTestObject, "href").trim()
	}

	/***
	 * Verify current URL, filters groups numbers, if filter is selected, applied criteria and number of products in subheading
	 * @author moham
	 * @param expectedURL
	 * @param filterNumber
	 * @param filtersGroupsNumber
	 * @param filterLinkParent
	 * @param filterName
	 */
	public static void verifyFilterApplied (String expectedURL, int filterNumber, int filtersGroupsNumber, TestObject filterLinkParent, String filterName) {
		TestObject pageSubHeading = findTestObject('Object Repository/SearchResultPage/h2_pageSubHeading')
		TestObject lastAppliedCriteriaLink = findTestObject('Filters/a_appliedCriteria')
		// Wait until overlay is disappeared
		WebUI.waitForElementNotVisible(findTestObject('Object Repository/General/div_overlay'), 2)
		// Verify current URL
		GeneralValidations.verifyCurrentPageURL(expectedURL)
		// Verify filters groups numbers
		assert (FiltersActions.storeFiltersGroupsNumber() < filtersGroupsNumber)
		// Verify if filter is selected
		assert WebUI.getAttribute(filterLinkParent, "class").contains("selected")
		// Verify applied criteria
		assert WebUI.getText(lastAppliedCriteriaLink).trim().toLowerCase().contains(filterName.toLowerCase())
		// Verify number of products in subheading
		String pageSubHeadingText = WebUI.getText(pageSubHeading)
		String [] stringArray = pageSubHeadingText.split("\\(")
		String stringNumber = stringArray.getAt(1).trim()
		int productsNumber = Integer.parseInt(stringNumber.substring(0, stringNumber.length() - 1))
		assert (filterNumber >= productsNumber)
	}
	/***
	 * choose From Selector
	 * @param BtnId
	 * @param value
	 * @author fatma
	 */
	public static void chooseFromSelector(String BtnId,String value) {
		GeneralActions.clickOnElement(BtnId)
		GeneralValidations.verifyHoverOnDropdowns(BtnId)
		WebUI.selectOptionByValue(BtnId,value, false)
	}
	/***
	 * verifyFilterManufacturerApplied Verify current URL, filters groups numbers, if filter is selected, applied criteria and number of products in subheading
	 * @author fatma
	 * @param expectedURL
	 * @param filterNumber
	 * @param filtersGroupsNumber
	 * @param filterLinkParent
	 * @param filterName
	 */
	public static void verifyFilterManufacturerApplied (String expectedURL, Integer filtersGroupsNumber, TestObject filterLinkParent, String filterName) {
		TestObject pageSubHeading = findTestObject('Object Repository/SearchResultPage/h2_pageSubHeading')
		TestObject lastAppliedCriteriaLink = findTestObject('Filters/a_appliedCriteria')
		// Wait until overlay is disappeared
		WebUI.waitForElementNotVisible(findTestObject('Object Repository/General/div_overlay'), 2)
		// Verify current URL
		GeneralValidations.verifyCurrentPageURL(expectedURL)
		// Verify if filter is selected
		//		assert WebUI.getAttribute(filterLinkParent, "selected").contains("selected")
		// Verify applied criteria
		assert WebUI.getText(lastAppliedCriteriaLink).trim().toLowerCase().contains(filterName.toLowerCase())
		// Verify number of products in subheading
		String pageSubHeadingText = WebUI.getText(pageSubHeading)
		if(filtersGroupsNumber!=null) {
			// Verify filters groups numbers
			assert (FiltersActions.storeFiltersGroupsNumber() < filtersGroupsNumber)
			List<WebElement> selectedFilters = WebUI.findWebElements(findTestObject('Object Repository/Filters/span_FilterProductCount'),GlobalVariable.visiablityItemTimeOut)
			int filtersCount = 0
			for(WebElement element:selectedFilters) {
				int currentCount=Integer.parseInt(element.getText().replaceAll("[^0-9]", ""))
				filtersCount+=currentCount
			}
			assert filtersCount== Integer.parseInt(pageSubHeadingText.replaceAll("[^0-9]", ""))
		}
	}
	/***
	 * verifyHoverOnDropdowns
	 * @param BtnId
	 * @author fatma
	 */
	public static void verifyHoverOnManufacturerDropdowns(String BtnId) {
		GeneralActions.mouseOverOnElement(BtnId)
		GeneralValidations.verifyHoverOnSelectMenu(BtnId)
	}
}
