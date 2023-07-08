import Pages.AllSongsPage;
import Pages.HomePage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaySongTests extends BaseTest{
    @Test (dataProvider = "ValidLoginData", dataProviderClass = BaseTest.class)
    public void playFromAllSongsTest(String email, String password){
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        AllSongsPage allSongsPage = new AllSongsPage(driver);

        loginPage.login(email, password);
        homePage.clickAllSongs();
        allSongsPage.contextClickSong(1).clickPlayback();

        Assert.assertTrue(homePage.moveToPauseBtn().isDisplayed());
    }
}
