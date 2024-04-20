package ru.merion.aqa.lesson7.page;

import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://www.labirint.ru/");
    }
}
