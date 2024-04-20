package ru.merion.aqa.lesson24.selenide_example;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CartCheckoutPage {
    private final SelenideElement firstNameInput = $("#first-name");
    private final SelenideElement lastNameInput = $("#last-name");
    private final SelenideElement postalCode = $("#postal-code");
    private final SelenideElement continueButton = $("#continue");
    private final SelenideElement priceText = $(".summary_total_label");


    public CartCheckoutPage open() {
        Selenide.open("https://www.saucedemo.com/cart.html");
        return this;
    }

    public CartCheckoutPage clickCheckout() {
        $("#checkout").shouldBe(visible).click();
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

    public SelenideElement checkTotalPrice(String valueToBe) {
        return priceText.shouldHave(text(valueToBe));
    }
}
