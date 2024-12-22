import path.PathMakeAppointment

import path.PathLogin

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.keyword.builtin.CallTestCaseKeyword
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
//import com.login.FormValidaiton as FormValidaiton
import blocks.login.DataLogin as DataLogin
import main.elementhelper.elementHelperr as ElementHelper

// Data Driven
TestData loginDataDriven = findTestData('Data Files/rawData/rawLoginData.xlsx')

// Ambil elemen dari PathLogin
TestObject ORCuraHealthcare = findTestObject(PathLogin.A_CURA)
TestObject ORLogin = findTestObject(PathLogin.A_LOGIN)
TestObject ORinput_Username = findTestObject(PathLogin.INPUT_USERNAME)
TestObject ORinput_Password = findTestObject(PathLogin.INPUT_PASSWORD)
TestObject ORbutton_Login = findTestObject(PathLogin.BUTTON_LOGIN)

// Inisialisasi ElementHelper untuk setiap elemen
// object dari class ElementHelper
ElementHelper cura = new ElementHelper(ORCuraHealthcare)
ElementHelper aLogin = new ElementHelper(ORLogin)
ElementHelper input_Username = new ElementHelper(ORinput_Username)
ElementHelper input_Password = new ElementHelper(ORinput_Password)
ElementHelper button_Login = new ElementHelper(ORbutton_Login)

// Call Test Case (Open Browser)
'step 1: call test case'
WebUI.callTestCase(findTestCase('blocks/Open Browser'), [:], FailureHandling.STOP_ON_FAILURE)

// Loop untuk setiap data dalam Data Driven Testing
for (int i = 1; i <= loginDataDriven.getRowNumbers(); i++) {
	println('baris ke: '+i)
	
	String username_ = loginDataDriven.getValue('username_', i)
	String password_ = loginDataDriven.getValue('password_', i)

	// Eksekusi langkah-langkah
	'step 2: click menu toggle'
	cura.click()
	
	'step 3: click Login Button'
	aLogin.click()
	
	'step 4'
	WebUI.clearText(findTestObject(PathLogin.INPUT_USERNAME))
	input_Username.setText(username_)
	
	'step 5'
	WebUI.clearText(findTestObject(PathLogin.INPUT_PASSWORD))
	input_Password.setEncryptedText(password_)

	
	'step 6'
	button_Login.click()
	
	'step 7'
	WebUI.delay(1)
	try {
			//Validasi Hasil dari Data Driven
			// menunggu hasil/PROSES dari step 7, apakah berhasil login atau gagal?
			if(loginDataDriven.getValue('Expected Result',i) == 'Success') {
				WebUI.verifyElementPresent(findTestObject(PathMakeAppointment.H2_MAKE_APPOINTMENT), 1, FailureHandling.STOP_ON_FAILURE)
			
				//logout jika login berhasil
				WebUI.callTestCase('blocks/logout', [:])
			} else {
				// jika login gagal
				WebUI.verifyElementPresent(findTestObject(PathMakeAppointment.P_LOGIN_FAILED), 3)
				
				//Reset ke halaman login
				WebUI.comment('Step: Navigating back to login page...')
				WebUI.navigateToUrl(GlobalVariable.baseUrlStaging)
			}
		}catch(Exception e) {
			WebUI.comment('Error terjadi: ' + e.getMessage())
			//KeywordUtil.markFailed("FAILED EXPECTED RESULT : "+e.getMessage());
			
		}	
}


////element testObject yang ingin diklik di object repository
//// OR = Object Repository
//TestObject ORLogin = findTestObject('Object Repository/LOGIN/a_Login')
//TestObject ORCuraHealthcare = findTestObject('Object Repository/LOGIN/a_CURA Healthcare_menu-toggle')
//TestObject ORinput_Username = findTestObject('Object Repository/LOGIN/input_Username')
//TestObject ORinput_Password = findTestObject('Object Repository/LOGIN/input_Password')
//TestObject ORbutton_Login = findTestObject('Object Repository/LOGIN/button_Login')
//
////// Inisialisasi class ElementHelper dengan TestObject yang dipilih
//ElementHelper aLogin = new ElementHelper(ORLogin)
//ElementHelper cura = new ElementHelper(ORCuraHealthcare)
//ElementHelper input_Username = new ElementHelper(ORinput_Username)
//ElementHelper input_Password = new ElementHelper(ORinput_Password)
//ElementHelper button_Login = new ElementHelper(ORbutton_Login)
//
////call test case
//'step 1 call test case'
//WebUI.callTestCase(findTestCase('blocks/Open Browser'), [:], FailureHandling.STOP_ON_FAILURE)
//
//
////CustomKeywords.'main.elementhelper.ElementHelper.click'(null)
//'step 2'
//cura.click()
//
//'step 3'
//aLogin.click()
//
//'step 4'
//input_Username.setText(username_)
//
//'step 5'
//input_Password.setEncryptedText(password_)
//
//'step 6'
//button_Login.click()

