package webDriver3;

import com.selenium.Log.Log;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.w3c.dom.DOMConfiguration;

public class MultipleBrowserSearchTest {

    public WebDriver driver;
    String baseUrl="http://www.baidu.com/";

    public static ExpectedCondition<Boolean> pageTitleStartWith(String searchString){
        return driver->driver.getTitle().toLowerCase().startsWith(searchString.toLowerCase());
    }


    @Parameters("browser")
    @BeforeClass
    //将 @Parameters("browser")括号中的参数传递给下行括号中的参数
    public void beforeTest(String Browser){
        DOMConfigurator.configure("Log4j.xml");
        Log.startTestCase("MultipleBrowserSearchTest");
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
    public void testSogouSearch(){
        driver.get(baseUrl+"/");

        WebElement inputBox=driver.findElement(By.id("kw"));
        Assert.assertTrue(inputBox.isDisplayed());

        inputBox.sendKeys("Selenium test");
        inputBox.submit();

        WebDriverWait wait=new WebDriverWait(driver,10000,50);
        wait.until(pageTitleStartWith("Selenium test"));

        Assert.assertTrue(driver.getPageSource().contains("Selenium test"));
    }

    @AfterClass
    public void afterTest(){
        driver.quit();
        Log.endTestCase("MultipleBrowserSearchTest");
    }

}
