package com.salesplay;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {

    private static WebDriver driver;

    // Constructor
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        driver.get("https://webpos.salesplaypos.com/registration"); // URL
    }

    //Locations
    private static By emailField = By.id("username");
    private static By passwordField = By.id("customerPassword");
    private By bussinessNameField = By.id("customerName");
    private By submitButton = By.id("submit1");
    private By TermsandConditions = By.id("remember_me");
    private By validationMessageEmail = By.xpath("//label[@for='username' and @class='error']");
    private By ValidationMessagePassword =  By.xpath("//label[@for='customerPassword' and @class='error']");
    private By ValidationMessageBusinessname =  By.xpath("//label[@for='customerName' and @class='error']");

    private By RegistrationSuccessfullMessage = By.xpath("//*[ @class='text-msg']");

    private By Emailconfirmationbuton = By.xpath("//button [@class ='btn btn-secondary full-width-btn']");

    private By exeSkipButton = By.id("skipInstallModal");

    private By exeDownloadButton = By.id("installApp");

    private By createProductbutton = By.xpath("//button [@class ='btn btn-primary']");


    // Actions
    public static void enterEmail(String email) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    public static void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void enterbussinessName(String bussinessName) {
        driver.findElement(bussinessNameField).clear();
        driver.findElement(bussinessNameField).sendKeys(bussinessName);
    }

    public void clickTermsandConditionsButton() {
        driver.findElement(TermsandConditions).click();
    }

    public void clickSubmitButton() {
        driver.findElement(submitButton).click();
    }

    public String getEmailValidationMessage() {

        return driver.findElement(validationMessageEmail).getText();
    }

    public String getPasswordValidationMessage() {

        return driver.findElement(ValidationMessagePassword).getText();
    }

    public String getBusinessNameValidationMessage() {

        return driver.findElement(ValidationMessageBusinessname).getText();
    }

    public String getRegistrationsuccestionMessage() {

        return driver.findElement(RegistrationSuccessfullMessage).getText();
    }

    public void clickOnEmailConfirmButton(){
        driver.findElement(Emailconfirmationbuton).click();
    }

    public void clickOnexeSkipButton(){
        driver.findElement(exeSkipButton).click();
    }

    public void clickOnexedownloadButton(){
        driver.findElement(exeDownloadButton).click();
    }

    public void clickOnCreateProductbutton(){
        driver.findElement(createProductbutton).click();
    }
}