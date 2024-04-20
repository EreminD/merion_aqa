package ru.merion.aqa.homeworks.lesson10.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TextInputPage {
    private final SelenideElement input = $("#newButtonName");
    private final SelenideElement button = $("#updatingButton");

    public TextInputPage open() {
        Selenide.open("http://uitestingplayground.com/textinput");
        return this;
    }

    public TextInputPage setButtonName(String newName) {
        input.val(newName);
        button.click();
        return this;
    }

    public String getButtonText() {
        return button.text();
    }
}
