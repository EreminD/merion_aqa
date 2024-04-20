package ru.merion.aqa.lesson25.labirint.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class HeaderElement {

    private final SelenideElement form = $("#searchform");

    private final SelenideElement searchField = $("#search-field");

    private final SelenideElement cartIcon = $(".b-header-b-personal-e-icon-count-m-cart");

    public void searchFor(String term) {
        searchField.val(term);
        form.submit();
    }

    public void checkCartCounterShouldBe(String numToBe) {
        cartIcon.shouldHave(text(numToBe));
    }

    public void clickCartIcon() {
        cartIcon.click();
    }
}
