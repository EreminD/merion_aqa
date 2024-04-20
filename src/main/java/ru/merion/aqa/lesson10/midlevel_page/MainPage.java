package ru.merion.aqa.lesson10.midlevel_page;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    public void open() {
        Selenide.open("/");
        Cookie cookie = new Cookie("cookie_policy", "1");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);

        Selenide.open("/");
    }

    public void searchFor(String term) {
        $("#search-field").val(term).pressEnter();
    }
}
