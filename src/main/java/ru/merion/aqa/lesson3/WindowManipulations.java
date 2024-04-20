package ru.merion.aqa.lesson3;

import org.openqa.selenium.WebDriver;
import ru.merion.aqa.WebDriverFactory;

public class WindowManipulations {

    public static void main(String[] args) {

        WebDriver driver = WebDriverFactory.create("chrome");

        driver.manage().window().minimize();
        driver.manage().window().maximize();
        driver.manage().window().fullscreen(); // F11

        driver.quit();

    }
}
