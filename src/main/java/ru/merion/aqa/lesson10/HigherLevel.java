package ru.merion.aqa.lesson10;

import com.codeborne.selenide.Configuration;
import ru.merion.aqa.lesson10.higherlevel_page.CartPage;
import ru.merion.aqa.lesson10.higherlevel_page.MainPage;

public class HigherLevel {

    public static void main(String[] args) {
        Configuration.baseUrl = "https://www.labirint.ru";

        new MainPage()
                .open()
                .header()
                .searchFor("Java")
                .addAllItemsToCart();

        String price = new CartPage().open().getCartPrice();
        System.out.println(price);
    }
}
