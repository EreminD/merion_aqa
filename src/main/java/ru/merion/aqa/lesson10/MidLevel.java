package ru.merion.aqa.lesson10;

import com.codeborne.selenide.Configuration;
import ru.merion.aqa.lesson10.midlevel_page.CartPage;
import ru.merion.aqa.lesson10.midlevel_page.MainPage;
import ru.merion.aqa.lesson10.midlevel_page.ResultPage;

public class MidLevel {

    public static void main(String[] args) {
        Configuration.baseUrl = "https://www.labirint.ru";

        MainPage main = new MainPage();
        main.open();
        main.searchFor("Java");

        ResultPage page = new ResultPage();
        page.addAllItemsToCart();

        CartPage cart = new CartPage();
        cart.open();
        String price = cart.getCartPrice().text();

        System.out.println(price);
    }
}
