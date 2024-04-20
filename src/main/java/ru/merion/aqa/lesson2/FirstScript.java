package ru.merion.aqa.lesson2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.merion.aqa.WebDriverFactory;

import java.io.File;


public class FirstScript {

    public static void main(String[] args) {
        ChromeOptions opts = new ChromeOptions();
        opts.addExtensions(new File("src/main/resources/chrome_ext/User-Agent-Switcher-for-Chrome.crx"));

        WebDriver driver = WebDriverFactory.create(opts);
        driver.get("https://ya.ru");

    }
}
