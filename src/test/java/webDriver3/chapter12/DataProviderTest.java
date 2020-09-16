package webDriver3.chapter12;

//Using TestNG to driver data

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DataProviderTest {

    private static WebDriver driver;

    @DataProvider(name="searchWords")
    public static Object[][] words(){
        return new Object[][]{{"还珠格格","主演","赵薇"},{"倚天屠龙记","主演","赵敏"},{"锦衣之下","主演","谭松韵"}};
    }

    @Test(dataProvider="searchWords")
    public void test(String searchWords1, String searchWords2,String searchWords3){
        System.setProperty("webdriver.firefox.bin","C:\\Users\\c5270503\\firefox.exe");
        System.setProperty("webdriver.gecko.driver","C:\\GitIDEA\\selenium-testng-study\\geckodriver.exe");
        driver=new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://www.sogou.com");

        driver.findElement(By.id("query")).sendKeys(searchWords1+" "+searchWords2);
        driver.findElement(By.id("stb")).click();

        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        Assert.assertTrue(driver.getPageSource().contains(searchWords3));
        driver.quit();
    }
}
