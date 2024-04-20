package ru.merion.aqa.homeworks.lesson7.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AjaxPage {
    private final WebDriver driver;

    public AjaxPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("http://uitestingplayground.com/ajax");
    }

    public void clickTheButton() {
        driver.findElement(By.cssSelector("#ajaxButton")).click();
    }

    public String getContent() {
        return driver.findElement(By.cssSelector("#content p")).getText();
    }
}
