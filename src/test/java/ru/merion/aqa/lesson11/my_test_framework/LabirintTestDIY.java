package ru.merion.aqa.lesson11.my_test_framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.merion.aqa.lesson7.page.CartPage;
import ru.merion.aqa.lesson7.page.MainPage;
import ru.merion.aqa.lesson7.page.ResultPage;

public class LabirintTestDIY {

    @TestDIY
    public void positiveScenario(WebDriver driver) {
        MainPage mainPage = openMainPage(driver);

        ResultPage resultPage = mainPage.header.searchFor("Java");
        resultPage.addAllItemsToCart();
        String iconText = resultPage.header.getIconText();
        if (iconText.equals("60")) {
            System.out.println("Проверили текст иконки");
        } else {
            System.err.println("Текст иконки не равен 60");
        }

        CartPage cartPage = resultPage.header.clickCartIcon();
        String counter = cartPage.getCartCounter();

        assert counter.equals("65 товаров");
    }

    public void test2(WebDriver driver) {
        MainPage mainPage = openMainPage(driver);

        ResultPage resultPage = mainPage.header.searchFor("sdhfjgmnbvcxsdfg");
        String msg = resultPage.getEmptyResultMessage();
        System.out.println(msg.equals("Мы ничего не нашли по вашему запросу! Что делать?"));

        String iconText = resultPage.header.getIconText();
        System.out.println(iconText.equals("0"));

        CartPage cartPage = resultPage.header.clickCartIcon();
        String counter = cartPage.getEmptyCartMessage();

        System.out.println(counter.equalsIgnoreCase("ВАША КОРЗИНА ПУСТА. ПОЧЕМУ?"));
    }

    @TestDIY()
    public void emptyScenario(WebDriver driver) {
        System.out.println("Вызвали третий тест");
    }

    public void test4(WebDriver driver) {
        System.out.println("Вызвали третий тест");
    }
    
    private MainPage openMainPage(WebDriver driver){
        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
        mainPage.open();
        return mainPage;
    }

}
