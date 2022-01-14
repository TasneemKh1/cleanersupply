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

import helpers.ProductHelpers

import org.openqa.selenium.WebElement

import internal.GlobalVariable

public class ProductValidations {
	/***
	 * @author moham
	 * @param priceString
	 * @return double
	 */
	public static double formatPrice (String priceString) {
		return Double.parseDouble(priceString.trim().substring(1))
	}

	/***
	 * @author moham
	 * @param percentageString
	 * @return double
	 */
	public static double formatPercentage (String percentageString) {
		return Double.parseDouble(percentageString.trim().substring(0, percentageString.length() - 1))
	}

	/***
	 * Verify if product price within the price range
	 * @author moham
	 * @param minPrice
	 * @param maxPrice
	 * @return doubel
	 */
	public static double verifyProductPrice(String minPrice, String maxPrice) {
		TestObject productPriceTestObject = findTestObject('Object Repository/ProductPage/span_productPrice')
		double actualPrice = formatPrice(WebUI.getText(productPriceTestObject))
		assert (Double.parseDouble(minPrice) <= actualPrice)
		assert (Double.parseDouble(maxPrice) >= actualPrice)
		return actualPrice
	}

	/***
	 * Verify if product list price within the list price range
	 * @author moham
	 * @param minPriceList
	 * @param maxPriceList
	 */
	public static void verifyProductPriceList(String minPriceList, String maxPriceList) {
		TestObject productPriceListTestObject = findTestObject('Object Repository/ProductPage/span_productListPrice')
		double actualPriceList = formatPrice(WebUI.getText(productPriceListTestObject))
		assert (Double.parseDouble(minPriceList) <= actualPriceList)
		assert (Double.parseDouble(maxPriceList) >= actualPriceList)
	}

	/***
	 * @author moham
	 */
	public static void verifybreadcrumbIsVisible() {
		WebUI.verifyElementVisible(findTestObject('Object Repository/ProductPage/a_breadcrumbLink'))
	}

	/***
	 * Verify if the product colors number matches the one mentioned in the result page
	 * @author moham
	 * @param colorsNumber
	 */
	public static void verifyNumberOfAvailableColors (String colorsNumber) {
		List<WebElement> avialableColorsWebElement = WebUI.findWebElements(findTestObject('Object Repository/ProductPage/a_productColorsOptions'), GlobalVariable.visiablityItemTimeOut)
		assert avialableColorsWebElement.size().equals(Integer.parseInt(colorsNumber))
	}

	/***
	 * Verify if the volume pricing table is correctly calculated
	 * @author moham
	 * @param productPrice
	 */
	public static void verifyVolumePricing (double productPrice) {
		List<WebElement> productPricesElements = WebUI.findWebElements(findTestObject('ProductPage/td_productPrices'), GlobalVariable.visiablityItemTimeOut)
		List<WebElement> productSavesElements = WebUI.findWebElements(findTestObject('ProductPage/td_productSaves'), GlobalVariable.visiablityItemTimeOut)
		for (int i = 1; i < productPricesElements.size(); i++) {
			String price =  WebUI.getText(WebUI.convertWebElementToTestObject(productPricesElements.get(i)))
			String save = WebUI.getText(WebUI.convertWebElementToTestObject(productSavesElements.get(i)))
			double expectedPrice = productPrice * (100 - formatPercentage(save)) / 100
			assert formatPrice(price) == (double) Math.round(expectedPrice * 100) / 100
		}
	}

	/***
	 * Verify if the sku number is correctly reflected in the URL
	 * @author moham
	 * @return sString
	 */
	public static String verifyProductSKU () {
		String productSKU = WebUI.getText(findTestObject('Object Repository/ProductPage/span_productSKU')).trim()
		assert WebUI.getUrl().endsWith(productSKU)
		return productSKU
	}

	/***
	 * @author moham
	 */
	public static void verifyIfXLargeFilterIsSelected () {
		ProductHelpers.verifyIfFilterButtonIsSelected(findTestObject('Object Repository/ProductPage/a_xLargeLink'))
	}

	/***
	 * @author moham
	 */
	public static void verifyIfLargeFilterIsSelected () {
		ProductHelpers.verifyIfFilterButtonIsSelected(findTestObject('Object Repository/ProductPage/a_largeLink'))
	}

	/***
	 * @author moham
	 */
	public static void verifyIfGreenFilterIsSelected () {
		ProductHelpers.verifyIfFilterButtonIsSelected(findTestObject('Object Repository/ProductPage/a_greenProductsLink'))
	}

	/***
	 * @author moham
	 */
	public static void verifyIfRoyalBlueFilterIsSelected () {
		ProductHelpers.verifyIfFilterButtonIsSelected(findTestObject('Object Repository/ProductPage/a_royalBlueProducts'))
	}

	/***
	 * Verify if price is updated based on the entered quantity
	 * @author moham
	 * @param discountedPrice
	 */
	public static void verifyIfDiscountIsApplied (double discountedPrice) {
		TestObject productPriceTestObject = findTestObject('Object Repository/ProductPage/span_productPrice')
		double actualPrice = formatPrice(WebUI.getText(productPriceTestObject))
		assert discountedPrice.equals(actualPrice)
	}

	/***
	 * Verify if stock notification showing that product is available
	 * @author moham
	 */
	public static void verifyStockNotification() {
		assert WebUI.getAttribute(findTestObject('Object Repository/ProductPage/div_stockNotification'), "class").contains("in-stock")
	}

	/***
	 * @author moham
	 * @param quantity
	 */
	public static void verifyQuantityInputValue (int quantity) {
		GeneralValidations.verifyInputValue(findTestObject('Object Repository/ProductPage/input_quantity'), quantity.toString())
	}

	/***
	 * Verify if total amount is correctly calculated in the cart label
	 * @author moham
	 * @param prices
	 * @param quantities
	 */
	public static void verifyProductTotalPriceInCart (List prices, List quantities) {
		double totalPrice = 0.00
		for (int i = 0; i < prices.size(); i ++) {
			totalPrice += (double) (prices.get(i) * quantities.getAt(i))
		}
		String totalPriceString = '$'.toString() +  String.format("%.2f", totalPrice)
		HeaderValidations.verifyCartLabel(totalPriceString)
	}

	/***
	 * Verify if product title is updated when filter is applied
	 * @author moham
	 * @param filterName
	 * @return String
	 */
	public static String verifyProductTitle (String filterName) {
		TestObject productTitle = findTestObject('Object Repository/ProductPage/h1_productTitle')
		assert WebUI.getText(productTitle).toLowerCase().contains(filterName.toLowerCase())
		return WebUI.getText(productTitle).trim()
	}

	/***
	 * Verify if color text is updated after changing the product color
	 * @author moham
	 * @param expectedColor
	 */
	public static void verifySelectedColorText (String expectedColor) {
		TestObject SelectedColorText = findTestObject('Object Repository/ProductPage/span_selectedColorText')
		assert WebUI.getText(SelectedColorText).trim().toLowerCase().equals(expectedColor.toLowerCase())
	}
}
