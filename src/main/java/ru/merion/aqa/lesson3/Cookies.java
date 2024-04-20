package ru.merion.aqa.lesson3;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import ru.merion.aqa.WebDriverFactory;

import java.util.Set;

public class Cookies {

    public static void main(String[] args) {
        String token = "61790ff29348904bc42217da%2FATTSbo74LkHkgHxbVAlzMyGIsX82jYVzJb7jGUliKbNfewk3UWT43IP3wVrkSkpBKaNMBCE9A340";

        WebDriver driver = WebDriverFactory.create();

        driver.get("https://www.labirint.ru/");
        Cookie cookie = new Cookie("cookie_policy", "1");
        driver.manage().addCookie(cookie);
        driver.get("https://www.labirint.ru/");

        driver.get("https://trello.com");
        driver.manage().addCookie(new Cookie("token", token ));
        driver.get("https://trello.com/w/dmitry97956255/home");

        Set<Cookie> cookies = driver.manage().getCookies();
        Cookie token1 = driver.manage().getCookieNamed("token");

        driver.quit();
    }
}





