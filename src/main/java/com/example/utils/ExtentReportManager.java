package com.example.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            extent = new ExtentReports();
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/extent-report.html");
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle("Test Report");
            sparkReporter.config().setReportName("Search Functionality Test Report");
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }
}
