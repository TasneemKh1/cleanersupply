package helpers
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.DecimalFormat

import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import actions.GeneralActions
import actions.Navigations
import internal.GlobalVariable
import validations.GeneralValidations
import validations.QuickOrderValidations


public class CheckOutHelpers {
	/***
	 * navigate to cart Scenario 
	 * @param productName the name of product
	 * @param quantity the quantity of product
	 * @param price the price of product
	 * @param sku the sku of product
	 * @author fatma
	 */
	public static void navigateToCart(List productName,List quantity ,List price,List sku) {
		//Navigate to the cart
		Navigations.navigateToCart();
		GeneralHelpers.newPageIsOpened(GlobalVariable.cartUrl,"Shopping Cart - Cleaner's Supply")
		GeneralValidations.verifyTitleOfHeading('SHOPPING CART CONTINUE SHOPPING')
		GeneralValidations.verifyCartProductData(productName,quantity,price,sku)
	}
	/***
	 * Verify Checkout Interstitial Text Page
	 * @author fatma
	 */
	public static void verifyCheckoutInterstitialTextPage() {
		assert WebUI.getText(findTestObject('Object Repository/Checkout/div_HeadingTitleOfCheckoutInterstitialPage')).contains('SECURE CHECKOUT');
		assert WebUI.getText(findTestObject('Object Repository/Checkout/div_TitleOfCheckOutAsGuest')).contains('Checkout as a guest or register'.toUpperCase());
	}
	public static void verifyCheckoutTextPage() {
		assert WebUI.getText(findTestObject('Object Repository/Checkout/div_HeadingTitleOfCheckoutInterstitialPage')).contains('SECURE CHECKOUT');
		assert WebUI.getText(findTestObject('Object Repository/Checkout/h1_headingTitleOfChekoutPage')).contains('Checkout'.toUpperCase());
	}
	/***
	 * verifyMyCartData Verify all data of products in my cart section
	 * @param productName the name of product
	 * @param quantity the quantity of product
	 * @param price the price of product
	 * @param sku the sku of product
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
		double totalPrice = 0.0;
		for(int i = 0; i <= rows_table.size()-1; ++i) {
			System.out.println(titleOfProduct.get(i).getText())
			assert titleOfProduct.get(i).getText().contains(productName[i]);
			System.out.println(priceOfProduct.get(i).getText().replace('$', ''))
			assert priceOfProduct.get(i).getText().replace('$', '').contains(price[i]);
			System.out.println(QuantityOfProducts.get(i).getAttribute('value').replace('$', ''))
			//			assert QuantityOfProducts.get(i).getAttribute('value').contains(quantity[i]);
			System.out.println(totalPriceOfProducts.get(i).getText().replace('$', ''))
			totalPrice+=QuantityOfProducts.get(i).getAttribute('value').toInteger() * priceOfProduct.get(i).getText().replace('$', '').toDouble()
			//			assert totalPriceOfProducts.get(i).getText().replace('$', '').toDouble().equals(totalPrice);
			System.out.println(skuNumberOfProducts.get(i).getText())
			assert skuNumberOfProducts.get(i).getText().contains(sku[i]);
			System.out.println(stockNotificationOfProduct.get(i).getText())
			assert stockNotificationOfProduct.get(i).getText().contains('In Stock!');
			//NumberOfSubTotal
			System.out.println(Double.parseDouble(WebUI.getText(findTestObject('Object Repository/Cart/td_NumberOfSubTotalItem')).replaceAll("[^0-9]", "")))
			assert Integer.parseInt(WebUI.getText(findTestObject('Object Repository/Cart/td_NumberOfSubTotalItem')).replaceAll("[^0-9]",""))==(rows_table.size());

		}
		//SubTotalSummary
		System.out.println(Double.parseDouble(WebUI.getText(findTestObject('Object Repository/Cart/td_SubTotalSummary')).replace('$', '').replace(",", "")))
		//			assert Double.parseDouble(WebUI.getText(findTestObject('Object Repository/Cart/td_SubTotalSummary')).replace('$', ''))==(totalPrice);

		//Estimated Total
		System.out.println("estimatedTax: "+WebUI.getText(findTestObject('Object Repository/Cart/td_EstematedTax')))
		String estimatedTax = WebUI.getText(findTestObject('Object Repository/Cart/td_EstematedTax'));
		//shipping
		System.out.println(WebUI.getText(findTestObject('Object Repository/Cart/td_Shipping')))

		String shipping = WebUI.getText(findTestObject('Object Repository/Cart/td_Shipping'));
		String expectedTotal = new DecimalFormat("#.00").format(totalPrice)
		if (estimatedTax !='T.B.D.' && shipping =='FREE') {
			System.out.println("estimatedTax"+estimatedTax +"  "+totalPrice)
			Double total=Double.parseDouble(estimatedTax.replace('$', '')) + totalPrice
			assert Double.parseDouble(WebUI.getText(findTestObject('Object Repository/Cart/td_Total')).replace('$', '').replace(',', ''))==(total);

			//TotalPriceOfOrder
			String NewTotal = new DecimalFormat("#.00").format(total)
			//System.out.println(Double.parseDouble(WebUI.getText(findTestObject('Object Repository/Cart/span_priceOfOrderTotal')).replace('$', '')))
			assert WebUI.getText(findTestObject('Object Repository/Cart/td_Total')).replaceAll(',',"").equals('$'+NewTotal)
			//assert WebUI.getText(findTestObject('Object Repository/Cart/span_priceOfOrderTotal')).replaceAll(',',"").equals('$'+NewTotal)

		}else if (estimatedTax =='T.B.D.' && shipping !='FREE'){
			Double total=Double.parseDouble(shipping) + totalPrice
			String NewTotal = new DecimalFormat("#.00").format(total)
			assert WebUI.getText(findTestObject('Object Repository/Cart/td_Total')).replaceAll(',',"").equals('$'+NewTotal)
			assert WebUI.getText(findTestObject('Object Repository/Cart/span_priceOfOrderTotal')).replaceAll(',',"").equals('$'+NewTotal)
		}else if(estimatedTax =='T.B.D.' && shipping =='FREE') {
			Double total= totalPrice
			String NewTotal = new DecimalFormat("#.00").format(total)
			assert WebUI.getText(findTestObject('Object Repository/Cart/td_Total')).replaceAll(',',"").equals('$'+NewTotal)
			assert WebUI.getText(findTestObject('Object Repository/Cart/span_priceOfOrderTotal')).replaceAll(',',"").equals('$'+NewTotal)
		}else{
			Double total= totalPrice+Double.parseDouble(estimatedTax)+Double.parseDouble(estimatedTax)
			String NewTotal = new DecimalFormat("#.00").format(total)
			assert WebUI.getText(findTestObject('Object Repository/Cart/td_Total')).replaceAll(',',"").equals('$'+NewTotal)
			assert WebUI.getText(findTestObject('Object Repository/Cart/span_priceOfOrderTotal')).replaceAll(',',"").equals('$'+NewTotal)
		}
		//Total
		//			assert Double.parseDouble(WebUI.getText(findTestObject('Object Repository/Cart/td_Total')).replace('$', '').replace(',', ''))==(totalPrice);
		assert WebUI.getText(findTestObject('Object Repository/Cart/td_Total')).replaceAll(',',"").equals('$'+expectedTotal)
	}

	/***
	 * proceed To CheckOut Scenario
	 * @author fatma
	 */
	public static void proceedToCheckOut() {
		GeneralActions.mouseOverOnElement('Object Repository/Cart/button_ProceedToCheckout')
		GeneralValidations.verifyActionOnButton('Object Repository/Cart/button_ProceedToCheckout',"box-shadow","rgba(0, 0, 0, 0.3) 0px 0px 10px 2px")
		GeneralActions.clickOnElement('Object Repository/Cart/button_ProceedToCheckout')
		GeneralHelpers.newPageIsOpened(GlobalVariable.checkoutInterstitialUrl, GlobalVariable.titleOfCheckoutInterstitialCleanerSupply)
		CheckOutHelpers.verifyCheckoutInterstitialTextPage();
	}

