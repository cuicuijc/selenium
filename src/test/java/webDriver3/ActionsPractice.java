package webDriver3;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import javax.swing.*;

public class ActionsPractice {

    RemoteWebDriver driver;

    @Parameters("browser")
    @BeforeClass
    public void beforeTest(String Browser){
        if(Browser.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.firefox.bin","C:\\Users\\c5270503\\firefox.exe");
            System.setProperty("webdriver.gecko.driver","C:\\GitIDEA\\selenium-testng-study\\geckodriver.exe");

            driver=new FirefoxDriver();
        }else {
            System.getProperty("webdriver.chrome.driver","C:\\webDriver\\chromedriver.exe");
            driver=new ChromeDriver();
        }
    }

    @Test
    public void practice1(){
        driver.navigate().to("https://www.baidu.com//");
        Actions action=new Actions(driver);
        //action.sendKeys("Selina").perform();
        action.click(driver.findElement(By.id("kw"))).sendKeys("Selenium").perform();
        driver.quit();
    }
}
