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
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import helpers.GeneralHelpers
import helpers.ProductHelpers
import actions.FiltersActions
import actions.GeneralActions
import actions.ProductActions
import actions.SelectCategoriesActions
import validations.GeneralValidations
import validations.ProductValidations
import helpers.CheckOutHelpers
// ----------- Navigate to https://www.cleanersupply.com/----------
GeneralHelpers.initScenario();
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
filtersGroupsNumber = FiltersActions.storeFiltersGroupsNumber()
//TestObject product=findTestObject('Object Repository/ProductPage/a_ComputerProduct')
//WebUI.click(product)
// --------- Navigate to the resulted product page ---------
firstProductMap = ProductHelpers.storeFirstProductManufacturerDetails()
ProductActions.clickOnViewDetailsButtonOnManufacturerFilter()
GeneralHelpers.verifyCurrentUrlAndPageTitle(firstProductMap.get('productUrl'), firstProductMap.get('productTitle'))
double productPrice = ProductHelpers.verifypriceAndListPriceAndVolumePrice(firstProductMap.get('minPrice'), firstProductMap.get(
		'maxPrice'), firstProductMap.get('minListPrice'), firstProductMap.get('maxListPrice'))
ProductValidations.verifyNumberOfAvailableColors(firstProductMap.get('availableColors'))
ProductValidations.verifybreadcrumbIsVisible()
ProductValidations.verifyProductSKU()
//--click on add to cart button --
GeneralValidations.verifyClickOnAddToCartButton()
// ----------- Navigate to the cart. ----------------
CheckOutHelpers.navigateToCart(["THERMAL BPA-FREE 21# RECEIPT ROLLS W/BACK PRINT - 160'/ROLL - 50/CASE - BLUE W/WHITE HANGER"], ['1'], ['89.99'],['RCT210BL'])
// ----------- Click on 'Proceed To Checkout' -----------
CheckOutHelpers.proceedToCheckOut(["THERMAL BPA-FREE 21# RECEIPT ROLLS W/BACK PRINT - 160'/ROLL - 50/CASE - BLUE W/WHITE HANGER"], ['1'], ['89.99'],['RCT210BL'])
// ----------- Select 'Checkut As Guest' and move to the next step. ---------------
CheckOutHelpers.selectCheckoutAsGuest(["THERMAL BPA-FREE 21# RECEIPT ROLLS W/BACK PRINT - 160'/ROLL - 50/CASE - BLUE W/WHITE HANGER"], ['1'], ['89.99'],['RCT210BL'])
