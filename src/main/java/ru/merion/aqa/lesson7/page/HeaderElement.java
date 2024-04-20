package ru.merion.aqa.lesson7.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderElement {
    private final WebDriver driver;

    @FindBy(css = "#searchform")
    private WebElement form;

    @FindBy(css = "#search-field")
    private WebElement searchField;

    @FindBy(css = ".b-header-b-personal-e-icon-count-m-cart")
    private WebElement cartIcon;

    public HeaderElement(WebDriver driver) {
        this.driver = driver;
    }

    public ResultPage searchFor(String term) {
        searchField.sendKeys(term);
        form.submit();
        return PageFactory.initElements(driver, ResultPage.class);
    }

    public String getIconText() {
        return cartIcon.getText();
    }

    public CartPage clickCartIcon() {
        cartIcon.click();
        return PageFactory.initElements(driver, CartPage.class);
    }

    public WebElement getCartIcon(){
        return this.cartIcon;
    }

}
