import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import actions.GeneralActions
import helpers.CheckOutHelpers
import helpers.GeneralHelpers
import helpers.HeaderHelpers
import helpers.QuickOrderHelpers
import internal.GlobalVariable
import validations.GeneralValidations
import validations.HeaderValidations
import validations.QuickOrderValidations


//open website & check title and url
GeneralHelpers.initScenario()
HeaderValidations.verifyCartItemsNumber(0)
HeaderValidations.verifyCartLabel('Cart')
HeaderHelpers.navigatingToQuickOrder()
GeneralValidations.verifyCurrentPageHeading('Object Repository/QuickOrder/span-QuickOrderHeading','QUICK');
List<String>listOfProducts = QuickOrderHelpers.fillingQuickOrderInputs()
List<String> names=listOfProducts.get(0);
List<String> Quantities=listOfProducts.get(1);
List<String> Prices=listOfProducts.get(2);
List<String> productID=listOfProducts.get(3);

QuickOrderHelpers.VerifyAddToCart()

GeneralValidations.verifyCartInfoInNav(WebUI.getText(findTestObject('Object Repository/Checkout/OrderSummery/total')),'5')
//GeneralHelpers.navigatingToCart()
CheckOutHelpers.navigateToCart(names.reverse(),Quantities.reverse(),Prices.reverse(),productID.reverse())
//check

GeneralActions.mouseOverOnElement(findTestObject('Object Repository/Checkout/OrderSummery/Btn-proceedToCheckout'))
//QuickOrderValidations.VerifyShadowWhenHoveringOnBtn(findTestObject('Object Repository/Checkout/OrderSummery/Btn-proceedToCheckout'))
WebUI.click(findTestObject('Object Repository/Checkout/OrderSummery/Btn-proceedToCheckout'));

///checkout-interstitial
GeneralHelpers.newPageIsOpened('/checkout-interstitial',"Checkout Interstitial - Cleaner's Supply")
assert WebUI.getText(findTestObject('Object Repository/Checkout/checkout Interstitial/div-heading')).contains('SECURE CHECKOUT')
//check side menu
WebUI.verifyElementChecked(findTestObject('Object Repository/Checkout/checkout Interstitial/radio-checkout-guest'), GlobalVariable.pageLoadTimeOut)

GeneralActions.mouseOverOnElement(findTestObject('Object Repository/Checkout/checkout Interstitial/btn-continue'))
QuickOrderValidations.VerifyShadowWhenHoveringOnBtn(findTestObject('Object Repository/Checkout/checkout Interstitial/btn-continue'))
WebUI.click(findTestObject('Object Repository/Checkout/checkout Interstitial/btn-continue'));

//checkout page
GeneralHelpers.newPageIsOpened('/checkout',"Checkout - Cleaner's Supply")
GeneralValidations.verifyCurrentPageHeading('Object Repository/General/h1-pageHeading','CHECKOUT')
CheckOutHelpers.proceedToReviewOrderFinish()

GeneralHelpers.newPageIsOpened('/checkout',"Checkout - Cleaner's Supply")
GeneralValidations.verifyCurrentPageHeading('Object Repository/General/h1-pageHeading','CHECKOUT')
String addressSection=WebUI.getText(findTestObject('Object Repository/Checkout/Review Checkout Info/address-section'))
addressSection.contains(GlobalVariable.CompanyName)
addressSection.contains(GlobalVariable.firstName)
addressSection.contains(GlobalVariable.lastName)
addressSection.contains(GlobalVariable.address1)
addressSection.contains(GlobalVariable.address2)
addressSection.contains(Integer.toString(GlobalVariable.zipCode))
addressSection.contains(GlobalVariable.city)
String phone=GlobalVariable.phone
String x=phone.substring(0,3)+"-"+phone.substring(3,6)+"-"+phone.substring(6,10)
addressSection.contains(x)
addressSection.contains('United States')
assert WebUI.getText(findTestObject('Object Repository/Checkout/Review Checkout Info/header-shipping-address')).equals('SHIPPING ADDRESS')
assert WebUI.getText(findTestObject('Object Repository/Checkout/Review Checkout Info/header-payment-method')).equals('PAYMENT METHOD')
String paymentSection=WebUI.getText(findTestObject('Object Repository/Checkout/Review Checkout Info/payment-section'))
assert paymentSection.contains(GlobalVariable.cardName)
String cardNumber=GlobalVariable.cardNumber

assert paymentSection.contains("**** "+cardNumber.substring(cardNumber.length() - 4))
assert paymentSection.contains('8/'+'2026'.substring('2026'.length() - 2))
GeneralValidations.verifyInputValue (findTestObject('Object Repository/Checkout/PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD Payment Method Section/po'), GlobalVariable.po)
GeneralValidations.verifyInputValue (findTestObject('Object Repository/Checkout/PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD PAYMENT METHOD Payment Method Section/comments'), GlobalVariable.comment)

GeneralActions.mouseOverOnElement(findTestObject('Object Repository/Checkout/Review Checkout Info/a-submit-order'))
QuickOrderValidations.VerifyShadowWhenHoveringOnBtn(findTestObject('Object Repository/Checkout/Review Checkout Info/a-submit-order'))
//WebUI.click(findTestObject('Object Repository/Checkout/Review Checkout Info/a-submit-order'));
WebUI.closeBrowser();