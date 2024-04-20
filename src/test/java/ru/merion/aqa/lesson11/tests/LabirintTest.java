package ru.merion.aqa.lesson11.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.merion.aqa.lesson7.WDFactory;
import ru.merion.aqa.lesson7.page.CartPage;
import ru.merion.aqa.lesson7.page.MainPage;
import ru.merion.aqa.lesson7.page.ResultPage;

public class LabirintTest {
    private WebDriver driver;

    @BeforeAll
    public static void globalSetUp(){
        System.out.println("Run tests");
    }

    @AfterAll
    public static void globalTearDown(){
        System.out.println("Finish test run");
    }


    @BeforeEach
    public void setUp() {
        driver = WDFactory.create();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Поиск товаров на сайте и добавление в корзину")
    @Tags({@Tag("positive"), @Tag("search")})
    public void positiveScenario() {
        MainPage mainPage = openMainPage(driver);

        ResultPage resultPage = mainPage.header.searchFor("Java");
        resultPage.addAllItemsToCart();
        String iconText = resultPage.header.getIconText();
        assert iconText.equals("60");

        CartPage cartPage = resultPage.header.clickCartIcon();
        String counter = cartPage.getCartCounter();

        assert counter.equals("60 товаров");
    }

    @Test
    @Tags({@Tag("negative"), @Tag("search")})
    @DisplayName("Поиск на сайте без результатов")
    public void emptySearchResult() {
        MainPage mainPage = openMainPage(driver);

        ResultPage resultPage = mainPage.header.searchFor("sdhfjgmnbvcxsdfg");
        String msg = resultPage.getEmptyResultMessage();
        assert msg.equals("Мы ничего не нашли по вашему запросу! Что делать?");

        String iconText = resultPage.header.getIconText();
        assert iconText.equals("0");

        CartPage cartPage = resultPage.header.clickCartIcon();
        String counter = cartPage.getEmptyCartMessage();

        assert counter.equalsIgnoreCase("ВАША КОРЗИНА ПУСТА. ПОЧЕМУ?");
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
