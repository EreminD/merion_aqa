package ru.merion.aqa.homeworks.lesson4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task3 {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("http://uitestingplayground.com/classattr");
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

        driver.quit();
    }
}
