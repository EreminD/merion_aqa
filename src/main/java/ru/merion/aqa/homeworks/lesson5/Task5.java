package ru.merion.aqa.homeworks.lesson5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Task5 {

    public static void main(String[] args) {
        int timeout = 45;

        WebDriver driver = new ChromeDriver();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html");

        driver.findElement(By.cssSelector("#delay")).clear();
        driver.findElement(By.cssSelector("#delay")).sendKeys(String.valueOf(timeout));

        WebElement keyboard = driver.findElement(By.cssSelector(".keys"));

        keyboard.findElement(By.xpath("//*[text() = '7']")).click();
        keyboard.findElement(By.xpath("//*[text() = '+']")).click();
        keyboard.findElement(By.xpath("//*[text() = '8']")).click();
        keyboard.findElement(By.xpath("//*[text() = '=']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#spinner")));

        String result = driver.findElement(By.cssSelector(".screen")).getText();
        System.out.println(result);

        driver.quit();
    }
}