	/***
	 * select checkout As Guest
	 * @param productName the name of product
	 * @param quantity the quantity of product
	 * @param price the price of product
	 * @param sku the sku of product
	 * @autor fatma
	 */
	public static void selectCheckoutAsGuest(List productName,List quantity ,List price,List sku) {
		GeneralActions.clickOnElement('Object Repository/Checkout/span_CheckoutAsGuestRadio')
		GeneralValidations.verifyActionOnButton('Object Repository/Checkout/span_CheckoutAsGuestRadio',"border-color","rgb(82, 36, 127)")
		//		CheckOutHelpers.verifyMyCartData(["THERMAL BPA-FREE 21# RECEIPT ROLLS W/BACK PRINT - 160'/ROLL - 50/CASE - BLUE W/WHITE HANGER"], ['1'], ['89.99'],['RCT210BL'])
		CheckOutHelpers.verifyMyCartData(productName,quantity,price,sku)
		GeneralActions.mouseOverOnElement('Object Repository/Checkout/button_ContinueButton')
		GeneralValidations.verifyActionOnButton('Object Repository/Checkout/button_ContinueButton',"box-shadow","rgba(0, 0, 0, 0.3) 0px 0px 10px 2px")
		GeneralActions.clickOnElement('Object Repository/Checkout/button_ContinueButton')
		GeneralHelpers.newPageIsOpened(GlobalVariable.checkoutUrl, GlobalVariable.titleOfCheckoutPage)
		CheckOutHelpers.verifyCheckoutTextPage();
	}

