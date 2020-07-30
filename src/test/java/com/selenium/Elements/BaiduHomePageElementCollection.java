package com.selenium.Elements;

import com.selenium.locators.BaiduHomePageLocatorCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class BaiduHomePageElementCollection {

    public static WebElement searchField(WebDriver driver){
        /*
        Wait<WebDriver> wait=new FluentWait<>(driver)
                .withTimeout(15, TimeUnit.SECONDS)
                .pollingEvery(500,TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withMessage("The message you will see in if a TimeoutException is thrown");
*/
        WebDriverWait wait=new WebDriverWait(driver,15,500);

        //当被忽略的异常抛出时，程序继续执行
        // 当没有被忽略的异常抛出时，程序中断执行
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);

        //wait的等待时间结束后，抛出timeout异常消息
        wait.withMessage("Looking for the element timeout: TimeoutException is thrown,");
       return wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.name(BaiduHomePageLocatorCollection.searchField_Name));
            }});

    }
}
