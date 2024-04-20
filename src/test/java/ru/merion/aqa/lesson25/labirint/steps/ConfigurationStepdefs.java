package ru.merion.aqa.lesson25.labirint.steps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.Before;
import io.cucumber.java.ru.И;
import org.openqa.selenium.Cookie;

public class ConfigurationStepdefs {

    @Before
    public void setUp(){
        WebDriverRunner.clearBrowserCache();
    }

    @И("проставить куки {string}")
    public void setCookie(String cookiePair) {
        String[] pair = cookiePair.split("=");
        String key = pair[0];
        String value = pair[1];
        Cookie cookie = new Cookie(key, value);
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
        Selenide.refresh();
    }


}
