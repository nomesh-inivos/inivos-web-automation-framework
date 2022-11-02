import org.testng.annotations.Test;
import pageEvents.HomePageEvents;
import pageEvents.LoginPageEvents;
import utils.BaseTest;

public class SampleTest extends BaseTest {

    @Test
    public void samplemethodForEmailEntering(){
        HomePageEvents homePageEvents = new HomePageEvents();
        homePageEvents.clickOnSignInButton();

        LoginPageEvents loginPageEvents = new LoginPageEvents();
        loginPageEvents.verifyLoginPageIsOpened();
        loginPageEvents.enterEmailId("nomeshd@gmail.com");

    }
}
