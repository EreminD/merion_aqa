package ru.merion.aqa.lesson5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.merion.aqa.WebDriverFactory;

import java.time.Duration;
import java.util.List;

public class Progressbar {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = WebDriverFactory.create();
        driver.get("http://uitestingplayground.com/progressbar");

        WebElement button = driver.findElement(By.cssSelector("#startButton"));
        button.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ZERO);
        wait.until(ExpectedConditions.textToBe(By.cssSelector("#progressBar"), "75%"));

        driver.findElement(By.cssSelector("#stopButton")).click();

        String result = driver.findElement(By.cssSelector("#result")).getText();
        System.out.println("result = " + result);

        System.out.println("Finish");

        driver.quit();
    }
}
