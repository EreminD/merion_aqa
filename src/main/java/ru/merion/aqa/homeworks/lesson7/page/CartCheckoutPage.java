package ru.merion.aqa.homeworks.lesson7.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartCheckoutPage {

    private final WebDriver driver;

    public CartCheckoutPage(WebDriver driver) {
        this.driver = driver;
    }


    public CartCheckoutPage open() {
        driver.get("https://www.saucedemo.com/cart.html");
        return this;
    }

    public CartCheckoutPage clickCheckout() {
        driver.findElement(By.cssSelector("#checkout")).click();
        return this;
    }

    public CartCheckoutPage setContactData(String firstName, String lastName, String zip) {
        driver.findElement(By.cssSelector("#first-name")).sendKeys(firstName);
        driver.findElement(By.cssSelector("#last-name")).sendKeys(lastName);
        driver.findElement(By.cssSelector("#postal-code")).sendKeys(zip);
        driver.findElement(By.cssSelector("#continue")).click();
        return this;
    }

    public String getTotalPrice() {
        return driver.findElement(By.cssSelector(".summary_info_label.summary_total_label")).getText();
    }
}
