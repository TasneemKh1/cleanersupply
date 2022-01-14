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
}
