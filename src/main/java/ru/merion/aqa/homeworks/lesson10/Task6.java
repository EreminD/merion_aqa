package ru.merion.aqa.homeworks.lesson10;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.merion.aqa.homeworks.lesson10.page.AuthPage;
import ru.merion.aqa.homeworks.lesson10.page.CartCheckoutPage;
import ru.merion.aqa.homeworks.lesson10.page.CatalogPage;

import java.util.Set;

public class Task6 {

    public static void main(String[] args) {
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--guest");

        AuthPage auth;
        CatalogPage catalog;

        Set<String> itemNames = Set.of("Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt", "Sauce Labs Onesie");

        auth = new AuthPage().open();
        catalog = auth.loginAs("standard_user", "secret_sauce");
        catalog.addItems(itemNames);

        String total = new CartCheckoutPage()
                .open()
                .clickCheckout()
                .setContactData("Иван", "Иванов", "123457")
                .getTotalPrice();

        System.out.println(total);
    }
}
