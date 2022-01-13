package actions

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

import helpers.FiltersHelpers
import internal.GlobalVariable

public class FiltersActions {
	/***
	 * @author moham
	 * @return filters groups number
	 */
	public static int storeFiltersGroupsNumber () {
		List<WebElement> filtersGroups = WebUI.findWebElements(findTestObject('Object Repository/Filters/div_filtersGroups'), GlobalVariable.visiablityItemTimeOut)
		return filtersGroups.size()
	}

	/***
	 * @author moham
	 * @return packing products number
	 */
	public static Integer storePackingProductsNumber () {
		TestObject categoryLinkTestObject = findTestObject('Filters/a_packagingProductsLink')
		return FiltersHelpers.storeFilteredProductNumber(categoryLinkTestObject)
	}

	/***
	 * @author moham
	 * @return packing products link Url
	 */
	public static String storePackingProductsLinkUrl () {
		TestObject categoryLinkTestObject = findTestObject('Filters/a_packagingProductsLink')
		return FiltersHelpers.storeFilteredProductLinkUrl(categoryLinkTestObject)
	}

	/***
	 * @author moham
	 */
	public static void clickOnPackingProductsLink () {
		WebUI.click(findTestObject('Filters/a_packagingProductsLink'))
	}

	/***
	 * @author moham
	 * @return plastic bags number
	 */
	public static Integer storePlasticBagsNumber () {
		TestObject categoryLinkTestObject = findTestObject('Object Repository/Filters/a_plasticBagsLink')
		return FiltersHelpers.storeFilteredProductNumber(categoryLinkTestObject)
	}

	/***
	 * @author moham
	 * @return plastic bags link Url
	 */
	public static String storePlasticBagsLinkUrl () {
		TestObject categoryLinkTestObject = findTestObject('Object Repository/Filters/a_plasticBagsLink')
		return FiltersHelpers.storeFilteredProductLinkUrl(categoryLinkTestObject)
	}

	/***
	 * @author moham
	 */
	public static void clickOnPlasticBagsLink () {
		WebUI.click(findTestObject('Object Repository/Filters/a_plasticBagsLink'))
	}

	/***
	 * @author moham
	 */
	public static void expandColorCard () {
		WebUI.click(findTestObject('Object Repository/Filters/div_colorFilterCard'))
		WebUI.waitForElementVisible(findTestObject('Object Repository/Filters/ul_colorOptionsList'), GlobalVariable.visiablityItemTimeOut)
	}

	/***
	 * @author moham
	 * @return green products number
	 */
	public static Integer storeGreenProductsNumber () {
		TestObject categoryLinkTestObject = findTestObject('Object Repository/Filters/a_greenProductsLink')
		return FiltersHelpers.storeFilteredProductNumber(categoryLinkTestObject)
	}

	/***
	 * @author moham
	 * @return green products link Url
	 */
	public static String storeGreenProductsLinkUrl () {
		TestObject categoryLinkTestObject = findTestObject('Object Repository/Filters/a_greenProductsLink')
		return FiltersHelpers.storeFilteredProductLinkUrl(categoryLinkTestObject)
	}

	/***
	 * @author moham
	 */
	public static void clickOnGreenProductsLink () {
		WebUI.click(findTestObject('Object Repository/Filters/a_greenProductsLink'))
	}
}
