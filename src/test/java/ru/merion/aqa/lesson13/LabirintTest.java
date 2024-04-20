package ru.merion.aqa.lesson13;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.merion.aqa.lesson13.ext.DriverResolver;
import ru.merion.aqa.lesson13.ext.SearchWordResolver;
import ru.merion.aqa.lesson7.page.CartPage;
import ru.merion.aqa.lesson7.page.MainPage;
import ru.merion.aqa.lesson7.page.ResultPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({DriverResolver.class, SearchWordResolver.class})
public class LabirintTest {

    @Test
    @DisplayName("Поиск товаров на сайте и добавление в корзину")
    @Tags({@Tag("positive"), @Tag("search")})
    public void positiveScenario(WebDriver driver, String word) {
        MainPage mainPage = openMainPage(driver);

        ResultPage resultPage = mainPage.header.searchFor(word);
        resultPage.addAllItemsToCart();
        String iconText = resultPage.header.getIconText();
        assertEquals("60", iconText);

        CartPage cartPage = resultPage.header.clickCartIcon();
        String counter = cartPage.getCartCounter();

        assertEquals("60 товаров", counter);
    }

    @Test
    @Tags({@Tag("negative"), @Tag("search")})
    @DisplayName("Поиск на сайте без результатов")
    public void emptySearchResult(WebDriver driver) {
        MainPage mainPage = openMainPage(driver);

        ResultPage resultPage = mainPage.header.searchFor("sdhfjgmnbvcxsdfg");
        String msg = resultPage.getEmptyResultMessage();
        assertEquals("Мы ничего не нашли по вашему запросу! Что делать?", msg);

        String iconText = resultPage.header.getIconText();
        assertEquals("0", iconText);

        CartPage cartPage = resultPage.header.clickCartIcon();
        String counter = cartPage.getEmptyCartMessage();

        assertTrue(counter.equalsIgnoreCase("ВАША КОРЗИНА ПУСТА. ПОЧЕМУ?"));
    }

    @Test
    public void test3() {
        System.out.println("test 3");
    }

    private MainPage openMainPage(WebDriver driver) {
        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
        mainPage.open();
        return mainPage;
    }
}
