import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import actions.FiltersActions
import actions.GeneralActions
import actions.SelectCategoriesActions
import helpers.CheckOutHelpers
import helpers.FiltersHelpers
import helpers.GeneralHelpers
import internal.GlobalVariable
import validations.FiltersValidations
import validations.GeneralValidations
import validations.HeaderValidations
// ----------- Navigate to https://www.cleanersupply.com/----------
GeneralHelpers.initScenario();
HeaderValidations.verifyCartItemsNumber(0)
HeaderValidations.verifyCartLabel('Cart')
// ----------- Hover on 'Tags & Forms' header items.     ----------
SelectCategoriesActions.hoverOnTagsAndFormMenu();
GeneralValidations.verifyHover("Object Repository/Header/a_tagsAndForm","rgb(255, 255, 255)","rgba(82, 36, 127, 1)","Object Repository/Header/ul_tagsAndForm","open-desktop");
// ----------- Select 'Computers & Registers' category.  ----------
SelectCategoriesActions.hoverOnComputerAndRegisterTab();
GeneralValidations.verifyHover("Object Repository/CategoryPage/a_ComputerAndRegister"," ","rgba(82, 36, 127, 1)"," "," ");
SelectCategoriesActions.clickOnComputerAndRegisterTab();
GeneralHelpers.newPageIsOpened(GlobalVariable.computerAndRegisterPageUrl, GlobalVariable.titleOfComputerRegisterPage)
GeneralValidations.verifyBreadcrump('Object Repository/CategoryPage/li_BreadcrumpOfComputerAndRegisterPAge', GlobalVariable.breadcrumpOfComputerAndRegister)
GeneralValidations.verifyTitleOfHeading(GlobalVariable.headingTitleOFComputerAndRegisterPage)
// ----------- From the manufacturer section, select the 'Casio' manufacturer and select 'SP1000' Model. -------
//ManufacterNumber = FiltersActions.storeManufacturerNumber()
ManufacterLinkUrl = 'https://www.cleanersupply.com/Tags-Forms/Computer-Register/?Manufacturer=Casio'
filtersGroupsNumber = FiltersActions.storeFiltersGroupsNumber()
//packagingProductsLinkUrl = FiltersActions.storePackingProductsLinkUrl()
FiltersValidations.verifyManufacturerFilterContent()
GeneralActions.clickOnElement('Object Repository/Filters/ManufacturerFilter/span_ManufacturerOptionSelect')
FiltersHelpers.verifyHoverOnManufacturerDropdowns('Object Repository/Filters/ManufacturerFilter/a_CasioSelected')
GeneralActions.clickOnElement('Object Repository/Filters/ManufacturerFilter/a_CasioSelected')
FiltersValidations.verifyCasioManufacturerFilterApplied(ManufacterLinkUrl, filtersGroupsNumber, 'Casio')

TestObject product=findTestObject('Object Repository/ProductPage/a_ComputerProduct')
WebUI.click(product)
// --------- Navigate to the resulted product page ---------
//firstProductMap = ProductHelpers.storeFirstProductManufacturerDetails()
//ProductActions.clickOnViewDetailsButtonOnManufacturerFilter()
//GeneralHelpers.verifyCurrentUrlAndPageTitle(firstProductMap.get('productUrl'), firstProductMap.get('productTitle'))
//double productPrice = ProductHelpers.verifypriceAndListPriceAndVolumePrice(firstProductMap.get('minPrice'), firstProductMap.get(
//		'maxPrice'), firstProductMap.get('minListPrice'), firstProductMap.get('maxListPrice'))
//ProductValidations.verifyNumberOfAvailableColors(firstProductMap.get('availableColors'))
//ProductValidations.verifybreadcrumbIsVisible()
//ProductValidations.verifyProductSKU()
//--click on add to cart button --
GeneralValidations.verifyClickOnAddToCartButton()
// ----------- Navigate to the cart. ----------------
CheckOutHelpers.navigateToCart(["THERMAL BPA-FREE 21# RECEIPT ROLLS W/BACK PRINT - 160'/ROLL - 50/CASE - BLUE W/WHITE HANGER"], ['1'], ['89.99'],['RCT210BL'])
// ----------- Click on 'Proceed To Checkout' -----------
CheckOutHelpers.proceedToCheckOut(["THERMAL BPA-FREE 21# RECEIPT ROLLS W/BACK PRINT - 160'/ROLL - 50/CASE - BLUE W/WHITE HANGER"], ['1'], ['89.99'],['RCT210BL'])
// ----------- Select 'Checkut As Guest' and move to the next step. ---------------
CheckOutHelpers.selectCheckoutAsGuest(["THERMAL BPA-FREE 21# RECEIPT ROLLS W/BACK PRINT - 160'/ROLL - 50/CASE - BLUE W/WHITE HANGER"], ['1'], ['89.99'],['RCT210BL'])
// ----------- 
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
