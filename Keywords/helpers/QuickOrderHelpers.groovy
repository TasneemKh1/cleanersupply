package helpers

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import actions.GeneralActions
import actions.QuickOrerActions
import internal.GlobalVariable
import validations.GeneralValidations
import validations.QuickOrderValidations


public class QuickOrderHelpers {
	public static int x=5;

	public static void verifyStockIdFields(TestObject input,String fieldValue,int i) {
		GeneralActions.SetTextForInputsFields(input, fieldValue)
		GeneralActions.typeIntoInputField(input,Keys.chord(Keys.ENTER))
		GeneralValidations.verifyInputValue(input, fieldValue)
		//QuickOrderValidations.verifyFieldBorder(input)
		QuickOrerActions.clickAtContainer(i)

	}
	public static int verifyQuntityField(int i) {
		List<WebElement> QuantityInput = WebUI.findWebElements(findTestObject('Object Repository/QuickOrder/input-Quantity'),
				GlobalVariable.visiablityItemTimeOut)
		TestObject input = WebUI.convertWebElementToTestObject(QuantityInput.get(i))
		int getRandomValue =generateRandomNumber()
		WebUI.waitForElementVisible(input, GlobalVariable.visiablityItemTimeOut)
		String quntityTxt = Integer.toString(getRandomValue)
		GeneralActions.typeIntoInputField(input,Keys.chord(Keys.BACK_SPACE))
		GeneralActions.typeIntoInputField(input, quntityTxt)
		GeneralValidations.verifyInputValue(input, quntityTxt)
		QuickOrerActions.clickAtContainer(i)
		return getRandomValue
	}

	public static int generateRandomNumber() {
		return  ((Math.random() * (GlobalVariable.maxQuantityNumber - GlobalVariable.minQuantityNumber)) as int) +
				GlobalVariable.minQuantityNumber
	}

	public static void fillingQuickOrderInputs() {
		//WebUI.waitForPageLoad(GlobalVariable.pageLoadTimeOut)
		List<WebElement> StockInput = WebUI.findWebElements(findTestObject('Object Repository/QuickOrder/input-stockID'), GlobalVariable.visiablityItemTimeOut)
		List<WebElement> QuickStockTable = []
		for (int i = 0; i < StockInput.size(); ++i) {
			TestObject stockInputField = WebUI.convertWebElementToTestObject(StockInput.get(i))
			verifyStockIdFields(stockInputField,GlobalVariable.productIDList[i],i)
			int randomQuntity=verifyQuntityField(i)
			TestObject removeBtn=QuickOrderValidations.verifyRemoveBtnIsDisplayed(i)
			GeneralActions.mouseOverOnElement(removeBtn)
			QuickOrderValidations.VerifyHoveringOnRemoveBtn(removeBtn)
			verifyproductStatus(i,removeBtn)

			List<WebElement> errorValidations = WebUI.findWebElements(findTestObject('Object Repository/QuickOrder/error-validation'),
					GlobalVariable.visiablityItemTimeOut)

			List<WebElement> imgQuickOrder = WebUI.findWebElements(findTestObject('Object Repository/QuickOrder/img-QuickOrder'),
					GlobalVariable.visiablityItemTimeOut)

			List<WebElement> productNameQuickOrder = WebUI.findWebElements(findTestObject('Object Repository/QuickOrder/a-productName'),
					GlobalVariable.visiablityItemTimeOut)



			QuickStockTable.add(GlobalVariable.productIDList[i])
			System.out.println(GlobalVariable.productIDList[i])
			QuickStockTable.add(Integer.toString(randomQuntity))
			TestObject imgSrcQuickOrder = WebUI.convertWebElementToTestObject(imgQuickOrder.get(i))
			QuickStockTable.add(WebUI.getAttribute(imgSrcQuickOrder, 'src'));
			System.out.println(WebUI.getAttribute(imgSrcQuickOrder, 'src'))
			GeneralActions.mouseOverOnElement(WebUI.convertWebElementToTestObject(productNameQuickOrder.get(i)));
			QuickStockTable.add(WebUI.getText(WebUI.convertWebElementToTestObject(productNameQuickOrder.get(i))));
			System.out.println(WebUI.getText(WebUI.convertWebElementToTestObject(productNameQuickOrder.get(i))));
			QuickStockTable.add('In Stock!')
			String  price=verifyPrice(i)
			QuickStockTable.add(price)

			List<WebElement> totalPriceQuickOrder = WebUI.findWebElements(findTestObject('Object Repository/QuickOrder/div-totalPrice'),
					GlobalVariable.visiablityItemTimeOut)
			TestObject total=WebUI.convertWebElementToTestObject(totalPriceQuickOrder.get(i));
			System.out.println(price+ " "+randomQuntity)
			System.out.println(Double.parseDouble(price)*randomQuntity)
			System.out.println("total"+String.format("%.2f", new BigDecimal(Double.parseDouble(price))*randomQuntity));
			String totalVal=String.format("%.2f", new BigDecimal(Double.parseDouble(price))*randomQuntity);
			//assert WebUI.getText(total).replace(",",'').contains(totalVal);
			QuickOrderValidations.verifyTotalPriceisDisplayed(total,totalVal)
			QuickOrderValidations.verifyDollarSignIsdisplayed(total)
			QuickStockTable.add(totalVal)
			System.out.println(QuickStockTable)
			

		}
	}

