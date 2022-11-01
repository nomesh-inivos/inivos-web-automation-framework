package main.java.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

public class Driver {

    private static WebDriver driver ;
    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        Driver.driver = driver;
    }

    public static void setUpDriver(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver. chrome.driver", System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver");
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "geckodriver");
            driver = new FirefoxDriver();
        } else {
            System.setProperty("webdriver. chrome.driver", System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver");
            driver = new ChromeDriver();
        }
        setDriver(driver);
    }
}
