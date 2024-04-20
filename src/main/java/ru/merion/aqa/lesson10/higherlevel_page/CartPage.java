package ru.merion.aqa.lesson10.higherlevel_page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CartPage extends BasePage{
    private final SelenideElement cartIcon = $("#basket-default-sumprice-discount");
    public String getCartPrice() {
        return cartIcon.text();
    }

    public CartPage open() {
        Selenide.open("/cart");
        return this;
    }
}
