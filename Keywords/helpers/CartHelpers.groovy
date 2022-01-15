package helpers

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.text.DecimalFormat

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

import internal.GlobalVariable

public class CartHelpers {
	public static ArrayList makeListReadyForCartAndCheckout (boolean isDouble, List list) {
		List newList = new ArrayList()
		for (int i = list.size() - 1; i >= 0; i--) {
			if (isDouble) {
				newList.push((list.get(i)).toString())
			} else {
				newList.push(list.get(i))
			}
		}
		return newList
	}

	public static void verifyCartDropdown (List titles, List quantities, List prices, List skus) {
		List <WebElement> productsNamesElements = WebUI.findWebElements(findTestObject('Object Repository/CartDropdown/a_productName'), GlobalVariable.visiablityItemTimeOut)
		List <WebElement> productsQuantitesElements = WebUI.findWebElements(findTestObject('Object Repository/CartDropdown/td_productQuantity'), GlobalVariable.visiablityItemTimeOut)
		List <WebElement> productsPricesElements = WebUI.findWebElements(findTestObject('Object Repository/CartDropdown/span_productPrice'), GlobalVariable.visiablityItemTimeOut)
		List <WebElement> productsSkuElements = WebUI.findWebElements(findTestObject('Object Repository/CartDropdown/span_productSku'), GlobalVariable.visiablityItemTimeOut)
		List <WebElement> productsTotalElements = WebUI.findWebElements(findTestObject('Object Repository/CartDropdown/td_productTotal'), GlobalVariable.visiablityItemTimeOut)
		TestObject itemsNumberObject = findTestObject('Object Repository/CartDropdown/span_itemsNumber')
		TestObject totalAmountObject = findTestObject('Object Repository/CartDropdown/span_totalAmount')
		verifyCartInfo(titles, quantities, prices, skus, productsNamesElements, productsQuantitesElements, productsPricesElements, productsSkuElements, productsTotalElements, itemsNumberObject, totalAmountObject, totalAmountObject, false)
	}

	public static void verifyCartinShoppingCartPage (List titles, List quantities, List prices, List skus) {
		List <WebElement> productsNamesElements = WebUI.findWebElements(findTestObject('Object Repository/Cart/List_TitleOfProductsCart'), GlobalVariable.visiablityItemTimeOut)
		List <WebElement> productsQuantitesElements = WebUI.findWebElements(findTestObject('Object Repository/Cart/List_QuantityOfProductsCart'), GlobalVariable.visiablityItemTimeOut)
		List <WebElement> productsPricesElements = WebUI.findWebElements(findTestObject('Object Repository/Cart/List_PriceOfProductsCart'), GlobalVariable.visiablityItemTimeOut)
		List <WebElement> productsSkuElements = WebUI.findWebElements(findTestObject('Object Repository/Cart/List_SkuNumberOfProduct'), GlobalVariable.visiablityItemTimeOut)
		List <WebElement> productsTotalElements = WebUI.findWebElements(findTestObject('Object Repository/Cart/List_TotalPriceOfProducts'), GlobalVariable.visiablityItemTimeOut)
		TestObject itemsNumberObject = findTestObject('Object Repository/Cart/td_NumberOfSubTotalItem')
		TestObject totalAmountObject = findTestObject('Object Repository/Cart/td_SubTotalSummary')
		TestObject totalInShoppingCardPage = findTestObject('Object Repository/Cart/td_Total')
		GeneralHelpers.newPageIsOpened("shopping-cart", "Shopping Cart")
		assert (WebUI.getText(findTestObject('Object Repository/CartDropdown/h1_pageHeading')).trim().toLowerCase()).contains("Shopping Cart".toLowerCase())
		verifyCartInfo(titles, quantities, prices, skus, productsNamesElements, productsQuantitesElements, productsPricesElements, productsSkuElements, productsTotalElements, itemsNumberObject, totalAmountObject, totalInShoppingCardPage, true)
	}

	public static void verifyCartInfo (
			List titles,
			List quantities,
			List prices,
			List skus,
			List productsNamesElements,
			List productsQuantitesElements,
			List productsPricesElements,
			List productsSkuElements,
			List productsTotalElements,
			TestObject itemsNumberObject,
			TestObject totalAmountObject,
			TestObject totalInShoppingCardPage,
			boolean isQuantityInput
	) {
		double totalAmount = 0
		DecimalFormat formatter = new DecimalFormat("#,###.00");
		for (int i = 0; i < titles.size(); i++) {
			// title
			TestObject expectedTitleObject = WebUI.convertWebElementToTestObject(productsNamesElements.get(i))
			String expectedTitle = WebUI.getText(expectedTitleObject).trim()
			assert (expectedTitle.toLowerCase()).equals(titles.get(i).toLowerCase())
			// quantity
			TestObject expectedQuantityObject = WebUI.convertWebElementToTestObject(productsQuantitesElements.get(i))
			String expectedQuantity
			// check id quantity is value or text
			if (isQuantityInput) {
				expectedQuantity = WebUI.getAttribute(expectedQuantityObject, "value").trim()
			} else {
				expectedQuantity = WebUI.getText(expectedQuantityObject).trim()
			}
			assert expectedQuantity.equals(quantities.get(i))
			// price
			TestObject expectedPriceObject = WebUI.convertWebElementToTestObject(productsPricesElements.get(i))
			String expectedPrice = WebUI.getText(expectedPriceObject).trim().replace('$', '')
			assert expectedPrice.equals(prices.get(i))
			// sku
			TestObject expectedSkuObject = WebUI.convertWebElementToTestObject(productsSkuElements.get(i))
			String expectedSku = WebUI.getText(expectedSkuObject)
			assert expectedSku.contains(skus.get(i))
			// products total
			TestObject expectedProductsTotalObject = WebUI.convertWebElementToTestObject(productsTotalElements.get(i))
			String expectedProductsTotal = WebUI.getText(expectedProductsTotalObject).trim().replace('$', '')
			double productsTotal = Double.parseDouble(prices.get(i)) * Integer.parseInt(quantities.get(i))
			String formattedActualProductsTotal = formatter.format(Double.parseDouble(prices.get(i)) * Integer.parseInt(quantities.get(i)));
			assert expectedProductsTotal.equals(formattedActualProductsTotal)
			totalAmount = totalAmount + productsTotal
			// items number
			String itemsNumber = WebUI.getText(itemsNumberObject)
			itemsNumber = itemsNumber.replaceAll("[^\\d.]", "")
			assert itemsNumber.equals((titles.size()).toString())
		}
		// sub total amount
		String expextedTotalAmount = WebUI.getText(totalAmountObject).trim().replace('$', '')
		assert expextedTotalAmount.equals(formatter.format(totalAmount))
		// total amount
		assert (WebUI.getText(totalInShoppingCardPage).trim().replace('$', '')).equals(expextedTotalAmount)
	}
}
