package actions

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class FooterActions {
	/**
	 * @Description Set Text for email input in footer
	 *@author tasneem
	 *@param emailTxt
	 *@return TestObject email
	 * */
	public static TestObject sendKeysForEmailInput(String emailTxt) {
		TestObject email=findTestObject('Object Repository/Footer/input-email');
		WebUI.sendKeys(email, emailTxt)
		return email
	}

	/**
	 * @Description mouse Over SignUp Btn
	 *@author tasneem
	 *@reteun signup TestObject
	 * */
	public static TestObject mouseOverSignUpBtn() {
		TestObject signUpBtn=findTestObject('Object Repository/Footer/a-signUp-btn');
		WebUI.mouseOver(signUpBtn)
		return signUpBtn
	}
	/***
	 * @author tasneem
	 *  @Description click On Buttons
	 *  @param TestObject Button
	 * ***/
	public static void clickOnBtn(TestObject Btn) {
		WebUI.click(Btn)
	}

	/***
	 * @author tasneem
	 *  @Description close Opened Tab and switch to main Window
	 *  
	 * ***/
	public static void closeOpenedTab() {
		//WebUI.delay(GlobalVariable.visiablityItemTimeOut)
		WebUI.closeWindowIndex(1)
		WebUI.switchToWindowIndex(0)
	}

	public static void mouseOverOnLinks(TestObject link) {
		WebUI.mouseOver(link);
		assert WebUI.getCSSValue(link, "text-decoration-line").equals("underline")
	}
	public static TestObject mouseOverSendFeedBack() {
		TestObject Btn=findTestObject('Object Repository/Footer/a-leave-feedback');
		WebUI.mouseOver(Btn)

		return Btn
	}
	public static void closeModal() {
		TestObject closeModal=findTestObject('Object Repository/Footer/modal/btn-close-modal');
		WebUI.click(closeModal)
	}
	public static void clickOnRegionDropdown() {
	TestObject dropdownBtn=findTestObject('Object Repository/Footer/btn-Region-dropdown');
	WebUI.click(dropdownBtn)
	}
	public static TestObject SelectCanadaFromDropdown() {
		TestObject selectorForRegion=findTestObject('Object Repository/Footer/region-selector');
		WebUI.selectOptionByValue(selectorForRegion, 'https://www.cleanersupply.ca/', false)
		return selectorForRegion
	}
}
