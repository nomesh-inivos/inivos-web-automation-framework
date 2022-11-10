package pageEvents;

import org.testng.Assert;
import pageObjects.LoginPageElements;
import utils.BaseTest;
import utils.ElementFetch;

public class LoginPageEvents {

    public void verifyLoginPageIsOpened(){
        ElementFetch elementFetch = new ElementFetch();
        BaseTest.logger.info("Verifying that the login page is loaded or not..!");
        Assert.assertTrue(elementFetch.getWebElementsList("XPATH",
                LoginPageElements.loginText).size() > 0,
                "Login Page did not open!");
    }

    public void enterEmailId(String email){
        BaseTest.logger.info("Entering the Email ID..!");
        new ElementFetch().getWebElement("XPATH", LoginPageElements.loginText).sendKeys(email);
    }

}
