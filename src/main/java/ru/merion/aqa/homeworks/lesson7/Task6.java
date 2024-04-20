package ru.merion.aqa.homeworks.lesson7;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.merion.aqa.homeworks.lesson7.page.AuthPage;
import ru.merion.aqa.homeworks.lesson7.page.CartCheckoutPage;
import ru.merion.aqa.homeworks.lesson7.page.CatalogPage;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

public class Task6 {

    public static void main(String[] args) {
        AuthPage auth;
        CatalogPage catalog;

        Set<String> itemNames = new HashSet<>(); // из файла, по сети, из бд
        itemNames.add("Sauce Labs Backpack");
        itemNames.add("Sauce Labs Bolt T-Shirt");
        itemNames.add("Sauce Labs Onesie");

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

        auth = new AuthPage(driver).open();
        catalog = auth.loginAs("standard_user", "secret_sauce");
        catalog.addItems(itemNames);

        String total = new CartCheckoutPage(driver)
                .open()
                .clickCheckout()
                .setContactData("Иван", "Иванов", "123457")
                .getTotalPrice();

        driver.quit();

        System.out.println(total);
    }

}
