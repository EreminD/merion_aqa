package ru.merion.aqa.lesson12_hw.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    private final WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public RegisterPage open() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/data-types.html");
        return this;
    }

    public void set(String field, String value) {
        driver.findElement(By.cssSelector("[name=" + field + "]")).sendKeys(value);
    }

    public void submitForm() {
        driver.findElement(By.cssSelector("[type=submit]")).click();
    }

    public String getCssProperty(String field, String cssProperty){
        return driver.findElement(By.cssSelector(field)).getCssValue(cssProperty);
    }
}
