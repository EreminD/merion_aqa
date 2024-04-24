package ru.merion.aqa.lesson12_hw;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.merion.aqa.lesson12_hw.page.CatalogPage;
import ru.merion.aqa.lesson12_hw.page.AuthPage;
import ru.merion.aqa.lesson12_hw.page.CartCheckoutPage;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {

    private WebDriver driver;

    @BeforeEach
    public void open(){
        driver = new ChromeDriver();
    }

    @AfterEach
    public void close(){
        if(driver != null){
            driver.quit();
        }
    }

    @Test
    public void happyPath() {
        AuthPage auth;
        CatalogPage catalog;

        Set<String> itemNames = new HashSet<>(); // из файла, по сети, из бд
        itemNames.add("Sauce Labs Backpack");
        itemNames.add("Sauce Labs Bolt T-Shirt");
        itemNames.add("Sauce Labs Onesie");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

        auth = new AuthPage(driver).open();
        catalog = auth.loginAs("standard_user", "secret_sauce");
        catalog.addItems(itemNames);

        String total = new CartCheckoutPage(driver)
                .open()
                .clickCheckout()
                .setContactData("Иван", "Иванов", "123457")
                .getTotalPrice();

        assertTrue(total.endsWith("$58.29"));
    }

}
