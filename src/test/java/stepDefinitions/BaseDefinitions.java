package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class BaseDefinitions {
    private static final ThreadLocal<WebDriver> THREAD_LOCAL = new ThreadLocal<>();

    private static final String GRID_URL = "http://192.168.1.254:4444";
    private static final String LAMBDA_HUB = "https://hub.lambdatest.com/wd/hub";

    public static WebDriver getThreadLocal() {
        return THREAD_LOCAL.get();
    }

    @Before
    public void initializeBrowser() throws MalformedURLException {
        WebDriver driver = pickBrowser(System.getProperty("browser", "chrome")); // fallback to chrome
        THREAD_LOCAL.set(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        driver.manage().window().maximize();
    }

    public WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("-private");
                return new FirefoxDriver(firefoxOptions);

            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();

            case "grid-firefox":
                capabilities.setCapability("browserName", "firefox");
                return new RemoteWebDriver(URI.create(GRID_URL).toURL(), capabilities);

            case "grid-edge":
                capabilities.setCapability("browserName", "edge");
                return new RemoteWebDriver(URI.create(GRID_URL).toURL(), capabilities);

            case "grid-chrome":
                capabilities.setCapability("browserName", "chrome");
                return new RemoteWebDriver(URI.create(GRID_URL).toURL(), capabilities);

            case "cloud":
                return lambdaTest();

            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-notifications", "--remote-allow-origins=*", "--incognito", "--start-maximized");
                chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                return new ChromeDriver(chromeOptions);
        }
    }

    public static WebDriver lambdaTest() throws MalformedURLException {
        EdgeOptions options = new EdgeOptions();
        options.setPlatformName("Windows 10");
        options.setBrowserVersion("112.0");

        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("username", System.getenv("LT_USERNAME")); // read from env vars
        ltOptions.put("accessKey", System.getenv("LT_ACCESS_KEY")); // read from env vars
        ltOptions.put("visual", true);
        ltOptions.put("video", true);
        ltOptions.put("build", "ReggTesting");
        ltOptions.put("project", "Untitled");
        ltOptions.put("w3c", true);

        options.setCapability("LT:Options", ltOptions);

        return new RemoteWebDriver(new URL(LAMBDA_HUB), options);
    }

    @After
    public void quitBrowser() {
        WebDriver driver = THREAD_LOCAL.get();
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                THREAD_LOCAL.remove();
            }
        }
    }

    @DataProvider(name = "ValidLoginData")
    public static Object[][] validParams() {
        return new Object[][]{
                {"daviyontae.floyd@testpro.io", "te$t$tudent"},
        };
    }

    @DataProvider(name = "InvalidLoginData")
    public static Object[][] invalidParams() {
        return new Object[][]{
                {"daviyontae.floyd@testpro.io", "wrongPassword"},
                {"invalid.email@gamil.com", "te$t$tudent"},
                {"", ""}
        };
    }
}
