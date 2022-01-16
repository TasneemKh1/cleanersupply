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
import helpers.CartHelpers


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
WebUI.mouseOver(findTestObject('Object Repository/Header/li_cartLink'))
WebUI.verifyElementVisible(findTestObject('Object Repository/CartDropdown/div_cartDropdown'))
//CartHelpers.verifyCartDropdown(names.reverse(),Quantities.reverse(),Prices.reverse(),productID.reverse())

CheckOutHelpers.navigateToCart(names.reverse(),Quantities.reverse(),Prices.reverse(),productID.reverse())

GeneralActions.mouseOverOnElement(findTestObject('Object Repository/Checkout/OrderSummery/Btn-proceedToCheckout'))
QuickOrderValidations.VerifyShadowWhenHoveringOnBtn(findTestObject('Object Repository/Checkout/OrderSummery/Btn-proceedToCheckout'))
WebUI.click(findTestObject('Object Repository/Checkout/OrderSummery/Btn-proceedToCheckout'));

CheckOutHelpers.verifyMyCartData(names.reverse(),Quantities.reverse(),Prices.reverse(),productID.reverse())

//checkout-interstitial
GeneralHelpers.newPageIsOpened('/checkout-interstitial',"Checkout Interstitial - Cleaner's Supply")
assert WebUI.getText(findTestObject('Object Repository/Checkout/checkout Interstitial/div-heading')).contains('SECURE CHECKOUT')
//check side menu

WebUI.verifyElementChecked(findTestObject('Object Repository/Checkout/checkout Interstitial/radio-checkout-guest'), GlobalVariable.pageLoadTimeOut)
 
GeneralActions.mouseOverOnElement(findTestObject('Object Repository/Checkout/checkout Interstitial/btn-continue'))
QuickOrderValidations.VerifyShadowWhenHoveringOnBtn(findTestObject('Object Repository/Checkout/checkout Interstitial/btn-continue'))
WebUI.click(findTestObject('Object Repository/Checkout/checkout Interstitial/btn-continue'));


//QuickOrderHelpers.checkButtonStyleOnHoveringthenClickOnIT('Object Repository/Checkout/checkout Interstitial/btn-continue')

CheckOutHelpers.verifyMyCartData(names.reverse(),Quantities.reverse(),Prices.reverse(),productID.reverse())


GeneralHelpers.newPageIsOpened('/checkout',"Checkout - Cleaner's Supply")
GeneralValidations.verifyCurrentPageHeading('Object Repository/General/h1-pageHeading','CHECKOUT')
CheckOutHelpers.proceedToReviewOrderFinish()


//CheckOutHelpers.verifyMyCartData(names.reverse(),Quantities.reverse(),Prices.reverse(),productID.reverse())

GeneralHelpers.newPageIsOpened('/checkout',"Checkout - Cleaner's Supply")
GeneralValidations.verifyCurrentPageHeading('Object Repository/General/h1-pageHeading','CHECKOUT')
QuickOrderValidations.verifyReviewInfoInCheckout()

//GeneralActions.mouseOverOnElement(findTestObject('Object Repository/Checkout/Review Checkout Info/a-submit-order'))
//QuickOrderValidations.VerifyShadowWhenHoveringOnBtn(findTestObject('Object Repository/Checkout/Review Checkout Info/a-submit-order'))
////WebUI.click(findTestObject('Object Repository/Checkout/Review Checkout Info/a-submit-order'));
WebUI.closeBrowser();