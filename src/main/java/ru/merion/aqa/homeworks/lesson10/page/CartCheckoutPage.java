package ru.merion.aqa.homeworks.lesson10.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CartCheckoutPage {
    private final SelenideElement firstNameInput = $("#first-name");
    private final SelenideElement lastNameInput = $("#last-name");
    private final SelenideElement postalCode = $("#postal-code");
    private final SelenideElement continueButton = $("#continue");
    private final SelenideElement priceText = $("[data-test=total-label]");


    public CartCheckoutPage open() {
        Selenide.open("https://www.saucedemo.com/cart.html");
        return this;
    }

    public CartCheckoutPage clickCheckout() {
        $("#checkout").click();
        System.out.println("clicked");
        return this;
    }

    public CartCheckoutPage setContactData(String firstName, String lastName, String zip) {
        firstNameInput.val(firstName);
        lastNameInput.val(lastName);
        postalCode.val(zip);
        continueButton.click();
        return this;
    }

    public String getTotalPrice() {
        return priceText.getText();
    }
}
