import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import actions.FiltersActions
import actions.GeneralActions
import actions.HeaderActions as HeaderActions
import actions.ProductActions as ProductActions
import helpers.CartHelpers as CartHelpers
import helpers.CheckOutHelpers as CheckOutHelpers
import helpers.FiltersHelpers as FiltersHelpers
import helpers.GeneralHelpers as GeneralHelpers
import helpers.ProductHelpers as ProductHelpers
import internal.GlobalVariable as GlobalVariable
import validations.FiltersValidations as FiltersValidations
import validations.GeneralValidations as GeneralValidations
import validations.HeaderValidations as HeaderValidations
import validations.ProductValidations
import validations.QuickOrderValidations
import validations.SearchResults as SearchResults
import org.openqa.selenium.Keys as Keys
import java.util.HashMap as HashMap
import java.util.Map as Map

// --------- Variables ---------
int packagingProductsNumber
String packagingProductsLinkUrl
int plasticBagsNumber
String plasticBagsLinkUrl
int filtersGroupsNumber
String greenProductsLinkUrl
int greenProductsNumber
int firstQuantity = 5
int secondQuantity = 3
HashMap<String, String> firstProductMap
List productsTitles = new ArrayList()
List productsPrices = new ArrayList()
List productsQuantities = new ArrayList()
List productsSKU = new ArrayList()
// ---------------------------
List modifiedProductsTitles = new ArrayList()
List modifiedProductsPrices = new ArrayList()
List modifiedProductsQuantities = new ArrayList()
List modifiedProductsSKU = new ArrayList()

// --------- Navigate to 'Home Page' ---------
GeneralHelpers.initScenario()
HeaderValidations.verifyCartItemsNumber(0)
HeaderValidations.verifyCartLabel('Cart')
HeaderValidations.verifySearchInputPlaceholderIsNotEmpty()
HeaderValidations.verifySearchInputIsEmpty()

// --------- Type term in 'Search Input' ---------
HeaderActions.typeIntoSearchInput(GlobalVariable.searchTerm)
HeaderValidations.verifySearchInputValue(GlobalVariable.searchTerm)
HeaderValidations.verifySearchAutoCompleteDropdownVisible()
HeaderValidations.verifySearchAutoCompleteDropdownHeader(GlobalVariable.searchTerm)
HeaderValidations.verifySearchAutoCompleteContentLabels()
HeaderValidations.verifySearchAutoCompleteCategories(GlobalVariable.searchTerm)

// --------- Click on 'Search Icon' ---------
HeaderActions.cliclOnSearchButton()
GeneralHelpers.verifyCurrentUrlAndPageTitle(GlobalVariable.searchTerm, 'search results')
HeaderValidations.verifySearchInputPlaceholderIsNotEmpty()
HeaderValidations.verifySearchInputIsEmpty()
SearchResults.verifysearchResultsPageHeading('search results')
SearchResults.verifysearchResultsPageSubHeading(GlobalVariable.searchTerm)

// --------- Select 'Packing Products' option from category filter ---------
packagingProductsNumber = FiltersActions.storePackingProductsNumber()
packagingProductsLinkUrl = FiltersActions.storePackingProductsLinkUrl()
filtersGroupsNumber = FiltersActions.storeFiltersGroupsNumber()
FiltersActions.clickOnPackingProductsLink()
FiltersValidations.verifyPackingProductsFilterApplied(packagingProductsLinkUrl, packagingProductsNumber, filtersGroupsNumber, 
    'Packaging Products')

// --------- Select 'Plastic Bags' option from category filter ---------
plasticBagsNumber = FiltersActions.storePlasticBagsNumber()
plasticBagsLinkUrl = FiltersActions.storePlasticBagsLinkUrl()
FiltersActions.clickOnPlasticBagsLink()
FiltersValidations.verifyBlasticBagsFilterApplied(plasticBagsLinkUrl, plasticBagsNumber + packagingProductsNumber, filtersGroupsNumber, 
    'Plastic Bags')

