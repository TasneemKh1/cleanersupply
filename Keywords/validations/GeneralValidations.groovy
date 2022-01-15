package validations

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebElement

import com.kms.katalon.core.mobile.keyword.builtin.WaitForElementAttributeValueKeyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import actions.GeneralActions
import internal.GlobalVariable

public class GeneralValidations {
	/***
	 * Verify Current Page Title Is Not Empty
	 * @author fatma
	 */
	public static void verifyCurrentPageTitleIsNotEmpty() {
		assert !WebUI.getWindowTitle().isEmpty()
	}
	/***
	 * verify current page title match the expected title
	 * @param expectedTitle
	 * @author fatma
	 *  @author Tasneem
	 */
	public static void verifyCurrentPageTitleValue(String expectedTitle) {
		assert WebUI.getWindowTitle().contains(expectedTitle)
	}
	/***
	 * @param expectedTitle expectedTitle or part of expectedTitle
	 */
	public static void verifyCurrentPageTitle(String expectedTitle) {
		assert WebUI.getWindowTitle().toLowerCase().contains(expectedTitle.toLowerCase())
	}

	/***
	 * verify Current Url And Page Title
	 * @author fatma
	 */
	public static void verifyCurrentUrlAndPageTitle (String expectedUrl, String expectedTitle) {
		WebUI.waitForPageLoad(GlobalVariable.pageLoadTimeOut)
		GeneralValidations.verifyCurrentPageURL(expectedUrl)
		GeneralValidations.verifyCurrentPageTitle(expectedTitle)
	}

	/**
	 * Verify Current Page URL matched the passed url
	 * @param expectedURL expectedURL or part of expectedURL
	 * @author fatma
	 */
	public static void verifyCurrentPageURL(String expectedURL) {
		assert WebUI.getUrl().contains(expectedURL)
	}

	/**
	 * @author tasneem
	 * */
	public static void verifyCurrentPageHeading(String testObjID,String expectedHeading) {
		assert WebUI.getText(findTestObject(testObjID)).contains(expectedHeading)
	}
	/***
	 * verify Hover On element
	 * @param elementID elementID
	 * @param backGround background of element style
	 * @param color color of element style
	 * @param elementIDOfAttribute element ID Of Attribute
	 * @param attribute attribute of element
	 * @author fatma
	 */
	public static void verifyHover(String elementID,String backGround ,String color,String elementIDOfAttribute,String attribute) {
		TestObject Element = findTestObject(elementID)
		assert WebUI.getCSSValue(Element, 'color').contains(color)
		System.out.println(WebUI.getCSSValue(Element, 'color'))
		if (attribute != " " && elementIDOfAttribute != " " && backGround != " ") {
			TestObject ElementIDOfAttribute = findTestObject(elementIDOfAttribute)
			WebUI.waitForElementAttributeValue(ElementIDOfAttribute, "class", attribute, GlobalVariable.pageLoadTimeOut)
			assert WebUI.getAttribute(ElementIDOfAttribute, "class").contains(attribute)
			assert WebUI.getCSSValue(Element, 'background').contains(backGround)
			System.out.println(WebUI.getCSSValue(Element, 'background'))
		}
	}
	/****
	 * @author tasneem
	 * **/
	public static void verifyHoverOnDropdowns(String elementID) {
		TestObject Element = findTestObject(elementID)
		assert WebUI.getCSSValue(Element, 'color').contains('#fff')
		assert WebUI.getCSSValue(Element, 'background-color').contains('#58585a')
		assert WebUI.getCSSValue(Element, 'text-decoration').contains('none')
	}


