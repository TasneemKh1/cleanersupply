import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import helpers.FooterHelpers
import helpers.GeneralHelpers
import internal.GlobalVariable
import validations.FooterValidations


GeneralHelpers.initScenario()
FooterHelpers.verifyAddingEmailAndCheckValue('tasneem@mailinator.com')
FooterHelpers.verifySigningUpForExclusiveEmail()
FooterHelpers.verifyEachLinkInFooter('Object Repository/Footer/a-socialIcon','socialIcons')
FooterHelpers.contactingByEmail()
FooterHelpers.contactingByChat()
FooterHelpers.verifyEachLinkInFooter('Object Repository/Footer/a-footer-1col','col1')
FooterHelpers.verifyEachLinkInFooter('Object Repository/Footer/a-footer-2col','col2')
FooterHelpers.verifyEachLinkInFooter('Object Repository/Footer/a-footer-3col','col3')
WebUI.switchToWindowIndex(0)
FooterHelpers.verifyEachLinkInFooter('Object Repository/Footer/div-footer-4col','col4')
FooterHelpers.checkRegionDropDownValue()
FooterHelpers.selectCanadianRegion()
WebUI.back()
GeneralHelpers.newPageIsOpened(GlobalVariable.baseURL,GlobalVariable.titleOfMainPage)
FooterHelpers.verifyClickingOnFeedBackModal();
FooterHelpers.checkFeedBackModal()
FooterValidations.verifyLogoVisibleInFooter()
//WebUI.verifyElementVisible(findTestObject('Object Repository/Footer/img-bbb-logo'))
FooterHelpers.verifyEachLinkInFooter('Object Repository/Footer/a-rights','rights')





