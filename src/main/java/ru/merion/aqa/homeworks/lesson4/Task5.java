package ru.merion.aqa.homeworks.lesson4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task5 {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("http://the-internet.herokuapp.com/inputs");

        WebElement input = driver.findElement(By.cssSelector("input"));

        input.sendKeys("1000");
        input.clear();
        input.sendKeys("2000");

        driver.quit();
    }
}
