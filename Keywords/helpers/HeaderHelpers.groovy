package helpers

import actions.HeaderActions
import validations.GeneralValidations



public class HeaderHelpers {
	/**
	 * @author tasneem
	 * 
	 * */
	public static void navigatingToQuickOrder() {
		String linkToQuickOrder=HeaderActions.mouseOverNavItem('Object Repository/Header/a_QuickOrder')
		HeaderActions.clickANavItem('Object Repository/Header/a_QuickOrder')
		GeneralHelpers.newPageIsOpened(linkToQuickOrder)
		GeneralValidations.verifyCurrentPageTitleValue("Quick Order - Cleaner's Supply")
	}
}
