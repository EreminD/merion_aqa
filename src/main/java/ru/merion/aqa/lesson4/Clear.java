package ru.merion.aqa.lesson4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.merion.aqa.WebDriverFactory;

public class Clear {

    public static void main(String[] args) {
        WebDriver driver = WebDriverFactory.create();

        driver.get("http://the-internet.herokuapp.com/inputs");

        driver.findElement(By.cssSelector("input")).sendKeys("12345");
        driver.findElement(By.cssSelector("input")).clear();

        driver.findElement(By.cssSelector("input")).sendKeys("765432");

        driver.quit();
    }
}
