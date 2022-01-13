package helpers
import actions.GeneralActions
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

import actions.GeneralActions
import actions.Navigations

import internal.GlobalVariable
import org.openqa.selenium.WebElement
import validations.GeneralValidations
import internal.GlobalVariable


public class CheckOutHelpers {
	public static void checkOutSenario() {
		//Navigate to the cart
		Navigations.navigateToCart();
		//Click on 'Proceed To Checkout'
		GeneralValidations.verifyCartProductData([
			"THERMAL BPA-FREE 21# RECEIPT ROLLS W/BACK PRINT - 160'/ROLL - 50/CASE - BLUE W/WHITE HANGER"
		], ['89.99'], ['89.99'],['RCT210BL'])
		//		TestObject processedToCheckOutButton = findTestObject('Object Repository/Cart/button_ProceedToCheckout')
		//		WebUI.click(processedToCheckOutButton)
		//		GeneralHelpers.newPageIsOpened(GlobalVariable.cartUrl, ' Shopping Cart ')
		//		GeneralValidations.verifyTitleOfHeading(' Shopping Cart ')

	}
	/***
	 * Verify Checkout Interstitial Text Page
	 * @author fatma
	 */
	public static void verifyCheckoutInterstitialTextPage() {
		assert WebUI.getText(findTestObject('Object Repository/Checkout/div_HeadingTitleOfCheckoutInterstitialPage')).contains('SECURE CHECKOUT');
		assert WebUI.getText(findTestObject('Object Repository/Checkout/div_TitleOfCheckOutAsGuest')).contains('Checkout as a guest or register'.toUpperCase());
	}
	
	/***
	 * verifyMyCartData Verify all data of products in my cart section
	 * @param productName
	 * @param quantity
	 * @param price
	 * @param sku
	 * @author fatma
	 */
	public static void verifyMyCartData(List productName,List quantity ,List price,List sku) {
		List<WebElement> titleOfProduct = WebUI.findWebElements(findTestObject('Object Repository/Cart/List_TitleOfProductsCart'), GlobalVariable.visiablityItemTimeOut)
		List<WebElement> priceOfProduct = WebUI.findWebElements(findTestObject('Object Repository/Checkout/List_PriceOfMyCart'), GlobalVariable.visiablityItemTimeOut)
		List<WebElement> stockNotificationOfProduct =  WebUI.findWebElements(findTestObject('Object Repository/Cart/ListStockNotificationOfProduct'), GlobalVariable.visiablityItemTimeOut)
		List<WebElement> QuantityOfProducts =  WebUI.findWebElements(findTestObject('Object Repository/Checkout/List_QuantityOfMyCart'), GlobalVariable.visiablityItemTimeOut)
		List<WebElement> totalPriceOfProducts =  WebUI.findWebElements(findTestObject('Object Repository/Checkout/List_SubTotalPriceOFMyCart'), GlobalVariable.visiablityItemTimeOut)
		List<WebElement> skuNumberOfProducts =  WebUI.findWebElements(findTestObject('Object Repository/Cart/List_SkuNumberOfProduct'), GlobalVariable.visiablityItemTimeOut)
		List<WebElement> rows_table =  WebUI.findWebElements(findTestObject('Object Repository/Cart/ListOfRowsInCart'), GlobalVariable.visiablityItemTimeOut)

		for(int i = 0; i < rows_table.size(); ++i) {
			System.out.println(titleOfProduct.get(i).getText())
			assert titleOfProduct.get(i).getText().contains(productName[i]);
			System.out.println(priceOfProduct.get(i).getText().replace('$', ''))
			assert priceOfProduct.get(i).getText().replace('$', '').contains(price[i]);
			//			System.out.println(QuantityOfProducts.get(i).getAttribute('value').replace('$', ''))
			//			assert QuantityOfProducts.get(i).getAttribute('value').contains(quantity[i]);
			System.out.println(totalPriceOfProducts.get(i).getText().replace('$', ''))
			//let quantity=1
			double totalPrice=1 * priceOfProduct.get(i).getText().replace('$', '').toDouble()
			assert totalPriceOfProducts.get(i).getText().replace('$', '').toDouble().equals(totalPrice);
			System.out.println(skuNumberOfProducts.get(i).getText())
			assert skuNumberOfProducts.get(i).getText().contains(sku[i]);
			System.out.println(stockNotificationOfProduct.get(i).getText())
			assert stockNotificationOfProduct.get(i).getText().contains('In Stock!');
			
			//SubTotalSummary
			System.out.println(Double.parseDouble(WebUI.getText(findTestObject('Object Repository/Cart/td_SubTotalSummary')).replace('$', '')))
			assert Double.parseDouble(WebUI.getText(findTestObject('Object Repository/Cart/td_SubTotalSummary')).replace('$', ''))==(totalPrice);
			//Total
			System.out.println(Double.parseDouble(WebUI.getText(findTestObject('Object Repository/Cart/td_Total')).replace('$', '')))
			assert Double.parseDouble(WebUI.getText(findTestObject('Object Repository/Cart/td_Total')).replace('$', ''))==(totalPrice);
			//NumberOfSubTotal
			System.out.println(Double.parseDouble(WebUI.getText(findTestObject('Object Repository/Cart/td_NumberOfSubTotalItem')).replaceAll("[^0-9]", "")))
			assert Integer.parseInt(WebUI.getText(findTestObject('Object Repository/Cart/td_NumberOfSubTotalItem')).replaceAll("[^0-9]",""))==(1);
			
		}
	}

