package helpers

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import actions.FooterActions
import internal.GlobalVariable
import validations.FooterValidations
import validations.GeneralValidations
public class FooterHelpers {
	/**
	 * @Description verify Adding Email And Check Value of input
	 *@author tasneem
	 *@param emailTxt
	 * */
	public static void verifyAddingEmailAndCheckValue(String emailTxt) {
		TestObject email=FooterActions.sendKeysForEmailInput(emailTxt)
		FooterValidations.VerifyEmailInputValue(email,emailTxt)
	}


	/**
	 * @Description verify Signing UpForExclusiveEmail and success message is displayed
	 *@author tasneem
	 *
	 * */
	public static void verifySigningUpForExclusiveEmail () {
		TestObject signUpBtn=FooterActions.mouseOverSignUpBtn()
		FooterValidations.VerifyShadowWhenHoveringOnBtn(signUpBtn,'rgba(0, 0, 0, 0.3) 0px 0px 10px 2px')
		FooterActions.clickOnBtn(signUpBtn)
		TestObject SuccessMessage=findTestObject('Object Repository/Footer/span-successAlert');
		FooterValidations.checkSuccessMessageisPresent(SuccessMessage)
	}



	/**
	 * @Description getting the Href value For Ancor Tag
	 *@author tasneem
	 *@return String
	 *@param TestObject link
	 *
	 * */
	public static String gettingHrefForAncorTag(TestObject link) {
		return WebUI.getAttribute(link, "href")
	}

	/**
	 * @Description verify Contacting By Email Button open mail window
	 *@author tasneem
	 * */
	public static void contactingByEmail() {
		TestObject contactByEmail=findTestObject('Object Repository/Footer/a-contact-email');
		FooterActions.mouseOverOnLinks(contactByEmail)
		//String contactByEmailLink = WebUI.getAttribute(contactByEmail, 'href')
		FooterActions.clickOnBtn(contactByEmail)
		WebUI.delay(GlobalVariable.visiablityItemTimeOut)
		WebUI.switchToWindowIndex(0)
	}
	/**
	 * @Description verify Contacting By chat Button open a new window
	 *@author tasneem
	 * */
	public static void contactingByChat() {
		TestObject link=findTestObject('Object Repository/Footer/a-chat');
		if(!WebUI.getText(link).contains('Offline')) {
			FooterActions.mouseOverOnLinks(link)
			String chatLink = gettingHrefForAncorTag(link)
			FooterActions.clickOnBtn(link)
			GeneralValidations.verifyCurrentPageTitleIsNotEmpty()
			assert WebUI.getUrl().contains(chatLink)
			FooterActions.closeOpenedTab()
		}
	}

