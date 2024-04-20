package ru.merion.aqa.homeworks.lesson5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Task3 {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        wait.until(textToBePresentInElementLocated(By.cssSelector("#text"), "Done!"));
//        wait.until(invisibilityOfElementLocated(By.cssSelector("#spinner")));
//        wait.until(stalenessOf(driver.findElement(By.cssSelector("#spinner"))));
        wait.until(numberOfElementsToBeMoreThan(By.cssSelector("#image-container img"), 2));

        List<WebElement> images = driver.findElement(By.cssSelector("#image-container")).findElements(By.cssSelector("img"));
        String src = images.get(2).getAttribute("src");
        System.out.println(src);

        driver.quit();
    }
}