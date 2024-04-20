package ru.merion.aqa.homeworks.lesson7;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.merion.aqa.homeworks.lesson7.page.TextInputPage;

public class Task2 {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        String text = new TextInputPage(driver).open().setButtonName("Merion").getButtonText();

        System.out.println("text = " + text);

        driver.quit();
    }
}
