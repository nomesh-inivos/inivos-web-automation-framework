package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;

import static utils.CommonUtils.*;

public class BaseTest {

    private static WebDriver driver;
    private static ExtentReports extent;
    private static ExtentHtmlReporter htmlReporter;
    public static ExtentTest logger;


    @BeforeTest
    public void beforeTest() {
        htmlReporter = new ExtentHtmlReporter(System.getProperty ("user.dir") + File.separator+ "reports" +File.separator + "AutomationReport.html");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config(). setReportName ("Automation Test Results");
        htmlReporter.config(). setTheme (Theme. DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Automation Tester", "Inivos - QA Department");

    }

    @BeforeMethod
    @Parameters(value={"browserName"})
    public void beforeMethodTest(String browserName, Method testMethod) {
        logger = extent.createTest(testMethod.getName());
        setupDriver(browserName);
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com");
   }

    @AfterMethod
    public void afterMethodTest(ITestResult result) {
        String methodName;
        String logText;
        Markup m;
        if(result.getStatus() == ITestResult.SUCCESS){
            methodName = result.getMethod().getMethodName();
            logText = "Test Case: " +methodName+ "Passed!";
            m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
            logger.log(Status.PASS, m);
        }else if(result.getStatus() == ITestResult.FAILURE) {
            methodName = result.getMethod().getMethodName();
            logText = "Test Case: " +methodName+ "Failed!";
            m = MarkupHelper.createLabel(logText, ExtentColor.RED);
            logger.log(Status.FAIL, m);
        }
        driver.quit();
    }

    @AfterTest
    public void AfterTest() {
        if(driver !=null){
            driver.quit();
        }
        extent.flush();
    }

    /**
     * Setup the Browser driver based on BrowserName and OS type
     * @param browserName
     */
    public void setupDriver(String browserName) {
        String driverPath= System.getProperty("user.dir") + File.separator + "drivers" + File.separator ;

        if (browserName.equalsIgnoreCase("chrome")) {
            if(isMac()){ driverPath+= "chromedriver_mac";
            } else if (isWindows()) { driverPath+= "chromedriver.exe";
            }else if (isLinux()) { driverPath+= "chromedriver_linux"; }

            System.setProperty("webdriver.chrome.driver", driverPath);
            System.out.println("driverPath: "+driverPath);
            driver = new ChromeDriver();

        } else if (browserName.equalsIgnoreCase("firefox")) {

            if(isMac()){   driverPath+= "geckodriver_mac";
            }else if (isWindows()) { driverPath+= "geckodriver.exe";
            }else if (isLinux()) { driverPath+= "geckodriver_linux_64"; }

            System.setProperty("webdriver.gecko.driver", driverPath);
            System.out.println("driverPath: "+driverPath);
            driver = new FirefoxDriver();

        } else {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver.exe");
            driver = new ChromeDriver();
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
