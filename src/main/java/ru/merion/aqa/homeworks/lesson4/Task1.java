package ru.merion.aqa.homeworks.lesson4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Task1 {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");

        WebElement add = driver.findElement(By.cssSelector( ".example button"));

        add.click();
        add.click();
        add.click();
        add.click();
        add.click();

        List<WebElement> buttons =
                driver.findElement(By.cssSelector("#elements")).findElements(By.cssSelector("button"));

        System.out.println(buttons.size());

        driver.quit();
    }
}
