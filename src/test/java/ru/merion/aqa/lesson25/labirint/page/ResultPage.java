package ru.merion.aqa.lesson25.labirint.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ResultPage {
    private final SelenideElement emptyResultMessage = $("h1");

    private final ElementsCollection cards = $$(".product-card");

    public void addAllItemsToCart() {
        cards.forEach(card -> card.find(".buy-link").click());
    }

    public void checkEmptyResultMessageShouldBe(String msg) {
        emptyResultMessage.shouldHave(text(msg));
    }
}