	public static void EnterValuesForShippingAddress(){
		TypeInFieldAndWerifyValue(findTestObject('Object Repository/Checkout/shipping Address Section/input-Company'), GlobalVariable.CompanyName);
		TypeInFieldAndWerifyValue(findTestObject('Object Repository/Checkout/shipping Address Section/firstName'), GlobalVariable.firstName);
		TypeInFieldAndWerifyValue(findTestObject('Object Repository/Checkout/shipping Address Section/lastName'), GlobalVariable.lastName);
		TypeInFieldAndWerifyValue(findTestObject('Object Repository/Checkout/shipping Address Section/address line1'), GlobalVariable.address1);
		TypeInFieldAndWerifyValue(findTestObject('Object Repository/Checkout/shipping Address Section/address line2'), GlobalVariable.address2);
		TypeInFieldAndWerifyValue(findTestObject('Object Repository/Checkout/shipping Address Section/zipCode'), Integer.toString(GlobalVariable.zipCode));
		TypeInFieldAndWerifyValue(findTestObject('Object Repository/Checkout/shipping Address Section/city'), GlobalVariable.city);
		GeneralActions.chooseFromSelector('Object Repository/Checkout/shipping Address Section/btn-state','Object Repository/Checkout/shipping Address Section/select-State','California');
		SetTextInFieldAndVerifyValue(findTestObject('Object Repository/Checkout/shipping Address Section/input_Phone_shp1-daytimePhoneNumber'), GlobalVariable.phone);
		WebUI.sendKeys(findTestObject('Object Repository/Checkout/shipping Address Section/phoneExtension'), Keys.chord(Keys.TAB))
		WebUI.sendKeys(findTestObject('Object Repository/Checkout/shipping Address Section/phoneExtension') , GlobalVariable.ext)
		WebUI.sendKeys(findTestObject('Object Repository/Checkout/shipping Address Section/phoneExtension'), Keys.chord(Keys.TAB))

		//assert WebUI.getCSSValue(findTestObject('Object Repository/Checkout/shipping Address Section/phoneExtension'), 'value').equals(GlobalVariable.ext)
		//WebUI.sendKeys(findTestObject('Object Repository/Checkout/shipping Address Section/phone-Extension'), Keys.chord(Keys.BACK_SPACE))


		//TypeInFieldAndWerifyValue(findTestObject('Object Repository/Checkout/shipping Address Section/phone-Extension'),GlobalVariable.ext);
		WebUI.setText(findTestObject('Object Repository/Checkout/shipping Address Section/input_Email_shp1-email'),GlobalVariable.email);
		GeneralValidations.verifyInputValue(findTestObject('Object Repository/Checkout/shipping Address Section/input_Email_shp1-email'),GlobalVariable.email);
		GeneralActions.clickOnElement('Object Repository/Checkout/shipping Address Section/checkbox-signUp')
	}

