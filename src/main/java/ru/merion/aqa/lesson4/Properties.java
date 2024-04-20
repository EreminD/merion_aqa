package ru.merion.aqa.lesson4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.merion.aqa.WebDriverFactory;

public class Properties {

    public static void main(String[] args) {

        WebDriver driver = WebDriverFactory.create();
        driver.get("https://habr.com/ru/feed/");

        String target1 = driver.findElement(By.cssSelector(".tm-feature__link")).getAttribute("font-family");
        String target2 = driver.findElement(By.cssSelector(".tm-feature__link")).getDomProperty("font-family");
        String target3 = driver.findElement(By.cssSelector(".tm-feature__link")).getDomAttribute("font-family");
        String target4 = driver.findElement(By.cssSelector(".tm-feature__link")).getCssValue("font-family");

        String innerText = driver.findElement(By.cssSelector(".tm-feature__link")).getAttribute("innerText");
        System.out.println(innerText);

        driver.quit();

    }
}
