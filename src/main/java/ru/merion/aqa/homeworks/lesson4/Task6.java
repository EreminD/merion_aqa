package ru.merion.aqa.homeworks.lesson4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task6 {

    public static void main(String[] args) {
        String login = "tomsmith";
        String password = "SuperSecretPassword!";

        WebDriver driver = new ChromeDriver();

        driver.get("http://the-internet.herokuapp.com/login");
        driver.findElement(By.cssSelector("#username")).sendKeys(login);
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        driver.findElement(By.cssSelector("button.radius")).click();

        String flash = driver.findElement(By.cssSelector("#flash")).getText();
        System.out.println(flash);

        driver.quit();
    }
}
