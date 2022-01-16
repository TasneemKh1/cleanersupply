import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import actions.FiltersActions
import actions.GeneralActions
import actions.ProductActions
import actions.SelectCategoriesActions
import helpers.CartHelpers
import helpers.CheckOutHelpers
import helpers.FiltersHelpers
import helpers.GeneralHelpers
import helpers.ProductHelpers
import internal.GlobalVariable
import validations.FiltersValidations
import validations.GeneralValidations
import validations.HeaderValidations
import validations.ProductValidations
import validations.QuickOrderValidations
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
//Casio
ManufacterLinkUrl = 'https://www.cleanersupply.com/Tags-Forms/Computer-Register/?Manufacturer=Casio'
filtersGroupsNumber = FiltersActions.storeFiltersGroupsNumber()
FiltersValidations.verifyManufacturerFilterContent()
GeneralActions.clickOnElement('Object Repository/Filters/ManufacturerFilter/span_ManufacturerOptionSelect')
FiltersHelpers.verifyHoverOnManufacturerDropdowns('Object Repository/Filters/ManufacturerFilter/a_CasioSelected')
GeneralActions.clickOnElement('Object Repository/Filters/ManufacturerFilter/a_CasioSelected')
FiltersValidations.verifyManufacturerFilterApplied(ManufacterLinkUrl, filtersGroupsNumber, 'Casio')
//model SP1000
ManufacterLinkUrl = 'https://www.cleanersupply.com/Tags-Forms/Computer-Register/?Model+%23=SP1000&Manufacturer=Casio'
GeneralActions.clickOnElement('Object Repository/Filters/ManufacturerFilter/span_ModelOption')
FiltersHelpers.verifyHoverOnManufacturerDropdowns('Object Repository/Filters/ManufacturerFilter/a_sp1000')
GeneralActions.clickOnElement('Object Repository/Filters/ManufacturerFilter/a_sp1000')
FiltersValidations.verifyManufacturerFilterApplied(ManufacterLinkUrl, null, 'SP1000')
// --------- Navigate to the resulted product page ---------
firstProductMap = ProductHelpers.storeFirstProductManufacturerDetails()
ProductActions.clickOnViewDetailsButtonOnManufacturerFilter()
GeneralHelpers.verifyCurrentUrlAndPageTitle(firstProductMap.get('productUrl'), firstProductMap.get('productTitle'))
double productPrice = ProductHelpers.verifypriceAndListPriceAndVolumePrice(firstProductMap.get('minPrice'), firstProductMap.get(
		'maxPrice'), firstProductMap.get('minListPrice'), firstProductMap.get('maxListPrice'))
//ProductValidations.verifyNumberOfAvailableColors(firstProductMap.get('availableColors'))
ProductValidations.verifybreadcrumbIsVisible()
ProductValidations.verifyProductSKU()
// --------- edit quantity value to be 10 ---------
double discountedPrice = ProductValidations.formatPrice(ProductActions.storeDiscountedPriceManufacturer())
List<Double> productsPrices = new ArrayList()
List productsTitles = new ArrayList()
List productsSKU = new ArrayList()
List<Integer> productsQuantities = new ArrayList()
productsPrices.add(15.54)
productsQuantities.add(10)
productsTitles.add('EPSON PRINTER INDELIBOND INK RIBBONS #TM290, ERC27 - 6/BOX')
productsSKU.add('STOCK # RIB290')
ProductHelpers.verifyUpdateQuantityAndAddToCart(10, discountedPrice, 1, productsPrices, productsQuantities)
modifiedProductsTitles = CartHelpers.makeListReadyForCartAndCheckout(false, productsTitles)
modifiedProductsQuantities = CartHelpers.makeListReadyForCartAndCheckout(true, productsQuantities)
modifiedProductsPrices = CartHelpers.makeListReadyForCartAndCheckout(true, productsPrices)
modifiedProductsSKU = CartHelpers.makeListReadyForCartAndCheckout(false, productsSKU)
//click on add to cart button
//GeneralValidations.verifyClickOnAddToCartButton()
// ----------- Navigate to the cart. ----------------
// --------- hover on cart icon ---------
WebUI.mouseOver(findTestObject('Object Repository/Header/li_cartLink'))
WebUI.verifyElementVisible(findTestObject('Object Repository/CartDropdown/div_cartDropdown'))
CartHelpers.verifyCartDropdown(modifiedProductsTitles, modifiedProductsQuantities, modifiedProductsPrices, modifiedProductsSKU)

// --------- verify shopping cart ---------
CheckOutHelpers.navigateToCart(GlobalVariable.casioName,GlobalVariable.casioQuantity,GlobalVariable.casioPrice,GlobalVariable.casioSku)
//GeneralHelpers.miniCartTest()
// ----------- Click on 'Proceed To Checkout' -----------
CheckOutHelpers.proceedToCheckOut()
// ----------- Select 'Checkut As Guest' and move to the next step. ---------------
CheckOutHelpers.selectCheckoutAsGuest(GlobalVariable.casioName,GlobalVariable.casioQuantity,GlobalVariable.casioPrice,GlobalVariable.casioSku)
// ----------- Fill Shipping and navigate to next step and observe the checkout review------
//checkout page
CheckOutHelpers.proceedToReviewOrderFinish()
GeneralHelpers.newPageIsOpened('/checkout',"Checkout - Cleaner's Supply")
GeneralValidations.verifyCurrentPageHeading('Object Repository/General/h1-pageHeading','CHECKOUT')
QuickOrderValidations.verifyReviewInfoInCheckout()
// ----------- close browser --------------------
WebUI.closeBrowser();