	/***
	 * verify Breadcrump Text
	 * @param element id of element
	 * @param BreadCrumbText the text of breadcrump
	 * @author fatma
	 */
	public static void verifyBreadcrump(String elementID,String BreadcrumbText) {
		TestObject Element = findTestObject(elementID)
		assert WebUI.getText(Element).contains(BreadcrumbText);
	}
	/***
	 * verify Title Of Heading
	 * @param Title the title of heading section
	 * @author fatma
	 */
	public static void verifyTitleOfHeading(String Title) {
		assert WebUI.getText(findTestObject('Object Repository/CategoryPage/h1_TitleOfHeading')).contains(Title);
	}
	/***
	 * verifyCartProductData verify all data of products that added is true
	 * @param productName the name of product
	 * @param quantity the quantity of product
	 * @param price the price of product
	 * @param sku the sku of product
	 * @author fatma
	 */
	public static  void verifyCartProductData(List productName,List quantity ,List price,List sku) {
		List<WebElement> titleOfProduct = WebUI.findWebElements(findTestObject('Object Repository/Cart/List_TitleOfProductsCart'), GlobalVariable.visiablityItemTimeOut)
		List<WebElement> priceOfProduct = WebUI.findWebElements(findTestObject('Object Repository/Cart/List_PriceOfProductsCart'), GlobalVariable.visiablityItemTimeOut)
		List<WebElement> stockNotificationOfProduct =  WebUI.findWebElements(findTestObject('Object Repository/Cart/ListStockNotificationOfProduct'), GlobalVariable.visiablityItemTimeOut)
		List<WebElement> QuantityOfProducts =  WebUI.findWebElements(findTestObject('Object Repository/Cart/List_QuantityOfProductsCart'), GlobalVariable.visiablityItemTimeOut)
		List<WebElement> totalPriceOfProducts =  WebUI.findWebElements(findTestObject('Object Repository/Cart/List_TotalPriceOfProducts'), GlobalVariable.visiablityItemTimeOut)
		List<WebElement> skuNumberOfProducts =  WebUI.findWebElements(findTestObject('Object Repository/Cart/List_SkuNumberOfProduct'), GlobalVariable.visiablityItemTimeOut)
		List<WebElement> rows_table =  WebUI.findWebElements(findTestObject('Object Repository/Cart/ListOfRowsInCart'), GlobalVariable.visiablityItemTimeOut)

		for(int i = 0; i < rows_table.size(); ++i) {
			System.out.println(titleOfProduct.get(i).getText())
			assert titleOfProduct.get(i).getText().contains(productName[i]);
			System.out.println(priceOfProduct.get(i).getText().replace('$', ''))
			assert priceOfProduct.get(i).getText().replace('$', '').contains(price[i]);
		    System.out.println(QuantityOfProducts.get(i).getAttribute('value').replace('$', ''))
			assert QuantityOfProducts.get(i).getAttribute('value').contains(quantity[i]);
			System.out.println(totalPriceOfProducts.get(i).getText().replace('$', ''))
			//let quantity=1
			double totalPrice=QuantityOfProducts.get(i).getAttribute('value').toDouble() * priceOfProduct.get(i).getText().replace('$', '').toDouble()
			assert totalPriceOfProducts.get(i).getText().replace('$', '').toDouble().equals(totalPrice);
			System.out.println(skuNumberOfProducts.get(i).getText())
			assert skuNumberOfProducts.get(i).getText().contains(sku[i]);
			System.out.println(stockNotificationOfProduct.get(i).getText())
			assert stockNotificationOfProduct.get(i).getText().contains('In Stock!');

			//SubTotalSummary

			System.out.println(Double.parseDouble(WebUI.getText(findTestObject('Object Repository/Cart/td_SubTotalSummary')).replace('$', '').replace(',', '')))
			assert Double.parseDouble(WebUI.getText(findTestObject('Object Repository/Cart/td_SubTotalSummary')).replace('$', '').replace(',', ''))==(totalPrice);
			//Total
			System.out.println(Double.parseDouble(WebUI.getText(findTestObject('Object Repository/Cart/td_Total')).replace('$', '')))
			assert Double.parseDouble(WebUI.getText(findTestObject('Object Repository/Cart/td_Total')).replace('$', ''))==(totalPrice);
			//NumberOfSubTotal
			System.out.println(Double.parseDouble(WebUI.getText(findTestObject('Object Repository/Cart/td_NumberOfSubTotalItem')).replaceAll("[^0-9]", "")))
			assert Integer.parseInt(WebUI.getText(findTestObject('Object Repository/Cart/td_NumberOfSubTotalItem')).replaceAll("[^0-9]",""))==(QuantityOfProducts.get(i).getAttribute('value').toInteger());

		}
	}
	/***
	 * Verify if input value matches the typed one
	 * @author moham
	 * @param inputTestObject
	 * @param expectedValue
	 */
	public static void verifyInputValue (TestObject inputTestObject, String expectedValue) {
		assert WebUI.getAttribute(inputTestObject, "value").trim().equals(expectedValue)
	}
	/***
	 * verify Action On Button
	 * @param elementID Id of element
	 * @param attribute attribute of element
	 * @param expectedValue expectedValue of element
	 */
	public static void verifyActionOnButton(String elementID,String attribute,String expectedValue) {
		TestObject Element = findTestObject(elementID)
		assert WebUI.getCSSValue(Element, attribute).contains(expectedValue)
		System.out.println(WebUI.getCSSValue(Element, attribute))
	}

	public static void verifyCartInfoInNav(String total,String productsCountTxt) {
		WebUI.verifyElementPresent(findTestObject('Object Repository/Header/Cart/total-price'), GlobalVariable.pageLoadTimeOut)
		assert WebUI.getText(findTestObject('Object Repository/Header/Cart/total-price')).contains(total);
		assert WebUI.getText(findTestObject('Object Repository/Header/Cart/products-count')).contains(productsCountTxt);
	}

	/***
	 * verify Click On Add To Cart Button
	 * @author fatma
	 */
	public static void verifyClickOnAddToCartButton() {
		TestObject AddToCartButton = findTestObject('Object Repository/ProductPage/button_addToCart')
		GeneralActions.clickOnElement('Object Repository/ProductPage/button_addToCart')
		//		WebUI.waitForElementAttributeValue(AddToCartButton, "class", 'change-status', GlobalVariable.pageLoadTimeOut)
		assert WebUI.getCSSValue(AddToCartButton, 'cursor').contains('not-allowed')
		assert WebUI.getCSSValue(AddToCartButton, 'background').contains('rgb(109, 110, 113) none repeat scroll 0% 0% / auto padding-box border-box')
		assert WebUI.getCSSValue(AddToCartButton, 'color').contains('rgba(255, 255, 255, 1)')
		//		WebUI.waitForElementNotHasAttribute(AddToCartButton, "class", 'change-status', GlobalVariable.pageLoadTimeOut)
	}
	/***
	 * verifyHoverOnSelectMenu
	 * @param elementID
	 * @author fatma
	 */
	public static void verifyHoverOnSelectMenu(String elementID) {
		TestObject Element = findTestObject(elementID)
		if(WebUI.waitForElementAttributeValue(AddToCartButton, "class", 'change-status', GlobalVariable.pageLoadTimeOut)) 
		{assert WebUI.getCSSValue(AddToCartButton, 'cursor').contains('not-allowed')
		assert WebUI.getCSSValue(AddToCartButton, 'background').contains('rgb(109, 110, 113) none repeat scroll 0% 0% / auto padding-box border-box')
		assert WebUI.getCSSValue(AddToCartButton, 'color').contains('rgba(255, 255, 255, 1)')
		}		
//		WebUI.waitForElementNotHasAttribute(AddToCartButton, "class", 'change-status', GlobalVariable.pageLoadTimeOut)

	}
}