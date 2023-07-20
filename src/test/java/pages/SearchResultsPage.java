package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static java.sql.DriverManager.getDriver;

public class SearchResultsPage extends BasePage{
    public SearchResultsPage(WebDriver givenDriver){ super(givenDriver); }
    @FindBy(css = "section.songs button[data-test='view-all-songs-btn']")
    private WebElement viewAllBtn;
    @FindBy(css = "section#songResultsWrapper .btn-add-to")
    private WebElement addToBtn;
    @FindBy(css = "#songResultsWrapper input")
    private WebElement newPlaylistField;
    private WebElement song;
    private WebElement list;

    public SearchResultsPage clickViewAll(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", viewAllBtn);
        return this;
    }
    public SearchResultsPage selectSong(int x){
        song = driver.findElement(By.cssSelector("#songResultsWrapper tr.song-item:nth-child(" + x + ")"));
        click(song); return this;}
    public SearchResultsPage addToList(int x){
        addToBtn.click();
        list = driver.findElement(By.cssSelector("#songResultsWrapper li:nth-child(" + x + ")"));
        click(list); return this;}
    public void addToNewPlaylist(String newPlaylist){ click(addToBtn); findElement(newPlaylistField).sendKeys(newPlaylist, Keys.ENTER);}

}
