package ru.merion.aqa.homeworks.lesson4;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Task7 {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://google.com");

        driver.findElement(By.cssSelector("[name=q]")).sendKeys("Merion Academy wiki " + Keys.RETURN);

        driver.findElement(By.cssSelector("h3")).click();
        // =====

        if (driver.getCurrentUrl().startsWith("https://wiki.merionet.ru")) {
            System.out.println("Добро пожаловать в Merion Academy!");
        } else {
            System.out.println("Мы попали куда-то не туда...");
        }

        driver.quit();
    }

}
