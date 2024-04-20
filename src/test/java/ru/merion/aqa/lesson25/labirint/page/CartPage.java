package ru.merion.aqa.lesson25.labirint.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class CartPage {
    private final SelenideElement productCounter = $("#basket-default-prod-count2");

    private final SelenideElement emptyCartMessage = $(".g-alttext-small.g-alttext-grey.g-alttext-head");

    public void checkTotalCounterShouldBe(String textToBe) {
        productCounter.shouldHave(text(textToBe));
    }

    public void checkEmptyCartMessageShouldBe(String msg) {
        emptyCartMessage.shouldHave(text(msg));
    }
}
