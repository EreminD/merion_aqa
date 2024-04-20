package ru.merion.aqa.lesson24.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть главную страницу")
    public void open() {
        driver.get("https://www.labirint.ru/");
    }

}
