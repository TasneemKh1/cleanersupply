package validations

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class QuickOrderValidations {
	public static void verifyFieldBorder(TestObject input) {
		assert WebUI.getCSSValue(input, "border-color").equals('rgb(99, 99, 99)')
	}
	public static  TestObject verifyRemoveBtnIsDisplayed(int i) {
		List<WebElement> removeBtns=WebUI.findWebElements(findTestObject('Object Repository/QuickOrder/btn-remove'),GlobalVariable.visiablityItemTimeOut)
		assert WebUI.verifyElementVisible(WebUI.convertWebElementToTestObject(removeBtns.get(i)));
		return WebUI.convertWebElementToTestObject(removeBtns.get(i))
	}

	public static void verifyProductIsInStock(TestObject input) {
		assert WebUI.getText(input).contains('In Stock!')
	}
	public static void verifyDollarSignIsdisplayed(TestObject input) {
		assert WebUI.getText(input).contains('$');
	}
	public static void verifyTotalPriceisDisplayed(TestObject input,String expectedTotal) {
		assert WebUI.getText(input).replace(",",'').contains(expectedTotal);
	}
	public static void VerifyShadowWhenHoveringOnBtn(TestObject Btn) {
		assert WebUI.getCSSValue(Btn, 'box-shadow').equals('rgba(0, 0, 0, 0.3) 0px 0px 10px 2px')
	}
	public static void VerifyHoveringOnRemoveBtn(TestObject Btn) {
		assert WebUI.getCSSValue(Btn, "background").contains("rgb(157, 159, 162)")
		assert WebUI.getCSSValue(Btn, "box-shadow").equals('rgba(0, 0, 0, 0.3) 0px 0px 10px 2px')
	}
	public static void verifyReviewInfoInCheckout() {
		String addressSection=WebUI.getText(findTestObject('Object Repository/Checkout/Review Checkout Info/address-section'))
		assert addressSection.contains(GlobalVariable.CompanyName)
		assert addressSection.contains(GlobalVariable.firstName)
		assert addressSection.contains(GlobalVariable.lastName)
		assert addressSection.contains(GlobalVariable.address1)
		assert addressSection.contains(GlobalVariable.address2)
		assert addressSection.contains(Integer.toString(GlobalVariable.zipCode))
		assert addressSection.contains(GlobalVariable.city)
		String phone=GlobalVariable.phone
		String x=phone.substring(0,3)+"-"+phone.substring(3,6)+"-"+phone.substring(6,10)
		assert addressSection.contains(x)
		assert addressSection.contains('United States')
		assert WebUI.getText(findTestObject('Object Repository/Checkout/Review Checkout Info/header-shipping-address')).equals('SHIPPING ADDRESS')
		assert WebUI.getText(findTestObject('Object Repository/Checkout/Review Checkout Info/header-payment-method')).equals('PAYMENT METHOD')
		String paymentSection=WebUI.getText(findTestObject('Object Repository/Checkout/Review Checkout Info/payment-section'))
		assert paymentSection.contains(GlobalVariable.cardName)
		String cardNumber=GlobalVariable.cardNumber
		
		assert paymentSection.contains("**** "+cardNumber.substring(cardNumber.length() - 4))
		assert paymentSection.contains('8/'+'2026'.substring('2026'.length() - 2))
		GeneralValidations.verifyInputValue (findTestObject('Object Repository/Checkout/PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD Payment Method Section/po'), GlobalVariable.po)
		GeneralValidations.verifyInputValue (findTestObject('Object Repository/Checkout/PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD Payment Method Section/comments'), GlobalVariable.comment)
		
	}
}
