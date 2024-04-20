package ru.merion.aqa.lesson24;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.merion.aqa.lesson24.ext.DriverResolver;
import ru.merion.aqa.lesson24.ext.MyLabel;
import ru.merion.aqa.lesson24.ext.SearchWordResolver;
import ru.merion.aqa.lesson24.page.CartPage;
import ru.merion.aqa.lesson24.page.MainPage;
import ru.merion.aqa.lesson24.page.ResultPage;

import java.util.Random;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.*;

@Owner("Dmitry Eremin")
@MyLabel("Hello!")
@Epic("Поиск по сайту")
@Feature("В шапке сайта есть поисковая строка")
@ExtendWith({DriverResolver.class, SearchWordResolver.class})
@DisplayName("Тесты на поиск по Лабиринту")
public class LabirintTest {

    private MainPage mainPage;

    @BeforeEach
    private void setUp(WebDriver driver) {
        mainPage = PageFactory.initElements(driver, MainPage.class);
        mainPage.open();
    }

    @AfterEach
    public void tearDown() {
        step("Зануляем mainpage", () -> mainPage = null);
    }

    @Test
    @Description("Test description")
    @DisplayName("Поиск товаров на сайте и добавление в корзину")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("LBRNT-456")
    @Story(value = "Как пользователь, я хочу видеть список книг по искомому слову, чтобы быстрее находить интересный мне товар")
    @Tags({@Tag("positive"), @Tag("search")})
    public void positiveScenario(String word) {
        ResultPage resultPage = step("Выполнить поиск по слову java", () -> mainPage.header.searchFor(word));

        step("на странице с результатами добавить все товары в корзину", () -> {
            resultPage.addAllItemsToCart();
            String iconText = resultPage.header.getIconText();
            assertEquals("60", iconText);
        });

        CartPage cartPage = step("Перейти в корзину", () -> resultPage.header.clickCartIcon());

        step("проверить, что в корзине 60 товаров", () -> {
            String counter = cartPage.getCartCounter();
            assertEquals("60 товаров", counter);
        });
    }

    @Test
    @Tags({@Tag("negative"), @Tag("search")})
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Поиск на сайте без результатов")
    @Issue("Labirint-980")
    @Story(value = "Как пользователь, я хочу видеть, что поиск не принес результатов, чтобы долго не ждать у пустой страницы")
    @Link(url = "https://habr.com/ru", name = "Habr")
    public void emptySearchResult() {
        ResultPage resultPage = mainPage.header.searchFor("sdhfjgmnbvcxsdfg");

        step("Проверить, что на экране отображается тескт 'Мы ничего не нашли по вашему запросу! Что делать?'",
                () -> assertEquals("Мы ничего не нашли по вашему запросу! Что делать?", resultPage.getEmptyResultMessage()));

        String iconText = resultPage.header.getIconText();

        step("Проверить, что счетчик товаров = 0", () -> assertEquals("0", iconText));

        CartPage cartPage = resultPage.header.clickCartIcon();
        String counter = cartPage.getEmptyCartMessage();

        step("Проверить, что текст отображается", () -> {
            if (new Random().nextDouble() < 0.5) {
                assertTrue(counter.equalsIgnoreCase("ВАША КОРЗИНА ПУСТА. ПОЧЕМУ?"));
            } else {
                assertFalse(counter.equalsIgnoreCase("ВАША КОРЗИНА ПУСТА. ПОЧЕМУ?"), "Не получили нужного на экране");
            }
        });
    }


}
