import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework19 extends BaseTest{
    @Test
    public void deletePlaylistTest() throws InterruptedException{

        //        Enter email into the email field
        enterEmailField("daviyontae.floyd@testpro.io");

//        Enter password into the password field
        enterPasswordField("te$t$tudent");

//        Click the Login button
        clickSubmit();

        selectPlaylist(3);
        Thread.sleep(5000);

        deletePlaylist();

//        Verify 'Deleted playlist "Delete Playlist."' notification
        WebElement notification = driver.findElement(By.cssSelector("div.success.show"));
        Assert.assertEquals(notification.getText(), "Deleted playlist \"Delete Playlist.\"");
    }
    public void selectPlaylist(int x){
        WebElement playlist = driver.findElement(By.cssSelector("section#playlists ul> li:nth-child(" + x + ")"));
        playlist.click();
    }
    public void deletePlaylist() throws InterruptedException{
        WebElement deleteBtn = driver.findElement(By.cssSelector("button.del"));
        deleteBtn.click();
        Thread.sleep(5000);
        /*for non-empty playlist
        WebElement okBtn = driver.findElement(By.cssSelector("button.ok"));
        okBtn.click();
        Thread.sleep(5000);*/
    }
}
