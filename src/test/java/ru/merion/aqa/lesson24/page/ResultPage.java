package ru.merion.aqa.lesson24.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class ResultPage extends BasePage {
    @FindBy(css = "h1")
    private WebElement emptyResultMessage;

    @FindBy(css = ".product-card")
    private List<WebElement> cards;

    public ResultPage(WebDriver driver) {
        super(driver);
    }

    public void addAllItemsToCart() {
        int counter = 0;
        for (WebElement card : cards) {
            card.findElement(By.cssSelector(".buy-link")).click();
            counter++;
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(textToBePresentInElement(header.getCartIcon(), String.valueOf(counter)));
    }

    @Step("Получить сообщение о пустом результате поиска")
    public String getEmptyResultMessage() {
        return emptyResultMessage.getText();
    }
}
