package ru.merion.aqa.lesson25.labirint.steps;

import io.cucumber.java.ru.И;
import ru.merion.aqa.lesson25.labirint.page.ResultPage;

public class ResultPageStepdefs {

    private final ResultPage resultPage;

    public ResultPageStepdefs(ResultPage resultPage) {
        this.resultPage = resultPage;
    }

    @И("добавить все товары в корзину")
    public void addAllItems() {
        resultPage.addAllItemsToCart();
    }

    @И("проверить, что на странице отображается текст {string}")
    public void checkEmptyMessage(String msg) {
        resultPage.checkEmptyResultMessageShouldBe(msg);
    }
}
