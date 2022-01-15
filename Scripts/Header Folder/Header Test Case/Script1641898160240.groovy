import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import actions.GeneralActions
import actions.HeaderActions
import helpers.GeneralHelpers
import helpers.HeaderHelpers
import internal.GlobalVariable
import validations.GeneralValidations
import validations.HeaderValidations
import validations.SearchResults

GeneralHelpers.initScenario()
GeneralValidations.verifyCurrentPageHeading('Object Repository/Header/Header Top/span-Free-Shipping','FREE SHIPPING & RETURNS ON ORDERS OVER $99')

HeaderHelpers.HoverOverLinksOnHeaderthenVisitLink('Object Repository/Header/Header Top/a-details',"Fast, Free Shipping - Cleaner's Supply")

HeaderHelpers.HoverOverLinksOnHeaderthenVisitLink('Object Repository/Header/Header Top/a-customerservice',"Customer Service - Cleaner's Supply" )


WebUI.verifyElementPresent(findTestObject('Object Repository/Header/a-imgLogo'), GlobalVariable.pageLoadTimeOut)
HeaderActions.clickANavItem('Object Repository/Header/a-imgLogo')
GeneralHelpers.newPageIsOpened(GlobalVariable.baseURL,GlobalVariable.titleOfMainPage)
//search by keyword
HeaderValidations.verifySearchInputPlaceholderIsNotEmpty()
HeaderValidations.verifySearchInputIsEmpty()

HeaderActions.typeIntoSearchInput(GlobalVariable.cottonTerm)
HeaderValidations.verifySearchInputValue(GlobalVariable.cottonTerm)
HeaderValidations.verifySearchAutoCompleteDropdownVisible()
HeaderValidations.verifySearchAutoCompleteDropdownHeader(GlobalVariable.cottonTerm)
HeaderValidations.verifySearchAutoCompleteContentLabels()
HeaderValidations.verifySearchAutoCompleteCategories(GlobalVariable.cottonTerm)

HeaderActions.cliclOnSearchButton()
GeneralHelpers.verifyCurrentUrlAndPageTitle(GlobalVariable.cottonTerm, 'search results')
HeaderValidations.verifySearchInputPlaceholderIsNotEmpty()
HeaderValidations.verifySearchInputIsEmpty()
SearchResults.verifysearchResultsPageHeading('search results')
SearchResults.verifysearchResultsPageSubHeading(GlobalVariable.cottonTerm)

//search by STOCK
HeaderActions.typeIntoSearchInput(GlobalVariable.stockIdTerm)
HeaderValidations.verifySearchInputValue(GlobalVariable.stockIdTerm)
HeaderValidations.verifySearchAutoCompleteDropdownVisible()
HeaderValidations.verifySearchAutoCompleteDropdownHeader(GlobalVariable.stockIdTerm)
HeaderValidations.verifySearchAutoCompleteContentLabelsForStockTerm()
HeaderValidations.verifySearchAutoCompleteCategories(GlobalVariable.stockIdTerm)
HeaderValidations.hoverOverItemsInSuggestionsAtHeader()
//List<WebElement> searchAutoCompleteCategories = WebUI.findWebElements(findTestObject('Object Repository/Header/li-boxSuggestions'), GlobalVariable.visiablityItemTimeOut)
//GeneralActions.clickOnElement(WebUI.convertWebElementToTestObject(searchAutoCompleteCategories.get(0)))

HeaderActions.cliclOnSearchButton()
String searchTerm=GlobalVariable.stockIdTerm
GeneralHelpers.verifyCurrentUrlAndPageTitle(searchTerm.replace("#", "") ,'search results')
HeaderValidations.verifySearchInputPlaceholderIsNotEmpty()
HeaderValidations.verifySearchInputIsEmpty()
SearchResults.verifysearchResultsPageHeading('search results')
SearchResults.verifysearchResultsPageSubHeading(GlobalVariable.stockIdTerm)

HeaderHelpers.navigatingToQuickOrder()
GeneralValidations.verifyCurrentPageHeading('Object Repository/QuickOrder/span-QuickOrderHeading','QUICK');

HeaderHelpers.navigatingToReorder()

HeaderValidations.verifyCartItemsNumber(0)
HeaderValidations.verifyCartLabel('Cart')
