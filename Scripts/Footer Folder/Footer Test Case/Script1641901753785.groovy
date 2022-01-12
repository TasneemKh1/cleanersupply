import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebElement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import helpers.GeneralHelpers
import internal.GlobalVariable
import validations.GeneralValidations


GeneralHelpers.initScenario()
//WebUI.scrollToElement(null, )

for(int i=0 ;i<9;++i) {
	List<WebElement> aScrollTags = WebUI.findWebElements(findTestObject('Object Repository/Footer/a-footer-1col'), GlobalVariable.visiablityItemTimeOut)
	TestObject aScrollTagLink = WebUI.convertWebElementToTestObject(aScrollTags.get(i))
	String hrefVal=WebUI.getAttribute(aScrollTagLink, "href")
	WebUI.mouseOver(aScrollTagLink);
	assert WebUI.getCSSValue(aScrollTagLink, "text-decoration-line").equals("underline")
	WebUI.click(aScrollTagLink)
	if (i <= 1) {
		//	assert !WebUI.getUrl().contains(hrefVal)
		GeneralHelpers.newPageIsOpened(GlobalVariable.baseURL,GlobalVariable.titleListForFooter[i])
	}else {
		//assert WebUI.getUrl().contains(hrefVal)


		GeneralHelpers.newPageIsOpened(hrefVal,GlobalVariable.titleListForFooter[i])

	}

	WebUI.back()
	GeneralHelpers.newPageIsOpened(GlobalVariable.baseURL,"Cleaner's Supply - Dry Cleaning Supplies")
	
}


for(int i=0 ;i<8;++i) {
	List<WebElement> aScrollTags = WebUI.findWebElements(findTestObject('Object Repository/Footer/a-footer-2col'), GlobalVariable.visiablityItemTimeOut)
	TestObject aScrollTagLink = WebUI.convertWebElementToTestObject(aScrollTags.get(i))
	String hrefVal=WebUI.getAttribute(aScrollTagLink, "href")
	WebUI.mouseOver(aScrollTagLink);
	assert WebUI.getCSSValue(aScrollTagLink, "text-decoration-line").equals("underline")
	WebUI.click(aScrollTagLink)
	GeneralHelpers.newPageIsOpened(hrefVal,GlobalVariable.titleListFor2ColInFooter[i])
	GeneralValidations.verifyCurrentPageHeading('Object Repository/General/h1-pageHeading',GlobalVariable.headingListForsecCol[i])
	WebUI.back()
	GeneralHelpers.newPageIsOpened(GlobalVariable.baseURL,"Cleaner's Supply - Dry Cleaning Supplies")
	
}

for(int i=0 ;i<4;++i) {
	List<WebElement> aScrollTags = WebUI.findWebElements(findTestObject('Object Repository/Footer/a-footer-3col'), GlobalVariable.visiablityItemTimeOut)
	TestObject aScrollTagLink = WebUI.convertWebElementToTestObject(aScrollTags.get(i))
	String hrefVal=WebUI.getAttribute(aScrollTagLink, "href")
	WebUI.mouseOver(aScrollTagLink);
	assert WebUI.getCSSValue(aScrollTagLink, "text-decoration-line").equals("underline")
	WebUI.click(aScrollTagLink)
	if (i <= 1) {
		//	assert !WebUI.getUrl().contains(hrefVal)
		GeneralHelpers.newPageIsOpened(GlobalVariable.baseURL,"Log In - Cleaner's Supply")
	}else if (i==2) {
		//assert WebUI.getUrl().contains(hrefVal)
		GeneralHelpers.newPageIsOpened(hrefVal,"Quick Order - Cleaner's Supply")
	}else {
		System.out.println("fliphtml5 is opened")
	}

	WebUI.back()
	GeneralHelpers.newPageIsOpened(GlobalVariable.baseURL,"Cleaner's Supply - Dry Cleaning Supplies")
	
	
	
}

TestObject email=findTestObject('Object Repository/Footer/input-email');
WebUI.sendKeys(email, 'tasneem@mailinator.com')
assert WebUI.getAttribute(email, "value").equals('tasneem@mailinator.com')
TestObject signUpBtn=findTestObject('Object Repository/Footer/a-signUp-btn');
WebUI.mouseOver(signUpBtn)
assert WebUI.getCSSValue(signUpBtn, 'box-shadow').equals('0 0 10px 2px rgb(0 0 0 / 30%)')
WebUI.click(signUpBtn)

TestObject SuccessMessage=findTestObject('Object Repository/Footer/span-successAlert');
 WebUI.verifyElementPresent(SuccessMessage, GlobalVariable.visiablityItemTimeOut,FailureHandling.OPTIONAL)
 
 List<WebElement> socialIcons = WebUI.findWebElements(findTestObject('Object Repository/Footer/a-socialIcon'), GlobalVariable.visiablityItemTimeOut)
 TestObject aScrollTagLink = WebUI.convertWebElementToTestObject(aScrollTags.get(i))
 
 
