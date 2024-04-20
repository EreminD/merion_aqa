package ru.merion.aqa.lesson25.labirint.steps;

import io.cucumber.java.ru.И;
import io.qameta.allure.Step;
import ru.merion.aqa.lesson25.labirint.page.HeaderElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeaderStepdefs {

    private final HeaderElement header;

    public HeaderStepdefs(HeaderElement header) {
        this.header = header;
    }

    @И("выполнить поиск по слову {string}")
    public void search(String term) {
        header.searchFor(term);
    }

    @И("проверить, что в иконке корзины стоит {string} товаров")
    public void checkCartIconText(String textToBe) {
        header.checkCartCounterShouldBe(textToBe);
    }

    @И("перейти в корзину")
    public void goToCart() {
        header.clickCartIcon();
    }
}
