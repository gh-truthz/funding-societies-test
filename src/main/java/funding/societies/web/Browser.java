package funding.societies.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class Browser {
    private WebDriver webDriver;

    public Browser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-geolocation");
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public WebDriver open(String url) {
        webDriver.get(url);
        return webDriver;
    }

    public void close() {
        webDriver.close();
    }

    public void click(String path) {
        webDriver.findElement(By.xpath(path)).click();
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }
}
