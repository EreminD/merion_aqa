package ru.merion.aqa.lesson5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CustomEC {

    public static ExpectedCondition<WebElement> textContainsAfterRefresh(By locator, String textToContain) {
        return new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                driver.navigate().refresh();
                WebElement element = driver.findElement(locator);
                String text = element.getText();
                return text.contains(textToContain) ? element : null;
            }

            @Override
            public String toString() {
                return "Элемент с локатором " + locator + " содержит текст \"" + textToContain + "\"";
            }
        };
    }
}
