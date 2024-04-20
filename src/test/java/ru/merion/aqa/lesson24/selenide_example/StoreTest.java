package ru.merion.aqa.lesson24.selenide_example;

import com.codeborne.selenide.junit5.TextReportExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashSet;
import java.util.Set;

@ExtendWith({TextReportExtension.class})
public class StoreTest {
    @BeforeAll
    public static void setUp(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @Test
    public void test() {

        AuthPage auth;
        CatalogPage catalog;

        Set<String> itemNames = new HashSet<>(); // из файла, по сети, из бд
        itemNames.add("Sauce Labs Backpack");
        itemNames.add("Sauce Labs Bolt T-Shirt");
        itemNames.add("Sauce Labs Onesie");

        auth = new AuthPage().open();
        catalog = auth.loginAs("standard_user", "secret_sauce");
        catalog.addItems(itemNames);

        new CartCheckoutPage()
                .open()
                .clickCheckout()
                .setContactData("Иван", "Иванов", "123457")
                .checkTotalPrice("Total: $32.38");
    }

}
