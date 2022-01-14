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
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElementDecl.GLOBAL

import org.junit.experimental.theories.suppliers.TestedOn
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

import internal.GlobalVariable

public class ProductActions {
	/***
	 * Remove the dollar sign from price string
	 * @author moham
	 * @param priceTestObject
	 * @return String
	 */
	public static String formatPrice (TestObject priceTestObject) {
		return WebUI.getText(priceTestObject).trim().substring(1)
	}

	/***
	 * Return product details from the result page before navigating to product page
	 * @author moham
	 * @return HashMap
	 */
	public static HashMap storeFirstProductDetails () {
		HashMap<String, String> firstProductMap = new HashMap<>()
		TestObject productLinkTestObject = findTestObject('Object Repository/ProductPage/a_firstProductLink')
		TestObject productTilteTestObject = findTestObject('Object Repository/ProductPage/h2_firstProductTitle')
		TestObject productColorsAvilabilityTestObject = findTestObject('Object Repository/ProductPage/span_firstProductColorsAvailability')
		List<WebElement> pricesGroupsWebElement = WebUI.findWebElements(findTestObject('Object Repository/ProductPage/span_firstProductPrices'), GlobalVariable.visiablityItemTimeOut)
		List<WebElement> listPricesGroupsWebElement = WebUI.findWebElements(findTestObject('Object Repository/ProductPage/span_firstProductListPrices'), GlobalVariable.visiablityItemTimeOut)

		String productUrl = WebUI.getAttribute(productLinkTestObject, "href")
		String productTitle = WebUI.getText(productTilteTestObject).trim()
		String minPrice = formatPrice(WebUI.convertWebElementToTestObject(pricesGroupsWebElement.get(0)))
		String maxPrice = formatPrice(WebUI.convertWebElementToTestObject(pricesGroupsWebElement.get(1)))
		String minListPrice = formatPrice(WebUI.convertWebElementToTestObject(listPricesGroupsWebElement.get(0)))
		String maxListPrice = formatPrice(WebUI.convertWebElementToTestObject(listPricesGroupsWebElement.get(1)))
		String availableColors = WebUI.getText(productColorsAvilabilityTestObject).split("\\s+").getAt(2).trim()

		firstProductMap.put("productUrl", productUrl)
		firstProductMap.put("productTitle", productTitle)
		firstProductMap.put("minPrice", minPrice)
		firstProductMap.put("maxPrice", maxPrice)
		firstProductMap.put("minListPrice", minListPrice)
		firstProductMap.put("maxListPrice", maxListPrice)
		firstProductMap.put("availableColors", availableColors)

		return firstProductMap
	}

	/***
	 * @author moham
	 */
	public static void clickOnViewDetailsButton () {
		WebUI.click(findTestObject('Object Repository/ProductPage/button_firstProductViewDetails'))
	}

	/***
	 * @author moham
	 */
	public static void clickOnXlargeButton () {
		WebUI.click(findTestObject('Object Repository/ProductPage/a_xLargeLink'))
	}

	/***
	 * @author moham
	 */
	public static void clickOnLargeButton () {
		WebUI.click(findTestObject('Object Repository/Header/input_search'))
		WebUI.scrollToElement(findTestObject('Object Repository/ProductPage/h1_productTitle'), GlobalVariable.visiablityItemTimeOut)
		WebUI.click(findTestObject('Object Repository/ProductPage/a_largeLink'))
	}

	/***
	 * @author moham
	 */
	public static void clickOnGreenButton () {
		WebUI.click(findTestObject('Object Repository/ProductPage/a_greenProductsLink'))
	}

	/***
	 * @author moham
	 */
	public static void clickOnRoyalBlueButton () {
		WebUI.click(findTestObject('Object Repository/ProductPage/a_royalBlueProducts'))
	}

	/***
	 * @author moham
	 */
	public static String storeDiscountedPrice() {
		return WebUI.getText(findTestObject('Object Repository/ProductPage/td_productDiscountedPrice'))
	}

	/***
	 * @author moham
	 * @param quantity
	 */
	public static void typeIntoQuantityInput (int quantity) {
		GeneralActions.typeIntoInputField(findTestObject('Object Repository/ProductPage/input_quantity'), Keys.BACK_SPACE + quantity.toString())
		WebUI.click(findTestObject('Object Repository/ProductPage/div_stockNotification'))
	}

	/***
	 * @author moham
	 */
	public static void clickOnAddToCartButton () {
		TestObject addToCartButton = findTestObject('Object Repository/ProductPage/button_add-to-cart')
		WebUI.click(addToCartButton)
		WebUI.waitForElementNotHasAttribute(addToCartButton, "disabled", GlobalVariable.visiablityItemTimeOut)
	}
}
