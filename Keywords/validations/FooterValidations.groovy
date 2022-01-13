package validations

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.WebElement

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

public class FooterValidations {
	/**
	 * @Description Verify Email Input value displays the expected Text
	 *@author tasneem
	 *@param expectedTxt
	 * */
	public static void VerifyEmailInputValue(TestObject email,String expectedTxt) {
		assert WebUI.getAttribute(email, "value").equals(expectedTxt)
	}
	/**
	 * @Description Verify the value of box-Shadow When Hovering On Btn
	 *@author tasneem
	 *@param TestObject Btn
	 *@param String expectedshadowValue
	 * */
	public static void VerifyShadowWhenHoveringOnBtn(TestObject Btn,String expectedshadowValue) {
		assert WebUI.getCSSValue(Btn, 'box-shadow').equals(expectedshadowValue)
	}
	/**
	 * @Description check if sucess Message is displayed
	 *@author tasneem
	 *@param TestObject SuccessMessage
	 * */
	public static void checkSuccessMessageisPresent(TestObject SuccessMessage) {
		WebUI.verifyElementPresent(SuccessMessage, GlobalVariable.visiablityItemTimeOut,FailureHandling.OPTIONAL)
	}

	public static void verifyBodyClasses(Boolean x) {
		TestObject pageBody=findTestObject('Object Repository/General/body');
		if(x) {
			assert WebUI.getAttribute(pageBody, 'class').contains('modal-open')
		}else {
			assert !WebUI.getAttribute(pageBody, 'class').contains('modal-open')
		}
	}
	public static void verifyModalIsDisplayed(Boolean x) {
		TestObject modal=findTestObject('Object Repository/Footer/modal/div-modal');
		if(x) {
			assert WebUI.getCSSValue(modal, 'display').equals('block')
		}else {
			assert WebUI.getCSSValue(modal, 'display').equals('none')

		}
	}
	public static void checkBackgroundImageForRegionDropdownAndClass(List<WebElement> dropdownValues,String url,String CountryClass) {
		TestObject flag =WebUI.convertWebElementToTestObject(dropdownValues.get(0))
		assert WebUI.getCSSValue(flag, 'background-image').contains(url)
		assert WebUI.getAttribute(flag, 'class').contains(CountryClass)

	}
	public static void checkCountryName(List<WebElement> dropdownValues,String CountryNameTxt) {
		TestObject countryName =WebUI.convertWebElementToTestObject(dropdownValues.get(1))
		WebUI.getText(countryName).contains(CountryNameTxt)
	}
	public static void checkClassOpenIsAddedToDropDown() {
		TestObject dropdownMenu=findTestObject('Object Repository/Footer/div-menu-dropdown');
		WebUI.getAttribute(dropdownMenu, "class").contains('open')
	}
	public static void checkUSAIsSelectedByDefault(List<WebElement> dropdownList) {
		TestObject USA =WebUI.convertWebElementToTestObject(dropdownList.get(0))
		assert WebUI.getAttribute(USA, 'class').contains('selected')
	}
	public static void checkSelectedItemIndropdown(TestObject selectorForRegion) {
		assert WebUI.getNumberOfSelectedOption(selectorForRegion)==1
		assert WebUI.getUrl().contains('https://www.cleanersupply.ca/')
	}
	public static void checkAlertAfterChangingRegionIntoCanada() {
		TestObject alert=findTestObject('Object Repository/Footer/span-holder-Alert');
		assert WebUI.getText(alert).contains('CANADIAN')
	}
}
