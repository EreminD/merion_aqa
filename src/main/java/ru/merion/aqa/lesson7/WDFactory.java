package ru.merion.aqa.lesson7;

import org.openqa.selenium.Cookie;
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

public class WDFactory {

    public static WebDriver create() {
        return create("chrome");
    }

    public static WebDriver create(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            return create(new ChromeOptions());
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
        WebDriver driver = new ChromeDriver(new ChromeOptions().merge(options));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        driver.get("https://www.labirint.ru/");
        Cookie cookie = new Cookie("cookie_policy", "1");
        driver.manage().addCookie(cookie);
        driver.manage().window().maximize();
        return driver;
    }
}


