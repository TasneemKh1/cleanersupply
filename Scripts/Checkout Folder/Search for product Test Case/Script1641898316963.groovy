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
import actions.FiltersActions as FiltersActions
import actions.HeaderActions as HeaderActions
import helpers.FiltersHelpers as FiltersHelpers
import helpers.GeneralHelpers as GeneralHelpers
import internal.GlobalVariable as GlobalVariable
import validations.FiltersValidations as FiltersValidations
import validations.HeaderValidations as HeaderValidations
import validations.SearchResults as SearchResults
import org.openqa.selenium.Keys as Keys

// --------- Variables ---------
int packagingProductsNumber
String packagingProductsLinkUrl
int plasticBagsNumber
String plasticBagsLinkUrl
int filtersGroupsNumber

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
