package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
    public HomePage(WebDriver givenDriver){ super(givenDriver); }
    @FindBy(css = "img.avatar")
    private WebElement avatarIcon;
    @FindBy(css = "span.name")
    private WebElement username;
    @FindBy(css = "li a.songs")
    private WebElement allSongs;
    @FindBy(css = ".pause .fa.fa-pause")
    private WebElement pauseBtn;
    @FindBy(css = ".play .fa.fa-play")
    private WebElement playBtn;
    @FindBy(css = "input[type='search']")
    private WebElement searchField;
    @FindBy(css = "i[title='Play next song']")
    private WebElement nextSongBtn;
    @FindBy(css = "i[title='Play previous song']")
    private WebElement prevSongBtn;
    @FindBy(css = "[name='name']")
    private WebElement playlistInputField;
    private WebElement playlist;

    public WebElement getAvatar(){ return avatarIcon; }
    public WebElement getUsername(){ return username; }
    public WebElement moveToPauseBtn(){ return findElement(pauseBtn); }
    public WebElement moveToPlayBtn(){ return findElement(playBtn); }
    public void clickAllSongs(){ allSongs.click(); }
    public void selectPlaylist(int x){
        playlist = driver.findElement(By.cssSelector("#playlists ul > li:nth-child(" + x + ")"));
        click(playlist); }
    public HomePage clickPrevSong(){ click(prevSongBtn); return this;}
    public HomePage clickNextSong(){ click(nextSongBtn); return this;}
    public HomePage searchSong(String song){ searchField.sendKeys(song); return this;}
    public HomePage renamePlaylist(int x, String newPlaylistName){
        playlist = driver.findElement(By.cssSelector("#playlists ul > li:nth-child(" + x + ")"));
        doubleClick(playlist);
        playlistInputField.sendKeys(Keys.chord(Keys.CONTROL, "A"), Keys.BACK_SPACE, newPlaylistName, Keys.ENTER);
        return this;
    }
}
