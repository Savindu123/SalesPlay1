package com.salesplay;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
//import java.util.List;

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
    private By categoryList = By.xpath("//a[@class='zoom-in nav-link text-center w-full mb-2 sm:mb-0 py-2 px-2 cat_link ']");
    private By continueButton = By.xpath("//button[@class='btn btn-primary w-32 shadow-md mb-3 ml-auto sign-in_continue']");
    private By getLoginButton = By.xpath("//button[@class='btn btn-default btn_signContinue']");
    private By firstItem1 = By.xpath("//div[contains(text(), 'Added to Price Item 2')]");
    private By cart1stItem = By.xpath("//a[@class='flex items-center p-3 cursor-pointer transition duration-300 ease-in-out bg-white dark:bg-darkmode-600 hover:bg-slate-100 dark:hover:bg-darkmode-400 rounded-md billing_items']");
    private By itemAmount1st = By.xpath("//div[@class='ml-auto font-medium line_price']");
    private By grandTotal = By.xpath("//span[@class='line_grantotal']");
    private By itemPageChargeButton = By.xpath("//div[@id='load_charge']");
    private By finalChargeButton = By.id("btn_charge");
    private By settingsButton = By.id("softeware_div");
    private By printerSetupButton = By.xpath("//div[@id='printer_txt']");
    private By addPrinterButton = By.id("btn_printer_next");
    private By addNewButton = By.xpath("//button[@class='btn btn-primary add_new_device']");
    private By printerNameTextField = By.id("printername");
    private By printReceiptsToggleButton = By.id("setting_printer_type_status");
    private By connectionTypeDropdown = By.id("select_connection_mode");
    private By wifiTextBox = By.id("wifi_ip");
    private By saveButton = By.id("btn_printer_layout");
    private By homeButton = By.id("lang_n_sale_tooltip");
    private By newSaleButton = By.xpath("//button[@class='btn btn-primary mt-2 p-3 w-full start_new_sl']");
    private By manageSaleButton = By.xpath("//span[@class='tab-name lang_m_sale']");
    private By pastReceiptAmount = By.xpath("//div[@class='mb-2 text-5xl']");


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

    public int getItemCount() {
        List<WebElement> links = driver.findElements(itemList);
        return links.size();
    }

    public void clickFirstItem(){

        driver.findElement(firstItem1).click();
    }

    public String getItemAmount1st() {

        return driver.findElement(itemAmount1st).getText();
    }

    public String getGrandTotal()   {

        return driver.findElement(grandTotal).getText();
    }

    public void clickItemPageChargeButton(){

        driver.findElement(itemPageChargeButton).click();
    }

    public void clickFinalChargeButton() {

        driver.findElement(finalChargeButton).click();
    }

    public void clickNewSaleButton() {

        driver.findElement(newSaleButton).click();
    }

    public void clickSettingButton() {

        driver.findElement(settingsButton).click();
    }

    public void clickprinterSetupButton() {

        driver.findElement(printerSetupButton).click();
    }

    public void clickaddPrinterButton() {

        driver.findElement(addPrinterButton).click();
    }

    public void clickaddNewButton() {

        driver.findElement(addNewButton).click();
    }

    public void enterPrinterName(String printerName) {
        driver.findElement(printerNameTextField).clear();
        driver.findElement(printerNameTextField).sendKeys(printerName);
    }

    public void clickprintReceiptsToggleButton() {

        driver.findElement(printReceiptsToggleButton).click();
    }

    public void clickConnectionTypeDropdown() {

        driver.findElement(connectionTypeDropdown).click();
    }

    public void enterPrinterWifi(String printerWifi) {
        driver.findElement(wifiTextBox).clear();
        driver.findElement(wifiTextBox).sendKeys(printerWifi);
    }

    public void clickSaveButton () {

        driver.findElement(saveButton).click();
    }

    public void clickHomeButton () {

        driver.findElement(homeButton).click();
    }

    public int getCategoryCount() {
        List<WebElement> links = driver.findElements(categoryList);
        return links.size();
    }

    public void clickPastReceiptButton () {

        driver.findElement(manageSaleButton).click();
    }

    public String getPastreceiptTotal(){

        return driver.findElement(pastReceiptAmount).getText();
    }
}