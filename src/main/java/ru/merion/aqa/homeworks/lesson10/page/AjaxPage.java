package ru.merion.aqa.homeworks.lesson10.page;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class AjaxPage {
    private final SelenideElement button = $("#ajaxButton");
    private final SelenideElement content = $("#content p");

    public void open() {
        Selenide.open("http://uitestingplayground.com/ajax");
    }

    public void clickTheButton() {
        button.click();
    }

    public String getContent() {
        return content.text();
    }
}
