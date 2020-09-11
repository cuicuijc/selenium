package com.selenium.Log;

import org.apache.log4j.Logger;

public class Log {

    private static Logger logger= Logger.getLogger(Log.class.getName());

    public static void startTestCase(String sTestCaseName){
        logger.info("------------------------------------------------");
        logger.info("********  "+sTestCaseName+"   *******");
    }

    public static void endTestCase(String sTestCaseName){
        logger.info("------------------------------------------------");
    }


    public static void debug(String msg){
        logger.debug(msg);
    }
    public static void info(String msg){
        logger.info(msg);
    }

    public static void error(String msg){
        logger.error(msg);
    }

    public static void warn(String msg){
        logger.warn(msg);
    }

}
