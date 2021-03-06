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

public class GeneralActions {
	/***
	 * Type into input field
	 * @author moham
	 * @param inputTestObject
	 * @param value
	 */
	public static void typeIntoInputField (TestObject inputTestObject, String value) {
		WebUI.sendKeys(inputTestObject, value)
	}
	/***
	 * clickOnElement
	 * @param testObjID the Id of element
	 * @author fatma
	 */
	public static void clickOnElement(String testObjID) {
		TestObject Element=findTestObject(testObjID)
		WebUI.click(Element)
	}
	public static void clickOnElement(TestObject testObj) {
		WebUI.click(testObj)
	}
	/***
	 * mouse Over On Element
	 * @param testObjID the Id of element 
	 * @author fatma
	 */
	public static void mouseOverOnElement(String testObjID) {
		TestObject Element=findTestObject(testObjID)
		WebUI.mouseOver(Element);
	}

	public static void mouseOverOnElement(TestObject Element) {
		WebUI.mouseOver(Element);
	}
	/***
	 * @author tasneem
	 * */
	public static void chooseFromSelector(String BtnId,String SelectID,String value) {
		clickOnElement(BtnId)
		TestObject Element=findTestObject(SelectID)
		WebUI.selectOptionByValue(Element,value, false)
	}


	public static void SetTextForInputsFields(TestObject inputTestObject, String value) {
		WebUI.setText(inputTestObject, value)
	}
}
