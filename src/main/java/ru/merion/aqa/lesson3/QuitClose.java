package ru.merion.aqa.lesson3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.merion.aqa.WebDriverFactory;

public class QuitClose {

    public static void main(String[] args) {
        WebDriver driver = WebDriverFactory.create();

        driver.get("http://the-internet.herokuapp.com/windows");
        driver.findElement(By.cssSelector("#content a")).click();

        driver.close();
        driver.quit();
    }
}