	/***
	 * 
	 */
	public static void proceedToCheckOut() {
		GeneralActions.mouseOverOnElement('Object Repository/Cart/button_ProceedToCheckout')
		GeneralValidations.verifyActionOnButton('Object Repository/Cart/button_ProceedToCheckout',"box-shadow","rgba(0, 0, 0, 0.3) 0px 0px 10px 2px")
		GeneralActions.clickOnElement('Object Repository/Cart/button_ProceedToCheckout')
		GeneralHelpers.newPageIsOpened(GlobalVariable.checkoutInterstitialUrl, GlobalVariable.titleOfCheckoutInterstitialCleanerSupply)
		CheckOutHelpers.verifyCheckoutInterstitialTextPage();
		GeneralActions.clickOnElement('Object Repository/Checkout/span_CheckoutAsGuestRadio')
		GeneralValidations.verifyActionOnButton('Object Repository/Checkout/span_CheckoutAsGuestRadio',"border-color","rgb(82, 36, 127)")
	    CheckOutHelpers.verifyMyCartData([
			"THERMAL BPA-FREE 21# RECEIPT ROLLS W/BACK PRINT - 160'/ROLL - 50/CASE - BLUE W/WHITE HANGER"
		], ['89.99'], ['89.99'],['RCT210BL'])
		CheckOutHelpers.verifyMyCartData([
			"THERMAL BPA-FREE 21# RECEIPT ROLLS W/BACK PRINT - 160'/ROLL - 50/CASE - BLUE W/WHITE HANGER"
		], ['89.99'], ['89.99'],['RCT210BL'])
		GeneralActions.mouseOverOnElement('Object Repository/Cart/button_ProceedToCheckout')
		GeneralValidations.verifyActionOnButton('Object Repository/Cart/button_ProceedToCheckout',"box-shadow","rgba(0, 0, 0, 0.3) 0px 0px 10px 2px")
		GeneralActions.clickOnElement('Object Repository/Cart/button_ProceedToCheckout')
	}
	public static void EnterValuesForShippingAddress(){
		GeneralActions.typeIntoInputField(findTestObject('Object Repository/Checkout/shipping Address Section/input-Company'), GlobalVariable.CompanyName)
		GeneralActions.typeIntoInputField(findTestObject('Object Repository/Checkout/shipping Address Section/firstName'), GlobalVariable.CompanyName)

		
	}
	
}