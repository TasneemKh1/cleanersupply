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

import actions.ProductActions
import internal.GlobalVariable
import validations.HeaderValidations
import validations.ProductValidations
import org.openqa.selenium.WebElement

public class ProductHelpers {
	/***
	 * Verify if selected filter button has "selected" class
	 * @author moham
	 * @param buttonTestObject
	 */
	public static void verifyIfFilterButtonIsSelected (TestObject buttonTestObject) {
		assert WebUI.getAttribute(buttonTestObject, "class").contains("selected")
	}

	/***
	 * Verify price and list price and volume prices
	 * @author moham
	 * @param minPrice
	 * @param maxPrice
	 * @param minListPrice
	 * @param maxListPrice
	 * @return double
	 */
	public static double verifypriceAndListPriceAndVolumePrice (String minPrice, String maxPrice, String minListPrice, String maxListPrice) {
		double actualPrice =  ProductValidations.verifyProductPrice(minPrice, maxPrice)
		ProductValidations.verifyProductPriceList(minListPrice, maxListPrice)
		ProductValidations.verifyVolumePricing(actualPrice)
		return actualPrice
	}

	/***
	 * update quantity, verify update quantity, add product to cart and verify cart icon
	 * @author moham
	 * @param productQuantity
	 * @param productPrice
	 * @param itemsNumber
	 * @param productsPrices
	 * @param productsQuantities
	 */
	public static void verifyUpdateQuantityAndAddToCart (int productQuantity, double productPrice, int itemsNumber, List productsPrices, List productsQuantities) {
		ProductActions.typeIntoQuantityInput(productQuantity)
		ProductValidations.verifyQuantityInputValue(productQuantity)
		ProductValidations.verifyIfDiscountIsApplied(productPrice)
		ProductActions.clickOnAddToCartButton()
		HeaderValidations.verifyCartItemsNumber(itemsNumber)
		ProductValidations.verifyProductTotalPriceInCart(productsPrices, productsQuantities)
		ProductValidations.verifyQuantityInputValue(1)
	}

	/***
	 * @author moham
	 * @param filterName
	 */
	public static void verifySkuAndTitle (String filterName) {
		ProductValidations.verifyProductSKU()
		ProductValidations.verifyProductTitle(filterName)
	}

	/***
	 * @author moham
	 * @param filterName
	 */
	public static void StockNotificationAndSelectedColorText (String filterName) {
		ProductValidations.verifyStockNotification()
		ProductValidations.verifySelectedColorText(filterName)
	}

	/***
	 * update products titles, products prices, products quantities and products SKU lists
	 * @author moham
	 * @param productsTitles
	 * @param productsPrices
	 * @param productsQuantities
	 * @param productsSKU
	 * @param productTitle
	 * @param productPrice
	 * @param productQuantity
	 * @param productSKU
	 */
	public static void updateProductsLists (List productsTitles, List productsPrices, List productsQuantities, List productsSKU, String productTitle, double productPrice, int productQuantity, String productSKU) {
		productsTitles.push(productTitle)
		productsPrices.push(productPrice)
		productsQuantities.push(productQuantity)
		productsSKU.push(productSKU)
	}
	/***
	 * storeFirstProductManufacturerDetails
	 * @return HashMap
	 * @author fatma
	 */
	public static HashMap storeFirstProductManufacturerDetails () {
		HashMap<String, String> firstProductMap = new HashMap<>()
		TestObject productLinkTestObject = findTestObject('Object Repository/ProductPage/div_FirstProductManufacturer')
		TestObject productTilteTestObject = findTestObject('Object Repository/ProductPage/h1_TitleOfProductManufacturer')
//		TestObject productColorsAvilabilityTestObject = findTestObject('Object Repository/ProductPage/span_firstProductColorsAvailability')
		List<WebElement> pricesGroupsWebElement = WebUI.findWebElements(findTestObject('Object Repository/ProductPage/span_priceOfFirstProductManu'), GlobalVariable.visiablityItemTimeOut)
		List<WebElement> listPricesGroupsWebElement = WebUI.findWebElements(findTestObject('Object Repository/ProductPage/span_ListPriceOfProductManu'), GlobalVariable.visiablityItemTimeOut)

		String productUrl = WebUI.getAttribute(productLinkTestObject, "href")
		String productTitle = WebUI.getText(productTilteTestObject).trim()
		String minPrice = ProductActions.formatPrice(WebUI.convertWebElementToTestObject(pricesGroupsWebElement.get(0)))
		String maxPrice = ProductActions.formatPrice(WebUI.convertWebElementToTestObject(pricesGroupsWebElement.get(1)))
		String minListPrice = ProductActions.formatPrice(WebUI.convertWebElementToTestObject(listPricesGroupsWebElement.get(0)))
//		String maxListPrice = ProductActions.formatPrice(WebUI.convertWebElementToTestObject(listPricesGroupsWebElement.get(1)))
//		String availableColors = WebUI.getText(productColorsAvilabilityTestObject).split("\\s+").getAt(2).trim()

		firstProductMap.put("productUrl", productUrl)
		firstProductMap.put("productTitle", productTitle)
		firstProductMap.put("minPrice", minPrice)
		firstProductMap.put("maxPrice", maxPrice)
		firstProductMap.put("minListPrice", minListPrice)
//		firstProductMap.put("maxListPrice", maxListPrice)
//		firstProductMap.put("availableColors", availableColors)

		return firstProductMap
	}
}
