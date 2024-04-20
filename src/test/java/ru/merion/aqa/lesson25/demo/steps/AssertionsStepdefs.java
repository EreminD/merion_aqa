package ru.merion.aqa.lesson25.demo.steps;

import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.merion.aqa.WebDriverFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssertionsStepdefs {

    private final WebDriver driver;

    public AssertionsStepdefs(WebDriverFactory factory) {
        this.driver = factory.getOrCreate();
    }

    @Then("меня перекинуло на страницу с результатами для запроса {string}")
    public void checkIAmOnResultPage(String term) {
        assertTrue(driver.getCurrentUrl().startsWith("https://www.google.ru/search?q=" + term));
        assertEquals(term, driver.findElement(By.cssSelector("[name=q]")).getText());
    }

    @Then("я остался на странице {string}")
    public void checkIamOnMainPage(String url) {
        assertEquals(url, driver.getCurrentUrl());
    }
}
