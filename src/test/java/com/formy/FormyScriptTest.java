package com.formy;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.formy.genericutility.BaseClass;
import com.formy.genericutility.WebDriverUtility;
import com.formy.objectrepository.FormyPage;

public class FormyScriptTest extends BaseClass{
	
	@Test
	public void formyApplication() {
		
		FormyPage fp= new FormyPage(driver);
		String expectedTitle="Formy";
		
		String actualTilte = driver.getTitle();
		Assert.assertEquals(actualTilte, expectedTitle);
		Reporter.log("Title is Verified", true);
		
		
		fp.formyApplication("Sonali", "Tiwari", "Automation");
		fp.getEducationLevel().click();
		fp.getSexCheckBox().click();
		WebDriverUtility.select(fp.getExperienceDropDown(), 2);
		
		fp.getDatePicker().click();
		fp.selectDate();
		fp.getSubmitbtn().click();;
		
		String expectedSucessFullMessage="The form was successfully submitted!";
		String actualSuccesFullMessage = fp.getSuccessfullMessage().getText();
		
		Assert.assertEquals(true, actualSuccesFullMessage.contains(expectedSucessFullMessage));
		Reporter.log("Form is Successfully Submited", true);
	}

}