// --------- Select 'Green' from color group filter ---------
FiltersActions.expandColorCard()
greenProductsNumber = FiltersActions.storeGreenProductsNumber()
greenProductsLinkUrl = FiltersActions.storeGreenProductsLinkUrl()
FiltersActions.clickOnGreenProductsLink()
FiltersValidations.verifyGreenProductsFilterApplied(greenProductsLinkUrl, greenProductsNumber, filtersGroupsNumber, 'Green')

// --------- Navigate to the resulted product page ---------
firstProductMap = ProductActions.storeFirstProductDetails()
ProductActions.clickOnViewDetailsButton()
GeneralHelpers.verifyCurrentUrlAndPageTitle(firstProductMap.get('productUrl'), firstProductMap.get('productTitle'))
double productPrice = ProductHelpers.verifypriceAndListPriceAndVolumePrice(firstProductMap.get('minPrice'), firstProductMap.get(
        'maxPrice'), firstProductMap.get('minListPrice'), firstProductMap.get('maxListPrice'))
ProductValidations.verifyNumberOfAvailableColors(firstProductMap.get('availableColors'))
ProductValidations.verifybreadcrumbIsVisible()
ProductValidations.verifyProductSKU()

// --------- select 'X-Large' size ---------
ProductActions.clickOnXlargeButton()
ProductValidations.verifyIfXLargeFilterIsSelected()
productPrice = ProductHelpers.verifypriceAndListPriceAndVolumePrice(firstProductMap.get('minPrice'), firstProductMap.get(
        'maxPrice'), firstProductMap.get('minListPrice'), firstProductMap.get('maxListPrice'))
ProductHelpers.verifySkuAndTitle('X-Large')

// --------- select 'Green' color ---------
ProductActions.clickOnGreenButton()
ProductValidations.verifyIfGreenFilterIsSelected()
productPrice = ProductHelpers.verifypriceAndListPriceAndVolumePrice(firstProductMap.get('minPrice'), firstProductMap.get(
        'maxPrice'), firstProductMap.get('minListPrice'), firstProductMap.get('maxListPrice'))
String productSKU = ProductValidations.verifyProductSKU()
String productTitle = ProductValidations.verifyProductTitle('Green')
double discountedPrice = ProductValidations.formatPrice(ProductActions.storeDiscountedPrice())
ProductHelpers.StockNotificationAndSelectedColorText('Green')

// --------- update products lists ---------
ProductHelpers.updateProductsLists(productsTitles, productsPrices, productsQuantities, productsSKU, productTitle, discountedPrice, 
    firstQuantity, productSKU)

// --------- edit quantity value to be 5 ---------
ProductHelpers.verifyUpdateQuantityAndAddToCart(firstQuantity, discountedPrice, 1, productsPrices, productsQuantities)

// --------- select 'Large' size ---------
ProductActions.clickOnLargeButton()
ProductValidations.verifyIfLargeFilterIsSelected()
productPrice = ProductHelpers.verifypriceAndListPriceAndVolumePrice(firstProductMap.get('minPrice'), firstProductMap.get(
        'maxPrice'), firstProductMap.get('minListPrice'), firstProductMap.get('maxListPrice'))
ProductHelpers.verifySkuAndTitle('Large')

// --------- select 'Royal Blue' color ---------
ProductActions.clickOnRoyalBlueButton()
ProductValidations.verifyIfRoyalBlueFilterIsSelected()
productPrice = ProductHelpers.verifypriceAndListPriceAndVolumePrice(firstProductMap.get('minPrice'), firstProductMap.get(
        'maxPrice'), firstProductMap.get('minListPrice'), firstProductMap.get('maxListPrice'))
productSKU = ProductValidations.verifyProductSKU()
productTitle = ProductValidations.verifyProductTitle('Royal Blue')
discountedPrice = ProductValidations.formatPrice(ProductActions.storeDiscountedPrice())
ProductHelpers.StockNotificationAndSelectedColorText('Royal Blue')

// --------- update products lists ---------
ProductHelpers.updateProductsLists(productsTitles, productsPrices, productsQuantities, productsSKU, productTitle, discountedPrice, 
    secondQuantity, productSKU)

