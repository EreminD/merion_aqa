package ru.merion.aqa.lesson24.page;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {
    @FindBy(css = "#basket-default-prod-count2")
    private WebElement productCounter;
    @FindBy(css = "#basket-default-sumprice-discount")
    private WebElement totalPrice;

    @FindBy(css = ".g-alttext-small.g-alttext-grey.g-alttext-head")
    private WebElement emptyCartMessage;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getCartCounter() {
        return productCounter.getText();
    }

    public String getCartPrice() {
        return totalPrice.getText();
    }

    @Step("Прочитать уведомление о пустой корзине")
    public String getEmptyCartMessage(){
        getScreenshot();
        return emptyCartMessage.getText();
    }

    @Attachment(value = "pageScreen.png", type = "image/png", fileExtension = ".png")
    public byte[] getScreenshot(){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
}
