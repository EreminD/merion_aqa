package ru.merion.aqa.lesson3;

import org.openqa.selenium.WebDriver;
import ru.merion.aqa.WebDriverFactory;

public class GetCurrentInfo {

    public static void main(String[] args) {

        WebDriver driver = WebDriverFactory.create();

        driver.get("https://habr.com/ru");
        String currentUrl = driver.getCurrentUrl();
        String title = driver.getTitle();
        String pageSource = driver.getPageSource();

        driver.quit();
    }
}
