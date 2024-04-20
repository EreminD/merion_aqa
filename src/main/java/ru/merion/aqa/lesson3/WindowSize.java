package ru.merion.aqa.lesson3;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import ru.merion.aqa.WebDriverFactory;

public class WindowSize {

    public static void main(String[] args) {

        WebDriver driver = WebDriverFactory.create("chrome");
        driver.get("https://habr.com/ru");

        Dimension size = driver.manage().window().getSize();
        System.out.println("size = " + size);

        driver.manage().window().setSize(new Dimension(550, 900));

        driver.quit();

    }

}
