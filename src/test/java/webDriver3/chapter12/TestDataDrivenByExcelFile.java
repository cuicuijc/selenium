package webDriver3.chapter12;

import com.google.gson.internal.$Gson$Preconditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestDataDrivenByExcelFile {
    String baseUrl="http://www.sogou.com";
    public WebDriver driver;
    //the path of CSV
    public static String testDataExcelFilePath="C:\\GitIDEA\\selenium-testng-study\\src\\test\\java\\webDriver3\\chapter12\\testData\\testData.xlsx";


    @DataProvider(name="testData")
    public static Object[][] words() throws IOException {
        return ExcelUtil.getTestData(testDataExcelFilePath,"Sheet1");
    }

    @Test(dataProvider="testData")
    public void testSearch(String searchStr1,String searchStr2,String searchStr3){
        System.setProperty("webdriver.firefox.bin","C:\\Users\\c5270503\\firefox.exe");
        System.setProperty("webdriver.gecko.driver","C:\\GitIDEA\\selenium-testng-study\\geckodriver.exe");
        driver=new FirefoxDriver();
        driver.get(baseUrl);

        driver.findElement(By.id("query")).sendKeys(searchStr1+" "+searchStr2);
        driver.findElement(By.id("stb")).click();

        (new WebDriverWait(driver,10)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d){
                return d.findElement(By.id("s_footer")).getText().contains("意见反馈及投诉");
            }
        });

        Assert.assertTrue(driver.getPageSource().contains(searchStr3));

    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }
}
