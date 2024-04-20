package ru.merion.aqa.lesson4;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.merion.aqa.WebDriverFactory;

import java.io.File;
import java.util.List;

public class WebElementInterface {

    public static void main(String[] args) {
        WebDriver driver = WebDriverFactory.create();

        driver.get("https://habr.com/ru");

        // кнопка Войти
        WebElement login = driver.findElement(By.cssSelector(".tm-header-user-menu__login"));

        // навигация
        WebElement nav = driver.findElement(By.cssSelector("nav"));
        List<WebElement> links = nav.findElements(By.cssSelector("a"));
        System.out.println(links.size());

        login.getScreenshotAs(OutputType.FILE).renameTo(new File("btn.png"));

        driver.quit();



    }
}
