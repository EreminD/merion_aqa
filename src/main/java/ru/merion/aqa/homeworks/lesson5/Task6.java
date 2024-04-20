package ru.merion.aqa.homeworks.lesson5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task6 {

    public static void main(String[] args) {

        Set<String> itemNames = new HashSet<>(); // из файла, по сети, из бд
        itemNames.add("Sauce Labs Backpack");
        itemNames.add("Sauce Labs Bolt T-Shirt");
        itemNames.add("Sauce Labs Onesie");

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();

        List<WebElement> items = driver.findElements(By.cssSelector(".inventory_item"));

        for (WebElement item : items) {
            String productName = item.findElement(By.cssSelector(".inventory_item_name")).getText();
            if(itemNames.contains(productName)){
                item.findElement(By.cssSelector("button")).click();
            }
        }

        driver.get("https://www.saucedemo.com/cart.html");
        driver.findElement(By.cssSelector("#checkout")).click();

        driver.findElement(By.cssSelector("#first-name")).sendKeys("Иван");
        driver.findElement(By.cssSelector("#last-name")).sendKeys("Иванов");
        driver.findElement(By.cssSelector("#postal-code")).sendKeys("123456");

        driver.findElement(By.cssSelector("#continue")).click();

        String total = driver.findElement(By.cssSelector(".summary_info_label.summary_total_label")).getText();
        driver.quit();

        System.out.println(total);
    }

}
