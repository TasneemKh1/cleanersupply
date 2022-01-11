import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import helpers.GeneralHelpers
import helpers.HeaderHelpers
import internal.GlobalVariable


//open website & check title and url
GeneralHelpers.initScenario();

HeaderHelpers.navigatingToQuickOrder();

//GeneralValidations.verifyCurrentPageHeading('Object Repository/QuickOrder/span-QuickOrderHeading',' QUICK ORDER');

List<WebElement> StockInput=WebUI.findWebElements(findTestObject('Object Repository/QuickOrder/input-stockID'),GlobalVariable.visiablityItemTimeOut)
List<WebElement> QuantityInput=WebUI.findWebElements(findTestObject('Object Repository/QuickOrder/input-Quantity'),GlobalVariable.visiablityItemTimeOut)

for (int i=0 ;i<StockInput.size();++i) {
	TestObject stockInputField=WebUI.convertWebElementToTestObject(StockInput.get(i))
	String RowId=WebUI.getAttribute(stockInputField, "id").charAt(0);
	WebUI.sendKeys(stockInputField, GlobalVariable.productIDList[i])
	
	//assert WebUI.getAttribute(stockInputField, "value").contains(GlobalVariable.productIDList[i])
	
	int getRandomValue = (int) (Math.random()*(GlobalVariable.maxQuantityNumber-GlobalVariable.minQuantityNumber)) + GlobalVariable.minQuantityNumber;
	WebUI.sendKeys(WebUI.convertWebElementToTestObject(QuantityInput.get(i)),getRandomValue)
}