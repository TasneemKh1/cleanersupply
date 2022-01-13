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
import org.openqa.selenium.WebElement

import internal.GlobalVariable

public class ProductValidations {
	public static double formatPrice (String priceString) {
		return Double.parseDouble(priceString.trim().substring(1))
	}

	public static double formatPercentage (String percentageString) {
		return Double.parseDouble(percentageString.trim().substring(0, percentageString.length() - 1))
	}


	public static double verifyProductPrice(String minPrice, String maxPrice) {
		TestObject productPriceTestObject = findTestObject('Object Repository/ProductPage/span_productPrice')
		double actualPrice = formatPrice(WebUI.getText(productPriceTestObject))
		assert (Double.parseDouble(minPrice) <= actualPrice)
		assert (Double.parseDouble(maxPrice) >= actualPrice)
		return actualPrice
	}

	public static void verifyProductPriceList(String minPriceList, String maxPriceList) {
		TestObject productPriceListTestObject = findTestObject('Object Repository/ProductPage/span_productListPrice')
		double actualPriceList = formatPrice(WebUI.getText(productPriceListTestObject))
		assert (Double.parseDouble(minPriceList) <= actualPriceList)
		assert (Double.parseDouble(maxPriceList) >= actualPriceList)
	}

	public static void verifybreadcrumbIsVisible() {
		WebUI.verifyElementVisible(findTestObject('Object Repository/ProductPage/a_breadcrumbLink'))
	}

	public static void verifyNumberOfAvailableColors (String colorsNumber) {
		List<WebElement> avialableColorsWebElement = WebUI.findWebElements(findTestObject('Object Repository/ProductPage/a_productColorsOptions'), GlobalVariable.visiablityItemTimeOut)
		assert avialableColorsWebElement.size().equals(Integer.parseInt(colorsNumber))
	}

	public static void verifyVolumePricing (double productPrice) {
		List<WebElement> productPricesElements = WebUI.findWebElements(findTestObject('Object Repository/ProductPage/span_productPrices'), GlobalVariable.visiablityItemTimeOut)
		List<WebElement> productSavesElements = WebUI.findWebElements(findTestObject('Object Repository/ProductPage/span_productSaves'), GlobalVariable.visiablityItemTimeOut)
		for (int i = 1; i < productPricesElements.size(); i++) {
			String price =  WebUI.getText(WebUI.convertWebElementToTestObject(productPricesElements.get(i)))
			String save = WebUI.getText(WebUI.convertWebElementToTestObject(productSavesElements.get(i)))
			double expectedPrice = productPrice * (100 - formatPercentage(save)) / 100
			System.out.println((double) Math.round(expectedPrice * 100) / 100)
			assert formatPrice(price) == (double) Math.round(expectedPrice * 100) / 100
		}
	}
}