package ru.merion.aqa.lesson5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.merion.aqa.WebDriverFactory;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class ECDemo {

    public static void main(String[] args) {
        WebDriver driver = WebDriverFactory.create();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // дождаться исчезновения(!) элемента
        wait.until(stalenessOf(driver.findElement(By.cssSelector(""))));

        // дождаться точного количества элементов на странице
        List<WebElement> elements = wait.until(numberOfElementsToBe(By.cssSelector(""), 7));

        // дождаться видимости элемента
        wait.until(visibilityOf(driver.findElement(By.cssSelector("")))).click();

        // дождаться появления alert/confirm/prompt и перейти в него
        wait.until(alertIsPresent());

        // дождаться отсутствия или скрытия элемента
        wait.until(invisibilityOf(driver.findElement(By.cssSelector(""))));

        // дождаться наличия элемента в DOM
        wait.until(presenceOfElementLocated(By.cssSelector("")));

        // дождаться, что атрибут элемента содержит(!) значение
        wait.until(attributeContains(By.cssSelector(""), "class", "btn-success"));

        // проверить, что title страницы содержит подстроку
        wait.until(titleContains("Входящие (3)"));

        // проверить, что title страницы равен(!) строке
        wait.until(titleIs("Входящие (3)"));

        // проверить, что url страницы содержит подстроку
        wait.until(urlContains("https://"));

        // объединить ожидания
        wait.until(and(alertIsPresent(), urlToBe("/feed")));

        // проверить, что элемент (не)выбран
        wait.until(elementSelectionStateToBe(By.cssSelector(""), true));

        // проверить, что элемент кликабельный (видимый+enabled)
        wait.until(elementToBeClickable(By.cssSelector("")));

        // проверить, что элементов на странице строго больше, чем ...
        wait.until(numberOfElementsToBeMoreThan(By.cssSelector(""), 7));

        // проверить, что выполняется хотя бы одно условие
        wait.until(or(urlToBe(""), titleIs("")));

        // дождаться, что элемент содержит в тексте подстроку
        wait.until(textToBePresentInElementLocated(By.cssSelector(""), "test"));

        // дождаться в атрибуте value подстроку
        wait.until(textToBePresentInElementValue(By.cssSelector(""), "test"));

    }

}