	public static void EnterValuesForPaymentMethod(){
		TypeInFieldAndWerifyValue(findTestObject('Object Repository/Checkout/PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD Payment Method Section/cardName'), GlobalVariable.cardName)
		TypeInFieldAndWerifyValue(findTestObject('Object Repository/Checkout/PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD Payment Method Section/cardNumber'), GlobalVariable.cardNumber)
		TypeInFieldAndWerifyValue(findTestObject('Object Repository/Checkout/PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD Payment Method Section/cvv'), Integer.toString(GlobalVariable.cvv))
		GeneralActions.chooseFromSelector('Object Repository/Checkout/PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD Payment Method Section/btn-expirationMonth','Object Repository/Checkout/PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD Payment Method Section/select-ExpirationMonth','8')
		GeneralActions.chooseFromSelector('Object Repository/Checkout/PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD Payment Method Section/btn-expirationYear','Object Repository/Checkout/PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD Payment Method Section/select-ExpirationYear','2026')
		//WebUI.verifyElementPresent(findTestObject('Object Repository/Checkout/PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD Payment Method Section/sectionDisappeared'), GlobalVariable.visiablityItemTimeOut)
		//GeneralActions.clickOnElement('Object Repository/Checkout/PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD Payment Method Section/checkbox-billing address')

		//WebUI.verifyElementNotPresent(findTestObject('Object Repository/Checkout/PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD Payment Method Section/sectionDisappeared'), GlobalVariable.visiablityItemTimeOut)
		TypeInFieldAndWerifyValue(findTestObject('Object Repository/Checkout/PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD Payment Method Section/po'), GlobalVariable.po)
		TypeInFieldAndWerifyValue(findTestObject('Object Repository/Checkout/PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD Payment Method Section/comments'), GlobalVariable.comment)
	}

	public static void proceedToReviewOrderFinish() {
		EnterValuesForShippingAddress()
		EnterValuesForPaymentMethod()
		hoverAndClick('Object Repository/Checkout/PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD Payment Method Section/a-reviewOrderBtn')

	}

	public static void TypeInFieldAndWerifyValue(TestObject inputTestObject, String expectedValue){
		GeneralActions.typeIntoInputField(inputTestObject, expectedValue)
		GeneralValidations.verifyInputValue (inputTestObject, expectedValue)
	}

	public static void SetTextInFieldAndVerifyValue(TestObject inputTestObject, String expectedValue){
		//replaceFirst("(\\d{3})(\\d{3})(\\d+)","$1-$2-$3");
		GeneralActions.SetTextForInputsFields(inputTestObject, expectedValue)
		String x=expectedValue.substring(0,3)+"-"+expectedValue.substring(3,6)+"-"+expectedValue.substring(6,10)
		GeneralValidations.verifyInputValue (inputTestObject, x)
	}
	public static void hoverAndClick(String testObjId) {
		GeneralActions.mouseOverOnElement(testObjId)
		QuickOrderValidations.VerifyShadowWhenHoveringOnBtn(findTestObject(testObjId))
		GeneralActions.clickOnElement(testObjId)
	}
}
