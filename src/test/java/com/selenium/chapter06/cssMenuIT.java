package com.selenium.chapter06;

import com.selenium.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class cssMenuIT extends DriverBase {

    RemoteWebDriver driver;

    @BeforeMethod
    public void bingDriver (){
        driver=DriverBase.getDriver();
    }

    @Test
    public void automateCSSMenu() throws InterruptedException {
        driver.get("http://web.masteringselenium.com/cssMenu.html");

        Actions action=new Actions(driver);
        WebDriverWait wait=new WebDriverWait(driver,10000,50);

        WebElement servicesMenu=driver.findElement(By.id("services"));
        WebElement webDevelopmentSubMenu=driver.findElement(By.cssSelector("#services > ul > li:nth-child(2)"));

        action.moveToElement(servicesMenu).perform();
        wait.until(ExpectedConditions.visibilityOf(webDevelopmentSubMenu));
        action.moveToElement(webDevelopmentSubMenu).click().perform();

    }
}
