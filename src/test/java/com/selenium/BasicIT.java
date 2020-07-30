package com.selenium;

import com.selenium.Elements.BaiduHomePageElementCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;


/* Change class's name from BasicTest to BasicIT,
  as maven-failsafe-plugin 在integration-test阶段运行测试，默认会选取以IT结尾的文件。
  然而，maven-surefile-plugin用来做单元测试，选取以TEST结尾的文件,此时线程配置将会被忽略
 */
public class BasicIT extends DriverBase {


    private ExpectedCondition<Boolean> pageTitleStartsWith(final String searchString){
        // Lambada表达式
        // ->左边的driver 为参数，传给右边的方法体；
        // ->右边的方法体返回Boolean型，然后作为pageTitleStartsWith 的返回值返回
        //该方法判断网页是否已跳转到指定页面，即页面的titile以查询字符起始
        return driver->driver.getTitle().toLowerCase().startsWith(searchString.toLowerCase());
    }

    private void googleExampleThatSearchesFor(final String searchString){
        WebDriver driver=DriverBase.getDriver();
        driver.get("https://www.baidu.com");
        WebElement searchField= BaiduHomePageElementCollection.searchField(driver);
        //    WebElement searchField=driver.findElement(By.name("wd"));

        searchField.clear();
        searchField.sendKeys(searchString);

        System.out.println("Page title is:"+driver.getTitle());

        searchField.submit();

        WebDriverWait wait=new WebDriverWait(driver,10,100);
        wait.until(pageTitleStartsWith(searchString));

        System.out.println("Page title is:"+driver.getTitle());

    }

    @Test
    public void googleCheeseExample(){
        googleExampleThatSearchesFor("Cheese!");
    }

    @Test
    public void googleMilkExample(){
        googleExampleThatSearchesFor("Milk!");
    }

    @Test
    public void googleCakeExample(){
        googleExampleThatSearchesFor("Cake!");
    }

}

