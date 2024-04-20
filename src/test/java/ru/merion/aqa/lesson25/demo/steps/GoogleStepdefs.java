package ru.merion.aqa.lesson25.demo.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import ru.merion.aqa.WebDriverFactory;

public class GoogleStepdefs {

    private final WebDriver driver;

    public GoogleStepdefs(WebDriverFactory factory) {
        this.driver = factory.getOrCreate();
    }

    @When("в поисковое поле ввожу {string}")
    public void sendSearchTerm(String term) {
        driver.findElement(By.cssSelector("[name=q]")).sendKeys(term);
    }

    @And("нажимаю на кнопку поиск")
    public void submitSearch() {
        driver.findElement(By.cssSelector("[name=q]")).sendKeys(Keys.RETURN);
    }
}
