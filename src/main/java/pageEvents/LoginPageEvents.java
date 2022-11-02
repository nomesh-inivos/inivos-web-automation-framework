package pageEvents;

import org.testng.Assert;
import pageObjects.LoginPageElements;
import utils.ElementFetch;

public class LoginPageEvents {

    public void verifyLoginPageIsOpened(){
        ElementFetch elementFetch = new ElementFetch();
        Assert.assertTrue(elementFetch.getWebElementsList("XPATH",
                LoginPageElements.loginText).size() > 0,
                "Login Page did not open!");
    }

    public void enterEmailId(String email){
        new ElementFetch().getWebElement("XPATH", LoginPageElements.emailAddress).sendKeys(email);
    }

}
