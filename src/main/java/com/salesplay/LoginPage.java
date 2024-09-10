package com.salesplay;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;

public class LoginPage {
    private WebDriver driver;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        driver.get("https://webpos.salesplaypos.com/sign_in"); // URL
    }

    // Locators
    private By emailField = By.id("replace_username");
    private By passwordField = By.id("replace_password");
    private By loginButton = By.id("sign_in_link");
    private By validationMessage = By.xpath("//label[@class='err_msg']");
    private By itemList = By.xpath("//a[@class='intro-y block col-span-6 sm:col-span-4 2xl:col-span-3 productinfo ']");
    private By  continueButton = By.xpath("//button[@class='btn btn-primary w-32 shadow-md mb-3 ml-auto sign-in_continue']");
    private By getLoginButton = By.xpath("//button[@class='btn btn-default btn_signContinue']");
    
    // Actions
    public void enterEmail(String email) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    // Actions
    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    // Actions
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    // Actions
    public String getValidationMessage() {
        return driver.findElement(validationMessage).getText();
    }

    public void clickContinueButton() {
        driver.findElement(continueButton).click();
    }

    public void clickLoginButton2() {
        driver.findElement(getLoginButton).click();
    }

//    public double getItemCount(){
//        return driver.List<WebElement> links = driver.findElements(itemList);
//    }
}