import Pages.HomePage;
import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;



public class LoginTests extends BaseTest {
    @Test (dataProvider = "ValidLoginData", dataProviderClass = BaseTest.class)
    public void successfulLoginTest(String email, String password){
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.login(email, password);
        Assert.assertTrue(homePage.getAvatar().isDisplayed());
    }

    @Test (dataProvider = "InvalidLoginData", dataProviderClass = BaseTest.class)
    public void invalidLoginTest(String email, String password){
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(email, password);
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }
}