	public static  void verifyproductStatus(int i,TestObject removeBtn) {
		List<WebElement> inStockQuickOrder = WebUI.findWebElements(findTestObject('Object Repository/QuickOrder/div-inStock'),
				GlobalVariable.visiablityItemTimeOut)
		TestObject inStockQuickOrderField = WebUI.convertWebElementToTestObject(inStockQuickOrder.get(i))
		System.out.println("cccc"+WebUI.getText(inStockQuickOrderField))
		if(WebUI.getText(inStockQuickOrderField).equals('In Stock!')) {
			QuickOrderValidations.verifyProductIsInStock(inStockQuickOrderField)
			System.out.println("yyy"+WebUI.getText(inStockQuickOrderField))

		}else {
			GeneralActions.clickOnElement(removeBtn)
			verifyStockIdFields(WebUI.convertWebElementToTestObject(
					WebUI.findWebElements(findTestObject('Object Repository/QuickOrder/input-stockID'), GlobalVariable.visiablityItemTimeOut)
					.get(i)
					),GlobalVariable.productIDList[i+x],i)
			if(x < 9) {
				x++;
			}
			int randomQuntity=verifyQuntityField(i)
			QuickOrderValidations.verifyRemoveBtnIsDisplayed(i)
			verifyproductStatus(i,removeBtn)
		}

		//return  WebUI.getText(inStockQuickOrderField).equals('In Stock!')
	}
	public static  String verifyPrice(int i) {
		List<WebElement> priceHolderQuickOrder = WebUI.findWebElements(findTestObject('Object Repository/QuickOrder/div-priceHolder'),
				GlobalVariable.visiablityItemTimeOut);
		TestObject price2 = WebUI.convertWebElementToTestObject(priceHolderQuickOrder.get(i))
		QuickOrderValidations.verifyDollarSignIsdisplayed(price2)
		String [] price = WebUI.getText(WebUI.convertWebElementToTestObject(priceHolderQuickOrder.get(i))).replace('$', '').split(" ")
		return price[0]
	}
	public static void  VerifyAddToCart() {
		TestObject addToCartBtn=findTestObject('Object Repository/QuickOrder/btn-addToCart')
		GeneralActions.mouseOverOnElement(addToCartBtn)
		QuickOrderValidations.VerifyShadowWhenHoveringOnBtn(addToCartBtn)
		WebUI.click(addToCartBtn)

	}

}

//			List<WebElement> priceNumbers = WebUI.findWebElements(findTestObject('Object Repository/QuickOrder/text-price'),
//					GlobalVariable.visiablityItemTimeOut);
//			TestObject priceNumTxt = WebUI.convertWebElementToTestObject(priceNumbers.get(i))
//			QuickStockTable.add(WebUI.getText(priceNumTxt).trim())
//			Double priceOfProduct= Double.parseDouble(WebUI.getText(priceNumTxt).trim())
//			List<WebElement> totalPriceQuickOrder = WebUI.findWebElements(findTestObject('Object Repository/QuickOrder/div-totalPrice'),
//					GlobalVariable.visiablityItemTimeOut)
//
//			TestObject total=WebUI.convertWebElementToTestObject(totalPriceQuickOrder.get(i));
//			System.out.println(priceOfProduct+ " "+randomQuntity)
//			System.out.println("total"+String.format("%.2f", new BigDecimal(priceOfProduct)*randomQuntity));
//			String totalVal=String.format("%.2f", new BigDecimal(priceOfProduct)*randomQuntity);
//			assert WebUI.getText(total).replace(",",'').contains(totalVal);
//			List<WebElement> priceHolderQuickOrder = WebUI.findWebElements(findTestObject('Object Repository/QuickOrder/div-priceHolder'),
//					GlobalVariable.visiablityItemTimeOut);
//			TestObject price2 = WebUI.convertWebElementToTestObject(priceHolderQuickOrder.get(i))
//			assert WebUI.getText(price2).contains('$');
//			String [] price = WebUI.getText(WebUI.convertWebElementToTestObject(priceHolderQuickOrder.get(i))).replace('$', '').split(" ")