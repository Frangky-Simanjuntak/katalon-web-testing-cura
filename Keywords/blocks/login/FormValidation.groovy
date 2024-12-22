package blocks.login
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


public class Loginn {
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
/**
 public class FormValidation {
 // Validation for username
 @Keyword //ANOTATION => agar keyword terbaca saat dipanggil
 public static void validateEmail(String username) {
 if(username == null || username.trim().isEmpty()) {
 WebUI.comment("username tidak boleh kosong")
 throw new AssertionError("username tidak boleh kosong")
 }
 if(username.length < 5) {
 WebUI.comment("username terlalu pendek, minimal 5 karakter")
 }
 }
 @Keyword
 public static void verifyLogin(String username, String password) {
 // Verifikasi apakah login berhasil
 if (WebUI.verifyElementVisible(findTestObject('Object Repository/LOGIN/h2_Make Appointment'))) {
 // Ini akan muncul di Log Viewer
 WebUI.comment('Login Berhasil');
 } else if (WebUI.verifyElementVisible(findTestObject('Object Repository/LOGIN/p_Login failed Please ensure the username and password are valid'))) {
 WebUI.comment('LOGIN GAGAL!!!');
 } else {
 WebUI.comment('GAGAL!!!');
 }
 }
 @Keyword
 public static void validateUsername(String username) {
 if(!username.null()) {
 }
 }
 @Keyword
 def clickElement(TestObject to) {
 try {
 WebElement element = WebUiBuiltInKeywords.findWebElement(to);
 KeywordUtil.logInfo("Clicking element")
 element.click()
 KeywordUtil.markPassed("Element has been clicked")
 } catch (WebElementNotFoundException e) {
 KeywordUtil.markFailed("Element not found")
 } catch (Exception e) {
 KeywordUtil.markFailed("Fail to click on element")
 }
 }
 @Keyword
 def List<WebElement> getHtmlTableRows(TestObject table, String outerTagName) {
 WebElement mailList = WebUiBuiltInKeywords.findWebElement(table)
 List<WebElement> selectedRows = mailList.findElements(By.xpath("./" + outerTagName + "/tr"))
 return selectedRows
 }
 }**/