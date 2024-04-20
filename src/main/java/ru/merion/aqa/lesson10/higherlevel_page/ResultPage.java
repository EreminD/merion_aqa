package ru.merion.aqa.lesson10.higherlevel_page;

import com.codeborne.selenide.ElementsCollection;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$;

public class ResultPage extends BasePage {
    private final ElementsCollection cards = $$(".product-card");

    public void addAllItemsToCart() {
        List<CardElement> cardsList = cards.asDynamicIterable().stream().map(CardElement::new).toList();
        cardsList.forEach(card -> {
            System.out.println(card.getTitle());
            card.addToCart();
        });
    }
}
