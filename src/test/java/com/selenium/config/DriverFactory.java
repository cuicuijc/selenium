package com.selenium.config;

import javafx.scene.layout.Priority;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.selenium.config.DriverType.*;

public class DriverFactory {

    private RemoteWebDriver webDriver;
    private DriverType selectedDriverType;

    private final String OPERATING_SYSTEM=System.getProperty("os.name").toUpperCase();
    private final String SYSTEM_ARCHITECTURE=System.getProperty("os.arch");

    public DriverFactory(){
        DriverType driverType=FIREFOX;

        //从pom文件中或许browser的属性，并且将该属性赋值给driverType
        String browser=System.getProperty("browser",driverType.toString()).toUpperCase();
        try{
            driverType=valueOf(browser);
        }catch (IllegalArgumentException ignored){
            System.err.println("Unknown driver specified, defaulting to '"+driverType+"'");
        }catch (NullPointerException ignored){
            System.err.println("No driver specified, defaulting to '"+driverType+"'");
        }
        selectedDriverType=driverType;
    }

    //如果某个实例已经启动，则获取现有的WebDriver对象；否则启动一个新的WedDriver对象
   public RemoteWebDriver getDriver(){
        if(webDriver==null){
            instantiateWebDriver(selectedDriverType);
        }
        return webDriver;
    }

    // 当打开多个窗口时，driver.close()用来关于其中一些窗口
    //driver.quit()关闭所有窗口，并且进行清理
    public void quitDriver(){
        if(webDriver!=null){
            webDriver.quit();
            webDriver=null;
        }
    }

    public void instantiateWebDriver(DriverType driverType){
        System.out.println(" ");
        System.out.println("Current Operating System: "+OPERATING_SYSTEM);
        System.out.println("Current Architecture: "+SYSTEM_ARCHITECTURE);
        System.out.println("Current Browser Selection:"+selectedDriverType);
        System.out.println(" ");

        //设置自动化相关参数
        DesiredCapabilities capabilities=new DesiredCapabilities();
        webDriver=driverType.getWebDriverObject(capabilities);

    }


}
