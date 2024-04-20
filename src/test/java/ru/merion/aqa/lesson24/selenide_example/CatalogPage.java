package ru.merion.aqa.lesson24.selenide_example;

import com.codeborne.selenide.ElementsCollection;

import java.util.Collection;

import static com.codeborne.selenide.Selenide.$$;

public class CatalogPage {
    private final ElementsCollection items = $$(".inventory_item");

    public void addItems(Collection<String> itemNames) {
        items.forEach(item -> {
            String productName = item.find(".inventory_item_name").text();
            if (itemNames.contains(productName)) {
                item.find("button").click();
            }
        });
    }
}
