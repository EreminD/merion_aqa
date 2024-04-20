package ru.merion.aqa.lesson8;

import com.codeborne.selenide.SelenideWait;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ExplicitlyWaitSelenide {

    public static void main(String[] args) {
        open("http://uitestingplayground.com/progressbar");

        $("#startButton").click();

        new SelenideWait(WebDriverRunner.getWebDriver(), 100000, 100)
                .until(ExpectedConditions.textToBe(By.cssSelector("#progressBar"), "75%"));

        $("#stopButton").click();


        String result = $("#result").text();
        System.out.println("result = " + result);
    }
}
