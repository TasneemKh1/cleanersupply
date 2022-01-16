package helpers

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import actions.HeaderActions
import internal.GlobalVariable
import validations.GeneralValidations
import validations.HeaderValidations

public class HeaderHelpers {
	/**
	 * @author tasneem
	 * 
	 * */
	public static void navigatingToQuickOrder() {
		String linkToQuickOrder=HeaderActions.mouseOverNavItem('Object Repository/Header/a_QuickOrder')
		GeneralValidations.verifyHover( "Object Repository/Header/i-hidden-mobile-QuickOrder"," " ,"rgba(82, 36, 127, 1)"," "," ")
		HeaderActions.clickANavItem('Object Repository/Header/a_QuickOrder')
		GeneralHelpers.newPageIsOpened(linkToQuickOrder,GlobalVariable.titleOfQuikOrderPage)
		//GeneralValidations.verifyCurrentPageTitleValue("Quick Order - Cleaner's Supply")
	}

	public static void navigatingToReorder() {
		String linkToreorder=HeaderActions.mouseOverNavItem('Object Repository/Header/a-reorder')
		GeneralValidations.verifyHover( "Object Repository/Header/i-hidden-mobilr-reorder"," " ,"rgba(82, 36, 127, 1)"," "," ")
		HeaderActions.clickANavItem('Object Repository/Header/a-reorder')
		GeneralHelpers.newPageIsOpened("previously-ordered","Log In - Cleaner's Supply")
		//GeneralValidations.verifyCurrentPageTitleValue("Quick Order - Cleaner's Supply")
	}
	public static void MyAccount() {
		String linkToreorder=HeaderActions.mouseOverNavItem('Object Repository/Header/btn-MyAccount')
		GeneralValidations.verifyHover( "Object Repository/Header/i-hidden-mobile-MyAccount"," " ,"rgba(82, 36, 127, 1)"," "," ")
		HeaderActions.clickANavItem('Object Repository/Header/btn-MyAccount')
		HeaderValidations.checkClassOpenIsAddedToMyAccount()
		HeaderValidations.checkMyAccountDropDownIsDisplayed()
	}

	public static void verifyCartEmptyForGuestUser() {
		
		HeaderValidations.verifyCartItemsNumber(0)
		HeaderValidations.verifyCartLabel('Cart')
		HeaderActions.mouseOverNavItem('Object Repository/Header/a-cart')
		GeneralValidations.verifyHover( "Object Repository/Header/i-mobile-hidden-Cart"," " ,"rgba(82, 36, 127, 1)"," "," ")
		WebUI.verifyElementVisible(findTestObject('Object Repository/Header/cart-empty'))
		HeaderActions.clickANavItem('Object Repository/Header/a-cart')
		GeneralHelpers.newPageIsOpened('shopping-cart/',"Shopping Cart - Cleaner's Supply")
		GeneralValidations.verifyCurrentPageHeading('Object Repository/General/h1-pageHeading','SHOPPING CART')
		
		
	}
	
	public static void HoverOverLinksOnHeaderthenVisitLink(String testObjectId,String title ) {
		HeaderActions.mouseOverDescription(findTestObject(testObjectId))
		String Href=WebUI.getAttribute(findTestObject(testObjectId), "href")
		HeaderActions.clickANavItem(testObjectId)
		GeneralHelpers.newPageIsOpened(Href,title)
		WebUI.back()
	}
}
