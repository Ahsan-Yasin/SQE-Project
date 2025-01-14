package com.biswa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ExtentTest test;

    public static void setExtent(){
       ExtentSparkReporter reporter = new ExtentSparkReporter("ExtentReport.html");
       extent = new ExtentReports();
       extent.attachReporter(reporter);
    }

    public static void createTest(String testName){
        test = extent.createTest(testName);
    }

    public static void flush(){
        extent.flush();
    }

    public static ExtentTest getTest(){
        return test;
    }
}
