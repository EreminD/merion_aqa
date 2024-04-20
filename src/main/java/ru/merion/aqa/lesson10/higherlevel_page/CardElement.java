package ru.merion.aqa.lesson10.higherlevel_page;

import com.codeborne.selenide.SelenideElement;

public class CardElement {
    private final SelenideElement title;
    private final SelenideElement buyButton;

    public CardElement(SelenideElement cardElement) {
        this.title = cardElement.find(".product-card__name");
        this.buyButton = cardElement.find(".btn-tocart.buy-link");
    }

    public String getTitle() {
        return title.text();
    }

    public CardElement addToCart() {
        buyButton.click();
        return this;
    }
}
