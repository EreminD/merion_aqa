package ru.merion.aqa.lesson25.demo.steps;

import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import ru.merion.aqa.WebDriverFactory;

public class BrowserStepdefs {
    private final WebDriver driver;

    public BrowserStepdefs(WebDriverFactory factory) {
        this.driver = factory.getOrCreate();
    }

    @And("открываю на сайт {string}")
    public void goTo(String url) {
        driver.get(url);
    }
}
