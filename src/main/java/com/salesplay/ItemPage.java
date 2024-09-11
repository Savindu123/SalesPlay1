package com.salesplay;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ItemPage {

    public static List<WebElement> itemList1;
    private static WebDriver driver;

     //Constructor
    public ItemPage(WebDriver driver) {
        this.driver = driver;
    }

    private static By firstItem = By.xpath("//a[@class='intro-y block col-span-6 sm:col-span-4 2xl:col-span-3 productinfo ']");
    private static By firstItem1 = By.linkText("Added To Price 1");

    public void clickFirstItem(){

        driver.findElement(firstItem1).click();

    }
}
