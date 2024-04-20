package ru.merion.aqa.homeworks.lesson4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task4 {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/entry_ad");
        Thread.sleep(3000);

        driver.findElement(By.cssSelector("#modal .modal-footer")).click();

        String content = driver.findElement(By.cssSelector("#content")).getText();
        System.out.println(content);

        driver.quit();

    }
}
