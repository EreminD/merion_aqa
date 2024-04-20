package ru.merion.aqa.lesson24.page;

import io.qameta.allure.Step;
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

    @Step("Выполнить поиск по сайту: {term}")
    public ResultPage searchFor(String term) {
        searchField.sendKeys(term);
        form.submit();
        return PageFactory.initElements(driver, ResultPage.class);
    }

    @Step("Посмотреть счетчик товаров в корзине")
    public String getIconText() {
        return cartIcon.getText();
    }

    @Step("Перейти в корзину")
    public CartPage clickCartIcon() {
        cartIcon.click();
        return PageFactory.initElements(driver, CartPage.class);
    }

    public WebElement getCartIcon(){
        return this.cartIcon;
    }

}
