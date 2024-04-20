package ru.merion.aqa.homeworks.lesson10.page;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;

public class RegisterPage {


    public RegisterPage open() {
        Selenide.open("https://bonigarcia.dev/selenium-webdriver-java/data-types.html");
        return this;
    }

    public void set(String field, String value) {
        $("[name=" + field + "]").val(value);
    }

    public void submitForm() {
        $("[type=submit]").click();
    }

    public String getCssProperty(String field, String cssProperty) {
        return $(field).getCssValue(cssProperty);
    }

    public String getBgColor(String field) {
        return $(field).getCssValue("background-color");
    }
}
