package ru.merion.aqa.homeworks.lesson5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task4 {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/data-types.html");

        driver.findElement(By.cssSelector("[name=first-name]")).sendKeys("Иван");
        driver.findElement(By.cssSelector("[name=last-name]")).sendKeys("Петров");
        driver.findElement(By.cssSelector("[name=address]")).sendKeys("Ленина 55-3");
        driver.findElement(By.cssSelector("[name=city]")).sendKeys("Москва");
        driver.findElement(By.cssSelector("[name=country]")).sendKeys("Россия");
        driver.findElement(By.cssSelector("[name=job-position]")).sendKeys("QA");
        driver.findElement(By.cssSelector("[name=company]")).sendKeys("Merion");

        driver.findElement(By.cssSelector("[type=submit]")).click();

        String zip_code_bg = driver.findElement(By.cssSelector("#zip-code")).getCssValue("background-color");
        String email_bg = driver.findElement(By.cssSelector("#e-mail")).getCssValue("background-color");
        String phone_bg = driver.findElement(By.cssSelector("#phone")).getCssValue("background-color");

        System.out.println(zip_code_bg);
        System.out.println(email_bg);
        System.out.println(phone_bg);

        driver.quit();
    }

}
