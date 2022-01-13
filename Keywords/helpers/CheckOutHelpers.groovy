package helpers
import actions.GeneralActions
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import actions.Navigations
import validations.GeneralValidations
import internal.GlobalVariable


public class CheckOutHelpers {
	public static void checkOutSenario() {
		//Navigate to the cart
		Navigations.navigateToCart();
		//Click on 'Proceed To Checkout'
		GeneralValidations.verifyCartProductData(['f'], ['20'], ['10'])
		//		TestObject processedToCheckOutButton = findTestObject('Object Repository/Cart/button_ProceedToCheckout')
		//		WebUI.click(processedToCheckOutButton)
		//		GeneralHelpers.newPageIsOpened(GlobalVariable.cartUrl, ' Shopping Cart ')
		//		GeneralValidations.verifyTitleOfHeading(' Shopping Cart ')

	}
	
	public static void EnterValuesForShippingAddress(){
		GeneralActions.typeIntoInputField(findTestObject('Object Repository/Checkout/shipping Address Section/input-Company'), GlobalVariable.CompanyName)
		GeneralActions.typeIntoInputField(findTestObject('Object Repository/Checkout/shipping Address Section/firstName'), GlobalVariable.CompanyName)
		
		
	}
	
}
