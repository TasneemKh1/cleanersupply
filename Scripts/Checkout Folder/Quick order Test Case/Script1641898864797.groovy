import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import helpers.QuickOrderHelpers
import helpers.GeneralHelpers
import helpers.HeaderHelpers
import internal.GlobalVariable
import validations.GeneralValidations


//open website & check title and url
GeneralHelpers.initScenario()
HeaderHelpers.navigatingToQuickOrder()
GeneralValidations.verifyCurrentPageHeading('Object Repository/QuickOrder/span-QuickOrderHeading','QUICK');
QuickOrderHelpers.fillingQuickOrderInputs()
QuickOrderHelpers.VerifyAddToCart()

GeneralValidations.verifyCartInfoInNav(WebUI.getText(findTestObject('Object Repository/Checkout/OrderSummery/total')),'5')
GeneralHelpers.navigatingToCart()
//check


