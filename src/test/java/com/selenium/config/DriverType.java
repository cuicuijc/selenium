package com.selenium.config;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public enum DriverType implements DriverSetup {
    FIREFOX{
        @Override
        public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            System.setProperty("webdriver.firefox.bin","C:\\Users\\c5270503\\firefox.exe");
            FirefoxOptions options=new FirefoxOptions();
            options.merge(capabilities);
            return new FirefoxDriver(options);
        }
    },
    CHROME{
        @Override
        public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            ChromeOptions options=new ChromeOptions();
            options.merge(capabilities);
           // options.addArguments("--no-default-browser-check");
            return new ChromeDriver(options);
        }
    }
}
