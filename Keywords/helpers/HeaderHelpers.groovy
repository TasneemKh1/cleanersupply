package helpers

import actions.HeaderActions
import validations.GeneralValidations
import internal.GlobalVariable
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject

import org.openqa.selenium.WebElement


public class HeaderHelpers {
	/**
	 * @author tasneem
	 * 
	 * */
	public static void navigatingToQuickOrder() {
		String linkToQuickOrder=HeaderActions.mouseOverNavItem('Object Repository/Header/a_QuickOrder',)
		GeneralValidations.verifyHover( "Object Repository/Header/i-hidden-mobile-QuickOrder"," " ,"rgba(82, 36, 127, 1)"," "," ")
		HeaderActions.clickANavItem('Object Repository/Header/a_QuickOrder')
		GeneralHelpers.newPageIsOpened(linkToQuickOrder,GlobalVariable.titleOfQuikOrderPage)
		//GeneralValidations.verifyCurrentPageTitleValue("Quick Order - Cleaner's Supply")
	}
}
