package ru.merion.aqa.lesson7.page;

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

    public String getEmptyCartMessage(){
        return emptyCartMessage.getText();
    }
}
