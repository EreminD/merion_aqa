package ru.merion.aqa.lesson10.higherlevel_page;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.$;

public class MainPage  extends BasePage {

    public MainPage open() {
        Selenide.open("/");
        Cookie cookie = new Cookie("cookie_policy", "1");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);

        Selenide.open("/");
        return this;
    }

}
