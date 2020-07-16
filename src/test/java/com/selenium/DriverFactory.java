package com.selenium;

import javafx.scene.layout.Priority;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

    private RemoteWebDriver webDriver;

    private final String OPERATING_SYSTEM=System.getProperty("os.name").toUpperCase();
    private final String SYSTEM_ARCHITECTURE=System.getProperty("os.arch");


    //如果某个实例已经启动，则获取现有的WebDriver对象；否则启动一个新的WedDriver对象
    RemoteWebDriver getDriver(){
        if(webDriver==null){
            System.out.println(" ");
            System.out.println("Current Operating System: "+OPERATING_SYSTEM);
            System.out.println("Current Architecture: "+SYSTEM_ARCHITECTURE);
            System.out.println("Current Browser Selection: Chrome");
            System.out.println(" ");
            webDriver=new ChromeDriver();
        }
        return webDriver;
    }

    // 当打开多个窗口时，driver.close()用来关于其中一些窗口
    //driver.quit()关闭所有窗口，并且进行清理
    void quitDriver(){
        if(webDriver!=null){
            webDriver.quit();
            webDriver=null;
        }
    }


}
