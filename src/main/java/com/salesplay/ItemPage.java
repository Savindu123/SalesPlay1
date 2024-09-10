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
        driver.get("https://webpos.salesplaypos.com/registration"); // URL
    }

    private static By itemList = By.xpath("//a[@class='intro-y block col-span-6 sm:col-span-4 2xl:col-span-3 productinfo ']");

}
