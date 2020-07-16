package com.selenium;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DriverBase {


    //List 是接口，不能被构造
    //ArrayList是接口List的一个实现类
    // List a =new List（）；是错误的

    /*
    List a=new Arraylist(); 是正确的。创建一个ArrayList对象，把它上溯到List接口，变成List对象；
    ArrayList中有的但List中没有的属性和方法，List对象就不能再使用了。
     */

    //ArrayList不是线程安全的；通过Collections.synchronizedList 获取一个线程安全的List对象
    private static List<DriverFactory> allDriverThreadPool=
            Collections.synchronizedList(new ArrayList<DriverFactory>());

    /*
    ThreadLocal是线程内部存储类，可以在指定的线程内存储数据；
    数据存储后，只有指定的线程才可以得到存储数据。
    ThreaLocal提供了线程内部存储变量的能力，这些变量不同之处在于每一个线程读取的变量是对应的，相互独立的；
    通过get和set方法就可以得到当前线程的对应值
     */
    private static ThreadLocal<DriverFactory> driverThread;


    //匿名内部类
    //创建一个DreiverFactory的类对象；将该对象加入到allDriverThreadPool 中；
    //并且将DriverFactory的类对象赋值给ThreadLocal对象。
    @BeforeSuite(alwaysRun = true)
    public static void instantiateDriverObject(){
        driverThread=new ThreadLocal<DriverFactory>() {
            @Override
            protected DriverFactory initialValue(){
            DriverFactory driverFactory = new DriverFactory();
            allDriverThreadPool.add(driverFactory);
            return driverFactory;
        }
        };

    }

    public static RemoteWebDriver getDriver(){
        return driverThread.get().getDriver();
    }


    //使用当前类中的getDriver()
    @AfterMethod(alwaysRun = true)
    public static void clearCookies(){
        getDriver().manage().deleteAllCookies();
    }

    @AfterSuite
    public static void closeDriverObjects(){
        for(DriverFactory driverFactory:allDriverThreadPool){
            driverFactory.quitDriver();
        }
    }
}
