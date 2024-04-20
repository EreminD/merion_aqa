package ru.merion.aqa.lesson25.demo.steps;

import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import ru.merion.aqa.WebDriverFactory;

public class CommonStepdefs {
    private final WebDriver driver;

    public CommonStepdefs(WebDriverFactory factory) {
        System.out.println("created");
        driver = factory.getOrCreate();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
