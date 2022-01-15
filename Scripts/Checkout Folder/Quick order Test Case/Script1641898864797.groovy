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
CheckOutHelpers.navigateToCart(names.reverse(),Quantities.reverse(),Prices.reverse(),productID.reverse())

//QuickOrderHelpers.checkButtonStyleOnHoveringthenClickOnIT('Object Repository/Checkout/OrderSummery/Btn-proceedToCheckout')
//--------------------cart--------------------------------------
//GeneralHelpers.navigatingToCart()
//CheckOutHelpers.navigateToCart(names.reverse(),Quantities.reverse(),Prices.reverse(),productID.reverse())
////check
//// ----------- Click on 'Proceed To Checkout' -----------
//CheckOutHelpers.proceedToCheckOut()
//// ----------- Select 'Checkut As Guest' and move to the next step. ---------------
//CheckOutHelpers.selectCheckoutAsGuest(names.reverse(),Quantities.reverse(),Prices.reverse(),productID.reverse())
// ----------- endCart--------------------

GeneralActions.mouseOverOnElement(findTestObject('Object Repository/Checkout/OrderSummery/Btn-proceedToCheckout'))
QuickOrderValidations.VerifyShadowWhenHoveringOnBtn(findTestObject('Object Repository/Checkout/OrderSummery/Btn-proceedToCheckout'))
WebUI.click(findTestObject('Object Repository/Checkout/OrderSummery/Btn-proceedToCheckout'));

//checkout-interstitial
GeneralHelpers.newPageIsOpened('/checkout-interstitial',"Checkout Interstitial - Cleaner's Supply")
assert WebUI.getText(findTestObject('Object Repository/Checkout/checkout Interstitial/div-heading')).contains('SECURE CHECKOUT')
//check side menu

WebUI.verifyElementChecked(findTestObject('Object Repository/Checkout/checkout Interstitial/radio-checkout-guest'), GlobalVariable.pageLoadTimeOut)
 
GeneralActions.mouseOverOnElement(findTestObject('Object Repository/Checkout/checkout Interstitial/btn-continue'))
QuickOrderValidations.VerifyShadowWhenHoveringOnBtn(findTestObject('Object Repository/Checkout/checkout Interstitial/btn-continue'))
WebUI.click(findTestObject('Object Repository/Checkout/checkout Interstitial/btn-continue'));


//QuickOrderHelpers.checkButtonStyleOnHoveringthenClickOnIT('Object Repository/Checkout/checkout Interstitial/btn-continue')
//checkout page
GeneralHelpers.newPageIsOpened('/checkout',"Checkout - Cleaner's Supply")
GeneralValidations.verifyCurrentPageHeading('Object Repository/General/h1-pageHeading','CHECKOUT')
CheckOutHelpers.proceedToReviewOrderFinish()

GeneralHelpers.newPageIsOpened('/checkout',"Checkout - Cleaner's Supply")
GeneralValidations.verifyCurrentPageHeading('Object Repository/General/h1-pageHeading','CHECKOUT')
QuickOrderValidations.verifyReviewInfoInCheckout()

//GeneralActions.mouseOverOnElement(findTestObject('Object Repository/Checkout/Review Checkout Info/a-submit-order'))
//QuickOrderValidations.VerifyShadowWhenHoveringOnBtn(findTestObject('Object Repository/Checkout/Review Checkout Info/a-submit-order'))
////WebUI.click(findTestObject('Object Repository/Checkout/Review Checkout Info/a-submit-order'));
WebUI.closeBrowser();