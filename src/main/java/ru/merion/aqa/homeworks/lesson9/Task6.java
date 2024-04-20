package ru.merion.aqa.homeworks.lesson9;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Set;

import static com.codeborne.selenide.Selenide.*;

public class Task6 {

    public static void main(String[] args) {
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--guest");

        Set<String> itemNames = Set.of("Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt", "Sauce Labs Onesie");

        open("https://www.saucedemo.com/");
        $("#user-name").val("standard_user");
        $("#password").val("secret_sauce");
        $("#login-button").click();

        $$(".inventory_item").forEach(item -> {
            String productName = item.find(".inventory_item_name").text();
            if (itemNames.contains(productName)) {
                item.find("button").click();
            }
        });

        open("https://www.saucedemo.com/cart.html");
        $("#checkout").click();

        $("#first-name").val("Иван");
        $("#last-name").val("Иванов");
        $("#postal-code").val("123456");

        $("#continue").click();

        String total = $("[data-test=total-label]").text();

        System.out.println(total);
    }
}
