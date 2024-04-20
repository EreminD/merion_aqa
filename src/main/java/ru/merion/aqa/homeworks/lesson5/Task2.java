package ru.merion.aqa.homeworks.lesson5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task2 {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("http://uitestingplayground.com/textinput");

        driver.findElement(By.cssSelector("#newButtonName")).sendKeys("Merion");
        driver.findElement(By.cssSelector("#updatingButton")).click();
        String text = driver.findElement(By.cssSelector("#updatingButton")).getText();
        System.out.println(text);

        driver.quit();
    }
}
