package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;
import org.testng.annotations.ITestAnnotation;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class SuitListener implements ITestListener, IAnnotationTransformer {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("New Test Started" + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Successfully Finished" + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed" + result.getName());
        String fileName = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + result.getMethod().getMethodName();
        File f = ((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(f, new File(fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped" + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test Failed but within success percentage" + result.getName());
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

        this.onTestFailure(result);
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("This is onStart method" + context.getOutputDirectory());

    }
    @Override
    public void onFinish(ITestContext context) {

        System.out.println("This is onFinish method" + context.getPassedTests());
        System.out.println("This is onFinish method" + context.getFailedTests());
    }

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}
