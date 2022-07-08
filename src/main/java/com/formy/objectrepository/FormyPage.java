package com.formy.objectrepository;

import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FormyPage extends BasePage{
	
	@FindBy(id = "first-name")
	private WebElement firstNameTxtField;
	
	@FindBy(id = "last-name")
	private WebElement lastNameTxtField;
	
	@FindBy(id="job-title")
	private WebElement jobTitleTxtField;
	
	@FindBy(id = "radio-button-2")
	private WebElement educationLevel;
	
	@FindBy(id = "checkbox-2")
	private WebElement sexCheckBox;
	
	@FindBy(id = "select-menu")
	private WebElement experienceDropDown;
	
	@FindBy(css = "a[class='btn btn-lg btn-primary']")
	private WebElement submitbtn;
	
	@FindBy(id = "datepicker")
	private WebElement datePicker;
	
	@FindBy(css  = "[class='alert alert-success']")
	private WebElement successfullMessage;
	
	
	public WebElement getSuccessfullMessage() {
		return successfullMessage;
	}
	public WebElement getSubmitbtn() {
		return submitbtn;
	}
	public WebElement getFirstNameTxtField() {
		return firstNameTxtField;
	}

	public WebElement getLastNameTxtField() {
		return lastNameTxtField;
	}

	public WebElement getJobTitleTxtField() {
		return jobTitleTxtField;
	}

	public WebElement getEducationLevel() {
		return educationLevel;
	}

	public WebElement getSexCheckBox() {
		return sexCheckBox;
	}

	public WebElement getExperienceDropDown() {
		return experienceDropDown;
	}

	public WebElement getDatePicker() {
		return datePicker;
	}

	public FormyPage(WebDriver driver) {
		super(driver);
	}

	public void formyApplication(String firstName,String lastName,String jobTitle) {
		firstNameTxtField.sendKeys(firstName);
		lastNameTxtField.sendKeys(lastName);
		jobTitleTxtField.sendKeys(jobTitle);
	}
	
	public void selectDate() {
		LocalDate ldt = LocalDate.now().plusDays(2);
		int year = ldt.getYear();
		int day = ldt.getDayOfMonth();
		String month = ldt.getMonth().name();
		month=month.substring(0,1).toUpperCase()+month.substring(1).toLowerCase();
		 WebElement date = driver.findElement(By.xpath("//th[text()='"+month+" "+year+"']/ancestor::table[@class='table-condensed']//td[text()='"+day+"']"));
		 date.click();
	}
}
