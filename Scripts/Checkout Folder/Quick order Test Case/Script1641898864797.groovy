import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import helpers.GeneralHelpers
import helpers.HeaderHelpers
import internal.GlobalVariable
import validations.GeneralValidations

//open website & check title and url
GeneralHelpers.initScenario()

HeaderHelpers.navigatingToQuickOrder()

GeneralValidations.verifyCurrentPageHeading('Object Repository/QuickOrder/span-QuickOrderHeading','QUICK');

List<WebElement> StockInput = WebUI.findWebElements(findTestObject('Object Repository/QuickOrder/input-stockID'), GlobalVariable.visiablityItemTimeOut)

List<WebElement> QuickStockTable = []


for (int i = 0; i < StockInput.size(); ++i) {
	TestObject stockInputField = WebUI.convertWebElementToTestObject(StockInput.get(i))

	String RowId = WebUI.getAttribute(stockInputField, 'id').charAt(0)
	System.out.println(" id "+WebUI.getAttribute(stockInputField, 'id').charAt(0))
//	WebUI.clearText(stockInputField)
//	WebUI.sendKeys(stockInputField, GlobalVariable.productIDList[i])
	WebUI.setText(stockInputField, GlobalVariable.productIDList[i])
	
	WebUI.getCSSValue(stockInputField, "border-color").equals('rgb(99, 99, 99)')
//	TestObject menu=stockInputField.addProperty("xpath", ConditionType.EQUALS,'/following-sibling::div')
//	assert WebUI.getAttribute(menu, "class").contains('open')
//	System.out.println( WebUI.getCSSValue(menu,"style"))
	//check if it is focused 
//	WebDriver driver = DriverFactory.getWebDriver()
//	WebElement focusedElement = driver.switchTo().activeElement()
//	assert focusedElement.getTagName().equals(stockInputField)
	
	//WebUI.click(findTestObject('QuickOrder/dropDownForStockId', [('row') : i]))

	QuickStockTable.add(GlobalVariable.productIDList[i])
	

	TestObject div= new TestObject().addProperty("xpath", ConditionType.EQUALS, '//div[@class="page-content"]/section/section/div[@class="container"]')
	WebUI.click(div)
	
	assert WebUI.getAttribute(stockInputField, 'value').contains(GlobalVariable.productIDList[i])

	List<WebElement> QuantityInput = WebUI.findWebElements(findTestObject('Object Repository/QuickOrder/input-Quantity'),
			GlobalVariable.visiablityItemTimeOut)
	
	List<WebElement> errorValidations = WebUI.findWebElements(findTestObject('Object Repository/QuickOrder/error-validation'),
			GlobalVariable.visiablityItemTimeOut)

	List<WebElement> imgQuickOrder = WebUI.findWebElements(findTestObject('Object Repository/QuickOrder/img-QuickOrder'),
			GlobalVariable.visiablityItemTimeOut)

	List<WebElement> productNameQuickOrder = WebUI.findWebElements(findTestObject('Object Repository/QuickOrder/a-productName'),
			GlobalVariable.visiablityItemTimeOut)

	List<WebElement> inStockQuickOrder = WebUI.findWebElements(findTestObject('Object Repository/QuickOrder/div-inStock'),
			GlobalVariable.visiablityItemTimeOut)

	

	int getRandomValue = ((Math.random() * (GlobalVariable.maxQuantityNumber - GlobalVariable.minQuantityNumber)) as int) +
			GlobalVariable.minQuantityNumber

	TestObject QuantityInputField = WebUI.convertWebElementToTestObject(QuantityInput.get(i))

	WebUI.waitForElementVisible(QuantityInputField, GlobalVariable.visiablityItemTimeOut)

	String quntityTxt = Integer.toString(getRandomValue)

	//WebUI.clearText(QuantityInputField)
	WebUI.clearText(QuantityInputField)
	WebUI.sendKeys(QuantityInputField,Keys.chord(Keys.BACK_SPACE))
	WebUI.sendKeys(QuantityInputField, quntityTxt)
	
	List<WebElement> removeBtns=WebUI.findWebElements(findTestObject('Object Repository/QuickOrder/btn-remove'),GlobalVariable.visiablityItemTimeOut)
	assert WebUI.verifyElementVisible(WebUI.convertWebElementToTestObject(removeBtns.get(i)))
	
//	if( WebUI.verifyElementVisible(WebUI.convertWebElementToTestObject(errorValidations.get(0)))) {
//		WebUI.click(WebUI.convertWebElementToTestObject(removeBtns.get(i)))
//		WebUI.sendKeys(stockInputField, GlobalVariable.productIDList[i+StockInput.size()])
//		WebUI.getCSSValue(stockInputField, "border-color").equals('rgb(99, 99, 99)')
//	}

	//WebUI.sendKeys(QuantityInputField,Integer.toString(getRandomValue))
	QuickStockTable.add(Integer.toString(getRandomValue))

	TestObject imgSrcQuickOrder = WebUI.convertWebElementToTestObject(imgQuickOrder.get(i))

	QuickStockTable.add(WebUI.getAttribute(imgSrcQuickOrder, 'src'))

	//imgSrc.add(
	QuickStockTable.add(WebUI.getText(WebUI.convertWebElementToTestObject(productNameQuickOrder.get(i))))

	TestObject inStockQuickOrderField = WebUI.convertWebElementToTestObject(inStockQuickOrder.get(i))

	assert WebUI.getText(inStockQuickOrderField).contains('In Stock!')
	WebUI.delay(GlobalVariable.visiablityItemTimeOut)
	List<WebElement> priceHolderQuickOrder = WebUI.findWebElements(findTestObject('Object Repository/QuickOrder/div-priceHolder'),
		GlobalVariable.visiablityItemTimeOut)

	String price = WebUI.getText(WebUI.convertWebElementToTestObject(priceHolderQuickOrder.get(i))).replace('$', '')

	QuickStockTable.add(price)

	List<WebElement> totalPriceQuickOrder = WebUI.findWebElements(findTestObject('Object Repository/QuickOrder/div-totalPrice'),
			GlobalVariable.visiablityItemTimeOut)

	WebUI.getText(WebUI.convertWebElementToTestObject(totalPriceQuickOrder.get(i)));
	//assert WebUI.getText(WebUI.convertWebElementToTestObject(totalPriceQuickOrder.get(i))).contains(Float.toString(Float.parseFloat(price)*getRandomValue));
	System.out.println(price+ " "+getRandomValue)
	System.out.println(Double.parseDouble(price)*getRandomValue)
	System.out.println("total"+String.format("%.2f", new BigDecimal(Double.parseDouble(price))*getRandomValue));
	//assert WebUI.getText(WebUI.convertWebElementToTestObject(totalPriceQuickOrder.get(i))).contains(Float.toString())
	
}

TestObject addToCartBtn=findTestObject('Object Repository/QuickOrder/btn-addToCart')

WebUI.click(addToCartBtn)


//    TestObject dropDownForStockId = findTestObject('Object Repository/QuickOrder/dropDownForStockId', [('row') : i])
//
//    WebUI.click(dropDownForStockId)
//	String liSelector ='//tr/td/div/input[contains(@id, "quick-order")]/following-sibling::div/ul[@class="dropdown-menu inner"]/li'
//	TestObject dropdown= new TestObject().addProperty("xpath", ConditionType.EQUALS, liSelector)
//WebUI.click(dropdown)
//List <String> imgSrc=[];
//List <String> productName=[];

