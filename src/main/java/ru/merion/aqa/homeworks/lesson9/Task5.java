package ru.merion.aqa.homeworks.lesson9;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Task5 {

    public static void main(String[] args) {
        int timeout = 6;

        open("https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html");

        $("#delay").val(Integer.toString(timeout));

        SelenideElement keyboard = $(".keys");

        keyboard.$x("*[text() = '7']").click();
        keyboard.$x("*[text() = '+']").click();
        keyboard.$x("*[text() = '8']").click();
        keyboard.$x("*[text() = '=']").click();

        $("#spinner").shouldNotBe(visible, Duration.ofSeconds(timeout+1));

        String result = $(".screen").text();
        System.out.println(result);
    }
}
