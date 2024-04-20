package ru.merion.aqa.lesson25.labirint.steps;

import io.cucumber.java.ru.И;
import ru.merion.aqa.lesson25.labirint.page.CartPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartStepdefs {

    private final CartPage cartPage;

    public CartStepdefs(CartPage cartPage) {
        this.cartPage = cartPage;
    }

    @И("проверить, что в корзине счетчик указывает на {string}")
    public void checkCartCounter(String textToBe) {
        cartPage.checkTotalCounterShouldBe(textToBe);
    }

    @И("проверить, что в корзине отображается текст {string}")
    public void checkEmptyCartMessage(String msg) {
        cartPage.checkEmptyCartMessageShouldBe(msg);
    }
}
