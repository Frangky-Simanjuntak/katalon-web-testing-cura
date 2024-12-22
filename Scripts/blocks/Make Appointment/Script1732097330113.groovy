import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable
import path.PathMakeAppointment

import org.openqa.selenium.Keys as Keys

'step 1'
WebUI.callTestCase(findTestCase('blocks/Open Browser'), [:], FailureHandling.STOP_ON_FAILURE)

'step 2'
WebUI.callTestCase(findTestCase('blocks/Login'), [('username_') : 'John Doe', ('password_') : 'g3/DOGG74jC3Flrr3yH+3D/yKbOqqUNM'], 
    FailureHandling.STOP_ON_FAILURE)

'step 3'
makeappointment appointment = new makeappointment();
//WebUI.selectOptionByValue(findTestObject('MAKE APPOINTMENT/select_Tokyo CURA Healthcare Center - Hongkong CURA Healthcare Center - Seoul CURA Healthcare Center'), 
//    facility, true)
appointment.selectDropdownOption(findTestObject(PathMakeAppointment.SELECT_TOKYO_CURA),
    facility, true)

WebUI.selectOptionByValue(findTestObject('MAKE APPOINTMENT/select_Tokyo CURA Healthcare Center - Hongkong CURA Healthcare Center - Seoul CURA Healthcare Center'), 
    'Tokyo CURA Healthcare Center ', true, FailureHandling.OPTIONAL)

//WebUI.check(findTestObject('Object Repository/MAKE APPOINTMENT/check_Apply for hospital readmission_hospital_readmission'))
'step 4'
if (!(hospitalReadmissionYesNo)) {
    //WebUI.check(findTestObject('Object Repository/MAKE APPOINTMENT/check_Apply for hospital readmission_hospital_readmission'))
    WebUI.comment('Nilai hospitalReadmissionYesNo: ' + hospitalReadmissionYesNo)
} else {
    WebUI.comment('Nilai hospitalReadmissionYesNo: ' + hospitalReadmissionYesNo)

    WebUI.check(findTestObject('Object Repository/MAKE APPOINTMENT/check_Apply for hospital readmission_hospital_readmission'))
}

//WebUI.click(findTestObject('MAKE APPOINTMENT/RB_Medicaid_programs'))
'step 5 - verifikasi nya ada di step 12'
def selectRadio = healthCareProgram

'step 5 - switch case healthcareProgram'
switch (selectRadio) {
    case selectRadio = 'Medicare':
        println(selectRadio)

        WebUI.click(findTestObject('MAKE APPOINTMENT/RB_Medicare_programs'))

        break
    case selectRadio = 'Medicaid':
        println(selectRadio)

        WebUI.click(findTestObject('MAKE APPOINTMENT/RB_Medicaid_programs'))

        break
    case selectRadio = 'None':
        println(selectRadio)

        WebUI.click(findTestObject('MAKE APPOINTMENT/RB_None_programs'))

        break
    default:
        println(selectRadio)

        WebUI.click(findTestObject('MAKE APPOINTMENT/RB_None_programs'))

        break
}

WebUI.setText(findTestObject('MAKE APPOINTMENT/input_Visit Date'), visitDate)

WebUI.setText(findTestObject('MAKE APPOINTMENT/textarea_Comment'), comment)

WebUI.click(findTestObject('MAKE APPOINTMENT/button_Book Appointment'))

WebUI.delay(3)

WebUI.verifyElementVisible(findTestObject('APPOINTMENT CONFIRMATION/h2_Appointment Confirmation'))

WebUI.verifyElementText(findTestObject('APPOINTMENT CONFIRMATION/p_facility'), facility)

//WebUI.verifyElementText(findTestObject(Object Repository/appointment/p_healthCareProgram'), healthCareProgram)
'step 12 - verifikasi dari step 5'
switch (selectRadio) {
    case selectRadio = 'Medicare':
        println(selectRadio)

        WebUI.verifyElementText(findTestObject('Object Repository/APPOINTMENT CONFIRMATION/p_healthCareProgram'), 'Medicare')

        break
    case selectRadio = 'Medicaid':
        println(selectRadio)

        WebUI.verifyElementText(findTestObject('Object Repository/APPOINTMENT CONFIRMATION/p_healthCareProgram'), 'Medicaid')

        break
    case selectRadio = 'None':
        println(selectRadio)

        WebUI.verifyElementText(findTestObject('Object Repository/APPOINTMENT CONFIRMATION/p_healthCareProgram'), 'None')

        break
    default:
        println(selectRadio)

        WebUI.verifyElementText(findTestObject('Object Repository/APPOINTMENT CONFIRMATION/p_healthCareProgram'), 'None')

        break
}

//WebUI.verifyElementText(findTestObject('APPOINTMENT CONFIRMATION/p_Yes_No'), hospitalReadmissionYesNo)
'step 13 - verifikasi dari step 4'
if (hospitalReadmissionYesNo == true) {
    WebUI.comment('nilai adalah :' + hospitalReadmissionYesNo // Ini akan muncul di Log Viewer
        )

    WebUI.verifyElementText(findTestObject('Object Repository/APPOINTMENT CONFIRMATION/p_Yes_No'), 'Yes')
} else {
    WebUI.verifyElementText(findTestObject('Object Repository/APPOINTMENT CONFIRMATION/p_Yes_No'), 'No')
}

WebUI.verifyElementText(findTestObject('APPOINTMENT CONFIRMATION/p_comment'), comment)

