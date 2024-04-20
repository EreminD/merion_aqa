package ru.merion.aqa.lesson24.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected final WebDriver driver;

    public HeaderElement header;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.header = PageFactory.initElements(driver, HeaderElement.class);
    }
}
