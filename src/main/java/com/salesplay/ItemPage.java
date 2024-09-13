package com.salesplay;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ItemPage {

    private static WebDriver driver;

     //Constructor
//    public ItemPage(WebDriver driver) {
//        this.driver = driver;
//    }


    private static By firstItem1 = By.xpath("//div[contains(text(), 'Test product 2')]");

    public void clickFirstItem(){

        driver.findElement(firstItem1).click();

    }
}
