package ru.merion.aqa.homeworks.lesson10.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class AuthPage {
    private final SelenideElement login = $("#user-name");
    private final SelenideElement pass = $("#password");
    private final SelenideElement button = $("#login-button");

    public AuthPage open() {
        Selenide.open("https://www.saucedemo.com/");
        return this;
    }

    public CatalogPage loginAs(String username, String password) {
        login.val(username);
        pass.val(password);
        button.click();
        return new CatalogPage();
    }
}
