package ru.merion.aqa.lesson3;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.merion.aqa.WebDriverFactory;

import java.io.File;

public class Screenshot {

    public static void main(String[] args) {
        WebDriver driver = WebDriverFactory.create();
        driver.get("https://play2048.co/");

        ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE).renameTo(new File("res.png"));

        driver.quit();
    }
}
