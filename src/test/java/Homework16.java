import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework16 extends BaseTest {
    @Test
    public void registrationNavigation(){

        String url2 = "https://qa.koel.app/registration";
//      Open the url in Chrome
        openLoginLink();

//        Click the Registration link
        WebElement registrationLink = driver.findElement(By.cssSelector("a[href='registration']"));
        registrationLink.click();

//        Verify user redirected to Registration page
        Assert.assertEquals(driver.getCurrentUrl(), url2);
    }
}
