package ru.merion.aqa.lesson10.midlevel_page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;

public class ResultPage {

    public void addAllItemsToCart() {
        $$(".product-card .buy-link").forEach(SelenideElement::click);
    }

}
