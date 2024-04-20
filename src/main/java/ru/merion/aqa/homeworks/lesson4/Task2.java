package ru.merion.aqa.homeworks.lesson4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task2 {

    public static void main(String[] args) {
            WebDriver driver = new ChromeDriver();

            driver.get("http://uitestingplayground.com/dynamicid");

            driver.findElement(By.cssSelector(".btn[type=button]")).click();

            driver.quit();
    }

}