	/**
	 * @Description verify each link in footer opens the right corresponding page
	 * *@author tasneem
	 *@param String testObjID 
	 *@param String section
	 * */
	public static void verifyEachLinkInFooter (String testObjID ,String section) {
		List<WebElement> linkList2= WebUI.findWebElements(findTestObject(testObjID), GlobalVariable.visiablityItemTimeOut)
		for(int i=0;i<linkList2.size();++i) {
			List<WebElement> linkList= WebUI.findWebElements(findTestObject(testObjID), GlobalVariable.visiablityItemTimeOut)
			TestObject link = WebUI.convertWebElementToTestObject(linkList.get(i))
			WebUI.waitForElementHasAttribute(link, 'href', GlobalVariable.visiablityItemTimeOut)
			String hrefVal=gettingHrefForAncorTag(link)
			String url='';
			if(section.equals("socialIcons")) {
				FooterActions.clickOnBtn(link)
				WebUI.switchToWindowIndex(1)
				if (i == linkList.size()-1) {
					url='https://www.linkedin.com/';
				}else{
					url=hrefVal;
				}
				GeneralHelpers.newPageIsOpened(url,GlobalVariable.soccialTitles[i])
				FooterActions.closeOpenedTab()
				GeneralHelpers.newPageIsOpened(GlobalVariable.baseURL,GlobalVariable.titleOfMainPage)
			}else if (section.equals("col1")){
				FooterActions.mouseOverOnLinks(link)
				FooterActions.clickOnBtn(link)
				if (i <= 1) {
					GeneralHelpers.newPageIsOpened(GlobalVariable.baseURL,GlobalVariable.titleListForFooter[i])
				}else {
					GeneralHelpers.newPageIsOpened(hrefVal,GlobalVariable.titleListForFooter[i])
				}
				WebUI.back()
				GeneralHelpers.newPageIsOpened(GlobalVariable.baseURL,GlobalVariable.titleOfMainPage)
			}else if (section.equals("col2")){
				FooterActions.mouseOverOnLinks(link)
				FooterActions.clickOnBtn(link)
				GeneralHelpers.newPageIsOpened(hrefVal,GlobalVariable.titleListFor2ColInFooter[i])
				GeneralValidations.verifyCurrentPageHeading('Object Repository/General/h1-pageHeading',GlobalVariable.headingListForsecCol[i])
				WebUI.back()
				GeneralHelpers.newPageIsOpened(GlobalVariable.baseURL,GlobalVariable.titleOfMainPage)
			}else if (section.equals("col3")){
				FooterActions.mouseOverOnLinks(link)
				FooterActions.clickOnBtn(link)
				if (i <= 1) {
					GeneralHelpers.newPageIsOpened(GlobalVariable.baseURL,"Log In - Cleaner's Supply")
				}else if (i==2) {
					GeneralHelpers.newPageIsOpened(hrefVal,GlobalVariable.titleOfQuikOrderPage)
				}else {
					System.out.println("fliphtml5 is opened")
					WebUI.closeWindowIndex(1)
					break;
				}

				WebUI.back()
				GeneralHelpers.newPageIsOpened(GlobalVariable.baseURL,GlobalVariable.titleOfMainPage)
			}else if(section.equals("col4")) {
				FooterActions.mouseOverOnLinks(link)
				FooterActions.clickOnBtn(link)
				WebUI.waitForPageLoad(GlobalVariable.pageLoadTimeOut)
				GeneralHelpers.newPageIsOpened(hrefVal,GlobalVariable.titleListForCol4[i])
				GeneralValidations.verifyCurrentPageHeading('Object Repository/General/h1-pageHeading',GlobalVariable.headingForCol4[i])
				WebUI.back()
				GeneralHelpers.newPageIsOpened(GlobalVariable.baseURL,GlobalVariable.titleOfMainPage)
			}else if(section.equals("rights")) {
				String txtValue=WebUI.getText(link)
				FooterActions.mouseOverOnLinks(link)
				FooterActions.clickOnBtn(link)
				GeneralHelpers.newPageIsOpened(hrefVal,GlobalVariable.titleForReservedRights[i])
				GeneralValidations.verifyCurrentPageHeading('Object Repository/General/h1-pageHeading',txtValue)
				WebUI.back()
				GeneralHelpers.newPageIsOpened(GlobalVariable.baseURL,GlobalVariable.titleOfMainPage)
			}
		}
	}
	/**
	 * @Description verify hovering over feed back button then clicking on it
	 * *@author tasneem
	 *
	 * */
	public static void verifyClickingOnFeedBackModal () {
		TestObject sendFeedback=FooterActions.mouseOverSendFeedBack();
		FooterValidations.VerifyShadowWhenHoveringOnBtn(sendFeedback,'rgba(0, 0, 0, 0.3) 0px 0px 10px 2px')
		FooterActions.clickOnBtn(sendFeedback)
	}

	/**
	 * @Description check feed-back modal from classes added to body to heading in modal then closing it
	 * *@author tasneem
	 *
	 * */
	public static void checkFeedBackModal () {
		Boolean x=true;
		FooterValidations.verifyBodyClasses(x)
		FooterValidations.verifyModalIsDisplayed(x)
		GeneralValidations.verifyCurrentPageHeading('Object Repository/Footer/modal/h2-modal-header','HAVE AN IDEA, SUGGESTION OR SOME FEEDBACK?')
		FooterActions.closeModal()
		x=false;
		FooterValidations.verifyBodyClasses(x)
		FooterValidations.verifyModalIsDisplayed(x)
	}

	/**
	 * @Description check Background Image For Region Dropdown and check checkCountryName then clicking on dropdown
	 * *@author tasneem
	 *
	 * */
	public static void checkRegionDropDownValue() {
		List<WebElement> dropdownValues = WebUI.findWebElements(findTestObject('Object Repository/Footer/span-region-dropdown'), GlobalVariable.visiablityItemTimeOut)
		FooterValidations.checkBackgroundImageForRegionDropdownAndClass( dropdownValues,'/Content/Images/assets/footer-region-usa.png','usa')
		FooterValidations.checkCountryName( dropdownValues,'USA')
		FooterActions.clickOnRegionDropdown()
		FooterValidations.checkClassOpenIsAddedToDropDown()
	}

	/**
	 * @Description check selecting canada from dropdown
	 * *@author tasneem
	 *
	 * */
	public static void selectCanadianRegion() {
		List<WebElement> dropdownList = WebUI.findWebElements(findTestObject('Object Repository/Footer/li-dropdown'), GlobalVariable.visiablityItemTimeOut)
		FooterValidations.checkUSAIsSelectedByDefault( dropdownList)
		TestObject selectorForRegion =FooterActions.SelectCanadaFromDropdown()
		FooterValidations.checkSelectedItemIndropdown(selectorForRegion)

		List<WebElement> dropdownValues = WebUI.findWebElements(findTestObject('Object Repository/Footer/span-region-dropdown'), GlobalVariable.visiablityItemTimeOut)
		FooterValidations.checkBackgroundImageForRegionDropdownAndClass( dropdownValues,'/Content/Images/assets/footer-region-canada.png','can')
		FooterValidations.checkCountryName( dropdownValues,'Canada')
		FooterValidations.checkAlertAfterChangingRegionIntoCanada()
	}
}
