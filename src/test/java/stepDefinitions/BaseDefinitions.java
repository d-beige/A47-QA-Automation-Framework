package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class BaseDefinitions {
    private static final ThreadLocal<WebDriver> THREAD_LOCAL = new ThreadLocal<>();
    static WebDriver driver = null;
    static WebDriverWait wait;
    static Actions actions;

    public static WebDriver getThreadLocal() {
        return THREAD_LOCAL.get();
    }
    @Before
    public void initializeBrowser() throws MalformedURLException {
        THREAD_LOCAL.set(pickBrowser(System.getProperty("browser")));
        THREAD_LOCAL.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        THREAD_LOCAL.get().manage().window().maximize();
    }
    public WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String gridURL = "http://192.168.1.254:4444/";
        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return driver = new FirefoxDriver();
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions eOptions = new EdgeOptions();
                eOptions.addArguments("--remote-allow-origins=*");
                return driver = new EdgeDriver(eOptions);
            case "grid-firefox":
                caps.setCapability("browserName", "firefox");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
            case "grid-edge":
                caps.setCapability("browserName", "MicrosoftEdge");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
            case "grid-chrome":
                caps.setCapability("browserName", "chrome");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
            case "cloud":
                return lambdaTest();
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions cOptions = new ChromeOptions();
                cOptions.addArguments("--remote-allow-origins=*");
                return driver = new ChromeDriver(cOptions);
        }
    }
    public static WebDriver lambdaTest() throws MalformedURLException {
        String hubURL = "https://hub.lambdatest.com/wd/hub";

        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("114.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "daviyontae.floyd");
        ltOptions.put("accessKey", "pkhma9IsnRQiTj7iyCg61dxihV4lMnhjvogRSf02ghI04rd8NL");
        ltOptions.put("video", true);
        ltOptions.put("build", "Chrome");
        ltOptions.put("project", "Untitled");
        ltOptions.put("name", "Delete/ Add Playlist");
        ltOptions.put("selenium_version", "4.0.0");
        ltOptions.put("w3c", true);
        browserOptions.setCapability("LT:Options", ltOptions);

        return new RemoteWebDriver(new URL(hubURL), browserOptions);
    }
    @After
    public void quitBrowser(){
//        Quit browser
        THREAD_LOCAL.get().close();
        THREAD_LOCAL.remove();
    }
    @DataProvider(name="ValidLoginData")
    public static Object[][] validParams(){
        return new Object[][]{
                {"daviyontae.floyd@testpro.io", "te$t$tudent"},
        };
    }
    @DataProvider(name="InvalidLoginData")
    public static Object[][] invalidParams(){
        return new Object[][]{
                {"daviyontae.floyd@testpro.io", "wrongPassword"},
                {"invalid.email@gamil.com", "te$t$tudent"},
                {"", ""}
        };
    }
}
