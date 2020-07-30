package com.selenium.listeners;

import com.selenium.DriverBase;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.TestListenerAdapter;
import org.testng.ITestResult;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ScreenshotListener extends TestListenerAdapter {

    //create a blank png file
    public boolean createFile(File screenshot) throws IOException {
        boolean fileCreated=false;
        if(screenshot.exists()){
            fileCreated=true;
        }else {
            File parentDirectory=new File(screenshot.getParent());
            if(parentDirectory.exists()||parentDirectory.mkdir()){
                try{
                    fileCreated=screenshot.createNewFile();
                }catch (IOException err){
                    err.printStackTrace();
                }
            }
        }

        return fileCreated;
    }



    //Write screenshot to blank png file
    public void writeScreenshotToFile(WebDriver driver,File screenshot){
        try{
            FileOutputStream screenshotStream=new FileOutputStream(screenshot);
            screenshotStream.write(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES));
            screenshotStream.close();
        }catch (IOException err){
            System.out.println("Unable to write "+screenshot.getAbsolutePath());
            err.printStackTrace();
        }

    }

    @Override
    public void onTestFailure(ITestResult failingTest){
        try{
            WebDriver driver= DriverBase.getDriver();
            String screenshotDirectory=System.getProperty("screenshotDirectory","target/screenshots");
            String screenshotAbsolutePath=
                    screenshotDirectory+File.separator+System.currentTimeMillis()+"_"+failingTest.getName()+".png";

            File screenshot=new File(screenshotAbsolutePath);
            if(createFile(screenshot)){

                try {
                    writeScreenshotToFile(driver,screenshot);
                }catch (ClassCastException ex){
                    writeScreenshotToFile(new Augmenter().augment(driver),screenshot);
                }
                System.out.println("Written screenshot to "+screenshotAbsolutePath);
            }else {
                System.err.println("Unable to create "+screenshotAbsolutePath);
            }

        }catch(Exception e){
            System.err.println("Unable to capture screenshot......");
            e.getCause();
        }
    }
}
