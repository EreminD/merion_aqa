package ru.merion.aqa.lesson25.labirint.steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import ru.merion.aqa.lesson25.labirint.page.MainPage;

public class MainPageStepdefs {

    private final MainPage mainPage;

    public MainPageStepdefs(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    @Дано("открыть главную страницу")
    public void openPage() {
        mainPage.open();
    }


}
