package ru.merion.aqa.lesson7;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.merion.aqa.lesson7.page.CartPage;
import ru.merion.aqa.lesson7.page.MainPage;
import ru.merion.aqa.lesson7.page.ResultPage;

public class LabirintScenario {

    public static void main(String[] args) {
        WebDriver driver = WDFactory.create();

        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
        mainPage.open();

        ResultPage resultPage = mainPage.header.searchFor("Java");
        resultPage.addAllItemsToCart();
        String iconText = resultPage.header.getIconText();
        System.out.println("iconText = " + iconText);

        CartPage cartPage = resultPage.header.clickCartIcon();
        String price = cartPage.getCartPrice();
        String counter = cartPage.getCartCounter();

        System.out.println("price = " + price);
        System.out.println("counter = " + counter);

        driver.quit();
    }
}
