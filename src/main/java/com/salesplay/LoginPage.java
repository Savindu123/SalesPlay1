package com.salesplay;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        driver.get("https://webpos.salesplaypos.com/sign_in"); // Replace with your URL
    }

    // Locators
    private By emailField = By.id("replace_username");
    private By passwordField = By.id("replace_password");
    private By loginButton = By.id("sign_in_link");
    private By validationMessage = By.xpath("//label[@class='err_msg']");

    // Actions
    public void enterEmail(String email) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public String getValidationMessage() {
        return driver.findElement(validationMessage).getText();
    }
}

