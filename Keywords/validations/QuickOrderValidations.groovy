package validations

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class QuickOrderValidations {
	public static void verifyFieldBorder(TestObject input) {
		assert WebUI.getCSSValue(input, "border-color").equals('rgb(99, 99, 99)')
	}
	public static  TestObject verifyRemoveBtnIsDisplayed(int i) {
		List<WebElement> removeBtns=WebUI.findWebElements(findTestObject('Object Repository/QuickOrder/btn-remove'),GlobalVariable.visiablityItemTimeOut)
		assert WebUI.verifyElementVisible(WebUI.convertWebElementToTestObject(removeBtns.get(i)));
		return WebUI.convertWebElementToTestObject(removeBtns.get(i))
	}

	public static void verifyProductIsInStock(TestObject input) {
		assert WebUI.getText(input).contains('In Stock!')
	}
	public static void verifyDollarSignIsdisplayed(TestObject input) {
		assert WebUI.getText(input).contains('$');
	}
	public static void verifyTotalPriceisDisplayed(TestObject input,String expectedTotal) {
		assert WebUI.getText(input).replace(",",'').contains(expectedTotal);
	}
	public static void VerifyShadowWhenHoveringOnBtn(TestObject Btn) {
		assert WebUI.getCSSValue(Btn, 'box-shadow').equals('rgba(0, 0, 0, 0.3) 0px 0px 10px 2px')
	}
	public static void VerifyHoveringOnRemoveBtn(TestObject Btn) {
		assert WebUI.getCSSValue(Btn, "background").contains("rgb(157, 159, 162)")
		assert WebUI.getCSSValue(Btn, "box-shadow").equals('rgba(0, 0, 0, 0.3) 0px 0px 10px 2px')
	}
}
