package ru.merion.aqa;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class WebDriverFactory {
    private final long timestamp;
    private WebDriver driver;

    public WebDriverFactory() {
        timestamp = System.nanoTime();
    }

    public WebDriver getOrCreate(){
        System.out.println("WDF id = " + timestamp);
        if (driver == null){
            driver = create();
            driver.manage().timeouts().implicitlyWait(Duration.of(4, ChronoUnit.SECONDS));
        }
        return driver;
    }

    public static WebDriver create() {
        return create("chrome");
    }

    public static WebDriver create(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);
            return create(options);
        }

        if (browserName.equalsIgnoreCase("firefox")) {
            return create(new FirefoxOptions());
        }

        if (browserName.equalsIgnoreCase("safari")) {
            return create(new SafariOptions());
        }

        if (browserName.equalsIgnoreCase("edge")) {
            return new EdgeDriver(new EdgeOptions());
        }

        throw new IllegalArgumentException("Неподдерживаемый тип браузера " + browserName + " (chrome|firefox|safari|edge)");
    }

    public static WebDriver create(SafariOptions options) {
        return new SafariDriver(new SafariOptions().merge(options));
    }

    public static WebDriver create(FirefoxOptions options) {
        return new FirefoxDriver(new FirefoxOptions().merge(options));
    }

    public static WebDriver create(EdgeOptions options) {
        return new EdgeDriver(new EdgeOptions().merge(options));
    }

    public static WebDriver create(ChromeOptions options) {
        return new ChromeDriver(new ChromeOptions().merge(options));
    }
}
