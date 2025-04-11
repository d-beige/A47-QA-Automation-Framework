package pages;

import org.openqa.selenium.*;
import org.testng.Assert;

public class HomePage extends BasePage{
    public HomePage(WebDriver givenDriver){ super(givenDriver); }

    public HomePage verifyLogin(){ Assert.assertTrue(getAvatar().isDisplayed()); return this; }

}
