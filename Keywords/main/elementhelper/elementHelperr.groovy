package main.elementhelper
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.server.handler.GetElementText
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword

//CONSTRUCTOR CLASS
public class ElementHelper {
	// object private tidak bisa diakses static
	// Private TestObject untuk enkapsulasi
	private TestObject testObject;

	// Constructor untuk inisialisasi, jadi TestObject(element path) bisa diubah2 di dalam testcase
	public ElementHelper(TestObject testObject) {
		this.testObject = testObject;
	}
	//CONSTRUCTOR
	//	public ElementHelper(){
	//		this(testObject);
	//	}

	//verifikasi element muncul(bisa dilihat pengguna)
	@Keyword
	public void verifyVisibility() {
		try {
			WebUI.verifyElementVisible(testObject, FailureHandling.STOP_ON_FAILURE);
			KeywordUtil.markPassed("Element is visible: " + testObject.getObjectId());
		} catch (Exception e) {
			WebUI.comment('Error terjadi: ' + e.getMessage())
			KeywordUtil.markFailed("Element is not visible: " + testObject.getObjectId());
		}
	}


	@Keyword
	public void verifyNotVisible() {
		try {
			WebUI.verifyElementNotVisible(testObject, FailureHandling.CONTINUE_ON_FAILURE);
			KeywordUtil.markPassed("Element is not visible: " + testObject.getObjectId());
		} catch (Exception e) {
			WebUI.comment('Error terjadi: ' + e.getMessage())
			KeywordUtil.markFailed("Element is still visible: " + testObject.getObjectId());
		}
	}


	@Keyword
	public String getText() {
		try {
			String text = WebUI.getText(testObject);
			KeywordUtil.markPassed("Text retrieved: " + text);
			return text;
		} catch (Exception e) {
			WebUI.comment('Error terjadi: ' + e.getMessage())
			KeywordUtil.markFailed("Failed to retrieve text: " + e.getMessage());
			return null;
		}
	}


	@Keyword
	public void verifyText(String expectedText) {
		try {
			// memanggil method verifyVisibility
			verifyVisibility(); // memastikan elemen terlihat
			String actualText = WebUI.getText(testObject);
			if (actualText == null || !actualText.equals(expectedText)) {
				KeywordUtil.markFailed("Expected: " + expectedText + ", but found: " + actualText);
			} else {
				KeywordUtil.markPassed("Text matches expected: " + expectedText);
			}
		} catch (Exception e) {
			WebUI.comment('Error terjadi: ' + e.getMessage())
			KeywordUtil.markFailed("Error in verifyText: " + e.getMessage());
		}
	}


	@Keyword
	public void setText(String text) {
		try {
			WebUI.setText(testObject, text);
			KeywordUtil.markPassed("Set text: " + text);
		} catch (Exception e) {
			WebUI.comment('Error terjadi: ' + e.getMessage())
			KeywordUtil.markFailed("Failed to set text: " + e.getMessage());
		}
	}


	@Keyword
	public void click() {
		try {
			WebUI.click(testObject);
			KeywordUtil.markPassed("Clicked on element: " + testObject.getObjectId());
		} catch (Exception e) {
			WebUI.comment('Error terjadi: ' + e.getMessage())
			KeywordUtil.markFailed("Failed to click element: " + e.getMessage());
		}
	}

	@Keyword
	public void setEncryptedText(String encryptedText) {
		try {
			WebUI.setEncryptedText(testObject, encryptedText);
			KeywordUtil.markPassed("Set encrypted text successfully.");
		} catch (Exception e) {
			KeywordUtil.markFailed("Failed to set encrypted text: " + e.getMessage());
		}
	}


	@Keyword
	public void verifyElementExists() {
		try {
			WebUI.verifyElementPresent(testObject, 5, FailureHandling.CONTINUE_ON_FAILURE);
			KeywordUtil.markPassed("Element exists: " + testObject.getObjectId());
		} catch (Exception e) {
			WebUI.comment('Error terjadi: ' + e.getMessage())
			KeywordUtil.markFailed("Element does not exist: " + testObject.getObjectId());
		}
	}
}