// --------- edit quantity value to be 3 ---------
ProductHelpers.verifyUpdateQuantityAndAddToCart(secondQuantity, discountedPrice, 2, productsPrices, productsQuantities)
modifiedProductsTitles = CartHelpers.makeListReadyForCartAndCheckout(false, productsTitles)
modifiedProductsQuantities = CartHelpers.makeListReadyForCartAndCheckout(true, productsQuantities)
modifiedProductsPrices = CartHelpers.makeListReadyForCartAndCheckout(true, productsPrices)
modifiedProductsSKU = CartHelpers.makeListReadyForCartAndCheckout(false, productsSKU)

// --------- hover on cart icon ---------
WebUI.mouseOver(findTestObject('Object Repository/Header/li_cartLink'))
WebUI.verifyElementVisible(findTestObject('Object Repository/CartDropdown/div_cartDropdown'))
CartHelpers.verifyCartDropdown(modifiedProductsTitles, modifiedProductsQuantities, modifiedProductsPrices, modifiedProductsSKU)

// --------- verify shopping cart ---------
WebUI.click(findTestObject('Object Repository/CartDropdown/a_viewCart'))
CartHelpers.verifyCartinShoppingCartPage(modifiedProductsTitles, modifiedProductsQuantities, modifiedProductsPrices, modifiedProductsSKU)

// --------- change products quantities from shopping cart ---------
WebUI.click(findTestObject('Object Repository/CartDropdown/button_increaseQuantity'))
WebUI.click(findTestObject('Object Repository/CartDropdown/button_decreaseQuantity'))
productsQuantities.set(0, 4)
productsQuantities.set(1, 4)
modifiedProductsQuantities = CartHelpers.makeListReadyForCartAndCheckout(true, productsQuantities)
CartHelpers.verifyCartinShoppingCartPage(modifiedProductsTitles, modifiedProductsQuantities, modifiedProductsPrices, modifiedProductsSKU)

// ---------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------
GeneralActions.mouseOverOnElement(findTestObject('Object Repository/Checkout/OrderSummery/Btn-proceedToCheckout'))
QuickOrderValidations.VerifyShadowWhenHoveringOnBtn(findTestObject('Object Repository/Checkout/OrderSummery/Btn-proceedToCheckout'))
// --------- move to checkout page ---------
WebUI.click(findTestObject('Object Repository/Checkout/OrderSummery/Btn-proceedToCheckout'));
GeneralHelpers.newPageIsOpened('/checkout-interstitial',"Checkout Interstitial - Cleaner's Supply")
assert WebUI.getText(findTestObject('Object Repository/Checkout/checkout Interstitial/div-heading')).contains('SECURE CHECKOUT')
WebUI.verifyElementChecked(findTestObject('Object Repository/Checkout/checkout Interstitial/radio-checkout-guest'), GlobalVariable.pageLoadTimeOut)
GeneralActions.mouseOverOnElement(findTestObject('Object Repository/Checkout/checkout Interstitial/btn-continue'))
QuickOrderValidations.VerifyShadowWhenHoveringOnBtn(findTestObject('Object Repository/Checkout/checkout Interstitial/btn-continue'))
CheckOutHelpers.verifyMyCartData(modifiedProductsTitles, modifiedProductsQuantities, modifiedProductsPrices, modifiedProductsSKU)
// --------- checkout as guest ---------
WebUI.click(findTestObject('Object Repository/Checkout/checkout Interstitial/btn-continue'));
GeneralHelpers.newPageIsOpened('/checkout',"Checkout - Cleaner's Supply")
GeneralValidations.verifyCurrentPageHeading('Object Repository/General/h1-pageHeading','CHECKOUT')
//CheckOutHelpers.verifyMyCartData(modifiedProductsTitles, modifiedProductsQuantities, modifiedProductsPrices, modifiedProductsSKU)
// --------- enter shipping address and payment method ---------
CheckOutHelpers.proceedToReviewOrderFinish()
GeneralHelpers.newPageIsOpened('/checkout',"Checkout - Cleaner's Supply")
// --------- review order ---------
GeneralValidations.verifyCurrentPageHeading('Object Repository/General/h1-pageHeading','CHECKOUT')
QuickOrderValidations.verifyReviewInfoInCheckout()
WebUI.closeBrowser();
