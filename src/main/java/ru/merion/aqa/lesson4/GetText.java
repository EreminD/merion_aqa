package ru.merion.aqa.lesson4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.merion.aqa.WebDriverFactory;

public class GetText {

    public static void main(String[] args) {
        WebDriver driver = WebDriverFactory.create();

        driver.get("http://the-internet.herokuapp.com/");
        String text = driver.findElement(By.cssSelector("ul")).getText();

        System.out.println(text);
        driver.quit();
    }
}
