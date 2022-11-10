package pageEvents;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.HomePageElements;
import utils.BaseTest;
import utils.ElementFetch;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;

public class HomePageEvents {

    public void clickOnSignInButton(){
        ElementFetch elementFetch = new ElementFetch();

        //Maximize window
        BaseTest.getDriver().manage().window().maximize();

        //Set the Script Timeout to 20 seconds
        BaseTest.getDriver().manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);



        JavascriptExecutor js = (JavascriptExecutor) BaseTest.getDriver();
        //Call executeAsyncScript() method to wait for 5 seconds
        js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 5000);");

        WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(),Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(HomePageElements.SKIP_COOKIES_NOTICE_BUTTON)));
        wait.until(ExpectedConditions.elementToBeClickable(
                elementFetch.getWebElement("XPATH", HomePageElements.signInButton))).click();

    }
}
